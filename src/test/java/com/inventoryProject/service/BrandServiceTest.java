package com.inventoryProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.exception.FieldsNotValidException;
import com.inventoryProject.models.Brand;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.services.BrandService;
import com.inventoryProject.validation.RequestValidator;

/**
 * BrandServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

  @Mock
  BrandRepository brandRepository;

  @Mock
  RequestValidator requestValidator;

  @InjectMocks
  BrandService brandService;

  @Test
  void getBrand_whenGivenID() {

    Brand brand = new Brand();
    brand.setBrandID(1L);
    brand.setBrandName("Blueshop");

    when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

    Brand result = brandService.get(1L);

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

    Brand result = brandService.create(brand);

    assertEquals("Blueshop", result.getBrandName());

  }

  @Test
  void create_givenBlank_throwException() {
    BrandDTO badRequest = new BrandDTO();

    doThrow(FieldsNotValidException.class).when(requestValidator).validateRequest(any());

    assertThrows(FieldsNotValidException.class, () -> {
      brandService.create(badRequest);
    });
    verifyNoInteractions(brandRepository);
  }

  @Test
  void updateBrand_whenGivenDTO() {

    Brand initialBrand = new Brand();
    initialBrand.setBrandID(1L);
    initialBrand.setBrandName("Blueshop");

    BrandDTO newBrand = new BrandDTO();
    newBrand.setBrandName("Well-off");

    Brand finalBrand = new Brand();
    finalBrand.setBrandID(1L);
    finalBrand.setBrandName("Well-off");

    when(brandRepository.findById(1L)).thenReturn(Optional.of(initialBrand));
    when(brandRepository.save(any(Brand.class))).thenReturn(finalBrand);

    Brand response = brandService.update(1L, newBrand);

    assertNotNull(response.getBrandID());
    assertEquals("Well-off", response.getBrandName());
  }

}
