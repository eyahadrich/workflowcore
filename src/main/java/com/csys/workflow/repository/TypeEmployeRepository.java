package com.csys.workflow.repository;

import com.csys.workflow.domain.TypeEmploye;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TypeEmploye entity.
 */
@Repository
public interface TypeEmployeRepository extends JpaRepository<TypeEmploye, Integer> {
}

