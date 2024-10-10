package com.csys.workflow.dto;

import java.lang.Integer;
import java.lang.String;


public class MenusDTO {
  private Integer codMnP;

  private String module;

  private String desMenuP;

  private String desMenuPSec;

  private String mnName;

  private String logo;

  public Integer getCodMnP() {
    return codMnP;
  }

  public MenusDTO setCodMnP(Integer codMnP) {
    this.codMnP = codMnP;
    return this;
  }

  public String getModule() {
    return module;
  }

  public MenusDTO setModule(String module) {
    this.module = module;
    return this;
  }

  public String getDesMenuP() {
    return desMenuP;
  }

  public MenusDTO setDesMenuP(String desMenuP) {
    this.desMenuP = desMenuP;
    return this;
  }

  public String getDesMenuPSec() {
    return desMenuPSec;
  }

  public MenusDTO setDesMenuPSec(String desMenuPSec) {
    this.desMenuPSec = desMenuPSec;
    return this;
  }

  public String getMnName() {
    return mnName;
  }

  public MenusDTO setMnName(String mnName) {
    this.mnName = mnName;
    return this;
  }

  public String getLogo() {
    return logo;
  }

  public MenusDTO setLogo(String logo) {
    this.logo = logo;
    return this;
  }
}

