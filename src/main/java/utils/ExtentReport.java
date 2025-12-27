package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;

public class ExtentReport
{
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static void Initialize(String path)
    {
        if (extentReports==null)
        {
            //step1 Create Spark Reporter (HTML)
            ExtentSparkReporter spark = new ExtentSparkReporter(path);

            //step2 Load XML config
            try
            {
                spark.loadXMLConfig(new File("src/test/extent-config.xml"));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            //step3 Create ExtentReports and attach reporter
            extentReports=new ExtentReports();
            extentReports.attachReporter(spark);

            //step4 System information
            extentReports.setSystemInfo("Hostname",System.getProperty("user.name"));
            extentReports.setSystemInfo("Environment","QA");
            extentReports.setSystemInfo("Operating System","Mac");
            extentReports.setSystemInfo("Author","Dipti Ranjan Dash");
        }
    }

}
