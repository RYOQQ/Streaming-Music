package streaming_musica.repository;

import streaming_musica.model.Cancion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CancionRepository{

    private final List<Cancion> canciones = new ArrayList<>();

    public List<Cancion> obtenerTodas() {
        return canciones;
    }

    public void guardar(Cancion cancion) {
        canciones.add(cancion);
    }

    public Optional<Cancion> buscarPorId(String id) {
        return canciones.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();
    }

    public void eliminar(String id) {
        canciones.removeIf(c -> c.getId().equals(id));
    }
}