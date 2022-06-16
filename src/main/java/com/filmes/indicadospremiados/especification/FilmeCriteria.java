package com.filmes.indicadospremiados.especification;

import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.service.FilmeService;
import com.filmes.indicadospremiados.service.IntervaloPremiacaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FilmeCriteria {

    private final FilmeService filmeService;
    private final IntervaloPremiacaoService intervaloPremiacaoService;

    public List<FilmesPremiadosDTO> filtrarPorCriterioMaiorPeriodo() {
        List<FilmeDTO> filmeDTOS = filmeService.buscarFilmesPremiados();
        if (!CollectionUtils.isEmpty(filmeDTOS)) {
            List<FilmesPremiadosDTO> filmesPremiadosMaiorIntervalo = new ArrayList<>();
            Map<String, List<FilmeDTO>> filmesPremeados = filmeDTOS.stream()
                    .collect(Collectors.groupingBy(FilmeDTO::getProducers));
            filmesPremiadosMaiorIntervalo = calcularPeriodoPremiacoes(filmesPremiadosMaiorIntervalo, filmesPremeados);
            return buscarMaiorIntervalo(filmesPremiadosMaiorIntervalo);
        }
        return new ArrayList<>();
    }

    private List<FilmesPremiadosDTO> buscarMaiorIntervalo(List<FilmesPremiadosDTO> filmesPremiadosMaiorIntervalo) {

        Optional<FilmesPremiadosDTO> filmeMaiorIntervaloPremiacao = filmesPremiadosMaiorIntervalo.stream()
                .max(Comparator.comparing(FilmesPremiadosDTO::getInterval));
        filmesPremiadosMaiorIntervalo.clear();
        filmesPremiadosMaiorIntervalo.add(filmeMaiorIntervaloPremiacao.get());
        return filmesPremiadosMaiorIntervalo;
    }

    public List<FilmesPremiadosDTO> filtrarPorCriterioMenorPeriodo() {
        List<FilmeDTO> filmeDTOS = filmeService.buscarFilmesPremiados();
        if (!CollectionUtils.isEmpty(filmeDTOS)) {
            List<FilmesPremiadosDTO> filmesPremiadosIntervalo = new ArrayList<>();
            Map<String, List<FilmeDTO>> filmesPremeados = filmeDTOS.stream()
                    .collect(Collectors.groupingBy(FilmeDTO::getProducers));
            filmesPremiadosIntervalo = calcularPeriodoPremiacoes(filmesPremiadosIntervalo, filmesPremeados);
            return buscarMenorIntervalo(filmesPremiadosIntervalo);
        }
        return new ArrayList<>();
    }

    private List<FilmesPremiadosDTO> buscarMenorIntervalo(List<FilmesPremiadosDTO> filmesPremiadosMenorIntervalo) {

        Optional<FilmesPremiadosDTO> filmeIntervaloPremiacao = filmesPremiadosMenorIntervalo.stream()
                .min(Comparator.comparing(FilmesPremiadosDTO::getInterval));
        filmesPremiadosMenorIntervalo.clear();
        filmesPremiadosMenorIntervalo.add(filmeIntervaloPremiacao.get());
        return filmesPremiadosMenorIntervalo;
    }

    protected List<FilmesPremiadosDTO> calcularPeriodoPremiacoes(List<FilmesPremiadosDTO> filmesPremiadosIntervalo,
                                                                 Map<String, List<FilmeDTO>> filmesPremeados) {
        for (Map.Entry<String, List<FilmeDTO>> filmeIntervalo : filmesPremeados.entrySet()) {
            filmesPremiadosIntervalo = intervaloPremiacaoService
                    .calcularPeriodoEntrePremiacoes(filmeIntervalo, filmesPremiadosIntervalo);
        }
        return filmesPremiadosIntervalo;
    }
}

