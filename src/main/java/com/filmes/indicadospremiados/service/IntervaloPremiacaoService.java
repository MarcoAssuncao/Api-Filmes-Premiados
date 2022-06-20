package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.converter.FilmePremiadoConverter;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.dto.ProducerFilmePremiadoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IntervaloPremiacaoService {

    private final FilmePremiadoConverter filmePremiadoConverter;

    public List<FilmesPremiadosDTO> calcularPeriodoPremiacoes(List<ProducerFilmePremiadoDTO> producerFilmePremiados) {

        List<FilmesPremiadosDTO> filmesPremiadosIntervalo = new ArrayList<>();
        for (ProducerFilmePremiadoDTO produtor : producerFilmePremiados) {

            List<ProducerFilmePremiadoDTO> premiadoDTOS = producerFilmePremiados
                    .stream().filter(dto -> dto.getProducer().equals(produtor.getProducer()))
                    .collect(Collectors.toList());
            if (premiadoDTOS.size() > 1) {
                boolean existeNaLista = filmesPremiadosIntervalo.stream()
                        .anyMatch(filme -> filme.getProducer().equals(produtor.getProducer()));
                if (!existeNaLista) {
                    ProducerFilmePremiadoDTO primeiroProdutor = premiadoDTOS.stream().findFirst().get();
                    ProducerFilmePremiadoDTO ultimoProdutor = premiadoDTOS.get(premiadoDTOS.size() - 1);
                    filmesPremiadosIntervalo
                            .add(filmePremiadoConverter.converterTOFilme(primeiroProdutor, ultimoProdutor));
                }
            }
        }
        return filmesPremiadosIntervalo;
    }
}
