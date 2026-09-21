package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Store
 */
@Entity
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long storeId;

  @Column(nullable = false)
  private String storeLocation;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public String getStoreLocation() {
    return storeLocation;
  }

  public void setStoreLocation(String storeLocation) {
    this.storeLocation = storeLocation;
  }

}
