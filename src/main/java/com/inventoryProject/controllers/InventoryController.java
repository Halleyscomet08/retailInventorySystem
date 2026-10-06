package com.inventoryProject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryProject.services.InventoryService;
import com.inventoryProject.models.Inventory;

/**
 * InventoryController
 */

@RestController
@RequestMapping("api/inventory")
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
  @GetMapping("/{variantId}")
  public ResponseEntity<List<Inventory>> getVariantStock(@PathVariable Long variantId) {

    return ResponseEntity.ok(inventoryService.getVariants(variantId, 0));

  }

}
