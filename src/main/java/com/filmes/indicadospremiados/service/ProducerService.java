package com.filmes.indicadospremiados.service;

import com.filmes.indicadospremiados.dto.ProducerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerService {

     public ProducerDTO filtrarProdutores(String producers) {

         List<String> produtores = new ArrayList<>();
         if(!producers.contains("and") && !producers.contains(",")) {
             produtores.add(producers);
             return ProducerDTO.builder().producers(produtores).build();
         }
         if (producers.contains("and") && producers.contains(",")) {
             produtores = buscarProdutoresPorVirgulaEAnd(produtores, producers);
             return ProducerDTO.builder().producers(produtores).build();
         }
         if (producers.contains("and") && !producers.contains(",")) {
             produtores = buscarProdutoresPorAnd(produtores, producers);
             return ProducerDTO.builder().producers(produtores).build();
         }
         return null;
     }

     private List<String> buscarProdutoresPorAnd(List<String> produtores, String producers) {

         String lista[]  = producers.split("and");
         for(int i = 0; i<lista.length; i++) {
             produtores.add(lista[i]);
         }
         return produtores;
     }

     private List<String>  buscarProdutoresPorVirgulaEAnd(List<String> produtores, String producers) {

         String lista[]  = producers.split("[,]");
         List<String> produtoresPremiados = new ArrayList<>();
         String listaAnd[] =  new String[lista.length];

         for (int i = 0; i < lista.length; i++) {
             if (lista[i].contains("and")) {
                 listaAnd = lista[i].split("and");

                 produtoresPremiados.add(lista[i]);
                 produtoresPremiados.removeIf(n -> (n.contains("and")));

                 for (int j = 0; j < listaAnd.length; j++) {
                     produtoresPremiados.add(listaAnd[j]);
                 }
             }
             if (!lista[i].contains("and")) {
                 produtoresPremiados.add(lista[i]);
             }
         }

         for (int i = 0; i < produtoresPremiados.size(); i++) {
             produtores.add(produtoresPremiados.get(i));
         }
         return produtores;
     }
}
