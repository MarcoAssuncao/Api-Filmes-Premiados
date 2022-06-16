package com.filmes.indicadospremiados.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class FilmesDTO {

    private List<FilmesPremiadosDTO> min;
    private List<FilmesPremiadosDTO> max;
}
