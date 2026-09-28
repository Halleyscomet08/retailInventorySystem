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
import com.inventoryProject.models.Color;
import com.inventoryProject.repositories.ColorRepository;
import com.inventoryProject.services.ColorService;

/**
 * ColorServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class ColorServiceTest {

  @Mock
  ColorRepository colorRepository;

  @InjectMocks
  ColorService colorService;

  @Captor
  ArgumentCaptor<Color> colorCaptor;

  @Test
  void create_validLabel_returnsColor() {

    String label = "Red";

    Color color = new Color();
    color.setColorId(1L);
    color.setColorLabel("Red");

    when(colorRepository.save(any(Color.class))).thenReturn(color);

    Color response = colorService.create(label);

    verify(colorRepository).save(colorCaptor.capture());

    Color captured = colorCaptor.getValue();

    assertNull(captured.getColorId());
    assertEquals("Red", captured.getColorLabel());
    assertEquals("Red", response.getColorLabel());
  }

  @Test
  void create_nullLabel_throwsException() {
    String label = null;

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      colorService.create(label);
    });
    assertEquals("Label field must not be null or blank", exception.getMessage());

  }

  @Test
  void create_blankLabel_throwsException() {
    String label = "      ";

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      colorService.create(label);
    });
    assertEquals("Label field must not be null or blank", exception.getMessage());

  }

  @Test
  void find_validId_returnsColor() {
    Long query = 1L;

    Color color = new Color();
    color.setColorId(1L);
    color.setColorLabel("Red");

    when(colorRepository.findById(query)).thenReturn(Optional.of(color));

    Color response = colorService.find(query);

    assertNotNull(response.getColorId());
    assertEquals("Red", response.getColorLabel());
  }

  @Test
  void find_invalidId_throwsException() {
    Long query = 1L;

    when(colorRepository.findById(query)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      colorService.find(query);
    });
    assertEquals("Requested Color does not exist", exception.getMessage());

  }

}
