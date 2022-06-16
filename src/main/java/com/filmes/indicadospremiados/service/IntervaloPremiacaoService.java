package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.converter.FilmePremiadoConverter;
import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IntervaloPremiacaoService {

    private final FilmePremiadoConverter filmePremiadoConverter;

    public List<FilmesPremiadosDTO> calcularPeriodoEntrePremiacoes(Map.Entry<String, List<FilmeDTO>> filmeIntervalo,
                                                                   List<FilmesPremiadosDTO> filmesPremiadosIntervalo) {

        if (filmeIntervalo.getValue().size() > 1) {
            FilmeDTO primeiroFilme = filmeIntervalo.getValue().stream().findFirst().get();
            FilmeDTO ultimoFilme = filmeIntervalo.getValue().get(filmeIntervalo.getValue().size() - 1);
            filmesPremiadosIntervalo
                    .add(filmePremiadoConverter.converterTOFilme(primeiroFilme, ultimoFilme));
        }
        return filmesPremiadosIntervalo;
    }
}
