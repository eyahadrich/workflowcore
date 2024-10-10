package com.csys.workflow.factory;



import com.csys.workflow.domain.*;
import com.csys.workflow.dto.OptionsDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OptionsFactory {
    public static OptionsDto optionsToOptionsDto(Options options) {
        OptionsDto optionsDto=new OptionsDto();
        optionsDto.setIdOption(options.getIdOption());
        optionsDto.setNomOption(options.getNomOption());
        optionsDto.setIdTicket(TicketFactory.ticketToTicketDTO(options.getTicket()).getIdTicket());


        return optionsDto;
    }

    public static Options optionsDTOToOptions(OptionsDto optionsDto) {
        Options options=new Options();
        options.setNomOption(optionsDto.getNomOption());
        if (optionsDto.getIdTicket() != null) {
            Ticket ticket = new Ticket();
            Interface interf = new Interface();
            Workflow workflow=new Workflow();
            interf.setWorkflow(workflow);
            ticket.setInterface1(interf);
            TypeTicket typeTicket=new TypeTicket();
            ticket.setTypeTicket(typeTicket);
            ticket.setIdTicket(optionsDto.getIdTicket());
            options.setTicket(ticket);
        }

        return options;
    }

    public static List<OptionsDto> optionsToOptionsDTOs(Collection<Options> options) {
        List<OptionsDto> optionsDtos=new ArrayList<>();
        options.forEach(x -> {
            optionsDtos.add(optionsToOptionsDto(x));
        } );
        return optionsDtos;
    }
}
