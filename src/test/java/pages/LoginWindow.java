package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginWindow {
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Войти')]")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Нет аккаунта')]")
    private SelenideElement haventLoginButton;
    @FindBy(how = How.XPATH,using = "//input[@name='email']")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH,using = "//input[@name='password']")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH,using = "//span[@class='input_span__yWPqB']")
    private SelenideElement errorMessage;

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public RegistrationWindow clickHaventLoginButton() {
        haventLoginButton.click();
        RegistrationWindow registrationWindow = page(RegistrationWindow.class);
        return registrationWindow;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public HomePage login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoad();
        return homePage;
    }

    public void checkErrorMessage(String expectedResult) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedResult));
    }
}
