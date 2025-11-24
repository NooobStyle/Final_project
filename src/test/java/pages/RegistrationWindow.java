package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationWindow {
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Создать аккаунт')]")
    private SelenideElement createUserButton;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Уже есть аккаунт')]")
    private SelenideElement alreadyHaveLoginButton;
    @FindBy(how = How.XPATH,using = "//input[@name='email']")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH,using = "//input[@name='password']")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH,using = "//input[@name='submitPassword']")
    private SelenideElement confirmPasswordInput;
    @FindBy(how = How.XPATH,using = "//span[@class='input_span__yWPqB']")
    private SelenideElement errorMessage;

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordInput.setValue(password);
    }

    public void clickAlreadyHaveLoginButton() {
        alreadyHaveLoginButton.click();
    }

    public void clickCreateUserButton() {
        createUserButton.click();
    }

    public HomePage register(String email, String password){
        setEmail(email);
        setPassword(password);
        setConfirmPassword(password);
        clickCreateUserButton();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoad();
        return homePage;
    }

    public void checkErrorMessage(String expectedResult) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedResult));
    }
}
