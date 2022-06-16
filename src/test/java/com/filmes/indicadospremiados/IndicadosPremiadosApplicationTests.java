package com.filmes.indicadospremiados;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(classes = IndicadosPremiadosApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class IndicadosPremiadosApplicationTests {

    @Test
	void contextLoads() {
	}

}
