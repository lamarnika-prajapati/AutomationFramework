Feature: functionalities testing on Yatra.com site

  @TagFlightSearching
  Scenario Outline: Validate flight searching functionality for one way trip with valid inputs
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available

    Examples:
      | Travel type | From      | To        | Departure        | Adults | Children | Infant | travel class | Special Fare   | Non Stop |
      | One Way     | BOM       | JAI       | 13 December 2025 | 4      | 1        | 1      | Economy      | Regular        | Yes      |
      | One Way     | New Delhi | Mumbai    | 18 November 2025 | 3      | 0        | 0      | Economy      | Student        | No       |
      | One Way     | MAA       | Bangalore | 29 October 2025  | 6      | 2        | 2      | Economy      | Armed Forces   | Yes      |
      | One Way     | Varanasi  | PNQ       | 27 October 2025  | 4      | 0        | 0      | Economy      | Senior Citizen | No       |

  @TagTravellersDependency
  Scenario: Validate all travellers dependencies functionality
    Given Given traveller is on the YatraCom site page
    When traveller select adult under travellers details field
      | Adult | 7 |
    Then child and infant select lists should be updated based on the selected adult
      | Child  | 2 |
      | Infant | 7 |

  @TagDepartAscendingFilter
  Scenario Outline: Validate depart filter functionality in ascending order on flight dashboard page
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Depart filter
    Then the flights list should be visible based on depart filter "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
#      | One Way     | BOM  | JAI | 13 December 2025 | 6      | 3        | 2      | Premium Economy | Regular      | Yes      |
      | One Way     | New Delhi  | Mumbai | 13 December 2025 | 1      | 0        | 0      | Economy | Regular      | No      |

  @TagDepartDescendingFilter
  Scenario Outline: Validate depart filter functionality in Descending order on flight dashboard page
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Depart filter to see flights in descending order
    Then the flights list should be visible in descending order based on depart filter "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
      | One Way     | New Delhi  | Mumbai | 13 December 2025 | 1      | 0        | 0      | Economy | Regular      | No      |


  @TagArriveFilter
  Scenario Outline: Validate filter functionality on flight dashboard page based on Arrive time
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Arrive filter
    Then the flights list should be visible based on Arrive filter "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
      | One Way     | New Delhi  | JAI | 13 December 2025 | 6      | 3        | 2      | Premium Economy | Regular      | Yes      |

  @TagArriveDescendingFilter
  Scenario Outline: Validate filter functionality in descending order on flight dashboard page based on Arrive time
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Arrive reverse filter
    Then the flights list should be visible in descending order based on Arrive filter "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
      | One Way     | New Delhi  | JAI | 13 December 2025 | 6      | 3        | 2      | Premium Economy | Regular      | Yes      |


  @TagDurationFilter
  Scenario Outline: Validate filter functionality on flight dashboard page based on Duration time
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Duration filter
    Then the flights list should be visible based on Duration filter "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
      | One Way     | New Delhi  | Mumbai | 25 October 2025 | 1      | 0        | 0      | Economy | Regular      | Yes      |


  @TagPriceFilter
  Scenario Outline: Validate filter functionality on flight dashboard page based on Price
    Given Given traveller is on the YatraCom site page
    When traveller enter flight details "<Travel type>" "<From>" "<To>" "<Departure>" "<Adults>" "<Children>" "<Infant>" "<travel class>" "<Special Fare>" "<Non Stop>" and search the flight
    And traveller click to search button
    Then the flights list should be visible if flights are available
    And traveller click on Price filter
    Then the flights list should be visible based on Price "<From>"
    Examples:
      | Travel type | From | To  | Departure        | Adults | Children | Infant | travel class    | Special Fare | Non Stop |
      | One Way     | New Delhi  | JAI | 13 December 2025 | 6      | 3        | 2      | Premium Economy | Regular      | Yes      |

