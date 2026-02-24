package uk.nhsbsa.jobs.steps;

import io.cucumber.java.en.Given;
import uk.nhsbsa.jobs.pages.SearchPage;

public class CompatibilitySteps {

    @Given("I launch the application in {string}")
    public void i_launch_the_application_in(String browser) {

        System.setProperty("browser", browser);

        new SearchPage().open();
    }
}