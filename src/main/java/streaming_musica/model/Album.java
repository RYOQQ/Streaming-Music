package streaming_musica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Album {
    //Atributos
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private Artista artista;
    private Productora productora;
    private LocalDate fechaPublicacion;
    private List<Cancion> canciones = new ArrayList<>();

    //Constructor
    public Album(String titulo, Artista artista, Productora productora, LocalDate fechaPublicacion){
        this.titulo = titulo;
        this.artista = artista;
        this.productora = productora;
        this.fechaPublicacion = fechaPublicacion;
    }
}
