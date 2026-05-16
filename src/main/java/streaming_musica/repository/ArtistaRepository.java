package streaming_musica.repository;

import org.springframework.stereotype.Repository;
import streaming_musica.model.Artista;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistaRepository {

    private final List<Artista> artistas = new ArrayList<>();

    public List<Artista> obtenerTodos() {
        return artistas;
    }

    public void guardar(Artista artista) {
        artistas.add(artista);
    }

    public Optional<Artista> buscarPorId(String id) {
        return artistas.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Artista> buscarPorNombre(String nombre) {
        return artistas.stream()
                .filter(a -> a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}