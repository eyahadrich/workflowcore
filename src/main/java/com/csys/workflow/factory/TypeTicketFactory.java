package com.csys.workflow.factory;


import com.csys.workflow.domain.TypeTicket;
import com.csys.workflow.dto.TypeTicketDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class TypeTicketFactory {

    public static TypeTicketDTO typeticketToTypeTicketDTO(TypeTicket typeticket) {
        TypeTicketDTO typeticketDTO = new TypeTicketDTO();
        typeticketDTO.setIdTypeTicket(typeticket.getIdTypeTicket());
        typeticketDTO.setTypeTicket(typeticket.getTypeTicket());
        return typeticketDTO;
    }

    public static TypeTicket typeticketDTOToTypeTicket(TypeTicketDTO typeticketDTO) {
        TypeTicket typeticket = new TypeTicket();
        typeticket.setIdTypeTicket(typeticketDTO.getIdTypeTicket());
        typeticket.setTypeTicket(typeticketDTO.getTypeTicket());
        return typeticket;
    }

    public static List<TypeTicketDTO> typeticketToTypeTicketDTOs(List<TypeTicket> typetickets) {
        List<TypeTicketDTO> typeticketsDTO = new ArrayList<>();
        typetickets.forEach(x -> {
            typeticketsDTO.add(typeticketToTypeTicketDTO(x));
        });
        return typeticketsDTO;
    }
}
