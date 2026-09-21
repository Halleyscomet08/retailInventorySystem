package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.InventoryId;

/**
 * InventoryRepository
 */
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

}
