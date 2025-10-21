Feature: functionalities testing on Yatra.com site

  Scenario Outline: Validate flight searching functionality for one way trip with valid inputs
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class | Special Fare | Non Stop |
      | One Way     | BOM  | JAI | 13 December 2025 | 1      | 1        | 1      | Economy      | Regular      | Yes      |
#      | One Way     | Chennai  | Lucknow   | 18 October 2025  | 5      | 0        | 0      | Economy         | Student        | No       |
#      | One Way     | Lucknow  | Bangalore | 19 October 2025  | 6      | 2        | 2      | Economy         | Armed Forces   | Yes      |
#      | One Way     | Varanasi | Chennai   | 27 October 2025  | 4      | 0        | 0      | Economy         | Senior Citizen | No       |

#  Scenario: Validate all travellers dependencies functionality
#    Given Given traveller is on the YatraCom site page
#    When traveller select adult under travellers details field
#      | Adult | 7 |
#    Then child and infant select lists should be updated based on the selected adult
#      | Child  | 2 |
#      | Infant | 7 |
#
#  Scenario Outline: Validate filter functionality on flight dashboard page
#      Given Given traveller is on the YatraCom site page
#      When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
#      And traveller click to search button
#      Then the flights list should be visible if flights are available
#      Examples:
#        | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
#        | One Way     | BOM  | JAI | 13 December 2025 | 6      | 3        | 2      | Premium Economy | Regular      | Yes      |
