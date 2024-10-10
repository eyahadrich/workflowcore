package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeTicketDTO {
 // @NotNull
  private Integer idTypeTicket;

  @Size(
      min = 0,
      max = 50
  )
  private String typeTicket;

  private List<TicketDTO> ticketList;

 public Integer getIdTypeTicket() {
    return idTypeTicket;
}

 public void setIdTypeTicket(Integer idTypeTicket) {
    this.idTypeTicket = idTypeTicket;
 }

  public String getTypeTicket() {
    return typeTicket;
  }

  public void setTypeTicket(String typeTicket) {
    this.typeTicket = typeTicket;
  }

//    public List<TicketDTO> getTicketList() {
//        return ticketList;
//    }
//
//    public void setTicketList(List<TicketDTO> ticketList) {
//        this.ticketList = ticketList;
//    }
//

}

