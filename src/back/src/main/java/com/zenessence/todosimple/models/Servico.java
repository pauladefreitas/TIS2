package com.zenessence.todosimple.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="service")
public class Servico {

    public Servico(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "service_name", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    private String serviceName;

    @Column(name = "staff_name", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    private String staffName;

    @Column(name = "duration", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    private String duration;

    @Column(name = "avaliable_date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "avaliable_time", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    private String time;

    public Servico(Long id, String serviceName, String staffName, String duration, LocalDate date, String time) {
        this.id = id;
        this.serviceName = serviceName;
        this.staffName = staffName;
        this.duration = duration;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
