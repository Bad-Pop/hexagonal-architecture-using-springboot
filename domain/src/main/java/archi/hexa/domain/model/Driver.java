package archi.hexa.domain.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class Driver {
  @With String number;
  String firstName;
  String lastName;
}
