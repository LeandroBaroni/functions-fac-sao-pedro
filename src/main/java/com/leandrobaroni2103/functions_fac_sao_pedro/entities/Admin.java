package com.leandrobaroni2103.functions_fac_sao_pedro.entities;

import com.leandrobaroni2103.functions_fac_sao_pedro.enums.UserType;

import java.util.Date;

public class Admin extends BaseModel {
  private String name;
  private String email;
  private UserType role;
  private Boolean active;
  private Date lastAccess;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserType getRole() {
    return role;
  }

  public void setRole(UserType role) {
    this.role = role;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Date getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess(Date lastAccess) {
    this.lastAccess = lastAccess;
  }
}
