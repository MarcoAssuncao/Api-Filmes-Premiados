package com.filmes.indicadospremiados.converter;

import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import org.springframework.stereotype.Component;

@Component
public class FilmePremiadoConverter {

    public FilmesPremiadosDTO converterTOFilme(FilmeDTO primeiroFilme,
                                               FilmeDTO ultimoFilme) {
        return FilmesPremiadosDTO
                .builder()
                .producer(primeiroFilme.getProducers())
                .previousWin(primeiroFilme.getYear())
                .followingWin(ultimoFilme.getYear())
                .interval(ultimoFilme.getYear() - primeiroFilme.getYear())
                .build();
    }
}
