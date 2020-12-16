package archi.hexa.domain.services.generators;

import lombok.val;

import java.util.Random;

public interface DrivingLicenceNumberGenerator {

  /** Generate a new driving licence number containing 10 numbers */
  static String generate() {
    val leftLimit = 48; // numeral '0' from ascii table
    val rightLimit = 57; // numeral '9' from ascii table
    val targetStringLength = 10;
    val random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
