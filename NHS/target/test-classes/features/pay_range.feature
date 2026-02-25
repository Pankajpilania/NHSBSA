@functional @advanced @payrange
Feature: NHS Jobs Advanced Search - Pay Range Filter

  Background:
    Given I am a jobseeker on the NHS Jobs search page
    And I expand more search options

  Scenario Outline: Search using each available pay range
    When I enter "nurse" in keyword
    And I enter "Leeds" in location
    And I select distance "10"
    And I select pay range "<payRange>"
    And I click the Search button
    Then I should receive matching jobs

    Examples:
      | payRange            |
      | £0 to £10,000       |
      | £10,000 to £20,000  |
      | £20,000 to £30,000  |
      | £30,000 to £40,000  |
      | £40,000 to £50,000  |
      | £50,000 to £60,000  |
      | £60,000 to £70,000  |
      | £70,000 to £80,000  |
      | £80,000 to £90,000  |
      | £90,000 to £100,000 |
      | £100,000 plus       |