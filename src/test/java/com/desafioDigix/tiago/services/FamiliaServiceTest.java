package com.desafioDigix.tiago.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.desafioDigix.tiago.models.Familia;
import com.desafioDigix.tiago.repository.FamiliaRepository;
@SpringBootTest
public class FamiliaServiceTest {
    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private FamiliaService familiaService;

    @BeforeEach
    @AfterEach
    void setUp() {
        familiaRepository.deleteAll();
    }

    @Test
    void deve_retornar_as_familias_ordenadas_por_pontuacao() {
        // Arrange
        Familia familia1 = Familia.builder().nomeDoResponsavel("Tiago").rendaTotal(900).quantidadeDeDependentes(3).build();
        Familia familia2 = Familia.builder().nomeDoResponsavel("Junior").rendaTotal(1200).quantidadeDeDependentes(2).build();
        Familia familia3 = Familia.builder().nomeDoResponsavel("Ana").rendaTotal(950).quantidadeDeDependentes(1).build();
        familiaRepository.saveAll(Arrays.asList(familia1, familia2, familia3));
        List<Familia> familiasEsperadas = Arrays.asList(familia1, familia2, familia3);

        // Action
        List<Familia> familiasObtidas = familiaService.ordenarFamiliasPorPontuacao(familiasEsperadas);

        // Assert
        assertThat(familiasEsperadas).isEqualTo(familiasObtidas);
    }
}
