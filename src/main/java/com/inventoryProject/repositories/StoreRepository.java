package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Store;

/**
 * StoreRepository
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

}
