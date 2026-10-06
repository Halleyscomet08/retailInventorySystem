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

  /**
   * 
   * Given there exists at least one store that has stock on hand for a variant,
   * when this variant is requested, then the API returns an List of Inventory
   * entries,
   * with each entry containing the store's name and on-hand quantity for the
   * requested variant.
   */
  @Test
  void getVariantStock_inventoryIdExists_returnsList() {
    // Arrange
    Store store1 = new Store();
    store1.setStoreId(1L);
    Store store2 = new Store();
    store1.setStoreId(2L);
    ProductVariant variant1 = new ProductVariant();
    variant1.setVariantId(1L);
    ProductVariant variant2 = new ProductVariant();
    variant1.setVariantId(2L);
    Inventory inventory1 = new Inventory();
    inventory1.setStore(store1);
    inventory1.setVariant(variant1);
    inventory1.setCount(5);
    Inventory inventory2 = new Inventory();
    inventory2.setStore(store1);
    inventory2.setVariant(variant1);
    inventory2.setCount(3);
    Inventory inventory3 = new Inventory();
    inventory3.setStore(store2);
    inventory3.setVariant(variant1);
    inventory3.setCount(0);
    Inventory inventory4 = new Inventory();
    inventory4.setStore(store1);
    inventory4.setVariant(variant2);
    inventory4.setCount(5);
    Inventory inventory5 = new Inventory();
    inventory5.setStore(store2);
    inventory5.setVariant(variant2);
    inventory5.setCount(5);

    // dependencies: inventoryRepository, query by ID, greater than one
    // variantRepository, query for existing variant

  }

  @Test
  void logInventory_negativeChange_returnsNewCount() {
    // Arrange
    Store store = new Store();
    store.setStoreId(1L);
    ProductVariant variant = new ProductVariant();

    variant.setVariantId(1L);
    InventoryId id = new InventoryId(store, variant);
    Inventory inventory = new Inventory();
    inventory.setVariant(variant);
    inventory.setStore(store);
    inventory.setCount(6);
    int change = -4;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariant(variant);
    finalInventory.setStore(store);
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
    inventory.setVariant(variant);
    inventory.setStore(store);
    inventory.setCount(6);
    int change = 4;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariant(variant);
    finalInventory.setStore(store);
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
    inventory.setVariant(variant);
    inventory.setStore(store);
    inventory.setCount(6);
    int change = -8;

    Inventory finalInventory = new Inventory();
    finalInventory.setVariant(variant);
    finalInventory.setStore(store);
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
    inventory.setStore(store);
    inventory.setVariant(variant);
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

    assertEquals(variant, captured.getVariant());
    assertEquals(store, captured.getStore());
    assertEquals(100, captured.getCount());

    assertNotNull(response.getVariant());
    assertNotNull(response.getStore());
    assertEquals(variant, response.getVariant());
    assertEquals(store, response.getStore());
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
