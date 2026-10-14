package com.inventoryProject.variants;

import java.util.List;

import com.inventoryProject.dto.VariantRequestDTO;
import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.product.Product;
import com.inventoryProject.product.ProductService;
import com.inventoryProject.validation.RequestValidator;

/**
 * VariantService
 */
public class VariantService {

  private final ProductVariantRepository variantRepository;
  private final ProductService productService;
  private final RequestValidator requestValidator;

  public VariantService(ProductVariantRepository variantRepository, ProductService productService,
      RequestValidator requestValidator) {
    this.variantRepository = variantRepository;
    this.productService = productService;
    this.requestValidator = requestValidator;
  }

  public ProductVariant find(Long variantID) {
    return variantRepository.findById(variantID)
        .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.VARIANT_NOT_FOUND));
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

    ProductVariant variantObject = new ProductVariant();
    variantObject.setProduct(product);
    variantObject.setColor(variant.getColor());
    variantObject.setSize(variant.getSize());
    variantObject.setPrice(variant.getPrice());

    ProductVariant response = variantRepository.save(variantObject);
    return response;
  }

}
