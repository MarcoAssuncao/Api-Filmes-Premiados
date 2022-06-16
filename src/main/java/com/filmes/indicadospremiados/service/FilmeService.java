package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.converter.FilmeConverter;
import com.filmes.indicadospremiados.dto.FilmeDTO;
import com.filmes.indicadospremiados.entity.Filme;
import com.filmes.indicadospremiados.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeConverter filmeConverter;

    public List<FilmeDTO> buscarFilmesPremiados() {
        List<Filme> filmes = filmeRepository.findByPremiadoTrue();
        List<FilmeDTO> filmesPremiados = filmes.stream()
                .map(filme -> filmeConverter.converterToDTO(filme))
                .collect(Collectors.toList());
        return filmesPremiados;
    }
}
