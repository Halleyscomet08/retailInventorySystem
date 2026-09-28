package com.inventoryProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Productdto
 */
public class Productdto {

  @NotNull(message = "Please include a brand.")
  private Long brand;

  @NotBlank(message = "Please include a Product Name.")
  private String productName;

  @NotNull(message = "Please include a Category.")
  private String category;

  public Long getBrand() {
    return brand;
  }

  public void setBrand(Long brand) {
    this.brand = brand;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
