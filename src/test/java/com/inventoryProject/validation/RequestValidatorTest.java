package com.inventoryProject.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventoryProject.dto.VariantRequestDTO;
import com.inventoryProject.exception.ExceptionMessages;
import com.inventoryProject.exception.FieldsNotValidException;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * RequestValidatorTest
 */
@ExtendWith(MockitoExtension.class)
public class RequestValidatorTest {

  @Test
  void create_missingValues_throwsException() {
    // wrong file, move to new

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    RequestValidator requestValidator2 = new RequestValidator(validator);

    VariantRequestDTO badRequest = new VariantRequestDTO();

    FieldsNotValidException exception = assertThrows(FieldsNotValidException.class, () -> {
      requestValidator2.validateRequest(badRequest);
    });

    List<String> assertedExceptions = new ArrayList<String>();
    assertedExceptions.add("price: " + ExceptionMessages.LABEL_REQUIRED);
    assertedExceptions.add("productId: " + ExceptionMessages.LABEL_REQUIRED);
    assertedExceptions.add("sizeId: " + ExceptionMessages.LABEL_REQUIRED);
    assertedExceptions.add("colorId: " + ExceptionMessages.LABEL_REQUIRED);

    Set<String> assertString = new HashSet<String>(assertedExceptions);
    Set<String> assertResponse = new HashSet<String>(exception.getErrorMessages());

    assertEquals(assertString, assertResponse);
    assertEquals(4, exception.getErrorMessages().size());

  }
}
