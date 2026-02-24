package uk.nhsbsa.jobs.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage extends BasePage {

    // Wait for results via URL change (most reliable)
    public void waitForResults() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("search"));
    }

    // Result cards
    private final By resultCards = By.cssSelector("li.search-result");

    // Date elements
    private final By datePostedLabels = By.cssSelector("time");

    // Sort dropdown
    private final By sortSelect =
            By.xpath("//select[@id='sort']");

    public void assertResultsAreReturned() {

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(resultCards));

        List<WebElement> cards = driver.findElements(resultCards);

        assertThat(cards)
                .as("Expected one or more search results")
                .isNotEmpty();
    }

    public void sortByNewestDatePosted() {
        Select select = new Select(new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(sortSelect)));

        select.selectByVisibleText("Date Posted (newest)");
    }

    public void assertSortSetToNewest() {
        Select select = new Select(driver.findElement(sortSelect));

        assertThat(select.getFirstSelectedOption().getText())
                .containsIgnoringCase("date posted (newest)");
    }

    public void assertDateOrderNewestFirstIfDatesVisible() {

        List<WebElement> dateElements = driver.findElements(datePostedLabels);
        List<LocalDate> parsedDates = new ArrayList<>();

        for (WebElement element : dateElements) {
            String text = element.getText().trim();

            if (text.matches("\\d{1,2} [A-Za-z]+ \\d{4}")) {
                parsedDates.add(
                        LocalDate.parse(text,
                                DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.UK))
                );
            }
        }

        if (parsedDates.size() > 1) {
            for (int i = 0; i < parsedDates.size() - 1; i++) {
                assertThat(parsedDates.get(i))
                        .as("Dates are not sorted newest first")
                        .isAfterOrEqualTo(parsedDates.get(i + 1));
            }
        }
    }
}