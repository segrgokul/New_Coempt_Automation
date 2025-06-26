package nizam_runner_Execution;



import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import browsers.BrowserManager;
import gitaProject.GitaReadPdfFiles;
import nizamProject.Nizam;



public class NizamExecution extends BrowserManager {

	// private ExtentReports extent;
	

	 ExtentReports extentReport ;
	 ExtentSparkReporter report ;
	 ExtentTest testCaseName;

	 GitaReadPdfFiles ReadPdf = new GitaReadPdfFiles();
//General login and logout
@Test(priority = 1, enabled = true,description = "Nizam")
public void testCase1() throws InterruptedException, IOException {
	
	
	try {
	        	
	
				  
			      System.out.println("=====================");
		            
		            Nizam nizam = new Nizam();
					nizam.readPdfData(extentReport,testCaseName);
		            System.out.println("PDF Data Read Test Completed.");
				
	
	           
	        } catch (IOException e) {
	            System.err.println("Error during PDF Data Read Test: " + e.getMessage());
	            e.printStackTrace();
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
