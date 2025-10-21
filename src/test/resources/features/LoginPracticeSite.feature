Feature: Test Login functionality for Practice site
  @TestCase1
  Scenario Outline:Validate login functionality with valid credentials
    Given User is on Login Page of Practice site
    When User enter the username and password using "<TestCaseName>" data
    And User click to Login button
    Then User successfully is navigated to the Dashboard page

    Examples:
      | TestCaseName |
      | TestCase1  |

  @TestCase2
  Scenario Outline:Validate login functionality with Invalid credentials
    Given User is on Login Page of Practice site
    When User enter the username and password using "<TestCaseName>" data
    And User click to Login button
    Then User successfully is navigated to the error message

    Examples:
      | TestCaseName |
      | TestCase2  |
