package steps;

import config.ConfigUrl;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AdPage;
import pages.CreateAdPage;
import pages.HomePage;
import pages.LoginWindow;

import static com.codeborne.selenide.Selenide.*;

public class AdSteps {

    private HomePage homePage;
    private LoginWindow loginWindow;
    private CreateAdPage createAdPage;
    private AdPage adPage;
    private User user = new User();

    @After
    public void closeBrowser() {
        closeWebDriver();
    }

    @Given("The user is authorized with password, the page with ads is open")
    public void openLoginPage() {
        homePage = open(ConfigUrl.URL,
                HomePage.class);
        user.userRegistrationApi();
        loginWindow = homePage.clickLoginAndRegistrationButton();
        homePage = loginWindow.login(user.getEmail(), user.getPassword());
        createAdPage = homePage.clickCreationAdButton();
    }

    @Given("User is authorized with password, the page with ads is open, ad created")
    public void createAndOpenAd() {
        openLoginPage();
        homePage = createAdPage.createAd("Тестовое объявление 59", "Продаю авто 59", "9999");
        homePage.search("Тестовое объявление 59");
        homePage.verifyFirstAdTitle("Тестовое объявление 59");
        adPage = homePage.firstAdPlateClick();
    }

    @When("Create Ad with data: {string}, {string}, {string}")
    public void login(String adName, String adDescription, String adPrice) {
        homePage = createAdPage.createAd(adName, adDescription, adPrice);
    }

    @When("Delete ad")
    public void deleteAd() {
        adPage.deleteAdClick();
    }

    @When("Edit ad with new data: {string}, {string}, {string}")
    public void deleteAd(String adName, String adDescription, String adPrice) {
        createAdPage = adPage.editAdClick();
        homePage = createAdPage.editAd(adName, adDescription, adPrice);
    }

    @Then("Ad {string} can be found in search")
    public void successResult(String expectedResult) {
        homePage.search(expectedResult);
        homePage.verifyFirstAdTitle(expectedResult);
        adPage = homePage.firstAdPlateClick();
        adPage.deleteAdClick();
    }

    @Then("Ad cannot be found in search")
    public void successResult() {
        homePage.search("Тестовое объявление 59");
        homePage.verifyNoResults("0 из 0");
    }

}
