@performance
Feature: NHS Jobs Search Performance

  As a jobseeker
  I want search results to load quickly
  So that I have a good user experience

  Scenario: Search response time is acceptable
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Leeds" within "10"
    Then the results should load within 8 seconds