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
@Table(name = "Workflow")
@NamedQueries({
    @NamedQuery(name = "Workflow.findAll", query = "SELECT w FROM Workflow w")})
public class Workflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_workflow", nullable = false)
    private Integer idWorkflow;
    @Size(max = 50)
    @Column(name = "nom_workflow", length = 50)
    private String nomWorkflow;
    @OneToMany(mappedBy = "workflow" , cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Etape> etapeList;

//    @JoinColumn(name = "id_interface", referencedColumnName = "id_interface")
//    @ManyToOne
//    private Interface interface1;
    @OneToMany(mappedBy = "workflow")
    private List<Interface> interfaces;

    public Workflow() {
    }

    public Workflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
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

    public List<Etape> getEtapeList() {
        return etapeList;
    }

    public void setEtapeList(List<Etape> etapeList) {
        this.etapeList = etapeList;
    }

//    public Interface getInterface1() {
//        return interface1;
//    }
//
//    public void setInterface1(Interface interface1) {
//        this.interface1 = interface1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkflow != null ? idWorkflow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workflow)) {
            return false;
        }
        Workflow other = (Workflow) object;
        if ((this.idWorkflow == null && other.idWorkflow != null) || (this.idWorkflow != null && !this.idWorkflow.equals(other.idWorkflow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Workflow[ idWorkflow=" + idWorkflow + " ]";
    }


    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
