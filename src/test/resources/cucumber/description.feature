Feature: Find

  Scenario Outline: Find an expense record
    Given The is an expense with id 1 object with <description>
    When I look for <word>
    Then I get an expense with <description>
    Examples:
      | description | word |
      | test        | test |
      | mama jest   | jest |

  Scenario Outline: Delete an expense record
    Given The is an expense with id 1 object with <description>
    When I delete expense with description <word>
    Then I get an expense with <description>
    Examples:
      | description | word |
      | test        | test |
      | mama jest   | jest |

  Scenario: Delete 3 expenses from 5
    Given The is an expense with id 1 object with pierwszy opis
    And The is an expense with id 2 object with drugi opis
    And The is an expense with id 3 object with trzeci opis
    And The is an expense with id 4 object with czwarty opis
    And The is an expense with id 5 object with piaty

    When I delete expense with description opis
    Then I get an expense list with 4 elements
    But There will be 1 element in database

