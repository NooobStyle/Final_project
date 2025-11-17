package Steps;

import PageObject.HomePage;
import PageObject.LoginWindow;
import io.cucumber.java.After;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;

    private static final Logger log = LoggerFactory.getLogger(RegistrationSteps.class);

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("opened login page")
    public void openLoginPage() {
        homePage = open("https://qa-desk.stand.praktikum-services.ru",
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
    }

    @When("input login data {string} and {string}")
    public void login(String email, String password) {
        homePage = loginWindow.login(email, password);
    }

    @Then("Login successful, avaliable {string} button")
    public void successResult(String expectedResult) {
        String actualResult = $(byXpath("//*[@id=\"root\"]/div/div[1]/div/div[1]/div/button")).shouldBe(visible).getText();
        assertEquals(expectedResult, actualResult);
    }

    @Then("login error {string} message shown")
    public void errorResult(String expectedResult) {
        String actualResult = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[1]/span")).shouldBe(visible).getText();
        assertEquals(expectedResult, actualResult);
    }

}
