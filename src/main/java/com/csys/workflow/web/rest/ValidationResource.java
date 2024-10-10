package com.csys.workflow.web.rest;

import com.csys.workflow.dto.DemandeDTO;
import com.csys.workflow.dto.ValidationDTO;
import com.csys.workflow.service.DemandeService;
import com.csys.workflow.service.ValidationService;
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
 * REST controller for managing Validation.
 */
@RestController
@RequestMapping("/api")
public class ValidationResource {
  private static final String ENTITY_NAME = "validation";

  private final ValidationService validationService;

  private final Logger log = LoggerFactory.getLogger(ValidationService.class);
  private final DemandeService demandeService;
  private final DemandeService etapeService;
  private final DemandeService employeService;

  public ValidationResource(ValidationService validationService, DemandeService demandeService, DemandeService etapeService, DemandeService employeService) {
    this.validationService=validationService;
      this.demandeService = demandeService;
      this.etapeService = etapeService;
      this.employeService = employeService;
  }

  /**
   * POST  /validations : Create a new validation.
   *
   * @param validationDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new validation, or with status 400 (Bad Request) if the validation has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/validations")
  public ResponseEntity<ValidationDTO> createValidation(@Valid @RequestBody ValidationDTO validationDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Validation : {}", validationDTO);



    // Check if idDemande exists
    if (validationDTO.getIdDemande() != null && !demandeService.existsById(validationDTO.getIdDemande())) {
      bindingResult.addError(new FieldError("ValidationDTO", "idDemande", "Demande with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    // Check if idEtape exists
//    if (!etapeService.existsById(validationDTO.getIdEtape())) {
//      bindingResult.addError(new FieldError("ValidationDTO", "idEtape", "Etape with this ID does not exist."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }

    // Check if idEmploye exists
//    if ( !employeService.existsById(validationDTO.getIdEmploye())) {
//      bindingResult.addError(new FieldError("ValidationDTO", "idEmploye", "Employe with this ID does not exist."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    if ( validationDTO.getIdValidation() != null) {
      bindingResult.addError( new FieldError("ValidationDTO","idValidation","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    ValidationDTO result = validationService.save(validationDTO);
    return ResponseEntity.created( new URI("/api/validations/"+ result.getIdValidation())).body(result);
  }

  /**
   * PUT  /validations : Updates an existing validation.
   *
   * @param id
   * @param validationDTO the validation to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated validation,
   * or with status 400 (Bad Request) if the validation is not valid,
   * or with status 500 (Internal Server Error) if the validation couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/validations/{id}")
  public ResponseEntity<ValidationDTO> updateValidation(@PathVariable Integer id, @Valid @RequestBody ValidationDTO validationDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update Validation: {}",id);

    // Check if idDemande exists
//    if (validationDTO.getIdDemande() != null && !demandeService.existsById(validationDTO.getIdDemande())) {
//      bindingResult.addError(new FieldError("ValidationDTO", "idDemande", "Demande with this ID does not exist."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
//
//    // Check if idEtape exists
//    if (validationDTO.getIdEtape() != null && !etapeService.existsById(validationDTO.getIdEtape())) {
//      bindingResult.addError(new FieldError("ValidationDTO", "idEtape", "Etape with this ID does not exist."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
//
//    // Check if idEmploye exists
//    if (validationDTO.getIdEmploye() != null && !employeService.existsById(validationDTO.getIdEmploye())) {
//      bindingResult.addError(new FieldError("ValidationDTO", "idEmploye", "Employe with this ID does not exist."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    validationDTO.setIdValidation(id);
    ValidationDTO result =validationService.update(validationDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /validations/{id} : get the "id" validation.
   *
   * @param id the id of the validation to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of validation, or with status 404 (Not Found)
   */
  @GetMapping("/validations/{id}")
  public ResponseEntity<ValidationDTO> getValidation(@PathVariable Integer id) {
    log.debug("Request to get Validation: {}",id);
    ValidationDTO dto = validationService.findById(id);
    RestPreconditions.checkFound(dto, "validation.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /validations : get all the validations.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of validations in body
   */
  @GetMapping("/validations")
  public List<ValidationDTO> getAllValidations() {
    log.debug("Request to get all  Validations : {}");
    return validationService.findAll();
  }

  /**
   * DELETE  /validations/{id} : delete the "id" validation.
   *
   * @param id the id of the validation to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/validations/{id}")
  public ResponseEntity<Void> deleteValidation(@PathVariable Integer id) {
    log.debug("Request to delete Validation: {}",id);
    validationService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/validations/demande/{idDemande}")
  public ResponseEntity<List<ValidationDTO>> findDemandsByEmployeId(@PathVariable Integer idDemande) {
    List<ValidationDTO> validations = validationService.findValidationsByDemandId(idDemande);
    return ResponseEntity.ok().body(validations);
  }
}

