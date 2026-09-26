package com.inventoryProject.services;

import org.springframework.stereotype.Service;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Brand;
import com.inventoryProject.models.EntityMode;
import com.inventoryProject.repositories.BrandRepository;

import jakarta.transaction.Transactional;

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
        .orElseThrow(() -> new ResourceNotFoundException("Brand not found")));
  }

  public Brand findBrandbyId(Long brandId) {
    return brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
  }

  public BrandDTO create(BrandDTO dto) {
    Brand created = new Brand();
    created.setBrandName(dto.getBrandName());

    Brand saved = brandRepository.save(created);

    return toDTO(saved);

  }

  @Transactional(rollbackOn = Exception.class)
  public void archiveBrand(Long brandId) {
    Brand brand = brandRepository.findById(brandId)
        .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    // in the future once the variants table is setup, query for variants
    // where productID = product.getproductId(), foreach(variant ->
    // variant.setdeletedAt(current_date))
    // For now,
    brand.setStatus(EntityMode.ARCHIVED);

    brandRepository.save(brand);
  }

  private BrandDTO toDTO(Brand brand) {
    BrandDTO dto = new BrandDTO();

    dto.setBrandName(brand.getBrandName());

    return dto;

  }

}
