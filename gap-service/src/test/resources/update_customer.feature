Feature: Update customer data

  Scenario: Update customer data
    Given A system customer
      | email          | password | firstName | lastName | username  |
      | test3@test.com | testpass | name      | lastName | nick_name |
    Then Update customers with following data, and assert that
      | email          | password  | firstName | lastName  | username   |
      | test3@test.com | testpass2 | name2     | lastName2 | nick_name2 |