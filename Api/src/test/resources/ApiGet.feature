Feature: Get User by ID

  Background:
    Given I have access to the User API

  Scenario: Get user without setting app-id in the request header
    Given I dont have an app-id in the header
    When I send a GET request to "/user/{id}" with user ID "60d0fe4f5311236168a109cd"
    Then I should receive a 403 status code
    And the response should contain an error message about "APP_ID_MISSING"
    
  Scenario: Get user with a valid and registered user ID
    Given I have a valid app-id in the header "665be3a3461fbb24df280141"
    When I send a GET request to "/user/{id}" with user ID "60d0fe4f5311236168a109cd"
    Then I should receive a 200 status code
    And the response should contain the user details

  Scenario: Get user with a user ID containing a symbol
    Given I have a valid app-id in the header "665be3a3461fbb24df280141"
    When I send a GET request to "/user/{id}" with user ID "60d0fe4f5311236168a109cd="
    Then I should receive a 400 status code
    And the response should contain an error message about "PARAMS_NOT_VALID"

  Scenario: Get user with an empty or null user ID
    Given I have a valid app-id in the header "665be3a3461fbb24df280141"
    When I send a GET request to "/user/{id}" with user ID ""
    Then I should receive a 200 status code
    And the response should contain multiple user details

  Scenario: Get user with an unregistered user ID
    Given I have a valid app-id in the header "665be3a3461fbb24df280141"
    When I send a GET request to "/user/{id}" with user ID "1234567890abc123456789c"
    Then I should receive a 400 status code
    And the response should contain an error message about "PARAMS_NOT_VALID"

  Scenario: Get user with a deleted user ID
    Given I have a valid app-id in the header "665be3a3461fbb24df280141"
    When I send a GET request to "/user/{id}" with user ID "60d0fe4f5311236168a109cc"
    Then I should receive a 404 status code
    And the response should contain an error message about "RESOURCE_NOT_FOUND"
