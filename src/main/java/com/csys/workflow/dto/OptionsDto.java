package com.csys.workflow.dto;



import java.lang.Integer;
import java.lang.String;



public class OptionsDto {
    private Integer idOption;

    private String nomOption;

    private Integer idTicket;

    public Integer getIdOption() {
        return idOption;
    }

    public OptionsDto setIdOption(Integer idOption) {
        this.idOption = idOption;
        return this;
    }

    public String getNomOption() {
        return nomOption;
    }

    public OptionsDto setNomOption(String nomOption) {
        this.nomOption = nomOption;
        return this;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public OptionsDto setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
        return this;
    }
}
