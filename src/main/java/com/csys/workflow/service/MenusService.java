package com.csys.workflow.service;


import com.csys.workflow.domain.Menus;
import com.csys.workflow.dto.MenusDTO;
import com.csys.workflow.factory.MenusFactory;
import com.csys.workflow.repository.MenusRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Menus.
 */
@Service
@Transactional
public class MenusService {
  private final Logger log = LoggerFactory.getLogger(MenusService.class);

  private final MenusRepository menusRepository;

  public MenusService(MenusRepository menusRepository) {
    this.menusRepository=menusRepository;
  }

  /**
   * Save a menusDTO.
   *
   * @param menusDTO
   * @return the persisted entity
   */
  public MenusDTO save(MenusDTO menusDTO) {
    log.debug("Request to save Menus: {}",menusDTO);
    Menus menus = MenusFactory.menusDTOToMenus(menusDTO);
    menus = menusRepository.save(menus);
    MenusDTO resultDTO = MenusFactory.menusToMenusDTO(menus);
    return resultDTO;
  }

  /**
   * Update a menusDTO.
   *
   * @param menusDTO
   * @return the updated entity
   */
  public MenusDTO update(MenusDTO menusDTO) {
    log.debug("Request to update Menus: {}",menusDTO);
    Menus inBase= menusRepository.findById(menusDTO.getCodMnP()).orElse(null);
    Preconditions.checkArgument(inBase != null, "menus.NotFound");
    Menus menus = MenusFactory.menusDTOToMenus(menusDTO);
    menus = menusRepository.save(menus);
    MenusDTO resultDTO = MenusFactory.menusToMenusDTO(menus);
    return resultDTO;
  }

  /**
   * Get one menusDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public MenusDTO findOne(Integer id) {
    log.debug("Request to get Menus: {}",id);
    Menus menus= menusRepository.findById(id).orElse(null);
    MenusDTO dto = MenusFactory.menusToMenusDTO(menus);
    return dto;
  }

  /**
   * Get one menus by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Menus findMenus(Integer id) {
    log.debug("Request to get Menus: {}",id);
    Menus menus= menusRepository.findById(id).orElse(null);
    return menus;
  }

  /**
   * Get all the menuss.
   *
   * @return the the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public Collection<MenusDTO> findAll() {
    log.debug("Request to get All Menuss");
    Collection<Menus> result= menusRepository.findAll();
    return MenusFactory.menusToMenusDTOs(result);
  }

  /**
   * Delete menus by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Menus: {}",id);
    menusRepository.deleteById(id);
  }
}

