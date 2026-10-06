package com.inventoryProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.InventoryId;
import com.inventoryProject.models.ProductVariant;

/**
 * InventoryRepository
 */
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

  List<Inventory> findByVariantIdAndCountGreaterThan(ProductVariant variantId, Integer count);
}
