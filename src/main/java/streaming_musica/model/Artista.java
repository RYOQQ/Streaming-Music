package streaming_musica.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Artista {
    //Atributos
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private String pais;
    private List<Cancion> canciones = new ArrayList<>();

    //Constuctor
    public Artista(String nombre, String pais){
        this.nombre = nombre;
        this.pais = pais;
    }
}
