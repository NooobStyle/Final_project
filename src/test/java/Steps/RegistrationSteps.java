package Steps;

import PageObject.HomePage;
import PageObject.LoginWindow;
import PageObject.RegistrationWindow;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private RegistrationWindow registrationWindow;

    private static final Logger log = LoggerFactory.getLogger(RegistrationSteps.class);

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("opened registration page")
    public void openRegistrationPage() {
        homePage = open("https://qa-desk.stand.praktikum-services.ru",
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        registrationWindow = loginWindow.clickHaventLoginButton();
    }

    @When("input email {string} and password {string}")
    public void registration(String email, String password) {
        homePage = registrationWindow.register(email, password);
    }

    @Then("open home page, avaliable {string} button")
    public void successResult(String expectedResult) {
        String actualResult = $(byXpath("//*[@id=\"root\"]/div/div[1]/div/div[1]/div/button")).shouldBe(visible).getText();
        assertEquals(expectedResult, actualResult);
    }

    @Then("registration error {string} message shown")
    public void errorResult(String expectedResult) {
        String actualResult = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[1]/span")).shouldBe(visible).getText();
        assertEquals(expectedResult, actualResult);
    }

}
