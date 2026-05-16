package streaming_musica.service;

import org.springframework.stereotype.Service;
import streaming_musica.model.Album;
import streaming_musica.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository repositorio;

    public AlbumService(AlbumRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Album> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public void guardar(Album album) {
        repositorio.guardar(album);
    }

    public Optional<Album> buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Album> buscarPorTitulo(String titulo) {
        return repositorio.buscarPorTitulo(titulo);
    }
}