package com.csys.workflow.service;


import com.csys.workflow.domain.Options;
import com.csys.workflow.dto.OptionsDto;
import com.csys.workflow.factory.OptionsFactory;
import com.csys.workflow.repository.OptionsRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional

public class OptionsService {
    private final Logger log = LoggerFactory.getLogger(OptionsService.class);

    private final OptionsRepository optionsRepository;

    public OptionsService(OptionsRepository optionsRepository) {
        this.optionsRepository=optionsRepository;
    }

    /**
     * Save a menusDTO.
     *
     * @param optionsDto
     * @return the persisted entity
     */
    public OptionsDto save(OptionsDto optionsDto) {
        log.debug("Request to save Options: {}",optionsDto);
        Options options = OptionsFactory.optionsDTOToOptions(optionsDto);
        options = optionsRepository.save(options);
        OptionsDto resultDTO = OptionsFactory.optionsToOptionsDto(options);
        return resultDTO;
    }

    /**
     * Update a menusDTO.
     *
     * @param optionsDto
     * @return the updated entity
     */
    public OptionsDto update(OptionsDto optionsDto) {
        log.debug("Request to update Menus: {}",optionsDto);
        Options inBase= optionsRepository.findById(optionsDto.getIdOption()).orElse(null);
        Preconditions.checkArgument(inBase != null, "options.NotFound");
        Options options = OptionsFactory.optionsDTOToOptions(optionsDto);
        options = optionsRepository.save(options);
        OptionsDto resultDTO = OptionsFactory.optionsToOptionsDto(options);
        return resultDTO;
    }

    /**
     * Get one optionsDto by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public OptionsDto findOne(Integer id) {
        log.debug("Request to get Options: {}",id);
        Options options= optionsRepository.findById(id).orElse(null);
        OptionsDto dto = OptionsFactory.optionsToOptionsDto(options);
        return dto;
    }



    /**
     * Get all the options.
     *
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public Collection<OptionsDto> findAll() {
        log.debug("Request to get All Options");
        Collection<Options> result= optionsRepository.findAll();
        return OptionsFactory.optionsToOptionsDTOs(result);
    }

    /**
     * Delete menus by id.
     *
     * @param id the id of the entity
     */
    public void delete(Integer id) {
        log.debug("Request to delete Option: {}",id);
        optionsRepository.deleteById(id);
    }
}
