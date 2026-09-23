package com.inventoryProject.services;

import org.springframework.stereotype.Service;

import com.inventoryProject.dto.ProductResponseDTO;
import com.inventoryProject.dto.Productdto;
import com.inventoryProject.models.Brand;
import com.inventoryProject.models.Product;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.repositories.ProductRepository;

import java.util.List;

/**
 * ProductService
 */
@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final BrandRepository brandRepository;

  public ProductService(ProductRepository productRepository, BrandRepository brandRepository) {
    this.productRepository = productRepository;
    this.brandRepository = brandRepository;
  }

  public List<ProductResponseDTO> findAll() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::toResponseDTO).toList();
  }

  public ProductResponseDTO create(Productdto dto) {
    Product product = new Product();
    Brand brand = brandRepository.findById(dto.getBrand())
        .orElseThrow(() -> new RuntimeException("Brand not found"));

    product.setBrand(brand);
    product.setproductName(dto.getProductName());
    product.setCategory(dto.getCategory());

    return toResponseDTO(productRepository.save(product));
  }

  public ProductResponseDTO get(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    return toResponseDTO(product);
  }

  public List<ProductResponseDTO> getByBrand(Long brandId) {

    List<Product> products = productRepository.findByBrand_BrandId(brandId);
    return products.stream().map(this::toResponseDTO).toList();

  }

  private ProductResponseDTO toResponseDTO(Product product) {

    ProductResponseDTO dto = new ProductResponseDTO();

    dto.setProductId(product.getproductId());
    dto.setBrandName(product.getBrand().getBrandName());
    dto.setProductName(product.getproductName());
    dto.setCategory(product.getCategory());

    return dto;

  }

  private Productdto toDTO(Product product) {

    Productdto dto = new Productdto();

    dto.setBrand(product.getBrand().getBrandID());
    dto.setProductName(product.getproductName());
    dto.setCategory(product.getCategory());

    return dto;
  }
}
