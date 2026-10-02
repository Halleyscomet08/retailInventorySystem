package com.inventoryProject.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.inventoryProject.exception.FieldsNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * RequestValidator
 *
 * This acts as a wrapper for a validator, that essentially tracks
 * violations done by an input. Use this to throw validation exceptions early.
 */
@Component
public class RequestValidator {

  private final Validator validator;

  public RequestValidator(Validator validator) {
    this.validator = validator;
  }

  public <T> void validateRequest(T request) {

    Set<ConstraintViolation<T>> violations = validator.validate(request);

    if (!violations.isEmpty()) {

      List<String> messages = new ArrayList<String>();

      for (ConstraintViolation<T> violation : violations) {
        messages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());

      }
      throw new FieldsNotValidException("Fields not valid", messages);

    }
  }
}
