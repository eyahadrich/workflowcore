package com.csys.workflow.service;


import com.csys.workflow.domain.RoleEquipe;
import com.csys.workflow.domain.RoleEquipePK;
import com.csys.workflow.dto.RoleEquipeDTO;
import com.csys.workflow.factory.RoleEquipeFactory;
import com.csys.workflow.repository.RoleEquipeRepository;
import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing RoleEquipe.
 */
@Service
@Transactional
public class RoleEquipeService {
  private final Logger log = LoggerFactory.getLogger(RoleEquipeService.class);

  private final RoleEquipeRepository roleequipeRepository;

  public RoleEquipeService(RoleEquipeRepository roleequipeRepository) {
    this.roleequipeRepository=roleequipeRepository;
  }

  /**
   * Save a roleequipeDTO.
   *
   * @param roleequipeDTO
   * @return the persisted entity
   */
  public RoleEquipeDTO save(RoleEquipeDTO roleequipeDTO) {
    log.debug("Request to save RoleEquipe: {}",roleequipeDTO);
    RoleEquipe roleequipe = RoleEquipeFactory.roleequipeDTOToRoleEquipe(roleequipeDTO);
    roleequipe = roleequipeRepository.save(roleequipe);
    RoleEquipeDTO resultDTO = RoleEquipeFactory.roleequipeToRoleEquipeDTO(roleequipe);
    return resultDTO;
  }

  /**
   * Update a roleequipeDTO.
   *
   * @param roleequipeDTO
   * @return the updated entity
   */
  public RoleEquipeDTO update(RoleEquipeDTO roleequipeDTO) {
    log.debug("Request to update RoleEquipe: {}",roleequipeDTO);
    RoleEquipe inBase= roleequipeRepository.findById(new RoleEquipePK(roleequipeDTO.getRoleEquipe(), roleequipeDTO.getIdEmploye(), roleequipeDTO.getIdEquipe())).orElse(null);
    Preconditions.checkArgument(inBase != null, "roleequipe.NotFound");
    RoleEquipe roleequipe = RoleEquipeFactory.roleequipeDTOToRoleEquipe(roleequipeDTO);
    roleequipe = roleequipeRepository.save(roleequipe);
    RoleEquipeDTO resultDTO = RoleEquipeFactory.roleequipeToRoleEquipeDTO(roleequipe);
    return resultDTO;
  }

  /**
   * Get one roleequipeDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public RoleEquipeDTO findById(RoleEquipePK id) {
    log.debug("Request to get RoleEquipe: {}",id);
    RoleEquipe roleequipe= roleequipeRepository.findById(id).orElse(null);
    RoleEquipeDTO dto = RoleEquipeFactory.roleequipeToRoleEquipeDTO(roleequipe);
    return dto;
  }

  /**
   * Get one roleequipe by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public RoleEquipe findRoleEquipe(RoleEquipePK id) {
    log.debug("Request to get RoleEquipe: {}",id);
    RoleEquipe roleequipe= roleequipeRepository.findById(id).orElse(null);
    return roleequipe;
  }

  /**
   * Get all the roleequipes.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public Collection<RoleEquipeDTO> findAll() {
    log.debug("Request to get All RoleEquipes");
    List<RoleEquipe> result= roleequipeRepository.findAll();
    return RoleEquipeFactory.roleequipeToRoleEquipeDTOs(result);
  }

  /**
   * Delete roleequipe by id.
   *
   * @param id the id of the entity
   */
  public void delete(RoleEquipePK id) {
    log.debug("Request to delete RoleEquipe: {}",id);
    roleequipeRepository.deleteById(id);
  }
}

