package com.zenessence.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zenessence.todosimple.models.Agendamento;

import java.util.List;


@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByUser_Id(Long id); //retorna todos os agendamentos do usuário

    // @Query(value="SELECT * FROM agendamento a WHERE a.user.id = :id", nativeQuery = true)
    // List<Agendamento> findByUser_Id(@Param("id") Long id);
}
