package com.inventoryProject.services;

import org.springframework.stereotype.Service;

import com.inventoryProject.dto.ProductResponseDTO;
import com.inventoryProject.dto.Productdto;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Brand;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.EntityMode;
import com.inventoryProject.repositories.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.List;

/**
 * ProductService
 */
@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final BrandService brandService;

  public ProductService(ProductRepository productRepository, BrandService brandService) {
    this.productRepository = productRepository;
    this.brandService = brandService;
  }

  public List<ProductResponseDTO> findAll() {
    List<Product> products = productRepository
        .findAllByStatus(EntityMode.ACTIVE);
    return products.stream().map(this::toResponseDTO).toList();
  }

  public ProductResponseDTO create(Productdto dto) {
    Product product = new Product();

    Brand brand = brandService.findBrandbyId(dto.getBrand());

    product.setBrand(brand);
    product.setproductName(dto.getProductName());
    product.setCategory(dto.getCategory());

    return toResponseDTO(productRepository.save(product));
  }

  public ProductResponseDTO get(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    return toResponseDTO(product);
  }

  public List<ProductResponseDTO> getByBrand(Long brandId) {

    List<Product> products = productRepository.findByBrand_BrandId(brandId);
    return products.stream().map(this::toResponseDTO).toList();

  }

  @Transactional(rollbackOn = Exception.class)
  public ProductResponseDTO update(Long productId, Productdto dto) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not Found"));

    Brand brand = brandService.findBrandbyId(dto.getBrand());

    product.setBrand(brand);
    product.setproductName(dto.getProductName());
    product.setCategory(dto.getCategory());

    Product updated = productRepository.save(product);

    return toResponseDTO(updated);
  }

  @Transactional(rollbackOn = Exception.class)
  public void archiveProduct(Long productID) {
    Product product = productRepository.findById(productID)
        .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    // in the future once the variants table is setup, query for variants
    // where productID = product.getproductId(), foreach(variant ->
    // variant.setdeletedAt(current_date))
    // For now,
    product.setStatus(EntityMode.ARCHIVED);

    productRepository.save(product);
  }

  private ProductResponseDTO toResponseDTO(Product product) {

    ProductResponseDTO dto = new ProductResponseDTO();

    dto.setProductId(product.getproductId());
    dto.setBrandName(product.getBrand().getBrandName());
    dto.setProductName(product.getproductName());
    dto.setCategory(product.getCategory());

    return dto;

  }

}
