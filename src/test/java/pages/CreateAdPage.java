package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class CreateAdPage {

    @FindBy(how = How.XPATH,using = "//input[@name='name']")
    private SelenideElement adNameInput;

    @FindBy(how = How.XPATH,using = "//textarea[@name='description']")
    private SelenideElement adDescriptionInput;

    @FindBy(how = How.XPATH,using = "//input[@name='price']")
    private SelenideElement adPriceInput;

    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Опубликовать')]")
    private SelenideElement publishButton;

    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Сохранить изменения')]")
    private SelenideElement editButton;

    public CreateAdPage waitForLoad() {
        adNameInput.shouldBe(visible, Duration.ofSeconds(1));
        return this;
    }

    public void setAdName(String adName) {
        adNameInput.setValue(adName);
    }

    public void setAdDescription(String adDescription) {
        adDescriptionInput.setValue(adDescription);
    }

    public void setAdPrice(String adPrice) {
        adPriceInput.setValue(adPrice);
    }

    public HomePage createAd(String adName, String adDescription, String adPrice) {
        setAdName(adName);
        setAdDescription(adDescription);
        setAdPrice(adPrice);
        publishButton.click();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoad();
        return homePage;
    }

    public HomePage editAd(String adName, String adDescription, String adPrice) {
        setAdName(adName);
        setAdDescription(adDescription);
        setAdPrice(adPrice);
        editButton.click();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoad();
        return homePage;
    }
}
