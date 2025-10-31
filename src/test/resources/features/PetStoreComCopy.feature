Feature: validating functionality on petStore.com site

#  Background:
#    Given user is on home page of petStore
#    When user enter "lamarnika123@gmail.com", "Rala@123" and click on login button
#    Then user is logged in successfully and navigated to Home page


  Scenario: validate a user can search, add products to cart
    Given user is on home page of petStore
    When user search a pet product and add the following product to the cart
      | search product | product name                                             |
      | Dog Costume    | BOB ROSS DOG COSTUME                                     |
      | Dog Costume    | PRISONER DOG COSTUME                                     |
      | Cat Meals      | FUSSIE CAT® GRAIN FREE QUAIL & DUCK MEAL CAT FOOD 10 LBS |
      | Dog Costume    | SHERLOCK HOLMES DOG COSTUME                              |

    Then all added products should appear in the cart page with correct details
    And the total price should equals quantity multiplied by unit price
    And the sub total should be updated on increasing of the product by user
      | product name         | increase quantity by |
      | BOB ROSS DOG COSTUME | 2                    |

    And the sub total should be updated on decreasing of the product by user
      | product name         | decrease quantity by |
      | BOB ROSS DOG COSTUME | 1                    |

    And the sub total should be updated on removal of product "SHERLOCK HOLMES DOG COSTUME"
    When user removes all the products from the cart
    Then the cart should be empty and shows "Your cart is currently empty."





