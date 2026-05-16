package streaming_musica.service;

import org.springframework.stereotype.Service;
import streaming_musica.model.Cancion;
import streaming_musica.model.Genero;
import streaming_musica.repository.CancionRepository;

import streaming_musica.strategy.EstrategiaRecomendacion;
import streaming_musica.strategy.RecomendacionPorGenero;
import streaming_musica.strategy.RecomendacionPorPopularidad;
import streaming_musica.strategy.RecomendacionDescubrimiento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CancionService {

    private final CancionRepository repositorio;

    public CancionService(CancionRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Cancion> obtenerTodas() {
        return repositorio.obtenerTodas();
    }

    public void guardar(Cancion cancion) {
        repositorio.guardar(cancion);
    }

    public Optional<Cancion> buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Cancion> filtrarPorGenero(Genero genero) {
        return repositorio.obtenerTodas()
            .stream()
            .filter(c -> c.getGenero() == genero)
            .toList();
    }

    public List<Cancion> filtrarCompuesto(
            Genero genero,
            String artista,
            int anioDesde,
            int anioHasta,
            double ratingMinimo) {

        return repositorio.obtenerTodas()
            .stream()
            .filter(c -> genero == null || c.getGenero() == genero)
            .filter(c -> artista == null || c.getArtista().getNombre().equalsIgnoreCase(artista))
            .filter(c -> c.getFechaPublicacion().getYear() >= anioDesde)
            .filter(c -> c.getFechaPublicacion().getYear() <= anioHasta)
            .filter(c -> c.getRating() >= ratingMinimo)
            .toList();
    }

    public List<Cancion> top10MasReproducidas() {
        return repositorio.obtenerTodas()
                .stream()
                .sorted(Comparator.comparingInt(
                    (Cancion c) -> c.getReproducciones().get()).reversed())
                .limit(10)
                .toList();
    }

    public Map<Genero, Double> promedioDuracionPorGenero() {
        return repositorio.obtenerTodas()
                .stream()
                .collect(Collectors.groupingBy(
                    Cancion::getGenero,
                    Collectors.averagingInt(Cancion::getDuracionSegundos)
                ));
    }

    public Optional<Cancion> cancionMasPopular() {
        return repositorio.obtenerTodas()
                .stream()
                .max(Comparator.comparingInt(
                    c -> c.getReproducciones().get()));
    }

    public Optional<String> artistaMasPopular() {
        return repositorio.obtenerTodas()
                .stream()
                .collect(Collectors.groupingBy(
                    c -> c.getArtista().getNombre(),
                    Collectors.summingInt(c -> c.getReproducciones().get())
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public Map<Integer, List<Cancion>> distribucionPorDecadas() {
        return repositorio.obtenerTodas()
            .stream()
            .collect(Collectors.groupingBy(
                c -> (c.getFechaPublicacion().getYear() / 10) * 10
            ));
    }

    public Optional<Cancion> busquedaBinariaPorTitulo(String titulo) {
        List<Cancion> cancionesOrdenadas = repositorio.obtenerTodas()
            .stream()
            .sorted(Comparator.comparing(Cancion::getTitulo))
            .toList();

        int izquierda = 0;
        int derecha = cancionesOrdenadas.size() - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            Cancion actual = cancionesOrdenadas.get(medio);

            int comparacion = actual.getTitulo().compareToIgnoreCase(titulo);

            if (comparacion == 0) {
                return Optional.of(actual);
            }

            if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return Optional.empty();
    }

    public List<Cancion> ordenarPorArtistaYFecha() {
        return repositorio.obtenerTodas()
            .stream()
            .sorted(
                Comparator.comparing((Cancion c) -> c.getArtista().getNombre())
                    .thenComparing(Cancion::getFechaPublicacion)
                    .reversed()
                )
            .toList();
    }

    public List<Cancion> busquedaLinealConPredicados(
            Genero genero,
            int anioMayorA,
            double ratingMayorA) {

        return repositorio.obtenerTodas()
            .stream()
            .filter(c -> c.getGenero() == genero)
            .filter(c -> c.getFechaPublicacion().getYear() > anioMayorA)
            .filter(c -> c.getRating() > ratingMayorA)
            .toList();
    }

    public List<Cancion> playlistAutomatica(int minutosObjetivo) {
        int segundosObjetivo = minutosObjetivo * 60;

        return buscarPlaylistRecursiva(
            repositorio.obtenerTodas(),
            segundosObjetivo,
            0,
            new ArrayList<>()
        );
    }

    private List<Cancion> buscarPlaylistRecursiva(
            List<Cancion> canciones,
            int segundosRestantes,
            int indice,
            List<Cancion> seleccionadas) {

        if (segundosRestantes == 0) {
            return seleccionadas;
        }

        if (segundosRestantes < 0 || indice >= canciones.size()) {
            return new ArrayList<>();
        }

        Cancion actual = canciones.get(indice);

        List<Cancion> conActual = new ArrayList<>(seleccionadas);
        conActual.add(actual);

        List<Cancion> resultadoConActual = buscarPlaylistRecursiva(
            canciones,
            segundosRestantes - actual.getDuracionSegundos(),
            indice + 1,
            conActual
        );

        if (!resultadoConActual.isEmpty()) {
            return resultadoConActual;
        }

        return buscarPlaylistRecursiva(
            canciones,
            segundosRestantes,
            indice + 1,
            seleccionadas
        );
    }

    public List<Cancion> recomendar(String idCancionBase, String tipo) {
        Optional<Cancion> cancionBase = buscarPorId(idCancionBase);

        if (cancionBase.isEmpty()) {
            return new ArrayList<>();
        }

        EstrategiaRecomendacion estrategia;

        switch (tipo.toLowerCase()) {
            case "genero":
                estrategia = new RecomendacionPorGenero();
                break;

            case "popularidad":
                estrategia = new RecomendacionPorPopularidad();
                break;

            case "descubrimiento":
                estrategia = new RecomendacionDescubrimiento();
                break;

            default:
                estrategia = new RecomendacionPorGenero();
                break;
        }

        return estrategia.recomendar(
            repositorio.obtenerTodas(),
            cancionBase.get()
        );
    }

    public Optional<Cancion> reproducirConcurrentemente(String id, int cantidadHilos, int reproduccionesPorHilo) {
        Optional<Cancion> cancionBuscada = buscarPorId(id);

        if (cancionBuscada.isEmpty()) {
            return Optional.empty();
        }

        Cancion cancion = cancionBuscada.get();

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < cantidadHilos; i++) {
            Thread hilo = new Thread(() -> {
                for (int j = 0; j < reproduccionesPorHilo; j++) {
                    cancion.reproducir();
                }
            });

            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return Optional.of(cancion);
    }
}