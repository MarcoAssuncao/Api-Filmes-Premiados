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
    private final FilmeService filmeService;

    public FilmesDTO buscarFilmesPremiados() {

        List<FilmesPremiadosDTO> filmesPremiadosDTOS = filmeService.classificarFilmesPremiadosPorPeriodo();
        List<FilmesPremiadosDTO> filtroMaiorPeriodo = filmeCriteria.filtrarPorCriterioMaiorPeriodo(filmesPremiadosDTOS);
        List<FilmesPremiadosDTO> filtroMenorPeriodo = filmeCriteria.filtrarPorCriterioMenorPeriodo(filmesPremiadosDTOS);
        return FilmesDTO
                .builder()
                .max(filtroMaiorPeriodo)
                .min(filtroMenorPeriodo)
                .build();
    }
}
