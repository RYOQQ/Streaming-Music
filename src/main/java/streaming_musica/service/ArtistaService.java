package streaming_musica.service;

import org.springframework.stereotype.Service;
import streaming_musica.model.Artista;
import streaming_musica.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository repositorio;

    public ArtistaService(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Artista> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public void guardar(Artista artista) {
        repositorio.guardar(artista);
    }

    public Optional<Artista> buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Artista> buscarPorNombre(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }
}