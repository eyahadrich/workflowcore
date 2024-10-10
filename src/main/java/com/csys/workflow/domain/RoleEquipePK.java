/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrateur
 */
@Embeddable
public class RoleEquipePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "role_equipe", nullable = false)
    private int roleEquipe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_employe", nullable = false)
    private int idEmploye;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_equipe", nullable = false)
    private int idEquipe;

    public RoleEquipePK() {
    }

    public RoleEquipePK(int roleEquipe, int idEmploye, int idEquipe) {
        this.roleEquipe = roleEquipe;
        this.idEmploye = idEmploye;
        this.idEquipe = idEquipe;
    }

    public int getRoleEquipe() {
        return roleEquipe;
    }

    public void setRoleEquipe(int roleEquipe) {
        this.roleEquipe = roleEquipe;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleEquipe;
        hash += (int) idEmploye;
        hash += (int) idEquipe;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleEquipePK)) {
            return false;
        }
        RoleEquipePK other = (RoleEquipePK) object;
        if (this.roleEquipe != other.roleEquipe) {
            return false;
        }
        if (this.idEmploye != other.idEmploye) {
            return false;
        }
        if (this.idEquipe != other.idEquipe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.RoleEquipePK[ roleEquipe=" + roleEquipe + ", idEmploye=" + idEmploye + ", idEquipe=" + idEquipe + " ]";
    }
    
}
