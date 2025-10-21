Feature: Test Auto suggestion feature on google search
  Scenario: Validate auto suggestion functionality
    Given user is on google page
    When user type the text "ultimatix" into the search box
    Then user click to the "ultimatix tcs" from the list
