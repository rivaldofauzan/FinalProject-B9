Feature: Add to Cart

  Scenario: Adding a product to the cart
    Given I am on the Dashboard page
    When I click the "Add To Cart" button next to an item
    Then I see 1 product(s) in the cart with descriptions, names, and total prices
