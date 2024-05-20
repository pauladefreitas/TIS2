package com.zenessence.todosimple.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)

public class User {

    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    public User() {}

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class) 
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;

    // @Column(name = "profile", nullable = false)
    // @ElementCollection(fetch = FetchType.EAGER)
    // @CollectionTable(name = "user_profile")
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private Set<Integer> profiles = new HashSet<>();

    @OneToMany(mappedBy = "user") //um user pode ter várias avaliações
    private List<Avaliacao> ratings = new ArrayList<Avaliacao>();

    @OneToMany(mappedBy = "user") //um user pode ter vários agendamentos
    private List<Agendamento> agendamento = new ArrayList<Agendamento>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public List<Avaliacao> getRatings() {
        return this.ratings;
    }

    public void setRatings(List<Avaliacao> ratings) {
        this.ratings = ratings;
    }

    @JsonIgnore
    public List<Agendamento> getAgendamento() {
        return this.agendamento;
    }

    public void setAgendamento(List<Agendamento> agendamento) {
        this.agendamento = agendamento;
    }


    // public Set<Integer> getProfiles() {
    //     return this.profiles;
    // }

    // public void setProfiles(Set<Integer> profiles) {
    //     this.profiles = profiles;
    // }

}