/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this workflow file, choose Tools | workflows
 * and open the workflow in the editor.
 */
package com.csys.workflow.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Employe")

@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")})
public class Employe implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "id_employe", nullable = false)
    private Integer idEmploye;

    @Size(max = 100)
    @Column(name = "nom_employe", length = 100)
    private String nomEmploye;

    @Size(max = 100)
    @Column(name = "prenom_employe", length = 100)
    private String prenomEmploye;

    @Size(max = 255)
    @Column(name = "email_employe", length = 255)
    private String emailEmploye;

    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Size(max = 255)
    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "tel")
    private Integer tel;
    @Size(max = 255)
    @Column(name = "login", length = 255)
    private String username;
    @Size(max = 255)
    @Column(name = "mdp", length = 255)
    private String password;

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getEmailEmploye() {
        return emailEmploye;
    }

    public void setEmailEmploye(String emailEmploye) {
        this.emailEmploye = emailEmploye;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }

    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    public TypeEmploye getTypeEmploye() {
        return typeEmploye;
    }

    public void setTypeEmploye(TypeEmploye typeEmploye) {
        this.typeEmploye = typeEmploye;
    }

//    public List<Token> getTokens() {
//        return tokens;
//    }
//
//    public void setTokens(List<Token> tokens) {
//        this.tokens = tokens;
//    }
//
//    public Authority getAuthority() {
//        return authority;
//    }
//
//    public void setAuthority(Authority authority) {
//        this.authority = authority;
//    }

    @OneToMany(mappedBy = "employe")
    private List<Validation> validationList;

    @OneToMany(mappedBy = "employe")
    private List<Demande> demandeList;

    @JoinColumn(name = "id_type_employe", referencedColumnName = "id_type_employe")
    @ManyToOne
    private TypeEmploye typeEmploye;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employe" )
    private List<RoleEquipe> roleEquipeList;
//    @OneToMany(mappedBy = "employe")
//    private List<Token> tokens;

    public Employe() {
    }

    public Employe(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmploye != null ? idEmploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.idEmploye == null && other.idEmploye != null) || (this.idEmploye != null && !this.idEmploye.equals(other.idEmploye))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "com.csys.workflow.domain.Employe[ idEmploye=" + idEmploye + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
//private Authority authority;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(authority.name()));
//    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public List<RoleEquipe> getRoleEquipeList() {
        return roleEquipeList;
    }

    public void setRoleEquipeList(List<RoleEquipe> roleEquipeList) {
        this.roleEquipeList = roleEquipeList;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }





}
