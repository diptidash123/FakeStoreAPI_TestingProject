package core;

import com.aventstack.extentreports.Status;
import helper.BasetestHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest_Extent
{
    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException
    {
        String subfolderpath = System.getProperty("user.dir") + "/Reports/" + BasetestHelper.Timestamp();
        BasetestHelper.createfolder(subfolderpath);
        ExtentReport.Initialize(subfolderpath + "/API_Automation.html");
    }

    @BeforeMethod(alwaysRun = true)
    public void startReport(Method method)
    {
        ExtentReport.extentTest = ExtentReport.extentReports.createTest(method.getName());
    }
    @AfterMethod(alwaysRun = true)
    public void fetchResult(ITestResult result)
    {
        if (result.getStatus()==ITestResult.SUCCESS)
        {
            ExtentReport.extentTest.log(Status.PASS,"Test Case" + " " + result.getName() + " is passed ");
        }
        if (result.getStatus()==ITestResult.CREATED)
        {
            ExtentReport.extentTest.log(Status.PASS,"Test Case" + " " + result.getName() + " is passed ");
        }
        if (result.getStatus()==ITestResult.FAILURE)
        {
            ExtentReport.extentTest.log(Status.FAIL, "Test Case " + " " + result.getName() + " is failed ");
            ExtentReport.extentTest.log(Status.FAIL, "Test Case is failed due to:" + " " + result.getThrowable());
        }
        if (result.getStatus()==ITestResult.SKIP)
        {
            ExtentReport.extentTest.log(Status.SKIP,"Test Case" + " " + result.getName() + " is skipped ");
        }
    }
    @AfterSuite(alwaysRun = true)
    public void endReport()
    {
        if (ExtentReport.extentReports!=null)
        {
            ExtentReport.extentReports.flush();
        }
    }

}
