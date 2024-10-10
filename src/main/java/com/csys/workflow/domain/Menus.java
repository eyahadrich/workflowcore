package com.csys.workflow.domain;

import javax.persistence.*;

@Entity
public class Menus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codMnP", nullable = false)
    private int codMnP;
    @Basic
    @Column(name = "module", nullable = true, length = 20)
    private String module;
    @Basic
    @Column(name = "desMenuP", nullable = true, length = 100)
    private String desMenuP;
    @Basic
    @Column(name = "desMenuPSec", nullable = true, length = 100)
    private String desMenuPSec;
    @Basic
    @Column(name = "mnName", nullable = true, length = 50)
    private String mnName;
    @Basic
    @Column(name = "logo", nullable = true, length = 50)
    private String logo;

    public int getCodMnP() {
        return codMnP;
    }

    public void setCodMnP(int codMnP) {
        this.codMnP = codMnP;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDesMenuP() {
        return desMenuP;
    }

    public void setDesMenuP(String desMenuP) {
        this.desMenuP = desMenuP;
    }

    public String getDesMenuPSec() {
        return desMenuPSec;
    }

    public void setDesMenuPSec(String desMenuPSec) {
        this.desMenuPSec = desMenuPSec;
    }

    public String getMnName() {
        return mnName;
    }

    public void setMnName(String mnName) {
        this.mnName = mnName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menus menus = (Menus) o;

        if (codMnP != menus.codMnP) return false;
        if (module != null ? !module.equals(menus.module) : menus.module != null) return false;
        if (desMenuP != null ? !desMenuP.equals(menus.desMenuP) : menus.desMenuP != null) return false;
        if (desMenuPSec != null ? !desMenuPSec.equals(menus.desMenuPSec) : menus.desMenuPSec != null) return false;
        if (mnName != null ? !mnName.equals(menus.mnName) : menus.mnName != null) return false;
        if (logo != null ? !logo.equals(menus.logo) : menus.logo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codMnP;
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (desMenuP != null ? desMenuP.hashCode() : 0);
        result = 31 * result + (desMenuPSec != null ? desMenuPSec.hashCode() : 0);
        result = 31 * result + (mnName != null ? mnName.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        return result;
    }
}
