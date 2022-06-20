package com.filmes.indicadospremiados.converter;

import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.dto.ProducerFilmePremiadoDTO;
import org.springframework.stereotype.Component;

@Component
public class FilmePremiadoConverter {

    public FilmesPremiadosDTO converterTOFilme(ProducerFilmePremiadoDTO primeiroFilme,
                                               ProducerFilmePremiadoDTO ultimoFilme) {
        return FilmesPremiadosDTO
                .builder()
                .producer(primeiroFilme.getProducer())
                .previousWin(primeiroFilme.getAnoLancamento())
                .followingWin(ultimoFilme.getAnoLancamento())
                .interval(ultimoFilme.getAnoLancamento() - primeiroFilme.getAnoLancamento())
                .build();
    }
}
