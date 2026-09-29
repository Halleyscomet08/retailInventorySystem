package com.inventoryProject.exception;

import java.util.List;

/**
 * ResourceNotFoundException
 */
public class FieldsNotValidException extends ResourceNotFoundException {

  private List<String> errorMessages;

  public FieldsNotValidException(String message, List<String> errorMessages) {
    super(message);
    this.errorMessages = errorMessages;
  }

  public List<String> getErrorMessages() {
    return errorMessages;
  }

}
