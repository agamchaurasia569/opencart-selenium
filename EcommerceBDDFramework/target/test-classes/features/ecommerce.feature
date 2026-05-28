Feature: E-Commerce End-to-End Flow

  Scenario Outline: Complete shopping flow using Excel login data
    Given User launches the browser
    When User reads username and password from Excel row <excelRow>
    Then User should be logged in successfully
    When User searches for product "<searchProduct>"
    And User applies filter "<filterValue>"
    And User adds multiple products to cart
    And User removes one product from cart
    Then User validates total amount in cart
    And User proceeds to checkout
    And User logs out

    Examples:
      | excelRow | searchProduct | filterValue |
      | 1        | Shoes         | Fashion     |
      | 2        | Laptop        | Electronics |
