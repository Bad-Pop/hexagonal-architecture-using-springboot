package archi.hexa.server.postgres.adapters;

import archi.hexa.domain.model.DrivingLicence;
import archi.hexa.domain.ports.server.IDrivingLicenceServerPort;
import archi.hexa.server.postgres.entities.DrivingLicenceEntity;
import archi.hexa.server.postgres.repositories.DrivingLicenceRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DrivingLicenceAdapter implements IDrivingLicenceServerPort {

  private final DrivingLicenceRepository drivingLicenceRepository;

  @Override
  public Option<DrivingLicence> findDrivingLicenceByNumber(final String licenceNumber) {
    return drivingLicenceRepository.findByNumber(licenceNumber).map(DrivingLicenceEntity::toDomain);
  }

  @Override
  public DrivingLicence saveDrivingLicence(final DrivingLicence drivingLicence) {
    return drivingLicenceRepository.save(DrivingLicenceEntity.from(drivingLicence)).toDomain();
  }
}
