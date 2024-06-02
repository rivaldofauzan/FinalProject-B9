Feature: Delete User by ID

  Background:
    Given I have access to the API User Controller

  Scenario: Attempt to delete a user without app-id
    Given I don't have an app-id
    When I send a DELETE request to "/user/{id}" with user ID "60d0fe4f5311236168a109cc"
    Then I should receive an error with status code 403
    And the response should display an error "APP_ID_MISSING"

  Scenario: Successfully delete a user with a valid and registered ID
    Given I have a valid app-id "665be3a3461fbb24df280141"
    When I send a DELETE request to "/user/{id}" with user ID "60d0fe4f5311236168a109cc"
    Then I should receive success with status code 200
    And the response should display the user id "60d0fe4f5311236168a109cc"

  Scenario: Attempt to delete a user with an invalid user ID containing symbols
    Given I have a valid app-id "665be3a3461fbb24df280141"
    When I send a DELETE request to "/user/{id}" with user ID "60d0fe4f5311236168a109cc="
    Then I should receive an error with status code 400
    And the response should display an error "PARAMS_NOT_VALID"

  Scenario: Attempt to delete a user with an empty or null user ID
    Given I have a valid app-id "665be3a3461fbb24df280141"
    When I send a DELETE request to "/user/{id}" with user ID ""
    Then I should receive an error with status code 404
    And the response should display an error "PATH_NOT_FOUND"

  Scenario: Attempt to delete a user with an unregistered user ID
    Given I have a valid app-id "665be3a3461fbb24df280141"
    When I send a DELETE request to "/user/{id}" with user ID "1234567890abc123456789c"
    Then I should receive an error with status code 400
    And the response should display an error "PARAMS_NOT_VALID"
