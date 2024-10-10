package com.csys.workflow.web.rest;

import com.csys.workflow.dto.EquipeDTO;
import com.csys.workflow.service.EquipeService;
import com.csys.workflow.service.RoleEquipeService;
import com.csys.workflow.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
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
 * REST controller for managing Equipe.
 */
@RestController
@RequestMapping("/api")
public class EquipeResource {
  private static final String ENTITY_NAME = "equipe";

  private final RoleEquipeService roleEquipeService;

  private final EquipeService equipeService;

  private final Logger log = LoggerFactory.getLogger(EquipeService.class);

  public EquipeResource(RoleEquipeService roleEquipeService, EquipeService equipeService) {
      this.roleEquipeService = roleEquipeService;
      this.equipeService=equipeService;
  }

  /**
   * POST  /equipes : Create a new equipe.
   *
   * @param equipeDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new equipe, or with status 400 (Bad Request) if the equipe has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/equipes")
  public ResponseEntity<EquipeDTO> createEquipe(@Valid @RequestBody EquipeDTO equipeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Equipe : {}", equipeDTO);

    if ( equipeDTO.getIdEquipe() != null) {
      bindingResult.addError( new FieldError("EquipeDTO","idEquipe","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    EquipeDTO result = equipeService.save(equipeDTO);
    return ResponseEntity.created( new URI("/api/equipes/"+ result.getIdEquipe())).body(result);
  }
  
  /**
   * PUT  /equipes : Updates an existing equipe.
   *
   * @param id
   * @param equipeDTO the equipe to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated equipe,
   * or with status 400 (Bad Request) if the equipe is not valid,
   * or with status 500 (Internal Server Error) if the equipe couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/equipes/{id}")
  public ResponseEntity<EquipeDTO> updateEquipe(@PathVariable Integer id, @Valid @RequestBody EquipeDTO equipeDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update Equipe: {}",id);
    equipeDTO.setIdEquipe(id);
    EquipeDTO result =equipeService.update(equipeDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /equipes/{id} : get the "id" equipe.
   *
   * @param id the id of the equipe to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of equipe, or with status 404 (Not Found)
   */
  @GetMapping("/equipes/{id}")
  public ResponseEntity<EquipeDTO> getEquipe(@PathVariable Integer id) {
    log.debug("Request to get Equipe: {}",id);
    EquipeDTO dto = equipeService.findById(id);
    RestPreconditions.checkFound(dto, "equipe.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /equipes : get all the equipes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of equipes in body
   */
  @GetMapping("/equipes")
  public List<EquipeDTO> getAllEquipes() {
    log.debug("Request to get all  Equipes : {}");
    return equipeService.findAll();
  }


  /**
   * DELETE  /equipes/{id} : delete the "id" equipe.
   *
   * @param id the id of the equipe to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/equipes/{id}")
  public ResponseEntity<Void> deleteEquipe(@PathVariable Integer id) {
    log.debug("Request to delete Equipe: {}",id);
    equipeService.delete(id);
    return ResponseEntity.ok().build();
  }




    /**
     * GET /equipes/employee/{employeeId} : Get all the equipes that an employee is part of.
     *
     * @param employeeId the id of the employee
     * @return the ResponseEntity with status 200 (OK) and the list of equipes in body
     */
    @GetMapping("/equipes/employee/{employeeId}")
    public List<EquipeDTO> getAllEquipesByEmployeeId(@PathVariable Integer employeeId) {
      log.debug("Request to get all Equipes for employee: {}", employeeId);
      return equipeService.findAllEquipesByEmployeeId(employeeId);
    }
  @GetMapping("/equipes/employeeid/{employeeId}")
  public List<List<Integer>> findAllEmployeeIdsByEmployeeId(@PathVariable Integer employeeId) {
    log.debug("Request to get all Equipes for employee: {}", employeeId);
    return equipeService.findAllEmployeeIdsByEmployeeId(employeeId);
  }
  @GetMapping("/equipes/employeeid/{employeeId}/role/{role}")
  public Set<Integer> findAllEmployeeIdsByEmployeeIdAndRole(@PathVariable Integer employeeId, @PathVariable String role) {
    log.debug("Request to get all Equipes for employee: {} with role: {}", employeeId, role);
    return equipeService.findAllEmployeeIdsOfEquipeByEmployeeIdAndRole(employeeId, role);
  }
//  @GetMapping("/equipes/employeeid1/{employeeId}/role1/{role}")
//  public List<Integer> findAllEmployeeIdsByEmployeeIdAndRole1(@PathVariable Integer employeeId, @PathVariable String role) {
//    log.debug("Request to get all Equipes for employee: {} with role: {}", employeeId, role);
//    return equipeService.findAllEmployeeIdsofEquipeByEmployeeIdAndRole1(employeeId, role);
//  }
}

