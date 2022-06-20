package com.filmes.indicadospremiados.especification;

import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.dto.ProducerFilmePremiadoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProducerCriteria {

    public List<ProducerFilmePremiadoDTO> buscarProdutoresPremiados(List<FilmeDTO> filmeDTOS) {
        List<ProducerFilmePremiadoDTO> producerFilmePremiadoDTOS = listarProdutoresPremiados(filmeDTOS);
        return extrairProdutoresPremiados(producerFilmePremiadoDTOS);
    }

    protected List<ProducerFilmePremiadoDTO> listarProdutoresPremiados(List<FilmeDTO> filmesPremiados) {

        List<ProducerFilmePremiadoDTO> listaProdutores = new ArrayList<>();
        for (FilmeDTO filme : filmesPremiados) {
            for (String produtor : filme.getProducerDTO().getProducers()) {

                ProducerFilmePremiadoDTO filmePremiadoDTO = ProducerFilmePremiadoDTO
                        .builder()
                        .anoLancamento(filme.getYear())
                        .nomeFilme(filme.getTitle().trim())
                        .producer(produtor.trim())
                        .build();
                listaProdutores.add(filmePremiadoDTO);
            }
        }
        return listaProdutores;
    }

    protected List<ProducerFilmePremiadoDTO> extrairProdutoresPremiados(List<ProducerFilmePremiadoDTO> producersPremiados) {

        List<ProducerFilmePremiadoDTO> premiadosMaisQueUmaVez = new ArrayList<>();
        List<ProducerFilmePremiadoDTO> premiadosApenasUmaVez = new ArrayList<>();

        for (ProducerFilmePremiadoDTO producerFilmePremiadoDTO : producersPremiados) {
            if (premiadosApenasUmaVez.size() >= 0) {
                List<ProducerFilmePremiadoDTO> dtos = premiadosApenasUmaVez.stream()
                        .filter(premiado -> premiado.getProducer().equals(producerFilmePremiadoDTO.getProducer()))
                        .collect(Collectors.toList());
                if (dtos.size() == 0) {
                    premiadosApenasUmaVez.add(producerFilmePremiadoDTO);
                } else if (dtos.size() > 0) {
                    premiadosMaisQueUmaVez.add(producerFilmePremiadoDTO);
                }
            }
        }
        return adicionarListaProdutores(premiadosMaisQueUmaVez, premiadosApenasUmaVez);
    }

    protected List<ProducerFilmePremiadoDTO> adicionarListaProdutores(List<ProducerFilmePremiadoDTO> premiadosMaisQueUmaVez,
                                                                      List<ProducerFilmePremiadoDTO> premiadosApenasUmaVez) {

        List<ProducerFilmePremiadoDTO> produtoresPremiados = new ArrayList<>();
        for (ProducerFilmePremiadoDTO produtorPremiado : premiadosMaisQueUmaVez) {

            Optional<ProducerFilmePremiadoDTO> premiadoDTO = premiadosApenasUmaVez.stream()
                    .filter(premiado -> premiado.getProducer().equals(produtorPremiado.getProducer())).findAny();
            if (premiadoDTO.isPresent()) {
                produtoresPremiados.add(premiadoDTO.get());
            }
        }
        produtoresPremiados.addAll(premiadosMaisQueUmaVez);
        return produtoresPremiados;
    }
}
