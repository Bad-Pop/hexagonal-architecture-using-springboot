package archi.exa.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static archi.exa.bootstrap.Packages.*;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
    packages = "archi.hexa",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class LayersDevelopmentRulesTest {

  // Layers names
  private static final String DOMAIN_LAYER = "Domain";
  private static final String BOOTSTRAP_LAYER = "Bootstrap";
  private static final String CLIENTS_LAYER = "Clients";
  private static final String REST_LAYER = "Rest";
  private static final String SERVERS_LAYER = "Servers";
  private static final String POSTGRES_LAYER = "Postgres";

  @ArchTest
  static ArchRule LAYERS_DEVELOPMENT_RULE =
      layeredArchitecture()
          .layer(DOMAIN_LAYER)
          .definedBy(R_DOMAIN_PACKAGE)
          .layer(BOOTSTRAP_LAYER)
          .definedBy(R_BOOTSTRAP_PACKAGE)
          .layer(CLIENTS_LAYER)
          .definedBy(R_CLIENTS_PACKAGE)
          .layer(REST_LAYER)
          .definedBy(R_REST_PACKAGE)
          .layer(SERVERS_LAYER)
          .definedBy(R_SERVERS_PACKAGE)
          .layer(POSTGRES_LAYER)
          .definedBy(R_POSTGRES_PACKAGE)
          .whereLayer(DOMAIN_LAYER)
          .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER, CLIENTS_LAYER, SERVERS_LAYER)
          .whereLayer(BOOTSTRAP_LAYER)
          .mayNotBeAccessedByAnyLayer()
          .whereLayer(CLIENTS_LAYER)
          .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
          .whereLayer(REST_LAYER)
          .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
          .whereLayer(SERVERS_LAYER)
          .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
          .whereLayer(POSTGRES_LAYER)
          .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
          .because("That's hexagonal baby !");
}
