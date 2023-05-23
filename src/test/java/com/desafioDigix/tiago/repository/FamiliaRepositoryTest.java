package com.desafioDigix.tiago.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desafioDigix.tiago.models.Familia;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class FamiliaRepositoryTest {

    @Autowired
    private FamiliaRepository familiaRepository;

    @BeforeEach
    @AfterEach
    void setUp() {
        familiaRepository.deleteAll();
    }

    @Test
    void deve_salvar_uma_familia() {
        // Arrange
        Familia familia = Familia.builder().nomeDoResponsavel("Tiago").rendaTotal(900).quantidadeDeDependentes(3).build();

        // Action
        familiaRepository.save(familia);

        // Assert
        assertThat(familia.getId()).isNotNull();
    }

    @Test
    void deve_retornar_familias() {
        // Arrange
        Familia familia1 = Familia.builder().nomeDoResponsavel("Tiago").rendaTotal(900).quantidadeDeDependentes(3).build();
        Familia familia2 = Familia.builder().nomeDoResponsavel("Sergio").rendaTotal(1200).quantidadeDeDependentes(4).build();
        familiaRepository.saveAll(Arrays.asList(familia1, familia2));
        List<Familia> familiasEsperadas = Arrays.asList(familia1, familia2);

        // Action
        List<Familia> familiasRetornadas = familiaRepository.findAll();
        // Assert
        assertThat(familiasRetornadas).isEqualTo(familiasEsperadas);
    }
}
