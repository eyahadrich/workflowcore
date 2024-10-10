package com.csys.workflow.dto;




public class RoleEquipeDTO {
    
private int roleEquipe;
    
private int idEmploye;

    
private int idEquipe;

private String nomEmploye;

 private String designation;
    
 private EmployeDTO employe;

 private RoleDTO role;

 public int getRoleEquipe() {
  return roleEquipe;
 }

 public RoleEquipeDTO setRoleEquipe(int roleEquipe) {
  this.roleEquipe = roleEquipe;
  return this;
 }

 public int getIdEmploye() {
  return idEmploye;
 }

 public RoleEquipeDTO setIdEmploye(int idEmploye) {
  this.idEmploye = idEmploye;
  return this;
 }

 public int getIdEquipe() {
  return idEquipe;
 }

 public RoleEquipeDTO setIdEquipe(int idEquipe) {
  this.idEquipe = idEquipe;
  return this;
 }

 public String getNomEmploye() {
  return nomEmploye;
 }

 public RoleEquipeDTO setNomEmploye(String nomEmploye) {
  this.nomEmploye = nomEmploye;
  return this;
 }

 public String getDesignation() {
  return designation;
 }

 public RoleEquipeDTO setDesignation(String designation) {
  this.designation = designation;
  return this;
 }

 public EmployeDTO getEmploye() {
  return employe;
 }

 public RoleEquipeDTO setEmploye(EmployeDTO employe) {
  this.employe = employe;
  return this;
 }

 public RoleDTO getRole() {
  return role;
 }

 public RoleEquipeDTO setRole(RoleDTO role) {
  this.role = role;
  return this;
 }
}

