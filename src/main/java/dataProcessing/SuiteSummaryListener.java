package dataProcessing;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SuiteSummaryListener implements ITestListener {

    int passCount = 0;
    int skipCount = 0;
    int failCount = 0;

    ExtentReports extent;
    ExtentTest suiteSummaryTest;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        suiteSummaryTest = extent.createTest("Suite Summary");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passCount++;
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failCount++;
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.fail("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipCount++;
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        String overallStatus = "PASS";

        if (failCount > 0) {
            overallStatus = "FAIL";
        } else if (skipCount >= passCount) {
            overallStatus = "SKIPPED";
        }

        suiteSummaryTest.info("Total Passed: " + passCount);
        System.out.println("Total Passed: " + passCount);
        
        suiteSummaryTest.info("Total Skipped: " + skipCount);
        System.out.println("Total Skipped: " + skipCount);
        
        suiteSummaryTest.info("Total Failed: " + failCount);
        System.out.println("Total Failed: " + failCount);
        suiteSummaryTest.info("OVERALL Suite Result: " + overallStatus);
        System.out.println("OVERALL Suite Result: " + overallStatus);


        System.out.println("========= OVERALL Suite Result: " + overallStatus + " =========");
    }
}
