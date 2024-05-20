package com.zenessence.todosimple.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.zenessence.todosimple.models.Agendamento;
import com.zenessence.todosimple.services.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamento")
@Validated
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService; 


    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> findById(@PathVariable Long id) {
        Agendamento obj = this.agendamentoService.findById(id); 
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Agendamento obj) {
        this.agendamentoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // @PutMapping("/{id}") 
    // @Validated
    // public ResponseEntity<Void> update(@Valid @RequestBody Agendamento obj, @PathVariable Long id){
    //     obj.setId(id);
    //     this.agendamentoService.update(obj);
    //     return ResponseEntity.noContent().build();
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.agendamentoService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Agendamento>> findAllByUserId(@PathVariable Long userId) {
        List<Agendamento> objs = this.agendamentoService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarStatusAgendamento(@PathVariable("id") Long id, @RequestBody Map<String, String> requestBody) {
        String novoStatus = requestBody.get("status");
        try {
            Agendamento agendamentoAtualizado = agendamentoService.atualizarStatus(id, novoStatus);
            return ResponseEntity.ok(agendamentoAtualizado);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o status do agendamento.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> findAllAgendamentos() {
        List<Agendamento> agendamentos = this.agendamentoService.findAllAgendamentos();
        return ResponseEntity.ok().body(agendamentos);
    }
    
}
