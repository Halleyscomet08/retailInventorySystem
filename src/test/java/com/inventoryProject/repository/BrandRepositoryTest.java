package com.inventoryProject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inventoryProject.models.Brand;
import com.inventoryProject.models.EntityMode;
import com.inventoryProject.repositories.BrandRepository;
import com.inventoryProject.services.BrandService;

/**
 * BrandRepositoryTest
 */
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class BrandRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  private Brand savedbrand1;
  private Brand savedbrand2;

  @BeforeEach
  public void setUp() {
    Brand brand1 = new Brand();
    brand1.setBrandName("Nike");
    Brand brand2 = new Brand();
    brand2.setBrandName("Adidas");

    savedbrand1 = brandRepository.save(brand1);
    savedbrand2 = brandRepository.save(brand2);
  }

  @Test
  void findBrandByStatus_Active_returnActive() {

    savedbrand2.setStatus(EntityMode.ARCHIVED);
    brandRepository.save(savedbrand2);

    List<Brand> query = brandRepository.findBrandByStatus(EntityMode.ACTIVE);

    assertEquals(1, query.size());
    assertEquals(1L, query.get(0).getBrandID());

  }

}
