package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class PimPage extends BasePage {
	private By addEmployeeBtn=By.xpath("//button[normalize-space()='Add']");

	private By firstName=By.name("firstName");
	private By lastName=By.name("lastName");

	private By searchNameInput=By.xpath("//input[@placeholder='Type for hints...']");
	private By searchBtn=By.xpath("//button[normalize-space()='Search']");
	private By saveBtn=By.xpath("//button[@type='submit']");

	private By noRecordMsg=By.xpath("//span[text()='No Records Found']");
	private By employeeIdField=By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");
    
    public PimPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddEmployee() {
        click(addEmployeeBtn);
    }

    public void addEmployee(String fName,String lName) {
        type(firstName, fName);
        type(lastName, lName);
        String empId=String.valueOf(System.currentTimeMillis()).substring(7);

        WebElement empIdInput=waitForVisibility(employeeIdField);
        empIdInput.clear();
        empIdInput.sendKeys(empId);

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        click(saveBtn);

        By loader=By.xpath("//div[contains(@class,'oxd-form-loader')]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

        wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }

    public void searchEmployee(String name) {
        type(searchNameInput, name);
        By suggestion=By.xpath("//div[@role='listbox']//span");
        try {
            WebDriverWait shortWait=new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
            click(suggestion);
        } catch (Exception e) {
            System.out.println("No suggestion dropdown");
        }
        click(searchBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
    }
    
    public void clickSave() {
        click(saveBtn);
    }
    
    public boolean isEmployeeFound(String name) {
        By employee=By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + name + "')]");
        return isElementDisplayed(employee);
    }

    public boolean isNoRecordFound() {
        return isElementDisplayed(noRecordMsg);
    }
    
    
    //Form Validation 
    By firstNameError=By.xpath("//input[@name='firstName']/ancestor::div[contains(@class,'oxd-input-group')]//span");
    By lastNameError=By.xpath("//input[@name='lastName']/ancestor::div[contains(@class,'oxd-input-group')]//span");
    By dateField=By.xpath("//label[contains(text(),'Date')]/../following-sibling::div//input");
    By dateError=By.xpath("//span[contains(@class,'error')]");

    public boolean isRequiredFieldErrorDisplayed() {
        return isElementDisplayed(firstNameError) || isElementDisplayed(lastNameError);
    }

    public void enterInvalidDate(String invalidDate) {
        WebElement dateInput=waitForVisibility(dateField);
        dateInput.clear();
        dateInput.sendKeys(invalidDate);
        dateInput.sendKeys(Keys.TAB); 
    }

    public boolean isDateErrorDisplayed() {
        return isElementDisplayed(dateError);
    }

    public boolean isDropdownSelectable() {
        By dropdown=By.xpath("//div[contains(text(),'-- Select --')]");
        click(dropdown);
        By options=By.xpath("//div[@role='listbox']//span");
        try {
            waitForVisibility(options);
            click(options);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void openEmployee(String name) {
        By employee=By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + name + "')]");
        click(employee);
    }

    public boolean isEmployeeDetailsDisplayed(String firstName) {
        By firstNameField=By.name("firstName");
        try {
            String value=waitForVisibility(firstNameField).getAttribute("value");
            return value.equalsIgnoreCase(firstName);
        } catch (Exception e) {
            return false;
        }
    }
}