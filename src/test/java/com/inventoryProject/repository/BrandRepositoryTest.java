package com.inventoryProject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.inventoryProject.models.Brand;
import com.inventoryProject.models.EntityMode;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.services.BrandService;

import jakarta.transaction.Transactional;

/**
 * BrandRepositoryTest
 */
@DataJpaTest(properties = {
})
public class BrandRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  @Autowired
  TestEntityManager testEntityManager;

  private Brand savedbrand1;
  private Brand savedbrand2;

  @BeforeEach
  public void setUp() {
    System.out.println("Start of Brand Repository:" + brandRepository.count());
    Brand brand1 = new Brand();
    brand1.setBrandName("Nike");
    Brand brand2 = new Brand();
    brand2.setBrandName("Adidas");

    savedbrand1 = brandRepository.save(brand1);
    savedbrand2 = brandRepository.save(brand2);
    testEntityManager.flush();
    testEntityManager.clear();
  }

  @Test
  void findBrandByStatus_Active_returnActive() {

    savedbrand2.setStatus(EntityMode.ARCHIVED);
    brandRepository.save(savedbrand2);

    List<Brand> query = brandRepository.findBrandByStatus(EntityMode.ACTIVE);

    assertTrue(query.stream().noneMatch(n -> (n.getStatus() == EntityMode.ARCHIVED)));
    // assertEquals(1, query.size());
    // assertEquals(1L, query.get(0).getBrandID());

  }

  @Transactional
  @AfterEach
  public void tearDown() {
    brandRepository.delete(savedbrand1);
    brandRepository.delete(savedbrand2);
    testEntityManager.flush();
    testEntityManager.clear();
  }

}
