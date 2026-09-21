package com.inventoryProject.services;

import org.springframework.stereotype.Service;

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

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Productdto create(Productdto dto) {
    Product product = new Product();
    Brand brand = brandRepository.findById(dto.getBrand())
        .orElseThrow(() -> new RuntimeException("Brand not found"));

    product.setBrand(brand);
    product.setproductName(dto.getProductName());
    product.setCategory(dto.getCategory());

    return toDTO(productRepository.save(product));
  }

  public Productdto get(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    return toDTO(product);
  }

  private Productdto toDTO(Product product) {

    Productdto dto = new Productdto();

    dto.setBrand(product.getBrand().getBrandID());
    dto.setProductName(product.getproductName());
    dto.setCategory(product.getCategory());

    return dto;
  }
}
