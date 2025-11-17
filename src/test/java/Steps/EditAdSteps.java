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

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditAdSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private CreateAdPage createAdPage;
    private AdPage adPage;

    private static final Logger log = LoggerFactory.getLogger(RegistrationSteps.class);

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("User is authorized, ad created")
    public void createAndOpenAd() {
        homePage = open("https://qa-desk.stand.praktikum-services.ru",
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        homePage = loginWindow.login("dmitriev_4999@gmail.com", "2294");
        createAdPage = homePage.clickCreationAdButton();
        homePage = createAdPage.createAd("Тестовое объявление 59", "Продаю авто 59", "9999");
        homePage.search("Тестовое объявление 59");
        SelenideElement element = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/h2"));
        element.shouldBe(visible);
        element.shouldHave(text("Тестовое объявление 59"));
        adPage = homePage.firstAdPlateClick();
    }

    @When("Edit ad with new data: {string}, {string}, {string}")
    public void deleteAd(String adName, String adDescription, String adPrice) {
        createAdPage = adPage.editAdClick();
        homePage = createAdPage.createAd(adName, adDescription, adPrice);
    }

    @Then("After edit add {string} can be found in search")
    public void successResult(String expectedResult) {
        homePage.search(expectedResult);
        SelenideElement resultElement = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/h2"));
        resultElement.shouldBe(visible, Duration.ofSeconds(1));
        resultElement.shouldHave(text(expectedResult));
        String actualResult = resultElement.getText();
        assertEquals(expectedResult, actualResult);
        adPage = homePage.firstAdPlateClick();
        adPage.deleteAdClick();
    }
}
