package com.inventoryProject.services;

import java.util.List;

import com.inventoryProject.dto.VariantRequestDTO;
import com.inventoryProject.models.Color;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.ProductVariantRepository;
import com.inventoryProject.validation.RequestValidator;

/**
 * VariantService
 */
public class VariantService {

  private final ProductVariantRepository variantRepository;
  private final ProductService productService;
  private final ColorService colorService;
  private final SizeService sizeService;
  private final RequestValidator requestValidator;

  public VariantService(ProductVariantRepository variantRepository, ProductService productService,
      ColorService colorService, SizeService sizeService, RequestValidator requestValidator) {
    this.variantRepository = variantRepository;
    this.productService = productService;
    this.colorService = colorService;
    this.sizeService = sizeService;
    this.requestValidator = requestValidator;
  }

  public ProductVariant find(Long variantID) {
    return null;
  }

  public List<ProductVariant> findAll() {
    return null;
  }

  public ProductVariant update(Long variantId, VariantRequestDTO variant) {
    return null;
  }

  public void archive(Long variantId) {
  }

  public void archiveByProduct(Product product) {
    variantRepository.archiveByProductId(product);
  }

  public ProductVariant create(VariantRequestDTO variant) {

    requestValidator.validateRequest(variant);

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
