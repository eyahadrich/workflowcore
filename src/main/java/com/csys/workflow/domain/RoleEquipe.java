/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;


import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */

@Entity
@Table(name = "RoleEquipe")
@NamedQueries({
        @NamedQuery(name = "RoleEquipe.findAll", query = "SELECT r FROM RoleEquipe r")})
public class RoleEquipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleEquipePK roleEquipePK;
    @JoinColumn(name = "id_employe", referencedColumnName = "id_employe", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employe employe;
    @JoinColumn(name = "id_equipe", referencedColumnName = "id_equipe", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipe equipe;
    @JoinColumn(name = "role_equipe", referencedColumnName = "id_role", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Role role;

    public RoleEquipePK getRoleEquipePK() {
        return roleEquipePK;
    }

    public void setRoleEquipePK(RoleEquipePK roleEquipePK) {
        this.roleEquipePK = roleEquipePK;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RoleEquipe() {
    }

    public RoleEquipe(RoleEquipePK roleEquipePK) {
        this.roleEquipePK = roleEquipePK;
    }

    public RoleEquipe(int roleEquipe, int idEmploye, int idEquipe) {
        this.roleEquipePK = new RoleEquipePK(roleEquipe, idEmploye, idEquipe);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleEquipePK != null ? roleEquipePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleEquipe)) {
            return false;
        }
        RoleEquipe other = (RoleEquipe) object;
        if ((this.roleEquipePK == null && other.roleEquipePK != null) || (this.roleEquipePK != null && !this.roleEquipePK.equals(other.roleEquipePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.RoleEquipe[ roleEquipePK=" + roleEquipePK + " ]";
    }
    
}
