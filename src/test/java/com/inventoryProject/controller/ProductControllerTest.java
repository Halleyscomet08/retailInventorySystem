package com.inventoryProject.controller;

import com.inventoryProject.controllers.ProductController;
import com.inventoryProject.services.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * BrandController
 */

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  ProductService productService;

  @Test
  void archiveProduct_givenID() throws Exception {
    doNothing().when(productService).archiveProduct(1L);

    mockMvc.perform(patch("/api/product/1/archive"))
        .andExpect(status().isOk());

    verify(productService, times(1)).archiveProduct(1L);
  }

}
