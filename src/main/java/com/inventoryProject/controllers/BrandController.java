package com.inventoryProject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.models.Brand;
import com.inventoryProject.services.BrandService;

import java.util.List;

import jakarta.validation.Valid;

/**
 * BrandController
 */
@RestController
@RequestMapping("/api/brand")
public class BrandController {

  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  public ResponseEntity<List<Brand>> getAll() {
    return ResponseEntity.ok(brandService.getAll());
  }

  @GetMapping("/{brandId}")
  public ResponseEntity<Brand> get(@PathVariable Long brandId) {
    return ResponseEntity.ok(brandService.get(brandId));
  }

  @PostMapping()
  public ResponseEntity<Brand> create(@Valid @RequestBody BrandDTO dto) {
    return ResponseEntity.ok(brandService.create(dto));

  }

  @PatchMapping("/{brandId}/archive")
  public ResponseEntity<String> archive(@PathVariable Long brandId) {
    brandService.archiveBrand(brandId);
    return ResponseEntity.ok("{brandId} archived. ");
  }

}
