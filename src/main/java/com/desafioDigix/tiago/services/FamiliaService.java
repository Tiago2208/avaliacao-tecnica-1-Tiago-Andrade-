package com.desafioDigix.tiago.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioDigix.tiago.models.Familia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class FamiliaService {

    @Autowired
    private EntityManager entityManager;
    public List<Familia> obterFamiliasDoBancoDeDados(String nomeDoResponsavel, double rendaTotal, int quantidadeDeDependentes) {
        Query queryFamilias = entityManager.createQuery(
            "select nome_do_responsavel, renda_total, quantidade_de_dependentes from familia f"
        );
        List<Familia> familias = queryFamilias.getResultList();

        return familias;
    }

    public List<Familia> ordenarFamiliasPorPontuacao(List<Familia> familias) {
        Collections.sort(familias, Comparator.comparingInt(Familia::calcularPontuacao).reversed());
        return familias;
    }
}
