package com.csys.workflow.factory;


import com.csys.workflow.domain.*;
import com.csys.workflow.dto.RoleEquipeDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.List;

public class RoleEquipeFactory {
    
    public static RoleEquipeDTO roleequipeToRoleEquipeDTO(RoleEquipe roleequipe) {
        RoleEquipeDTO roleequipeDTO = new RoleEquipeDTO();
        roleequipeDTO.setIdEmploye(roleequipe.getRoleEquipePK().getIdEmploye());
        roleequipeDTO.setIdEquipe(roleequipe.getRoleEquipePK().getIdEquipe());
        roleequipeDTO.setRoleEquipe(roleequipe.getRoleEquipePK().getRoleEquipe());
        roleequipeDTO.setEmploye(EmployeFactory.employeToEmployeDTO(roleequipe.getEmploye()));
        roleequipeDTO.setRole(RoleFactory.roleToRoleDTO(roleequipe.getRole()));
        roleequipeDTO.setNomEmploye(roleequipeDTO.getEmploye().getNomEmploye());
        roleequipeDTO.setDesignation(roleequipeDTO.getRole().getDesignation());
        return roleequipeDTO;
    }
    
    public static RoleEquipe roleequipeDTOToRoleEquipe(RoleEquipeDTO roleequipeDTO) {
        RoleEquipe roleequipe = new RoleEquipe();
        RoleEquipePK roleEquipePK = new RoleEquipePK();
        roleEquipePK.setIdEmploye(roleequipeDTO.getIdEmploye());
        roleEquipePK.setIdEquipe(roleequipeDTO.getIdEquipe());
        roleEquipePK.setRoleEquipe(roleequipeDTO.getRoleEquipe());
        roleequipe.setRoleEquipePK(roleEquipePK);
        Employe employe = new Employe();
        TypeEmploye typeEmploye=new TypeEmploye();
        employe.setTypeEmploye(typeEmploye);
        employe.setIdEmploye(roleequipeDTO.getIdEmploye());
        roleequipe.setEmploye(employe);
        Role role=new Role();
        role.setIdRole(roleequipeDTO.getRoleEquipe());
        roleequipe.setRole(role);
        return roleequipe;

    }

    public static List<RoleEquipeDTO> roleequipeToRoleEquipeDTOs(List<RoleEquipe> roleequipes) {
        List<RoleEquipeDTO> roleequipesDTO = new ArrayList<>();
        roleequipes.forEach(x -> {
            roleequipesDTO.add(roleequipeToRoleEquipeDTO(x));
        });
        return roleequipesDTO;
    }
}
