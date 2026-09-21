package com.inventoryProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Color
 */
@Entity
public class Color {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long colorId;

  @Column(nullable = false)
  private String colorLabel;

  public Color(String colorLabel) {
    this.colorLabel = colorLabel;
  }

  public Long getColorId() {
    return colorId;
  }

  public void setColorId(Long colorId) {
    this.colorId = colorId;
  }

  public String getColorLabel() {
    return colorLabel;
  }

  public void setColorLabel(String colorLabel) {
    this.colorLabel = colorLabel;
  }

}
