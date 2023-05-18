package com.desafioDigix.tiago.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DependenteTest {
    @Test
    void deve_criar_um_dependente() {
        // Arrange
        String nome = "Tiago Andrade";
        int idade = 15;

        // Action
        Dependente dependente = Dependente.builder().nome(nome).idade(idade).build();

        // Asserts
        assertThat(dependente.getNome()).isEqualTo(nome);
        assertThat(dependente.getIdade()).isEqualTo(idade);

    }
}
