package com.csys.workflow.web.rest;

import com.csys.workflow.dto.RoleDTO;
import com.csys.workflow.service.RoleService;
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
 * REST controller for managing Role.
 */
@RestController
@RequestMapping("/api")
public class RoleResource {
  private static final String ENTITY_NAME = "role";

  private final RoleService roleService;

  private final Logger log = LoggerFactory.getLogger(RoleService.class);

  public RoleResource(RoleService roleService) {
    this.roleService=roleService;
  }

  /**
   * POST  /roles : Create a new role.
   *
   * @param roleDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new role, or with status 400 (Bad Request) if the role has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/roles")
  public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Role : {}", roleDTO);
//    if (roleDTO.getIdRole() != null && roleService.existsById(roleDTO.getIdRole())) {
//      bindingResult.addError(new FieldError("RoleDTO", "id", "Role with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }

    if ( roleDTO.getIdRole() != null) {
      bindingResult.addError( new FieldError("RoleDTO","idRole","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    RoleDTO result = roleService.save(roleDTO);
    return ResponseEntity.created( new URI("/api/roles/"+ result.getIdRole())).body(result);
  }

  /**
   * PUT  /roles : Updates an existing role.
   *
   * @param id
   * @param roleDTO the role to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated role,
   * or with status 400 (Bad Request) if the role is not valid,
   * or with status 500 (Internal Server Error) if the role couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/roles/{id}")
  public ResponseEntity<RoleDTO> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleDTO roleDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update Role: {}",id);
    roleDTO.setIdRole(id);
    RoleDTO result =roleService.update(roleDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /roles/{id} : get the "id" role.
   *
   * @param id the id of the role to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of role, or with status 404 (Not Found)
   */
  @GetMapping("/roles/{id}")
  public ResponseEntity<RoleDTO> getRole(@PathVariable Integer id) {
    log.debug("Request to get Role: {}",id);
    RoleDTO dto = roleService.findById(id);
    RestPreconditions.checkFound(dto, "role.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /roles : get all the roles.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of roles in body
   */
  @GetMapping("/roles")
  public List<RoleDTO> getAllRoles() {
    log.debug("Request to get all  Roles : {}");
    return roleService.findAll();
  }

  /**
   * DELETE  /roles/{id} : delete the "id" role.
   *
   * @param id the id of the role to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/roles/{id}")
  public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
    log.debug("Request to delete Role: {}",id);
    roleService.delete(id);
    return ResponseEntity.ok().build();
  }
}

