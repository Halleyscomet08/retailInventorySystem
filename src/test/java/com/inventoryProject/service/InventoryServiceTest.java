package com.inventoryProject.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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
import com.inventoryProject.models.InventoryId;
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
  void logInventory_negativeChange_returnsNewCount() {
    // Arrange
    Store store = new Store();
    store.setStoreId(1L);
    ProductVariant variant = new ProductVariant();

    variant.setVariantId(1L);
    InventoryId id = new InventoryId(store, variant);
    Inventory inventory = new Inventory();
    inventory.setVariantId(variant);
    inventory.setStoreId(store);
    inventory.setCount(6);
    int change = -4;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariantId(variant);
    finalInventory.setStoreId(store);
    finalInventory.setCount(2);

    // Act

    when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
    when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

    Inventory response = inventoryService.logInventory(change, id);

    verify(inventoryRepository).save(inventoryCaptor.capture());
    Inventory captured = inventoryCaptor.getValue();

    // Assert: sent was correct, response was correct
    assertEquals(2, response.getCount());
    assertEquals(2, captured.getCount());

  }

  @Test
  void logInventory_positiveChange_returnsNewCount() {
    // Arrange
    Store store = new Store();
    store.setStoreId(1L);
    ProductVariant variant = new ProductVariant();

    variant.setVariantId(1L);
    InventoryId id = new InventoryId(store, variant);
    Inventory inventory = new Inventory();
    inventory.setVariantId(variant);
    inventory.setStoreId(store);
    inventory.setCount(6);
    int change = 4;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariantId(variant);
    finalInventory.setStoreId(store);
    finalInventory.setCount(10);

    // Act

    when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
    when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

    Inventory response = inventoryService.logInventory(change, id);

    verify(inventoryRepository).save(inventoryCaptor.capture());
    Inventory captured = inventoryCaptor.getValue();

    // Assert: sent was correct, response was correct
    assertEquals(10, response.getCount());
    assertEquals(10, captured.getCount());

  }

  @Test
  void logInventory_negativeInventory_throwsException() {
    // Arrange
    Store store = new Store();
    store.setStoreId(1L);
    ProductVariant variant = new ProductVariant();

    variant.setVariantId(1L);
    InventoryId id = new InventoryId(store, variant);
    Inventory inventory = new Inventory();
    inventory.setVariantId(variant);
    inventory.setStoreId(store);
    inventory.setCount(6);
    int change = -8;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariantId(variant);
    finalInventory.setStoreId(store);
    finalInventory.setCount(10);

    when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

    doThrow(FieldsNotValidException.class).when(requestValidator).validateRequest(any());

    // Act
    // Assert
    assertThrows(FieldsNotValidException.class, () -> {
      inventoryService.logInventory(change, id);
    });
    verify(inventoryRepository, times(0)).save(inventory);
    verifyNoMoreInteractions(inventoryRepository);

  }

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
