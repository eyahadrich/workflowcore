package com.csys.workflow.service;

import com.csys.workflow.domain.*;
import com.csys.workflow.dto.DemandeDTO;
import com.csys.workflow.dto.EmployeDTO;
import com.csys.workflow.dto.InterfaceDTO;
import com.csys.workflow.factory.DemandeFactory;
import com.csys.workflow.repository.DemandeRepository;

import java.lang.Integer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Service Implementation for managing Demande.
 */
@Service
@Transactional
public class DemandeService {
  private final Logger log = LoggerFactory.getLogger(DemandeService.class);

  private final DemandeRepository demandeRepository;
  private  final EmployeService employeService;
  private final InterfaceService interfaceService;

  public DemandeService(DemandeRepository demandeRepository, EmployeService employeService, EmployeService employeRepository, InterfaceService interfaceService) {
    this.demandeRepository=demandeRepository;

      this.employeService = employeService;
      this.interfaceService = interfaceService;
  }

  /**
   * Save a demandeDTO.
   *
   * @param demandeDTO
   * @return the persisted entity
   */
  public DemandeDTO save(DemandeDTO demandeDTO) {
    log.debug("Request to save Demande: {}",demandeDTO);
    Demande demande = DemandeFactory.demandeDTOToDemande(demandeDTO);
    demande = demandeRepository.save(demande);
    DemandeDTO resultDTO = DemandeFactory.demandeToDemandeDTO(demande);
    return resultDTO;
  }

  /**
   * Update a demandeDTO.
   *
   * @param demandeDTO
   * @return the updated entity
   */

//  public DemandeDTO update(DemandeDTO demandeDTO) {
//    log.debug("Request to update Demande: {}",demandeDTO);
//    Demande inBase= demandeRepository.findById(demandeDTO.getIdDemande()).orElse(null);
//    Preconditions.checkArgument(inBase != null, "demande.NotFound");
//    Demande demande = DemandeFactory.demandeDTOToDemande(demandeDTO);
//    demande = demandeRepository.save(demande);
//    DemandeDTO resultDTO = DemandeFactory.demandeToDemandeDTO(demande);
//    return resultDTO;
//  }
  @Transactional
  public DemandeDTO update(DemandeDTO demandeDTO) {
    // Retrieve the Interface entity from the database
    Demande demande = demandeRepository.findById(demandeDTO.getIdDemande())
            .orElseThrow(() -> new EntityNotFoundException("Demand not found with id: " + demandeDTO.getIdDemande()));

    // Update the interface name
    demande.setIdDemande(demandeDTO.getIdDemande());
    demande.setStatus(demandeDTO.getStatus());
    demande.setDateCreationDemande(demandeDTO.getDateCreationDemande());


    Employe employe = new Employe();
    TypeEmploye typeEmploye=new TypeEmploye();
    employe.setTypeEmploye(typeEmploye);
    employe.setIdEmploye(demandeDTO.getIdEmploye());
    demande.setEmploye(employe);


    // Set the Interface object

    Interface interface1 = new Interface();
    Workflow workflow=new Workflow();
    interface1.setWorkflow(workflow);
    interface1.setIdInterface(demandeDTO.getIdInterface());
    demande.setInterface1(interface1);


    // Save the updated entity
    Demande updatedDemande = demandeRepository.save(demande);

    // Convert the updated entity to DTO and return
    return DemandeFactory.demandeToDemandeDTO(updatedDemande);
  }

  /**
   * Get one demandeDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public DemandeDTO findById(Integer id) {
    log.debug("Request to get Demande: {}",id);
    Demande demande= demandeRepository.findById(id).orElse(null);
    DemandeDTO dto = DemandeFactory.demandeToDemandeDTO(demande);
    return dto;
  }
  public  boolean existsById(Integer id) {
    return demandeRepository.existsById(id);
  }

  /**
   * Get one demande by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Demande findDemande(Integer id) {
    log.debug("Request to get Demande: {}",id);
    Demande demande= demandeRepository.findById(id).orElse(null);
    return demande;
  }

  /**
   * Get all the demandes.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public List<DemandeDTO> findAll() {
    log.debug("Request to get All Demandes");
    List<Demande> result= demandeRepository.findAll();
    return DemandeFactory.demandeToDemandeDTOs(result);
  }

  /**
   * Delete demande by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Demande: {}",id);
    demandeRepository.deleteById(id);
  }
  @Transactional(readOnly = true)
  public List<DemandeDTO> findDemandsByEmployeId(Integer idEmploye) {
    // Retrieve the Employe object corresponding to the given ID
    EmployeDTO employeDTO = employeService.findById(idEmploye);

    // If the Employe object is found, use it to find the associated demands
    if (employeDTO!=null) {
      // Retrieve the list of demands associated with the given employe
      List<Demande> demands = demandeRepository.findByEmploye_IdEmploye(employeDTO.getIdEmploye());

      // Convert the list of Demande entities to DemandeDTOs
      return DemandeFactory.demandeToDemandeDTOs(demands);
    } else {
      // Handle the case where the Employe object with the given ID is not found
      // For example, you could return an empty list or throw an exception
      List<Demande> demands = demandeRepository.findAll();
      return DemandeFactory.demandeToDemandeDTOs(demands);
    }
  }
  @Transactional(readOnly = true)
  public List<DemandeDTO> findDemandsByInterfaceId(Integer idInterface) {
    // Retrieve the Employe object corresponding to the given ID
    InterfaceDTO interfaceDTO = interfaceService.findById(idInterface);

    // If the Employe object is found, use it to find the associated demands
    if (interfaceDTO!=null) {
      // Retrieve the list of demands associated with the given employe
      List<Demande> demands = demandeRepository.findByInterface1_IdInterface(interfaceDTO.getIdInterface());

      // Convert the list of Demande entities to DemandeDTOs
      return DemandeFactory.demandeToDemandeDTOs(demands);
    } else {
      // Handle the case where the Employe object with the given ID is not found
      // For example, you could return an empty list or throw an exception
      List<Demande> demands = demandeRepository.findAll();
      return DemandeFactory.demandeToDemandeDTOs(demands);
    }
  }


  @Transactional(readOnly = true)
  public List<DemandeDTO> findDemandsByEmployeIdAndInterfaceId(Integer idEmploye, Integer idInterface) {
    EmployeDTO employeDTO = employeService.findById(idEmploye);
    InterfaceDTO interfaceDTO = interfaceService.findById(idInterface);
    if (employeDTO != null && interfaceDTO != null) {
      List<Demande> demands = demandeRepository.findByEmploye_IdEmployeAndInterface1_IdInterface(idEmploye, idInterface);
      return DemandeFactory.demandeToDemandeDTOs(demands);
    } else {
      List<Demande> demands = demandeRepository.findAll();
      return DemandeFactory.demandeToDemandeDTOs(demands);
    }
  }
}

