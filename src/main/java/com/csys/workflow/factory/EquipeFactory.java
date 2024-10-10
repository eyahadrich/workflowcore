package com.csys.workflow.factory;



import com.csys.workflow.domain.Equipe;
import com.csys.workflow.domain.RoleEquipe;
import com.csys.workflow.dto.EquipeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

public class EquipeFactory {
  public static EquipeDTO equipeToEquipeDTO(Equipe equipe) {
    EquipeDTO equipeDTO=new EquipeDTO();
    equipeDTO.setIdEquipe(equipe.getIdEquipe());
    equipeDTO.setNomEquipe(equipe.getNomEquipe());
//    equipeDTO.setRoleEquipeList(equipe.getRoleEquipeList());
    equipeDTO.setRoleEquipeList(RoleEquipeFactory.roleequipeToRoleEquipeDTOs(equipe.getRoleEquipeList()));
    return equipeDTO;
  }

  public static Equipe equipeDTOToEquipe(EquipeDTO equipeDTO) {
    Equipe equipe=new Equipe();
    equipe.setIdEquipe(equipeDTO.getIdEquipe());
    equipe.setNomEquipe(equipeDTO.getNomEquipe());
    //equipe.setRoleEquipeList(equipeDTO.getRoleEquipeList());
    List<RoleEquipe> roleEquipeList = new ArrayList<>();
    equipe.setRoleEquipeList(roleEquipeList);

    return equipe;
  }

  public static List<EquipeDTO> equipeToEquipeDTOs(List<Equipe> equipes) {
    List<EquipeDTO> equipesDTO=new ArrayList<>();
    equipes.forEach(x -> {
      equipesDTO.add(equipeToEquipeDTO(x));
    } );
    return equipesDTO;
  }
  // Add this method to check if an employee is in the team
  public static boolean isEmployeeInEquipe(Equipe equipe, int employeeId) {
    if (equipe.getRoleEquipeList() != null) {
      for (RoleEquipe roleEquipe : equipe.getRoleEquipeList()) {
        if (roleEquipe.getRoleEquipePK().getIdEmploye() == employeeId) {
          return true;
        }
      }
    }
    return false;
  }



    /**
     * Transform a list of Equipe entities into a list of employee IDs for each equipe.
     *
     * @param equipes the list of Equipe entities
     * @return the list of employee IDs for each equipe
     */
    public static List<List<Integer>> equipesToEmployeeIdLists(List<Equipe> equipes) {
      return equipes.stream()
              .map(equipe -> equipe.getRoleEquipeList().stream()
                      .map(roleEquipe -> roleEquipe.getRoleEquipePK().getIdEmploye())
                      .collect(Collectors.toList()))
              .collect(Collectors.toList());
    }
  public static boolean isEmployeeInEquipeWithRole(Equipe equipe, int employeeId, String role) {
    if (equipe.getRoleEquipeList() != null) {
      for (RoleEquipe roleEquipe : equipe.getRoleEquipeList()) {
        if (roleEquipe.getRoleEquipePK().getIdEmploye() == employeeId && role.equals(roleEquipe.getRole().getDesignation())) {
          return true;
        }
      }
    }
    return false;
  }
  public static List<Integer> equipesToEmployeeIdList(List<Equipe> equipes) {
    return equipes.stream()
            .flatMap(equipe -> equipe.getRoleEquipeList().stream()
                    .map(roleEquipe -> roleEquipe.getRoleEquipePK().getIdEmploye()))
            .collect(Collectors.toList());
  }




}

