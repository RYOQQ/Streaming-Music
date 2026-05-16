package streaming_musica.service;

import org.springframework.stereotype.Service;
import streaming_musica.model.Productora;
import streaming_musica.repository.ProductoraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoraService {

    private final ProductoraRepository repositorio;

    public ProductoraService(ProductoraRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Productora> obtenerTodas() {
        return repositorio.obtenerTodas();
    }

    public void guardar(Productora productora) {
        repositorio.guardar(productora);
    }

    public Optional<Productora> buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Productora> buscarPorNombre(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }
}