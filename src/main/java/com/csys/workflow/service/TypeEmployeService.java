package com.csys.workflow.service;

import com.csys.workflow.domain.TypeEmploye;
import com.csys.workflow.dto.TypeEmployeDTO;
import com.csys.workflow.factory.TypeEmployeFactory;
import com.csys.workflow.repository.TypeEmployeRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeEmployeService {
  private final Logger log = LoggerFactory.getLogger(TypeEmployeService.class);

  private final TypeEmployeRepository typeemployeRepository;

  public TypeEmployeService(TypeEmployeRepository typeemployeRepository) {
    this.typeemployeRepository=typeemployeRepository;
  }

  /**
   * Save a typeemployeDTO.
   *
   * @param typeemployeDTO
   * @return the persisted entity
   */
  public TypeEmployeDTO save(TypeEmployeDTO typeemployeDTO) {
    log.debug("Request to save TypeEmploye: {}",typeemployeDTO);
    TypeEmploye typeemploye = TypeEmployeFactory.typeemployeDTOToTypeEmploye(typeemployeDTO);
    typeemploye = typeemployeRepository.save(typeemploye);
    TypeEmployeDTO resultDTO = TypeEmployeFactory.typeemployeToTypeEmployeDTO(typeemploye);
    return resultDTO;
  }
  public  boolean existsById(Integer id) {
    return typeemployeRepository.existsById(id);
  }

  /**
   * Update a typeemployeDTO.
   *
   * @param typeemployeDTO
   * @return the updated entity
   */
  public TypeEmployeDTO update(TypeEmployeDTO typeemployeDTO) {
    log.debug("Request to update TypeEmploye: {}",typeemployeDTO);
    TypeEmploye inBase= typeemployeRepository.findById(typeemployeDTO.getIdTypeEmploye()).orElse(null);
    Preconditions.checkArgument(inBase != null, "typeemploye.NotFound");
    TypeEmploye typeemploye = TypeEmployeFactory.typeemployeDTOToTypeEmploye(typeemployeDTO);
    typeemploye = typeemployeRepository.save(typeemploye);
    TypeEmployeDTO resultDTO = TypeEmployeFactory.typeemployeToTypeEmployeDTO(typeemploye);
    return resultDTO;
  }

  /**
   * Get one typeemployeDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
          readOnly = true
  )
  public TypeEmployeDTO findById(Integer id) {
    log.debug("Request to get TypeEmploye: {}",id);
    TypeEmploye typeemploye= typeemployeRepository.findById(id).orElse(null);
    TypeEmployeDTO dto = TypeEmployeFactory.typeemployeToTypeEmployeDTO(typeemploye);
    return dto;
  }

  /**
   * Get one typeemploye by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
          readOnly = true
  )
  public TypeEmploye findTypeEmploye(Integer id) {
    log.debug("Request to get TypeEmploye: {}",id);
    TypeEmploye typeemploye= typeemployeRepository.findById(id).orElse(null);
    return typeemploye;
  }

  /**
   * Get all the typeemployes.
   *
   * @return the the list of entities
   */
  @Transactional(
          readOnly = true
  )
  public List<TypeEmployeDTO> findAll() {
    log.debug("Request to get All TypeEmployes");
    List<TypeEmploye> result= typeemployeRepository.findAll();
    return TypeEmployeFactory.typeemployeToTypeEmployeDTOs(result);
  }

  /**
   * Delete typeemploye by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete TypeEmploye: {}",id);
    typeemployeRepository.deleteById(id);
  }
}