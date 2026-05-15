package streaming_musica.service;

import org.springframework.stereotype.Service;

import streaming_musica.model.Cancion;
import streaming_musica.model.Genero;
import streaming_musica.repository.CancionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CancionService {
    private final CancionRepository repositorio;

    public CancionService(CancionRepository repositorio){
        this.repositorio = repositorio;
    }

    public List<Cancion> obtenerTodas(){
        return repositorio.obtenerTodas();
    }

    public void guardar(Cancion cancion){
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

    public List<Cancion> top10MasReproducidas() {
        return repositorio.obtenerTodas()
            .stream()
            .sorted(Comparator.comparingInt(
                (Cancion c) -> c.getReproducciones().get())
                .reversed())
            
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
}
