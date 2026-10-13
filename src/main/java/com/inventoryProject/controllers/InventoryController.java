package com.inventoryProject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.services.InventoryService;
import com.inventoryProject.dto.InventoryRequestDTO;
import com.inventoryProject.models.Inventory;
import com.inventoryProject.models.InventoryId;

/**
 * InventoryController
 */

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  /**
   * @param variantId
   *                  This API takes an existing variantId, and returns a list of
   *                  all inventory entities
   *                  that are associated with that variantId that have more than
   *                  0 in stock.
   *                  Pre-conditions: variantId exists
   *                  Post-conditions: List of inventory entities associated with
   *                  variantId
   *                  Invariants: List is read-only, not changed.
   *
   * @return
   */
  @GetMapping("/variant/{variantId}")
  public ResponseEntity<List<Inventory>> getVariantStock(@PathVariable Long variantId) {
    return ResponseEntity.ok(inventoryService.getVariantStocks(variantId, 0));
  }

  /**
   * @param requestDTO
   *
   *                   This API takes a log object and changes the count in
   *                   relation to the passed change.
   *                   Currently: requestDTO is not a valid thing right now.
   *                   UNIMPLEMENTED.
   * @return new Saved change
   */
  @PostMapping("/log")
  public ResponseEntity<Inventory> logInventory(@RequestBody InventoryRequestDTO requestDTO) {
    InventoryId id = new InventoryId();
    // need to somehow query the InventoryID, introduces some
    // coupling. WIll need an inventoryService that builds the InventoryId, cause I
    // don't
    // want it here

    return ResponseEntity.ok(inventoryService.logInventory(10, id));

  }

}
