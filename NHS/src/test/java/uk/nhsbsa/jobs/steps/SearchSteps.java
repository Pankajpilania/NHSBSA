package uk.nhsbsa.jobs.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.nhsbsa.jobs.pages.ResultsPage;
import uk.nhsbsa.jobs.pages.SearchPage;

public class SearchSteps {

    private final SearchPage searchPage = new SearchPage();
    private final ResultsPage resultsPage = new ResultsPage();

    @Given("I am a jobseeker on the NHS Jobs search page")
    public void iAmAJobseekerOnTheNHSJobsSearchPage() {
        searchPage.open();
    }

   /* @When("I search for {string} jobs in {string} within {string} miles")
    public void iSearchForJobsInWithinMiles(String keyword, String location, String distance) {
        searchPage.enterKeyword(keyword);
        searchPage.enterLocation(location);
        searchPage.chooseDistance(distance);
        searchPage.submitSearch();
    }*/
    @When("I search for {string} jobs in {string} within {string}")
    public void i_search_for_jobs_in_within(String keyword, String location, String distance) {

        searchPage.enterKeyword(keyword);
        searchPage.enterLocation(location);
        searchPage.chooseDistance(distance);
        searchPage.submitSearch();
        searchPage.submitSearch();
    }
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
}
