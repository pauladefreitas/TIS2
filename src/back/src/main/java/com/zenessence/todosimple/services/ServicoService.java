package com.zenessence.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenessence.todosimple.models.Servico;
import com.zenessence.todosimple.repositories.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public Servico create(Servico obj) {
        obj.setId(null);
        obj = this.servicoRepository.save(obj);
        return obj;
    }

    public Servico findById(Long id) {
        Optional<Servico> user = servicoRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Serviço não encontrado."));
    }

    @Transactional
    public Servico update (Servico obj) {
        Servico newObj = findById(obj.getId()); 
        newObj.setServiceName(obj.getServiceName()); 
        newObj.setDate(obj.getDate());
        newObj.setStaffName(obj.getStaffName());
        newObj.setDuration(obj.getDuration());
        newObj.setTime(obj.getTime());
        return this.servicoRepository.save(newObj); 
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.servicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o serviço."); 
        }
    }

    public List<Servico> findAllServices() {
        return servicoRepository.findAll();
    }

}
