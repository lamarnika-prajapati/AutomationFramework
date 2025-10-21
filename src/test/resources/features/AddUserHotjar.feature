Feature: Hotjar User Management

  Scenario: Add user to Hotjar tool
    Given I am on Hotjar login portal
    When enter username "rajat.prajapati1991@gmail.com" and password "Lara@0511"
    And click to Sign In button
    And navigate to invite people page
    And enter user email and select the access level
    | user email |
    | lamarnika.prajapat.7@gmail.com |
    | pr.lamarnika@gmail.com |
    | lamarnika123@gmail.com |
    | lamarnika1234@gmail.com |
    | gudduprajapati0506@gmail.com |
    And click on Send invites button
    Then sent invitation message is displayed