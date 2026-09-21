package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Product;

/**
 * StockRepository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
