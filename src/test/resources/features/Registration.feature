Feature: Registration
  Scenario: Successful registration
    Given opened registration page
    When input email and password
    Then open home page, avaliable "Выйти" button

  Scenario: Re-registration with already used data
    Given opened registration page
    When input already exist email and password
    Then registration error "Ошибка" message shown