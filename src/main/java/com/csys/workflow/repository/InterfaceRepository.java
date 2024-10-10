package com.csys.workflow.repository;

import com.csys.workflow.domain.Employe;
import com.csys.workflow.domain.Interface;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Demande entity.
 */
@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Integer> {
    Interface findByNomInterface(String nomInterface);
}

