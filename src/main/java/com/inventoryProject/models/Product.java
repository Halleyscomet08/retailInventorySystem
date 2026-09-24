package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Product
 */
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @Column(nullable = false)
  private String productName;

  @Column
  private String category;

  @Enumerated(EnumType.STRING)
  private EntityMode status = EntityMode.ACTIVE;

  public Product() {
  }

  public Long getproductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public String getproductName() {
    return productName;
  }

  public void setproductName(String productName) {
    this.productName = productName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public EntityMode getStatus() {
    return status;
  }

  public void setStatus(EntityMode status) {
    this.status = status;
  }

}
