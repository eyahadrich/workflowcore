package com.csys.workflow.factory;


import com.csys.workflow.domain.Role;
import com.csys.workflow.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class RoleFactory {
  public static RoleDTO roleToRoleDTO(Role role) {
    RoleDTO roleDTO=new RoleDTO();
    roleDTO.setIdRole(role.getIdRole());
    roleDTO.setDesignation(role.getDesignation());
    //roleDTO.setRoleEquipeList(role.getRoleEquipeList());
    return roleDTO;
  }

  public static Role roleDTOToRole(RoleDTO roleDTO) {
    Role role=new Role();
    role.setIdRole(roleDTO.getIdRole());
    role.setDesignation(roleDTO.getDesignation());
    //role.setRoleEquipeList(roleDTO.getRoleEquipeList());
    return role;
  }

  public static List<RoleDTO> roleToRoleDTOs(List<Role> roles) {
    List<RoleDTO> rolesDTO=new ArrayList<>();
    roles.forEach(x -> {
      rolesDTO.add(roleToRoleDTO(x));
    } );
    return rolesDTO;
  }
}

