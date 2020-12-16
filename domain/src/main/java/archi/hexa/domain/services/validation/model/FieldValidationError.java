package archi.hexa.domain.services.validation.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FieldValidationError {
  String field;
  String message;
}
