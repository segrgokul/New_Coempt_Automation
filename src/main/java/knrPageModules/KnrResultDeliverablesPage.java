package knrPageModules;


import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import pageObjMod.knrPom;

public class KnrResultDeliverablesPage extends BasicFunctions {
	public Object[][] data1;
	public Object[][] data2;
	  // Static ExtentTest, accessible from anywhere in the class
    public static ExtentTest testCaseName;
//	static ExtentReports report;


	    // Constructor to initialize ExtentReports
	  // Set the test case name only once
    KnrReportEnrollmentPage Enrollment = new  KnrReportEnrollmentPage();
    
	   
   
	
	    	

	public void ResultDeliverablesPageNavigation(ExtentTest testCaseName) throws IOException {
		
	//	testCaseName.log(Status.INFO, "Report Card Navigation");
		ExtentTest testCaseScenario = testCaseName.createNode("Result Deliverables Navigation");
		
		implicitWait( 30);
		explicitWait( knrPom.getInstanceCourseXP().loginTags, 30);

		if (knrPom.getInstanceCourseXP().loginTags.isDisplayed()) {

			implicitWait( 30);
			explicitWait( knrPom.getInstanceCourseXP().loginTags, 30);

		//	testCaseName.log(Status.INFO, "Report Card Navigation");
			scrollTillWebElement( knrPom.getInstanceResultDeliverablesCP().resultDeliverablesOption);

			implicitWait( 30);
			
		
			
			if(knrPom.getInstanceResultDeliverablesCP().resultDeliverablesOption.isDisplayed()) {
			
			explicitWait( knrPom.getInstanceResultDeliverablesCP().resultDeliverablesOption, 30);
			click( knrPom.getInstanceResultDeliverablesCP().resultDeliverablesOption);
			testCaseScenario.log(Status.PASS, "result Deliverables Option page is Navigated sucessfully");
			
		
		//	testCaseName.log(Status.FAIL, "This is a pass message");
			}	}
			else {
	       
				testCaseScenario.log(Status.FAIL, "result Deliverables Option page not navigating ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
			}

	   
		}
	

	
	
	public void TRDataCollegewiseNavigation(ExtentTest testCaseName) throws IOException {
	// // // 	// // // ExtentReports     report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\ReportCoursePage.html");
    //    report.loadConfig(new File("path_to_config_file.xml"));
	    
		
		ExtentTest testCaseScenario = testCaseName.createNode("TR Data College wise navigation");
		
		implicitWait( 30);
		explicitWait( knrPom.getInstanceResultDeliverablesCP().TRDataCollegewiseOption, 30);

		scrollTillWebElement(  knrPom.getInstanceResultDeliverablesCP().TRDataCollegewiseOption);

		implicitWait( 30);
		
		
		try {
		if( knrPom.getInstanceResultDeliverablesCP().TRDataCollegewiseOption.isDisplayed()) {
			implicitWait( 30);		
		
		explicitWait( knrPom.getInstanceResultDeliverablesCP().TRDataCollegewiseOption, 30);
		click( knrPom.getInstanceResultDeliverablesCP().TRDataCollegewiseOption);
		testCaseScenario.log(Status.PASS, "TR Data College wise navigation sucessfully");
	
		
		}
		}
		catch(Exception e) {
			testCaseScenario.log(Status.FAIL, "TR Data College wise navigation wise does not navigation sucessfully ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		}

	//	testCase.log(LogStatus.PASS, "ReportCard option is navigated perfectly ");
		

	}
	
	public void handleCollegeCode(Object clgCode,ExtentTest testCaseName) throws InterruptedException, IOException {
		 
		
		ExtentTest testCaseScenario = testCaseName.createNode("Report card course wise for the following clg code: " + clgCode);
	

		// Wait for the dropdown and textbox elements
		implicitWait( 50);
		
		knrPom.getInstanceCourseXP().clgDropDown.isDisplayed();
		
		explicitWait( knrPom.getInstanceCourseXP().clgDropDown, 30);
	
		// Click the college dropdown
		click( knrPom.getInstanceCourseXP().clgDropDown);
		
		// Wait for the textbox to become clickable
		implicitWait( 30);
		explicitWait( knrPom.getInstanceCourseXP().clgTextBox, 30);
		
		// Enter the college code in the textbox
		click( knrPom.getInstanceCourseXP().clgTextBox);
		Thread.sleep(1000);  // Sleep to ensure the textbox is fully active
		implicitWait( 30);
		sendKeys(knrPom.getInstanceCourseXP().clgTextBox, String.valueOf(clgCode));
		
		// Wait for the dropdown results to load
		implicitWait( 30);
		explicitWait( knrPom.getInstanceCourseXP().clgDropDownResults.get(0), 30);
	
		// Click the first result in the dropdown
		if (knrPom.getInstanceCourseXP().clgDropDownResults.get(0).isDisplayed()) {
			click( knrPom.getInstanceCourseXP().clgDropDownResults.get(0));
			testCaseScenario.log(Status.PASS, "College code has entered sucessfully");
			
			

		} else {
//			System.out.println("College code not found.");
			
			testCaseScenario.log(Status.FAIL, "College code has not entered ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			
	//		testCase.log(LogStatus.FAIL, "Click action performed", testCase.addScreenCapture(BasicFunctions.capture(driver)));
		}
//		 report.endTest(test); // End the test after all logs are added
//	        report.flush();
	}
	
			
	public void handleOtherParameters(Object examdate, Object awardName,Object regulation,Object semester, Object examType,ExtentTest testCaseName) throws IOException, InterruptedException, AWTException {
		
	
		// Log the received parameters for debugging purposes
		/*
		 * System.out.println("Handling parameters:"); System.out.println("Exam Date: "
		 * + examdate); System.out.println("Award Name: " + awardName);
		 * System.out.println("Regulation: " + regulation);
		 * System.out.println("ExamType:: " + examType);
		 * 
		 * System.out.println("Semester: " + semester);
		 */
	


   

		Enrollment.EnrollmentExamDate(examdate,testCaseName);
	implicitWait( 30);

	Enrollment.EnrollmentAwardName(awardName,testCaseName);
	implicitWait( 30);

//	reportEnrollmentPage.EnrollmentAwardName(programcourse);
	implicitWait( 30);
	Enrollment.EnrollmentRegulation(regulation,testCaseName);
	implicitWait( 30);
	Enrollment.EnrollmentExamType(examType,testCaseName);

	implicitWait( 30);
	Enrollment.EnrollmentSemester(semester,testCaseName);	
	}









	public void handleProgramCourse(Object programcourseCode,ExtentTest testCaseName) throws InterruptedException, IOException {
	

		ExtentTest testCaseScenario = testCaseName.createNode("Program course Test case has started running ");
	
		// Wait for the dropdown and textbox elements
		implicitWait( 30);
		explicitWait( knrPom.getInstanceCourseXP().ProgramCourse, 30);
	
		// Click the college dropdown
		click( knrPom.getInstanceCourseXP().ProgramCourse);
		
		Thread.sleep(1000);  // Sleep to ensure the textbox is fully active
	   		implicitWait(30);	
//		   		
//		   	//li[contains(@class,'select2-results__option')
	//	WebElement ProgramCourseOption = driver.findElement(By.xpath("//li[@role='option' and contains(text(), '" + programcourse + "')]"));

//				explicitWait( ProgramCourseOption, 30);
//		click(yearSessionOption);
//		    	
	// System.out.println(yearSessionOption.isDisplayed());
				click( knrPom.getInstanceCourseXP().programCourseTextBox);
				Thread.sleep(1000);  // Sleep to ensure the textbox is fully active
				implicitWait( 30);
			//	sendKeys(knrPom.getInstanceCourseXP().clgTextBox, String.valueOf(programcourseCode));
				
				// Wait for the dropdown results to load
				implicitWait( 30);
				
				
				WebElement ProgramCourseOption = driver.findElement(By.xpath("//li[contains(@id,'" + programcourseCode + "')]"));

							explicitWait( ProgramCourseOption, 30);
//						click(yearSessionOption);
				
				
				
//				explicitWait( knrPom.getInstanceCourseXP().clgDropDownResults.get(0), 30);
//			
//				// Click the first result in the dropdown
//				if (knrPom.getInstanceCourseXP().clgDropDownResults.get(0).isDisplayed()) {
//					
//					testCaseScenario.log(Status.PASS, "College code has entered sucessfully");
//					click(ProgramCourseOption);
//					
//
//				} else {
////					System.out.println("College code not found.");
//					
//					testCaseScenario.log(Status.FAIL, "College code has not entered ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//
//					
//			//		testCase.log(LogStatus.FAIL, "Click action performed", testCase.addScreenCapture(BasicFunctions.capture(driver)));
//				}
//				 report.endTest(test); // End the test after all logs are added
//			        report.flush();
			
			
//
//				implicitWait(50);	
//			System.out.println("fgkjhgdfkj"+ProgramCourseOption.isDisplayed());	
//				
//				
		if (ProgramCourseOption.isDisplayed()) {
			explicitWait( ProgramCourseOption, 30);
			implicitWait( 30);		
		click( ProgramCourseOption);
		testCaseScenario.log(Status.PASS, "Program course has entered sucessfully");
		
		}
		
		else {
			implicitWait( 30);
			explicitWait(  knrPom.getInstanceCourseXP().ProgramCourse, 30);
			click(  knrPom.getInstanceCourseXP().ProgramCourse);
			implicitWait( 30);
			explicitWait( knrPom.getInstanceCourseXP().ProgramCourse, 30);
			click( ProgramCourseOption);
			testCaseScenario.log(Status.FAIL, "Program course has not entered ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//
		}
		
		

	//reportEnrollmentPage.processOtherDetails(examdate,awardName,semester, regulation, examType);
    // Assuming the ReportCardCheckData method requires all these parameters
	
       

	}

	 

	public void DownloadReport() {

		try {
			// Zoom out to 90% using JavaScript
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("document.body.style.zoom='90%'");
			Actions action = new Actions(driver);

			Thread.sleep(5000);

			implicitWait(30);

//			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportTo).perform();

			action.moveToElement(knrPom.getInstanceResultDeliverablesCP().reportCardExportTo).click().perform();

			implicitWait(30);

//			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportToPdf).perform();

			action.moveToElement(knrPom.getInstanceResultDeliverablesCP().reportCardExportToPdf).click().perform();

			implicitWait(30);

			Thread.sleep(10000);

			// Get the parent window handle and store it in a list
			ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

			// Switch to the second window (index 1 in the list)
			driver.switchTo().window(windowHandles.get(1));

			// Perform actions in the new window/tab
			// ...
			implicitWait(60);

			driver.close();

			// Switch back to the parent window (index 0 in the list)
			driver.switchTo().window(windowHandles.get(0));

		}

		catch (Exception e) {

		}

	}		
		
		public double objectToDataType(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Object cannot be null");
		}

		if (obj instanceof Double) {
			return (Double) obj;
		} else if (obj instanceof Integer) {
			return ((Integer) obj).doubleValue();
		} else if (obj instanceof Float) {
			return ((Float) obj).doubleValue();
		} else if (obj instanceof Long) {
			return ((Long) obj).doubleValue();
		} else if (obj instanceof String) {
			String str = (String) obj;
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("String value cannot be converted to double: " + str);
			}
		} else {
			throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getSimpleName());
		}
	}
}


