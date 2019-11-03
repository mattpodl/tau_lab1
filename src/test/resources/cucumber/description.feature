Feature: Find
  Scenario Outline: Find a expense record
    Given The is an expense object with <description>
    When I look for <word>
    Then I get an expense with <description>
    Examples:
    | description |  word |
    | test       | test   |
    | mama jest najlepsza | jest |
