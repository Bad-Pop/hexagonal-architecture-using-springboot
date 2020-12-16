package archi.hexa.client.rest.model.responses;

import archi.hexa.domain.model.Offence;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OffenceDto {

  String number;
  String cause;
  int pointsCost;
  LocalDate offenceDate;

  public static OffenceDto from(final Offence offence) {
    return OffenceDto.builder()
        .number(offence.getNumber())
        .cause(offence.getCause().name())
        .pointsCost(offence.getPointsCost())
        .offenceDate(offence.getOffenceDate())
        .build();
  }
}
