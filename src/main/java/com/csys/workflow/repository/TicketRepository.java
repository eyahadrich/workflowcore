package com.csys.workflow.repository;

import com.csys.workflow.domain.Interface;
import com.csys.workflow.domain.Ticket;
import java.lang.Integer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ticket entity.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {


    List<Ticket> findByInterface1(Interface interface1);
}

