package sctevt_runner_Execution;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.io.Files;

import base.BasicFunctions;
import browsers.BrowserManager;
import dataProcessing.KnrRCReadExcelFiles;
import dataProcessing.SctevtResultReadExcelFile;
import scevtPageModules.Scte_VtResultPage;
import scevtPageModules.scevtLoginPage;

public class sctevtExceution  extends BrowserManager {
	public Object[][] resultProcessData;
	public Object[][] scTEVTLoginExcel;
	public Object[][] scTEVTStudentDetailsExcel;
	Set<String> processedRegNos = new HashSet<>();
	 ExtentReports extentReport ;
	 ExtentSparkReporter report ;
	 ExtentTest testCaseName;
		private static boolean isTestCaseEnrollSet1 = false; //assigning as false to run only only time 
	
		scevtLoginPage scevtLoginPage = new scevtLoginPage();
		Scte_VtResultPage Scte_VtResultPage =new Scte_VtResultPage();

		@DataProvider(name ="ScTE&VT_Result_Process")

	    public Object[][] getDataScTEandVT() throws IOException {
	        Map<String, SctevtResultReadExcelFile.StudentInfo> groupedData =
	        		SctevtResultReadExcelFile.readExcel("excel.file4", "excel.file4");

	        List<Object[]> data = new ArrayList<>();
	        for (Map.Entry<String, SctevtResultReadExcelFile.StudentInfo> entry : groupedData.entrySet()) {
	            String regNo = entry.getKey();
	            System.out.println(regNo);
	            
	            SctevtResultReadExcelFile.StudentInfo info = entry.getValue();
	            
	            System.out.println(info);
	            
	            
	            // You can extract or loop through subject keys to generate subject-specific data.
	            for (String subject : info.subjectsAndMarks.keySet()) {
	            System.out.println(subject);
	            	data.add(new Object[]{regNo,info,subject});
	            }
	        }

	        return data.toArray(new Object[0][0]);
	    }
		
