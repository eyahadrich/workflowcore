package com.csys.workflow.factory;


import com.csys.workflow.domain.Menus;
import com.csys.workflow.dto.MenusDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenusFactory {
  public static MenusDTO menusToMenusDTO(Menus menus) {
    MenusDTO menusDTO=new MenusDTO();
    menusDTO.setCodMnP(menus.getCodMnP());
    menusDTO.setModule(menus.getModule());
    menusDTO.setDesMenuP(menus.getDesMenuP());
    menusDTO.setDesMenuPSec(menus.getDesMenuPSec());
    menusDTO.setMnName(menus.getMnName());
    menusDTO.setLogo(menus.getLogo());
    return menusDTO;
  }

  public static Menus menusDTOToMenus(MenusDTO menusDTO) {
    Menus menus=new Menus();
   // menus.setCodMnP(menusDTO.getCodMnP());
    menus.setModule(menusDTO.getModule());
    menus.setDesMenuP(menusDTO.getDesMenuP());
    menus.setDesMenuPSec(menusDTO.getDesMenuPSec());
    menus.setMnName(menusDTO.getMnName());
    menus.setLogo(menusDTO.getLogo());
    return menus;
  }

  public static Collection<MenusDTO> menusToMenusDTOs(Collection<Menus> menuss) {
    List<MenusDTO> menussDTO=new ArrayList<>();
    menuss.forEach(x -> {
      menussDTO.add(menusToMenusDTO(x));
    } );
    return menussDTO;
  }
}

