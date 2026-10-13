package com.inventoryProject.dto;

import java.math.BigDecimal;

import com.inventoryProject.exception.ExceptionMessages;

import jakarta.validation.constraints.NotNull;

/**
 * VariantRequestDTO
 */
public class VariantRequestDTO {

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private Long productId;

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private String color;

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private String size;

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private BigDecimal price;

  public VariantRequestDTO() {
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

}
