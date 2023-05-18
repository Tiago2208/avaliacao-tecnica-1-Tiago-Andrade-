package com.desafioDigix.tiago.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.desafioDigix.tiago.models.Dependente;

public interface DependenteRepository extends CrudRepository<Dependente, Long> {
    List<Dependente> findAll();
}
