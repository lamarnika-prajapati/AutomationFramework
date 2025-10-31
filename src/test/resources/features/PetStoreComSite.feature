Feature: validating functionality on petStore.com site

#  Background:
#    Given user is on home page of petStore
#    When user enter "lamarnika123@gmail.com", "Rala@123" and click on login button
#    Then user is logged in successfully and navigated to Home page


#  Scenario Outline: validate user can search and add a product to cart and validate product details
#    Given user is on home page of petStore
#    When user clicks on search bar and search a pet product "<search product>"
#    And choose a specific pet product "<product name 1>" from the search results
#    Then product details page should display product name "<product name 1>", "<price 1>" and add to cart button
#    When click on add to cart button
#    And close the cart
#    And user clicks on search bar and search a pet product "<search product>"
#    And choose a specific pet product "<product name 2>" from the search results
#    Then product details page should display product name "<product name 2>", "<price 2>" and add to cart button
#    When click on add to cart button
#    And close the cart
#    And user clicks on search bar and search a pet product "<search product>"
#    And choose a specific pet product "<product name 3>" from the search results
#    Then product details page should display product name "<product name 3>", "<price 3>" and add to cart button
#    When click on add to cart button
#    Then the selected product should appear in the cart page with correct name "<product name 3>" , price "<price 3>" and quantity "<default quantity>"
#    When user increases the quantity "<increase product>", "<increase quantity>" of the product
#    And the total price should equals quantity multiplied by unit price
#    When user removes one product "<product name 2>" from the cart
#    Then user validates the final price and quantity of products 2 in the cart
#    When user removes one product from the cart
#    Then user validates the final price and quantity of products 1 in the cart
#    When user removes product from the cart
#    Then the cart should be empty and shows "your cart is empty"
    Examples:
      | search product | product name 1       | product name 2              | product name 3       | increase product            | price 1 | price 2 | price 3 | default quantity | increase quantity |
      | dog costume    | BOB ROSS DOG COSTUME | SHERLOCK HOLMES DOG COSTUME | PRISONER DOG COSTUME | SHERLOCK HOLMES DOG COSTUME | $30.00  | $32.00  | $25.00  | 1                | 4                 |
#      | dog costume    | BOB ROSS DOG COSTUME | SHERLOCK HOLMES DOG COSTUME | PRISONER DOG COSTUME | PRISONER DOG COSTUME        | $30.00  | $32.00  | $25.00  | 1                | 5                 |



