package archi.hexa.domain.services.validation.validators;

import archi.hexa.domain.services.validation.model.FieldValidationError;
import io.vavr.control.Validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

public interface LicenceNumberValidator {

  /**
   * Validate that a licence number is correctly formed.
   *
   * <p>If the licence number contains only number and has a length of 10, then it will return the
   * driving licence number
   *
   * <p>If the licence number does not match these rules, then it will return a FieldValidationError
   *
   * <p>In any case, the returned object is contained in a Vavr functional Validation
   */
  static Validation<FieldValidationError, String> validateLicenceNumber(
      final String licenceNumber) {
    return (licenceNumber.matches("[0-9]+") && licenceNumber.length() == 10)
        ? Valid(licenceNumber)
        : Invalid(
            FieldValidationError.builder()
                .field("licenceNumber")
                .message(
                    "Please make sur to enter a valid licence number. It should contain only numbers and count 10 characters")
                .build());
  }
}
