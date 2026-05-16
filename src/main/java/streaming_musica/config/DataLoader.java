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

        Productora universal = new Productora("Universal Music", "USA");
        Productora warner = new Productora("Warner Music", "USA");
        Productora sony = new Productora("Sony Music", "USA");

        productoraService.guardar(universal);
        productoraService.guardar(warner);
        productoraService.guardar(sony);

        Artista queen = new Artista("Queen", "UK");
        Artista duaLipa = new Artista("Dua Lipa", "UK");
        Artista eminem = new Artista("Eminem", "USA");
        Artista coldplay = new Artista("Coldplay", "UK");
        Artista bizarrap = new Artista("Bizarrap", "Argentina");

        artistaService.guardar(queen);
        artistaService.guardar(duaLipa);
        artistaService.guardar(eminem);
        artistaService.guardar(coldplay);
        artistaService.guardar(bizarrap);

        Album opera = new Album("A Night at the Opera", queen, universal, LocalDate.of(1975, 11, 21));
        Album futureNostalgia = new Album("Future Nostalgia", duaLipa, warner, LocalDate.of(2020, 3, 27));
        Album curtainCall = new Album("Curtain Call", eminem, universal, LocalDate.of(2005, 12, 6));
        Album musicOfSpheres = new Album("Music of the Spheres", coldplay, warner, LocalDate.of(2021, 10, 15));
        Album bzrpSessions = new Album("BZRP Music Sessions", bizarrap, sony, LocalDate.of(2024, 1, 1));

        albumService.guardar(opera);
        albumService.guardar(futureNostalgia);
        albumService.guardar(curtainCall);
        albumService.guardar(musicOfSpheres);
        albumService.guardar(bzrpSessions);

        Cancion c1 = new Cancion("Bohemian Rhapsody", queen, opera, Genero.Rock, 354, 4.9, LocalDate.of(1975, 10, 31));
        Cancion c2 = new Cancion("Love of My Life", queen, opera, Genero.Rock, 220, 4.7, LocalDate.of(1975, 11, 21));
        Cancion c3 = new Cancion("Don't Start Now", duaLipa, futureNostalgia, Genero.Pop, 183, 4.6, LocalDate.of(2019, 10, 31));
        Cancion c4 = new Cancion("Levitating", duaLipa, futureNostalgia, Genero.Pop, 203, 4.5, LocalDate.of(2020, 3, 27));
        Cancion c5 = new Cancion("Lose Yourself", eminem, curtainCall, Genero.Hip_Hop, 326, 4.8, LocalDate.of(2002, 10, 28));
        Cancion c6 = new Cancion("Mockingbird", eminem, curtainCall, Genero.Hip_Hop, 251, 4.7, LocalDate.of(2004, 4, 25));
        Cancion c7 = new Cancion("Higher Power", coldplay, musicOfSpheres, Genero.Pop, 211, 4.1, LocalDate.of(2021, 5, 7));
        Cancion c8 = new Cancion("My Universe", coldplay, musicOfSpheres, Genero.Pop, 228, 4.3, LocalDate.of(2021, 9, 24));
        Cancion c9 = new Cancion("BZRP Music Session 57", bizarrap, bzrpSessions, Genero.Trap, 185, 4.4, LocalDate.of(2024, 10, 4));
        Cancion c10 = new Cancion("BZRP Music Session 60", bizarrap, bzrpSessions, Genero.Trap, 190, 4.2, LocalDate.of(2025, 3, 20));

        reproducirVariasVeces(c1, 900);
        reproducirVariasVeces(c2, 500);
        reproducirVariasVeces(c3, 1200);
        reproducirVariasVeces(c4, 1500);
        reproducirVariasVeces(c5, 2000);
        reproducirVariasVeces(c6, 1300);
        reproducirVariasVeces(c7, 700);
        reproducirVariasVeces(c8, 850);
        reproducirVariasVeces(c9, 300);
        reproducirVariasVeces(c10, 100);

        cancionService.guardar(c1);
        cancionService.guardar(c2);
        cancionService.guardar(c3);
        cancionService.guardar(c4);
        cancionService.guardar(c5);
        cancionService.guardar(c6);
        cancionService.guardar(c7);
        cancionService.guardar(c8);
        cancionService.guardar(c9);
        cancionService.guardar(c10);

        System.out.println("Datos cargados correctamente.");
    }

    private void reproducirVariasVeces(Cancion cancion, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            cancion.reproducir();
    }
}
}