package PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[1]/div/button[1]")
    private SelenideElement loginAndRegistrationButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[1]/div/button")
    private SelenideElement creationAdButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/form/div[1]/div/div/input")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/form/div[2]/button")
    private SelenideElement searchButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[1]/h2")
    private SelenideElement firstAdPlate;

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
}
