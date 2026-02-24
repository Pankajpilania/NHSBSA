@functional
Feature: NHS Jobs search

  As a jobseeker on NHS Jobs
  I want to search for jobs with my preferences
  So that I can get recently posted job results

  @search
  Scenario: Search with preferences and sort by newest date posted
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Leeds" within "10"
    Then I should receive matching jobs
    And I sort results by newest date posted
    Then the newest jobs should be shown first

  @positive
  Scenario: Search with keyword only
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "" within ""
    Then I should receive matching jobs

  @negative
  Scenario: Search with invalid location
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Invalid location" within "10"
    Then I should see a no results message

  @boundary
  Scenario: Search with maximum distance radius
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Leeds" within "100"
    Then I should receive matching jobs

  @sorting
  Scenario: Verify sort dropdown contains newest option
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Leeds" within "10"
    Then the sort dropdown should contain "Date Posted (newest)"

  @negative
  Scenario: Search with empty criteria
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "" jobs in "" within ""
    Then the system should handle the request without error

  @security
  Scenario: SQL injection attempt in keyword field
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "' OR 1=1 --" jobs in "Leeds" within "10"
    Then the system should handle the input safely

  @security
  Scenario: Script injection attempt in keyword field
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "<script>alert('x')</script>" jobs in "Leeds" within "10"
    Then the system should not execute the script