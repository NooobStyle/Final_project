Feature: Working with Ads
  Scenario: Ad creation
    Given The user is authorized with password, the page with ads is open
    When Create Ad with data: "Тестовое объявление 49", "Продам автомобиль 49", "9999"
    Then Ad "Тестовое объявление 49" can be found in search

  Scenario: Ad delete
    Given User is authorized with password, the page with ads is open, ad created
    When Delete ad
    Then Ad cannot be found in search

  Scenario: Ad edit
    Given User is authorized with password, the page with ads is open, ad created
    When Edit ad with new data: "Тестовое объявление 99", "Продам автомобиль 99", "10000"
    Then Ad "Тестовое объявление 99" can be found in search