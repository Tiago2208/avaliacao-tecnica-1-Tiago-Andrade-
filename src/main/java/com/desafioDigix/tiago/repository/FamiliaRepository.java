package com.desafioDigix.tiago.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.desafioDigix.tiago.models.Familia;

public interface FamiliaRepository extends CrudRepository<Familia, Long> {
    List<Familia> findAll();
    List<Familia> findByNomeDoResponsavel(String nomeDoResponsavel);
}
