package com.csys.workflow.web.rest;

import com.csys.workflow.domain.Employe;
import com.csys.workflow.dto.EmployeDTO;
import com.csys.workflow.service.EmployeService;
import com.csys.workflow.service.TypeEmployeService;
import com.csys.workflow.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Employe.
 */
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class EmployeResource {
  private static final String ENTITY_NAME = "employe";

  private final EmployeService employeService;
  private  final TypeEmployeService typeEmployeService;
  private final ModelMapper modelMapper = new ModelMapper();

  private final Logger log = LoggerFactory.getLogger(EmployeService.class);

  public EmployeResource(EmployeService employeService, TypeEmployeService typeEmployeService) {
    this.employeService=employeService;
      this.typeEmployeService = typeEmployeService;
  }

  @PostMapping("/employs/authentication")
  @ApiOperation(value = "${UtilisateurResource.signin}")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 422, message = "Invalid username/password supplied")
  })
  public ResponseEntity<Map<String, String>> signin(@Valid @RequestBody ConfigClientProperties.Credentials credentials, HttpServletRequest request) {
    log.info("Signing in user: {}", credentials.getUsername());
    Map<String, String> token = employeService.signin(credentials.getUsername(), credentials.getPassword());
    request.setAttribute("authenticatedUsername", credentials.getUsername()); // Stocker le nom d'utilisateur authentifié dans la requête
    return ResponseEntity.ok(token);
  }


  /**
   * POST  /employes : Create a new employe.
   *
   * @param employeDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new employe, or with status 400 (Bad Request) if the employe has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/employes")
  public ResponseEntity<EmployeDTO> createEmploye(@Valid @RequestBody EmployeDTO employeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Employe : {}", employeDTO);

    // Check if the idTypeEmploye associated with the employe exists
    if (employeDTO.getTypeEmploye() != null && !typeEmployeService.existsById(employeDTO.getIdTypeEmploye())) {
      bindingResult.addError(new FieldError("EmployeDTO", "typeEmploye.idTypeEmploye", "TypeEmploye with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( employeDTO.getIdEmploye() != null) {
      bindingResult.addError( new FieldError("EmployeDTO","idEmploye","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    EmployeDTO result = employeService.save(employeDTO);
    return ResponseEntity.created( new URI("/api/employes/"+ result.getIdEmploye())).body(result);
  }

  /**
   * PUT  /employes : Updates an existing employe.
   *
   * @param id
   * @param employeDTO the employe to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated employe,
   * or with status 400 (Bad Request) if the employe is not valid,
   * or with status 500 (Internal Server Error) if the employe couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/employes/{id}")
  public ResponseEntity<EmployeDTO> updateEmploye(@PathVariable Integer id, @Valid @RequestBody EmployeDTO employeDTO ,BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update Employe: {}",id);
    // Check if the idTypeEmploye associated with the employe exists
    if (employeDTO.getTypeEmploye() != null && !typeEmployeService.existsById(employeDTO.getIdTypeEmploye())) {
      bindingResult.addError(new FieldError("EmployeDTO", "typeEmploye.idTypeEmploye", "TypeEmploye with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    employeDTO.setIdEmploye(id);
    EmployeDTO result =employeService.update(employeDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /employes/{id} : get the "id" employe.
   *
   * @param id the id of the employe to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of employe, or with status 404 (Not Found)
   */
  @GetMapping("/employes/{id}")
  public ResponseEntity<EmployeDTO> getEmploye(@PathVariable Integer id) {
    log.debug("Request to get Employe: {}",id);
    EmployeDTO dto = employeService.findById(id);
    RestPreconditions.checkFound(dto, "employe.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /employes : get all the employes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of employes in body
   */
  @GetMapping("/employes")
  public List<EmployeDTO> getAllEmployes() {
    log.debug("Request to get all  Employes : {}");
    return employeService.findAll();
  }

  /**
   * DELETE  /employes/{id} : delete the "id" employe.
   *
   * @param id the id of the employe to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/employes/{id}")
  public ResponseEntity<Void> deleteEmploye(@PathVariable Integer id) {
    log.debug("Request to delete Employe: {}",id);
    employeService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/login/{login}")
  public ResponseEntity<EmployeDTO> getEmployeByLogin(@PathVariable String login) {
    log.debug("Request to get Employe: {}",login);
    EmployeDTO dto = employeService.findByLogin(login);
    RestPreconditions.checkFound(dto, "employe.NotFound");
    return ResponseEntity.ok().body(dto);
  }
  @GetMapping("/employes/exists/login/{login}")
  public ResponseEntity<Boolean> checkIfEmployeExistsByLogin(@PathVariable String login) {
    log.debug("Request to check if Employe exists by login: {}", login);
    boolean exists = employeService.existsByLogin(login);
    return ResponseEntity.ok().body(exists);
  }
  @GetMapping("/employes/exists/email/{email}")
  public ResponseEntity<Boolean> checkIfEmployeExistsByEmail(@PathVariable String email) {
    log.debug("Request to check if Employe exists by email: {}", email);
    boolean exists = employeService.existsByEmail(email);
    return ResponseEntity.ok().body(exists);
  }
  @GetMapping("/employes/exists/tel/{tel}")
  public ResponseEntity<Boolean> checkIfEmployeExistsByTel(@PathVariable Integer tel) {
    log.debug("Request to check if Employe exists by Tel: {}", tel);
    boolean exists = employeService.existsByTel(tel);
    return ResponseEntity.ok().body(exists);
  }

  @PostMapping("/employes/signin")
  @ApiOperation(value = "${UtilisateurResource.signin}")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 422, message = "Invalid username/password supplied")
  })
  public ResponseEntity<Map<String, String>> signin(@Valid @RequestBody com.csys.template.domain.Credentials credentials, HttpServletRequest request) {
    log.info("Signing in user: {}", credentials.getUsername());
    Map<String, String> token = employeService.signin(credentials.getUsername(), credentials.getPassword());
    request.setAttribute("authenticatedUsername", credentials.getUsername()); // Stocker le nom d'utilisateur authentifié dans la requête
    return ResponseEntity.ok(token);
  }
}

