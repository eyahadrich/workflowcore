package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InterfaceDTO {


    private Integer idInterface;

    @Size(
            min = 0,
            max = 50
    )
    private String nomInterface;
    private Date dateCreation;
    private Date dateModification;
    private Integer idWorkflow;
    private String nomWorkflow;
    /* List<TicketDTO> ticketList;

    private List<WorkflowDTO> workflowList;

    private List<DemandeDTO> demandeList;*/

    public Integer getIdInterface() {
        return idInterface;
    }

    public void setIdInterface(Integer idInterface) {
        this.idInterface = idInterface;
    }

    public String getNomInterface() {
        return nomInterface;
    }

    public void setNomInterface(String nomInterface) {
        this.nomInterface = nomInterface;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
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
/*
    public List<TicketDTO> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketDTO> ticketList) {
        this.ticketList = ticketList;
    }

    public List<WorkflowDTO> getWorkflowList() {
        return workflowList;
    }

    public void setWorkflowList(List<WorkflowDTO> workflowList) {
        this.workflowList = workflowList;
    }

    public List<DemandeDTO> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<DemandeDTO> demandeList) {
        this.demandeList = demandeList;
    }
*/
}
