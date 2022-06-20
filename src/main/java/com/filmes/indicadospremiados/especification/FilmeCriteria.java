package com.filmes.indicadospremiados.especification;

import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class FilmeCriteria {

    public List<FilmesPremiadosDTO> filtrarPorCriterioMaiorPeriodo(List<FilmesPremiadosDTO> filmesPremiadosIntervalo) {
        if (!CollectionUtils.isEmpty(filmesPremiadosIntervalo)) {
            return buscarMaiorIntervalo(filmesPremiadosIntervalo);
        }
        return new ArrayList<>();
    }

    private List<FilmesPremiadosDTO> buscarMaiorIntervalo(List<FilmesPremiadosDTO> filmesPremiadosIntervalo) {

        Optional<FilmesPremiadosDTO> filmeMaiorIntervaloPremiacao = filmesPremiadosIntervalo.stream()
                .max(Comparator.comparing(FilmesPremiadosDTO::getInterval));
        List<FilmesPremiadosDTO> filmesPremiadosMaiorIntervalo = new ArrayList<>();
        filmesPremiadosMaiorIntervalo.add(filmeMaiorIntervaloPremiacao.get());
        return filmesPremiadosMaiorIntervalo;
    }

    public List<FilmesPremiadosDTO> filtrarPorCriterioMenorPeriodo(List<FilmesPremiadosDTO> filmesPremiadosIntervalo) {
        if (!CollectionUtils.isEmpty(filmesPremiadosIntervalo)) {
            return buscarMenorIntervalo(filmesPremiadosIntervalo);
        }
        return new ArrayList<>();
    }

    private List<FilmesPremiadosDTO> buscarMenorIntervalo(List<FilmesPremiadosDTO> filmesPremiadosIntervalo) {

        Optional<FilmesPremiadosDTO> filmeIntervaloPremiacao = filmesPremiadosIntervalo.stream()
                .min(Comparator.comparing(FilmesPremiadosDTO::getInterval));
        List<FilmesPremiadosDTO> filmesPremiadosMenorIntervalo = new ArrayList<>();
        filmesPremiadosMenorIntervalo.add(filmeIntervaloPremiacao.get());
        return filmesPremiadosMenorIntervalo;
    }
}

