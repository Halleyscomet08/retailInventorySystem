package com.inventoryProject.services;

import com.inventoryProject.dto.InventoryRequestDTO;
import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.InventoryId;
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
    requestValidator.validateRequest(inventory);

    ProductVariant variant = variantService.find(inventory.getVariantId());
    Store store = storeService.get(inventory.getVariantId());

    Inventory finalInventory = new Inventory();
    finalInventory.setStoreId(store);
    finalInventory.setVariantId(variant);
    finalInventory.setCount(inventory.getCount());

    Inventory response = inventoryRepository.save(finalInventory);

    return response;
  }

  /**
   * @param change: change number
   *
   *                Edits the specific Inventoryid's count number.
   *
   *                Preconditions: InventoryId exists, change is an int
   *                Postconditions: InventoryId's count is modified by the change
   *                Invariants: inventroy count cannot be negative.
   *
   */
  public Inventory logInventory(int change, InventoryId id) {
    Inventory inventory = inventoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.LABEL_REQUIRED));

    inventory.changeInventory(change);
    requestValidator.validateRequest(inventory);

    Inventory response = inventoryRepository.save(inventory);
    return response;

  }
}
