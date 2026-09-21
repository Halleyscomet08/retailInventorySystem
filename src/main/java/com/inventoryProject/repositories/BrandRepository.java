package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Brand;

/**
 * BrandRepository
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
