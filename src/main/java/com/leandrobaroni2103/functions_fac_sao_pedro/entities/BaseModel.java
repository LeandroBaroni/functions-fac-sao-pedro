package com.leandrobaroni2103.functions_fac_sao_pedro.entities;

import java.util.Date;

public class BaseModel {
  private String id;
  private Date createdAt;
  private Date updatedAt;

  public String getId() {
    return id;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
