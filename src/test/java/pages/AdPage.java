package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AdPage {

    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Удалить')]")
    private SelenideElement deleteAdButton;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Редактировать объявление')]")
    private SelenideElement editAdButton;

    public AdPage waitForLoad() {
        deleteAdButton.shouldBe(visible, Duration.ofSeconds(1)).shouldBe(enabled);;
        editAdButton.shouldBe(visible, Duration.ofSeconds(1)).shouldBe(enabled);
        return this;
    }

    public void deleteAdClick() {
        deleteAdButton.click();
    }

    public CreateAdPage editAdClick() {
        editAdButton.click();
        CreateAdPage createAdPage = page(CreateAdPage.class);
        createAdPage.waitForLoad();
        return createAdPage;
    }
}
