package com.csys.workflow.web.rest;

import com.csys.workflow.dto.WorkflowDTO;
import com.csys.workflow.service.WorkflowService;
import com.csys.workflow.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Workflow.
 */
@RestController
@RequestMapping("/api")
public class WorkflowResource {
  private static final String ENTITY_NAME = "workflow";

  private final WorkflowService workflowService;

  private final Logger log = LoggerFactory.getLogger(WorkflowService.class);

  public WorkflowResource(WorkflowService workflowService) {
    this.workflowService=workflowService;
  }

  /**
   * POST  /workflows : Create a new workflow.
   *
   * @param workflowDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new workflow, or with status 400 (Bad Request) if the workflow has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/workflows")
  public ResponseEntity<WorkflowDTO> createWorkflow(@Valid @RequestBody WorkflowDTO workflowDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Workflow : {}", workflowDTO);
    if ( workflowDTO.getIdWorkflow() != null) {
      bindingResult.addError( new FieldError("WorkflowDTO","idWorkflow","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    WorkflowDTO result = workflowService.save(workflowDTO);
    return ResponseEntity.created( new URI("/api/workflows/"+ result.getIdWorkflow())).body(result);
  }

  /**
   * PUT  /workflows : Updates an existing workflow.
   *
   * @param id
   * @param workflowDTO the workflow to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated workflow,
   * or with status 400 (Bad Request) if the workflow is not valid,
   * or with status 500 (Internal Server Error) if the workflow couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/workflows/{id}")
  public ResponseEntity<WorkflowDTO> updateWorkflow(@PathVariable Integer id, @Valid @RequestBody WorkflowDTO workflowDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update Workflow: {}",id);
    workflowDTO.setIdWorkflow(id);
    WorkflowDTO result =workflowService.update(workflowDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /workflows/{id} : get the "id" workflow.
   *
   * @param id the id of the workflow to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of workflow, or with status 404 (Not Found)
   */
  @GetMapping("/workflows/{id}")
  public ResponseEntity<WorkflowDTO> getWorkflow(@PathVariable Integer id) {
    log.debug("Request to get Workflow: {}",id);
    WorkflowDTO dto = workflowService.findById(id);
    RestPreconditions.checkFound(dto, "workflow.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /workflows : get all the workflows.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of workflows in body
   */
  @GetMapping("/workflows")
  public List<WorkflowDTO> getAllWorkflows() {
    log.debug("Request to get all  Workflows : {}");
    return workflowService.findAll();
  }

  /**
   * DELETE  /workflows/{id} : delete the "id" workflow.
   *
   * @param id the id of the workflow to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/workflows/{id}")
  public ResponseEntity<Void> deleteWorkflow(@PathVariable Integer id) {
    log.debug("Request to delete Workflow: {}",id);
    workflowService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/workflows/exists/nomWorkflow/{nomWorkflow}")
  public ResponseEntity<Boolean> checkIfWorkflowExistsByNomWorkflow(@PathVariable String nomWorkflow) {
    log.debug("Request to check if Interface exists by nom: {}", nomWorkflow);
    boolean exists = workflowService.existsByNomWorkflow(nomWorkflow);
    return ResponseEntity.ok().body(exists);
  }
}

