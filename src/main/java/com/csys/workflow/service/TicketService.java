package com.csys.workflow.service;


import com.csys.workflow.domain.Interface;
import com.csys.workflow.domain.Ticket;
import com.csys.workflow.dto.TicketDTO;
import com.csys.workflow.factory.TicketFactory;
import com.csys.workflow.repository.InterfaceRepository;
import com.csys.workflow.repository.TicketRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Ticket.
 */
@Service
@Transactional
public class TicketService {
  private final Logger log = LoggerFactory.getLogger(TicketService.class);

  private final TicketRepository ticketRepository;
  private final InterfaceRepository interfaceRepository;

  public TicketService(TicketRepository ticketRepository, InterfaceRepository interfaceRepository) {
    this.ticketRepository=ticketRepository;
      this.interfaceRepository = interfaceRepository;
  }

  /**
   * Save a ticketDTO.
   *
   * @param ticketDTO
   * @return the persisted entity
   */
  public TicketDTO save(TicketDTO ticketDTO) {
    log.debug("Request to save Ticket: {}",ticketDTO);
    Ticket ticket = TicketFactory.ticketDTOToTicket(ticketDTO);
    ticket = ticketRepository.save(ticket);
    TicketDTO resultDTO = TicketFactory.ticketToTicketDTO(ticket);
    return resultDTO;
  }
  public  boolean existsById(Integer id) {
    return ticketRepository.existsById(id);
  }
  /**
   * Update a ticketDTO.
   *
   * @param ticketDTO
   * @return the updated entity
   */
  public TicketDTO update(TicketDTO ticketDTO) {
    log.debug("Request to update Ticket: {}",ticketDTO);
    Ticket inBase= ticketRepository.findById(ticketDTO.getIdTicket()).orElse(null);
    Preconditions.checkArgument(inBase != null, "ticket.NotFound");
    Ticket ticket = TicketFactory.ticketDTOToTicket(ticketDTO);
    ticket = ticketRepository.save(ticket);
    TicketDTO resultDTO = TicketFactory.ticketToTicketDTO(ticket);
    return resultDTO;
  }

  /**
   * Get one ticketDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public TicketDTO findById(Integer id) {
    log.debug("Request to get Ticket: {}",id);
    Ticket ticket= ticketRepository.findById(id).orElse(null);
    TicketDTO dto = TicketFactory.ticketToTicketDTO(ticket);
    return dto;
  }

  /**
   * Get one ticket by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Ticket findTicket(Integer id) {
    log.debug("Request to get Ticket: {}",id);
    Ticket ticket= ticketRepository.findById(id).orElse(null);
    return ticket;
  }

  /**
   * Get all the tickets.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public List<TicketDTO> findAll() {
    log.debug("Request to get All Tickets");
    List<Ticket> result= ticketRepository.findAll();
    return TicketFactory.ticketToTicketDTOs(result);
  }

  /**
   * Delete ticket by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Ticket: {}",id);
    ticketRepository.deleteById(id);
  }
  public List<TicketDTO> findTicketsByInterfaceId(Integer idInterface) {
    // Retrieve the Interface object corresponding to the given ID

    Interface interfaceObject = interfaceRepository.findById(idInterface).orElse(null);

    // If the Interface object is found, use it to find the associated tickets
    if (interfaceObject != null) {
      // Retrieve the list of tickets associated with the given interface
      List<Ticket> tickets = ticketRepository.findByInterface1(interfaceObject);

      // Convert the list of Ticket entities to TicketDTOs
      return TicketFactory.ticketToTicketDTOs(tickets);
    } else {
      // Handle the case where the Interface object with the given ID is not found
      // For example, you could return an empty list or throw an exception
      return Collections.emptyList();
    }
  }
}

