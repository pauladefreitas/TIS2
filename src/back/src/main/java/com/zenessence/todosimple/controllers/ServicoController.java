package com.zenessence.todosimple.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zenessence.todosimple.models.Servico;
import com.zenessence.todosimple.services.ServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/service")
@Validated

public class ServicoController {

    @Autowired
    private ServicoService servicoService; 

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        Servico obj = this.servicoService.findById(id); 
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Servico obj) {
        System.out.println("Recebido serviço: " + obj);
        this.servicoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") 
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Servico obj, @PathVariable Long id){
        obj.setId(id);
        this.servicoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.servicoService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Servico>> findAllServicos() {
        List<Servico> Servicos = this.servicoService.findAllServices();
        return ResponseEntity.ok().body(Servicos);
    }
}