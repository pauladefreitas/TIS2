package com.zenessence.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zenessence.todosimple.models.Agendamento;
import com.zenessence.todosimple.models.User;
import com.zenessence.todosimple.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UserService userService; 

    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = this.agendamentoRepository.findById(id);
        return agendamento.orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
    }

    public List<Agendamento> findAllByUserId(Long UserId) {
        List<Agendamento> agendamentos = this.agendamentoRepository.findByUser_Id(UserId); 
        return agendamentos;
    }

    public List<Agendamento> findAllAgendamentos() {
        return agendamentoRepository.findAll();
    }
    

    @Transactional
    public Agendamento create(Agendamento obj) {
        User user = this.userService.findById(obj.getUser().getId()); //validação que o usuário existe
        obj.setId(null);
        obj.setUser(user);
        obj = this.agendamentoRepository.save(obj); 
        return obj;
    }

    @Transactional
    public Agendamento update (Agendamento obj) {
        Agendamento newObj = findById(obj.getId()); 
        newObj.setDataAgendamento(obj.getDataAgendamento()); 
        newObj.setHoraAgendamento(obj.getHoraAgendamento());
        return this.agendamentoRepository.save(newObj); 
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.agendamentoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir."); 
        }
    }

    public Agendamento atualizarStatus(Long id, String novoStatus) throws NotFoundException {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }
}
