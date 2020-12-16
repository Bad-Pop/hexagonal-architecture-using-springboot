package archi.hexa.client.rest.model.responses;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
  String error;
  String stackTrace;
}
