package PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class CreateAdPage {

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div/form/div[2]/div[1]/div/div/input")
    private SelenideElement adNameInput;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div/form/div[4]/div/textarea")
    private SelenideElement adDescriptionInput;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div/form/div[5]/div/div/input")
    private SelenideElement adPriceInput;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div/form/button")
    private SelenideElement publishButton;

    public CreateAdPage waitForLoad() {
        adNameInput.shouldBe(visible, Duration.ofSeconds(1));
        publishButton.shouldBe(visible, Duration.ofSeconds(1)).shouldBe(enabled);
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
}
