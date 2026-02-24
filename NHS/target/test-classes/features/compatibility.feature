@compatibility
Feature: NHS Jobs Search Cross Browser Support

  As a jobseeker
  I want the search functionality to work across supported browsers
  So that I can access jobs regardless of browser choice

  Scenario Outline: Search works across supported browsers
    Given I launch the application in "<browser>"
    When I search for "nurse" jobs in "Leeds" within "10"
    Then I should receive matching jobs

    Examples:
      | browser  |
      | chrome   |
      | firefox  |