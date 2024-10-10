package com.csys.workflow.service;


import com.csys.workflow.domain.Interface;
import com.csys.workflow.domain.Workflow;
import com.csys.workflow.dto.InterfaceDTO;
import com.csys.workflow.factory.InterfaceFactory;
import com.csys.workflow.repository.InterfaceRepository;
import com.csys.workflow.repository.WorkflowRepository;

import java.lang.Integer;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Service Implementation for managing Interface.
 */
@Service
@Transactional
public class InterfaceService {
  private final Logger log = LoggerFactory.getLogger(InterfaceService.class);

  private final InterfaceRepository interfaceRepository;
  private final WorkflowRepository workflowRepository;

  public InterfaceService(InterfaceRepository interfaceRepository, WorkflowRepository workflowRepository) {
    this.interfaceRepository=interfaceRepository;
      this.workflowRepository = workflowRepository;
  }

  /**
   * Save a interfaceDTO.
   *
   * @param interfaceDTO
   * @return the persisted entity
   */
  public InterfaceDTO save(InterfaceDTO interfaceDTO) {
    log.debug("Request to save Interface: {}",interfaceDTO);
    Interface inter = InterfaceFactory.interDTOToInterface(interfaceDTO);
    inter = interfaceRepository.save(inter);
    InterfaceDTO resultDTO = InterfaceFactory.interToInterfaceDTO(inter);
    return resultDTO;
  }
  public  boolean existsById(Integer id) {
    return interfaceRepository.existsById(id);
  }

  /**
   * Update a interfaceDTO.
   *
   * @param interfaceDTO
   * @return the updated entity
   */
//  public InterfaceDTO update(InterfaceDTO interfaceDTO) {
//    log.debug("Request to update Interface: {}",interfaceDTO);
//    Interface inBase= interfaceRepository.findById(interfaceDTO.getIdInterface()).orElse(null);
//    Preconditions.checkArgument(inBase != null, "inter.NotFound");
//    Interface inter = InterfaceFactory.interDTOToInterface(interfaceDTO);
//    inter = interfaceRepository.save(inter);
//    InterfaceDTO resultDTO = InterfaceFactory.interToInterfaceDTO(inter);
//    return resultDTO;
//  }
  @Transactional
  public InterfaceDTO update(InterfaceDTO interfaceDTO) {
    // Retrieve the Interface entity from the database
    Interface inter = interfaceRepository.findById(interfaceDTO.getIdInterface())
            .orElseThrow(() -> new EntityNotFoundException("Interface not found with id: " + interfaceDTO.getIdInterface()));

    // Update the interface name
    inter.setNomInterface(interfaceDTO.getNomInterface());
    inter.setDateCreation(interfaceDTO.getDateCreation());
    inter.setDateModification(new Date());
    Workflow workflow = workflowRepository.findById(interfaceDTO.getIdWorkflow())
            .orElseThrow(() -> new EntityNotFoundException("Workflow not found with id: " + interfaceDTO.getIdWorkflow()));

    // Associate the updated Workflow with the Interface
    inter.setWorkflow(workflow);

    // Save the updated entity
    Interface updatedInterface = interfaceRepository.save(inter);

    // Convert the updated entity to DTO and return
    return InterfaceFactory.interToInterfaceDTO(updatedInterface);
  }

  /**
   * Get one interfaceDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public InterfaceDTO findById(Integer id) {
    log.debug("Request to get Interface: {}",id);
    Interface inter= interfaceRepository.findById(id).orElse(null);
    InterfaceDTO dto = InterfaceFactory.interToInterfaceDTO(inter);
    return dto;
  }

  /**
   * Get one inter by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Interface findInterface(Integer id) {
    log.debug("Request to get Interface: {}",id);
    Interface inter= interfaceRepository.findById(id).orElse(null);
    return inter;
  }

  /**
   * Get all the interfaces.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public List<InterfaceDTO> findAll() {
    log.debug("Request to get All Interfaces");
    List<Interface> result= interfaceRepository.findAll();
    return InterfaceFactory.interToInterfaceDTOs(result);
  }

  /**
   * Delete inter by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Interface: {}",id);
    interfaceRepository.deleteById(id);
  }
  @Transactional(readOnly = true)
  public boolean existsByNomInterface(String nomInterface) {
    log.debug("Request to check if Interface exists by nom: {}", nomInterface);
    Interface inter = interfaceRepository.findByNomInterface(nomInterface);
    return inter != null;
  }
}

