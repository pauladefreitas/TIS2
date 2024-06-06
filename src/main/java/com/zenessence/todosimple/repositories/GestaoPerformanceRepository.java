package com.zenessence.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zenessence.todosimple.models.GestaoPerformance;

@Repository
public interface GestaoPerformanceRepository extends JpaRepository<GestaoPerformance, Long>  {


}

