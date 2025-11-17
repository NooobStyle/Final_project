package PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginWindow {
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[3]/button[1]")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[3]/button[2]")
    private SelenideElement haventLoginButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[1]/div/div/input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[2]/div/div/input")
    private SelenideElement passwordInput;

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
}
