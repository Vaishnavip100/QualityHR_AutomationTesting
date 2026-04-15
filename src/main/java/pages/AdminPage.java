package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class AdminPage extends BasePage {
    private By addBtn=By.xpath("//button[normalize-space()='Add']");
    private By userRoleDropdown=By.xpath("(//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text')])[1]");
    private By employeeNameField=By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");
    private By statusDropdown=By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private By usernameField=By.xpath("//label[text()='Username']/../following-sibling::div//input");
    private By passwordField=By.xpath("(//input[@type='password'])[1]");
    private By confirmPasswordField=By.xpath("(//input[@type='password'])[2]");
    private By saveBtn=By.xpath("//button[@type='submit']");

    private By searchUsernameField=By.xpath("(//label[text()='Username']/../following-sibling::div//input)[1]");
    private By searchBtn=By.xpath("//button[normalize-space()='Search']");
    private By tableRows=By.cssSelector(".oxd-table-card");
    private By deleteBtn=By.cssSelector(".oxd-icon.bi-trash");
    private By confirmDeleteBtn=By.xpath("//button[normalize-space()='Yes, Delete']");
    private By noRecordMsg=By.xpath("//span[text()='No Records Found']");
    
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddUser() {
        click(addBtn);
    }

    public void createUser(String empName, String username, String password) {
        click(userRoleDropdown);
        driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        type(employeeNameField, empName);
	    By suggestion=By.xpath("//div[@role='listbox']//span");
	    waitForVisibility(suggestion);
	    click(suggestion);

        click(statusDropdown);
        driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        type(usernameField, username);
        type(passwordField, password);
        type(confirmPasswordField, password);

        click(saveBtn);
	    wait.until(ExpectedConditions.urlContains("viewSystemUsers"));
    }

    public void searchUser(String username) {
        type(searchUsernameField, username);
        click(searchBtn);
    }

    public boolean isUserFound(String username) {
        return !driver.findElements(tableRows).isEmpty();
    }

    public void deleteUser(String username) {
        if (isUserFound(username)) {
            click(deleteBtn);
            click(confirmDeleteBtn);
        }
    }

    public boolean isUserDeleted() {
        return isElementDisplayed(noRecordMsg);
    }
}