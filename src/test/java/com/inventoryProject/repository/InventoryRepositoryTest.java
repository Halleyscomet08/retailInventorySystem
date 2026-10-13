package com.inventoryProject.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.inventoryProject.models.Brand;
import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Store;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.repositories.InventoryRepository;
import com.inventoryProject.repositories.ProductRepository;
import com.inventoryProject.repositories.ProductVariantRepository;
import com.inventoryProject.repositories.StoreRepository;

import jakarta.transaction.Transactional;

/**
 * InventoryRepositoryTest
 */
@DataJpaTest
public class InventoryRepositoryTest {

  @Autowired
  InventoryRepository inventoryRepository;

  @Autowired
  ProductVariantRepository productVariantRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  BrandRepository brandRepository;
  @Autowired
  StoreRepository storeRepository;
  @Autowired
  TestEntityManager testEntityManager;

  Inventory savedInventory1;
  Inventory savedInventory2;
  Inventory savedInventory3;
  Inventory savedInventory4;
  Inventory savedInventory5;
  ProductVariant savedVariant1;
  ProductVariant savedVariant2;

  @BeforeEach
  void setUp() {

    Brand brand1 = new Brand();
    brand1.setBrandName("Nike");
    Brand brand2 = new Brand();
    brand2.setBrandName("Adidas");

    Brand savedBrand1 = brandRepository.save(brand1);
    Brand savedBrand2 = brandRepository.save(brand2);
    Product product1 = new Product();
    product1.setproductName("Shoe");
    product1.setBrand(savedBrand1);
    Product product2 = new Product();
    product2.setproductName("Shoes");
    product2.setBrand(savedBrand2);

    Product savedProduct1 = productRepository.save(product1);
    Product savedProduct2 = productRepository.save(product2);

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

    Store store1 = new Store();
    store1.setStoreLocation("SM");
    Store savedStore1 = storeRepository.save(store1);

    Store store2 = new Store();
    store2.setStoreLocation("Ayala");
    Store savedStore2 = storeRepository.save(store2);
    Store store3 = new Store();
    store3.setStoreLocation("Robinson's");
    Store savedStore3 = storeRepository.save(store3);

    Inventory inventory1 = new Inventory();
    inventory1.setVariant(savedVariant1);
    inventory1.setStore(savedStore1);
    inventory1.setCount(6);
    savedInventory1 = inventoryRepository.save(inventory1);
    Inventory inventory2 = new Inventory();
    inventory2.setVariant(savedVariant1);
    inventory2.setStore(savedStore2);
    inventory2.setCount(7);
    savedInventory2 = inventoryRepository.save(inventory2);
    Inventory inventory3 = new Inventory();
    inventory3.setVariant(savedVariant1);
    inventory3.setStore(savedStore3);
    inventory3.setCount(0);
    savedInventory3 = inventoryRepository.save(inventory3);
    Inventory inventory4 = new Inventory();
    inventory4.setVariant(savedVariant2);
    inventory4.setStore(savedStore1);
    inventory4.setCount(6);
    savedInventory4 = inventoryRepository.save(inventory4);
    Inventory inventory5 = new Inventory();
    inventory5.setVariant(savedVariant2);
    inventory5.setStore(savedStore2);
    inventory5.setCount(6);
    savedInventory5 = inventoryRepository.save(inventory5);

    testEntityManager.flush();
    testEntityManager.clear();
  }

  @Test
  void findByVariantID_givenValidIdAndBoundary_returnsList() {
    List<Inventory> response = inventoryRepository.findByVariantIdAndCountGreaterThan(savedVariant1, 0);

    System.out.println(response);
    assertTrue(response.stream().allMatch(n -> (n.getVariantId().equals(savedVariant1.getVariantId()))));
    assertTrue(response.stream().allMatch(n -> (n.getCount() > 0)));

  }

  @Transactional
  @AfterEach
  public void tearDown() {
    inventoryRepository.delete(savedInventory1);
    inventoryRepository.delete(savedInventory2);
    inventoryRepository.delete(savedInventory3);
    inventoryRepository.delete(savedInventory4);
    inventoryRepository.delete(savedInventory5);
    productVariantRepository.delete(savedVariant1);
    productVariantRepository.delete(savedVariant2);
    testEntityManager.flush();
    testEntityManager.clear();
  }

}
