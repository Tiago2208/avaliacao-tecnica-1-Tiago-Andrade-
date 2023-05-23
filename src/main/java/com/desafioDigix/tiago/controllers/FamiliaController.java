package com.desafioDigix.tiago.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioDigix.tiago.dtos.ListagemFamiliasDTO;
import jakarta.validation.Valid;
import com.desafioDigix.tiago.models.Familia;
import com.desafioDigix.tiago.repository.FamiliaRepository;
import com.desafioDigix.tiago.services.FamiliaService;

@RestController
@RequestMapping("/familias")
public class FamiliaController {
    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private FamiliaRepository familiaRepository;
    

    @PostMapping
    public void cadastrarFamilia(@RequestBody @Valid ListagemFamiliasDTO dados ) {
        familiaRepository.save(new Familia(dados));
    }

    @GetMapping
    public ResponseEntity<List<Familia>> buscarTodos() {
        Iterable<Familia> iterable = familiaRepository.findAll();
        List<Familia> familias = new ArrayList<>();
        iterable.forEach(familias::add);
        return ResponseEntity.ok().body(familias);
    }
    @GetMapping("/buscarOrdenadosPelaPontuacao")
    public ResponseEntity<List<Familia>> buscarTodosOrdenadosPelaPontuacao() {
        Iterable<Familia> iterable = familiaRepository.findAll();
        List<Familia> familias = new ArrayList<>();
        familias = familiaService.ordenarFamiliasPorPontuacao(familias);
        iterable.forEach(familias::add);
        return ResponseEntity.ok().body(familias);
    }
    
    
}
