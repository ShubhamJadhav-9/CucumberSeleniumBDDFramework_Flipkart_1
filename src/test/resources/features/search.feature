@smoke @regression
Feature: Product Search on Flipkart

  @valid
  Scenario Outline: Valid search for multiple products
    Given user is on Flipkart homepage
    When user searches for "<product>"
    Then search results should be displayed for "<product>"
    Examples:
      | product            |
      | iPhone 16          |
      | Samsung Galaxy     |
      | Wireless Earbuds   |
      | Laptop             |

  @invalid
  Scenario: Search with special characters
    Given user is on Flipkart homepage
    When user searches for "@#$%^"
    Then search results should be displayed for "@#$%^"