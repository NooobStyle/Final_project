package steps;

import api.user.CreateUser;
import api.user.CreateUserData;
import com.codeborne.selenide.WebDriverRunner;
import config.DataGenerator;
import io.qameta.allure.Step;

public class User {

    private String email;
    private String password;
    private DataGenerator dataGenerator = new DataGenerator();

    /**public User(String email, String password) {
        this.email = email;
        this.password = password;
    }**/
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    @Step("Регистрация пользователя через API")
    public void userRegistrationApi(){
        email = dataGenerator.generateEmail();
        password = dataGenerator.generatePassword();
        WebDriverRunner.getWebDriver().manage().window().maximize();
        CreateUserData createUserData = new CreateUserData(email, password, password);
        CreateUser createUser = new CreateUser();
        createUser.createUser(createUserData).then().statusCode(201);
    }
}
