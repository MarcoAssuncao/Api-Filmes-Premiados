package com.filmes.indicadospremiados.batch;

import com.filmes.indicadospremiados.converter.FilmeConverter;
import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.entity.Filme;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmeProcessor implements ItemProcessor<FilmeDTO, Filme> {

    private final FilmeConverter filmeConverter;

    @Override
    public Filme process(FilmeDTO filmeDTO) throws Exception {
        return filmeConverter.converterToEntity(filmeDTO);
    }
}
