package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailedRetryAnalyzerReader implements IRetryAnalyzer
{
    //Create RetryAnalyzer Class
    private int retryCount=0;
    private int maxretryCount=2; // retry failed test case 2 times

    @Override
    public boolean retry(ITestResult result)
    {
        if(retryCount<maxretryCount)
            {
                retryCount++;
                return true; // re-run test
            }
            return false;
        }
}