package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Brand
 */
@Entity
public class Brand {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long brandId;

  @Column
  private String brandName;

  @Enumerated(EnumType.STRING)
  private EntityMode status = EntityMode.ACTIVE;

  public Brand() {
  }

  public Long getBrandID() {
    return brandId;
  }

  public void setBrandID(Long brandID) {
    this.brandId = brandID;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public EntityMode getStatus() {
    return status;
  }

  public void setStatus(EntityMode status) {
    this.status = status;
  }
}
