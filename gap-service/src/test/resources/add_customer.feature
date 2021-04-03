Feature: Add new customer to the system

  Scenario: Add new customer to system
    Given A system without the following clients
      | test@test.com |
    When The customers enters their data in the system
      | email         | password | firstName | lastName | username  |
      | test@test.com | testpass | name      | lastName | nick_name |
    Then Clients have been created with the following data
      | email         | firstName | lastName | username  |
      | test@test.com | name      | lastName | nick_name |


  Scenario: Error adding a client with the same email
    Given A system with the following clients
      | email          | password | firstName | lastName | username  |
      | test2@test.com | testpass | name      | lastName | nick_name |
    Then The customers enters their data in the system, and the system return error
      | email          | password  | firstName | lastName  | username   |
      | test2@test.com | otherpass | name2     | lastName2 | nick_name2 |
