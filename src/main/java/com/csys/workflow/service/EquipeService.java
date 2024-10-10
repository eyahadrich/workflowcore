package com.csys.workflow.service;


import com.csys.workflow.domain.Equipe;
import com.csys.workflow.dto.EquipeDTO;
import com.csys.workflow.factory.EquipeFactory;
import com.csys.workflow.repository.EquipeRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Equipe.
 */
@Service
@Transactional
public class EquipeService {

    private final Logger log = LoggerFactory.getLogger(EquipeService.class);

    private final EquipeRepository equipeRepository;


    public EquipeService(EquipeRepository equipeRepository, RoleEquipeService roleEquipeService) {
        this.equipeRepository = equipeRepository;

    }

    /**
     * Save a equipeDTO.
     *
     * @param equipeDTO
     * @return the persisted entity
     */
    public EquipeDTO save(EquipeDTO equipeDTO) {
        log.debug("Request to save Equipe: {}", equipeDTO);
        Equipe equipe = EquipeFactory.equipeDTOToEquipe(equipeDTO);
        equipe = equipeRepository.save(equipe);
        EquipeDTO resultDTO = EquipeFactory.equipeToEquipeDTO(equipe);
        return resultDTO;
    }

    /**
     * Update a equipeDTO.
     *
     * @param equipeDTO
     * @return the updated entity
     */
    public EquipeDTO update(EquipeDTO equipeDTO) {
        log.debug("Request to update Equipe: {}", equipeDTO);
        Equipe inBase = equipeRepository.findById(equipeDTO.getIdEquipe()).orElse(null);
        Preconditions.checkArgument(inBase != null, "equipe.NotFound");
        Equipe equipe = EquipeFactory.equipeDTOToEquipe(equipeDTO);
        equipe = equipeRepository.save(equipe);
        EquipeDTO resultDTO = EquipeFactory.equipeToEquipeDTO(equipe);
        return resultDTO;
    }
    public  boolean existsById(Integer id) {
        return equipeRepository.existsById(id);
    }

    /**
     * Get one equipeDTO by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public EquipeDTO findById(Integer id) {
        log.debug("Request to get Equipe: {}", id);
        Equipe equipe = equipeRepository.findById(id).orElse(null);
        EquipeDTO dto = EquipeFactory.equipeToEquipeDTO(equipe);
        return dto;
    }

    /**
     * Get one equipe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(
            readOnly = true
    )
    public Equipe findEquipe(Integer id) {
        log.debug("Request to get Equipe: {}", id);
        Equipe equipe = equipeRepository.findById(id).orElse(null);
        return equipe;
    }

    /**
     * Get all the equipes.
     *
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public List<EquipeDTO> findAll() {
        log.debug("Request to get All Equipes");
        List<Equipe> result = equipeRepository.findAll();
        return EquipeFactory.equipeToEquipeDTOs(result);
    }

    /**
     * Delete equipe by id.
     *
     * @param id the id of the entity
     */
    public void delete(Integer id) {
        log.debug("Request to delete Equipe: {}", id);
        equipeRepository.deleteById(id);
    }




        /**
         * Get all the equipes that an employee is part of.
         *
         * @param employeeId the id of the employee
         * @return the list of equipe DTOs
         */
        @Transactional(readOnly = true)
        public List<EquipeDTO> findAllEquipesByEmployeeId(Integer employeeId) {
            log.debug("Request to get all Equipes for employee: {}", employeeId);
            List<Equipe> allEquipes = equipeRepository.findAll();
            List<Equipe> filteredEquipes = allEquipes.stream()
                    .filter(equipe -> EquipeFactory.isEmployeeInEquipe(equipe, employeeId))
                    .collect(Collectors.toList());
            return EquipeFactory.equipeToEquipeDTOs(filteredEquipes);
        }


        /**
         * Get all the employee IDs that an employee is part of.
         *
         * @param employeeId the id of the employee
         * @return the list of employee IDs
         */
//        @Transactional(readOnly = true)
//        public List<Integer> findAllEquipesByEmployeeId(Integer employeeId) {
//            log.debug("Request to get all Equipes for employee: {}", employeeId);
//            List<Equipe> allEquipes = equipeRepository.findAll();
//            return allEquipes.stream()
//                    .filter(equipe -> EquipeFactory.isEmployeeInEquipe(equipe, employeeId))
//                    .map(equipe -> equipe.getRoleEquipeList().stream()
//                            .map(roleEquipe -> roleEquipe.getRoleEquipePK().getIdEmploye())
//                            .collect(Collectors.toList()))
//                    .flatMap(List::stream)
//                    .distinct()
//                    .collect(Collectors.toList());
//        }



        /**
         * Transform a list of Equipe entities into a list of employee IDs for each equipe.
         *
         * @param equipes the list of Equipe entities
         * @return the list of employee IDs for each equipe
         */
        public static List<List<Integer>> equipesToEmployeeIdLists(List<Equipe> equipes) {
            return equipes.stream()
                    .map(equipe -> equipe.getRoleEquipeList().stream()
                            .map(roleEquipe -> roleEquipe.getRoleEquipePK().getIdEmploye())
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        }

        /**
         * Get the list of employee IDs for each equipe that an employee is part of.
         *
         * @param employeeId the id of the employee
         * @return the list of employee IDs for each equipe
         */
        @Transactional(readOnly = true)
        public List<List<Integer>> findAllEmployeeIdsByEmployeeId(Integer employeeId) {
            log.debug("Request to get all Equipes for employee: {}", employeeId);
            List<Equipe> allEquipes = equipeRepository.findAll();
            List<Equipe> filteredEquipes = allEquipes.stream()
                    .filter(equipe -> EquipeFactory.isEmployeeInEquipe(equipe, employeeId))
                    .collect(Collectors.toList());
            return EquipeFactory.equipesToEmployeeIdLists(filteredEquipes);
        }
    @Transactional(readOnly = true)
    public List<Integer> findAllEmployeeIdsofEquipeByEmployeeIdAndRole1(Integer employeeId, String role) {
        log.debug("Request to get all Equipes for employee: {} with role: {}", employeeId, role);
        List<Equipe> allEquipes = equipeRepository.findAll();
        List<Equipe> filteredEquipes = new ArrayList<>();
        for (Equipe equipe : allEquipes) {
            if (EquipeFactory.isEmployeeInEquipeWithRole(equipe, employeeId, role)) {
                filteredEquipes.add(equipe);
            }
        }
        return EquipeFactory.equipesToEmployeeIdList(filteredEquipes);
    }
    @Transactional(readOnly = true)
    public Set<Integer> findAllEmployeeIdsOfEquipeByEmployeeIdAndRole(Integer employeeId, String role) {
        log.debug("Request to get all Equipes for employee: {} with role: {}", employeeId, role);
        List<Equipe> allEquipes = equipeRepository.findAll();
        Set<Integer> employeeIds = new HashSet<>();
        for (Equipe equipe : allEquipes) {
            if (EquipeFactory.isEmployeeInEquipeWithRole(equipe, employeeId, role)) {
                employeeIds.addAll(EquipeFactory.equipesToEmployeeIdList(Collections.singletonList(equipe)));
            }
        }
        // Remove the searched employeeId from the set
        employeeIds.remove(employeeId);
        return employeeIds;
    }









}
