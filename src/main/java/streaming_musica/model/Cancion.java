package streaming_musica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cancion {
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private Artista artista;
    private Album album;
    private Genero genero;
    private int duracionSegundos;;
    private AtomicInteger reproducciones = new AtomicInteger(0);
    private double rating;
    private LocalDate fechaPublicacion;

    public void reproducir(){
        reproducciones.incrementAndGet();
    }
}
