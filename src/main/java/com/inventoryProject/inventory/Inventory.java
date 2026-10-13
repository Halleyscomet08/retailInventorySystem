package com.inventoryProject.inventory;

import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Store;

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

  public void changeInventory(int change) {
    count += change;
  }

  public Inventory() {
  }

  public Store getStore() {
    return storeId;
  }

  public void setStore(Store storeId) {
    this.storeId = storeId;
  }

  public ProductVariant getVariant() {
    return variantId;
  }

  public void setVariant(ProductVariant variantId) {
    this.variantId = variantId;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getCount() {
    return count;
  }

  public Long getStoreId() {
    return storeId.getStoreId();
  }

  public Long getVariantId() {
    return variantId.getVariantId();
  }

}
