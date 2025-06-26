package knrPageModules;



import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import pageObjMod.KnrPom;


public class KnrReportCoursePage extends BasicFunctions {
	public Object[][] data1;
	public Object[][] data2;
	  // Static ExtentTest, accessible from anywhere in the class
    public static ExtentTest testCaseName;
//	static ExtentReports report;


	    // Constructor to initialize ExtentReports
	  // Set the test case name only once
	 
    
	   
   
    KnrReportEnrollmentPage Enrollment = new  KnrReportEnrollmentPage();
	    	

	public void ReportCardNavigation(ExtentTest testCaseName) throws IOException {
		
	//	testCaseName.log(Status.INFO, "Report Card Navigation");
		ExtentTest testCaseScenario = testCaseName.createNode("Report Card Navigation");
		
		implicitWait( 30);
		explicitWait( KnrPom.getInstanceCourseXP().loginTags, 30);

		if (KnrPom.getInstanceCourseXP().loginTags.isDisplayed()) {

			implicitWait( 30);
			explicitWait( KnrPom.getInstanceCourseXP().loginTags, 30);

		//	testCaseName.log(Status.INFO, "Report Card Navigation");
			scrollTillWebElement( KnrPom.getInstanceCourseXP().reportCardOption);

			implicitWait( 30);
			
		
			
			if(KnrPom.getInstanceCourseXP().reportCardOption.isDisplayed()) {
			
			explicitWait( KnrPom.getInstanceCourseXP().reportCardOption, 30);
			click( KnrPom.getInstanceCourseXP().reportCardOption);
			testCaseScenario.log(Status.PASS, "Report Card is Navigated sucessfully");
			
		
		//	testCaseName.log(Status.FAIL, "This is a pass message");
			}	}
			else {
	       
				testCaseScenario.log(Status.FAIL, "Report Card page not navigating ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
			}

	   
		}
	

	
	
	public void ReportCardCourseNavigation(ExtentTest testCaseName) throws IOException {
	// // // 	// // // ExtentReports     report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\ReportCoursePage.html");
    //    report.loadConfig(new File("path_to_config_file.xml"));
	    
		
		ExtentTest testCaseScenario = testCaseName.createNode("Report card course wise navigation");
		
		implicitWait( 30);
		explicitWait( KnrPom.getInstanceCourseXP().reportCardCourse, 30);

		scrollTillWebElement( KnrPom.getInstanceCourseXP().reportCardCourse);

		implicitWait( 30);
		
		
		try {
		if(KnrPom.getInstanceCourseXP().reportCardCourse.isDisplayed()) {
			implicitWait( 30);		
		
		explicitWait( KnrPom.getInstanceCourseXP().reportCardCourse, 30);
		click( KnrPom.getInstanceCourseXP().reportCardCourse);
		testCaseScenario.log(Status.PASS, "Report Card is course wise navigation sucessfully");
	
		
		}
		}
		catch(Exception e) {
			testCaseScenario.log(Status.FAIL, "Report Card is course wise does not navigation sucessfully ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		}

	//	testCase.log(LogStatus.PASS, "ReportCard option is navigated perfectly ");
		

	}
	
	public void handleCollegeCode(Object clgCode,ExtentTest testCaseName) throws InterruptedException, IOException {
		 
		
		ExtentTest testCaseScenario = testCaseName.createNode("Report card course wise for the following clg code: " + clgCode);
	

		// Wait for the dropdown and textbox elements
		implicitWait( 50);
		
		KnrPom.getInstanceCourseXP().clgDropDown.isDisplayed();
		
		explicitWait( KnrPom.getInstanceCourseXP().clgDropDown, 30);
	
		// Click the college dropdown
		click( KnrPom.getInstanceCourseXP().clgDropDown);
		
		// Wait for the textbox to become clickable
		implicitWait( 30);
		explicitWait( KnrPom.getInstanceCourseXP().clgTextBox, 30);
		
		// Enter the college code in the textbox
		click( KnrPom.getInstanceCourseXP().clgTextBox);
		Thread.sleep(1000);  // Sleep to ensure the textbox is fully active
		implicitWait( 30);
		sendKeys(KnrPom.getInstanceCourseXP().clgTextBox, String.valueOf(clgCode));
		
		// Wait for the dropdown results to load
		implicitWait( 30);
		explicitWait( KnrPom.getInstanceCourseXP().clgDropDownResults.get(0), 30);
	
		// Click the first result in the dropdown
		if (KnrPom.getInstanceCourseXP().clgDropDownResults.get(0).isDisplayed()) {
			click( KnrPom.getInstanceCourseXP().clgDropDownResults.get(0));
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









	public void handleProgramCourse(Object programcourse,ExtentTest testCaseName) throws InterruptedException, IOException {
	

		ExtentTest testCaseScenario = testCaseName.createNode("Program course Test case has started running ");
	
		// Wait for the dropdown and textbox elements
		implicitWait( 30);
		explicitWait( KnrPom.getInstanceCourseXP().ProgramCourse, 30);
	
		// Click the college dropdown
		click( KnrPom.getInstanceCourseXP().ProgramCourse);
		
		Thread.sleep(1000);  // Sleep to ensure the textbox is fully active
	   		implicitWait(30);	
//		   		
//		   	//li[contains(@class,'select2-results__option')
		WebElement ProgramCourseOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + programcourse + "']"));
				explicitWait( ProgramCourseOption, 30);
//		click(yearSessionOption);
//		    	
	// System.out.println(yearSessionOption.isDisplayed());
		if (ProgramCourseOption.isDisplayed()) {
			explicitWait( ProgramCourseOption, 30);
			implicitWait( 30);		
		click( ProgramCourseOption);
		testCaseScenario.log(Status.PASS, "Program course has entered sucessfully");
		
		}
		
		else {
			implicitWait( 30);
			explicitWait(  KnrPom.getInstanceCourseXP().ProgramCourse, 30);
			click(  KnrPom.getInstanceCourseXP().ProgramCourse);
			implicitWait( 30);
			explicitWait( KnrPom.getInstanceCourseXP().ProgramCourse, 30);
			click( ProgramCourseOption);
			testCaseScenario.log(Status.FAIL, "Program course has not entered ", MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		}
		
		

	//reportEnrollmentPage.processOtherDetails(examdate,awardName,semester, regulation, examType);
    // Assuming the ReportCardCheckData method requires all these parameters
	
       

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
