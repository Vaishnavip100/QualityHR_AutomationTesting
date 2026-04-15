package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class LoginPage extends BasePage {
    By username=By.name("username");
    By password=By.name("password");
    By loginBtn=By.xpath("//button[@type='submit']");
    By errorMsg=By.xpath("//p[contains(@class,'alert')]");
    By requiredMsg=By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }
    
    public void clickLogin() {
        click(loginBtn);
    }
    
    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMsg);
    }

    public boolean isRequiredMessageDisplayed() {
        return isElementDisplayed(requiredMsg);
    }
}