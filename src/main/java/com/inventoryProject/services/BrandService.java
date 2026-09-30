package com.inventoryProject.services;

import org.springframework.stereotype.Service;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.models.Brand;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.EntityMode;
import com.inventoryProject.repositories.BrandRepository;

import java.util.List;
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

  public List<Brand> getAll() {
    List<Brand> products = brandRepository
        .findBrandByStatus(EntityMode.ACTIVE);
    return products;

  }

  public Brand get(Long brandId) {
    return brandRepository.findById(brandId)
        .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
  }

  public Brand findBrandbyId(Long brandId) {
    return brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
  }

  public Brand create(BrandDTO dto) {
    Brand created = new Brand();
    created.setBrandName(dto.getBrandName());

    Brand saved = brandRepository.save(created);

    return saved;

  }

  @Transactional(rollbackOn = Exception.class)
  public Brand update(Long brandId, BrandDTO dto) {
    Brand brand = brandRepository.findById(brandId)
        .orElseThrow(() -> new ResourceNotFoundException("Brand not Found"));

    brand.setBrandName(dto.getBrandName());

    Brand updated = brandRepository.save(brand);

    return updated;
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

}
