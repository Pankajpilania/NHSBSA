package uk.nhsbsa.jobs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
public class SearchPage extends BasePage {

    private static final String SEARCH_URL = "https://www.jobs.nhs.uk/candidate/search";

    private final By keywordField = By.cssSelector("input[name='keyword'], input[id*='keyword']");
    private final By locationField = By.cssSelector("input[name='location'], input[id*='location']");
    private final By distanceSelect = By.cssSelector("select[name='distance'], select[id*='distance']");
    private final By cookiesButton = By.xpath("//button[@id='accept-cookies']");
    private final By searchButton = By.cssSelector("button[type='submit'], input[type='submit']");
    private final By moreSearchOptionsLink = By.xpath("//a[contains(text(),'More search options') or contains(text(),'Fewer search options')]");
    private final By jobReferenceField = By.xpath("//input[@id='jobReference']");
    private final By employerField = By.xpath("//input[@id='employer']");

    public void open() {
        driver.get(SEARCH_URL);
        driver.findElement(cookiesButton).click();
    }

    public void enterKeyword(String keyword) {
        type(keywordField, keyword);
    }

    public void enterLocation(String location) {
        type(locationField, location);
    }

    public void chooseDistance(String miles) {
        click(distanceSelect);
        click(By.xpath("//select[contains(@name,'distance') or contains(@id,'distance')]//option[contains(normalize-space(), '" + miles + "')]"));
    }

    public void submitSearch() {
        click(searchButton);
    }


    private final By payRangeSelect = By.id("payRange");

    public void selectPayRange(String visibleText) {

        // Ensure advanced section is expanded first
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(payRangeSelect)
        );

        dropdown.click();

        Select select = new Select(dropdown);

        wait.until(driver -> select.getOptions().size() > 1);

        select.selectByVisibleText(visibleText);
    }

    public void expandMoreSearchOptions() {

        wait.until(ExpectedConditions.elementToBeClickable(moreSearchOptionsLink))
                .click();

        // Wait until advanced fields are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobReferenceField));
    }


    // Enter Job Reference
    public void enterJobReference(String reference) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jobReferenceField)
        );

        field.clear();
        field.sendKeys(reference);
    }


    // Enter Employer


    // Visibility Checks
    public boolean isJobReferenceVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(jobReferenceField))
                .isDisplayed();
    }

    public boolean isEmployerVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(employerField))
                .isDisplayed();
    }
}
