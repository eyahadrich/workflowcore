package com.csys.workflow.web.rest;

import com.csys.workflow.dto.TypeTicketDTO;
import com.csys.workflow.service.TypeTicketService;
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
 * REST controller for managing TypeTicket.
 */
@RestController
@RequestMapping("/api")
public class TypeTicketResource {
  private static final String ENTITY_NAME = "typeticket";

  private final TypeTicketService typeticketService;

  private final Logger log = LoggerFactory.getLogger(TypeTicketService.class);

  public TypeTicketResource(TypeTicketService typeticketService) {
    this.typeticketService=typeticketService;
  }

  /**
   * POST  /typetickets : Create a new typeticket.
   *
   * @param typeticketDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new typeticket, or with status 400 (Bad Request) if the typeticket has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/typetickets")
  public ResponseEntity<TypeTicketDTO> createTypeTicket(@Valid @RequestBody TypeTicketDTO typeticketDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save TypeTicket : {}", typeticketDTO);
//    // Check if the idTypeTicket already exists
//    if (typeticketService.existsById(typeticketDTO.getIdTypeTicket())) {
//      bindingResult.addError(new FieldError("TypeTicketDTO", "idTypeTicket", "TypeTicket with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    if ( typeticketDTO.getIdTypeTicket() != null) {
      bindingResult.addError( new FieldError("TypeTicketDTO","idTypeTicket","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeTicketDTO result = typeticketService.save(typeticketDTO);
    return ResponseEntity.created( new URI("/api/typetickets/"+ result.getIdTypeTicket())).body(result);
  }

  /**
   * PUT  /typetickets : Updates an existing typeticket.
   *
   * @param id
   * @param typeticketDTO the typeticket to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated typeticket,
   * or with status 400 (Bad Request) if the typeticket is not valid,
   * or with status 500 (Internal Server Error) if the typeticket couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/typetickets/{id}")
  public ResponseEntity<TypeTicketDTO> updateTypeTicket(@PathVariable Integer id, @Valid @RequestBody TypeTicketDTO typeticketDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update TypeTicket: {}",id);
    typeticketDTO.setIdTypeTicket(id);
    TypeTicketDTO result =typeticketService.update(typeticketDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /typetickets/{id} : get the "id" typeticket.
   *
   * @param id the id of the typeticket to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of typeticket, or with status 404 (Not Found)
   */
  @GetMapping("/typetickets/{id}")
  public ResponseEntity<TypeTicketDTO> getTypeTicket(@PathVariable Integer id) {
    log.debug("Request to get TypeTicket: {}",id);
    TypeTicketDTO dto = typeticketService.findById(id);
    RestPreconditions.checkFound(dto, "typeticket.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /typetickets : get all the typetickets.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of typetickets in body
   */
  @GetMapping("/typetickets")
  public List<TypeTicketDTO> getAllTypeTickets() {
    log.debug("Request to get all  TypeTickets : {}");
    return typeticketService.findAll();
  }

  /**
   * DELETE  /typetickets/{id} : delete the "id" typeticket.
   *
   * @param id the id of the typeticket to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/typetickets/{id}")
  public ResponseEntity<Void> deleteTypeTicket(@PathVariable Integer id) {
    log.debug("Request to delete TypeTicket: {}",id);
    typeticketService.delete(id);
    return ResponseEntity.ok().build();
  }
}

