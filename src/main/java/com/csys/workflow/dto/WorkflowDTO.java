package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WorkflowDTO {

  private Integer idWorkflow;

  @Size(
      min = 0,
      max = 50
  )
  private String nomWorkflow;

  //private List<EtapeDTO> etapeList;
//  private Integer idInterface1;
//  private String nomInterface1;


  //private InterfaceDTO interface1;

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

   /* public InterfaceDTO getInterface1() {
        return interface1;
    }

    public void setInterface1(InterfaceDTO interface1) {
        this.interface1 = interface1;
    }*/

//  public Integer getIdInterface1() {
//    return idInterface1;
//  }
//
//  public void setIdInterface1(Integer idInterface1) {
//    this.idInterface1 = idInterface1;
//  }
//
//  public String getNomInterface1() {
//    return nomInterface1;
//  }
//
//  public void setNomInterface1(String nomInterface1) {
//    this.nomInterface1 = nomInterface1;
//  }

  // public List<EtapeDTO> getEtapeList() {
     //   return etapeList;
    //}

   // public void setEtapeList(List<EtapeDTO> etapeList) {
      //  this.etapeList = etapeList;
    //}


}

