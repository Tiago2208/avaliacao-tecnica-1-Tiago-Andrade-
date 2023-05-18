package com.desafioDigix.tiago.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FamiliaTest {
    @Test
    void deve_criar_uma_familia() {
        // Arrange
        double rendaTotal = 900;
        int quantidadeDeDependentes = 3;
        // Action
        Familia familia = Familia.builder().rendaTotal(rendaTotal).quantidadeDeDependentes(quantidadeDeDependentes).build();
        // Asserts
        assertThat(familia.getRendaTotal()).isEqualTo(rendaTotal);
        assertThat(familia.getQuantidadeDeDependentes()).isEqualTo(quantidadeDeDependentes);
    }
}
