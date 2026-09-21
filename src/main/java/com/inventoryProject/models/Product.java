package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  public Product() {
  }

  public Long getproductId() {
    return productId;
  }

  public String getproductName() {
    return productName;
  }

  public Brand getBrand() {
    return brand;
  }

  public String getCategory() {
    return category;
  }

  public void setproductId(Long productId) {
    this.productId = productId;
  }

  public void setproductName(String productName) {
    this.productName = productName;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
