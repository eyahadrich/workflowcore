package com.csys.workflow.repository;

import com.csys.workflow.domain.TypeTicket;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TypeTicket entity.
 */
@Repository
public interface TypeTicketRepository extends JpaRepository<TypeTicket, Integer> {
}

