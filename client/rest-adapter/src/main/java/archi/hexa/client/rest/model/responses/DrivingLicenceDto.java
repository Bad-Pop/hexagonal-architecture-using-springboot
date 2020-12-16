package archi.hexa.client.rest.model.responses;

import archi.hexa.domain.model.DrivingLicence;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class DrivingLicenceDto {

  String number;
  LocalDate deliveryDate;
  LocalDate expirationDate;
  boolean active;
  int points;
  DriverDto driver;
  List<OffenceDto> offences;

  public static DrivingLicenceDto from(final DrivingLicence drivingLicence) {
    return DrivingLicenceDto.builder()
        .number(drivingLicence.getNumber())
        .deliveryDate(drivingLicence.getDeliveryDate())
        .expirationDate(drivingLicence.getExpirationDate())
        .active(drivingLicence.isActive())
        .points(drivingLicence.getPoints())
        .driver(DriverDto.from(drivingLicence.getDriver()))
        .offences(drivingLicence.getOffences().map(OffenceDto::from).toJavaList())
        .build();
  }
}
