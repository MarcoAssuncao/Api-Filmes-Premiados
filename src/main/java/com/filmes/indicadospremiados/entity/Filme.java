package com.filmes.indicadospremiados.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Filme implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String nomeFilme;
    private Integer anoLancamento;
    private String estudio;
    private String produtor;
    private Boolean premiado;

    public  String  venceuPiorFilme() {
        return  this.premiado == true ? "Yes" : "NO";
    }
}
