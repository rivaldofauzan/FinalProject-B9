Feature: User API Controller - Create User

  Background:
    Given I have access to the User API Controller

  Scenario: TC01 - Fail to create a user without app-id header
    Given I have a user payload with title "mr" and other required fields
    When I send a POST request to create the user
    Then I should receive a response with the status code 403
    And the response body should contain "APP_ID_MISSING"

  Scenario: TC02 - Fail to create a user with invalid app-id header value
    Given I am authorized with an invalid app-id "invalid_app_id"
    And I have a user payload with title "mr" and other required fields
    When I send a POST request to create the user
    Then I should receive a response with the status code 403
    And the response body should contain "APP_ID_NOT_EXIST"

  Scenario: TC03 - Fail to create a user with incorrectly set app-id header
    Given I am authorized with an incorrectly set app-id "incorrect_app_id"
    And I have a user payload with title "mr" and other required fields
    When I send a POST request to create the user
    Then I should receive a response with the status code 403
    And the response body should contain "APP_ID_MISSING"

  Scenario: TC04 - Successfully create a user with valid app-id header
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with title "mr" and other required fields
    When I send a POST request to create the user
    Then I should receive a successful response with the status code 200
    And the response body should contain user data

  Scenario: TC05 - Successfully create a user with valid firstName and lastName and new email
    Given I am authorized with a valid app-id "665c257a4ff6ee05ceaa8edf"
    And I have a user payload with firstName "Levi", lastName "Ackerman", and email "leviackerman54321@example.com"
    When I send a POST request to create the user
    Then I should receive a successful response with the status code 200
    And the response body should contain user data

