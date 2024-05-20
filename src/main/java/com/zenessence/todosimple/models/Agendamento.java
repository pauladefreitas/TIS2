package com.zenessence.todosimple.models;

import jakarta.persistence.*;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_servico", nullable = false)
    private String tipoServico;

    @Column(name = "data_agendamento", nullable = false)
    private String dataAgendamento;

    @Column(name = "hora_agendamento", nullable = false)
    private String horaAgendamento;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user; 

    public Agendamento() {
    }

    public Agendamento(Long id, String tipoServico, String dataAgendamento, String horaAgendamento, User user) {
        this.id = id;
        this.tipoServico = tipoServico;
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServico() {
        return this.tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDataAgendamento() {
        return this.dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHoraAgendamento() {
        return this.horaAgendamento;
    }

    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
