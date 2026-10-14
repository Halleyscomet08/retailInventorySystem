package com.inventoryProject.variants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.inventoryProject.brand.Brand;
import com.inventoryProject.brand.BrandRepository;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.product.Product;
import com.inventoryProject.product.ProductRepository;

import jakarta.transaction.Transactional;

/**
 * VariantRepositoryTest
 */
@DataJpaTest(properties = {
})
public class VariantRepositoryTest {

  @Autowired
  ProductVariantRepository productVariantRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  BrandRepository brandRepository;
  @Autowired
  TestEntityManager testEntityManager;

  private Brand savedbrand1;
  private Brand savedbrand2;
  private Product savedProduct1;
  private Product savedProduct2;
  private ProductVariant savedVariant1;
  private ProductVariant savedVariant2;

  @BeforeEach
  public void setUp() {
    Brand brand1 = new Brand();
    brand1.setBrandName("Nike");
    Brand brand2 = new Brand();
    brand2.setBrandName("Adidas");

    savedbrand1 = brandRepository.save(brand1);
    savedbrand2 = brandRepository.save(brand2);
    Product product1 = new Product();
    product1.setproductName("Shoe");
    product1.setBrand(savedbrand1);
    Product product2 = new Product();
    product2.setproductName("Shoes");
    product2.setBrand(savedbrand2);

    savedProduct1 = productRepository.save(product1);
    savedProduct2 = productRepository.save(product2);

    ProductVariant variant1 = new ProductVariant();
    variant1.setProduct(savedProduct1);
    variant1.setColor("Red");
    variant1.setSize("XL");
    variant1.setPrice(BigDecimal.valueOf(10));
    ProductVariant variant2 = new ProductVariant();
    variant2.setProduct(savedProduct2);
    variant2.setColor("Red");
    variant2.setSize("XL");
    variant2.setPrice(BigDecimal.valueOf(10));

    savedVariant1 = productVariantRepository.save(variant1);
    savedVariant2 = productVariantRepository.save(variant2);

    testEntityManager.flush();
    testEntityManager.clear();

  }

  @Test
  void archiveByProductID_existingProduct_archivesVariant() {
    productVariantRepository.archiveByProductId(savedProduct1);

    ProductVariant finalVariant1 = productVariantRepository.findById(savedVariant1.getVariantId())
        .orElseThrow(() -> new ResourceNotFoundException("huh"));
    ProductVariant finalVariant2 = productVariantRepository.findById(savedVariant2.getVariantId())
        .orElseThrow(() -> new ResourceNotFoundException("huh"));

    assertEquals(LocalDate.now(), finalVariant1.getArchivedAt());
    assertNull(finalVariant2.getArchivedAt());
  }

  @Transactional
  @AfterEach
  public void tearDown() {
    productVariantRepository.delete(savedVariant1);
    productVariantRepository.delete(savedVariant2);
    productRepository.delete(savedProduct1);
    productRepository.delete(savedProduct2);
    brandRepository.delete(savedbrand1);
    brandRepository.delete(savedbrand2);
    testEntityManager.flush();
    testEntityManager.clear();
    System.out.println(brandRepository.count());
  }
}
