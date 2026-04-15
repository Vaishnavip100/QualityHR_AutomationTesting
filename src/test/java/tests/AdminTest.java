package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.AdminPage;
import pages.PimPage;
import utils.ConfigReader;

public class AdminTest extends BaseTest {

    ConfigReader config = new ConfigReader();

    @Test
    public void userManagementTest() {
        String firstName="Vaishnavi" + System.currentTimeMillis();
        String lastName="Perumalla";
        String username="user" + System.currentTimeMillis();
        String password="Test@123";

        LoginPage login=new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        DashboardPage dashboard=new DashboardPage(getDriver());
        dashboard.goToPIM();
        
        PimPage pim=new PimPage(getDriver());
        pim.clickAddEmployee();
        pim.addEmployee(firstName, lastName);

        String employeeName=firstName + " " + lastName;

        dashboard.goToAdmin();

        AdminPage admin=new AdminPage(getDriver());
        admin.clickAddUser();
        admin.createUser(employeeName, username, password);
        admin.searchUser(username);
        Assert.assertTrue(admin.isUserFound(username), "User not found");

        admin.deleteUser(username);
        admin.searchUser(username);
        Assert.assertTrue(admin.isUserDeleted(), "User still exists after deletion");
    }
}