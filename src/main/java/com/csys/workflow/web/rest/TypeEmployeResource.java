package com.csys.workflow.web.rest;

import com.csys.workflow.dto.TypeEmployeDTO;
import com.csys.workflow.service.TypeEmployeService;
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
 * REST controller for managing TypeEmploye.
 */
@RestController
@RequestMapping("/api")
public class TypeEmployeResource {
  private static final String ENTITY_NAME = "typeemploye";

  private final TypeEmployeService typeemployeService;

  private final Logger log = LoggerFactory.getLogger(TypeEmployeService.class);

  public TypeEmployeResource(TypeEmployeService typeemployeService) {
    this.typeemployeService=typeemployeService;
  }

  /**
   * POST  /typeemployes : Create a new typeemploye.
   *
   * @param typeemployeDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new typeemploye, or with status 400 (Bad Request) if the typeemploye has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/typeemployes")
  public ResponseEntity<TypeEmployeDTO> createTypeEmploye(@Valid @RequestBody TypeEmployeDTO typeemployeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save TypeEmploye : {}", typeemployeDTO);
//    // Check if the idTypeEmploye already exists
//    if (typeemployeService.existsById(typeemployeDTO.getIdTypeEmploye())) {
//      bindingResult.addError(new FieldError("TypeEmployeDTO", "idTypeEmploye", "TypeEmploye with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    if ( typeemployeDTO.getIdTypeEmploye() != null) {
      bindingResult.addError( new FieldError("TypeEmployeDTO","idTypeEmploye","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeEmployeDTO result = typeemployeService.save(typeemployeDTO);
    return ResponseEntity.created( new URI("/api/typeemployes/"+ result.getIdTypeEmploye())).body(result);
  }

  /**
   * PUT  /typeemployes : Updates an existing typeemploye.
   *
   * @param id
   * @param typeemployeDTO the typeemploye to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated typeemploye,
   * or with status 400 (Bad Request) if the typeemploye is not valid,
   * or with status 500 (Internal Server Error) if the typeemploye couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/typeemployes/{id}")
  public ResponseEntity<TypeEmployeDTO> updateTypeEmploye(@PathVariable Integer id, @Valid @RequestBody TypeEmployeDTO typeemployeDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update TypeEmploye: {}",id);
    typeemployeDTO.setIdTypeEmploye(id);
    TypeEmployeDTO result =typeemployeService.update(typeemployeDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /typeemployes/{id} : get the "id" typeemploye.
   *
   * @param id the id of the typeemploye to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of typeemploye, or with status 404 (Not Found)
   */
  @GetMapping("/typeemployes/{id}")
  public ResponseEntity<TypeEmployeDTO> getTypeEmploye(@PathVariable Integer id) {
    log.debug("Request to get TypeEmploye: {}",id);
    TypeEmployeDTO dto = typeemployeService.findById(id);
    RestPreconditions.checkFound(dto, "typeemploye.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /typeemployes : get all the typeemployes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of typeemployes in body
   */
  @GetMapping("/typeemployes")
  public List<TypeEmployeDTO> getAllTypeEmployes() {
    log.debug("Request to get all  TypeEmployes : {}");
    return typeemployeService.findAll();
  }

  /**
   * DELETE  /typeemployes/{id} : delete the "id" typeemploye.
   *
   * @param id the id of the typeemploye to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/typeemployes/{id}")
  public ResponseEntity<Void> deleteTypeEmploye(@PathVariable Integer id) {
    log.debug("Request to delete TypeEmploye: {}",id);
    typeemployeService.delete(id);
    return ResponseEntity.ok().build();
  }
}

