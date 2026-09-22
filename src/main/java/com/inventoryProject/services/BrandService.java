package com.inventoryProject.services;

import org.springframework.stereotype.Service;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.models.Brand;
import com.inventoryProject.repositories.BrandRepository;

/**
 * BrandService
 */
@Service
public class BrandService {

  private final BrandRepository brandRepository;

  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public BrandDTO get(Long brandId) {
    return toDTO(brandRepository.findById(brandId)
        .orElseThrow(() -> new RuntimeException("Brand not found")));
  }

  public BrandDTO create(BrandDTO dto) {
    Brand created = new Brand();
    created.setBrandName(dto.getBrandName());

    Brand saved = brandRepository.save(created);

    return toDTO(saved);

  }

  private BrandDTO toDTO(Brand brand) {
    BrandDTO dto = new BrandDTO();

    dto.setBrandName(brand.getBrandName());

    return dto;

  }

}
