package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DashboardPage extends BasePage {
    private By dashboardHeader=By.xpath("//h6[text()='Dashboard']");

    private By profileDropdown=By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logoutBtn=By.xpath("//a[text()='Logout']");

    private By adminMenu=By.xpath("//span[text()='Admin']");
    private By pimMenu=By.xpath("//span[text()='PIM']");
    private By leaveMenu=By.xpath("//span[text()='Leave']");
    private By myInfoMenu=By.xpath("//span[text()='My Info']");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    public void logout() {
        click(profileDropdown);
        click(logoutBtn);
    }

    
    public void goToAdmin() {
        click(adminMenu);
    }

    public void goToPIM() {
        click(pimMenu);
    }

    public void goToLeave() {
        click(leaveMenu);
    }

    public void goToMyInfo() {
        click(myInfoMenu);
    }
}