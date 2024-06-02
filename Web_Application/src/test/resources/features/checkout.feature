Feature: Checkout

  Scenario: Cancel from the Checkout Overview page
    Given I am on the Checkout Information page
    When I fill in first name with "Lebron"
    And I fill in last name with "James"
    And I fill in Zip/Postal Code with "40121"
    And I click the Continue button
    And I press the Cancel button
    Then I go to the cart page

  Scenario: Finalise checkout and proceed to Checkout Complete
    Given I am on the Checkout Information page
    When I fill in first name with "Lebron"
    And I fill in last name with "James"
    And I fill in Zip/Postal Code with "40121"
    And I click the Continue button
    And I press the Finish button
    Then I go to the Checkout Complete page

  Scenario: Go back to home from Checkout Complete page
    Given I am on the Checkout Information page
    When I fill in first name with "Lebron"
    And I fill in last name with "James"
    And I fill in Zip/Postal Code with "40121"
    And I click the Continue button
    And I press the Finish button
    And I press the Back Home button
    Then I go to the Product page
