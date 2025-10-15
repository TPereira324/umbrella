package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<utilizador, Long>
{

}