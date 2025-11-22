Feature: Authorization
  Scenario: Successful authorization
    Given opened login page
    When input login data
    Then Login successful, avaliable "Выйти" button

  Scenario: Authorization with incorrect data
    Given opened login page
    When input wrong login data
    Then login error "Логин или пароль неверны" message shown