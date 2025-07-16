
package knr_runner_Execution;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BasicFunctions;
import browsers.BrowserManager;

import dataProcessing.KnrRCReadExcelFiles;
import knrPageModules.KnrLoginPage;
import knrPageModules.KnrReportEnrollmentPage;
import knrPageModules.KnrReportEnrollmentPageCopy;
import knrPageModules.KnrReportEnrollmentPageCourseNameValidation;
import knrPageModules.KnrReportEnrollmentPageForAddtionalParameter;


public class Knr_runner_Execution_Copy extends BrowserManager {

public Object[][] data1;
public Object[][] data2;

public Object[][] data3;
public Object[][] data4;

Set<String> processedRegNos = new HashSet<>();

//Declare a static flag outside for the stop print the same word in report again and again

private static boolean isTestCaseCourseSet = false;
private static boolean isTestCaseEnrollSet1 = false;
	// private ExtentReports extent;
	

	 ExtentReports extentReport ;
	 ExtentSparkReporter report ;
	 ExtentTest testCaseName;

	 
	 
	KnrLoginPage knrLogin = new KnrLoginPage();


	KnrReportEnrollmentPageCourseNameValidation knrEnrollment = new  KnrReportEnrollmentPageCourseNameValidation();

	KnrReportEnrollmentPageForAddtionalParameter KnrEnrollmentPageForAdditionalParameter = new  KnrReportEnrollmentPageForAddtionalParameter();
	

	    @DataProvider(name = "KNR_RC")
	    public Object[][] getDataForKNR_RC() throws IOException {
	        Map<String, KnrRCReadExcelFiles.StudentInfo> groupedData =
	        		KnrRCReadExcelFiles.readExcel("excel.file1", "excel.file1");

	        List<Object[]> data = new ArrayList<>();
	        for (Map.Entry<String, KnrRCReadExcelFiles.StudentInfo> entry : groupedData.entrySet()) {
	            String regNo = entry.getKey();
	            KnrRCReadExcelFiles.StudentInfo info = entry.getValue();

	            // You can extract or loop through subject keys to generate subject-specific data.
	            for (String subject : info.subjectsAndMarks.keySet()) {
	                data.add(new Object[]{regNo, info, subject});
	            }
	        }

	        return data.toArray(new Object[0][0]);
	    }
	

	
	
