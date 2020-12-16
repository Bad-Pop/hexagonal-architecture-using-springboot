package archi.hexa.domain.model;

import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

import static io.vavr.API.Seq;

@Value
@Builder
public class DrivingLicence {
  @With String number;
  LocalDate deliveryDate;
  LocalDate expirationDate;
  Driver driver;
  @With @Default boolean active = true;
  @With @Default int points = 12;
  @With @Default Seq<Offence> offences = Seq();
}
