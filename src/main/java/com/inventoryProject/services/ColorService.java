package com.inventoryProject.services;

import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Color;
import com.inventoryProject.repositories.ColorRepository;

/**
 * ColorService
 */
public class ColorService {

  private final ColorRepository colorRepository;

  public ColorService(ColorRepository colorRepository) {
    this.colorRepository = colorRepository;
  }

  public Color create(String label) {

    if (label == null || label.trim().isEmpty()) {

      throw new IllegalArgumentException(ExceptionMessages.LABEL_REQUIRED);

    }
    Color input = new Color();
    input.setColorLabel(label);

    Color response = colorRepository.save(input);

    return response;
  };

  public Color find(Long colorId) {
    Color response = colorRepository.findById(colorId)
        .orElseThrow(() -> {
          throw new ResourceNotFoundException(ExceptionMessages.COLOR_NOT_FOUND);
        });
    return response;
  }

}
