package streaming_musica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import streaming_musica.model.Cancion;
import streaming_musica.model.Genero;
import streaming_musica.service.CancionService;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> listarTodas() {
        return ResponseEntity.ok(cancionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> buscarPorId(@PathVariable String id) {
        return cancionService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Cancion>> filtrarPorGenero(@PathVariable Genero genero) {
        return ResponseEntity.ok(cancionService.filtrarPorGenero(genero));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Cancion>> top10MasReproducidas() {
        return ResponseEntity.ok(cancionService.top10MasReproducidas());
    }

    @GetMapping("/popular")
    public ResponseEntity<Cancion> cancionMasPopular() {
        return cancionService.cancionMasPopular()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> busquedaFiltrada(
        @RequestParam(required = false) Genero genero,
        @RequestParam(required = false) String artista,
        @RequestParam(defaultValue = "0") int anioDesde,
        @RequestParam(defaultValue = "9999") int anioHasta,
        @RequestParam(defaultValue = "0.0") double ratingMinimo) {

        return ResponseEntity.ok(
            cancionService.filtrarCompuesto(
                genero,
                artista,
                anioDesde,
                anioHasta,
                ratingMinimo
            )
        );
    }

    @PostMapping("/{id}/reproducir")
    public ResponseEntity<Cancion> reproducir(@PathVariable String id) {
        return cancionService.buscarPorId(id)
            .map(cancion -> {
                cancion.reproducir();
                return ResponseEntity.ok(cancion);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/binaria")
    public ResponseEntity<Cancion> buscarBinariaPorTitulo(@RequestParam String titulo) {
        return cancionService.busquedaBinariaPorTitulo(titulo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ordenadas")
    public ResponseEntity<List<Cancion>> ordenarPorArtistaYFecha() {
        return ResponseEntity.ok(cancionService.ordenarPorArtistaYFecha());
    }

    @GetMapping("/lineal")
    public ResponseEntity<List<Cancion>> busquedaLineal(
        @RequestParam Genero genero,
        @RequestParam int anioMayorA,
        @RequestParam double ratingMayorA) {

        return ResponseEntity.ok(
            cancionService.busquedaLinealConPredicados(
                genero,
                anioMayorA,
                ratingMayorA
            )
        );
    }

    @GetMapping("/playlist")
    public ResponseEntity<List<Cancion>> playlistAutomatica(@RequestParam int minutos) {
        return ResponseEntity.ok(cancionService.playlistAutomatica(minutos));
    }
}
