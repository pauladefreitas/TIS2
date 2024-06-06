package com.zenessence.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zenessence.todosimple.models.GestaoPerformance;
import com.zenessence.todosimple.models.Avaliacao;
import com.zenessence.todosimple.repositories.GestaoPerformanceRepository;

@Service
public class GestaoPerformanceService {

    @Autowired
    private GestaoPerformanceRepository gestaoRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public GestaoPerformance findById(Long id) {
        Optional<GestaoPerformance> gestao = this.gestaoRepository.findById(id);
        return gestao.orElseThrow(() -> new RuntimeException("Gestão não encontrada."));
    }

    public List<GestaoPerformance> findAllGestaoPerformances() {
        return gestaoRepository.findAll();
    }

    @Transactional
    public GestaoPerformance create(GestaoPerformance obj) {
        Avaliacao avaliacao = this.avaliacaoService.findById(obj.getAvaliacao().getId());
        obj.setId(null);
        obj.setAvaliacao(avaliacao);
        obj = this.gestaoRepository.save(obj);
        return obj;
    }

    @Transactional
    public GestaoPerformance update(GestaoPerformance obj) {
        GestaoPerformance newObj = findById(obj.getId());
        newObj.setServico(obj.getServico());
        newObj.setAgendamentoReuniao(obj.getAgendamentoReuniao());
        newObj.setAvaliacao(obj.getAvaliacao());
        return this.gestaoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.gestaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir a gestão de performance.");
        }
    }

    public GestaoPerformance atualizarAgendamento(Long id, String novoStatus) throws NotFoundException {
        GestaoPerformance gestaoPerformance = gestaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        gestaoPerformance.setAgendamentoReuniao(novoStatus);
        return gestaoRepository.save(gestaoPerformance);
    }
}

