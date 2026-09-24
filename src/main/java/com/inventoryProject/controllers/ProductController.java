package com.inventoryProject.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.services.ProductService;

import jakarta.validation.Valid;

import com.inventoryProject.dto.ProductResponseDTO;
import com.inventoryProject.dto.Productdto;

/**
 * ProductController
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> get(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.get(productId));
  }

  // function that manages output of products
  @GetMapping("/brand/{brandId}")
  public ResponseEntity<List<ProductResponseDTO>> getByBrand(@PathVariable Long brandId) {
    return ResponseEntity.ok(productService.getByBrand(brandId));
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody Productdto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> update(@PathVariable Long productId, @RequestBody Productdto dto) {
    return ResponseEntity.ok(productService.update(productId, dto));
  }

  @PatchMapping("/{product_id}/archive")
  public ResponseEntity<String> archive(@PathVariable Long productId) {
    productService.archiveProduct(productId);
    return ResponseEntity.ok("Product Archived Successfully");
  }
}
