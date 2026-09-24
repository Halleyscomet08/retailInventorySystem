package com.inventoryProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventoryProject.models.EntityMode;
import com.inventoryProject.models.Product;

/**
 * StockRepository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  public List<Product> findByBrand_BrandId(Long brandId);

  @Query("SELECT b FROM Brand b WHERE b.status = :status")
  public List<Product> findAllByStatus(@Param("status") EntityMode status);

}
