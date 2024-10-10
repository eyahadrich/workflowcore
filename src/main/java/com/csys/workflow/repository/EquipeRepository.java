package com.csys.workflow.repository;

import com.csys.workflow.domain.Equipe;
import java.lang.Integer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Equipe entity.
 */
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
}

