package com.inventoryProject.models;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * InventoryId
 */
public class InventoryId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store storeId;

  @ManyToOne
  @JoinColumn(name = "variant_id")
  private ProductVariant variantId;

  public InventoryId(Store storeId, ProductVariant variantId) {
    this.storeId = storeId;
    this.variantId = variantId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
    result = prime * result + ((variantId == null) ? 0 : variantId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    InventoryId other = (InventoryId) obj;
    if (storeId == null) {
      if (other.storeId != null)
        return false;
    } else if (!storeId.equals(other.storeId))
      return false;
    if (variantId == null) {
      if (other.variantId != null)
        return false;
    } else if (!variantId.equals(other.variantId))
      return false;
    return true;
  }

}
