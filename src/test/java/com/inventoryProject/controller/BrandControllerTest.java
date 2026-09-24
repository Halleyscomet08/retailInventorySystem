package com.inventoryProject.controller;

import com.inventoryProject.controllers.BrandController;
import com.inventoryProject.dto.BrandDTO;
import com.inventoryProject.services.BrandService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * BrandController
 */

@WebMvcTest(BrandController.class)
public class BrandControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  BrandService brandService;

  @Test
  void getBrand_givenID() throws Exception {
    BrandDTO brand = new BrandDTO();
    brand.setBrandName("Blueshop");
    when(brandService.get(1L)).thenReturn(brand);

    mockMvc.perform(get("/api/brand/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brandName").value("Blueshop"));

  }

}
