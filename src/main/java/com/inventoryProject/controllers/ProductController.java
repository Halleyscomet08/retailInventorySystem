package com.inventoryProject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.services.ProductService;
import com.inventoryProject.dto.ProductResponseDTO;
import com.inventoryProject.dto.Productdto;
import com.inventoryProject.models.Product;

/**
 * ProductController
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  @ResponseBody
  public ResponseEntity<List<ProductResponseDTO>> getAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @PostMapping("/create")
  public ResponseEntity<Productdto> create(@RequestBody Productdto dto) {
    return ResponseEntity.ok(productService.create(dto));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> get(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.get(productId));
  }

  // function that manages output of products
  @GetMapping("brand/{brandId}")
  public ResponseEntity<List<ProductResponseDTO>> getByBrand(@PathVariable Long brandId) {
    return ResponseEntity.ok(productService.getByBrand(brandId));
  }
}
