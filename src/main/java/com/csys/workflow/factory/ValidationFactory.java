package com.csys.workflow.factory;

import com.csys.workflow.domain.*;
import com.csys.workflow.dto.ValidationDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class ValidationFactory {
  public static ValidationDTO validationToValidationDTO(Validation validation) {
    ValidationDTO validationDTO=new ValidationDTO();
    validationDTO.setIdValidation(validation.getIdValidation());
    validationDTO.setReponse(validation.getReponse());
    // Set idDemande if it exists
    if (validation.getDemande() != null) {
      validationDTO.setIdDemande(DemandeFactory.demandeToDemandeDTO(validation.getDemande()).getIdDemande());
    }
    // Set idEmploye if it exists
    if (validation.getEmploye() != null) {
      validationDTO.setIdEmploye(EmployeFactory.employeToEmployeDTO(validation.getEmploye()).getIdEmploye());
    }
    // Set idEtape if it exists
    if (validation.getEtape() != null) {
      validationDTO.setIdEtape(EtapeFactory.etapeToEtapeDTO(validation.getEtape()).getIdEtape());
      validationDTO.setNomEtape(EtapeFactory.etapeToEtapeDTO(validation.getEtape()).getNomEtape());
    }
    return validationDTO;
  }

  public static Validation validationDTOToValidation(ValidationDTO validationDTO) {
    Validation validation=new Validation();
    validation.setIdValidation(validationDTO.getIdValidation());
    validation.setReponse(validationDTO.getReponse());

    // Set Demande with idDemande
    Demande demande = new Demande();
    Employe employe = new Employe();
    Interface inter = new Interface();
      Workflow workflow2=new Workflow();
      inter.setWorkflow(workflow2);
    demande.setInterface1(inter);
    TypeEmploye typeEmploye = new TypeEmploye();
    employe.setTypeEmploye(typeEmploye);
    demande.setEmploye(employe);
    demande.setIdDemande(validationDTO.getIdDemande());
    validation.setDemande(demande);
    // Set Employe with idEmploye
   Employe employe1 = new Employe();
   TypeEmploye typeEmploye1=new TypeEmploye();
   employe1.setTypeEmploye(typeEmploye1);
   employe1.setIdEmploye(validationDTO.getIdEmploye());
   validation.setEmploye(employe1);
    // Set Etape with idEtape
    Etape etape = new Etape();
      Interface inter2=new Interface();
      Workflow workflow=new Workflow();
      inter2.setWorkflow(workflow);
    etape.setWorkflow(workflow);
    etape.setIdEtape(validationDTO.getIdEtape());
    validation.setEtape(etape);
    return validation;
  }

  public static List<ValidationDTO> validationToValidationDTOs(List<Validation> validations) {
    List<ValidationDTO> validationsDTO=new ArrayList<>();
    validations.forEach(x -> {
      validationsDTO.add(validationToValidationDTO(x));
    } );
    return validationsDTO;
  }
}

