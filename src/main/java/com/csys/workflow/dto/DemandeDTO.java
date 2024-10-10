package com.csys.workflow.dto;

import java.lang.Integer;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class DemandeDTO {
 // @NotNull
  private Integer idDemande;
    private String status;

  private Date dateCreationDemande;

  //private List<TicketDataDTO> ticketDataList;

  //private List<ValidationDTO> validationList;

  private Integer idEmploye;
  private  String nomEmplye;
  private  String prenomEmploye;
  private Integer idInterface;
    private Integer idWorkflow;
  private String nomInterface;
  public Integer getIdDemande() {
    return idDemande;
  }

  public void setIdDemande(Integer idDemande) {
    this.idDemande = idDemande;
  }

  public Date getDateCreationDemande() {
    return dateCreationDemande;
  }

  public void setDateCreationDemande(Date dateCreationDemande) {
    this.dateCreationDemande = dateCreationDemande;
  }

  public Integer getIdEmploye() {
    return idEmploye;
  }

  public void setIdEmploye(Integer idEmploye) {
    this.idEmploye = idEmploye;
  }

  public Integer getIdInterface() {
    return idInterface;
  }

  public void setIdInterface(Integer idInterface) {
    this.idInterface = idInterface;
  }

  public String getNomEmplye() {
    return nomEmplye;
  }

  public void setNomEmplye(String nomEmplye) {
    this.nomEmplye = nomEmplye;
  }

  public String getNomInterface() {
    return nomInterface;
  }

  public void setNomInterface(String nomInterface) {
    this.nomInterface = nomInterface;
  }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    /*public List<TicketDataDTO> getTicketDataList() {
        return ticketDataList;
    }

    public void setTicketDataList(List<TicketDataDTO> ticketDataList) {
        this.ticketDataList = ticketDataList;
    }

    public List<ValidationDTO> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<ValidationDTO> validationList) {
        this.validationList = validationList;
    }*/



}

