package com.inventoryProject.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.InventoryRequestDTO;
import com.inventoryProject.exception.FieldsNotValidException;
import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.ProductVariant;
import com.inventoryProject.models.Store;
import com.inventoryProject.repositories.InventoryRepository;
import com.inventoryProject.services.InventoryService;
import com.inventoryProject.services.StoreService;
import com.inventoryProject.services.VariantService;
import com.inventoryProject.validation.RequestValidator;

/**
 * InventoryServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

  @Mock
  VariantService variantService;

  @Mock
  StoreService storeService;

  @Mock
  InventoryRepository inventoryRepository;

  @Mock
  RequestValidator requestValidator;

  @InjectMocks
  InventoryService inventoryService;

  @Captor
  ArgumentCaptor<Inventory> inventoryCaptor;

  @Test
  void createInventory_whenGivenDTO() {

    Store store = new Store();
    store.setStoreId(1L);
    ProductVariant variant = new ProductVariant();
    variant.setVariantId(1L);

    Inventory inventory = new Inventory();
    inventory.setStoreId(store);
    inventory.setVariantId(variant);
    inventory.setCount(100);

    InventoryRequestDTO request = new InventoryRequestDTO();
    request.setVariantId(1L);
    request.setBrandId(1L);
    request.setCount(100);

    when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
    when(variantService.find(1L)).thenReturn(variant);
    when(storeService.get(1L)).thenReturn(store);

    Inventory response = inventoryService.create(request);
    verify(inventoryRepository).save(inventoryCaptor.capture());

    Inventory captured = inventoryCaptor.getValue();

    assertEquals(variant, captured.getVariantId());
    assertEquals(store, captured.getStoreId());
    assertEquals(100, captured.getCount());

    assertNotNull(response.getVariantId());
    assertNotNull(response.getStoreId());
    assertEquals(variant, response.getVariantId());
    assertEquals(store, response.getStoreId());
    assertEquals(100, response.getCount());

  }

  @Test
  void create_givenBlank_throwException() {
    InventoryRequestDTO badRequest = new InventoryRequestDTO();

    doThrow(FieldsNotValidException.class).when(requestValidator).validateRequest(any());

    assertThrows(FieldsNotValidException.class, () -> {
      inventoryService.create(badRequest);
    });
    verifyNoInteractions(inventoryRepository);
  }

}
