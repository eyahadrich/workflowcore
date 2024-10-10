package com.csys.workflow.service;

import com.csys.workflow.domain.TypeTicket;
import com.csys.workflow.dto.TypeTicketDTO;
import com.csys.workflow.factory.TypeTicketFactory;
import com.csys.workflow.repository.TypeTicketRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeTicketService {
  private final Logger log = LoggerFactory.getLogger(TypeTicketService.class);

  private final TypeTicketRepository typeticketRepository;

  public TypeTicketService(TypeTicketRepository typeticketRepository) {
    this.typeticketRepository=typeticketRepository;
  }

  /**
   * Save a typeticketDTO.
   *
   * @param typeticketDTO
   * @return the persisted entity
   */
  public TypeTicketDTO save(TypeTicketDTO typeticketDTO) {
    log.debug("Request to save TypeTicket: {}",typeticketDTO);
    TypeTicket typeticket = TypeTicketFactory.typeticketDTOToTypeTicket(typeticketDTO);
    typeticket = typeticketRepository.save(typeticket);
    TypeTicketDTO resultDTO = TypeTicketFactory.typeticketToTypeTicketDTO(typeticket);
    return resultDTO;
  }
  public  boolean existsById(Integer id) {
    return typeticketRepository.existsById(id);
  }

  /**
   * Update a typeticketDTO.
   *
   * @param typeticketDTO
   * @return the updated entity
   */
  public TypeTicketDTO update(TypeTicketDTO typeticketDTO) {
    log.debug("Request to update TypeTicket: {}",typeticketDTO);
    TypeTicket inBase= typeticketRepository.findById(typeticketDTO.getIdTypeTicket()).orElse(null);
    Preconditions.checkArgument(inBase != null, "typeticket.NotFound");
    TypeTicket typeticket = TypeTicketFactory.typeticketDTOToTypeTicket(typeticketDTO);
    typeticket = typeticketRepository.save(typeticket);
    TypeTicketDTO resultDTO = TypeTicketFactory.typeticketToTypeTicketDTO(typeticket);
    return resultDTO;
  }

  /**
   * Get one typeticketDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
          readOnly = true
  )
  public TypeTicketDTO findById(Integer id) {
    log.debug("Request to get TypeTicket: {}",id);
    TypeTicket typeticket= typeticketRepository.findById(id).orElse(null);
    TypeTicketDTO dto = TypeTicketFactory.typeticketToTypeTicketDTO(typeticket);
    return dto;
  }

  /**
   * Get one typeticket by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
          readOnly = true
  )
  public TypeTicket findTypeTicket(Integer id) {
    log.debug("Request to get TypeTicket: {}",id);
    TypeTicket typeticket= typeticketRepository.findById(id).orElse(null);
    return typeticket;
  }

  /**
   * Get all the typetickets.
   *
   * @return the the list of entities
   */
  @Transactional(
          readOnly = true
  )
  public List<TypeTicketDTO> findAll() {
    log.debug("Request to get All TypeTickets");
    List<TypeTicket> result= typeticketRepository.findAll();
    return TypeTicketFactory.typeticketToTypeTicketDTOs(result);
  }

  /**
   * Delete typeticket by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete TypeTicket: {}",id);
    typeticketRepository.deleteById(id);
  }
}