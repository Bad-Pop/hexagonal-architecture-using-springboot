package archi.hexa.domain.exceptions;

import archi.hexa.domain.services.validation.model.FieldValidationError;
import io.vavr.collection.Seq;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

  private final Seq<FieldValidationError> errors;

  @Override
  public String toString() {
    return errors.toJavaList().toString()
        + "\n Caused by : "
        + Arrays.toString(this.getStackTrace());
  }
}
