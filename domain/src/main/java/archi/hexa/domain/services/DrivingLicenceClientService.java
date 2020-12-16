package archi.hexa.domain.services;

import archi.hexa.domain.exceptions.LicenceNotFoundException;
import archi.hexa.domain.exceptions.ValidationException;
import archi.hexa.domain.model.Driver;
import archi.hexa.domain.model.DrivingLicence;
import archi.hexa.domain.model.Offence;
import archi.hexa.domain.model.Offence.OffenceCause;
import archi.hexa.domain.ports.client.IDrivingLicenceClientPort;
import archi.hexa.domain.ports.server.IDrivingLicenceServerPort;
import archi.hexa.domain.services.generators.DriverNumberGenerator;
import archi.hexa.domain.services.generators.DrivingLicenceNumberGenerator;
import archi.hexa.domain.services.generators.OffenceNumberGenerator;
import archi.hexa.domain.services.validation.model.FieldValidationError;
import archi.hexa.domain.services.validation.validators.LicenceNumberValidator;
import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.val;

import java.time.LocalDate;

import static archi.hexa.domain.services.validation.validators.StringValidator.validateStringIsNotBlank;
import static io.vavr.API.Seq;
import static io.vavr.control.Validation.combine;

@AllArgsConstructor
public class DrivingLicenceClientService implements IDrivingLicenceClientPort {

  private final IDrivingLicenceServerPort drivingLicenceAdapter;

  @Override
  public DrivingLicence create(final Tuple2<String, String> params) {

    val now = LocalDate.now();
    return validateFirstAndLastName(params._1, params._2)
        .map(
            validParams ->
                DrivingLicence.builder()
                    .deliveryDate(now)
                    .expirationDate(now.plusYears(10L))
                    .driver(
                        Driver.builder()
                            .firstName(validParams._1)
                            .lastName(validParams._2)
                            .build()
                            .withNumber(DriverNumberGenerator.generate()))
                    .build()
                    .withNumber(DrivingLicenceNumberGenerator.generate()))
        .peek(drivingLicenceAdapter::saveDrivingLicence)
        .mapError(ValidationException::new)
        .mapError(this::throwOnValidationException)
        .getOrElse(this::throwUnknownInternalError);
  }

  @Override
  public int getCurrentPoints(final String licenceNumber) {
    return validateLicenceNumber(licenceNumber)
        .map(drivingLicenceAdapter::findDrivingLicenceByNumber)
        .map(
            maybeLicence ->
                maybeLicence
                    .map(DrivingLicence::getPoints)
                    .getOrElseThrow(
                        () ->
                            new LicenceNotFoundException("This driving licence does not exist !")))
        .mapError(error -> new ValidationException(Seq(error)))
        .mapError(this::throwOnValidationException)
        .getOrElseThrow(() -> new RuntimeException("Unknown internal error"));
  }

  @Override
  public DrivingLicence removePoints(final Tuple2<String, String> params) {

    val offenceCause = OffenceCause.find(params._2);
    return validateLicenceNumber(params._1)
        .map(drivingLicenceAdapter::findDrivingLicenceByNumber)
        .map(
            maybeDrivingLicence ->
                maybeDrivingLicence
                    .map(
                        drivingLicence ->
                            drivingLicence
                                .withOffences(
                                    List.ofAll(drivingLicence.getOffences())
                                        .append(
                                            Offence.builder()
                                                .cause(offenceCause)
                                                .pointsCost(offenceCause.getPointCost())
                                                .offenceDate(LocalDate.now())
                                                .build()
                                                .withNumber(OffenceNumberGenerator.generate())))
                                .withPoints(
                                    drivingLicence.getPoints() - offenceCause.getPointCost()))
                    .getOrElseThrow(
                        () ->
                            new LicenceNotFoundException("This driving licence does not exist !")))
        .map(
            drivingLicence ->
                (drivingLicence.getPoints() < 1)
                    ? cancel(drivingLicence.getNumber())
                    : drivingLicenceAdapter.saveDrivingLicence(drivingLicence))
        .mapError(error -> new ValidationException(Seq(error)))
        .mapError(this::throwOnValidationException)
        .getOrElse(this::throwUnknownInternalError);
  }

  @Override
  public DrivingLicence addPoints(final Tuple2<String, Integer> params) {
    return validateLicenceNumber(params._1)
        .map(drivingLicenceAdapter::findDrivingLicenceByNumber)
        .map(
            maybeDrivingLicence ->
                maybeDrivingLicence
                    .map(
                        drivingLicence ->
                            drivingLicence.withPoints(
                                Math.min(drivingLicence.getPoints() + params._2, 12)))
                    .getOrElseThrow(
                        () ->
                            new LicenceNotFoundException("This driving licence does not exist !")))
        .peek(drivingLicenceAdapter::saveDrivingLicence)
        .mapError(error -> new ValidationException(Seq(error)))
        .mapError(this::throwOnValidationException)
        .getOrElse(this::throwUnknownInternalError);
  }

  @Override
  public DrivingLicence cancel(final String licenceNumber) {
    return validateLicenceNumber(licenceNumber)
        .map(drivingLicenceAdapter::findDrivingLicenceByNumber)
        .map(
            maybeDrivingLicence ->
                maybeDrivingLicence
                    .map(drivingLicence -> drivingLicence.withActive(false).withPoints(0))
                    .getOrElseThrow(
                        () ->
                            new LicenceNotFoundException("This driving licence does not exist !")))
        .peek(drivingLicenceAdapter::saveDrivingLicence)
        .mapError(error -> new ValidationException(Seq(error)))
        .mapError(this::throwOnValidationException)
        .getOrElse(this::throwUnknownInternalError);
  }

  @Override
  public Validation<FieldValidationError, String> validateLicenceNumber(
      final String licenceNumber) {
    return LicenceNumberValidator.validateLicenceNumber(licenceNumber);
  }

  @Override
  public Validation<Seq<FieldValidationError>, Tuple2<String, String>> validateFirstAndLastName(
      final String firstName, final String lastName) {
    return combine(
            validateStringIsNotBlank(firstName, "firstName"),
            validateStringIsNotBlank(lastName, "lastName"))
        .ap(API::Tuple);
  }

  private Exception throwOnValidationException(final ValidationException validationException) {
    throw validationException;
  }

  private DrivingLicence throwUnknownInternalError() {
    throw new RuntimeException("Unknown internal error");
  }
}
