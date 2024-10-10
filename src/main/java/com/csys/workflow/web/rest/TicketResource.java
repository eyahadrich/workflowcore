package com.csys.workflow.web.rest;

import com.csys.workflow.dto.TicketDTO;
import com.csys.workflow.service.InterfaceService;
import com.csys.workflow.service.TicketService;
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
 * REST controller for managing Ticket.
 */
@RestController
@RequestMapping("/api")
public class TicketResource {
  private static final String ENTITY_NAME = "ticket";

  private final TicketService ticketService;
  private final InterfaceService interfaceService;
  private final TypeTicketService typeTicketService;

  private final Logger log = LoggerFactory.getLogger(TicketService.class);

  public TicketResource(TicketService ticketService, InterfaceService interfaceService, TypeTicketService typeTicketService) {
    this.ticketService=ticketService;
      this.interfaceService = interfaceService;
      this.typeTicketService = typeTicketService;
  }

  /**
   * POST  /tickets : Create a new ticket.
   *
   * @param ticketDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new ticket, or with status 400 (Bad Request) if the ticket has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/tickets")
  public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Ticket : {}", ticketDTO);
    // Check if the ticket with the given ID already exists
//    if (ticketService.existsById(ticketDTO.getIdTicket())) {
//      bindingResult.addError(new FieldError("TicketDTO", "idTicket", "Ticket with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
    // Check if idInterface exists
    if (!interfaceService.existsById(ticketDTO.getIdInterface())) {
      bindingResult.addError(new FieldError("TicketDTO", "idInterface", "Interface with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    // Check if idTypeTicket exists
    if (!typeTicketService.existsById(ticketDTO.getIdTypeTicket())) {
      bindingResult.addError(new FieldError("TicketDTO", "idTypeTicket", "TypeTicket with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( ticketDTO.getIdTicket() != null) {
      bindingResult.addError( new FieldError("TicketDTO","idTicket","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TicketDTO result = ticketService.save(ticketDTO);
    return ResponseEntity.created( new URI("/api/tickets/"+ result.getIdTicket())).body(result);
  }

  /**
   * PUT  /tickets : Updates an existing ticket.
   *
   * @param id
   * @param ticketDTO the ticket to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated ticket,
   * or with status 400 (Bad Request) if the ticket is not valid,
   * or with status 500 (Internal Server Error) if the ticket couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/tickets/{id}")
  public ResponseEntity<TicketDTO> updateTicket(@PathVariable Integer id, @Valid @RequestBody TicketDTO ticketDTO ,BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update Ticket: {}",id);
    // Check if idInterface exists
    if (!interfaceService.existsById(ticketDTO.getIdInterface())) {
      bindingResult.addError(new FieldError("TicketDTO", "idInterface", "Interface with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    // Check if idTypeTicket exists
    if (!typeTicketService.existsById(ticketDTO.getIdTypeTicket())) {
      bindingResult.addError(new FieldError("TicketDTO", "idTypeTicket", "TypeTicket with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    ticketDTO.setIdTicket(id);
    TicketDTO result =ticketService.update(ticketDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /tickets/{id} : get the "id" ticket.
   *
   * @param id the id of the ticket to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of ticket, or with status 404 (Not Found)
   */
  @GetMapping("/tickets/{id}")
  public ResponseEntity<TicketDTO> getTicket(@PathVariable Integer id) {
    log.debug("Request to get Ticket: {}",id);
    TicketDTO dto = ticketService.findById(id);
    RestPreconditions.checkFound(dto, "ticket.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /tickets : get all the tickets.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of tickets in body
   */
  @GetMapping("/tickets")
  public List<TicketDTO> getAllTickets() {
    log.debug("Request to get all  Tickets : {}");
    return ticketService.findAll();
  }
  @GetMapping("/tickets/inter/{id}")
  public List<TicketDTO> getTicketsByInterface(@PathVariable Integer id) {
    log.debug("Request to get all  Tickets by interface id : {}",id);
    return ticketService.findTicketsByInterfaceId(id);
  }

  /**
   * DELETE  /tickets/{id} : delete the "id" ticket.
   *
   * @param id the id of the ticket to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/tickets/{id}")
  public ResponseEntity<Void> deleteTicket(@PathVariable Integer id) {
    log.debug("Request to delete Ticket: {}",id);
    ticketService.delete(id);
    return ResponseEntity.ok().build();
  }
}

