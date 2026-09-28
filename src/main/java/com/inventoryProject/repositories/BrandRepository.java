package com.inventoryProject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventoryProject.models.Brand;
import com.inventoryProject.models.EntityMode;

/**
 * BrandRepository
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {

  @Query("SELECT b FROM Brand b WHERE b.status = :status")
  public List<Brand> findAllByStatus(@Param("status") EntityMode status);
}
