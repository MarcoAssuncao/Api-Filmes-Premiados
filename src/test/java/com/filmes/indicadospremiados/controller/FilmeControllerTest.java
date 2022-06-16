package com.filmes.indicadospremiados.controller;

import com.filmes.indicadospremiados.IndicadosPremiadosApplicationTests;
import com.filmes.indicadospremiados.dto.FilmesDTO;
import com.filmes.indicadospremiados.dto.FilmesPremiadosDTO;
import com.filmes.indicadospremiados.service.FilmePremiadoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FilmeControllerTest extends IndicadosPremiadosApplicationTests {

    private final String BASE_URL = "/filmes";

    private MockMvc mockMvc;

    @MockBean
    private  JobLauncher jobLauncher;

    @MockBean
    private  Job job;

    @MockBean
    private FilmePremiadoService filmePremiadoService;

    @Autowired
    private FilmeController filmeController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
    }


    @Test
    public void deveBuscarFilmesPremiadoMenorEMaiorIntervaloTempo() throws Exception {

        FilmesDTO filmesDTO = buildFilmes();
        when(filmePremiadoService.buscarFilmesPremiados()).thenReturn(filmesDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/filmes-premiados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(filmePremiadoService, times(1)).buscarFilmesPremiados();
    }

    private FilmesDTO buildFilmes() {
        return FilmesDTO
                .builder()
                .max(buildListMaxPeriodo())
                .min(buildListMinPeriodo())
                .build();
    }

    private List<FilmesPremiadosDTO> buildListMaxPeriodo() {
       return  Arrays.asList(buildMaxPeriodo());
    }

    private List<FilmesPremiadosDTO> buildListMinPeriodo() {
        return  Arrays.asList(buildMinPeriodo());
    }

    private FilmesPremiadosDTO buildMaxPeriodo() {
        return FilmesPremiadosDTO
                .builder()
                .previousWin(2013)
                .followingWin(2018)
                .interval(5)
                .producer("Peter Farrelly")
                .build();
    }

    private FilmesPremiadosDTO buildMinPeriodo() {
        return FilmesPremiadosDTO
                .builder()
                .previousWin(2015)
                .followingWin(2017)
                .interval(2)
                .producer("Dana Brunetti")
                .build();
    }
}
