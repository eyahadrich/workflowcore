package com.csys.workflow.web.rest;

import com.csys.workflow.dto.EquipeDTO;
import com.csys.workflow.dto.RoleEquipeDTO;
import com.csys.workflow.service.EquipeService;
import com.csys.workflow.service.RoleEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class RoleEquipe {

    private final RoleEquipeService roleEquipeService;
    private final EquipeService equipeService;

    private final Logger log = LoggerFactory.getLogger(RoleEquipeService.class);



    public RoleEquipe(RoleEquipeService roleEquipeService, EquipeService equipeService) {
        this.roleEquipeService = roleEquipeService;
        this.equipeService = equipeService;
    }

    // Existing methods...

    /**
     * POST  /role-equipes : Create a new roleEquipe.
     *
     * @param roleEquipeDTO the roleEquipeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new roleEquipeDTO, or with status 400 (Bad Request) if the roleEquipe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/role-equipes")
    public ResponseEntity<RoleEquipeDTO> createRoleEquipe(@Valid @RequestBody RoleEquipeDTO roleEquipeDTO) throws URISyntaxException {
        log.debug("REST request to save RoleEquipe : {}", roleEquipeDTO);
        RoleEquipeDTO result = roleEquipeService.save(roleEquipeDTO);
        return ResponseEntity.created(new URI("/api/role-equipes/" + result.getRoleEquipe())).body(result);
    }
    @PutMapping("/role-equipes/{idEquipe}/{idEmploye}/{roleEquipe}")
    public ResponseEntity<RoleEquipeDTO> updateRoleEquipe(
            @PathVariable Integer idEquipe,
            @PathVariable Integer idEmploye,
            @PathVariable Integer roleEquipe,
            @Valid @RequestBody RoleEquipeDTO roleEquipeDTO) {

        log.debug("Request to update RoleEquipe with idEquipe: {}, idEmploye: {}, roleEquipe: {}", idEquipe, idEmploye, roleEquipe);

        // Set the path variable values into the DTO
        roleEquipeDTO.setIdEquipe(idEquipe);
        roleEquipeDTO.setIdEmploye(idEmploye);
        roleEquipeDTO.setRoleEquipe(roleEquipe);

        RoleEquipeDTO result = roleEquipeService.update(roleEquipeDTO);
        return ResponseEntity.ok().body(result);
    }

}

