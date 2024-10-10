/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "TypeTicket")
@NamedQueries({
    @NamedQuery(name = "TypeTicket.findAll", query = "SELECT t FROM TypeTicket t")})
public class TypeTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "id_type_ticket", nullable = false)
    private Integer idTypeTicket;
    @Size(max = 50)
    @Column(name = "type_ticket", length = 50)
    private String typeTicket;
    @OneToMany(mappedBy = "typeTicket")
    private List<Ticket> ticketList;

    public TypeTicket() {
    }

    public TypeTicket(Integer idTypeTicket) {
        this.idTypeTicket = idTypeTicket;
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

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeTicket != null ? idTypeTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeTicket)) {
            return false;
        }
        TypeTicket other = (TypeTicket) object;
        if ((this.idTypeTicket == null && other.idTypeTicket != null) || (this.idTypeTicket != null && !this.idTypeTicket.equals(other.idTypeTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.TypeTicket[ idTypeTicket=" + idTypeTicket + " ]";
    }
    
}
