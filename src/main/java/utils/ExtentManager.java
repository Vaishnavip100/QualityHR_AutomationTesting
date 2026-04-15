package utils;

import java.io.File;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent==null) {
        	String reportPath=System.getProperty("user.dir") + "/reports/";
        	File folder=new File(reportPath);
        	if (!folder.exists()) {
        	    folder.mkdirs();
        	}
        	ExtentSparkReporter spark=new ExtentSparkReporter(reportPath + "ExtentReport.html");

            spark.config().setReportName("OrangeHRM Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Vaishnavi");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}