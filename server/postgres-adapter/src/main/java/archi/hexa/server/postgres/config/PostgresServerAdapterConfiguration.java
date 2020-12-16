package archi.hexa.server.postgres.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "archi.hexa.server.postgres.entities")
@EnableJpaRepositories(basePackages = "archi.hexa.server.postgres.repositories")
public class PostgresServerAdapterConfiguration {}
