package com.inventoryProject.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * ProductVariant
 */
@Entity
public class ProductVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long variantId;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product productId;

  @ManyToOne
  @JoinColumn(name = "color_id")
  private Color colorId;

  @ManyToOne
  @JoinColumn(name = "size_id")
  private Size sizeId;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  public ProductVariant() {
  }

  public Long getVariantId() {
    return variantId;
  }

  public void setVariantId(Long variantId) {
    this.variantId = variantId;
  }

  public Product getProductId() {
    return productId;
  }

  public void setProductId(Product productId) {
    this.productId = productId;
  }

  public Color getColorId() {
    return colorId;
  }

  public void setColorId(Color colorId) {
    this.colorId = colorId;
  }

  public Size getSizeId() {
    return sizeId;
  }

  public void setSizeId(Size sizeId) {
    this.sizeId = sizeId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
