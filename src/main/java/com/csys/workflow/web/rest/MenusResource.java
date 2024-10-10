package com.csys.workflow.web.rest;

import com.csys.workflow.dto.MenusDTO;
import com.csys.workflow.service.MenusService;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.validation.Valid;

import com.csys.workflow.util.RestPreconditions;
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
 * REST controller for managing Menus.
 */
@RestController
@RequestMapping("/api")
public class MenusResource {
  private static final String ENTITY_NAME = "menus";

  private final MenusService menusService;

  private final Logger log = LoggerFactory.getLogger(MenusService.class);

  public MenusResource(MenusService menusService) {
    this.menusService=menusService;
  }

  /**
   * POST  /menuss : Create a new menus.
   *
   * @param menusDTO
   * @param bindingResult
   * @return the ResponseEntity with status 201 (Created) and with body the new menus, or with status 400 (Bad Request) if the menus has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PostMapping("/menuss")
  public ResponseEntity<MenusDTO> createMenus(@Valid @RequestBody MenusDTO menusDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Menus : {}", menusDTO);
    if ( menusDTO.getCodMnP() != null) {
      bindingResult.addError( new FieldError("MenusDTO","codMnP","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    MenusDTO result = menusService.save(menusDTO);
    return ResponseEntity.created( new URI("/api/menuss/"+ result.getCodMnP())).body(result);
  }

  /**
   * PUT  /menuss : Updates an existing menus.
   *
   * @param id
   * @param menusDTO the menus to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated menus,
   * or with status 400 (Bad Request) if the menus is not valid,
   * or with status 500 (Internal Server Error) if the menus couldn't be updated
   * @throws org.springframework.web.bind.MethodArgumentNotValidException
   */
  @PutMapping("/menuss/{id}")
  public ResponseEntity<MenusDTO> updateMenus(@PathVariable Integer id, @Valid @RequestBody MenusDTO menusDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update Menus: {}",id);
    menusDTO.setCodMnP(id);
    MenusDTO result =menusService.update(menusDTO);
    return ResponseEntity.ok().body(result);
  }

  /**
   * GET /menuss/{id} : get the "id" menus.
   *
   * @param id the id of the menus to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body of menus, or with status 404 (Not Found)
   */
  @GetMapping("/menuss/{id}")
  public ResponseEntity<MenusDTO> getMenus(@PathVariable Integer id) {
    log.debug("Request to get Menus: {}",id);
    MenusDTO dto = menusService.findOne(id);
    RestPreconditions.checkFound(dto, "menus.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  /**
   * GET /menuss : get all the menuss.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of menuss in body
   */
  @GetMapping("/menuss")
  public Collection<MenusDTO> getAllMenuss() {
    log.debug("Request to get all  Menuss : {}");
    return menusService.findAll();
  }

  /**
   * DELETE  /menuss/{id} : delete the "id" menus.
   *
   * @param id the id of the menus to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/menuss/{id}")
  public ResponseEntity<Void> deleteMenus(@PathVariable Integer id) {
    log.debug("Request to delete Menus: {}",id);
    menusService.delete(id);
    return ResponseEntity.ok().build();
  }
}

