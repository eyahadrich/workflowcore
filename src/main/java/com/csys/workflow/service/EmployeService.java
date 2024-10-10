package com.csys.workflow.service;


import com.csys.workflow.config.MailSender;
import com.csys.workflow.domain.Employe;
import com.csys.workflow.dto.EmployeDTO;
import com.csys.workflow.exception.CustomException;
import com.csys.workflow.factory.EmployeFactory;
import com.csys.workflow.repository.EmployeRepository;
import com.csys.workflow.security.JwtTokenProvider;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Employe.
 */
@Service
@Transactional
public class EmployeService {
  private final Logger log = LoggerFactory.getLogger(EmployeService.class);

  private final EmployeRepository employeRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  private final MailSender mailSender;
  private final AuthenticationManager authenticationManager;

  public EmployeService(EmployeRepository employeRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
    MailSender mailSender,
                        AuthenticationManager authenticationManager) {
    this.employeRepository = employeRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
    this.mailSender = mailSender;
    this.authenticationManager = authenticationManager;
  }

  public Map<String, String> signin(String username, String password) {
    try {
      Employe employer = employeRepository.findByUsername(username).orElse(null);

      if (employer == null) {
        throw new CustomException("Employe non trouvé", HttpStatus.UNPROCESSABLE_ENTITY);
      }

      log.info("employer------------------------{}", employer);
      log.info("employer.getPassword()------------------------{}", employer.getPassword());
      log.info("password------------------------{}", password);
      if (!passwordEncoder.matches(password, passwordEncoder.encode(employer.getPassword()))) {
        throw new CustomException("Mot de passe incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
      }
      String token = jwtTokenProvider.createToken(username);
      Map<String, String> response = new HashMap<>();
      response.put("token", token);
      return response;
    } catch (CustomException e) {
      throw e; // Re-lancer l'exception personnalisée
    } catch (Exception e) {
      throw new CustomException("Une erreur s'est produite lors de l'authentification", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private boolean validateCredentials(String username, String password) {
    Employe employer = employeRepository.findByUsername(username).orElse(null);

    if (employer != null && passwordEncoder.matches(password, employer.getPassword())) {
      return true;
    } else {
      return false;
    }
  }

  public String signup(Employe employer) {
    if (!employeRepository.existsByUsername(employer.getUsername())) {
      employer.setPassword(passwordEncoder.encode(employer.getPassword()));
      employeRepository.save(employer);
      if (employer.getUsername().equals("admin")) {
        // Créez un exemple d'employer admin avec un nom d'employer et un mot de passe par défaut
        Employe adminUser = new Employe();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin")); // Mot de passe par défaut

        // Enregistrez l'employer admin dans la base de données
        employeRepository.save(adminUser);
      }
      return jwtTokenProvider.createToken(employer.getUsername());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }


  public Employe search(String username) {
    Employe employer = employeRepository.findByUsername(username).orElse(null);
    if (employer == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return employer;
  }


  public String refresh(String username) {
    return jwtTokenProvider.createToken(username);
  }
  
  
  
  
  
  
  /**
   * Save a employeDTO.
   *
   * @param employeDTO
   * @return the persisted entity
   */
  public EmployeDTO save(EmployeDTO employeDTO) {
    log.debug("Request to save Employe: {}",employeDTO);
    Employe employe = EmployeFactory.employeDTOToEmploye(employeDTO);
    employe = employeRepository.save(employe);
    EmployeDTO resultDTO = EmployeFactory.employeToEmployeDTO(employe);

    String subject = "Nouveau Employé Ajouté";
    String body = String.format("Bienvenue %s %s!\n\nVoici vos informations de connexion:\nNom d'utilisateur: %s\nMot de passe: %s\n",
            employe.getPrenomEmploye(), employe.getNomEmploye(), employe.getPrenomEmploye(), employeDTO.getPassword());
    try {
      mailSender.sendMail("ehadrich914@gmail.com", employe.getEmailEmploye(), subject, body);
    } catch (Exception e) {
      log.error("Failed to send email: {}", e.getMessage());
      throw new RuntimeException("Email sending failed");
    }
    return resultDTO;
  }

  /**
   * Update a employeDTO.
   *
   * @param employeDTO
   * @return the updated entity
   */
  public EmployeDTO update(EmployeDTO employeDTO) {
    log.debug("Request to update Employe: {}",employeDTO);
    Employe inBase= employeRepository.findById(employeDTO.getIdEmploye()).orElse(null);
    Preconditions.checkArgument(inBase != null, "employe.NotFound");
    Employe employe = EmployeFactory.employeDTOToEmploye(employeDTO);
    employe = employeRepository.save(employe);
    EmployeDTO resultDTO = EmployeFactory.employeToEmployeDTO(employe);
    return resultDTO;
  }

  /**
   * Get one employeDTO by id.
   *
   * @param id the id of the entity
   * @return the entity DTO
   */
  @Transactional(
      readOnly = true
  )
  public EmployeDTO findById(Integer id) {
    log.debug("Request to get Employe: {}",id);
    Employe employe= employeRepository.findById(id).orElse(null);
    EmployeDTO employeDTO = EmployeFactory.employeToEmployeDTO(employe);
    return employeDTO;
  }

  /**
   * Get one employe by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(
      readOnly = true
  )
  public Employe findEmploye(Integer id) {
    log.debug("Request to get Employe: {}",id);
    Employe employe= employeRepository.findById(id).orElse(null);
    return employe;
  }
  public boolean existsById(Integer id) {
    return employeRepository.existsById(id);
  }

  /**
   * Get all the employes.
   *
   * @return the list of entities
   */
  @Transactional(
      readOnly = true
  )
  public List<EmployeDTO> findAll() {
    log.debug("Request to get All Employes");
    List<Employe> result= employeRepository.findAll();
    return EmployeFactory.employeToEmployeDTOs(result);
  }

  /**
   * Delete employe by id.
   *
   * @param id the id of the entity
   */
  public void delete(Integer id) {
    log.debug("Request to delete Employe: {}",id);
    employeRepository.deleteById(id);
  }
  @Transactional(readOnly = true)
  public EmployeDTO findByLogin(String username) {
    log.debug("Request to get Employe by login: {}", username);
    Optional<Employe> employeOptional = employeRepository.findByUsername(username);
    return employeOptional.map(EmployeFactory::employeToEmployeDTO).orElse(null);
  }
  @Transactional(readOnly = true)
  public boolean existsByLogin(String username) {
    log.debug("Request to check if Employe exists by login: {}", username);
    Optional<Employe> employe = employeRepository.findByUsername(username);
    return employe.isPresent();
  }
  @Transactional(readOnly = true)
  public EmployeDTO findByEmail(String email) {
    log.debug("Request to get Employe by email: {}", email);
    Employe employe = employeRepository.findByEmailEmploye(email);
    return EmployeFactory.employeToEmployeDTO(employe);
  }
  @Transactional(readOnly = true)
  public boolean existsByEmail(String email) {
    log.debug("Request to check if Employe exists by email: {}", email);
    Employe employe = employeRepository.findByEmailEmploye(email);
    return employe != null;
  }
  @Transactional(readOnly = true)
  public boolean existsByTel(Integer tel) {
    log.debug("Request to check if Employe exists by login: {}", tel);
    Employe employe = employeRepository.findByTel(tel);
    return employe != null;
  }

  @Transactional(readOnly = true)
  public  Optional<Employe> findByUsername(String username) {
    log.debug("Request to get Employe by login: {}", username);
    Optional<Employe> employeOptional = employeRepository.findByUsername(username);
    return employeOptional;
  }
}

