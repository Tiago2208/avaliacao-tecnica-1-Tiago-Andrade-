package com.desafioDigix.tiago.models;

import com.desafioDigix.tiago.dtos.ListagemFamiliasDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Familia {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeDoResponsavel;

    @Column(nullable = false)
    private double rendaTotal;

    @Column(nullable = false)
    private int quantidadeDeDependentes;
    
    public Familia(String nomeDoResponsavel, double rendaTotal, int quantidadeDeDependentes, int pontuacao) {
    }
    public Familia(ListagemFamiliasDTO dados) {
        this.nomeDoResponsavel = dados.nomeDoResponsavel();
        this.rendaTotal = dados.rendaTotal();
        this.quantidadeDeDependentes = dados.quantidadeDeDependentes();
    }
    public int calcularPontuacao() {
        int pontos = 0;
        if (rendaTotal <= 900) {
            pontos += 5;
        } else if (rendaTotal <= 1500) {
            pontos += 3;
        }

        if (quantidadeDeDependentes >= 3) {
            pontos += 3;
        } else if (quantidadeDeDependentes >= 1) {
            pontos += 2;
        }
        return pontos;
    }

    
}