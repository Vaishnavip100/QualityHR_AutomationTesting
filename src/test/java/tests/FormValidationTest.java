package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.LeavePage;
import pages.PimPage;
import utils.ConfigReader;

public class FormValidationTest extends BaseTest {
    ConfigReader config = new ConfigReader();

    //Empty Mandatory Fields Validation
    @Test
    public void verifyEmptyMandatoryFields() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToPIM();

        PimPage pim = new PimPage(getDriver());
        pim.clickAddEmployee();

        pim.clickSave();

        Assert.assertTrue(
                pim.isRequiredFieldErrorDisplayed(),
                "Required field validation not displayed"
        );
    }

    // Invalid Date Validation
    @Test
    public void verifyInvalidDateFormat() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

       LeavePage leave = new LeavePage(getDriver());

        leave.goToApply();
        leave.selectLeaveType();

       leave.applyLeave("12345", "67890");
       Assert.assertTrue(leave.isErrorDisplayed() || leave.isWarningDisplayed(),"Invalid date validation not shown");
    }

    // Dropdown Validation
    @Test
    public void verifyDropdownSelection() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

        LeavePage leave = new LeavePage(getDriver());

        Assert.assertTrue(
                leave.isLeaveDropdownWorking(),
                "Dropdown not working"
        );
    }
}