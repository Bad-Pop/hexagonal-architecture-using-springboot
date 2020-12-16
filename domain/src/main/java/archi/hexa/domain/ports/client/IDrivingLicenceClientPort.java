package archi.hexa.domain.ports.client;

import archi.hexa.domain.model.DrivingLicence;
import archi.hexa.domain.services.validation.model.FieldValidationError;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public interface IDrivingLicenceClientPort {

  /**
   * params._1 -> firstname
   *
   * <p>params._2 -> lastname
   */
  DrivingLicence create(final Tuple2<String, String> params);

  int getCurrentPoints(final String licenceNumber);

  /**
   * params._1 -> driving licence number
   *
   * <p>params._2 -> offence cause
   */
  DrivingLicence removePoints(final Tuple2<String, String> params);

  /**
   * params._1 -> licence number
   *
   * <p>params._2 -> points to add
   */
  DrivingLicence addPoints(final Tuple2<String, Integer> params);

  DrivingLicence cancel(final String licenceNumber);

  Validation<FieldValidationError, String> validateLicenceNumber(final String licenceNumber);

  Validation<Seq<FieldValidationError>, Tuple2<String, String>> validateFirstAndLastName(
      final String firstName, final String lastName);
}
