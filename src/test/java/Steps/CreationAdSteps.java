package Steps;

import PageObject.AdPage;
import PageObject.CreateAdPage;
import PageObject.HomePage;
import PageObject.LoginWindow;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreationAdSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private CreateAdPage createAdPage;
    private AdPage adPage;

    private static final Logger log = LoggerFactory.getLogger(RegistrationSteps.class);

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("The user {string} is authorized with password {string}, the page with ads is open")
    public void openLoginPage(String email, String password) {
        homePage = open("https://qa-desk.stand.praktikum-services.ru",
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        homePage = loginWindow.login(email, password);
        createAdPage = homePage.clickCreationAdButton();
    }

    @When("Create Ad with data: {string}, {string}, {string}")
    public void login(String adName, String adDescription, String adPrice) {
        homePage = createAdPage.createAd(adName, adDescription, adPrice);
    }

    @Then("Ad {string} can be found in search")
    public void successResult(String expectedResult) {
        homePage.search(expectedResult);
        SelenideElement resultElement = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/h2"));
        resultElement.shouldBe(visible);
        resultElement.shouldHave(text(expectedResult));
        String actualResult = resultElement.getText();
        assertEquals(expectedResult, actualResult);
        adPage = homePage.firstAdPlateClick();
        adPage.deleteAdClick();
    }

}
