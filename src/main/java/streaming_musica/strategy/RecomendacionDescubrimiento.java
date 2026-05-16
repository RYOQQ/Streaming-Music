package streaming_musica.strategy;

import streaming_musica.model.Cancion;

import java.time.LocalDate;
import java.util.List;

public class RecomendacionDescubrimiento implements EstrategiaRecomendacion {

    @Override
    public List<Cancion> recomendar(List<Cancion> catalogo, Cancion base) {
        LocalDate fechaLimite = LocalDate.now().minusYears(2);

        return catalogo.stream()
                .filter(c -> c.getReproducciones().get() < 1000)
                .filter(c -> c.getFechaPublicacion().isAfter(fechaLimite))
                .filter(c -> c.getGenero() != base.getGenero())
                .toList();
    }
}