package com.inventoryProject.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Size;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.SizeRepository;
import com.inventoryProject.repositories.SizeRepository;
import com.inventoryProject.services.SizeService;
import com.inventoryProject.services.SizeService;

/**
 * SizeServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class SizeServiceTest {

  @Mock
  SizeRepository sizeRepository;

  @InjectMocks
  SizeService sizeService;

  @Captor
  ArgumentCaptor<Size> sizeCaptor;

  @Test
  void create_validLabel_returnsSize() {

    String label = "XL";

    Size size = new Size();
    size.setSizeId(1L);
    size.setSizeLabel("XL");

    when(sizeRepository.save(any(Size.class))).thenReturn(size);

    Size response = sizeService.create(label);

    verify(sizeRepository).save(sizeCaptor.capture());

    Size captured = sizeCaptor.getValue();

    assertNull(captured.getSizeId());
    assertEquals("XL", captured.getSizeLabel());
    assertEquals("XL", response.getSizeLabel());
  }

  @Test
  void create_nullLabel_throwsException() {
    String label = null;

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      sizeService.create(label);
    });
    assertEquals("Label field must not be null or blank", exception.getMessage());

  }

  @Test
  void create_blankLabel_throwsException() {
    String label = "      ";

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      sizeService.create(label);
    });
    assertEquals("Label field must not be null or blank", exception.getMessage());

  }

  @Test
  void find_validId_returnsSize() {
    Long query = 1L;

    Size size = new Size();
    size.setSizeId(1L);
    size.setSizeLabel("XL");

    when(sizeRepository.findById(query)).thenReturn(Optional.of(size));

    Size response = sizeService.find(query);

    assertNotNull(response.getSizeId());
    assertEquals("XL", response.getSizeLabel());
  }

  @Test
  void find_invalidId_throwsException() {
    Long query = 1L;

    when(sizeRepository.findById(query)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      sizeService.find(query);
    });
    assertEquals("Requested Size does not exist", exception.getMessage());

  }

}
