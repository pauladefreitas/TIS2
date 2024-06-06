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

import com.zenessence.todosimple.models.GestaoPerformance;
import com.zenessence.todosimple.services.GestaoPerformanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestaoPerformance")
@Validated
public class GestaoPerfomanceController {

    @Autowired
    private GestaoPerformanceService gestaoPerformanceService; 


    @GetMapping("/{id}")
    public ResponseEntity<GestaoPerformance> findById(@PathVariable Long id) {
        GestaoPerformance obj = this.gestaoPerformanceService.findById(id); 
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody GestaoPerformance obj) {
        this.gestaoPerformanceService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.gestaoPerformanceService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarStatusGestaoPerformance(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String novoStatus = requestBody.get("agendamentoReuniao");
        try {
            GestaoPerformance GestaoPerformanceAtualizado = gestaoPerformanceService.atualizarAgendamento(id, novoStatus);
            return ResponseEntity.ok(GestaoPerformanceAtualizado);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o agendamento de Gestão de Performance.");
        }
    }

    @GetMapping
    public ResponseEntity<List<GestaoPerformance>> findAllGestaoPerformances() {
        List<GestaoPerformance> GestaoPerformances = this.gestaoPerformanceService.findAllGestaoPerformances();
        return ResponseEntity.ok().body(GestaoPerformances);
    }
}
