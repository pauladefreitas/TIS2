package com.zenessence.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmailService emailService; // Injetar o serviço de email

    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = this.agendamentoRepository.findById(id);
        return agendamento.orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
    }

    public List<Agendamento> findAllByUserId(Long userId) {
        return this.agendamentoRepository.findByUser_Id(userId);
    }

    @Transactional
    public Agendamento create(Agendamento obj) {
        if (obj.getClientEmail() == null || obj.getClientEmail().isEmpty()) {
            obj.setClientEmail("jotaa45@outlook.com"); // Email fixo para testes
        }
        if (obj.getManagerId() == null) {
            obj.setManagerId("defaultManager"); // Manager ID fixo para testes
        }

        // Validação que o usuário existe
        if (obj.getUser() != null && obj.getUser().getId() != null) {
            User user = this.userService.findById(obj.getUser().getId());
            obj.setUser(user);
        } else {
            throw new RuntimeException("User não encontrado.");
        }

        obj.setId(null);
        obj = this.agendamentoRepository.save(obj);

        // Enviar email de confirmação
        emailService.sendSimpleMessage(obj.getClientEmail(), "Agendamento Confirmado", "Seu agendamento foi confirmado!");

        return obj;
    }

    @Transactional
    public Agendamento update(Agendamento obj) {
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

    @Transactional
    public Agendamento approveAppointment(Long appointmentId, String managerId) {
        Agendamento agendamento = agendamentoRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
        if (agendamento.getManagerId().equals(managerId)) {
            agendamento.setApproved(true);
            return agendamentoRepository.save(agendamento);
        } else {
            throw new RuntimeException("Gerente não autorizado.");
        }
    }

    @Transactional
    public Agendamento atualizarStatus(Long id, String novoStatus) {
        Agendamento agendamento = findById(id);
        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> findAllAgendamentos() {
        return agendamentoRepository.findAll();
    }
}
