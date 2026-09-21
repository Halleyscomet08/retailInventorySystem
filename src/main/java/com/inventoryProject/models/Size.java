package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Size
 */
@Entity
public class Size {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sizeId;

  @Column(nullable = false)
  private String sizeLabel;

  public Size() {
  }

  public Long getSizeId() {
    return sizeId;
  }

  public void setSizeId(Long sizeId) {
    this.sizeId = sizeId;
  }

  public String getSizeLabel() {
    return sizeLabel;
  }

  public void setSizeLabel(String sizeLabel) {
    this.sizeLabel = sizeLabel;
  }

}
