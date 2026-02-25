@performance
Feature: NHS Jobs Search Performance

  Background:
    Given I am a jobseeker on the NHS Jobs search page

  Scenario: Search response time is acceptable
    When I search for "nurse" jobs in "Leeds" within "10"
    Then the results should load within 3 seconds