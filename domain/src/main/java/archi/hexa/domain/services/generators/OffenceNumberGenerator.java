package archi.hexa.domain.services.generators;

import java.util.UUID;

public interface OffenceNumberGenerator {

  /** Generate a new offence number as UUID */
  static String generate() {
    return UUID.randomUUID().toString();
  }
}
