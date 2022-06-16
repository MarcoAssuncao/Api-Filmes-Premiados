package com.filmes.indicadospremiados.converter;

import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.entity.Filme;
import com.filmes.indicadospremiados.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmeConverter {

    private final ProducerService producerService;

    public FilmeDTO converterToDTO(Filme filme){
        return FilmeDTO.builder()
                .year(filme.getAnoLancamento())
                .title(filme.getNomeFilme())
                .studios(filme.getEstudio())
                .producers(filme.getProdutor())
                .producerDTO(producerService.filtrarProdutores(filme.getProdutor()))
                .winner(filme.venceuPiorFilme())
                .build();
    }

    public Filme converterToEntity(FilmeDTO filmeDTO){
        return Filme.builder()
                .anoLancamento(filmeDTO.getYear())
                .nomeFilme(filmeDTO.getTitle())
                .estudio(filmeDTO.getStudios())
                .produtor(filmeDTO.getProducers())
                .premiado(filmeDTO.getWinner().equals("yes") ? Boolean.TRUE : Boolean.FALSE)
                .build();
    }
}
