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

import static com.codeborne.selenide.Selenide.*;

public class AuthorizationSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private String email;
    private String password;
    private DataGenerator dataGenerator = new DataGenerator();
    private User user = new User();

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
        WebDriverRunner.getWebDriver().manage().window().maximize();
        user.userRegistrationApi();
        homePage = loginWindow.login(user.getEmail(), user.getPassword());
    }

    @When("input wrong login data")
    public void loginWithWrongData() {
        email = dataGenerator.generateEmail();
        password = dataGenerator.generatePassword();
        homePage = loginWindow.login(email, password);
    }

    @Then("Login successful, avaliable {string} button")
    public void successResult(String expectedResult) {
        homePage.checkExitButton(expectedResult);
    }

    @Then("login error {string} message shown")
    public void errorResult(String expectedResult) {
        loginWindow.checkErrorMessage(expectedResult);
    }

}
