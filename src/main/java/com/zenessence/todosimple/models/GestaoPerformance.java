package com.zenessence.todosimple.models;

import jakarta.persistence.*;

@Entity
@Table(name = "gestao_performance")
public class GestaoPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servico", nullable = false)
    private String servico;

    @Column(name = "agendamento_reuniao", nullable = false)
    private String agendamentoReuniao;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id", nullable = false, updatable = false)
    private Avaliacao avaliacao;

    public GestaoPerformance() {}

    public GestaoPerformance(Long id, String servico, String agendamentoReuniao, Avaliacao avaliacao) {
        this.id = id;
        this.servico = servico;
        this.agendamentoReuniao = agendamentoReuniao;
        this.avaliacao = avaliacao;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getAgendamentoReuniao() {
        return agendamentoReuniao;
    }

    public void setAgendamentoReuniao(String agendamentoReuniao) {
        this.agendamentoReuniao = agendamentoReuniao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
}
