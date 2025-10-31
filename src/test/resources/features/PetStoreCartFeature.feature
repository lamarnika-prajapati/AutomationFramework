Scenario: validate a user can search, add products to cart
Given user is on home page of petStore
When user search a pet product and add the following product to the cart
| search product | product name                                             |
| Dog Costume    | BOB ROSS DOG COSTUME                                     |
| Dog Costume    | PRISONER DOG COSTUME                                     |
| Cat Meals      | FUSSIE CAT® GRAIN FREE QUAIL & DUCK MEAL CAT FOOD 10 LBS |
| Dog Costume    | SHERLOCK HOLMES DOG COSTUME                              |