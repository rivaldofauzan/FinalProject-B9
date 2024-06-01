Feature: Logout
  Background:
    Given I am on the Login page

  Scenario: Logout
    When I type in a valid username in the username field
    And I type in a valid password in the password field
    And I press the Login button
    Then I should login successfully
    And I click menu button
    When I click logout button 
    Then I should be logged out successfully