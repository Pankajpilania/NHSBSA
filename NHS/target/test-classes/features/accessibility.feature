@accessibility
Feature: NHS Jobs Search Accessibility

  As a user with accessibility needs
  I want the search functionality to be accessible
  So that I can use it without barriers

  Scenario: Search page is accessible via keyboard
    Given I am a jobseeker on the NHS Jobs search page
    When I navigate using only keyboard
    Then all interactive elements should be accessible

  Scenario: Results are readable at 200 percent zoom
    Given I am a jobseeker on the NHS Jobs search page
    When I zoom the page to 200 percent
    Then the layout should remain usable

  Scenario: Page passes automated accessibility scan
    Given I am a jobseeker on the NHS Jobs search page
    Then the page should have no critical accessibility violations