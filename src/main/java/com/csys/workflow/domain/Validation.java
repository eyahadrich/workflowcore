/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Validation")
@NamedQueries({
    @NamedQuery(name = "Validation.findAll", query = "SELECT v FROM Validation v")})
public class Validation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validation", nullable = false)
    private Integer idValidation;
    @Column(name = "reponse")
    private Boolean reponse;
    @JoinColumn(name = "id_demande", referencedColumnName = "id_demande")
    @ManyToOne
    private Demande demande;
    @JoinColumn(name = "id_employe", referencedColumnName = "id_employe")
    @ManyToOne
    private Employe employe;
    @JoinColumn(name = "id_etape", referencedColumnName = "id_etape")
    @ManyToOne
    private Etape etape;

    public Validation() {
    }

    public Validation(Integer idValidation) {
        this.idValidation = idValidation;
    }

    public Integer getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Integer idValidation) {
        this.idValidation = idValidation;
    }

    public Boolean getReponse() {
        return reponse;
    }

    public void setReponse(Boolean reponse) {
        this.reponse = reponse;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidation != null ? idValidation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Validation)) {
            return false;
        }
        Validation other = (Validation) object;
        if ((this.idValidation == null && other.idValidation != null) || (this.idValidation != null && !this.idValidation.equals(other.idValidation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Validation[ idValidation=" + idValidation + " ]";
    }
    
}
