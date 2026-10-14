package com.inventoryProject.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.variants.ProductVariant;

/**
 * InventoryRepository
 */
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

  List<Inventory> findByVariantIdAndCountGreaterThan(ProductVariant variantId, Integer count);
}
