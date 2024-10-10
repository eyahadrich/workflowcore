/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import liquibase.pro.packaged.W;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Interface")
@NamedQueries({
    @NamedQuery(name = "Interface.findAll", query = "SELECT i FROM Interface i")})
public class Interface implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_interface", nullable = false)
    private Integer idInterface;
    @Size(max = 50)
    @Column(name = "nom_interface", length = 50)
    private String nomInterface;
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "date_modification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @OneToMany(mappedBy = "interface1" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketList;
//    @OneToMany(mappedBy = "interface1")
//    private List<Workflow> workflowList;
    @JoinColumn(name = "id_workflow", referencedColumnName = "id_workflow")
    @ManyToOne
    private Workflow workflow;
    @OneToMany(mappedBy = "interface1")
    private List<Demande> demandeList;

    public Interface() {
    }

    public Interface(Integer idInterface) {
        this.idInterface = idInterface;

    }

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

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }



    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInterface != null ? idInterface.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interface)) {
            return false;
        }
        Interface other = (Interface) object;
        if ((this.idInterface == null && other.idInterface != null) || (this.idInterface != null && !this.idInterface.equals(other.idInterface))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Interface[ idInterface=" + idInterface + " ]";
    }

//    public List<Workflow> getWorkflowList() {
//        return workflowList;
//    }
//
//    public void setWorkflowList(List<Workflow> workflowList) {
//        this.workflowList = workflowList;
//    }

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

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
}
