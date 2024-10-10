package com.csys.workflow.factory;

import com.csys.workflow.domain.TypeEmploye;
import com.csys.workflow.dto.TypeEmployeDTO;

import java.util.ArrayList;
import java.util.List;

public class TypeEmployeFactory {
  public static TypeEmployeDTO typeemployeToTypeEmployeDTO(TypeEmploye typeemploye) {
    TypeEmployeDTO typeemployeDTO=new TypeEmployeDTO();
    typeemployeDTO.setIdTypeEmploye(typeemploye.getIdTypeEmploye());
    typeemployeDTO.setTypeemploye(typeemploye.getTypeemploye());
    return typeemployeDTO;
  }

  public static TypeEmploye typeemployeDTOToTypeEmploye(TypeEmployeDTO typeemployeDTO) {
    TypeEmploye typeemploye=new TypeEmploye();
    typeemploye.setIdTypeEmploye(typeemployeDTO.getIdTypeEmploye());
    typeemploye.setTypeemploye(typeemployeDTO.getTypeemploye());
    return typeemploye;
  }

  public static List<TypeEmployeDTO> typeemployeToTypeEmployeDTOs(List<TypeEmploye> typeemployes) {
    List<TypeEmployeDTO> typeemployesDTO=new ArrayList<>();
    typeemployes.forEach(x -> {
      typeemployesDTO.add(typeemployeToTypeEmployeDTO(x));
    } );
    return typeemployesDTO;
  }
}