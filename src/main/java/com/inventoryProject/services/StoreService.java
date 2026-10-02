package com.inventoryProject.services;

import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Store;
import com.inventoryProject.repositories.StoreRepository;

/**
 * StoreService
 */
public class StoreService {

  private final StoreRepository storeRepository;

  public StoreService(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  public Store get(Long storeId) {
    return storeRepository.findById(storeId)
        .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.STORE_NOT_FOUND));

  }
}
