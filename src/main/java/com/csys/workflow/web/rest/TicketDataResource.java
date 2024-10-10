package com.csys.workflow.web.rest;

import com.csys.workflow.dto.TicketDataDTO;
import com.csys.workflow.service.DemandeService;
import com.csys.workflow.service.TicketDataService;
import com.csys.workflow.service.TicketService;
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
import org.springframework.http.HttpStatus;
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
 * REST controller for managing TicketData.
 */
@RestController
@RequestMapping("/api")
public class TicketDataResource {
  private static final String ENTITY_NAME = "ticketdata";

  private final TicketDataService ticketdataService;
  private final TicketService ticketService;
  private final DemandeService demandeService;

  private final Logger log = LoggerFactory.getLogger(TicketDataService.class);

  public TicketDataResource(TicketDataService ticketdataService, TicketService ticketService, DemandeService demandeService) {
    this.ticketdataService=ticketdataService;
      this.ticketService = ticketService;
      this.demandeService = demandeService;
  }

  /**
   * POST  /ticketdatas : Create a new ticketdata.
   *
   * @param ticketdataDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new ticketdata, or with status 400 (Bad Request) if the ticketdata has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/ticketdatas")
  public ResponseEntity<TicketDataDTO> createTicketData(@Valid @RequestBody TicketDataDTO ticketdataDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save TicketData : {}", ticketdataDTO);
    // Check if idTicketData already exists
//    if (ticketdataService.existsById(ticketdataDTO.getIdTicketData())) {
//      bindingResult.addError(new FieldError("TicketDataDTO", "idTicketData", "TicketData with this ID already exists."));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }

    // Check if idTicket exists
    if (!ticketService.existsById(ticketdataDTO.getIdTicket())) {
      bindingResult.addError(new FieldError("TicketDataDTO", "idTicket", "Ticket with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    // Check if idDemande exists
    if (!demandeService.existsById(ticketdataDTO.getIdDemande())) {
      bindingResult.addError(new FieldError("TicketDataDTO", "idDemande", "Demande with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( ticketdataDTO.getIdTicketData() != null) {
      bindingResult.addError( new FieldError("TicketDataDTO","idTicketData","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TicketDataDTO result = ticketdataService.save(ticketdataDTO);
    return ResponseEntity.created( new URI("/api/ticketdatas/"+ result.getIdTicketData())).body(result);
  }

  /**
   * PUT  /ticketdatas : Updates an existing ticketdata.
   *
   * @param id
   * @param ticketdataDTO the ticketdata to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated ticketdata,
   * or with status 400 (Bad Request) if the ticketdata is not valid,
   * or with status 500 (Internal Server Error) if the ticketdata couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/ticketdatas/{id}")
  public ResponseEntity<TicketDataDTO> updateTicketData(@PathVariable Integer id, @Valid @RequestBody TicketDataDTO ticketdataDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update TicketData: {}",id);
    // Check if idTicket exists
    if (!ticketService.existsById(ticketdataDTO.getIdTicket())) {
      bindingResult.addError(new FieldError("TicketDataDTO", "idTicket", "Ticket with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

    // Check if idDemande exists
    if ( !demandeService.existsById(ticketdataDTO.getIdDemande())) {
      bindingResult.addError(new FieldError("TicketDataDTO", "idDemande", "Demande with this ID does not exist."));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    ticketdataDTO.setIdTicketData(id);
    TicketDataDTO result =ticketdataService.update(ticketdataDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /ticketdatas/{id} : get the "id" ticketdata.
   *
   * @param id the id of the ticketdata to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of ticketdata, or with status 404 (Not Found)
   */
  @GetMapping("/ticketdatas/{id}")
  public ResponseEntity<TicketDataDTO> getTicketData(@PathVariable Integer id) {
    log.debug("Request to get TicketData: {}",id);
    TicketDataDTO dto = ticketdataService.findById(id);
    RestPreconditions.checkFound(dto, "ticketdata.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /ticketdatas : get all the ticketdatas.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of ticketdatas in body
   */
  @GetMapping("/ticketdatas")
  public List<TicketDataDTO> getAllTicketDatas() {
    log.debug("Request to get all  TicketDatas : {}");
    return ticketdataService.findAll();
  }

  /**
   * DELETE  /ticketdatas/{id} : delete the "id" ticketdata.
   *
   * @param id the id of the ticketdata to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/ticketdatas/{id}")
  public ResponseEntity<Void> deleteTicketData(@PathVariable Integer id) {
    log.debug("Request to delete TicketData: {}",id);
    ticketdataService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/ticketdatas/demand/{idDemande}")
  public ResponseEntity<List<TicketDataDTO>> findTicketsDataByDemandId(@PathVariable Integer idDemande) {
    List<TicketDataDTO> ticketDataList = ticketdataService.findTicketsDataByDemandId(idDemande);
    if (ticketDataList.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(ticketDataList, HttpStatus.OK);
    }
  }
}

