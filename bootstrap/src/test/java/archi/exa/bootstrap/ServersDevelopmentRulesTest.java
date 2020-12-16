package archi.exa.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static archi.exa.bootstrap.Packages.*;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "archi.hexa.servers",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ServersDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule SERVERS_GLOBAL_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(R_SERVERS_PACKAGE)
          .should()
          .onlyDependOnClassesThat()
          .resideInAnyPackage(
              JAVA_PACKAGE,
              R_SERVERS_PACKAGE,
              R_DOMAIN_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE,
              JAVAX_PACKAGE,
              HIBERNATE_PACKAGE)
          .andShould()
          .onlyAccessClassesThat()
          .resideInAnyPackage(
              JAVA_PACKAGE,
              R_SERVERS_PACKAGE,
              R_DOMAIN_PACKAGE,
              VAVR_PACKAGE,
              LOMBOK_PACKAGE,
              SPRING_PACKAGE)
          .andShould()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(R_SERVERS_PACKAGE, R_BOOTSTRAP_PACKAGE)
          .because("That's hexagonal baby !");
}
