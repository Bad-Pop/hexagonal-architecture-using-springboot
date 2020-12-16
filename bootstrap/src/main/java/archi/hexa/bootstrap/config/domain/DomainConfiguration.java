package archi.hexa.bootstrap.config.domain;

import archi.hexa.domain.ports.client.IDrivingLicenceClientPort;
import archi.hexa.domain.ports.server.IDrivingLicenceServerPort;
import archi.hexa.domain.services.DrivingLicenceClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public IDrivingLicenceClientPort drivingLicenceClientPort(
      final IDrivingLicenceServerPort drivingLicenceAdapter) {
    return new DrivingLicenceClientService(drivingLicenceAdapter);
  }
}
