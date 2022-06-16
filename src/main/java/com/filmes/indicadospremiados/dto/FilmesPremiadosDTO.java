package com.filmes.indicadospremiados.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class FilmesPremiadosDTO {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
