package com.csys.workflow.repository;

import com.csys.workflow.domain.Workflow;
import com.csys.workflow.domain.Workflow;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Workflow entity.
 */
@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Integer> {
    Workflow findByNomWorkflow(String nomWorkflow);
}

