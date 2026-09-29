package com.inventoryProject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Brand;
import com.inventoryProject.models.Color;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.repositories.ColorRepository;
import com.inventoryProject.repositories.ProductRepository;
import com.inventoryProject.repositories.ProductVariantRepository;
import com.inventoryProject.repositories.SizeRepository;

/**
 * VariantRepositoryTest
 */
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class VariantRepositoryTest {

  @Autowired
  ProductVariantRepository productVariantRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  BrandRepository brandRepository;
  @Autowired
  ColorRepository colorRepository;
  @Autowired
  SizeRepository sizeRepository;

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

    Color color1 = new Color();
    color1.setColorLabel("Red");
    Color savedColor1 = colorRepository.save(color1);
    Size size1 = new Size();
    size1.setSizeLabel("S");
    Size savedSize1 = sizeRepository.save(size1);

    ProductVariant variant1 = new ProductVariant();
    variant1.setProductId(savedProduct1);
    variant1.setColorId(savedColor1);
    variant1.setSizeId(savedSize1);
    variant1.setPrice(BigDecimal.valueOf(10));
    ProductVariant variant2 = new ProductVariant();
    variant2.setProductId(savedProduct2);
    variant2.setColorId(savedColor1);
    variant2.setSizeId(savedSize1);
    variant2.setPrice(BigDecimal.valueOf(10));

    savedVariant1 = productVariantRepository.save(variant1);
    savedVariant2 = productVariantRepository.save(variant2);
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

}
