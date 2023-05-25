package com.desafioDigix.tiago.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.desafioDigix.tiago.dtos.ListagemFamiliasDTO;
import com.desafioDigix.tiago.models.Familia;
import com.desafioDigix.tiago.repository.FamiliaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class FamiliaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    @BeforeEach
    public void resetDb() {
        familiaRepository.deleteAll();
    }

    @Test
    public void deve_cadastrar_uma_familia() throws Exception {
        String nomeDoResponsavel = "João";
        double rendaTotal = 1200.0;
        int quantidadeDeDependentes = 2;

        ListagemFamiliasDTO dados = new ListagemFamiliasDTO(nomeDoResponsavel, rendaTotal, quantidadeDeDependentes);

        MvcResult result = mockMvc.perform(post("/familias/cadastrarFamilia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).isEmpty();
        List<Familia> familiaCadastrada = familiaRepository.findByNomeDoResponsavel(nomeDoResponsavel);
        assertThat(familiaCadastrada).isNotNull();
        assertThat(familiaCadastrada.get(0).getRendaTotal()).isEqualTo(rendaTotal);
        assertThat(familiaCadastrada.get(0).getQuantidadeDeDependentes()).isEqualTo(quantidadeDeDependentes);

    }

    @Test
    public void deve_buscar_todas_as_familias() throws Exception {
        // Arrange
        String nomeDoResponsavel1 = "João";
        double rendaTotal1 = 1200.0;
        int quantidadeDeDependentes1 = 2;
        String nomeDoResponsavel2 = "Jorge";
        double rendaTotal2 = 900.0;
        int quantidadeDeDependentes2 = 3;
        Familia familia1 = Familia.builder().nomeDoResponsavel(nomeDoResponsavel1).rendaTotal(rendaTotal1)
                .quantidadeDeDependentes(quantidadeDeDependentes1).build();
        Familia familia2 = Familia.builder().nomeDoResponsavel(nomeDoResponsavel2).rendaTotal(rendaTotal2)
                .quantidadeDeDependentes(quantidadeDeDependentes2).build();
        familiaRepository.saveAll(Arrays.asList(familia1, familia2));

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/familias/buscarTodasAsFamilias")).andReturn();

        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void deve_buscar_todas_as_familias_ordenadas() throws Exception {
        // Arrange
        String nomeDoResponsavel1 = "Kaio";
        double rendaTotal1 = 1200.0;
        int quantidadeDeDependentes1 = 2;
        String nomeDoResponsavel2 = "Jorge";
        double rendaTotal2 = 900.0;
        int quantidadeDeDependentes2 = 3;
        String nomeDoResponsavel3 = "Maria";
        double rendaTotal3 = 901.0;
        int quantidadeDeDependentes3 = 4;
        Familia familia1 = Familia.builder().nomeDoResponsavel(nomeDoResponsavel1).rendaTotal(rendaTotal1)
                .quantidadeDeDependentes(quantidadeDeDependentes1).build();
        Familia familia2 = Familia.builder().nomeDoResponsavel(nomeDoResponsavel2).rendaTotal(rendaTotal2)
                .quantidadeDeDependentes(quantidadeDeDependentes2).build();
        Familia familia3 = Familia.builder().nomeDoResponsavel(nomeDoResponsavel3).rendaTotal(rendaTotal3)
                .quantidadeDeDependentes(quantidadeDeDependentes3).build();
        familiaRepository.saveAll(Arrays.asList(familia1, familia2, familia3));
        List<Familia> familiasEsperadas = Arrays.asList(familia2, familia3, familia1);

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/familias/buscarOrdenadosPelaPontuacao")).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Familia> familiasRetornadas = objectMapper.readValue(responseBody, new TypeReference<List<Familia>>() {});

        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        assertThat(familiasEsperadas).isEqualTo(familiasRetornadas);
    }

}
