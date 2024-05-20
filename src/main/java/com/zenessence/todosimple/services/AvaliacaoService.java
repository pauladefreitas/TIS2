package com.zenessence.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zenessence.todosimple.models.Avaliacao;
import com.zenessence.todosimple.models.User;
import com.zenessence.todosimple.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UserService userService; 

    public Avaliacao findById(Long id) {
        Optional<Avaliacao> avaliacao = this.avaliacaoRepository.findById(id);
        return avaliacao.orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));
    }

    public List<Avaliacao> findAllByUserId(Long userId) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepository.findByUser_Id(userId); 
        return avaliacoes;
    }

    @Transactional
    public Avaliacao create(Avaliacao obj) {
        User user = this.userService.findById(obj.getUser().getId()); //validação que o usuário existe
        obj.setId(null);
        obj.setUser(user);
        obj = this.avaliacaoRepository.save(obj); 
        return obj;
    }

    @Transactional
    public Avaliacao update (Avaliacao obj) {
        Avaliacao newObj = findById(obj.getId()); 
        newObj.setServiceName(obj.getServiceName()); 
        newObj.setEvaluationDate(obj.getEvaluationDate());
        newObj.setNota(obj.getNota());
        newObj.setComment(obj.getComment());
        return this.avaliacaoRepository.save(newObj); 
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.avaliacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir a avaliação."); 
        }
    }
}
