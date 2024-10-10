package com.csys.workflow.web.rest;


import com.csys.workflow.dto.EtapeDTO;

import com.csys.workflow.dto.TicketDTO;
import com.csys.workflow.service.EtapeService;
import com.csys.workflow.service.WorkflowService;
import com.csys.workflow.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
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
 * REST controller for managing Etape.
 */
@RestController
@RequestMapping("/api")
public class EtapeResource {
  private static final String ENTITY_NAME = "etape";

  private final EtapeService etapeService;
  private final WorkflowService workflowService;

  private final Logger log = LoggerFactory.getLogger(EtapeService.class);

  public EtapeResource(EtapeService etapeService, WorkflowService workflowService) {
    this.etapeService=etapeService;
      this.workflowService = workflowService;
  }

  /**
   * POST  /etapes : Create a new etape.
   *
   * @param etapeDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new etape, or with status 400 (Bad Request) if the etape has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/etapes")
  public ResponseEntity<EtapeDTO> createEtape(@Valid @RequestBody EtapeDTO etapeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Etape : {}", etapeDTO);
    // Check if an etape with the same ID already exists
//    if (etapeService.existsById(etapeDTO.getIdEtape())) {
//      bindingResult.addError(new FieldError("EtapeDTO", "idEtape", "Etape with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    // Check if the workflow associated with the etape exists
    if (!workflowService.existsById(etapeDTO.getIdWorkflow())) {
      bindingResult.addError(new FieldError("EtapeDTO", "workflow", "Workflow with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( etapeDTO.getIdEtape() != null) {
      bindingResult.addError( new FieldError("EtapeDTO","idEtape","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    EtapeDTO result = etapeService.save(etapeDTO);
    return ResponseEntity.created( new URI("/api/etapes/"+ result.getIdEtape())).body(result);
  }

  /**
   * PUT  /etapes : Updates an existing etape.
   *
   * @param id
   * @param etapeDTO the etape to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated etape,
   * or with status 400 (Bad Request) if the etape is not valid,
   * or with status 500 (Internal Server Error) if the etape couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/etapes/{id}")
  public ResponseEntity<EtapeDTO> updateEtape(@PathVariable Integer id, @Valid @RequestBody EtapeDTO etapeDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update Etape: {}",id);
    if (!workflowService.existsById(etapeDTO.getIdWorkflow())) {
      bindingResult.addError(new FieldError("EtapeDTO", "workflow", "Workflow with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    etapeDTO.setIdEtape(id);
    EtapeDTO result =etapeService.update(etapeDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /etapes/{id} : get the "id" etape.
   *
   * @param id the id of the etape to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of etape, or with status 404 (Not Found)
   */
  @GetMapping("/etapes/{id}")
  public ResponseEntity<EtapeDTO> getEtape(@PathVariable Integer id) {
    log.debug("Request to get Etape: {}",id);
    EtapeDTO dto = etapeService.findById(id);
    RestPreconditions.checkFound(dto, "etape.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /etapes : get all the etapes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of etapes in body
   */
  @GetMapping("/etapes")
  public List<EtapeDTO> getAllEtapes() {
    log.debug("Request to get all  Etapes : {}");
    return etapeService.findAll();
  }

  /**
   * DELETE  /etapes/{id} : delete the "id" etape.
   *
   * @param id the id of the etape to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/etapes/{id}")
  public ResponseEntity<Void> deleteEtape(@PathVariable Integer id) {
    log.debug("Request to delete Etape: {}",id);
    etapeService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/etapes/workflow/{id}")
  public List<EtapeDTO> getEtapesByWorkflow(@PathVariable Integer id) {
    log.debug("Request to get all  Etapes by workflow id : {}",id);
    return etapeService.findEtapesByWorkflowId(id);
  }

}

