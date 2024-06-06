package com.zenessence.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacao")
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
    private LocalDate evaluationDate;

    @Column(name = "nota", nullable = false)
    @NotNull
    private int nota;

    @Column(name = "status", length = 255, nullable = false)
    @NotNull
    private String status;

    @Column(name = "comment", length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotBlank
    private String comment;

    public Avaliacao() {}

    public Avaliacao(Long id, User user, String serviceName, LocalDate evaluationDate, int nota, String status, String comment) {
        this.id = id;
        this.user = user;
        this.serviceName = serviceName;
        this.evaluationDate = evaluationDate;
        this.nota = nota;
        this.status = status;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
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

    public Avaliacao evaluationDate(LocalDate evaluationDate) {
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
