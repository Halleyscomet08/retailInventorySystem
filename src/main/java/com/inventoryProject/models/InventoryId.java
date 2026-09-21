package com.inventoryProject.models;

import java.io.Serializable;

/**
 * InventoryId
 */
public class InventoryId implements Serializable {

  private Long storeId;
  private Long variantId;

  public InventoryId(Long storeId, Long variantId) {
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
