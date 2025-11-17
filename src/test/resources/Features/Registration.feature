Feature: Registration
  Scenario: Successful registration
    Given opened registration page
    When input email "dmitriev_1015@gmail.com" and password "2294"
    Then open home page, avaliable "Выйти" button

  Scenario: Re-registration with already used data
    Given opened registration page
    When input email "dmitriev_4999@gmail.com" and password "2294"
    Then registration error "Ошибка" message shown