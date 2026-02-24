Feature: NHS Jobs search
  As a jobseeker on NHS Jobs
  I want to search for jobs with my preferences
  So that I can get recently posted job results

  @functional @search
  Scenario: Search with preferences and sort by newest date posted
    Given I am a jobseeker on the NHS Jobs search page
    When I search for "nurse" jobs in "Leeds" within "10"
    Then I should receive matching jobs
    And I sort results by newest date posted
    Then the newest jobs should be shown first