	@DataProvider(name ="ScTE&VT2")
	public Object[][]ReadExcelScTEandVT2() throws IOException,InvalidFormatException {
		
//		Object[][] data = dataProcessing.ReadExcelFiles.readExcel("file1","file1");



		scTEVTLoginExcel= dataProcessing.ReadExcelFiles.readExcel("excel.file5", "excel.file5");
		scTEVTStudentDetailsExcel = dataProcessing.ReadExcelFiles.readExcel("excel.file6", "excel.file6");
	//System.out.println("Data size: " + data.length);

		 List<Object[]> combined = new ArrayList<>();

		    for (Object[] loginRow : scTEVTLoginExcel) {
		        for (Object[] studentRow : scTEVTStudentDetailsExcel) {
		            Object[] merged = new Object[loginRow.length + studentRow.length];
		            System.arraycopy(loginRow, 0, merged, 0, loginRow.length);
		            System.arraycopy(studentRow, 0, merged, loginRow.length, studentRow.length);
		            combined.add(merged);
		        }
		    }

		    return combined.toArray(new Object[0][0]);

	}			

		
//Fpr SCTE&VT result process project
@Test(priority = 1, enabled = true, dataProvider = "ScTE&VT_Result_Process",description = "ScTE&VT_Result_Process")
public void ScTEandVT(String regNo, SctevtResultReadExcelFile.StudentInfo studentInfo,String subjectToFind) throws InterruptedException, IOException, AWTException {
	
    if (testCaseName == null) {
     
  	  Browser_Launch();
  
    }
    System.out.println("=========================");
   
    	
    	
    try {
        // Perform LoginPage Actions

   	    String examSemester = (String) studentInfo.examSemester;
 	    
   	    Map<String, String> subjects = studentInfo.subjectsAndMarks;
   	    System.out.println("subjects "+subjects);
   	    System.out.println("regno " + regNo);
   	    System.out.println("Semester: " + examSemester);
   	    System.out.println("Exam Mark: " +studentInfo.semMark);
   	    
   	
   	  for (Map.Entry<String, String> entry : subjects.entrySet()) {
	        String subjectName = entry.getKey();

	    
	 	    String[] marks = entry.getValue().split("=");
	 	    
	 	    String semesterMark = marks.length > 0 ? marks[0] : "";
	     
	 	    if (!subjectName.equalsIgnoreCase(subjectToFind)) {
    
	            continue; // Skip if not the subject we want
	        }
    

	        System.out.println("Subject: " + subjectName);
	        System.out.println("  semesterMark : " + semesterMark);
	     
	    	if (!processedRegNos.contains(regNo)) {
	   			
	    		String currentWindow = driver.getWindowHandle();
	    		Set<String> windowHandles = driver.getWindowHandles();
	    		 testCaseName = extentReport.createTest("Report Card Enrollment Page Actions for the following register number: "+regNo+" and for the semester " + examSemester);
	    		
	    	    
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
	   			
	   			
	   			Scte_VtResultPage.ScTEVT_ResultProcess(regNo, examSemester, subjectName,semesterMark, testCaseName);
	   		   processedRegNos.add(regNo);
	   			
	   			
	   		}	     

	//        knrEnrollment.monitorNetworkDuringTest(testCaseName);
	        System.out.println("=========================");
	        System.out.println("This is a Normal Test Case6");
	        System.out.println("Starting testCase6 execution for the reg: " + regNo + "and  for the Subject: " + subjectToFind);

	    	 		
	        System.out.println("=========================");
	    	ExtentTest testCaseScenario = testCaseName.createNode("SCET&VT Result process Action for the semester " + examSemester +" and the reg: " + regNo +" and subject " + subjectName);
		    
	        System.out.println("SCET&VT Result process Action for the semester " + examSemester +" and the reg: " + regNo +" and subject " +subjectName);
	          	   
	            	Scte_VtResultPage.ScTEVT_ResultProcess1(regNo, examSemester, subjectName,semesterMark, testCaseScenario);
	            	
	        
	    }
	    
    } catch (Exception e) {
e.printStackTrace();
        testCaseName.fail("Test failed for reg: " + regNo + " due to " + e.getMessage());
    }

    System.out.println("=========================");
    System.out.println("Ended testCase execution for the reg: " + regNo);
}





//Fpr SCTE&VT login project
@Test(priority = 2, enabled = false, dataProvider = "ScTE&VT2",description = "ScTEVT")
public void ScTEandVT2(Object admin , Object passWord, Object regno, Object semester) throws InterruptedException, IOException, AWTException {
if (testCaseName == null) {
    testCaseName = extentReport.createTest("SCET&VT Actions");

	  Browser_Launch();
	  scevtLoginPage.login(admin, passWord, testCaseName);
	  scevtLoginPage.resultPageNavigation(admin, passWord, testCaseName);
}

System.out.println("Starting testCase execution for the semester " + admin +" and the reg: " + admin);
System.out.println("=========================");

System.out.println("Admin: " + admin);
System.out.println("Password: " + passWord);
System.out.println("regno: " + regno);
System.out.println("subjectName: " + semester);

scevtLoginPage.regnoEnter(regno, semester, testCaseName);
scevtLoginPage.regnoEnter1(regno, semester, testCaseName);
try {
    // Perform LoginPage Actions

} catch (Exception e) {
 
    testCaseName.fail("Test failed for reg: " + admin + " due to " + e.getMessage());
}

System.out.println("=========================");
System.out.println("Ended testCase execution for the reg: " + admin);
}@BeforeMethod
public void beforeMethod() throws IOException, InterruptedException {
	System.out.println("This will execute foruth before every Method and after the before class");
//	ReadExcelData.ExcelReader(C:\\Users\\User\\Downloads\\DumpScore.xlsx,"mds");
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
//	report = new 




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
