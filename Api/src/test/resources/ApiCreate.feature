Feature: User API Controller - Create User

  Background:
    Given I have access to the User API Controller

  Scenario: Successfully create a user with title "mr"
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with title "mr" and other required fields
    When I send a POST request to create the user
    Then I should receive a successful response with the status code 200

  Scenario: Successfully create a user with firstName "Johnathan"
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with firstName "Johnathan" and other required fields
    When I send a POST request to create the user
    Then I should receive a successful response with the status code 200

  Scenario: Fail to create a user with empty email field
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with an empty email field
    When I send a POST request to create the user
    Then I should receive a response with the status code 400

  Scenario: Fail to create a user with non-string title
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with a non-string title
    When I send a POST request to create the user
    Then I should receive a response with the status code 400

  Scenario: Fail to create a user with invalid app-id
    Given I am authorized with an invalid app-id "12345678790abc1234567"
    And I have a valid user payload
    When I send a POST request to create the user
    Then I should receive a response with the status code 400