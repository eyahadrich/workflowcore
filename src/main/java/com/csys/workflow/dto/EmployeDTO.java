package com.csys.workflow.dto;



import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EmployeDTO {


    private Integer idEmploye;

    @Size(
            min = 0,
            max = 100
    )
    private String nomEmploye;

    @Size(
            min = 0,
            max = 100
    )
    private String prenomEmploye;

    @Size(
            min = 0,
            max = 255
    )
    private String emailEmploye;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Size(
            min = 0,
            max = 255
    )
    private String adresse;

    private Integer tel;

    @Size(
            min = 0,
            max = 255
    )
    private String username;

    @Size(
            min = 0,
            max = 255
    )
    private String password;

    //private List<ValidationDTO> validationList;

   // private List<DemandeDTO> demandeList;
    private Integer idTypeEmploye;

  private String typeEmploye;

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public EmployeDTO setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
        return this;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public String getEmailEmploye() {
        return emailEmploye;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public EmployeDTO setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public Integer getTel() {
        return tel;
    }

    public EmployeDTO setTel(Integer tel) {
        this.tel = tel;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public EmployeDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getIdTypeEmploye() {
        return idTypeEmploye;
    }

    public EmployeDTO setIdTypeEmploye(Integer idTypeEmploye) {
        this.idTypeEmploye = idTypeEmploye;
        return this;
    }

    public String getTypeEmploye() {
        return typeEmploye;
    }

    public EmployeDTO setTypeEmploye(String typeEmploye) {
        this.typeEmploye = typeEmploye;
        return this;
    }

    public EmployeDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public EmployeDTO setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public EmployeDTO setEmailEmploye(String emailEmploye) {
        this.emailEmploye = emailEmploye;
        return this;
    }

    public EmployeDTO setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
        return this;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public EmployeDTO setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
        return this;
    }
/*public List<ValidationDTO> getValidationList() {
        return validationList;
    }
    public void setValidationList(List<ValidationDTO> validationList) {
        this.validationList = validationList;
    }

    public List<DemandeDTO> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<DemandeDTO> demandeList) {
        this.demandeList = demandeList;
    }*/

//    public TypeEmployeDTO getTypeEmploye() {
//        return typeEmploye;
//    }
//
//    public void setTypeEmploye(TypeEmployeDTO typeEmploye) {
//        this.typeEmploye = typeEmploye;
//    }

//    public List<RoleEquipeDTO> getRoleEquipeList() {
//        return roleEquipeList;
//    }

//    public void setRoleEquipeList(List<RoleEquipeDTO> roleEquipeList) {
//        this.roleEquipeList = roleEquipeList;
//    }

}
