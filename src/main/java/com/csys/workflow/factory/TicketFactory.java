package com.csys.workflow.factory;

import com.csys.workflow.domain.Interface;
import com.csys.workflow.domain.Ticket;
import com.csys.workflow.domain.TypeTicket;
import com.csys.workflow.domain.Workflow;
import com.csys.workflow.dto.TicketDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class TicketFactory {
  public static TicketDTO ticketToTicketDTO(Ticket ticket) {
    TicketDTO ticketDTO=new TicketDTO();
    ticketDTO.setIdTicket(ticket.getIdTicket());
    ticketDTO.setOrdreTicket(ticket.getOrdreTicket());
    ticketDTO.setNomTicket(ticket.getNomTicket());
    ticketDTO.setMinLength(ticket.getMinLength());
    ticketDTO.setMaxLength(ticket.getMaxLength());
    ticketDTO.setRegleTicket(ticket.getRegleTicket());;
    ticketDTO.setPlaceholder(ticket.getPlaceholder());

      ticketDTO.setIdTypeTicket(TypeTicketFactory.typeticketToTypeTicketDTO(ticket.getTypeTicket()).getIdTypeTicket());
      ticketDTO.setTypeTicket(TypeTicketFactory.typeticketToTypeTicketDTO(ticket.getTypeTicket()).getTypeTicket());


      ticketDTO.setIdInterface(InterfaceFactory.interToInterfaceDTO(ticket.getInterface1()).getIdInterface());
      ticketDTO.setNomInterface(InterfaceFactory.interToInterfaceDTO(ticket.getInterface1()).getNomInterface());

    return ticketDTO;
  }

  public static Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
    Ticket ticket=new Ticket();
    ticket.setIdTicket(ticketDTO.getIdTicket());
    ticket.setOrdreTicket(ticketDTO.getOrdreTicket());
    ticket.setNomTicket(ticketDTO.getNomTicket());
    ticket.setMinLength(ticketDTO.getMinLength());
    ticket.setMaxLength(ticketDTO.getMaxLength());
    ticket.setRegleTicket(ticketDTO.getRegleTicket());
    ticket.setPlaceholder(ticketDTO.getPlaceholder());


      Interface interf = new Interface();
    Workflow workflow=new Workflow();
    interf.setWorkflow(workflow);
      interf.setIdInterface(ticketDTO.getIdInterface());
      ticket.setInterface1(interf);


      TypeTicket typeTicket = new TypeTicket();
      typeTicket.setIdTypeTicket(ticketDTO.getIdTypeTicket());
      ticket.setTypeTicket(typeTicket);





    return ticket;
  }

  public static List<TicketDTO> ticketToTicketDTOs(List<Ticket> tickets) {
    List<TicketDTO> ticketsDTO=new ArrayList<>();
    tickets.forEach(x -> {
      ticketsDTO.add(ticketToTicketDTO(x));
    } );
    return ticketsDTO;
  }
}

