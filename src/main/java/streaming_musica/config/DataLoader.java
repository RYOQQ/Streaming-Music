package streaming_musica.config;

import streaming_musica.model.*;
import streaming_musica.service.ArtistaService;
import streaming_musica.service.CancionService;
import streaming_musica.service.AlbumService;
import streaming_musica.service.ProductoraService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CancionService cancionService;
    private final ArtistaService artistaService;
    private final AlbumService albumService;
    private final ProductoraService productoraService;

    public DataLoader(CancionService cancionService, ArtistaService artistaService, AlbumService albumService, ProductoraService productoraService) {
        this.cancionService = cancionService;
        this.artistaService = artistaService;
        this.albumService = albumService;
        this.productoraService = productoraService;
    }

    @Override
    public void run(String... args) {

        Productora productora = new Productora("Universal Music", "USA");
        productoraService.guardar(productora);

        Artista queen = new Artista("Queen", "UK");
        artistaService.guardar(queen);

        Album album = new Album(
            "A Night at the Opera",
            queen,
            productora,
            LocalDate.of(1975, 11, 21)
        );
        albumService.guardar(album);

        Cancion c1 = new Cancion(
            "Bohemian Rhapsody",
            queen,
            album,
            Genero.Rock,
            354,
            4.9,
            LocalDate.of(1975, 10, 31)
        );

        Cancion c2 = new Cancion(
            "Love of My Life",
            queen,
            album,
            Genero.Rock,
            220,
            4.7,
            LocalDate.of(1975, 11, 21)
        );

        c1.reproducir();
        c1.reproducir();
        c1.reproducir();

        c2.reproducir();

        cancionService.guardar(c1);
        cancionService.guardar(c2);

        System.out.println("Datos cargados correctamente.");
    }
}