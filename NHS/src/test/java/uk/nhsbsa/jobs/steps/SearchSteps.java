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
        resultsPage.assertNoResultsMessageDisplayed();
    }

    @Then("the sort dropdown should contain {string}")
    public void the_sort_dropdown_should_contain(String option) {
        resultsPage.assertSortOptionPresent(option);
    }

    @Then("the system should handle the request without error")
    @Then("the system should handle the input safely")

    @When("I select pay range {string}")
    public void i_select_pay_range(String payRange) {
        searchPage.selectPayRange(payRange);
    }
    // Expand Advanced
    @When("I expand more search options")
    public void i_expand_more_search_options() {
        searchPage.expandMoreSearchOptions();
    }

    // Visibility checks
    @Then("the Job reference field should be visible")
    public void job_reference_field_should_be_visible() {
        assertThat(searchPage.isJobReferenceVisible()).isTrue();
    }

    @Then("the Employer field should be visible")
    public void employer_field_should_be_visible() {
        assertThat(searchPage.isEmployerVisible()).isTrue();
    }

    // Enter Job Reference
    @When("I enter job reference {string}")
    public void i_enter_job_reference(String reference) {
        searchPage.enterJobReference(reference);
    }

    // Click Search
    @When("I click the Search button")
    public void i_click_the_search_button() {
        searchPage.submitSearch();
    }
    @When("I enter {string} in keyword")
    public void i_enter_in_keyword(String keyword) {
        searchPage.enterKeyword(keyword);
    }

    @When("I enter {string} in location")
    public void i_enter_in_location(String location) {
        searchPage.enterLocation(location);
    }

    @When("I select distance {string}")
    public void i_select_distance(String distance) {
        searchPage.chooseDistance(distance);
    }

}