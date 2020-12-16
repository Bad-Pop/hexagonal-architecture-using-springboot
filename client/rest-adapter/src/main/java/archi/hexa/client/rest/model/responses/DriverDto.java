package archi.hexa.client.rest.model.responses;

import archi.hexa.domain.model.Driver;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DriverDto {

  String number;
  String firstName;
  String lastName;

  public static DriverDto from(final Driver driver) {
    return DriverDto.builder()
        .number(driver.getNumber())
        .firstName(driver.getFirstName())
        .lastName(driver.getLastName())
        .build();
  }
}
