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
@Table(name = "TypeEmploye")
@NamedQueries({
    @NamedQuery(name = "TypeEmploye.findAll", query = "SELECT t FROM TypeEmploye t")})
public class TypeEmploye implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_type_employe", nullable = false)
    private Integer idTypeEmploye;
    @Size(max = 100)
    @Column(name = "Type_employe", length = 100)
    private String typeemploye;
    @OneToMany(mappedBy = "typeEmploye")
    private List<Employe> employeList;

    public TypeEmploye() {
    }

    public TypeEmploye(Integer idTypeEmploye) {
        this.idTypeEmploye = idTypeEmploye;
    }

    public Integer getIdTypeEmploye() {
        return idTypeEmploye;
    }

    public void setIdTypeEmploye(Integer idTypeEmploye) {
        this.idTypeEmploye = idTypeEmploye;
    }

    public String getTypeemploye() {
        return typeemploye;
    }

    public void setTypeemploye(String typeemploye) {
        this.typeemploye = typeemploye;
    }

    public List<Employe> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employe> employeList) {
        this.employeList = employeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeEmploye != null ? idTypeEmploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeEmploye)) {
            return false;
        }
        TypeEmploye other = (TypeEmploye) object;
        if ((this.idTypeEmploye == null && other.idTypeEmploye != null) || (this.idTypeEmploye != null && !this.idTypeEmploye.equals(other.idTypeEmploye))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.TypeEmploye[ idTypeEmploye=" + idTypeEmploye + " ]";
    }
    
}
