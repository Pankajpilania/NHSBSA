package uk.nhsbsa.jobs.steps;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import uk.nhsbsa.jobs.config.DriverManager;
import uk.nhsbsa.jobs.pages.SearchPage;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessibilitySteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final SearchPage searchPage = new SearchPage();

    @When("I navigate using only keyboard")
    public void navigateUsingOnlyKeyboard() {

        Actions actions = new Actions(driver);

        // Simulate TAB navigation through first few elements
        for (int i = 0; i < 5; i++) {
            actions.sendKeys(Keys.TAB).perform();
        }

        WebElement active = driver.switchTo().activeElement();
        assertThat(active).isNotNull();
    }

    @When("I zoom the page to 200 percent")
    public void zoomTo200Percent() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='200%'");
    }

    @Then("the layout should remain usable")
    public void verifyLayoutUsable() {

        WebElement keyword = driver.findElement(
                By.cssSelector("input[name='keyword'], input[id*='keyword']")
        );

        assertThat(keyword.isDisplayed()).isTrue();
    }

    @Then("all interactive elements should be accessible")
    public void verifyInteractiveElementsAccessible() {

        assertThat(driver.findElements(By.tagName("input")).size())
                .isGreaterThan(0);

        assertThat(driver.findElements(By.tagName("button")).size())
                .isGreaterThan(0);
    }

    @Then("the page should have no critical accessibility violations")
    public void runAxeAccessibilityScan() {

        Results results = new AxeBuilder().analyze(driver);

        // Get only critical violations
        var criticalViolations = results.getViolations().stream()
                .filter(v -> "critical".equalsIgnoreCase(v.getImpact()))
                .toList();

        assertThat(criticalViolations)
                .as("Critical accessibility violations found: " + criticalViolations)
                .isEmpty();
    }
}