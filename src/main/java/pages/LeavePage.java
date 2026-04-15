package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.BasePage;

public class LeavePage extends BasePage {
    private By applyTab=By.xpath("//a[text()='Apply']");
    private By myLeaveTab=By.xpath("//a[text()='My Leave']");

    private By leaveTypeDropdown=By.xpath("//div[contains(text(),'-- Select --')]");
    private By leaveOption=By.xpath("//div[@role='listbox']//span");

    private By fromDate=By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]");
    private By toDate=By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]");

    private By applyBtn=By.xpath("//button[normalize-space()='Apply']");
    private By successMsg=By.xpath("//p[contains(text(),'Success')]");
    private By errorMsg=By.xpath("//span[contains(@class,'error')]");

    private By leaveStatus = By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'Pending') or contains(text(),'Scheduled')]");
    
    public LeavePage(WebDriver driver) {
        super(driver);
    }

    public void goToApply() {
        click(applyTab);
    }

    public void goToMyLeave() {
        click(myLeaveTab);
    }

    public boolean selectLeaveType() {
        By noLeaveMsg = By.xpath("//p[contains(text(),'No Leave Types')]");

        if (driver.findElements(noLeaveMsg).size() > 0) {
            System.out.println("⚠ No leave balance → skipping selection");
            return false;
        }
        By dropdown = By.xpath("//div[contains(@class,'oxd-select-text')]");
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By options = By.xpath("//div[@role='listbox']//span");
        wait.until(ExpectedConditions.visibilityOfElementLocated(options));
        driver.findElements(options).get(0).click();
        return true;
    }

    public void applyLeave(String from, String to) {
        WebElement fromInput = waitForVisibility(fromDate);
        fromInput.click();
        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        fromInput.sendKeys(from);

        WebElement toInput = waitForVisibility(toDate);
        toInput.click();
        toInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        toInput.sendKeys(to);

        click(applyBtn);
        try {
            By loader = By.xpath("//div[contains(@class,'oxd-form-loader')]");
            wait.until(ExpectedConditions.presenceOfElementLocated(loader));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            System.out.println("No loader, continuing...");
        }
    }

    public boolean isLeaveStatusDisplayed() {
        By rows = By.cssSelector(".oxd-table-card");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(rows));
            return driver.findElements(rows).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLeaveApplied() {
        try {
            By successMsg=By.xpath("//div[contains(@class,'toast-message') or contains(text(),'Success')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        By errorMsg=By.xpath("//span[contains(@class,'error')]");
        return isElementDisplayed(errorMsg);
    }
    
    public boolean isWarningDisplayed() {
        try {
            By toast = By.xpath("//div[contains(@class,'oxd-toast-content')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
            String text = driver.findElement(toast).getText();
            System.out.println("Toast: " + text);
            return text.toLowerCase().contains("fail") ||
                   text.toLowerCase().contains("error");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLeaveDropdownWorking() {
        click(leaveTypeDropdown);
        By options=By.xpath("//div[@role='listbox']//span");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(options));
            click(options);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLeavePageDisplayed() {
        By header = By.xpath("//h6[text()='Leave']");
        return isElementDisplayed(header);
    }
}