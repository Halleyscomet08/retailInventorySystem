package com.inventoryProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.models.Brand;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.services.BrandService;

/**
 * BrandServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

  @Mock
  BrandRepository brandRepository;

  @InjectMocks
  BrandService brandService;

  @Test
  void getBrand_whenGivenID() {

    Brand brand = new Brand();
    brand.setBrandID(1L);
    brand.setBrandName("Blueshop");

    when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

    BrandDTO result = brandService.get(1L);

    assertEquals("Blueshop", result.getBrandName());

  }

  @Test
  void createBrand_whenGivenName() {

    BrandDTO brand = new BrandDTO();
    brand.setBrandName("Blueshop");

    Brand brandRepoSave = new Brand();
    brandRepoSave.setBrandID(1L);
    brandRepoSave.setBrandName("Blueshop");

    when(brandRepository.save(any(Brand.class))).thenReturn(brandRepoSave);

    BrandDTO result = brandService.create(brand);

    assertEquals("Blueshop", result.getBrandName());

  }

}
