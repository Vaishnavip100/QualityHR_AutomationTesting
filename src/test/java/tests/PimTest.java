package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.PimPage;
import utils.ConfigReader;

public class PimTest extends BaseTest {
    ConfigReader config=new ConfigReader();

    String firstName="Vaishnavi" + System.currentTimeMillis();
    String lastName="Perumalla";

    //Add Employee
    @Test(priority=1)
    public void addEmployeeTest() {
        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(),config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        dashboard.goToPIM();

        PimPage pim=new PimPage(getDriver());
        pim.clickAddEmployee();
        pim.addEmployee(firstName,lastName);

        dashboard.goToPIM();
        pim.searchEmployee(firstName);

        Assert.assertTrue(pim.isEmployeeFound(firstName),"Employee not found after adding");
    }

    //Search Employee
    @Test(priority=2)
    public void searchEmployeeTest() {
        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(),config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        dashboard.goToPIM();

        PimPage pim=new PimPage(getDriver());

        pim.clickAddEmployee();
        pim.addEmployee(firstName,lastName);

        dashboard.goToPIM();

        pim.searchEmployee(firstName);

        Assert.assertTrue(pim.isEmployeeFound(firstName),"Employee not found in search");
    }

    //Invalid Search
    @Test(priority=3)
    public void invalidSearchTest() {
        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(),config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        dashboard.goToPIM();

        PimPage pim=new PimPage(getDriver());
        pim.searchEmployee("InvalidName123");
        
        Assert.assertTrue(pim.isNoRecordFound(),"Invalid search should show no records");
    }
}