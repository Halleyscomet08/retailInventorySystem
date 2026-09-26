package com.inventoryProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.ProductResponseDTO;
import com.inventoryProject.dto.Productdto;
import com.inventoryProject.models.Brand;
import com.inventoryProject.models.Product;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.repositories.ProductRepository;
import com.inventoryProject.services.BrandService;
import com.inventoryProject.services.ProductService;

/**
 * ProductServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  ProductRepository productRepository;

  @Mock
  BrandService brandService;

  @InjectMocks
  ProductService productService;

  @Test
  void getProduct_givenID() {
    Brand brand = new Brand();
    brand.setBrandID(1L);
    brand.setBrandName("Blueshop");

    Product product = new Product();

    product.setproductId(1L);
    product.setproductName("Polo");
    product.setBrand(brand);
    product.setCategory("Outerwear");

    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    ProductResponseDTO response = productService.get(1L);

    assertNotNull(response.getProductId());
    assertEquals("Polo", response.getProductName());
    assertEquals("Blueshop", response.getBrandName());
    assertEquals("Outerwear", response.getCategory());

  }

  @Test
  void createProduct_whenGivenDTO() {
    Brand brand = new Brand();
    brand.setBrandID(1L);
    brand.setBrandName("Blueshop");

    Productdto productRequest = new Productdto();
    productRequest.setProductName("Polo");
    productRequest.setBrand(1L);
    productRequest.setCategory("Outerwear");

    Product product = new Product();

    product.setproductId(1L);
    product.setproductName("Polo");
    product.setBrand(brand);
    product.setCategory("Outerwear");

    when(productRepository.save(any(Product.class))).thenReturn(product);
    when(brandService.findBrandbyId(1L)).thenReturn(brand);

    ProductResponseDTO response = productService.create(productRequest);

    assertNotNull(response.getProductId());
    assertEquals("Polo", response.getProductName());
    assertEquals("Blueshop", response.getBrandName());
    assertEquals("Outerwear", response.getCategory());

  }

  @Test
  void updateProduct_whenGivenDTO() {
    Productdto input = new Productdto();
    input.setBrand(2L);
    input.setProductName("Barong");
    input.setCategory("Outerwear");
    Long productId = 1L;

    Brand initialBrand = new Brand();
    initialBrand.setBrandID(1L);
    initialBrand.setBrandName("Blueshop");

    Product initialProduct = new Product();
    initialProduct.setproductId(1L);
    initialProduct.setproductName("Polo");
    initialProduct.setBrand(initialBrand);
    initialProduct.setCategory("Outerwear");

    Brand newBrand = new Brand();
    newBrand.setBrandID(2L);
    newBrand.setBrandName("Well-off");

    Product newProduct = new Product();
    newProduct.setproductId(1L);
    newProduct.setproductName("Barong");
    newProduct.setBrand(newBrand);
    newProduct.setCategory("Outerwear");

    when(productRepository.save(any(Product.class))).thenReturn(newProduct);
    when(productRepository.findById(1L)).thenReturn(Optional.of(initialProduct));
    when(brandService.findBrandbyId(2L)).thenReturn(newBrand);

    ProductResponseDTO response = productService.update(productId, input);

    assertNotNull(response.getProductId());
    assertEquals("Barong", response.getProductName());
    assertEquals("Well-off", response.getBrandName());
    assertEquals("Outerwear", response.getCategory());
  }

}
