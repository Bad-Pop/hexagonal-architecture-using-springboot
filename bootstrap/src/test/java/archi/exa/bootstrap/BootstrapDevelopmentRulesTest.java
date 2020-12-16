package archi.exa.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;

@AnalyzeClasses(
    packages = "archi.hexa.bootstrap",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class BootstrapDevelopmentRulesTest {}
