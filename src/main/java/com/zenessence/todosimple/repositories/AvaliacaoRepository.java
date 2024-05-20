package com.zenessence.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zenessence.todosimple.models.Avaliacao;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    List<Avaliacao> findByUser_Id(Long id); //retorna todos os agendamentos do usuário

}