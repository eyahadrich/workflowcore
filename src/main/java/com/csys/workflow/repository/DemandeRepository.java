package com.csys.workflow.repository;

import com.csys.workflow.domain.Demande;
import java.lang.Integer;
import java.util.List;

import com.csys.workflow.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Demande entity.
 */
@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    List<Demande> findByEmploye_IdEmploye(Integer idEmploye);
    List<Demande> findByInterface1_IdInterface(Integer idInterface);

    List<Demande> findByEmploye_IdEmployeAndInterface1_IdInterface(Integer idEmploye, Integer idInterface);
}

