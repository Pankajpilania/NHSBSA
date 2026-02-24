package uk.nhsbsa.jobs.runner;

import static io.cucumber.junit.platform.engine.Constants.*;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = FILTER_TAGS_PROPERTY_NAME,
        value = "not @accessibility"
)

@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "uk.nhsbsa.jobs")

@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "html:target/cucumber-report/cucumber.html," +
                "json:target/cucumber-report/cucumber.json"
)

@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @wip")

@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PARALLEL_CONFIG_STRATEGY_PROPERTY_NAME, value = "fixed")
@ConfigurationParameter(key = PARALLEL_CONFIG_FIXED_PARALLELISM_PROPERTY_NAME, value = "2")

public class RunCucumberTest {
}