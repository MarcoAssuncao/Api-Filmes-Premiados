package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.converter.FilmeConverter;
import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.dto.ProducerFilmePremiadoDTO;
import com.filmes.indicadospremiados.entity.Filme;
import com.filmes.indicadospremiados.especification.ProducerCriteria;
import com.filmes.indicadospremiados.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeConverter filmeConverter;
    private final ProducerCriteria producerCriteria;
    private final IntervaloPremiacaoService intervaloPremiacaoService;

    public List<FilmeDTO> buscarFilmesPremiados() {
        List<Filme> filmes = filmeRepository.findByPremiadoTrue();
        List<FilmeDTO> filmesPremiados = filmes.stream()
                .map(filme -> filmeConverter.converterToDTO(filme))
                .collect(Collectors.toList());
        return filmesPremiados;
    }

    public List<FilmesPremiadosDTO> classificarFilmesPremiadosPorPeriodo() {

        List<FilmesPremiadosDTO> filmesPremiadosIntervalo = new ArrayList<>();
        List<FilmeDTO> filmeDTOS = buscarFilmesPremiados();
        if (!CollectionUtils.isEmpty(filmeDTOS)) {
            List<ProducerFilmePremiadoDTO> producerFilmePremiadoDTOS = producerCriteria
                    .buscarProdutoresPremiados(filmeDTOS);
            filmesPremiadosIntervalo = intervaloPremiacaoService
                    .calcularPeriodoPremiacoes(producerFilmePremiadoDTOS);
        }
        return filmesPremiadosIntervalo;
    }
}
