package uk.nhsbsa.jobs.pages;

import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private static final String SEARCH_URL = "https://www.jobs.nhs.uk/candidate/search";

    private final By keywordField = By.cssSelector("input[name='keyword'], input[id*='keyword']");
    private final By locationField = By.cssSelector("input[name='location'], input[id*='location']");
    private final By distanceSelect = By.cssSelector("select[name='distance'], select[id*='distance']");
    private final By cookiesButton = By.cssSelector("button[type='submit'], input[type='submit']");
    private final By searchButton = By.cssSelector("button[type='submit'], input[type='submit']");
    public void search(String keyword, String location, String distance) {

        if (keyword != null && !keyword.isBlank()) {
            enterKeyword(keyword);
        }

        if (location != null && !location.isBlank()) {
            enterLocation(location);
        }

        if (distance != null && !distance.isBlank()) {
            chooseDistance(distance);
        }

        submitSearch();
    }
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
}
