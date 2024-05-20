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

import com.zenessence.todosimple.models.Avaliacao;
import com.zenessence.todosimple.services.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacao")
@Validated
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService; 


    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> findById(@PathVariable Long id) {
        Avaliacao obj = this.avaliacaoService.findById(id); 
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Avaliacao obj) {
        this.avaliacaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") 
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Avaliacao obj, @PathVariable Long id){
        obj.setId(id);
        this.avaliacaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.avaliacaoService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Avaliacao>> findAllByUserId(@PathVariable Long userId) {
        List<Avaliacao> objs = this.avaliacaoService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
    }
}
