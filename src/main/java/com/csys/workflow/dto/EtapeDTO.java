package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EtapeDTO {

  private Integer idEtape;

  @Size(
      min = 0,
      max = 255
  )
  private String roleEtape;

  @Size(
      min = 0,
      max = 50
  )
  private String nomEtape;

  private Integer ordre;

 // private List<ValidationDTO> validationList;

  private Integer idWorkflow;


  private String nomWorkflow;

  public Integer getIdEtape() {
    return idEtape;
  }

  public void setIdEtape(Integer idEtape) {
    this.idEtape = idEtape;
  }

  public String getRoleEtape() {
    return roleEtape;
  }

  public void setRoleEtape(String roleEtape) {
    this.roleEtape = roleEtape;
  }

  public String getNomEtape() {
    return nomEtape;
  }

  public void setNomEtape(String nomEtape) {
    this.nomEtape = nomEtape;
  }

  public Integer getOrdre() {
    return ordre;
  }

  public void setOrdre(Integer ordre) {
    this.ordre = ordre;
  }

  public Integer getIdWorkflow() {
    return idWorkflow;
  }

  public void setIdWorkflow(Integer idWorkflow) {
    this.idWorkflow = idWorkflow;
  }

  public String getNomWorkflow() {
    return nomWorkflow;
  }

  public void setNomWorkflow(String nomWorkflow) {
    this.nomWorkflow = nomWorkflow;
  }

   /* public List<ValidationDTO> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<ValidationDTO> validationList) {
        this.validationList = validationList;
    }*/





}

