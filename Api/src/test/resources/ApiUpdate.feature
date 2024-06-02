@Update
Feature: User API Controller - Update User Data

    Scenario: Successfully update user's title
        Given I have a user registered on the system
        When I update the user with the title to "miss"
        Then I should receive a success response with status code 200
        And the title in the response should reflect the update to "miss"

    Scenario: Successfully update user's firstName
        Given I have a user registered on the system
        When I update the user with the firstName to "Suzy"
        Then I should receive a success response with status code 200

    Scenario: Successfully update user's lastName
        Given I have a user registered on the system
        When I update the user with the lastName to "Bae"
        Then I should receive a success response with status code 200

    Scenario: Successfully update user's gender
        Given I have a user registered on the system
        When I update the user with the gender to "other"
        Then I should receive a success response with status code 200

    Scenario: Successfully update user's dateOfBirth
        Given I have a user registered on the system
        When I update the user with the dateOfBirth to "2003-05-13"
        Then I should receive a success response with status code 200


