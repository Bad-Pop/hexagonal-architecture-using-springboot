package archi.hexa.server.postgres.repositories;

import archi.hexa.server.postgres.entities.DrivingLicenceEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingLicenceRepository extends JpaRepository<DrivingLicenceEntity, Long> {

  Option<DrivingLicenceEntity> findByNumber(final String number);
}
