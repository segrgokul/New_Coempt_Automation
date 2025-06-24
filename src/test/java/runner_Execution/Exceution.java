//package runner_Execution;
//
//import java.awt.AWTException;
//import java.awt.Desktop;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.ITestContext;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import base.BasicFunctions;
//import browsers.KnrBrowserManager;
//import dataProcessing.KnrRCReadExcelFiles;
//import pageModules.LoginPage;
//import pageModules.ReportEnrollmentPage;
//
//
//public class Exceution extends KnrBrowserManager {
//
//public Object[][] data1;
//public Object[][] data2;
//
//public Object[][] data3;
//public Object[][] data4;
//public Object[][] ScTEVTLoginExcel;
//public Object[][] ScTEVTStudentDetailsExcel;
//Set<String> processedRegNos = new HashSet<>();
//
////Declare a static flag outside for the stop print the same word in report again and again
//
//private static boolean isTestCaseCourseSet = false;
//private static boolean isTestCaseEnrollSet1 = false;
//	// private ExtentReports extent;
//	
//
//	 ExtentReports extentReport ;
//	 ExtentSparkReporter report ;
//	 ExtentTest testCaseName;
//
//	 
//	 
//	LoginPage login = new LoginPage();
//
//
//	ReportEnrollmentPage Enrollment = new  ReportEnrollmentPage();
//
//
//	
//
//	    @DataProvider(name = "KNR_RC")
//	    public Object[][] getDataForKNR_RC() throws IOException {
//	        Map<String, KnrRCReadExcelFiles.StudentInfo> groupedData =
//	        		KnrRCReadExcelFiles.readExcel("excel.file1", "excel.file1");
//
//	        List<Object[]> data = new ArrayList<>();
//	        for (Map.Entry<String, KnrRCReadExcelFiles.StudentInfo> entry : groupedData.entrySet()) {
//	            String regNo = entry.getKey();
//	            KnrRCReadExcelFiles.StudentInfo info = entry.getValue();
//
//	            // You can extract or loop through subject keys to generate subject-specific data.
//	            for (String subject : info.subjectsAndMarks.keySet()) {
//	                data.add(new Object[]{regNo, info, subject});
//	            }
//	        }
//
//	        return data.toArray(new Object[0][0]);
//	    }
//	
//	
//	
//	
//	
//	
//	private static int totalPassed = 0;
//	private static int totalFailed = 0;
//
//
//
//
//	
////General login and logout
//@Test(priority = 1, enabled = false)
//public void testCase1() throws InterruptedException, IOException {
//	testCaseName = extentReport.createTest("Login Page Actions");
//	
//	System.out.println("This is a Normal Test Case1");
//
//   
//
//  
//    // Perform LoginPage Actions
//	    login.DirectSignIn();
//	    login.DirectPassEntry();
//	    login.DirectUserEntry();
//
// 	    // Validate Login Failures and Success
//	    login.LoginInFail();
////	   	login.Login();
//
////	    SetAssignCourse.check();
//	    // Perform Logout and Re-login
//	
////	    
////	    Therory.AttendanceMarkNavigation();
////	    Therory.TheroryAttendanceNavigation();
////	    Therory.TheroryAttendanceBrowse();
////	    TRData.ResultDeliverablesNavigation();
////	    TRData.ResultDeliverablesTRDataClgWiseNavigation();
//
//	}	
//	
//
//
////General login and logout
//
//
//
////for KNR
//
//
//
//
//
//@Test(priority = 5, enabled = true, dataProvider = "KNR_RC")
//public void testCase6(String regNo, KnrRCReadExcelFiles.StudentInfo studentInfo, String subjectToFind)
//        throws InterruptedException, IOException, AWTException {
//
//    if (!isTestCaseEnrollSet1) {
//        testCaseName = extentReport.createTest("Report Card Enrollment Page Actions");
//        isTestCaseEnrollSet1 = true;
//        monitorNetworkDuringTest(testCaseName, regNo, subjectToFind);
//        Browser_Launch();
//        login.Login();
//        Enrollment.ReportCardNavigation(testCaseName);
//
//    }
//    
//    String examDate = (String) studentInfo.examSeries;
//    String awardName = (String) studentInfo.awardName;
//    String semester = (String) studentInfo.semYear;
//    String regulation = (String) studentInfo.regulation;
//    String examType = (String) studentInfo.examType;
//
//    Map<String, String> subjects = studentInfo.subjectsAndMarks;
//    System.out.println("subjects"+subjects);
//    
//  
//    
//    System.out.println("regno" + regNo);
//    System.out.println("Exam Date: " + examDate);
//    System.out.println("Award Name: " + awardName);
//    System.out.println("Semester: " + semester);
//    System.out.println("Regulation: " + regulation);
//    System.out.println("Exam Type: " + examType);
//
//
//    System.out.println(processedRegNos+"processedRegNos");
//
//    if (!processedRegNos.contains(regNo)) {
//    	
//
//        // Call Enrollment methods only if regNo is not processed
//        Enrollment.ReportCardEnrollNavigation(testCaseName);
//        Enrollment.EnrollmentRegNo(regNo, testCaseName);
//        Enrollment.EnrollmentExamDate(examDate, testCaseName);
//        Enrollment.EnrollmentAwardName(awardName, testCaseName);
//        Enrollment.EnrollmentSemester(semester, testCaseName);
//        Enrollment.EnrollmentRegulation(regulation, testCaseName);
//        Enrollment.EnrollmentExamType(examType, testCaseName);
//        Enrollment.submitButton(testCaseName);
//        Enrollment.DownloadReport(testCaseName);
//        System.out.println("regno" + regNo);
//        // Mark regNo as processed
//        processedRegNos.add(regNo);
//    }
//
//    
//    for (Map.Entry<String, String> entry : subjects.entrySet()) {
//        String subjectName = entry.getKey();
//
//        if (!subjectName.equalsIgnoreCase(subjectToFind)) {
//            continue; // Skip if not the subject we want
//        }
//
//        String[] marks = entry.getValue().split(";\\s*");
//
//        String paper1 = marks.length > 0 ? marks[0] : "";
//        String paper2 = marks.length > 1 ? marks[1] : "";
//        String paper3 = marks.length > 2 ? marks[2] : "";
//        String theoryExam = marks.length > 3 ? marks[3] : "";
//        String practicalExam = marks.length > 4 ? marks[4] : "";
//        String examTotal = marks.length > 5 ? marks[5] : "";
//
//        System.out.println("Subject: " + subjectName);
//        System.out.println("  Paper 1: " + paper1);
//        System.out.println("  Paper 2: " + paper2);
//        System.out.println("  Paper 3: " + paper3);
//        System.out.println("  Theory Exam: " + theoryExam);
//        System.out.println("  Practical Exam: " + practicalExam);
//        System.out.println("  Exam Total: " + examTotal);
//
//        monitorNetworkDuringTest(testCaseName, regNo, subjectToFind);
//        System.out.println("=========================");
//        System.out.println("This is a Normal Test Case6");
//        System.out.println("Starting testCase6 execution for the reg: " + regNo + " for the Subject: " + subjectToFind);
//        System.out.println("=========================");
//
//        Enrollment.downloadPdfReportValidation(
//            testCaseName, regNo,
//            paper1, paper2, paper3, practicalExam, theoryExam, examTotal, subjectToFind
//        );
//    }
//    
//
//	// Switch back to the parent window
//	String currentWindow = driver.getWindowHandle();
//	Set<String> windowHandles = driver.getWindowHandles();
//
//	for (String handle : windowHandles) {
//	    if (!handle.equals(currentWindow)) {
//	    	System.out.println("Before closing: " + driver.getWindowHandles());
//	    	driver.close();
//	    	Set<String> newHandles = driver.getWindowHandles();
//	    	System.out.println("After closing: " + newHandles);
//	        driver.switchTo().window(handle); // switch to another open window
//	        break;
//	    }
//	}
//    System.out.println("=====================");
//    System.out.println("Reached end of testCase6 execution for: " + regNo + " and subject: " + subjectToFind);
//
//    if (regNo == null || regNo.trim().isEmpty()) {
//        System.out.println("Execution stopped: Found a null or empty cell in Excel.");
//        System.exit(0);
//    }
// 
//}
//
//
//
//
//
//	@BeforeMethod
//	public void beforeMethod() throws IOException, InterruptedException {
//		System.out.println("This will execute foruth before every Method and after the before class");
////		ReadExcelData.ExcelReader(C:\\Users\\User\\Downloads\\DumpScore.xlsx,"mds");
//	//	ReadExcelData.getColumnData()
//		
//	
//	}
//
//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("This will execute after every Method");
//		  Properties prop = new Properties();
//	        try {
//	            // Load the properties file
//	      	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
//	            prop.load(fileInputStream);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        // Flush Extent Report
//	        extentReport.flush();
//	}
//
//	@BeforeClass
//	public void beforeClass() {
//		System.out.println("This will execute third before the Class and after the befortest");
//		  // Set up ExtentReports
//     
//	}
//
//	@AfterClass
//	public void afterClass() {
//		System.out.println("This will execute after the Class");
//
//	}
//
//	@BeforeTest
//	public void beforeTest() {
//		System.out.println("This will execute second before the Test and after the before test suite");
//	  // Check if the driver is not already initialized
//			
//		
//	}
//	
//	@AfterTest
//	public void afterTest() {
//		System.out.println("This will execute after the Test");
//		 Properties prop = new Properties();
//
//	     try {
//	          // Load the properties file
//	    	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
//	        
//	          prop.load(fileInputStream);
//	      } catch (IOException e) {
//	          e.printStackTrace();
//	      }
//		 
//	        String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
//			
//		
//	
//		 try {
//	            Desktop.getDesktop().browse(new URI("file:///" + reportPath));
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }	  // Ensure the browser is closed after the test
//		
//	}
//	
//	@BeforeSuite
//	public void beforeSuite() throws InterruptedException, FileNotFoundException {
//		System.out.println("This will execute first before the Test Suite");
////		report = new 
//
//
//	
//	
//	  Properties prop = new Properties();
//      try {
//          // Load the properties file
//    	  FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
//        
//          prop.load(fileInputStream);
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//
//      // Read properties
//      String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
//      String theme = prop.getProperty("report.theme");
//      String documentTitle = prop.getProperty("report.documentTitle");
//      String reportName = prop.getProperty("report.reportName");
//
//      // Set up Extent Reports
//      ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
//      report.config().setDocumentTitle(documentTitle);
//      report.config().setReportName(reportName);
//
//      // Set Theme based on config
//      if ("DARK".equalsIgnoreCase(theme)) {
//          report.config().setTheme(Theme.DARK);
//      } else {
//          report.config().setTheme(Theme.STANDARD);
//      }
//
//  	  extentReport =new ExtentReports(); 
//      extentReport.attachReporter(report);
//     
//
//      System.out.println("Extent Report generated successfully at: " + reportPath);
//  
//	
//	  
//   
//	}
//
//
//	@AfterSuite
//	public void afterSuite(ITestContext context) throws IOException, URISyntaxException {
//     
//		
//		
//	    Properties prop = new Properties();
//
//
// 
//        String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
//		
//		System.out.println("This will execute after the Test Suite");
//
//        // Log file path (replace with actual path)
//    
//        boolean logContainsFailure = false;
//
//        // Check if the log contains "failed"
//        try {
//            List<String> lines = Files.readAllLines(Paths.get(reportPath));
//            for (String line : lines) {
//                if (line.toLowerCase().equals("FAIL")) {  // 🔍 Check for failure in logs
//                    logContainsFailure = true;
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("❌ Error reading log file: " + e.getMessage());
//        }
//
//        // Fetch current test execution counts
//        int totalTests = context.getAllTestMethods().length;
//        int passedTests = context.getPassedTests().size();
//        int failedTests = context.getFailedTests().size();
//        int skippedTests = context.getSkippedTests().size();
//
//        // If failure is detected in logs, increase failure count
//        if (logContainsFailure) {
//            failedTests++; // Increase failures count
//            passedTests--; // Decrease passes count
//            System.out.println("❌ FAILURE DETECTED IN LOGS! Marking test as failed.");
//        }
//
//        // Display updated test summary
//        System.out.println("\n========== TEST EXECUTION SUMMARY ==========");
//        System.out.println("Total tests run: " + totalTests);
//        System.out.println("Passes: " + passedTests);
//        System.out.println("Failures: " + failedTests);
//        System.out.println("Skips: " + skippedTests);
//        System.out.println("=============================================\n");
//
//        // Update Extent Report
////        if (failedTests == 0) {
////            extentReport.createTest("SCET&VT_SEM_5 Actions Final Status")
////                        .pass("All tests executed successfully.");
////        } else {
////            extentReport.createTest("SCET&VT_SEM_5 Actions Final Status")
////                        .fail("Some tests failed during execution.");
////            throw new AssertionError("⚠️ Test execution failed due to failure logs found!");
////        }
//    
//        // Open the report in the default browser
//       
//    }
//
//	 @Test(priority = 0, enabled = false)
//	 public void monitorNetworkDuringTest(ExtentTest testCaseName,Object regno,String subjectToFind) throws InterruptedException, IOException, AWTException {
//		  // Single method to setup report, check internet, and log the result
//		    ExtentTest testCaseScenario = testCaseName
//		            .createNode("Internet Connectivity Check for the following register number"+ regno +" and subjectame "+ subjectToFind );
//		 try {
//	
//  // Load config file and fetch the report path
//		        Properties properties = new Properties();
//		        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
//		        	
//		        	
//		      	 
//		            properties.load(fileInputStream);
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//
//		    
//		        // Start the test and check internet connectivity
//		    
//		    		        try {
//		            InetAddress address = InetAddress.getByName("google.com");
//		            if (address.isReachable(3000)) {
//		          //  	testCaseScenario.log(Status.INFO, "Internet is connected.");
//		                System.out.println("Internet is connected.");
//		             	testCaseScenario.info("Network status for the following register number"+ regno +" and subject name "+ subjectToFind  +" is connected"); 
//		                   
//		             	try {
//		             		WebElement networkTryAgainBtn = driver.findElement(By.xpath("//button[@id='neterrorTryAgainButton']"));
//		             	if(networkTryAgainBtn.isDisplayed()) {
//		            		Thread.sleep(200);
//		            		
//		            	networkTryAgainBtn.click();
//		            	
////		            	driver.navigate().back();
////		            	Thread.sleep(5000);
////		            	driver.navigate().back();
//		            	
//		            
//		             		}
//		             
//		             	else {
//      		WebElement dashBoard = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')])"));
//		             		
//		             		if(dashBoard.isDisplayed()) {
//		             			  Enrollment.ReportCardNavigation(testCaseName);
//		             		}
//		             	}
//		             	
//		             	}
//		             	
//		             	catch(Exception e2) {
//		             		
//		       
//		             		
//		             	}
//		            } else {
//		  
//		            //	testCaseScenario.fail("Network status for the following register number"+ regno +" and subjectame "+ subjectToFind  +" is not connected");
//		                System.out.println( "Network status for the following register number"+ regno +" and subject name "+ subjectToFind  +" is not connected Internet is NOT connected.");
//		                driver.navigate().refresh();
//		            	testCaseScenario.skip("Network status for the following register number"+ regno +" and subjectame "+ subjectToFind  +" is not connected"
//		            			,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build()); 
//				           
//		            }
//		        } catch (Exception e) {
//		        	
//		        	
//		        	
//		        	
//		        	
//		       
//		         
//			          
//			            try {
//			              	testCaseScenario.warning("Network status for the following register number"+ regno +" and subjectame "+ subjectToFind  +" is not connected"
//			              			
//			              			
//			              			,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build()); 
//			 		       // 	testCaseScenario.log(Status.WARNING, "Internet is NOT connected.");
//			 		            System.out.println("Network status for the following register number"+ regno +" and subjectame "+ subjectToFind  +" is not connected please check Internet is NOT connected.");
//			 		            
//			 		            
//			          driver.navigate().refresh();
//		            	
//		            	
//		            
//		            }
//		            	
//		            	
//		            
//			            
//			            catch(Exception e2) {
//
//			            	Thread.sleep(5000);
//			          
//			          	  WebElement networkTryAgainBtn = driver.findElement(By.xpath("//button[@id='neterrorTryAgainButton']"));
//			          	 
//			            	while(networkTryAgainBtn.isDisplayed()) {
//			            		Thread.sleep(200);
//			            		
//			            	networkTryAgainBtn.click();
//			            	
//			            	
//			            	
//			            
//			            }
//			            	WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
//			            	
//			            	if(userName.isDisplayed()) {
//			            	
//			            	
//			            		  Enrollment.ReportCardNavigation(testCaseName);
//			            		  
//			            	}
//			            }
//		            
//		        }
//		        
//		 }//try
//		 
//		 catch(Exception e3){
//			
//			 testCaseScenario.log(Status.FAIL,e3.getMessage());
//		 }
//
//		    }
//		
//
//
//		public boolean isNetworkAvailable() {
//		    try {
//		        return InetAddress.getByName("google.com").isReachable(2000);
//		    } catch (IOException e) {
//		        return false;
//		    }
//		}
//	
//}
