package PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationWindow {
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[3]/button[1]")
    private SelenideElement createUserButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[3]/button[2]")
    private SelenideElement alreadyHaveLoginButton;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[1]/div/div/input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[2]/div/div/input")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/div[2]/div[5]/form/div[2]/div[3]/div/div/input")
    private SelenideElement confirmPasswordInput;

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
}
