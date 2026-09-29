package com.inventoryProject.services;

import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.ResourceNotFoundException;
import com.inventoryProject.models.Size;
import com.inventoryProject.repositories.SizeRepository;

/**
 * SizeService
 */
public class SizeService {

  private final SizeRepository sizeRepository;

  public SizeService(SizeRepository sizeRepository) {
    this.sizeRepository = sizeRepository;
  }

  public Size create(String label) {

    if (label == null || label.trim().isEmpty()) {

      throw new IllegalArgumentException(ExceptionMessages.LABEL_REQUIRED);

    }
    Size input = new Size();
    input.setSizeLabel(label);

    Size response = sizeRepository.save(input);

    return response;
  };

  public Size find(Long sizeId) {
    Size response = sizeRepository.findById(sizeId)
        .orElseThrow(() -> {
          throw new ResourceNotFoundException(ExceptionMessages.SIZE_NOT_FOUND);
        });
    return response;
  }
}
