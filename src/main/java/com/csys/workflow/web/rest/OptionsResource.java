package com.csys.workflow.web.rest;

import com.csys.workflow.dto.MenusDTO;
import com.csys.workflow.dto.OptionsDto;
import com.csys.workflow.service.MenusService;
import com.csys.workflow.service.OptionsService;
import com.csys.workflow.util.RestPreconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * REST controller for managing Options.
 */
@RestController
@RequestMapping("/api")

public class OptionsResource {
    private static final String ENTITY_NAME = "options";

    private final OptionsService optionsService;

    private final Logger log = LoggerFactory.getLogger(OptionsService.class);

    public OptionsResource(OptionsService optionsService) {
        this.optionsService=optionsService;
    }

    /**
     * POST  /options : Create a new options.
     *
     * @param optionsDto
     * @param bindingResult
     * @return the ResponseEntity with status 201 (Created) and with body the new options, or with status 400 (Bad Request) if the options has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws org.springframework.web.bind.MethodArgumentNotValidException
     */
    @PostMapping("/options")
    public ResponseEntity<OptionsDto> createOptions(@Valid @RequestBody OptionsDto optionsDto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save option : {}", optionsDto);
        if ( optionsDto.getIdOption() != null) {
            bindingResult.addError( new FieldError("OptionsDto","idOption","POST method does not accepte "+ENTITY_NAME+" with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        OptionsDto result = optionsService.save(optionsDto);
        return ResponseEntity.created( new URI("/api/options/"+ result.getIdOption())).body(result);
    }

    /**
     * PUT  /options : Updates an existing options.
     *
     * @param id
     * @param optionsDto the menus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated options,
     * or with status 400 (Bad Request) if the options is not valid,
     * or with status 500 (Internal Server Error) if the options couldn't be updated
     * @throws org.springframework.web.bind.MethodArgumentNotValidException
     */
    @PutMapping("/options/{id}")
    public ResponseEntity<OptionsDto> updateOptions(@PathVariable Integer id, @Valid @RequestBody OptionsDto optionsDto) throws MethodArgumentNotValidException {
        log.debug("Request to update Option: {}",id);
        optionsDto.setIdOption(id);
        OptionsDto result =optionsService.update(optionsDto);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET /options/{id} : get the "id" options.
     *
     * @param id the id of the options to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body of options, or with status 404 (Not Found)
     */
    @GetMapping("/options/{id}")
    public ResponseEntity<OptionsDto> getOptions(@PathVariable Integer id) {
        log.debug("Request to get Option: {}",id);
        OptionsDto dto = optionsService.findOne(id);
        RestPreconditions.checkFound(dto, "menus.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    /**
     * GET /options : get all the options.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of options in body
     */
    @GetMapping("/options")
    public Collection<OptionsDto> getAllOptions() {
        log.debug("Request to get all  Options : {}");
        return optionsService.findAll();
    }

    /**
     * DELETE  /options/{id} : delete the "id" menus.
     *
     * @param id the id of the options to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/options/{id}")
    public ResponseEntity<Void> deleteOptions(@PathVariable Integer id) {
        log.debug("Request to delete option: {}",id);
        optionsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
