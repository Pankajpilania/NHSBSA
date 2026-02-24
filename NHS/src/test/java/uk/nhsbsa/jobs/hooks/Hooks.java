package uk.nhsbsa.jobs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import uk.nhsbsa.jobs.config.DriverManager;
import uk.nhsbsa.jobs.config.WebDriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.setDriver(WebDriverFactory.createDriver());
    }

    @After
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
