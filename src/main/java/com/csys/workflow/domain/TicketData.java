/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "TicketData")
@NamedQueries({
    @NamedQuery(name = "TicketData.findAll", query = "SELECT t FROM TicketData t")})
public class TicketData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ticket_data", nullable = false)
    private Integer idTicketData;
    @Size(max = 255)
    @Column(name = "valeur_ticket", length = 255)
    private String valeurTicket;
    @JoinColumn(name = "id_demande", referencedColumnName = "id_demande")
    @ManyToOne
    private Demande demande;
    @JoinColumn(name = "id_ticket", referencedColumnName = "id_ticket")
    @ManyToOne
    private Ticket ticket;

    public TicketData() {
    }

    public TicketData(Integer idTicketData) {
        this.idTicketData = idTicketData;
    }

    public Integer getIdTicketData() {
        return idTicketData;
    }

    public void setIdTicketData(Integer idTicketData) {
        this.idTicketData = idTicketData;
    }

    public String getValeurTicket() {
        return valeurTicket;
    }

    public void setValeurTicket(String valeurTicket) {
        this.valeurTicket = valeurTicket;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicketData != null ? idTicketData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketData)) {
            return false;
        }
        TicketData other = (TicketData) object;
        if ((this.idTicketData == null && other.idTicketData != null) || (this.idTicketData != null && !this.idTicketData.equals(other.idTicketData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.TicketData[ idTicketData=" + idTicketData + " ]";
    }
    
}
