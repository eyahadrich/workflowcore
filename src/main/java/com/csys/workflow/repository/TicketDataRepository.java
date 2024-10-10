package com.csys.workflow.repository;

import com.csys.workflow.domain.Demande;
import com.csys.workflow.domain.TicketData;
import java.lang.Integer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TicketData entity.
 */
@Repository
public interface TicketDataRepository extends JpaRepository<TicketData, Integer> {
    List<TicketData> findByDemande(Demande demande);
}

