package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.LeavePage;
import utils.ConfigReader;

public class LeaveTest extends BaseTest {
    ConfigReader config = new ConfigReader();

    //Verify Leave Page Loads
    @Test
    public void verifyLeavePageLoad() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

        LeavePage leave = new LeavePage(getDriver());
        leave.goToApply();

//        Assert.assertTrue(true, "Leave page loaded");
    }

    //Apply Leave
    @Test
    public void applyLeaveTest() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

       LeavePage leave = new LeavePage(getDriver());
       leave.goToApply();

        if (!leave.selectLeaveType()) {
            System.out.println("Test skipped: No leave balance");
            return;
        }

        leave.applyLeave("2026-04-20", "2026-04-21");

        leave.goToMyLeave();

        Assert.assertTrue(
            leave.isLeaveStatusDisplayed(),
            "Leave not visible in list"
        );
    }

    //Verify Leave Status
    @Test
    public void verifyLeaveStatusTest() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

        LeavePage leave = new LeavePage(getDriver());
        leave.goToMyLeave();

        Assert.assertTrue(leave.isLeaveStatusDisplayed(),"Leave status not visible");
    }

    @Test
    public void applyPastDateLeaveTest() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.goToLeave();

        LeavePage leave = new LeavePage(getDriver());
        leave.goToApply();

        leave.selectLeaveType();

        leave.applyLeave("2023-01-01", "2023-01-02");

        Assert.assertTrue(leave.isWarningDisplayed(),"Warning not shown for past date");
    }
}