package archi.hexa.domain.services.validation.validators;

import archi.hexa.domain.services.validation.model.FieldValidationError;
import io.vavr.API;
import io.vavr.control.Validation;

public interface StringValidator {

  /**
   * Validate that a string is not blank. It means that the string cannot be null or empty or
   * contains only whitespaces
   *
   * <p>If the string is valid, it will return the given string.
   *
   * <p>If the string is invalid, it will return a FieldValidationError
   *
   * <p>In any case, the returned object is contained in a Vavr functional Validation
   */
  static Validation<FieldValidationError, String> validateStringIsNotBlank(
      final String s, final String fieldName) {
    return (s == null || s.isBlank())
        ? API.Invalid(
            FieldValidationError.builder()
                .field(fieldName)
                .message("The given string is invalid. It should not be blank !")
                .build())
        : API.Valid(s);
  }
}
