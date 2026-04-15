package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import utils.ConfigReader;
import utils.ExcelUtil;

public class LoginTest extends BaseTest {
	ConfigReader config = new ConfigReader();
    @DataProvider(name="loginData")
    public Object[][] getLoginData() {return ExcelUtil.getData(config.getTestDataPath(),"LoginData");
    }

    @Test(dataProvider="loginData")
    public void loginTest(String username,String password,String expected) {
        LoginPage login=new LoginPage(getDriver());
        login.login(username,password);

        if (expected.equalsIgnoreCase("valid")) {
            DashboardPage dashboard=new DashboardPage(getDriver());
            Assert.assertTrue(dashboard.isDashboardDisplayed(),"Valid login failed");
        } else {
        	Assert.assertTrue(login.getErrorMessage().contains("Invalid"),"Invalid login error not shown");
        }
    }
    
    //Verify with empty username and password
    @Test
    public void emptyLoginTest() {
        LoginPage login=new LoginPage(getDriver());
        login.clickLogin();

        Assert.assertTrue(login.isRequiredMessageDisplayed(),"Validation message not shown");
    }
    
    //Verify logout
    @Test
    public void logoutTest() {
        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(),config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        dashboard.logout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("login"),"Logout failed");
    }
}