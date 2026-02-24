@functional @advanced
Feature: NHS Jobs Advanced Search

  As a jobseeker
  I want to use advanced search filters
  So that I can narrow down results

  Scenario: Advanced search fields are visible
    Given I am a jobseeker on the NHS Jobs search page
    When I expand more search options
    Then the Job reference field should be visible
    And the Employer field should be visible

  @positive
  Scenario: Search using valid job reference
    Given I am a jobseeker on the NHS Jobs search page
    When I expand more search options
    And I enter job reference "914-JOBREF-a"
    And I click the Search button
    Then I should receive matching jobs

  @negative
  Scenario: Search using invalid job reference
    Given I am a jobseeker on the NHS Jobs search page
    When I expand more search options
    And I enter job reference "INVALID-REF-9999"
    And I click the Search button
    Then I should see a no results message