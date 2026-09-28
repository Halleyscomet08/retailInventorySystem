package com.inventoryProject.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * BrandDTO
 */
public class BrandDTO {

  @NotBlank
  private String brandName;

  public BrandDTO() {
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

}
