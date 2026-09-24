package com.inventoryProject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.services.BrandService;

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

  @GetMapping("/{brandId}")
  public ResponseEntity<BrandDTO> get(@PathVariable Long brandId) {
    return ResponseEntity.ok(brandService.get(brandId));
  }

  @PostMapping()
  public ResponseEntity<BrandDTO> create(@Valid @RequestBody BrandDTO dto) {
    return ResponseEntity.ok(brandService.create(dto));

  }

}
