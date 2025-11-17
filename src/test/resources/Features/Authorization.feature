Feature: Authorization
  Scenario: Successful authorization
    Given opened login page
    When input login data "dmitriev_4999@gmail.com" and "2294"
    Then Login successful, avaliable "Выйти" button

  Scenario: Authorization with incorrect data
    Given opened login page
    When input login data "dmitriev_14999@gmail.com" and "2294"
    Then login error "Логин или пароль неверны" message shown