package com.inventoryProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.inventoryProject.controllers.ProductController;
import com.inventoryProject.dto.VariantRequestDTO;
import com.inventoryProject.exception.FieldsNotValidException;
import com.inventoryProject.models.Color;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.ProductVariantRepository;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

/**
 * VariantService
 */
public class VariantService {

  private final ProductVariantRepository variantRepository;
  private final ProductService productService;
  private final ColorService colorService;
  private final SizeService sizeService;
  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  Validator validator = factory.getValidator();

  public VariantService(ProductVariantRepository variantRepository, ProductService productService,
      ColorService colorService, SizeService sizeService) {
    this.variantRepository = variantRepository;
    this.productService = productService;
    this.colorService = colorService;
    this.sizeService = sizeService;
  }

  public ProductVariant find(Long variantID) {
    return null;
  }

  public ProductVariant create(VariantRequestDTO variant) {

    Set<ConstraintViolation<VariantRequestDTO>> violations = validator.validate(variant);

    if (!violations.isEmpty()) {

      List<String> messages = new ArrayList<String>();

      for (ConstraintViolation<VariantRequestDTO> violation : violations) {
        messages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());

      }
      throw new FieldsNotValidException("Fields not valid", messages);

    }
    ;
    Product product = productService.findByProductId(variant.getProductId());
    Color color = colorService.find(variant.getColorId());
    Size size = sizeService.find(variant.getSizeId());

    ProductVariant variantObject = new ProductVariant();
    variantObject.setProductId(product);
    variantObject.setColorId(color);
    variantObject.setSizeId(size);
    variantObject.setPrice(variant.getPrice());

    ProductVariant response = variantRepository.save(variantObject);
    return response;
  }

}
