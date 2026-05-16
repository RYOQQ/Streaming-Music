package streaming_musica.strategy;

import streaming_musica.model.Cancion;

import java.util.Comparator;
import java.util.List;

public class RecomendacionPorGenero implements EstrategiaRecomendacion {

    @Override
    public List<Cancion> recomendar(List<Cancion> catalogo, Cancion base) {
        return catalogo.stream()
                .filter(c -> c.getGenero() == base.getGenero())
                .filter(c -> !c.getId().equals(base.getId()))
                .sorted(Comparator.comparingDouble(Cancion::getRating).reversed())
                .toList();
    }
}