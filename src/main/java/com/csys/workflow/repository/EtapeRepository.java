package com.csys.workflow.repository;

import com.csys.workflow.domain.Etape;
import java.lang.Integer;
import java.util.List;

import com.csys.workflow.domain.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Etape entity.
 */
@Repository
public interface EtapeRepository extends JpaRepository<Etape, Integer> {

   List<Etape> findByWorkflow(Workflow workflow);
}

