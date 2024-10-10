package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class  TypeEmployeDTO {

  private Integer idTypeEmploye;

  @Size(
      min = 0,
      max = 100
  )
  private String typeemploye;


  public Integer getIdTypeEmploye() {
    return idTypeEmploye;
  }

  public void setIdTypeEmploye(Integer idTypeEmploye) {
    this.idTypeEmploye = idTypeEmploye;
  }

  public String getTypeemploye() {
    return typeemploye;
  }

  public void setTypeemploye(String typeemploye) {
    this.typeemploye = typeemploye;
  }

}

