package archi.hexa.bootstrap.config;

import archi.hexa.bootstrap.config.domain.DomainConfiguration;
import archi.hexa.server.postgres.config.PostgresServerAdapterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfiguration.class, PostgresServerAdapterConfiguration.class})
public class ApplicationConfiguration {}
