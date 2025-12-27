package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
//Retry Listener in Rest Assured automatically re-runs failed API test cases using TestNG to handle
// flaky failures caused by network or environment issues.
public class RetryTransformerReader implements IAnnotationTransformer
{
    //Retry via Listener (Project-Wide – Real Time)
    //Instead of adding retry to every test, use ITestListener + AnnotationTransformer
    public void tranformer(ITestAnnotation testAnnotation, Class testClass, Method testMethod, Constructor testConstructor)
    {
        testAnnotation.setRetryAnalyzer(FailedRetryAnalyzerReader.class);
    }
}
