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
public class ProducerDTO {

    private List<String> producers;
}
