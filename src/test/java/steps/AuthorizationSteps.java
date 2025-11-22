package steps;

import config.ConfigUrl;
import config.DataGenerator;
import pages.HomePage;
import pages.LoginWindow;
import io.cucumber.java.After;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CreateUser;
import utils.CreateUserData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private String email;
    private String password;
    private DataGenerator dataGenerator = new DataGenerator();

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("opened login page")
    public void openLoginPage() {
        homePage = open(ConfigUrl.URL,
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
    }

    @When("input login data")
    public void login() {
        email = dataGenerator.generateEmail();
        password = dataGenerator.generatePassword();
        WebDriverRunner.getWebDriver().manage().window().maximize();
        CreateUserData createUserData = new CreateUserData(email, password, password);
        CreateUser createUser = new CreateUser();
        createUser.createUser(createUserData).then().statusCode(201);
        homePage = loginWindow.login(email, password);
    }

    @When("input wrong login data")
    public void loginWithWrongData() {
        email = dataGenerator.generateEmail();
        password = dataGenerator.generatePassword();
        homePage = loginWindow.login(email, password);
    }

    @Then("Login successful, avaliable {string} button")
    public void successResult(String expectedResult) {
        $(byXpath("//*[@id=\"root\"]/div/div[1]/div/div[1]/div/button")).shouldHave(text(expectedResult));
    }

    @Then("login error {string} message shown")
    public void errorResult(String expectedResult) {
        $(byXpath("//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[1]/span")).shouldHave(text(expectedResult));
    }

}
