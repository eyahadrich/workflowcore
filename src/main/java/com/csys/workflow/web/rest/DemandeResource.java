package com.csys.workflow.web.rest;

import com.csys.workflow.dto.DemandeDTO;
import com.csys.workflow.dto.InterfaceDTO;
import com.csys.workflow.service.DemandeService;
import com.csys.workflow.service.EmployeService;
import com.csys.workflow.service.InterfaceService;
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
 * REST controller for managing Demande.
 */
@RestController
@RequestMapping("/api")
public class DemandeResource {
  private static final String ENTITY_NAME = "demande";

  private final DemandeService demandeService;
  private final EmployeService employeService;
  private final InterfaceService interfaceService;

  private final Logger log = LoggerFactory.getLogger(DemandeService.class);

  public DemandeResource(DemandeService demandeService, EmployeService employeService, InterfaceService interfaceService) {
    this.demandeService=demandeService;
      this.employeService = employeService;
      this.interfaceService = interfaceService;
  }

  /**
   * POST  /demandes : Create a new demande.
   *
   * @param demandeDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new demande, or with status 400 (Bad Request) if the demande has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/demandes")
  public ResponseEntity<DemandeDTO> createDemande(@Valid @RequestBody DemandeDTO demandeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Demande : {}", demandeDTO);

// Check if the employe associated with the demande exists
    if (demandeDTO.getIdEmploye() != null && !employeService.existsById(demandeDTO.getIdEmploye())) {
      bindingResult.addError(new FieldError("DemandeDTO", "idEmploye", "Employe with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

// Check if the interface associated with the demande exists
    if (demandeDTO.getIdInterface() != null && !interfaceService.existsById(demandeDTO.getIdInterface())) {
      bindingResult.addError(new FieldError("DemandeDTO", "idInterface", "Interface with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    if ( demandeDTO.getIdDemande() != null) {
      bindingResult.addError( new FieldError("DemandeDTO","idDemande","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    DemandeDTO result = demandeService.save(demandeDTO);
    return ResponseEntity.created( new URI("/api/demandes/"+ result.getIdDemande())).body(result);
  }

  /**
   * PUT  /demandes : Updates an existing demande.
   *
   * @param id
   * @param demandeDTO the demande to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated demande,
   * or with status 400 (Bad Request) if the demande is not valid,
   * or with status 500 (Internal Server Error) if the demande couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/demandes/{id}")
  public ResponseEntity<DemandeDTO> updateDemande(@PathVariable Integer id, @Valid @RequestBody DemandeDTO demandeDTO ,BindingResult bindingResult ) throws MethodArgumentNotValidException {
    log.debug("Request to update Demande: {}",id);
    // Check if the employe associated with the demande exists
    if (demandeDTO.getIdEmploye() != null && !employeService.existsById(demandeDTO.getIdEmploye())) {
      bindingResult.addError(new FieldError("DemandeDTO", "idEmploye", "Employe with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

// Check if the interface associated with the demande exists
    if (demandeDTO.getIdInterface() != null && !interfaceService.existsById(demandeDTO.getIdInterface())) {
      bindingResult.addError(new FieldError("DemandeDTO", "idInterface", "Interface with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    demandeDTO.setIdDemande(id);
    DemandeDTO result =demandeService.update(demandeDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /demandes/{id} : get the "id" demande.
   *
   * @param id the id of the demande to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of demande, or with status 404 (Not Found)
   */
  @GetMapping("/demandes/{id}")
  public ResponseEntity<DemandeDTO> getDemande(@PathVariable Integer id) {
    log.debug("Request to get Demande: {}",id);
    DemandeDTO dto = demandeService.findById(id);
    RestPreconditions.checkFound(dto, "demande.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /demandes : get all the demandes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of demandes in body
   */
  @GetMapping("/demandes")
  public List<DemandeDTO> getAllDemandes() {
    log.debug("Request to get all  Demandes : {}");
    return demandeService.findAll();
  }

  /**
   * DELETE  /demandes/{id} : delete the "id" demande.
   *
   * @param id the id of the demande to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/demandes/{id}")
  public ResponseEntity<Void> deleteDemande(@PathVariable Integer id) {
    log.debug("Request to delete Demande: {}",id);
    demandeService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/demandes/employe/{idEmploye}")
  public ResponseEntity<List<DemandeDTO>> findDemandsByEmployeId(@PathVariable Integer idEmploye) {
    // Use EmployeService to find demands by employee ID
    List<DemandeDTO> demands = demandeService.findDemandsByEmployeId(idEmploye);
    return ResponseEntity.ok().body(demands);
  }
  @GetMapping("/demandes/employe/{idEmploye}/interface/{idInterface}")
  public ResponseEntity<List<DemandeDTO>> findDemandsByEmployeIdAndInterfaceId(@PathVariable Integer idEmploye, @PathVariable Integer idInterface) {
    List<DemandeDTO> demands = demandeService.findDemandsByEmployeIdAndInterfaceId(idEmploye, idInterface);
    if (demands.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(demands);
    }
  }


}

