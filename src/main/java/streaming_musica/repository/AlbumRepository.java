package streaming_musica.repository;

import org.springframework.stereotype.Repository;
import streaming_musica.model.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    private final List<Album> albumes = new ArrayList<>();

    public List<Album> obtenerTodos() {
        return albumes;
    }

    public void guardar(Album album) {
        albumes.add(album);
    }

    public Optional<Album> buscarPorId(String id) {
        return albumes.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Album> buscarPorTitulo(String titulo) {
        return albumes.stream()
                .filter(a -> a.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }
}