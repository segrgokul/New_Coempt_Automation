package knrPageModules;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import knrEnrollmentPageReportCardPatternIdentity.KnrReportCardPatternIdentify;

import pageObjMod.KnrPom;

public class KnrReportEnrollmentPageCourseNameValidation extends BasicFunctions {
	String subject;	
	String status;
	
	KnrReportCardPatternIdentify coursePatternIdentify = new KnrReportCardPatternIdentify();
	
	public void monitorNetworkDuringTest(ExtentTest testCaseName)
			throws InterruptedException, IOException, AWTException {


		try {
			InetAddress address = InetAddress.getByName("google.com");
			if (address.isReachable(3000)) {
				ExtentTest testCaseScenario = testCaseName.createNode("Internet Connectivity Check");
				// testCaseScenario.log(Status.INFO, "Internet is connected.");
				System.out.println("Internet is connected.");
				testCaseScenario.info("Network status is connected");

				try {
					WebElement networkTryAgainBtn = driver
							.findElement(By.xpath("//button[@id='neterrorTryAgainButton']"));
					if (networkTryAgainBtn.isDisplayed()) {
						Thread.sleep(200);

						networkTryAgainBtn.click();
					}

					else {
						WebElement dashBoard = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')])"));

						if (dashBoard.isDisplayed()) {
							ReportCardNavigation();
						}
					}

				}

				catch (Exception e2) {

				}
			} else {
				ExtentTest testCaseScenario = testCaseName.createNode("Internet Connectivity Check");
				System.out.println("Network status is not connected Internet is NOT connected.");
				driver.navigate().refresh();
				testCaseScenario.warning("Network status is not connected",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
		} catch (Exception e) {

			try {
				ExtentTest testCaseScenario = testCaseName.createNode("Internet Connectivity Check");
				testCaseScenario.warning("Network status for the following register number is not connected"

						, MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				System.out.println(
						"Network status for the following register number is not connected please check Internet is NOT connected.");

				driver.navigate().refresh();

			}

			catch (Exception e2) {

				Thread.sleep(7000);

				WebElement networkTryAgainBtn = driver.findElement(By.xpath("//button[@id='neterrorTryAgainButton']"));

				while (networkTryAgainBtn.isDisplayed()) {
					Thread.sleep(200);

					networkTryAgainBtn.click();

				}
				WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));

				if (userName.isDisplayed()) {

					ReportCardNavigation();

				}
			}
		}
	}

	public void ReportCardNavigation() throws IOException, InterruptedException, AWTException {
	
		implicitWait(3);
		explicitWait(KnrPom.getInstanceEnrollXP().loginTags, 30);

		if (KnrPom.getInstanceEnrollXP().loginTags.isDisplayed()) {

//			testCaseName = extentReport.createTest("Login in Tag");

			implicitWait(3);
			explicitWait(KnrPom.getInstanceEnrollXP().loginTags, 30);

			scrollTillWebElement(KnrPom.getInstanceEnrollXP().reportCardOption);
			if (KnrPom.getInstanceEnrollXP().reportCardOption.isDisplayed()) {

				implicitWait(3);
				explicitWait(KnrPom.getInstanceEnrollXP().reportCardOption, 30);
				click(KnrPom.getInstanceEnrollXP().reportCardOption);
			}
		}
	}

	public void ReportCardEnrollNavigation(ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {
		ExtentTest testCaseScenario = testCaseName
				.createNode("Report Card Enrollment wise navigation Test case has started running");

		implicitWait(3);

		try {
			explicitWait(KnrPom.getInstanceEnrollXP().reportCardEnroll, 30);
			scrollTillWebElement(KnrPom.getInstanceEnrollXP().reportCardEnroll);
       try {
		
			if (KnrPom.getInstanceEnrollXP().reportCardEnroll.isDisplayed()) {
				implicitWait(3);
				explicitWait(KnrPom.getInstanceEnrollXP().reportCardEnroll, 30);
				click(KnrPom.getInstanceEnrollXP().reportCardEnroll);
				testCaseScenario.log(Status.PASS, "Report Card Enrollment wise has navigated successfully");

			} else {
				testCaseScenario.log(Status.FAIL, "Report Card Enrollment wise navigation Fail",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			}

		}
		
		
		catch (NoSuchElementException e) {
			monitorNetworkDuringTest(testCaseName);
			testCaseScenario.log(Status.FAIL, "Report Card Enrollment wise element not found Fail",
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}
		
	}catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "Unexpected error FAIL: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentRegNo(Object regno, ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {

		ExtentTest testCaseScenario = testCaseName
				.createNode("Report card Enrollment wise for the following register number: " + regno);

		implicitWait(3);

		try {
			explicitWait(KnrPom.getInstanceEnrollXP().enrollNo, 30);
			click(KnrPom.getInstanceEnrollXP().enrollNo);

			if (KnrPom.getInstanceEnrollXP().enrollNo.isDisplayed()) {
				implicitWait(3);
				explicitWait(KnrPom.getInstanceEnrollXP().enrollNo, 30);
				sendKeys(KnrPom.getInstanceEnrollXP().enrollNo, String.valueOf(regno));
				testCaseScenario.log(Status.PASS,
						"Enrollment wise " + regno + " Register number has entered successfully");

			} else {
				// If enrollNo is not displayed, try alternative action
				try {
					WebElement yearSessionOptionSelect = driver
							.findElement(By.xpath("//li[@role='option' and text()='Select']"));

					if (yearSessionOptionSelect.isDisplayed()) {
						testCaseScenario.log(Status.FAIL,
								"Enrollment wise " + regno + " Register number has not entered FAIL ",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}
				} catch (NoSuchElementException e) {
					monitorNetworkDuringTest(testCaseName);
					testCaseScenario.log(Status.FAIL, "FAIL Alternative year session option not found.",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
			}

		} catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "FAIL Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentExamDate(Object examDate, ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode("Exam Date Test case has started running");

		implicitWait(3);

		try {
			explicitWait(KnrPom.getInstanceEnrollXP().examSeries, 30);
			click(KnrPom.getInstanceEnrollXP().examSeries);

			implicitWait(3);
			explicitWait(KnrPom.getInstanceEnrollXP().examSeries, 30);
			click(KnrPom.getInstanceEnrollXP().examSeries);

			implicitWait(3);
			explicitWait(KnrPom.getInstanceEnrollXP().examSeries, 30);

			if (KnrPom.getInstanceEnrollXP().examSeries.isDisplayed()) {
				click(KnrPom.getInstanceEnrollXP().examSeries);

				implicitWait(20);

				try {
					// Try to find the exam date option
					WebElement examDateOption = driver
							.findElement(By.xpath("//li[@role='option' and text()='" + examDate + "']"));
					explicitWait(examDateOption, 30);

					if (examDateOption.isDisplayed()) {
						explicitWait(examDateOption, 30);
						implicitWait(3);
						click(examDateOption);
						testCaseScenario.log(Status.PASS, "Exam date has entered successfully");
					}

				} catch (NoSuchElementException e) {

					// If exam date option is not found, log failure
					testCaseScenario.log(Status.FAIL, "FAIL Exam date selection failed: Element not found",

							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
				}

			} else {
				// If exam series dropdown is not displayed, try alternative action
				try {
					WebElement yearSessionOptionSelect = driver
							.findElement(By.xpath("//li[@role='option' and text()='Select']"));

					if (yearSessionOptionSelect.isDisplayed()) {
						testCaseScenario.log(Status.FAIL, "Exam date has not entered",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}
				} catch (NoSuchElementException e) {

					testCaseScenario.log(Status.FAIL, "FAIL Alternative year session option not found.",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
				}
			}

		} catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "FAIL Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentAwardName(Object awardName, ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode("Award Name Test case has started running");

		implicitWait(3);
		explicitWait(KnrPom.getInstanceEnrollXP().awardName, 30);

		try {
			if (KnrPom.getInstanceEnrollXP().awardName.isDisplayed()) {
				click(KnrPom.getInstanceEnrollXP().awardName);

				try {
					// Try to find the award option
					WebElement awardOption = driver
							.findElement(By.xpath("//li[@role='option' and text()='" + awardName + "']"));

					if (awardOption.isDisplayed()) {
						explicitWait(awardOption, 30);
						implicitWait(3);
						click(awardOption);
						testCaseScenario.log(Status.PASS, "Award name has entered successfully");
					}

				} catch (NoSuchElementException e) {
					// If award option is not found, log failure
					testCaseScenario.log(Status.FAIL, "Award name selection fail: Element not found",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}

			} else {
				// If award name dropdown is not displayed, try alternative action
				try {
					WebElement yearSessionOptionSelect = driver
							.findElement(By.xpath("//li[@role='option' and text()='Select']"));

					if (yearSessionOptionSelect.isDisplayed()) {
						testCaseScenario.log(Status.FAIL, "FAIL Award name has not entered",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}
				} catch (NoSuchElementException e) {

					testCaseScenario.log(Status.FAIL, "Alternative year session option not found. fail ");
					monitorNetworkDuringTest(testCaseName);
				}
			}

		} catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "fail Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentSemester(Object semester, ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode("Semester Test case has started running");

		implicitWait(3);
		explicitWait(KnrPom.getInstanceEnrollXP().yearSession, 30);

		try {
			if (KnrPom.getInstanceEnrollXP().yearSession.isDisplayed()) {
				while(KnrPom.getInstanceEnrollXP().yearSession.isDisplayed()){
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
				
				if(KnrPom.getInstanceEnrollXP().role2ndOption.isDisplayed()) {
					break;
				}
				}


				try {
					// Try to find the element
					WebElement yearSessionOption = driver
							.findElement(By.xpath("//li[@role='option' and text()='" + semester + "']"));

					explicitWait(yearSessionOption, 30);
					implicitWait(3);
					click(yearSessionOption);
					testCaseScenario.log(Status.PASS, "Semester has entered successfully");

				} catch (NoSuchElementException e) {
					// If element not found, log the failure

					boolean optionsVisible = false;

					// Keep clicking the dropdown until options are visible
					while (!optionsVisible) {
						click(KnrPom.getInstanceEnrollXP().yearSession);

						try {
							// Wait briefly to give time for the options to become visible
							Thread.sleep(500); // Adjust time as needed
							WebElement yearSessionOption = driver
									.findElement(By.xpath("//li[@role='option' and text()='" + semester + "']"));

							explicitWait(yearSessionOption, 30);
							optionsVisible = true; // Exit loop if options are visible
						} catch (Exception e1) {

							// Catch any exception if options are not visible yet, and keep clicking
							System.out.println("Options not visible, retrying...");

						}
					}

					testCaseScenario.log(Status.FAIL, "Semester selection failed: Element not found fail ",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
				}

			} else {

				testCaseScenario.log(Status.FAIL, "fail Year session dropdown is not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				monitorNetworkDuringTest(testCaseName);
			}
		} catch (Exception e) {
			testCaseScenario.log(Status.FAIL, "fail Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentRegulation(Object regulation, ExtentTest testCaseName)
			throws IOException, InterruptedException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode("Regulation Test case has started running");

		implicitWait(3);
		System.out.println("regulation" + regulation);

		explicitWait(KnrPom.getInstanceEnrollXP().regulation, 30);

		try {
			if (KnrPom.getInstanceEnrollXP().regulation.isDisplayed()) {
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().regulation);
				implicitWait(3);

				try {
					// Try to find the regulation option
					WebElement regulationOption = driver
							.findElement(By.xpath("//li[@role='option' and text()='" + regulation + "']"));

					explicitWait(regulationOption, 30);
					implicitWait(3);
					click(regulationOption);
					testCaseScenario.log(Status.PASS, "Regulation has entered successfully");

				} catch (NoSuchElementException e) {

					e.printStackTrace();

					// If regulation option is not found, log failure
					testCaseScenario.log(Status.FAIL, "Regulation selection fail: Element not found",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
				}

			} else {
				// If regulation dropdown is not displayed, try alternative action
				try {
					WebElement yearSessionOptionSelect = driver
							.findElement(By.xpath("//li[@role='option' and text()='Select']"));

					if (yearSessionOptionSelect.isDisplayed()) {

						implicitWait(3);
						click(KnrPom.getInstanceEnrollXP().regulation);
						testCaseScenario.log(Status.FAIL, "Regulation has not entered fail",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						monitorNetworkDuringTest(testCaseName);
					}
				} catch (NoSuchElementException e) {

					testCaseScenario.log(Status.FAIL, "Alternative year session option not found fail.",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
				}
			}

		} catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "FAIL Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
		}
	}

	public void EnrollmentExamType(Object examType, ExtentTest testCaseName)
			throws InterruptedException, IOException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode("Exam Type Test case has started running");

		implicitWait(3);

		System.out.println("examType" + examType);
		explicitWait(KnrPom.getInstanceEnrollXP().examType, 30);

		try {
			if (KnrPom.getInstanceEnrollXP().examType.isDisplayed()) {
				click(KnrPom.getInstanceEnrollXP().examType);
				implicitWait(3);

				try {
					// Try to find the exam type option
					WebElement examTypesOption = driver
							.findElement(By.xpath("//li[@role='option' and text()='" + examType + "']"));

					if (examTypesOption.isDisplayed()) {
						explicitWait(examTypesOption, 30);
						implicitWait(3);
						click(examTypesOption);
						testCaseScenario.log(Status.PASS, "Exam Type has entered successfully");
					}

				} catch (NoSuchElementException e) {
					// If exam type option is not found, log failure
					testCaseScenario.log(Status.FAIL, "Exam Type selection fail: Element not found",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					monitorNetworkDuringTest(testCaseName);
					System.out.println("Exam Type selection fail: Element not found");
				}

			} else {
				// If exam type dropdown is not displayed, try alternative action
				try {
					WebElement yearSessionOptionSelect = driver
							.findElement(By.xpath("//li[@role='option' and text()='Select']"));

					if (yearSessionOptionSelect.isDisplayed()) {
						testCaseScenario.log(Status.FAIL, "Exam Type has not entered fail ",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("Exam Type has not entered fail");
						monitorNetworkDuringTest(testCaseName);
					}
				} catch (NoSuchElementException e) {

					testCaseScenario.log(Status.FAIL, "Alternative year session option not found fail.",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					System.out.println("Alternative year session option not found fail.");
					monitorNetworkDuringTest(testCaseName);
				}
			}

		} catch (Exception e) {

			testCaseScenario.log(Status.FAIL, "FAIL Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
			System.out.println("FAIL");

		}
	}

	public void submitButton(ExtentTest testCaseName) throws InterruptedException, IOException, AWTException {

		ExtentTest testCaseScenario = testCaseName.createNode(" Submit Button Test case has started running ");

		try {
			implicitWait(3);
			explicitWait(KnrPom.getInstanceEnrollXP().submitBtn, 10);
			if (KnrPom.getInstanceEnrollXP().submitBtn.isDisplayed()) {

				click(KnrPom.getInstanceEnrollXP().submitBtn);
				testCaseScenario.log(Status.PASS, "Submit button click sucessfully");

				Thread.sleep(3000);
			
			}

			else {

				testCaseScenario.log(Status.FAIL, "FAIL Submit button not found or not clickable",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				monitorNetworkDuringTest(testCaseName);
				System.out.println("FAIL Submit button not found or not clickable");
			}

		}

		catch (Exception e) {
			testCaseScenario.log(Status.FAIL, "Fail Unexpected error: " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			monitorNetworkDuringTest(testCaseName);
			System.out.println("FAIL Submit button not found or not clickable");
		}
	}

	public void DownloadReport(Object regno,ExtentTest testCaseName) throws IOException, InterruptedException, AWTException {
		try {
		Actions action = new Actions(driver);

		implicitWait(3);
		explicitWait(KnrPom.getInstanceEnrollXP().reportCardExportTo, 30);
		
		if (KnrPom.getInstanceEnrollXP().reportCardExportTo.isDisplayed()) {
			ExtentTest testCaseScenario = testCaseName
					.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno);

		
		// Click on "Export To" and then "Export to PDF"
		action.moveToElement(KnrPom.getInstanceEnrollXP().reportCardExportTo).click().perform();
		implicitWait(7);
		action.moveToElement(KnrPom.getInstanceEnrollXP().reportCardExportToPdf).click().perform();
		implicitWait(7);

		Thread.sleep(3000);

		// Switch to the second window (new tab)
		ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));

		// Validate PDF URL
		implicitWait(8);
		if (driver.getCurrentUrl().endsWith(".pdf")) {
			System.out.println("PDF opened successfully: " + driver.getCurrentUrl());
			System.out.println("=========================");
			testCaseScenario.log(Status.PASS, "PDF opened successfully sucessfully" + driver.getCurrentUrl() + " for the following register number " + regno  );

		} else {
			System.out.println(driver.getCurrentUrl());
			System.out.println("Fail to open the PDF.");
			testCaseScenario.log(Status.FAIL, "Fail to open the PDF.");
			System.out.println("Fail to open the PDF.");

		}}}

		
		
	
	catch (Exception e) {
	}
}

	public void downloadPdfReportValidation(ExtentTest testCaseName, String regno, String semester,String regulation,Object paper1, Object paper2,
			Object paper3,Object paper4, Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, String subjectToFind)
			throws InterruptedException, IOException, AWTException {
		try {	

		// Wait for the PDF file to download
			String downloadDir = System.getProperty("user.home") + "/Downloads"; // Downloads folder
			File dir = new File(downloadDir);
			File latestFile = null;
			while (true) {
				File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf"));
				if (files != null && files.length > 0) {
					Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
					latestFile = files[0];
					break; // File is downloaded
				}
				Thread.sleep(700); // Check every second
			}	

			try {
			
				if (KnrPom.getInstanceEnrollXP().okBtn.isDisplayed()&& KnrPom.getInstanceEnrollXP().noRecordsFoundAlert.isDisplayed() ) {
					monitorNetworkDuringTest(testCaseName);
					ExtentTest testCaseScenario = testCaseName
							.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

					
					System.out.println(KnrPom.getInstanceEnrollXP().okBtn.isDisplayed());
			
					testCaseScenario.log(Status.SKIP, "Facing error to generate the pdf for following regno: " +regno + " and subject: "+ subjectToFind+" Please check whether the following register number is NE or NR ",
							
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					explicitWait(KnrPom.getInstanceEnrollXP().okBtn, 10);
					click(KnrPom.getInstanceEnrollXP().okBtn);
					implicitWait(3);
					driver.navigate().refresh();
			
				}

			}

			catch (Exception e) {
				
				if (driver.getCurrentUrl().endsWith(".pdf")) {
			
					monitorNetworkDuringTest(testCaseName);
					
					ExtentTest testCaseScenario = testCaseName
						.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

				System.out.println("able to geanerater");
				testCaseScenario.log(Status.PASS, "Able to generate ");

			System.out.println(subjectToFind+"subjectToFind");
			
			//Pattern vlaidation
			coursePatternIdentify.processPdfBasedOnCoursePattern(latestFile, regno,semester,regulation, paper1, paper2, paper3,paper4, theoryExamTotal, practicalExamTotal,
					grandTotal, subjectToFind, testCaseName);
		
			
				}	else	if (KnrPom.getInstanceEnrollXP().enrollNo.isDisplayed()) {
					monitorNetworkDuringTest(testCaseName);
					ExtentTest testCaseScenario = testCaseName
							.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

			
					testCaseScenario.log(Status.SKIP, "Facing error to generate the pdf for following regno: " +regno + " and subject: "+ subjectToFind+"check whether the following register number is NE or NR",
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					implicitWait(3);		
				}
				else if (KnrPom.getInstanceEnrollXP().okBtn.isDisplayed()&&KnrPom.getInstanceEnrollXP().allFieldsMandatoryAlert.isDisplayed() ) {
					monitorNetworkDuringTest(testCaseName);
					ExtentTest testCaseScenario = testCaseName
							.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

					
					System.out.println(KnrPom.getInstanceEnrollXP().okBtn.isDisplayed());
			
					testCaseScenario.log(Status.FAIL, "Facing error to generate the pdf for following regno: " +regno + " and subject: "+ subjectToFind,
							
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					explicitWait(KnrPom.getInstanceEnrollXP().okBtn, 10);
					click(KnrPom.getInstanceEnrollXP().okBtn);
					implicitWait(3);
					driver.navigate().refresh();
			
				}
			
		}
			
		}//try
		

		catch (Exception e) {
			
			if (KnrPom.getInstanceEnrollXP().enrollNo.isDisplayed()) {
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

				monitorNetworkDuringTest(testCaseName);
				testCaseScenario.log(Status.FAIL, "Please check the following regno: " +regno + " and subject: "+ subjectToFind+"check wherther",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				implicitWait(3);		
			}
			
		}
	}
	

	

			

	
	
	
	
	
	
}
