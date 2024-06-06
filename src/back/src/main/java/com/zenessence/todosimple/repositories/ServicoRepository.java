package com.zenessence.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenessence.todosimple.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
