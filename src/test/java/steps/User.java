package steps;

import api.user.CreateUser;
import api.user.CreateUserData;
import com.codeborne.selenide.WebDriverRunner;
import config.DataGenerator;
import io.qameta.allure.Step;
import pages.HomePage;
import pages.LoginWindow;

public class User {

    private String email;
    private String password;
    private DataGenerator dataGenerator = new DataGenerator();
    private LoginWindow loginWindow;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    @Step("API user registration")
    public void userRegistrationApi(){
        email = dataGenerator.generateEmail();
        password = dataGenerator.generatePassword();
        WebDriverRunner.getWebDriver().manage().window().maximize();
        CreateUserData createUserData = new CreateUserData(email, password, password);
        CreateUser createUser = new CreateUser();
        createUser.createUser(createUserData).then().statusCode(201);
    }

    @Step("UI user authorization")
    public HomePage userLoginUi(HomePage homePage, String loginEmail, String loginPassword){
        loginWindow = homePage.clickLoginAndRegistrationButton();
        homePage = loginWindow.login(loginEmail, loginPassword);
        return homePage;
    }
}
