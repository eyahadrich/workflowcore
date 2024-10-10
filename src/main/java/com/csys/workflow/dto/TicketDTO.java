package com.csys.workflow.dto;


import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TicketDTO {


    private Integer idTicket;

    private Integer ordreTicket;

    @Size(
            min = 0,
            max = 50
    )
    private String nomTicket;

    private Integer minLength;

    private Integer maxLength;

    @Size(
            min = 0,
            max = 255
    )
    private String regleTicket;
    @Size(
            min = 0,
            max = 50
    )
    private String placeholder;

    //private List<TicketDataDTO> ticketDataList;


private  Integer idInterface;
private String nomInterface;
    private Integer idTypeTicket;
private String typeTicket;

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getOrdreTicket() {
        return ordreTicket;
    }

    public void setOrdreTicket(Integer ordreTicket) {
        this.ordreTicket = ordreTicket;
    }

    public String getNomTicket() {
        return nomTicket;
    }

    public void setNomTicket(String nomTicket) {
        this.nomTicket = nomTicket;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getRegleTicket() {
        return regleTicket;
    }

    public void setRegleTicket(String regleTicket) {
        this.regleTicket = regleTicket;
    }

    public String getNomInterface() {
        return nomInterface;
    }

    public void setNomInterface(String nomInterface) {
        this.nomInterface = nomInterface;
    }

    public Integer getIdInterface() {
        return idInterface;
    }

    public void setIdInterface(Integer idInterface) {
        this.idInterface = idInterface;
    }

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

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

//    public List<TicketDataDTO> getTicketDataList() {
//        return ticketDataList;
//    }
//
//    public void setTicketDataList(List<TicketDataDTO> ticketDataList) {
//        this.ticketDataList = ticketDataList;
//    }



}
