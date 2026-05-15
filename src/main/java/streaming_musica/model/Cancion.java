package streaming_musica.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor

public class Cancion {
    //Atributos
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private Artista artista;
    private Album album;
    private Genero genero;
    private int duracionSegundos;
    private AtomicInteger reproducciones = new AtomicInteger(0);
    private double rating;
    private LocalDate fechaPublicacion;

    //Constructor
    public Cancion(String titulo, Artista artista, Album album, Genero genero, int duracionSegundos, double rating, LocalDate fechaPublicacion){
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracionSegundos = duracionSegundos;
        this.rating = rating;
        this.fechaPublicacion = fechaPublicacion;
    }

    public void reproducir(){
        reproducciones.incrementAndGet();
    }

    public int getCantidadRepro(){
        return reproducciones.get();
    }
}
