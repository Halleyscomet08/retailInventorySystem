package com.inventoryProject.dto;

import java.math.BigDecimal;

import com.inventoryProject.exception.ExceptionMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * VariantRequestDTO
 */
public class VariantRequestDTO {

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private Long productId;

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private Long colorId;

  @NotNull(message = ExceptionMessages.LABEL_REQUIRED)
  private Long sizeId;

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

  public Long getColorId() {
    return colorId;
  }

  public void setColorId(Long colorId) {
    this.colorId = colorId;
  }

  public Long getSizeId() {
    return sizeId;
  }

  public void setSizeId(Long sizeId) {
    this.sizeId = sizeId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
