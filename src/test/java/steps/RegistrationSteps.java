package steps;

import config.ConfigUrl;
import config.DataGenerator;
import pages.HomePage;
import pages.LoginWindow;
import pages.RegistrationWindow;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private RegistrationWindow registrationWindow;
    private DataGenerator dataGenerator = new DataGenerator();
    private String generatedEmail;
    private String generatedPassword;
    private User user = new User();


    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("opened registration page")
    public void openRegistrationPage() {
        homePage = open(ConfigUrl.URL,
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        registrationWindow = loginWindow.clickHaventLoginButton();
    }


    @When("input email and password")
    public void registration() {
        generatedEmail = dataGenerator.generateEmail();
        generatedPassword = dataGenerator.generatePassword();
        homePage = registrationWindow.register(generatedEmail, generatedPassword);
    }

    @When("input already exist email and password")
    public void registrationWithSameData() {
        user.userRegistrationApi();
        homePage = registrationWindow.register(user.getEmail(), user.getPassword());
    }

    @Then("open home page, avaliable {string} button")
    public void successResult(String expectedResult) {
        homePage.checkExitButton(expectedResult);
    }

    @Then("registration error {string} message shown")
    public void errorResult(String expectedResult) {
        registrationWindow.checkErrorMessage(expectedResult);
    }

}
