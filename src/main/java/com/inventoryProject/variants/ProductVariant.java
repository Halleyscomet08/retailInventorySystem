package com.inventoryProject.variants;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.inventoryProject.product.Product;

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

  @Column
  private String color;

  @Column
  private String size;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column
  private LocalDate archivedAt;

  public LocalDate getArchivedAt() {
    return archivedAt;
  }

  public void setArchivedAt(LocalDate archivedAt) {
    this.archivedAt = archivedAt;
  }

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

  public void setProduct(Product productId) {
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
