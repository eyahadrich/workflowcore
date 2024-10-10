package com.csys.workflow.factory;


import com.csys.workflow.domain.*;
import com.csys.workflow.dto.TicketDataDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class TicketDataFactory {


    public static TicketDataDTO ticketdataToTicketDataDTO(TicketData ticketdata) {
    TicketDataDTO ticketdataDTO=new TicketDataDTO();
    ticketdataDTO.setIdTicketData(ticketdata.getIdTicketData());
    ticketdataDTO.setValeurTicket(ticketdata.getValeurTicket());
    //ticketdataDTO.setDemande(DemandeFactory.demandeToDemandeDTO(ticketdata.getDemande()));
    //ticketdataDTO.setTicket(TicketFactory.ticketToTicketDTO(ticketdata.getTicket()));
    if (ticketdata.getDemande() != null) {
      ticketdataDTO.setIdDemande(DemandeFactory.demandeToDemandeDTO(ticketdata.getDemande()).getIdDemande());
    }
    // Add idTicket if it exists
    if (ticketdata.getTicket() != null) {
      ticketdataDTO.setIdTicket(TicketFactory.ticketToTicketDTO(ticketdata.getTicket()).getIdTicket());
        ticketdataDTO.setNomTicket(TicketFactory.ticketToTicketDTO(ticketdata.getTicket()).getNomTicket());

    }
    return ticketdataDTO;
  }

  public static TicketData ticketdataDTOToTicketData(TicketDataDTO ticketdataDTO) {
    TicketData ticketdata=new TicketData();
    ticketdata.setIdTicketData(ticketdataDTO.getIdTicketData());
    ticketdata.setValeurTicket(ticketdataDTO.getValeurTicket());
    // Initialize Ticket object and set idTicket if provided
    Ticket ticket = new Ticket();
    TypeTicket typeTicket = new TypeTicket();
    ticket.setTypeTicket(typeTicket);
    Interface inter = new Interface();
      Workflow workflow=new Workflow();
      inter.setWorkflow(workflow);
    ticket.setInterface1(inter);
    ticket.setIdTicket(ticketdataDTO.getIdTicket());
    ticketdata.setTicket(ticket);
    // Add idDemande to the Demande object
    Demande demande = new Demande();
    Interface inter2=new Interface();
      Workflow workflow2=new Workflow();
      inter2.setWorkflow(workflow2);
    demande.setInterface1(inter2);
    Employe employe = new Employe();
    TypeEmploye typeEmploye = new TypeEmploye();
    employe.setTypeEmploye(typeEmploye);
    demande.setEmploye(employe);
    demande.setIdDemande(ticketdataDTO.getIdDemande());
    ticketdata.setDemande(demande);




    return ticketdata;
  }

  public static List<TicketDataDTO> ticketdataToTicketDataDTOs(List<TicketData> ticketdatas) {
    List<TicketDataDTO> ticketdatasDTO=new ArrayList<>();
    ticketdatas.forEach(x -> {
      ticketdatasDTO.add(ticketdataToTicketDataDTO(x));
    } );
    return ticketdatasDTO;
  }
}

