package com.inventoryProject.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.VariantRequestDTO;
import com.inventoryProject.exception.FieldsNotValidException;
import com.inventoryProject.models.Color;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.ProductVariantRepository;
import com.inventoryProject.services.ColorService;
import com.inventoryProject.services.ProductService;
import com.inventoryProject.services.SizeService;
import com.inventoryProject.services.VariantService;
import com.inventoryProject.validation.RequestValidator;

/**
 * VariantServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class VariantServiceTest {

  @Mock
  ProductVariantRepository variantRepository;

  @Mock
  ProductService productService;

  @Mock
  ColorService colorService;

  @Mock
  SizeService sizeService;

  @Mock
  RequestValidator requestValidator;

  @InjectMocks
  VariantService variantService;

  @Captor
  ArgumentCaptor<ProductVariant> variantCaptor;

  // given a variantID that exists, when I call find(), then the function returns
  // the correct Variant
  // given a variantID that is null, when I call find(), then the function will
  // return an IllegalArgumentException
  // given a variantID that is blank, when I call find(), then the function wil
  // return an IllegalArgumentException
  //
  // given a good Variant DTO, when I call create(), a variant product will be
  // created.

  @Test
  void create_validDTO_variantCreated() {

    // Arrange
    Product product = new Product();
    product.setproductId(1L);
    Color color = new Color();
    color.setColorId(3L);
    Size size = new Size();
    size.setSizeId(2L);

    ProductVariant variant = new ProductVariant();
    variant.setVariantId(1L);
    variant.setProductId(product);
    variant.setColorId(color);
    variant.setSizeId(size);
    variant.setPrice(BigDecimal.valueOf(100));

    VariantRequestDTO variantRequest = new VariantRequestDTO();
    variantRequest.setProductId(1L);
    variantRequest.setColorId(3L);
    variantRequest.setSizeId(2L);
    variantRequest.setPrice(BigDecimal.valueOf(100));

    when(variantRepository.save(any(ProductVariant.class))).thenReturn(variant);
    when(productService.findByProductId(1L)).thenReturn(product);
    when(colorService.find(3L)).thenReturn(color);
    when(sizeService.find(2L)).thenReturn(size);

    // Act
    ProductVariant response = variantService.create(variantRequest);
    verify(variantRepository).save(variantCaptor.capture());
    ProductVariant captured = variantCaptor.getValue();

    // Assert

    // check if dto is assigned correctly
    assertNull(captured.getVariantId());
    assertEquals(product.getproductId(), captured.getProductId().getproductId());
    assertEquals(color.getColorId(), captured.getColorId().getColorId());
    assertEquals(size.getSizeId(), captured.getSizeId().getSizeId());
    assertEquals(BigDecimal.valueOf(100), captured.getPrice());

    // check if save is called
    assertNotNull(response.getProductId().getproductId());
    assertNotNull(response.getColorId().getColorId());
    assertNotNull(response.getSizeId().getSizeId());
    assertNotNull(response.getPrice());

  }
  // given a Variant DTO with missing values, when I call create(), then the
  // function will return an IllegalArgumentException

  @Test
  void create_missingValues_throwsException() {
    // wrong file, move to new
    //
    // need to trigger the request validator function
    //

    VariantRequestDTO badRequest = new VariantRequestDTO();

    doThrow(FieldsNotValidException.class).when(requestValidator).validateRequest(any());

    assertThrows(FieldsNotValidException.class, () -> {
      variantService.create(badRequest);
    });
    verifyNoInteractions(variantRepository);

  }

  // Given a Variant DTO with values, when I call create(), then the function will

}
