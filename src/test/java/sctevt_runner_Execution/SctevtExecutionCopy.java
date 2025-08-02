
package sctevt_runner_Execution;


import java.awt.AWTException;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import browsers.BrowserManager;
import dataProcessing.SctevtResultReadExcelFile;
import scevtPageModules.ScevtLoginPage;
import scevtPageModules.Scevt_RC;
import scevtPageModules.Scte_VtResultPage;
import scevtPageModules.Scte_VtResultPageSID_06;
import scevtPageModules.Scte_VtResultPageSID_07;
import scevtPageModules.SctevtExamHistoryRegNoSearchPage1;


public class SctevtExecutionCopy  extends BrowserManager {
	public Object[][] resultProcessData;
	public Object[][] scTEVTLoginExcel;
	public Object[][] scTEVTStudentDetailsExcel;
	Set<String> processedRegNos = new HashSet<>();
	 ExtentReports extentReport ;
	 ExtentSparkReporter report ;
	 ExtentTest testCaseName;
		private static boolean isTestCaseEnrollSet = false; //assigning as false to run only only time 
	
		ScevtLoginPage scevtLoginPage = new ScevtLoginPage();
		SctevtExamHistoryRegNoSearchPage1 SctevtExamHistoryRegNoSearchPage = new SctevtExamHistoryRegNoSearchPage1(); 
		Scte_VtResultPageSID_06 Scte_VtResultPage_SID_06 =new Scte_VtResultPageSID_06();
		Scte_VtResultPageSID_07 Scte_VtResultPage_SID_07 =new Scte_VtResultPageSID_07();
		Scevt_RC Scevt_RC = new Scevt_RC();
		
		
		
		
		@DataProvider(name ="ScTE&VT_Result_Process")

