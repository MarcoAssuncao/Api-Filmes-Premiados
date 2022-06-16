package com.filmes.indicadospremiados.controller;

import com.filmes.indicadospremiados.dto.FilmesDTO;
import com.filmes.indicadospremiados.service.FilmePremiadoService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/filmes")
@AllArgsConstructor
public class FilmeController {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final FilmePremiadoService filmePremiadoService;

    @GetMapping("/load")
    public ResponseEntity<BatchStatus> load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        System.out.println("Iniciando processamento gravação dos arquivos dos filmes");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        if (BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {
            return ResponseEntity.ok(jobExecution.getStatus());
        }
        return ResponseEntity.badRequest().body(jobExecution.getStatus());
    }

    @GetMapping("/filmes-premiados")
    public ResponseEntity<FilmesDTO> findIntervaloPremiacao() {
        FilmesDTO filmesDTO = filmePremiadoService.buscarFilmesPremiados();
        if (filmesDTO.getMax().size() > 0 && filmesDTO.getMin().size() > 0) {
            return ResponseEntity.ok(filmesDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
