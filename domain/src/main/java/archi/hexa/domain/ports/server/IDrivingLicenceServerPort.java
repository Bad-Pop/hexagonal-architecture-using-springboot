package archi.hexa.domain.ports.server;

import archi.hexa.domain.model.DrivingLicence;
import io.vavr.control.Option;

public interface IDrivingLicenceServerPort {

  /** Retrieve a licence by it's number */
  Option<DrivingLicence> findDrivingLicenceByNumber(final String licenceNumber);

  /** Save or update a licence */
  DrivingLicence saveDrivingLicence(final DrivingLicence drivingLicence);
}
