package com.inventoryProject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Product;

/**
 * StockRepository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  public List<Product> findByBrand_BrandId(Long brandId);

}
