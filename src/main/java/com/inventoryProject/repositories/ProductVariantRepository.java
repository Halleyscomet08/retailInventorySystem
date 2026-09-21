package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.ProductVariant;

/**
 * ProductVariantRepository
 */
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
