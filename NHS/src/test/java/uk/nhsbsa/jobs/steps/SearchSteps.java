package uk.nhsbsa.jobs.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import uk.nhsbsa.jobs.config.DriverManager;
import uk.nhsbsa.jobs.config.PerformanceContext;
import uk.nhsbsa.jobs.pages.ResultsPage;
import uk.nhsbsa.jobs.pages.SearchPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final SearchPage searchPage = new SearchPage();
    private final ResultsPage resultsPage = new ResultsPage();

    // =========================
    // Navigation
    // =========================

    @Given("I am a jobseeker on the NHS Jobs search page")
    public void iAmAJobseekerOnTheNHSJobsSearchPage() {
        searchPage.open();
    }

    // =========================
    // Search
    // =========================

    @When("I search for {string} jobs in {string} within {string}")
    public void i_search_for_jobs_in_within(String keyword, String location, String distance) {

        // Start performance timer
        PerformanceContext.startTimer();

        if (keyword != null && !keyword.isBlank()) {
            searchPage.enterKeyword(keyword);
        }

        if (location != null && !location.isBlank()) {
            searchPage.enterLocation(location);
        }

        if (distance != null && !distance.isBlank()) {
            searchPage.chooseDistance(distance);
        }

        searchPage.submitSearch();
    }

    // =========================
    // Positive Verification
    // =========================

    @Then("I should receive matching jobs")
    public void iShouldReceiveMatchingJobs() {
        resultsPage.waitForResults();
        resultsPage.assertResultsAreReturned();
    }

    @And("I sort results by newest date posted")
    public void iSortResultsByNewestDatePosted() {
        resultsPage.sortByNewestDatePosted();
    }

    @Then("the newest jobs should be shown first")
    public void theNewestJobsShouldBeShownFirst() {
        resultsPage.assertSortSetToNewest();
        resultsPage.assertDateOrderNewestFirstIfDatesVisible();
    }

    // =========================
    // Negative / Validation
    // =========================

    @Then("I should see a no results message")
    public void i_should_see_a_no_results_message() {
        assertThat(driver.getPageSource())
                .contains("Location not found");
    }

    @Then("the sort dropdown should contain {string}")
    public void the_sort_dropdown_should_contain(String option) {
        resultsPage.assertSortOptionPresent(option);
    }

    @Then("the system should handle the request without error")
    public void the_system_should_handle_the_request_without_error() {
        assertThat(driver.getTitle()).isNotBlank();
    }

    @Then("the system should handle the input safely")
    public void the_system_should_handle_the_input_safely() {
        assertThat(driver.getPageSource())
                .doesNotContain("SQL")
                .doesNotContain("syntax error");
    }

    @Then("the system should not execute the script")
    public void the_system_should_not_execute_the_script() {
        assertThat(driver.getPageSource())
                .doesNotContain("<script>alert");
    }
}