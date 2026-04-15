package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import com.aventstack.extentreports.*;
import utils.ExtentManager;

import org.openqa.selenium.*;
import base.BaseTest;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class TestListener implements ITestListener {
    private static ExtentReports extent=ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        Object currentClass=result.getInstance();
        WebDriver driver=((BaseTest) currentClass).getDriver();

        TakesScreenshot ts=(TakesScreenshot) driver;
        File src=ts.getScreenshotAs(OutputType.FILE);

        String folderPath=System.getProperty("user.dir") + "/screenshots/";
        File folder=new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String path=folderPath + result.getMethod().getMethodName() + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}