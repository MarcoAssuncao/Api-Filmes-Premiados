package com.filmes.indicadospremiados.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class FilmeDTO {

    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

    private ProducerDTO producerDTO;

}
