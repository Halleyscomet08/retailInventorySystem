package com.inventoryProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

/**
 * Inventory
 */
@Entity
@IdClass(InventoryId.class)
public class Inventory {

  @Id
  private Long storeId;

  @Id
  private Long variantId;

  public Inventory(Long storeId, Long variantId) {
    this.storeId = storeId;
    this.variantId = variantId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Long getVariantId() {
    return variantId;
  }

  public void setVariantId(Long variantId) {
    this.variantId = variantId;
  }

}
