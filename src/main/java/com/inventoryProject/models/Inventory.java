package com.inventoryProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Inventory
 */
@Entity
@IdClass(InventoryId.class)
public class Inventory {

  @Id
  private Store storeId;

  @Id
  private ProductVariant variantId;

  @PositiveOrZero
  private Integer count;

  public void addInventory(int change) {
    count += change;
  }

  public void subtractInventory(int change) {
    count -= change;
  }

  public Inventory() {
  }

  public Store getStoreId() {
    return storeId;
  }

  public void setStoreId(Store storeId) {
    this.storeId = storeId;
  }

  public ProductVariant getVariantId() {
    return variantId;
  }

  public void setVariantId(ProductVariant variantId) {
    this.variantId = variantId;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getCount() {
    return count;
  }

}
