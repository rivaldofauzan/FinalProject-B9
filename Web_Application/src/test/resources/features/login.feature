Feature: Login

  Background:
    Given I am on the Login page
  
  Scenario: Login with a valid username and password
    When I type in a valid username in the username field
    And I type in a valid password in the password field
    And I press the Login button
    Then I should login successfully

  Scenario: Correct username and incorrect password
    When I type in a valid username in the username field
    And I type in an invalid password in the password field
    And I press the Login button
    Then I should see an error message "Error Password doesn't match"

  Scenario: Incorrect username and correct password
    When I type in an invalid username in the username field
    And I type in a valid password in the password field
    And I press the Login button
    Then I should see an error message "Error Username doesn't match"

  Scenario: Missing Username and password
    When I type in a valid username in the username field
    And I press the Login button
    Then I should see an error message "Error Mandatory Checking When Username & Password Not Filled"

  Scenario: Missing username
    When I type in a valid password in the password field
    And I press the Login button
    Then I should see an error message "Error Mandatory Checking When Username Not Filled"

  Scenario: Missing password
    When I type in a valid username in the username field
    And I press the Login button
    Then I should see an error message "Error Mandatory Checking When Password Not Filled"