package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.dto.FilmesDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.especification.FilmeCriteria;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmePremiadoService {

    private final FilmeCriteria filmeCriteria;

    public FilmesDTO buscarFilmesPremiados() {

        List<FilmesPremiadosDTO> filtroFilmesPremiadosDTOS = filmeCriteria.filtrarPorCriterioMaiorPeriodo();
        List<FilmesPremiadosDTO> filtroFilmesPremeadosMenorPeriodo = filmeCriteria.filtrarPorCriterioMenorPeriodo();
        return FilmesDTO
                .builder()
                .max(filtroFilmesPremiadosDTOS)
                .min(filtroFilmesPremeadosMenorPeriodo)
                .build();
    }
}
