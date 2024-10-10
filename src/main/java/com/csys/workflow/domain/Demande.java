/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Demande")
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id_demande", nullable = false)
    private Integer idDemande;
    @Size(max = 100)
    @Column(name = "status", length = 100)
    private String status;
    @Column(name = "date_creation_demande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationDemande;
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketData> ticketDataList;
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validationList;
    @JoinColumn(name = "id_employe", referencedColumnName = "id_employe")
    @ManyToOne
    private Employe employe;
    @JoinColumn(name = "id_interface", referencedColumnName = "id_interface")
    @ManyToOne
    private Interface interface1;

    public Demande() {
    }

    public Demande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Date getDateCreationDemande() {
        return dateCreationDemande;
    }

    public void setDateCreationDemande(Date dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
    }

    public List<TicketData> getTicketDataList() {
        return ticketDataList;
    }

    public void setTicketDataList(List<TicketData> ticketDataList) {
        this.ticketDataList = ticketDataList;
    }

    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Interface getInterface1() {
        return interface1;
    }

    public void setInterface1(Interface interface1) {
        this.interface1 = interface1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Demande[ idDemande=" + idDemande + " ]";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
