package com.filmes.indicadospremiados.batch;

import com.filmes.indicadospremiados.entity.Filme;
import com.filmes.indicadospremiados.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FilmeWrite implements ItemWriter<Filme> {


    private final FilmeRepository filmeRepository;

    @Override
    public void write(List<? extends Filme> filmes) throws Exception {
        filmes.forEach(filme -> filmeRepository.save(filme));
    }
}
