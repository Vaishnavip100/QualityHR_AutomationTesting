package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import utils.ConfigReader;

public class DashboardTest extends BaseTest {
    ConfigReader config = new ConfigReader();

    @Test
    public void verifyDashboardLoads() {
        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        Assert.assertTrue(dashboard.isDashboardDisplayed(),"Dashboard not loaded properly");
    }
}