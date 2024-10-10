package com.csys.workflow.service;


import com.csys.workflow.domain.Role;
import com.csys.workflow.dto.RoleDTO;
import com.csys.workflow.factory.RoleFactory;
import com.csys.workflow.repository.RoleRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Role.
 */
@Service
@Transactional
public class RoleService {
  private final Logger log = LoggerFactory.getLogger(RoleService.class);

  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository=roleRepository;
  }

  /**
   * Save a roleDTO.
   *
   * @param roleDTO
   * @return the persisted entity
   */
  public RoleDTO save(RoleDTO roleDTO) {
    log.debug("Request to save Role: {}",roleDTO);
    Role role = RoleFactory.roleDTOToRole(roleDTO);
    role = roleRepository.save(role);
    RoleDTO resultDTO = RoleFactory.roleToRoleDTO(role);
    return resultDTO;
  }
  public  boolean existsById(Integer id) {
    return roleRepository.existsById(id);
  }

  /**
   * Update a roleDTO.
   *
   * @param roleDTO
   * @return the updated entity
   */
  public RoleDTO update(RoleDTO roleDTO) {
    log.debug("Request to update Role: {}",roleDTO);
    Role inBase= roleRepository.findById(roleDTO.getIdRole()).orElse(null);
    Preconditions.checkArgument(inBase != null, "role.NotFound");
    Role role = RoleFactory.roleDTOToRole(roleDTO);
    role = roleRepository.save(role);
    RoleDTO resultDTO = RoleFactory.roleToRoleDTO(role);
    return resultDTO;
  }

  /**
   * Get one roleDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public RoleDTO findById(Integer id) {
    log.debug("Request to get Role: {}",id);
    Role role= roleRepository.findById(id).orElse(null);
    RoleDTO dto = RoleFactory.roleToRoleDTO(role);
    return dto;
  }

  /**
   * Get one role by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Role findRole(Integer id) {
    log.debug("Request to get Role: {}",id);
    Role role= roleRepository.findById(id).orElse(null);
    return role;
  }

  /**
   * Get all the roles.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public List<RoleDTO> findAll() {
    log.debug("Request to get All Roles");
    List<Role> result= roleRepository.findAll();
    return RoleFactory.roleToRoleDTOs(result);
  }

  /**
   * Delete role by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Role: {}",id);
    roleRepository.deleteById(id);
  }
}

