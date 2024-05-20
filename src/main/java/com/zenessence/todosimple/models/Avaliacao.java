package com.zenessence.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "service_evaluation")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "service_name", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    private String serviceName;

    @Column(name = "evaluation_date", nullable = false)
    @NotNull
    @NotEmpty
    private String evaluationDate;

    @Column(name = "nota", nullable = false)
    @NotNull
    private int nota; 

    @Column(name = "comment", length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotBlank
    private String comment;


    public Avaliacao() {
    }

    public Avaliacao(Long id, User user, String serviceName, String evaluationDate, int nota, String comment) {
        this.id = id;
        this.user = user;
        this.serviceName = serviceName;
        this.evaluationDate = evaluationDate;
        this.nota = nota;
        this.comment = comment;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEvaluationDate() {
        return this.evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public int getNota() {
        return this.nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Avaliacao id(Long id) {
        setId(id);
        return this;
    }

    public Avaliacao user(User user) {
        setUser(user);
        return this;
    }

    public Avaliacao serviceName(String serviceName) {
        setServiceName(serviceName);
        return this;
    }

    public Avaliacao evaluationDate(String evaluationDate) {
        setEvaluationDate(evaluationDate);
        return this;
    }

    public Avaliacao nota(int nota) {
        setNota(nota);
        return this;
    }

    public Avaliacao comment(String comment) {
        setComment(comment);
        return this;
    }

}