Feature: Test add Employee functionality
  @AddUserTest
  Scenario: validate if employee is being successfully added
    Given User is on Login Page
    When User enter Username "Admin" and Password "admin123"
    And User click to Login page
    And User Navigate to the PMI Tab
    And User add to the employee
    | first Name | Middle Name | Last Name | Emp ID |
    | Pooja | Test | Automation | 2839 |
    Then Validate the message "successfully" is displayed
    Then validate the employee is displayed into the employee list
    | firstName | Pooja |

