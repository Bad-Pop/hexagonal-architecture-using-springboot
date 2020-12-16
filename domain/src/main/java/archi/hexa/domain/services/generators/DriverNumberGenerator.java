package archi.hexa.domain.services.generators;

import java.util.UUID;

public interface DriverNumberGenerator {

  /** Generate a new driver number as UUID */
  static String generate() {
    return UUID.randomUUID().toString();
  }
}
