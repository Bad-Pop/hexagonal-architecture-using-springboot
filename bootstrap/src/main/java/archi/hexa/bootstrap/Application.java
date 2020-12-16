package archi.hexa.bootstrap;

import archi.hexa.bootstrap.config.ApplicationConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "archi.hexa")
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /** Use only for testing purpose */
  @Override
  public void run(String... args) {}
}
