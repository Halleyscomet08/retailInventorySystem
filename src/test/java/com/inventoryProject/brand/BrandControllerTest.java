package com.inventoryProject.brand;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
  void get_validId_returnBrand() throws Exception {
    Brand brand = new Brand();
    brand.setBrandID(1L);

    brand.setBrandName("Blueshop");
    when(brandService.get(1L)).thenReturn(brand);

    mockMvc.perform(get("/api/brand/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brandName").value("Blueshop"));

  }

}
