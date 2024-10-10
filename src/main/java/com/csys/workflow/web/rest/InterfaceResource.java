package com.csys.workflow.web.rest;

import com.csys.workflow.domain.Employe;
import com.csys.workflow.dto.InterfaceDTO;
import com.csys.workflow.service.InterfaceService;
import com.csys.workflow.util.RestPreconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class InterfaceResource {
    private static final String ENTITY_NAME = "interface";

    private final InterfaceService interfaceService;

    private final Logger log = LoggerFactory.getLogger(InterfaceResource.class);

    public InterfaceResource(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
    }

    @PostMapping("/interfaces")
    public ResponseEntity<InterfaceDTO> createInterface(@Valid @RequestBody InterfaceDTO interfaceDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save Interface : {}", interfaceDTO);
//        if (interfaceDTO.getIdInterface() != null && interfaceService.existsById(interfaceDTO.getIdInterface())) {
//            bindingResult.addError(new FieldError("InterfaceDTO", "id", "Interface with this ID already exists."));
//            throw new MethodArgumentNotValidException(null, bindingResult);
//        }

        if (interfaceDTO.getIdInterface() != null) {
            bindingResult.addError(new FieldError("InterfaceDTO", "idInterface", "POST method does not accept " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        InterfaceDTO result = interfaceService.save(interfaceDTO);
        return ResponseEntity.created(new URI("/api/interfaces/" + result.getIdInterface())).body(result);
    }

    @PutMapping("/interfaces/{id}")
    public ResponseEntity<InterfaceDTO> updateInterface(@PathVariable Integer id, @Valid @RequestBody InterfaceDTO interfaceDTO) throws MethodArgumentNotValidException {
        log.debug("Request to update Interface: {}", id);
        interfaceDTO.setIdInterface(id);
        InterfaceDTO result = interfaceService.update(interfaceDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/interfaces/{id}")
    public ResponseEntity<InterfaceDTO> getInterface(@PathVariable Integer id) {
        log.debug("Request to get Interface: {}", id);
        InterfaceDTO dto = interfaceService.findById(id);
        RestPreconditions.checkFound(dto, "interface.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/interfaces")
    public List<InterfaceDTO> getAllInterfaces() {
        log.debug("Request to get all Interfaces");
        return interfaceService.findAll();
    }



    @DeleteMapping("/interfaces/{id}")
    public ResponseEntity<?> deleteInterface(@PathVariable Integer id) {
        try {
            log.debug("Request to delete Interface: {}", id);
            interfaceService.delete(id);
            return ResponseEntity.ok().body("Interface deleted successfully.");
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = "Deletion cannot be performed due to a constraint violation. There are associated records in  table TicketData.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "An error occurred while deleting the interface: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/employes/exists/nomInterface/{nomInterface}")
    public ResponseEntity<Boolean> checkIfEmployeExistsByNomInterface(@PathVariable String nomInterface) {
        log.debug("Request to check if Interface exists by nom: {}", nomInterface);
        boolean exists = interfaceService.existsByNomInterface(nomInterface);
        return ResponseEntity.ok().body(exists);
    }
}
