package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDTO {


    private Integer idRole;

    @Size(
            min = 0,
            max = 255
    )
    private String designation;

   // private List<RoleEquipeDTO> roleEquipeList;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

   /* public List getRoleEquipeList() {
        return roleEquipeList;
    }

    public void setRoleEquipeList(List roleEquipeList) {
        this.roleEquipeList = roleEquipeList;
    }*/
}
