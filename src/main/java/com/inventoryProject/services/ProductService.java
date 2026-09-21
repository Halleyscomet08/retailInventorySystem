package com.inventoryProject.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventoryProject.models.Product;
import com.inventoryProject.repositories.ProductRepository;

import java.util.List;

/**
 * ProductService
 */
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

}
