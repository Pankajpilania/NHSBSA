@accessibility
Feature: NHS Jobs Search Accessibility

  Background:
    Given I am a jobseeker on the NHS Jobs search page

  Scenario: Search page is accessible via keyboard
    When I navigate using only keyboard
    Then all interactive elements should be accessible

  Scenario: Page passes automated accessibility scan
    Then the page should have no critical accessibility violations