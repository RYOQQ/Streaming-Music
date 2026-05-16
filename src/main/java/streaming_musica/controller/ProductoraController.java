package streaming_musica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streaming_musica.model.Productora;
import streaming_musica.service.ProductoraService;

import java.util.List;

@RestController
@RequestMapping("/api/productoras")
public class ProductoraController {

    private final ProductoraService productoraService;

    public ProductoraController(ProductoraService productoraService) {
        this.productoraService = productoraService;
    }

    @GetMapping
    public ResponseEntity<List<Productora>> listarTodas() {
        return ResponseEntity.ok(productoraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productora> buscarPorId(@PathVariable String id) {
        return productoraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Productora>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoraService.buscarPorNombre(nombre));
    }
}