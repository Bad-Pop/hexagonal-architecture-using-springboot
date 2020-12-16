package archi.hexa.domain.model;

import io.vavr.collection.Seq;
import lombok.*;

import java.time.LocalDate;

import static io.vavr.API.Seq;

@Value
@Builder
public class Offence {

  @With String number;
  OffenceCause cause;
  int pointsCost;
  LocalDate offenceDate;

  @Getter
  @AllArgsConstructor
  public enum OffenceCause {
    GO_THROUGH_RED_LIGHT(3),
    EXCESSIVE_SPEED(5),
    OTHER(1);

    private static final Seq<OffenceCause> causes = Seq(values());
    private final int pointCost;

    public static OffenceCause find(String cause) {
      return causes
          .find(offenceCause -> offenceCause.name().equalsIgnoreCase(cause))
          .getOrElse(OTHER);
    }
  }
}
