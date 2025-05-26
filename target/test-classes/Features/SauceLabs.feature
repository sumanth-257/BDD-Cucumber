Feature: Adding Product to Cart in SwagLabs

  Background: 
    Given User is on SwagLabs Login page
    When User enters valid credentials
    Then User should be redirected to Products

  Scenario: Adding Product to Cart
    When User filter the product by price
    Then add first product to the cart
    And opens cart to print the added product
