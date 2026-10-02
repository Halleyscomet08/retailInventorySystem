package com.inventoryProject.dto;

import jakarta.validation.constraints.PositiveOrZero;

/**
 * InventoryRequestDTO
 */
public class InventoryRequestDTO {

  @PositiveOrZero
  private Long brandId;
  @PositiveOrZero
  private Long variantId;
  @PositiveOrZero
  private Integer count;

  public InventoryRequestDTO() {
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public Long getVariantId() {
    return variantId;
  }

  public void setVariantId(Long variantId) {
    this.variantId = variantId;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
