Feature: Test Login functionality
  Background:
    Given User is on Login Page

  @LoginTest
  Scenario: Validate login functionality with valid credentials
    When User enter Username "Admin" and Password "admin123"
    And User click to Login page
    Then User successfully is navigated to Dashboard page

  @ForgotPasswordTest
  Scenario: Validate forgot password functionality
    When User click to forgot password
    Then user is navigated to reset password page

