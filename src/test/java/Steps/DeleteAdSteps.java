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

public class DeleteAdSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private CreateAdPage createAdPage;
    private AdPage adPage;

    private static final Logger log = LoggerFactory.getLogger(RegistrationSteps.class);

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("User {string} is authorized with password {string}, the page with ads is open, ad created")
    public void createAndOpenAd(String email, String password) {
        homePage = open("https://qa-desk.stand.praktikum-services.ru",
                HomePage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        homePage = loginWindow.login(email, password);
        createAdPage = homePage.clickCreationAdButton();
        homePage = createAdPage.createAd("Тестовое объявление 59", "Продаю авто 59", "9999");
        homePage.search("Тестовое объявление 59");
        SelenideElement element = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/h2"));
        element.shouldBe(visible);
        element.shouldHave(text("Тестовое объявление 59"));
        adPage = homePage.firstAdPlateClick();
    }

    @When("Delete ad")
    public void deleteAd() {
        adPage.deleteAdClick();
    }

    @Then("Ad cannot be found in search")
    public void successResult() {
        homePage.search("Тестовое объявление 59");
        SelenideElement resultElement = $(byXpath("//*[@id=\"root\"]/div/div[2]/div[3]/p"));
        resultElement.shouldBe(visible);
        resultElement.shouldHave(text("0 из 0"));
        String actualResult = resultElement.getText();
        assertEquals("0 из 0", actualResult);
    }
}
