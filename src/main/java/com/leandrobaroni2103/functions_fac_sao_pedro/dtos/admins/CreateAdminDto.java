package com.leandrobaroni2103.functions_fac_sao_pedro.dtos.admins;

public class CreateAdminDto {
  private String name;
  private String email;
  private String password;
  private Boolean active;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return this.password;
  }

  public Boolean getActive() {
    return this.active;
  }
}
