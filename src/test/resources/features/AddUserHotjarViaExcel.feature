Feature: Hotjar User Management Automation

  Scenario: Add users to Hotjar portal
    Given I am on Hotjar login portal
    When enter username "rajat.prajapati1991@gmail.com" and password "Lara@0511"
    And click to Sign In button
    And navigate to invite people page
    And enter user email from excel "src/test/resources/testdata/UserData.xlsx" and sheet "useremail" and select the access level
    And click on Send invites button
    Then sent invitation message is displayed