Feature: Menu

  Scenario: As a user, I want to see all products
    Given I am logged in
    When I click on the sidebar menu
    And I click the All Items link
    Then I am redirected to the Products page

  Scenario: As a user, I want to view more about the company
    Given I am logged in
    When I click on the sidebar menu
    And I click the About link
    Then I am redirected to saucelabs.com

  Scenario: As a user, I want to clear the cart
    Given I am logged in
    When I click on the sidebar menu
    And I click on Reset App State link
    Then All items in the cart are removed