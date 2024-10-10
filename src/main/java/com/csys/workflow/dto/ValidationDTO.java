package com.csys.workflow.dto;


import java.lang.Boolean;
import java.lang.Integer;
import javax.validation.constraints.NotNull;


public class ValidationDTO {

  private Integer idValidation;
  private Boolean reponse;
  private Integer idDemande;
  private Integer IdEmploye;
  private Integer idEtape;
  private String nomEtape;

  public Integer getIdValidation() {
    return idValidation;
  }

  public ValidationDTO setIdValidation(Integer idValidation) {
    this.idValidation = idValidation;
    return this;
  }

  public Boolean getReponse() {
    return reponse;
  }

  public ValidationDTO setReponse(Boolean reponse) {
    this.reponse = reponse;
    return this;
  }

  public Integer getIdDemande() {
    return idDemande;
  }

  public ValidationDTO setIdDemande(Integer idDemande) {
    this.idDemande = idDemande;
    return this;
  }

  public Integer getIdEmploye() {
    return IdEmploye;
  }

  public ValidationDTO setIdEmploye(Integer idEmploye) {
    IdEmploye = idEmploye;
    return this;
  }

  public Integer getIdEtape() {
    return idEtape;
  }

  public ValidationDTO setIdEtape(Integer idEtape) {
    this.idEtape = idEtape;
    return this;
  }

  public String getNomEtape() {
    return nomEtape;
  }

  public ValidationDTO setNomEtape(String nomEtape) {
    this.nomEtape = nomEtape;
    return this;
  }
}

