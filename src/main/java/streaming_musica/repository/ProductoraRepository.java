package streaming_musica.repository;

import org.springframework.stereotype.Repository;
import streaming_musica.model.Productora;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoraRepository {

    private final List<Productora> productoras = new ArrayList<>();

    public List<Productora> obtenerTodas() {
        return productoras;
    }

    public void guardar(Productora productora) {
        productoras.add(productora);
    }

    public Optional<Productora> buscarPorId(String id) {
        return productoras.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Productora> buscarPorNombre(String nombre) {
        return productoras.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}