	    public Object[][] getDataScTEandVTForResultProcess() throws IOException {
	        Map<String, SctevtResultReadExcelFile.StudentInfo> groupedData =
	        		SctevtResultReadExcelFile.readExcel("excel.file4", "excel.file4");

	        List<Object[]> data = new ArrayList<>();
	        for (Map.Entry<String, SctevtResultReadExcelFile.StudentInfo> entry : groupedData.entrySet()) {

	        
	            String[] parts   = entry.getKey().split("_", 2);
	            String regNo     = parts[0];           // F18109004072
	            String semester  = parts.length > 1 ? parts[1] : "";  // "1"  (use it if you need)

	            SctevtResultReadExcelFile.StudentInfo info = entry.getValue();

	          
	            System.out.println(regNo);
	            System.out.println(semester);
	   
	            
	            System.out.println(info);
	            
	            
	            
	            // You can extract or loop through subject keys to generate subject-specific data.
	            for (String subject : info.subjectsAndMarks.keySet()) {
	            System.out.println(subject);
	            	data.add(new Object[]{regNo,info,subject});
	            }
	        }

	        return data.toArray(new Object[0][0]);
	    }
		
		
	@DataProvider(name ="ScTE&VTLogin")
	public Object[][]getDataScTEandVTForLogin() throws IOException,InvalidFormatException {
		
//		Object[][] data = dataProcessing.ReadExcelFiles.readExcel("file1","file1");



	//System.out.println("Data size: " + data.length);
		     Map<String, SctevtResultReadExcelFile.StudentInfo> groupedData =
		        		SctevtResultReadExcelFile.readExcel("excel.file6", "excel.file6");

		        List<Object[]> data = new ArrayList<>();
		        for (Map.Entry<String, SctevtResultReadExcelFile.StudentInfo> entry : groupedData.entrySet()) {

		        	System.out.println(entry.getKey());
		        
		            String[] parts   = entry.getKey().split("_", 2);
		            String regNo     = parts[0];           // F18109004072
		            String semester  = parts.length > 1 ? parts[1] : "";  // "1"  (use it if you need)

		            SctevtResultReadExcelFile.StudentInfo info = entry.getValue();

		          
		            System.out.println(regNo);
		            System.out.println(semester);
		   
		            
		            System.out.println(info);
		            
		            
		            
		            // You can extract or loop through subject keys to generate subject-specific data.
		            for (String subject : info.subjectsAndMarks.keySet()) {
		            System.out.println(subject);
		            	data.add(new Object[]{regNo,info,subject});
		            }
		        }

		        return data.toArray(new Object[0][0]);
		    }
				

		
//Fpr SCTE&VT_SID_06 result process project
@Test(priority = 1, enabled = true, dataProvider = "ScTE&VT_Result_Process",description = "ScTE&VT_Result_Process")
public void ScTEandVT_SID_06(String regNo, SctevtResultReadExcelFile.StudentInfo studentInfo,String subjectToFind) throws InterruptedException, IOException, AWTException {
	

    if (!isTestCaseEnrollSet) {
  
  	  Browser_Launch();
      isTestCaseEnrollSet = true;
    }
    System.out.println("=========================");
   
   
    String examSemester = (String) studentInfo.examSemester;
    Map<String, String> subjects = studentInfo.subjectsAndMarks;

    System.out.println("subjects: " + subjects);
    System.out.println("regno: " + regNo);
    System.out.println("semester: " + examSemester);

    String regNoWithSemester = regNo + "_SEM" + examSemester;

    if (!processedRegNos.contains(regNoWithSemester)) {

    	  testCaseName = extentReport.createTest("Report Card Enrollment Page Actions for the following register number: " + regNo + " and for the semester " +examSemester);

     System.out.println("Starting testCase execution for the semester " + examSemester + " and the reg: " + regNo);
     System.out.println("=========================");

     System.out.println("regno: " + regNo);
     System.out.println("semester: " + examSemester);


     Scte_VtResultPage_SID_06.ScTEVT_ResultProcess(regNo, examSemester, testCaseName);
     System.out.println("regNo: "+regNo);
     System.out.println("examSemester: "+examSemester);
     System.out.println("subjects:" +subjects);

     // ✅ Updated: pass the whole map of subjects at once
  
    
     Scte_VtResultPage_SID_06.ScTEVT_ResultProcess1(regNo, examSemester, subjects, testCaseName);
     processedRegNos.add(regNoWithSemester);

    }

    }
   	
   	


//Fpr SCTE&VT_SID_07 result process project
@Test(priority = 2, enabled = false, dataProvider = "ScTE&VT_Result_Process",description = "ScTE&VT_Result_Process")
public void ScTEandVT_SID_07(String regNo, SctevtResultReadExcelFile.StudentInfo studentInfo,String subjectToFind) throws InterruptedException, IOException, AWTException {
	

  if (!isTestCaseEnrollSet) {

	  Browser_Launch();
    isTestCaseEnrollSet = true;
  }
  System.out.println("=========================");
 
 
  String examSemester = (String) studentInfo.examSemester;
  Map<String, String> subjects = studentInfo.subjectsAndMarks;

  System.out.println("subjects: " + subjects);
  System.out.println("regno: " + regNo);
  System.out.println("semester: " + examSemester);

  String regNoWithSemester = regNo + "_SEM" + examSemester;

  if (!processedRegNos.contains(regNoWithSemester)) {

  	  testCaseName = extentReport.createTest("Report Card Enrollment Page Actions for the following register number: " + regNo + " and for the semester " +examSemester);

   System.out.println("Starting testCase execution for the semester " + examSemester + " and the reg: " + regNo);
   System.out.println("=========================");

   System.out.println("regno: " + regNo);
   System.out.println("semester: " + examSemester);


   Scte_VtResultPage_SID_07.ScTEVT_ResultProcess(regNo, examSemester, testCaseName);
   System.out.println("regNo: "+regNo);
   System.out.println("examSemester: "+examSemester);
   System.out.println("subjects:" +subjects);

   // ✅ Updated: pass the whole map of subjects at once

  
   Scte_VtResultPage_SID_07.ScTEVT_ResultProcess1(regNo, examSemester, subjects, testCaseName);
   processedRegNos.add(regNoWithSemester);

  }

  }
 	

//For SCTE&VT login project
@Test(priority = 3, enabled = false, dataProvider = "ScTE&VTLogin", description = "ScTEVTLogin")
public void ScTEandVT2(String regNo, SctevtResultReadExcelFile.StudentInfo studentInfo, String subjectToFind) throws InterruptedException, IOException, AWTException {

	 if (!isTestCaseEnrollSet) {
	        isTestCaseEnrollSet = true;
 Browser_Launch();
 scevtLoginPage.login();
}

String examSemester = (String) studentInfo.examSemester;
Map<String, String> subjects = studentInfo.subjectsAndMarks;

System.out.println("subjects: " + subjects);

String regNoWithSemester = regNo + "_SEM" + examSemester;

if (!processedRegNos.contains(regNoWithSemester)) {

	  testCaseName = extentReport.createTest("Report Card Enrollment Page Actions for the following register number: " + regNo + " and for the semester " +examSemester);

 System.out.println("Starting testCase execution for the semester " + examSemester + " and the reg: " + regNo);
 System.out.println("=========================");

 System.out.println("regno: " + regNo);
 System.out.println("semester: " + examSemester);

 SctevtExamHistoryRegNoSearchPage.resultPageNavigation(regNo, examSemester, testCaseName);

 SctevtExamHistoryRegNoSearchPage.regnoEnter(regNo, examSemester, testCaseName);
 
 System.out.println("regNo: "+regNo);
 System.out.println("examSemester: "+examSemester);
 System.out.println("subjects:" +subjects);

 // ✅ Updated: pass the whole map of subjects at once
SctevtExamHistoryRegNoSearchPage.regnoValidation(regNo, examSemester, subjects, testCaseName);

 processedRegNos.add(regNoWithSemester);
}
}
//For SCTE&VT RC project
@Test(priority = 3, enabled = false)
public void ScTEandVT2() throws InterruptedException, IOException, AWTException {
 
	 if (!isTestCaseEnrollSet) {
	        isTestCaseEnrollSet = true;
	   	 testCaseName = extentReport.createTest("ScTEVt Report Card ");
 }

	 
	 Scevt_RC.readPdfData(testCaseName);
	 
 }





@BeforeMethod
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
 
	
	
  //  Properties prop = new Properties();



   // String reportPath = prop.getProperty("report.path").replace("\\", "/"); // Convert to valid URI format
	
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
