package com.inventoryProject.services;

import com.inventoryProject.dto.InventoryRequestDTO;
import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Store;
import com.inventoryProject.repositories.InventoryRepository;
import com.inventoryProject.validation.RequestValidator;

/**
 * InventoryService
 */
@SuppressWarnings("unused")
public class InventoryService {

  private final VariantService variantService;
  private final StoreService storeService;
  private final InventoryRepository inventoryRepository;
  private final RequestValidator requestValidator;

  public InventoryService(RequestValidator requestValidator, VariantService variantService, StoreService storeService,
      InventoryRepository inventoryRepository) {
    this.requestValidator = requestValidator;
    this.variantService = variantService;
    this.storeService = storeService;
    this.inventoryRepository = inventoryRepository;
  }

  public Inventory create(InventoryRequestDTO inventory) {
    // what the fuck does the input for this even look like
    // NEW DTO TIME MOTHER FUCKERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
    // Validate:
    requestValidator.validateRequest(inventory);
    // Query Variant and Store
    ProductVariant variant = variantService.find(inventory.getVariantId());
    Store store = storeService.get(inventory.getVariantId());
    // Build Inventory -- I have no idea how to do this given a serialized ID
    Inventory finalInventory = new Inventory();
    finalInventory.setStoreId(store);
    finalInventory.setVariantId(variant);
    finalInventory.setCount(inventory.getCount());
    // Save Inventory
    Inventory response = inventoryRepository.save(finalInventory);
    // Return Inventory
    return response;
  }

  void editInventory(int change) {
  }
}
