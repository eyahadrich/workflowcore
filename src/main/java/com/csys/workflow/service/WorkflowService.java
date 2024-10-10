package com.csys.workflow.service;

import com.csys.workflow.domain.Workflow;
import com.csys.workflow.dto.WorkflowDTO;
import com.csys.workflow.factory.WorkflowFactory;
import com.csys.workflow.repository.WorkflowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class WorkflowService {
  private final Logger log = LoggerFactory.getLogger(WorkflowService.class);

  private final WorkflowRepository workflowRepository;

  public WorkflowService(WorkflowRepository workflowRepository) {
    this.workflowRepository=workflowRepository;
  }

  /**
   * Save a workflowDTO.
   *
   * @param workflowDTO
   * @return the persisted entity
   */
  public WorkflowDTO save(WorkflowDTO workflowDTO) {
    log.debug("Request to save Workflow: {}",workflowDTO);
    Workflow workflow = WorkflowFactory.workflowDTOToWorkflow(workflowDTO);
    workflow = workflowRepository.save(workflow);
    WorkflowDTO resultDTO = WorkflowFactory.workflowToWorkflowDTO(workflow);
    return resultDTO;
  }

  /**
   * Update a workflowDTO.
   *
   * @param workflowDTO
   * @return the updated entity
   */
//  public WorkflowDTO update(WorkflowDTO workflowDTO) {
//    log.debug("Request to update Workflow: {}",workflowDTO);
//    Workflow inBase= workflowRepository.findById(workflowDTO.getIdWorkflow()).orElse(null);
//    Preconditions.checkArgument(inBase != null, "workflow.NotFound");
//    Workflow workflow = WorkflowFactory.workflowDTOToWorkflow(workflowDTO);
//    workflow = workflowRepository.save(workflow);
//    WorkflowDTO resultDTO = WorkflowFactory.workflowToWorkflowDTO(workflow);
//    return resultDTO;
//  }
  @Transactional
  public WorkflowDTO update(WorkflowDTO workflowDTO) {
    // Retrieve the Interface entity from the database
    Workflow workflow = workflowRepository.findById(workflowDTO.getIdWorkflow())
            .orElseThrow(() -> new EntityNotFoundException("Interface not found with id: " + workflowDTO.getIdWorkflow()));

    // Update the interface name
    workflow.setNomWorkflow(workflowDTO.getNomWorkflow());

    // Save the updated entity
    Workflow updatedWorkflow = workflowRepository.save(workflow);

    // Convert the updated entity to DTO and return
    return WorkflowFactory.workflowToWorkflowDTO(updatedWorkflow);
  }

  /**
   * Get one workflowDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
          readOnly = true
  )
  public WorkflowDTO findById(Integer id) {
    log.debug("Request to get Workflow: {}",id);
    Workflow workflow= workflowRepository.findById(id).orElse(null);
    WorkflowDTO dto = WorkflowFactory.workflowToWorkflowDTO(workflow);
    return dto;
  }

  /**
   * Get one workflow by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
          readOnly = true
  )
  public Workflow findWorkflow(Integer id) {
    log.debug("Request to get Workflow: {}",id);
    Workflow workflow= workflowRepository.findById(id).orElse(null);
    return workflow;
  }

  /**
   * Get all the workflows.
   *
   * @return the the list of entities
   */
  @Transactional(
          readOnly = true
  )
  public List<WorkflowDTO> findAll() {
    log.debug("Request to get All Workflows");
    List<Workflow> result= workflowRepository.findAll();
    return WorkflowFactory.workflowToWorkflowDTOs(result);
  }
  public  boolean existsById(Integer id) {
    return workflowRepository.existsById(id);
  }

  /**
   * Delete workflow by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Workflow: {}",id);
    workflowRepository.deleteById(id);
  }
  @Transactional(readOnly = true)
  public boolean existsByNomWorkflow(String nomWorkflow) {
    log.debug("Request to check if Workflow exists by nom: {}", nomWorkflow);
    Workflow workflow = workflowRepository.findByNomWorkflow(nomWorkflow);
    return workflow != null;
  }
}