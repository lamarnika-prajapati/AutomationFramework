Feature: Test Login functionality
  @LoginTest
  Scenario Outline:Validate login functionality with valid credentials
    Given User is on Login Page
    When User enter Username "<username>" and Password "<password>"
    And User click to Login page
    Then User successfully is navigated to Dashboard page

    Examples:
    | username | password |
    | Admin    | admin123 |
