package archi.hexa.bootstrap.config.domain;

import archi.hexa.client.rest.model.responses.ErrorDto;
import archi.hexa.domain.exceptions.LicenceNotFoundException;
import archi.hexa.domain.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(NO_CONTENT)
  @ExceptionHandler(LicenceNotFoundException.class)
  public ResponseEntity<ErrorDto> handleException(final LicenceNotFoundException lnfe) {
    return ResponseEntity.status(NO_CONTENT)
        .body(ErrorDto.builder().error(lnfe.getMessage()).stackTrace(lnfe.toString()).build());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorDto> handleException(final ValidationException ve) {
    return ResponseEntity.status(BAD_REQUEST)
        .body(
            ErrorDto.builder()
                .error(ve.getErrors().toJavaList().toString())
                .stackTrace(ve.toString())
                .build());
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDto> handleException(final RuntimeException re) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(ErrorDto.builder().error(re.getMessage()).stackTrace(re.toString()).build());
  }
}
