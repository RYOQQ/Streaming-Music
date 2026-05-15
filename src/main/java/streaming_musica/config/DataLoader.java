package streaming_musica.config;

import streaming_musica.model.*;
import streaming_musica.service.CancionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CancionService cancionService;

    public DataLoader(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @Override
    public void run(String... args) {

        Productora productora = new Productora("Universal Music", "USA");

        Artista queen = new Artista("Queen", "UK");

        Album album = new Album(
            "A Night at the Opera",
            queen,
            productora,
            LocalDate.of(1975, 11, 21)
        );

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