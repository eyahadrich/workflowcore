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
@Table(name = "Etape")
@NamedQueries({
    @NamedQuery(name = "Etape.findAll", query = "SELECT e FROM Etape e")})
public class Etape implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etape", nullable = false)
    private Integer idEtape;
    @Size(max = 255)
    @Column(name = "role_etape", length = 255)
    private String roleEtape;
    @Size(max = 50)
    @Column(name = "nom_etape", length = 50)
    private String nomEtape;
    @Column(name = "ordre")
    private Integer ordre;
    @OneToMany(mappedBy = "etape")
    private List<Validation> validationList;
    @JoinColumn(name = "id_workflow", referencedColumnName = "id_workflow")
    @ManyToOne
    private Workflow workflow;

    public Etape() {
    }

    public Etape(Integer idEtape) {
        this.idEtape = idEtape;
    }

    public Integer getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(Integer idEtape) {
        this.idEtape = idEtape;
    }

    public String getRoleEtape() {
        return roleEtape;
    }

    public void setRoleEtape(String roleEtape) {
        this.roleEtape = roleEtape;
    }

    public String getNomEtape() {
        return nomEtape;
    }

    public void setNomEtape(String nomEtape) {
        this.nomEtape = nomEtape;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtape != null ? idEtape.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etape)) {
            return false;
        }
        Etape other = (Etape) object;
        if ((this.idEtape == null && other.idEtape != null) || (this.idEtape != null && !this.idEtape.equals(other.idEtape))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Etape[ idEtape=" + idEtape + " ]";
    }
    
}
