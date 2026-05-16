package streaming_musica.strategy;

import streaming_musica.model.Cancion;

import java.util.List;

public interface EstrategiaRecomendacion {

    List<Cancion> recomendar(List<Cancion> catalogo, Cancion base);
}