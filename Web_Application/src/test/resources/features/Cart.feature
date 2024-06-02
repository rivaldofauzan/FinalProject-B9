Feature: Checkout

  Scenario: Checkout berisi 1 barang
    Given I am on the Dashboard page
    When I click the "Add To Cart" button next to an item
    And I click the cart icon
    Then I see 1 product(s) in the cart with descriptions, names, and total prices

  Scenario: Checkout berisi lebih dari 1 barang dengan maks 6 barang
    Given I am on the Dashboard page
    When I add 2 random product(s) to the cart
    And I click the cart icon
    Then I see 2 product(s) in the cart with descriptions, names, and total prices

  Scenario: Checkout saat cart berisi semua barang
    Given I am on the Dashboard page
    When I add 6 random product(s) to the cart
    And I click the cart icon
    Then I see 6 product(s) in the cart with descriptions, names, and total prices
