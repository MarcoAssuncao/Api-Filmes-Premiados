package com.filmes.indicadospremiados.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProducerFilmePremiadoDTO {

    private String producer;
    private Integer anoLancamento;
    private String nomeFilme;
}
