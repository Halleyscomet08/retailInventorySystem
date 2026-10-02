package com.inventoryProject.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventoryProject.models.EntityMode;
import com.inventoryProject.models.Product;
import com.inventoryProject.models.ProductVariant;

/**
 * ProductVariantRepository
 */
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

  @Modifying(clearAutomatically = true)
  @Query("UPDATE ProductVariant SET archivedAt = CURRENT_DATE WHERE productId = :productId AND archivedAt IS NULL")
  public int archiveByProductId(@Param("productId") Product productId);

  public List<ProductVariant> findProductVariantByarchivedAt(@Param("archivedAt") LocalDate archivedAt);
}
