package archi.exa.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static archi.exa.bootstrap.Packages.*;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "archi.hexa.clients",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ClientsDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule CLIENTS_GLOBAL_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(R_CLIENTS_PACKAGE)
          .should()
          .onlyDependOnClassesThat()
          .resideInAnyPackage(
              R_CLIENTS_PACKAGE,
              R_DOMAIN_PACKAGE,
              JAVA_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE,
              SWAGGER_PACKAGE)
          .andShould()
          .onlyAccessClassesThat()
          .resideInAnyPackage(
              R_CLIENTS_PACKAGE,
              R_DOMAIN_PACKAGE,
              JAVA_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE,
              SWAGGER_PACKAGE)
          .andShould()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(R_CLIENTS_PACKAGE, R_BOOTSTRAP_PACKAGE)
          .because("That's hexagonal baby !");

  ;

  @ArchTest
  public static ArchRule REST_GLOBAL_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(R_REST_PACKAGE)
          .should()
          .onlyDependOnClassesThat()
          .resideInAnyPackage(
              R_REST_PACKAGE,
              R_DOMAIN_PACKAGE,
              JAVA_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE,
              SWAGGER_PACKAGE)
          .andShould()
          .onlyAccessClassesThat()
          .resideInAnyPackage(
              R_REST_PACKAGE,
              R_DOMAIN_PACKAGE,
              JAVA_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE,
              SWAGGER_PACKAGE)
          .andShould()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(R_REST_PACKAGE, R_BOOTSTRAP_PACKAGE)
          .because("That's hexagonal baby !");

  @ArchTest
  public static ArchRule REST_RESOURCES_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(REST_RESOURCES_PACKAGE)
          .should()
          .beAnnotatedWith(RestController.class);

  @ArchTest
  public static ArchRule REST_RESOURCES_API_DEVELOPMENT_RULE =
      classes().that().resideInAPackage(REST_RESOURCES_API_PACKAGE).should().beInterfaces();

  @ArchTest
  public static ArchRule REST_MODEL_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(R_REST_MODEL_PACKAGE)
          .should()
          .haveSimpleNameContaining("Dto");
}
