package uk.nhsbsa.jobs.steps;

import io.cucumber.java.en.Then;
import uk.nhsbsa.jobs.config.PerformanceContext;
import uk.nhsbsa.jobs.pages.ResultsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PerformanceSteps {

    private final ResultsPage resultsPage = new ResultsPage();

    @Then("the results should load within {int} seconds")
    public void the_results_should_load_within(Integer seconds) {

        // Wait for search results to load
        resultsPage.waitForResults();

        long durationMs = PerformanceContext.getDurationMillis();
        long durationSeconds = durationMs / 1000;

        assertThat(durationSeconds)
                .as("Search took too long: " + durationSeconds + " seconds")
                .isLessThanOrEqualTo(seconds);

        PerformanceContext.clear();
    }
}