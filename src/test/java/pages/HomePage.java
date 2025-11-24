package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Вход и регистрация')]")
    private SelenideElement loginAndRegistrationButton;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Разместить объявление')]")
    private SelenideElement creationAdButton;
    @FindBy(how = How.XPATH,using = "//input[@name='name']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Применить')]")
    private SelenideElement searchButton;
    @FindBy(how = How.XPATH,using = "//div[@class='card']")
    private SelenideElement firstAdPlate;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Выйти')]")
    SelenideElement exitButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div//h2")
    SelenideElement firstAdTitle;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[3]/p")
    SelenideElement results;

    public HomePage waitForLoad() {
        searchInput.shouldBe(visible, Duration.ofSeconds(1));
        firstAdPlate.shouldBe(visible, Duration.ofSeconds(1));
        searchButton.shouldBe(visible, Duration.ofSeconds(1));
        return this;
    }

    public void setSearch(String searchText) {
        searchInput.setValue(searchText);
    }

    public void searchButtonClick() {
        searchButton.click();
    }

    public LoginWindow clickLoginAndRegistrationButton() {
        loginAndRegistrationButton.click();
        LoginWindow loginWindow = page(LoginWindow.class);
        return loginWindow;
    }

    public CreateAdPage clickCreationAdButton() {
        creationAdButton.click();
        CreateAdPage createAdPage = page(CreateAdPage.class);
        createAdPage.waitForLoad();
        return createAdPage;
    }

    public void search(String searchText) {
        searchInput.click();
        setSearch(searchText);
        searchButtonClick();
        waitForLoad();
    }

    public AdPage firstAdPlateClick() {
        firstAdPlate.click();
        AdPage adPage = page(AdPage.class);
        adPage.waitForLoad();
        return adPage;
    }

    public void verifyFirstAdTitle(String expectedTitle) {
        firstAdTitle.shouldBe(visible).shouldHave(text(expectedTitle));
    }

    public void verifyNoResults(String expectedResult) {
        results.shouldBe(visible).shouldHave(text(expectedResult));
    }

    public void checkExitButton(String expectedResult) {
        exitButton.shouldBe(visible).shouldHave(text(expectedResult));
    }

}