	private static int totalPassed = 0;
	private static int totalFailed = 0;




	
//General login and logout
@Test(priority = 1, enabled = false)
public void testCase1() throws InterruptedException, IOException {
	testCaseName = extentReport.createTest("Login Page Actions");
	
	System.out.println("This is a Normal Test Case1");

    // Perform LoginPage Actions
	knrLogin.DirectSignIn();
	    knrLogin.DirectPassEntry();
	    knrLogin.DirectUserEntry();

 	    // Validate Login Failures and Success
	    knrLogin.LoginInFail();
//	   	login.Login();

//	    SetAssignCourse.check();
	    // Perform Logout and Re-login
	
//	    
//	    Therory.AttendanceMarkNavigation();
//	    Therory.TheroryAttendanceNavigation();
//	    Therory.TheroryAttendanceBrowse();
//	    TRData.ResultDeliverablesNavigation();
//	    TRData.ResultDeliverablesTRDataClgWiseNavigation();

	}	

@Test(priority = 5, enabled = true, dataProvider = "KNR_RC")
public void testCase6(String regNo, KnrRCReadExcelFiles.StudentInfo studentInfo, String subjectToFind)
        throws InterruptedException, IOException, AWTException {
	
    if (!isTestCaseEnrollSet1) {
        isTestCaseEnrollSet1 = true;
        Browser_Launch();
        knrLogin.Login();
        knrEnrollment.ReportCardNavigation();
    }

    String examDate = (String) studentInfo.examSeries;
    String awardName = (String) studentInfo.awardName;
    String semester = (String) studentInfo.semYear;
    String regulation = (String) studentInfo.regulation;
    String examType = (String) studentInfo.examType;
    
    Map<String, String> subjects = studentInfo.subjectsAndMarks;
    System.out.println("subjects"+subjects);
    System.out.println("regno" + regNo);
    System.out.println("Exam Date: " + examDate);
    System.out.println("Award Name: " + awardName);
    System.out.println("Semester: " + semester);
    System.out.println("Regulation: " + regulation);
    System.out.println("Exam Type: " + examType);

    System.out.println(processedRegNos+"processedRegNos");
	
	if (!processedRegNos.contains(regNo)) {
    	String currentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		 testCaseName = extentReport.createTest("Report Card Enrollment Page Actions for the following register number: "+regNo);
			
		for (String handle : windowHandles) {
		    if (!handle.equals(currentWindow)) {
		    	System.out.println("Before closing: " + driver.getWindowHandles());
		    	driver.close();
		    	Set<String> newHandles = driver.getWindowHandles();
		    	System.out.println("After closing: " + newHandles);
		        driver.switchTo().window(handle); // switch to another open window
		        break;
		    }
		}
	
		ExtentTest testCaseScenario = testCaseName.createNode("Starting testCase execution for the register number: " + regNo );
	 	   
    	   // Call Enrollment methods only if regNo is not processed
    	knrEnrollment.ReportCardEnrollNavigation(testCaseScenario);
    	knrEnrollment.EnrollmentRegNo(regNo, testCaseScenario);
    	knrEnrollment.EnrollmentExamDate(examDate, testCaseScenario);
    	knrEnrollment.EnrollmentAwardName(awardName, testCaseScenario);
        knrEnrollment.EnrollmentSemester(semester, testCaseScenario);
        knrEnrollment.EnrollmentRegulation(regulation, testCaseScenario);
        knrEnrollment.EnrollmentExamType(examType, testCaseScenario);
        knrEnrollment.submitButton(testCaseScenario);
        knrEnrollment.DownloadReport(regNo,testCaseScenario);
        System.out.println("regno" + regNo);
        // Mark regNo as processed
        processedRegNos.add(regNo);
    }

   
    for (Map.Entry<String, String> entry : subjects.entrySet()) {
        String subjectName = entry.getKey();

        if (!subjectName.equalsIgnoreCase(subjectToFind)) {
            continue; // Skip if not the subject we want
        }

        String[] marks = entry.getValue().split(";\\s*");

        String paper1 = marks.length > 0 ? marks[0] : "";
        String paper2 = marks.length > 1 ? marks[1] : "";
        String paper3 = marks.length > 2 ? marks[2] : "";
        String paper4 =  marks.length > 3 ? marks[3] : "";
        String theoryExamTotal =marks.length > 4 ? marks[4] : "";
        String practicalExamTotal = marks.length > 5 ? marks[5] : "";
        String grandTotal = marks.length > 6 ? marks[6] : "";

        System.out.println("Subject: " + subjectName);
        System.out.println("  Paper 1: " + paper1);
        System.out.println("  Paper 2: " + paper2);
        System.out.println("  Paper 3: " + paper3);
        System.out.println("  Paper 4: " + paper4);
        System.out.println("  Theory Exam: " + theoryExamTotal);
        System.out.println("  Practical Exam: " + practicalExamTotal);
        System.out.println("  Exam Total: " + grandTotal);

//        knrEnrollment.monitorNetworkDuringTest(testCaseName);
        System.out.println("=========================");
        System.out.println("This is a Normal Test Case6");
        System.out.println("Starting testCase6 execution for the reg: " + regNo + "and  for the Subject: " + subjectToFind);

    	ExtentTest testCaseScenario = testCaseName.createNode("Starting testCase Validation for the register number: " + regNo + " and for the Subject: " + subjectToFind);
 	   
    		
        System.out.println("=========================");

        knrEnrollment.downloadPdfReportValidation(
        		testCaseScenario, regNo,semester,regulation,
            paper1, paper2, paper3,paper4, theoryExamTotal,practicalExamTotal, grandTotal, subjectToFind
        );
    }
    

    System.out.println("=====================");
    System.out.println("Reached end of testCase6 execution for: " + regNo + " and subject: " + subjectToFind);

    if (regNo == null || regNo.trim().isEmpty()) {
        System.out.println("Execution stopped: Found a null or empty cell in Excel.");
        System.exit(0);
    }
 
}


//for bnys pattern
@Test(priority = 5, enabled = false, dataProvider = "KNR_RC",description = "KNR_RC")
public void knr_RC(String regNo, KnrRCReadExcelFiles.StudentInfo studentInfo, String subjectToFind) 
                      throws InterruptedException, IOException, AWTException {
    // Set the test case name only once
 
    
   

	  if (!isTestCaseEnrollSet1) {
	        testCaseName = extentReport.createTest("Report Card Enrollment Page Actions");
	        isTestCaseEnrollSet1 = true;
	        knrEnrollment.monitorNetworkDuringTest(testCaseName);
	        Browser_Launch();
	        knrLogin.Login();
	        knrEnrollment.ReportCardNavigation();

	    }
	 
	
	   String examDate = (String) studentInfo.examSeries;
	    String awardName = (String) studentInfo.awardName;
	    String semester = (String) studentInfo.semYear;
	    String regulation = (String) studentInfo.regulation;
	    String examType = (String) studentInfo.examType;

	    
	    
	    Map<String, String> subjects = studentInfo.subjectsAndMarks;
	    System.out.println("subjects"+subjects);
	    System.out.println("regno" + regNo);
	    System.out.println("Exam Date: " + examDate);
	    System.out.println("Award Name: " + awardName);
	    System.out.println("Semester: " + semester);
	    System.out.println("Regulation: " + regulation);
	    System.out.println("Exam Type: " + examType);
		
	

	    if (!processedRegNos.contains(regNo)) {
	    	String currentWindow = driver.getWindowHandle();
			Set<String> windowHandles = driver.getWindowHandles();

			for (String handle : windowHandles) {
			    if (!handle.equals(currentWindow)) {
			    	System.out.println("Before closing: " + driver.getWindowHandles());
			    	driver.close();
			    	Set<String> newHandles = driver.getWindowHandles();
			    	System.out.println("After closing: " + newHandles);
			        driver.switchTo().window(handle); // switch to another open window
			        break;
			    }
			}
	        // Call Enrollment methods only if regNo is not processed
	    	knrEnrollment.ReportCardEnrollNavigation(testCaseName);
	    	knrEnrollment.EnrollmentRegNo(regNo, testCaseName);
	    	knrEnrollment.EnrollmentExamDate(examDate, testCaseName);
	    	knrEnrollment.EnrollmentAwardName(awardName, testCaseName);
	        knrEnrollment.EnrollmentSemester(semester, testCaseName);
	        knrEnrollment.EnrollmentRegulation(regulation, testCaseName);
	        knrEnrollment.EnrollmentExamType(examType, testCaseName);
	        knrEnrollment.submitButton(testCaseName);
	        knrEnrollment.DownloadReport(regNo,testCaseName);
	        System.out.println("regno" + regNo);
	        // Mark regNo as processed
	        processedRegNos.add(regNo);
	    }

	   
	    for (Map.Entry<String, String> entry : subjects.entrySet()) {
	        String subjectName = entry.getKey();

	        if (!subjectName.equalsIgnoreCase(subjectToFind)) {
	            continue; // Skip if not the subject we want
	        }

	        String[] marks = entry.getValue().split(";\\s*");

	        String paper1 = marks.length > 0 ? marks[0] : "";
	        String paper2 = marks.length > 1 ? marks[1] : "";
	        String paper3 = marks.length > 2 ? marks[2] : "";
	        String theoryExam = marks.length > 3 ? marks[3] : "";
	        String practicalExam = marks.length > 4 ? marks[4] : "";
	        String examTotal = marks.length > 5 ? marks[5] : "";
	        String theoryInt = marks.length > 6 ? marks[6] : "";
	        String theoryTh = marks.length > 7 ? marks[7] : "";
	        String praticalInt = marks.length > 8 ? marks[8] : "";
	        String praticalPractical = marks.length > 9 ? marks[9] : "";
	        String praticalViva = marks.length > 10 ? marks[10] : "";
	        
	        System.out.println("Subject: " + subjectName);
	        System.out.println("  Paper 1: " + paper1);
	        System.out.println("  Paper 2: " + paper2);
	        System.out.println("  Paper 3: " + paper3);
	        System.out.println("  Theory Exam: " + theoryExam);
	        System.out.println("  Practical Exam: " + practicalExam);
	        System.out.println("  Exam Total: " + examTotal);
	    	System.out.println("theroryInt: " + theoryInt);
			System.out.println("theroryTh: " + theoryTh);
			System.out.println("praticalInt: " + praticalInt);
			System.out.println("praticalPractical: " + praticalPractical);
			System.out.println("praticalViva: " + praticalViva);
		    System.out.println(processedRegNos+"processedRegNos");
	//        knrEnrollment.monitorNetworkDuringTest(testCaseName);
	        System.out.println("=========================");
	        System.out.println("This is a Normal Test Case6");
	        System.out.println("Starting testCase6 execution for the reg: " + regNo + " for the Subject: " + subjectToFind);
	        System.out.println("=========================");
   
	        
	        KnrEnrollmentPageForAdditionalParameter.downloadPdfReportValidation(testCaseName,regNo,
	  paper1,paper2,paper3,practicalExam,theoryExam,examTotal,subjectToFind,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
	 


//Need to Create the separte methos in one class for the below ones exam,awardName,etcc

    // Handle other details like exam date, award name, semester, etc.
 //   Enrollment.processOtherDetails(examdate, awardName, semester, regulation, examType);

    // Validate paper results and scores
 //  Enrollment.validatePaperResults(Regno, paper1, paper2, paper3, praticalExam, theroryExam, examTotal);

	System.out.println("=====================");
    System.out.println("Reached end of testCase5 execution for: " + regNo +" and subject: "+ subjectToFind );
    // **Stop execution if any cell is null or empty**
    if (regNo == null || regNo.toString().trim().isEmpty()) {
        System.out.println("Execution stopped: Found a null or empty cell in Excel.");
        System.exit(0); // **Stops the program immediately**
    }
}

}


	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException {
		System.out.println("This will execute foruth before every Method and after the before class");
//		ReadExcelData.ExcelReader(C:\\Users\\User\\Downloads\\DumpScore.xlsx,"mds");
	//	ReadExcelData.getColumnData()
		
	
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This will execute after every Method");
		  Properties prop = new Properties();
	        try {
	            // Load the properties file
	      	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
	            prop.load(fileInputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // Flush Extent Report
	        extentReport.flush();
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("This will execute third before the Class and after the befortest");
		  // Set up ExtentReports
     
	}

	@AfterClass
	public void afterClass() {
		System.out.println("This will execute after the Class");

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("This will execute second before the Test and after the before test suite");
	  // Check if the driver is not already initialized
			
		
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("This will execute after the Test");
		 Properties prop = new Properties();

	     try {
	          // Load the properties file
	    	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
	        
	          prop.load(fileInputStream);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
		 
	        String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
			
		
	
		 try {
	            Desktop.getDesktop().browse(new URI("file:///" + reportPath));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }	  // Ensure the browser is closed after the test
		
	}
	
	@BeforeSuite
	public void beforeSuite() throws InterruptedException, FileNotFoundException {
		System.out.println("This will execute first before the Test Suite");
//		report = new 


	
	
	  Properties prop = new Properties();
      try {
          // Load the properties file
    	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        
          prop.load(fileInputStream);
      } catch (IOException e) {
          e.printStackTrace();
      }

      // Read properties
      String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
      String theme = prop.getProperty("report.theme");
      String documentTitle = prop.getProperty("report.documentTitle");
      String reportName = prop.getProperty("report.reportName");

      // Set up Extent Reports
      ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
      report.config().setDocumentTitle(documentTitle);
      report.config().setReportName(reportName);

      // Set Theme based on config
      if ("DARK".equalsIgnoreCase(theme)) {
          report.config().setTheme(Theme.DARK);
      } else {
          report.config().setTheme(Theme.STANDARD);
      }

  	  extentReport =new ExtentReports(); 
      extentReport.attachReporter(report);
     

      System.out.println("Extent Report generated successfully at: " + reportPath);
  
	
	  
   
	}


	@AfterSuite
	public void afterSuite(ITestContext context) throws IOException, URISyntaxException {
     
		
		
	    Properties prop = new Properties();


 
        String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
		
		System.out.println("This will execute after the Test Suite");

        // Log file path (replace with actual path)
    
        boolean logContainsFailure = false;

        // Check if the log contains "failed"
        try {
            List<String> lines = Files.readAllLines(Paths.get(reportPath));
            for (String line : lines) {
                if (line.toLowerCase().equals("FAIL")) {  // 🔍 Check for failure in logs
                    logContainsFailure = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading log file: " + e.getMessage());
        }

        // Fetch current test execution counts
        int totalTests = context.getAllTestMethods().length;
        int passedTests = context.getPassedTests().size();
        int failedTests = context.getFailedTests().size();
        int skippedTests = context.getSkippedTests().size();

        // If failure is detected in logs, increase failure count
        if (logContainsFailure) {
            failedTests++; // Increase failures count
            passedTests--; // Decrease passes count
            System.out.println("❌ FAILURE DETECTED IN LOGS! Marking test as failed.");
        }

        // Display updated test summary
        System.out.println("\n========== TEST EXECUTION SUMMARY ==========");
        System.out.println("Total tests run: " + totalTests);
        System.out.println("Passes: " + passedTests);
        System.out.println("Failures: " + failedTests);
        System.out.println("Skips: " + skippedTests);
        System.out.println("=============================================\n");

	}
}
