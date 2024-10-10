/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;




import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */

@Entity
@Table(name = "Equipe")
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e")})
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)

    @Column(name = "id_equipe", nullable = false)
    private Integer idEquipe;
    @Size(max = 50)
    @Column(name = "nom_equipe", length = 50)
    private String nomEquipe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipe")
    private List<RoleEquipe> roleEquipeList;

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<RoleEquipe> getRoleEquipeList() {
        return roleEquipeList;
    }

    public void setRoleEquipeList(List<RoleEquipe> roleEquipeList) {
        this.roleEquipeList = roleEquipeList;
    }

    public Equipe() {
    }

    public Equipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipe != null ? idEquipe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.idEquipe == null && other.idEquipe != null) || (this.idEquipe != null && !this.idEquipe.equals(other.idEquipe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflow.domain.Equipe[ idEquipe=" + idEquipe + " ]";
    }
    
}
