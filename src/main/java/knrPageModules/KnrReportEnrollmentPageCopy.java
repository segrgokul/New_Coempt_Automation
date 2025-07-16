
package knrPageModules;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
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
import pageObjMod.KnrPom;

public class KnrReportEnrollmentPageCopy extends BasicFunctions {
	static ExtentTest test;

	public double Paper1=0.0;
	public double Paper2=0.0;
	public double Paper3=0.0;
	public double Paper4=0.0;
	
	double TheroryExamTotal=0.0;
	double PraticalExamTotal=0.0;
	public double ExamTotalScore=0.0;

	double securedMark=0.0;
	double paper1Mark=0.0;

	double paper2Mark=0.0;
	double paper3Mark=0.0;
	double paper4Mark=0.0;
	double praticalMinMark=0.0;

	static double minMark=0.0;
	double paper1MinMark=0.0;
	double paper2MinMark=0.0;
	double paper3MinMark=0.0;
	double paper4MinMark=0.0;
	double practicalMinMark=0.0;
	double theroryExamTotalMinMark=0.0;
	double examTotalScoreMinMark=0.0;

	double theoryMinMark=0.0;
	double theoryMaxMark=0.0;
	double grandTotalMinMark=0.0;
	double grandTotalMaxMark=0.0;
	double theoryTotal=0.0;
	double praticalTotal=0.0;
	double grandTotal=0.0;
	String subject;
	// four pattern
	double theorySecMark=0.0;

	double praticalMaxMark=0.0;

	double praticalVivaMaxMark=0.0;
	double praticalTotalMaxMark=0.0;
	double praticalTotalSecMark=0.0;
	double grandTotalSecMark=0.0;

	String status;

	double theoryInternalMaxMark=0.0;
	double theoryInternalSecMark=0.0;

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
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
				implicitWait(3);
				click(KnrPom.getInstanceEnrollXP().yearSession);
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

	public void downloadPdfReportValidation1(ExtentTest testCaseName, Object regno,Object semester,Object regulation, Object paper1, Object paper2,
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
			processPdfBasedOnCoursePattern(latestFile, regno, paper1, paper2, paper3,paper4, theoryExamTotal, practicalExamTotal,
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

	public void processBHMSRegulation22SubjectPatternPdf(Object Regno, File latestFile, Object paper1, Object paper2,
			Object paper3,Object paper4, Object praticalExam, Object theoryExam, Object examTotal, String subjectToFind,
			ExtentTest testCaseName) throws IOException {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("---------------------------------------------------");
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");
				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);
					// TO print the text
					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
				//	System.out.println(text);

					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");
					// Extract registration number

			
					Pattern securedMarksPattern = Pattern.compile("Secured Marks\\s*:\\s*(\\d+)");

					Matcher securedMatcher = securedMarksPattern.matcher(text);
					if (securedMatcher.find()) {
						String securedMarks = securedMatcher.group(1); // Extract the number
						securedMark = Long.parseLong(securedMarks);

						System.out.println("Secured Marks: " + securedMark);

					} else {

						System.out.println("No match found.");
					}

					
					String regexBHMS2022 = "(BHMS)\\s+";
					Pattern regexBHMS2022Pattern = Pattern.compile(regexBHMS2022, Pattern.MULTILINE);
					Matcher regexBHMS2022Matcher = regexBHMS2022Pattern.matcher(text);
					
					if (regexBHMS2022Matcher.find()) {

						System.out.println("Match found Course: " + regexBHMS2022Matcher.group());
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.PASS, "Match found Course:" + regexBHMS2022Matcher.group()
								+ " The following Register number " + Regno + " for the subject " + subjectToFind);

					}
					
					Pattern bhms22MarksPattern = Pattern.compile(
							
							
//							"^([\\s\\S]*?)\\s+" + // 1. Subject Name
						//	"^([A-Z][A-Z\\s\\-&()\\.]+?)\\s+" +  // New: only captures uppercase subject lines
							"^(?!.*Theory Marks Practical or Clinical Marks Result Max Obtained Sub total)" + // Skip lines with this header
							"([A-Z][A-Z\\s\\-&()\\.]+?)\\s+" + 
							"(HomUG-[A-Z]+(?:-[A-Z]+)?)\\s+" + // 2. Subject Code
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 3. Mark 1
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 4. Mark 2
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 5. Mark 3
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 6. Mark 4
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 7. Mark 5
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 8. Mark 6
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 9. Mark 7
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 10. Mark 8
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 11. Mark 9
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 12. Mark 10
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 13. Mark 11
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)" + // 14. Mark 12
							"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 15. Mark 13 (optional)
							"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 16. Mark 14 (optional)
							"(?:\\s+([A-Z]))?" + // 17. Optional grade letter
							"\\s+(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // 18. Final Result
							Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

					Matcher bhms22MarksPatternMatcher = bhms22MarksPattern.matcher(text);

					while (bhms22MarksPatternMatcher.find()) {
						
						
						if(bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim().contains("Theory Marks Practical or Clinical Marks Result Max Obtained Sub total")) {
							subject =  bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").replaceAll(
								    "Theory Marks Practical or Clinical Marks Result Max Obtained Sub total", ""
									).trim();
							
						}
						else {
							subject = bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
						}
						
						String subjectCode =bhms22MarksPatternMatcher.group(2);
						String theoryMaxMarks = bhms22MarksPatternMatcher.group(3);
						String theoryMinMarks = bhms22MarksPatternMatcher.group(4);
						String theoryObtainedMarks = bhms22MarksPatternMatcher.group(5);
						String practicalMaxPracticalMarks = bhms22MarksPatternMatcher.group(6);
						String practicalMaxVivaMarks = bhms22MarksPatternMatcher.group(7);
						String practicalMaxIAMarks = bhms22MarksPatternMatcher.group(8);
						String practicalObtainedPracticalMarks = bhms22MarksPatternMatcher.group(9);
						String practicalObtainedVivaMarks = bhms22MarksPatternMatcher.group(10);
						String practicalObtainedIAMarks = bhms22MarksPatternMatcher.group(11);
						String subTotalMaxMarks = bhms22MarksPatternMatcher.group(12);
						String subTotalMinMarks= bhms22MarksPatternMatcher.group(13);
						String subTotalObtainedMarks = bhms22MarksPatternMatcher.group(14);
						String subjectTotal = bhms22MarksPatternMatcher.group(15);
						String finalResult2 = bhms22MarksPatternMatcher.group(16);
						String finalResult3 = bhms22MarksPatternMatcher.group(17);
						status= bhms22MarksPatternMatcher.group(18);
						String finalTheoryObtainedMarks =null;
						String finalSubjectTotal = null;
						System.out.println("=====================================================================");
						
						System.out.println("Subject                     : " + subject);
						System.out.println("Subject Code                : " + subjectCode);
						System.out.println("Theory Max Marks            : " + theoryMaxMarks);
						System.out.println("Theory Min Marks            : " + theoryMinMarks);
						System.out.println("Theory Obtained Marks       : " + theoryObtainedMarks);
						System.out.println("Practical Max Practical Marks     : " + practicalMaxPracticalMarks);
						System.out.println("Practical Max Viva Marks          : " + practicalMaxVivaMarks);
						System.out.println("Practical Max IA Marks      : " + practicalMaxIAMarks);
						System.out.println("Practical Obtained Practical Marks: " + practicalObtainedPracticalMarks);
						System.out.println("Practical Obtained Viva Marks     : " + practicalObtainedVivaMarks);
						System.out.println("Practical Obtained IA Marks : " + practicalObtainedIAMarks);
						System.out.println("Sub Total Max Marks         : " + subTotalMaxMarks);
						System.out.println("Sub Total Min Marks          : " + subTotalMinMarks);
						System.out.println("Sub Total obtained Marks      : " + subTotalObtainedMarks);
						System.out.println("Subject Total      : " + subjectTotal);
						System.out.println("Final Result2      : " + finalResult2);
						System.out.println("Final Result3      : " + finalResult3);
						System.out.println("Final Result4      : " + status);


						   Pattern pattern = Pattern.compile("Elective-\\d+\\s+(.*)\\s+([A-F])$",
                                   Pattern.MULTILINE);
						   Matcher matcher = pattern.matcher(text);
						while (matcher.find()) {
							
							String elective = matcher.group(0).trim();
							String subject = matcher.group(1).trim();
							String grade = matcher.group(2);
							
							System.out.println("Elective: " +elective);
							System.out.println("Subject: " + subject ); 
							System.out.println("Grade: " +grade);
								
						}
						System.out.println("=====================================================================");
						
						try {
						if(!finalResult3.equals("null")) {
							
							finalTheoryObtainedMarks = theoryObtainedMarks+finalResult3;
							
							finalSubjectTotal = subjectTotal+finalResult3;
							
							System.out.println("finalTheoryObtainedMarks: " +finalTheoryObtainedMarks);
							System.out.println("finalSubjectTotal: " + finalSubjectTotal);
							}
						}
						catch(Exception e) {
								finalTheoryObtainedMarks = theoryObtainedMarks;								
								finalSubjectTotal =subjectTotal;
								
								System.out.println("finalTheoryObtainedMarks: " +finalTheoryObtainedMarks);
								System.out.println("finalSubjectTotal: " + finalSubjectTotal);

							}
					
					
						if ((status.trim().equals("Pass") || status.trim().equals("Fail")
								|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

							try {

								if (!theoryObtainedMarks.trim().equals("NA") || !theoryObtainedMarks.trim().equals("AB")
										|| !theoryObtainedMarks.trim().equals("NE")
										|| !theoryObtainedMarks.trim().equals("NE (AT)")) {
								
									
									if(finalTheoryObtainedMarks.contains("G")) {
										paper1Mark = Double.parseDouble(theoryObtainedMarks);
										System.out.println("Yes");
										verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);
										gracePaper1Check(Regno,"Theory Internal Sec Marks",paper1,
											 subjectToFind,  finalResult3, testCaseName);
										
										//	
										
									}else {
										try {
											
											validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryObtainedMarks, theoryMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Paper 1 Sec mark validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Paper 1 Sec mark " + theoryObtainedMarks);							
									}
									}
									
								}
								// Use the value
							}
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Paper 1 Sec mark validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Paper 1 Sec mark " + theoryObtainedMarks);							
							}
							
					
							try {

								nonValidateMarks(Regno," Practical Obtained Practical Sec mark ", subject,
										practicalObtainedPracticalMarks, testCaseName);		
						}
						
						catch(Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode("practical Obtained Practical Sec mark validation for the subject " + subject +" Test case has started running");
							
							testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" practical Obtained Practical Sec mark is: " + practicalObtainedPracticalMarks,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							
							System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Obtained Practical Sec mark is: " + practicalObtainedPracticalMarks);
												
						}

							try {

								nonValidateMarks(Regno,"Practical Obtained Viva Sec mark ", subject,
										practicalObtainedVivaMarks, testCaseName);		
						}
						
						catch(Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode("practical Obtained Viva Sec mark validation for the subject " + subject +" Test case has started running");
							
							testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" practical Obtained Viva Sec mark is: " + practicalObtainedVivaMarks,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							
							System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Obtained Viva Sec mark is: " + practicalObtainedVivaMarks);
												
						}
						
												
					
			
							try {

								if (!subTotalObtainedMarks.equals("NA") || !subTotalObtainedMarks.equals("AB")
										|| !subTotalObtainedMarks.equals("NE") || !subTotalObtainedMarks.equals("NA")
										|| !subTotalObtainedMarks.equals("NE (AT)")
										|| ! subTotalObtainedMarks.equals("---")) {
									praticalTotalMaxMark = Double.parseDouble(subTotalMaxMarks);
									praticalTotalSecMark = Double.parseDouble(subTotalObtainedMarks);

									double praticalTotal = Double.parseDouble(practicalObtainedPracticalMarks)+
											
											 Double.parseDouble(practicalObtainedVivaMarks) + Double.parseDouble(practicalObtainedIAMarks) ;
											
									System.out.println(praticalTotal);
									System.out.println(praticalTotalSecMark);
									System.out.println("subTotalObtainedMarks:" +subTotalObtainedMarks);
									if (praticalTotal == praticalTotalSecMark) {
									
										ExtentTest testCaseScenario = testCaseName
												.createNode("pratical total marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.PASS,
												"Both theory papers total "+praticalTotal +" and theory total "+praticalTotalSecMark +" mark are equals ");

										System.out.println("Both theory papers total "+praticalTotal +" and theory total "+praticalTotalSecMark +" mark are equals ");

										
									}

									else {
										ExtentTest testCaseScenario = testCaseName
												.createNode("pratical total marks validation for the subject "
														+ subject + " Test case has started running");
										testCaseScenario.log(Status.FAIL,
												"Both theory papers total "+praticalTotal +" and theory total "+praticalTotalSecMark +" mark are not equals ", 	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
										System.out.println("Both theory papers total "+praticalTotal +" and theory total "+praticalTotalSecMark +" mark are equals ");
									}

								
									try {	
										validateMarks(Regno,"Pratical Total Sec. Marks", paper1, paper2, paper3,paper4,
												  theoryExam,praticalExam,subject,
										        examTotal, subTotalObtainedMarks, subTotalMaxMarks, 0.50, testCaseName);		
									}	
										
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Pratical Total Sec. Marks " + subTotalObtainedMarks);							
									}
									
								}
							} catch (NumberFormatException e) {


							}
							
							try {
								if (!subjectTotal.equals("NA") || !subjectTotal.equals("AB")
										|| !subjectTotal.equals("NE") || !subjectTotal.equals("NA")
										||	!subjectTotal.equals("---")
										|| !subjectTotal.equals("NE (AT)")) {	
									if(finalTheoryObtainedMarks.contains("G")) {
										paper3Mark =Double.parseDouble((String) paper3);
										Paper3 =Double.parseDouble((String) paper3);
										paper1Mark = Double.parseDouble(theoryObtainedMarks);
										System.out.println("Yes");
										grandTotalMaxMark = Double.parseDouble(theoryMaxMarks) + Double.parseDouble(subTotalMaxMarks) ;
										System.out.println("grandTotalMaxMark"+ grandTotalMaxMark);
											ExamTotalScore = Double.parseDouble(subjectTotal);
											
										verifyScore(ExamTotalScore, grandTotalMaxMark, 0.50);				
										graceTheoryPlusPracticalSecMarksCheck(Regno,"Theory Internal Sec Marks",theoryExam,
											 subjectToFind,  finalResult3, testCaseName);
		
									}else {
										try {	
										paper3Mark=0.0;
										Paper3=0.0;
										theoryInternalMaxMark = Double.parseDouble(theoryMaxMarks);
									
										paper1Mark = Double.parseDouble(theoryObtainedMarks);
										grandTotalMaxMark = Double.parseDouble(theoryMaxMarks) + Double.parseDouble(subTotalMaxMarks) ;
									
										String grandTotalMaxMarks = String.valueOf(grandTotalMaxMark);
										
											validateMarks(Regno,"Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, subjectTotal, grandTotalMaxMarks, 0.50, testCaseName);		
										}	
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Pratical Total Sec. Marks " + subjectTotal);							
										}
									}
									System.out.println("==============");
									// securedMarks(Regno, examTotal, testCaseName);
									System.out.println("==============");
								} // Use the value
							} catch (NumberFormatException e) {
								if (subjectTotal.equals("AB") || subjectTotal.equals("NE")
										|| subjectTotal.equals("NA")
										|| subjectTotal.equals("---")
										|| subjectTotal.equals("NE (AT)")) {
									ExamTotalScore = 0.0;
									System.out.println(ExamTotalScore);

									ExtentTest testCaseScenario = testCaseName
											.createNode("Grand Total Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");

									testCaseScenario.log(Status.INFO,
											"\n The Following Registration number " + Regno
													+ " for the Subject " + subject + " marks is: "
													+ subjectTotal);

									System.out.println("\nThe Following Registration number " + Regno
											+ " Grand Total Sec. Marks is:" + subjectTotal);

								}

								else {

									ExamTotalScore = 0.0;
									System.out.println(ExamTotalScore);

									ExtentTest testCaseScenario = testCaseName
											.createNode("Grand Total Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");

									testCaseScenario.log(Status.INFO,
											"\n The Following Registration number " + Regno
													+ " for the Subject " + subject + " marks is: "
													+ subjectTotal);

									System.out.println("\nThe Following Registration number " + Regno
											+ " Grand Total Sec. Marks is:" + subjectTotal);
								}

							}

						}

					}

				}
			} catch (Exception e) {

			}

		} // if
	}

	public void processEightSubjectPatternPdf(Object Regno, File latestFile, Object paper1, Object paper2,
			Object paper3,Object paper4, Object theoryExam, Object praticalExam,Object examTotal, String subjectToFind,
			ExtentTest testCaseName) throws IOException {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
			//		System.out.println(text);

					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");
		
					Pattern securedMarksPattern = Pattern.compile("Secured Marks\\s*:\\s*(\\d+)");

					Matcher securedMatcher = securedMarksPattern.matcher(text);
					if (securedMatcher.find()) {
						String securedMarks = securedMatcher.group(1); // Extract the number
						securedMark = Long.parseLong(securedMarks);

						System.out.println("Secured Marks: " + securedMark);

					} else {

						System.out.println("No match found.");
					}

					String courseSubjectNameRegex = "(B\\.Sc\\. MLT|M\\.Sc\\.|B\\.Sc\\.|BPT|Post Basic B.Sc.Nursing|BDS|MBBS|BUMS|BAMS)\\s+";

					Pattern courseSubjectNameRegexPattern = Pattern.compile(courseSubjectNameRegex, Pattern.MULTILINE);
					Matcher courseSubjectNameRegexPatternMatcher = courseSubjectNameRegexPattern.matcher(text);
				

					if (courseSubjectNameRegexPatternMatcher.find()) {
						System.out.println("Match found Course: " + courseSubjectNameRegexPatternMatcher.group());
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.PASS, "Match found Course:" + courseSubjectNameRegexPatternMatcher.group()
								+ " The following Register number " + Regno + " for the subject " + subjectToFind);

					}
		
					else {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found",MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subject + " No match found");

						System.out.println("No match found.");
					}
					
//					Pattern mbbsMarksPattern = Pattern.compile(
//						    "(?<subject>[A-Z &]+)\\s+" +
//						    "(?<marks>(?:(?:\\d+|NA|AB|NE|\\(AT\\))\\s*(?:\\(F\\))?\\s*)*?)" +
//						    "(\\d+)\\s+(\\d+)\\s+(?:(?:\\d+|NA|AB|NE|NE\\s*\\(AT\\))\\s*(?:\\(F\\))?)\\s+" +
//
//						    "(?<result>Pass|Fail|AP)"
//						);

			

					// Pattern mbbsMarksPattern = Pattern.compile("^([A-Z
					// &]+?)\\s+((?:\\d+|AB|NE|NA)(?:\\s+(?:\\d+|AB|NE|NA|\\(AT\\)))*?)\\s+(Pass|Fail)$\r\n");
				
				
				
							if ((courseSubjectNameRegexPatternMatcher.group().trim().contains("BUMS")) || (courseSubjectNameRegexPatternMatcher.group().trim().contains("BAMS"))
									|| (courseSubjectNameRegexPatternMatcher.group().trim().contains("BHMS"))) {
				
								Pattern patternBums = Pattern.compile(

										"^(?!Fail|Pass|AP|NE|AB|Theory|KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Practical|Secured Marks in Words: |Grand Total|Controller of Examinations|Principal)\\s*"
												+
												// "([A-Za-z &,\\-]+(?:\\s+(?:\\(?[A-Za-z &,\\-]+\\)?))*)\\s*"+
												"([A-Z][A-Za-z &,\\-]+(?:\\s+[A-Za-z &,\\-]+)*)\\s*" 
									
												+ "(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 2: Theory Int Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 3: Theory Int Sec
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 4: Theory Univ Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 5: Theory Univ Sec
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 6: Prac Int Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 7: Prac Int Sec
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 8: Prac Univ Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 9: Prac Univ Sec
												"(\\d+|NA|NE|AB|---)\\s+" + // Group 10: Theory + Prac Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 11: Theory + Prac Sec
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 12: Grand Max
												"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 13: Grand Sec
												"(Pass|Fail|AP)", // Group 14: Result
										Pattern.MULTILINE);

								Matcher bumsMarksPatternmatcher = patternBums.matcher(text);
								ExtentTest testCaseScenario1 = testCaseName.createNode("Pattern progress");

								
								while (bumsMarksPatternmatcher.find()) {
									testCaseScenario1.log(Status.INFO, "Pattern process sucessful");

									try {

								System.out.println("yes");

								
								System.out.println("mjdnfsgdfg" + bumsMarksPatternmatcher.group(1));
								
								System.out.println(
										"Subject456: " + bumsMarksPatternmatcher.group(1).replace("\n", " ").trim());

								subject = bumsMarksPatternmatcher.group(1).replace("\n", " ").trim();

								String theoryPaper1 = bumsMarksPatternmatcher.group(2);
								String theoryPaper2 = bumsMarksPatternmatcher.group(3);
								String totalTheoryMaxMarks = bumsMarksPatternmatcher.group(4);
								String totalTheorySecMarks = bumsMarksPatternmatcher.group(5);
								String internalAssessmentSecMark = bumsMarksPatternmatcher.group(6);
								String electiveSecMark = bumsMarksPatternmatcher.group(7);
								String practicalSecMark = bumsMarksPatternmatcher.group(8);
								String vivaSecMark = bumsMarksPatternmatcher.group(9);
								String totalPracticalMaxMarks = bumsMarksPatternmatcher.group(10);
								String totalPracticalSecMarks = bumsMarksPatternmatcher.group(11);
								String grandTotalMaxMarks = bumsMarksPatternmatcher.group(12);
								String grandTotalSecMarks = bumsMarksPatternmatcher.group(13);
								status = bumsMarksPatternmatcher.group(14);
  
								System.out.println("-------------------------------------------------------");
								System.out.println("Subject: " + subject);
								System.out.println("Theory Paper 1: " + theoryPaper1);
								System.out.println("Theory Paper 2: " + theoryPaper2);
								System.out.println("Total Theory Max Marks: " + totalTheoryMaxMarks);
								System.out.println("Total Theory Sec Marks: " + totalTheorySecMarks);
								System.out.println("Internal Assessment Sec Mark: " + internalAssessmentSecMark);
								System.out.println("Elective Sec Mark: " + electiveSecMark);
								System.out.println("Practical Sec Mark: " + practicalSecMark);
								System.out.println("Viva Sec Mark: " + vivaSecMark);
								System.out.println("Total Practical Max Marks: " + totalPracticalMaxMarks);
								System.out.println("Total Practical Sec Marks: " + totalPracticalSecMarks);
								System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
								System.out.println("Grand Total Sec Mark: " + grandTotalSecMarks);
								System.out.println("Status: " + status);
								System.out.println("-------------------------------------------------------");

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {
										if (!theoryPaper1.equals("NA") || !theoryPaper1.equals("AB")
												|| !theoryPaper1.equals("NE") || !theoryPaper1.equals("NA")
												|| !theoryPaper1.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1 Sec. Marks is: "
															+ theoryPaper1);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + theoryPaper1);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (theoryPaper1.trim().equals("AB") || theoryPaper1.trim().equals("NE")
												|| theoryPaper1.trim().equals("NA")
												|| theoryPaper1.trim().equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1 Sec. Marks is: "
															+ theoryPaper1);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + theoryPaper1);

										} else {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1. Sec Marks is: "
															+ theoryPaper1);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + theoryPaper1);

										}

									}
									try {
										System.out.println("Theory Paper 2: " + theoryPaper2);

										System.out.println(!theoryPaper2.trim().equals("NA"));

										if ((!theoryPaper2.equals("NA")) || !theoryPaper2.equals("AB")
												|| !theoryPaper2.equals("NE") || !theoryPaper2.equals("NA")
												|| !theoryPaper2.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper2 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper2 Sec. Marks is: "
															+ theoryPaper2);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper2 Sec. Marks is: " + theoryPaper2);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (theoryPaper2.trim().equals("AB") || theoryPaper2.trim().equals("NE")
												|| theoryPaper2.trim().equals("NA") || theoryPaper2.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper2 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper2 Sec. Marks is: "
															+ theoryPaper2);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper2 Sec. Marks is: " + theoryPaper2);

										} else {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1. Sec Marks is: "
															+ theoryPaper2);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + theoryPaper2);

										}

									}

									try {

										if (!totalTheorySecMarks.equals("NA") || !totalTheorySecMarks.equals("AB")
												|| !totalTheorySecMarks.equals("NE")
												|| !totalTheorySecMarks.equals("NE (AT)")) {
											theoryInternalMaxMark = Double.parseDouble(totalTheoryMaxMarks);

											if (!theoryPaper2.trim().equals("NA")) {

												paper1Mark = Double.parseDouble(theoryPaper1)
														+ Double.parseDouble(theoryPaper2);
												System.out.println(paper1Mark);

											} else {
												paper1Mark = Double.parseDouble(theoryPaper1);
												System.out.println(paper1Mark);
											}

											verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);
											checkMarks(Regno, "Theory Internal Sec Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													totalTheorySecMarks, theoryInternalMaxMark, testCaseName);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (totalTheorySecMarks.equals("AB") || totalTheorySecMarks.equals("NE")
												|| totalTheorySecMarks.equals("NA")
												|| totalTheorySecMarks.equals("NE (AT)")) {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ totalTheorySecMarks);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ totalTheorySecMarks);

										}

										else {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);
											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ totalTheorySecMarks);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ totalTheorySecMarks);

										}

									}

									try {
										if (!internalAssessmentSecMark.equals("NA")
												|| !internalAssessmentSecMark.equals("AB")
												|| !internalAssessmentSecMark.equals("NE")
												|| !internalAssessmentSecMark.equals("NA")
												|| !internalAssessmentSecMark.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName.createNode(
													"internal Assessment Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " internal Assessment Sec. Marks is: "
															+ internalAssessmentSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " internal Assessment Sec. Marks is: "
													+ internalAssessmentSecMark);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (internalAssessmentSecMark.trim().equals("AB")
												|| internalAssessmentSecMark.trim().equals("NE")
												|| internalAssessmentSecMark.trim().equals("NA")
												|| internalAssessmentSecMark.trim().equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName.createNode(
													"internal Assessment Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " internal Assessment Sec. Marks is: "
															+ internalAssessmentSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " internal Assessment Sec. Marks is: "
													+ internalAssessmentSecMark);

										} else {

											ExtentTest testCaseScenario = testCaseName.createNode(
													"internal Assessment Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " internal Assessment Sec. Marks is: "
															+ internalAssessmentSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " internal Assessment Sec. Marks is: "
													+ internalAssessmentSecMark);

										}
									}

									try {
										if (!electiveSecMark.equals("NA") || !electiveSecMark.equals("AB")
												|| !electiveSecMark.equals("NE") || !electiveSecMark.equals("NA")
												|| !electiveSecMark.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("elective Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " elective Sec. Marks is: " + electiveSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " elective Sec. Marks is: " + electiveSecMark);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (electiveSecMark.trim().equals("AB") || electiveSecMark.trim().equals("NE")
												|| electiveSecMark.trim().equals("NA")
												|| electiveSecMark.trim().equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("elective Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " elective Sec. Marks is: " + electiveSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " elective Sec. Marks is: " + electiveSecMark);

										} else {

											ExtentTest testCaseScenario = testCaseName
													.createNode("elective Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " elective Sec. Marks is: " + electiveSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " elective Sec. Marks is: " + electiveSecMark);

										}
									}

									try {
										if (!practicalSecMark.equals("NA") || !practicalSecMark.equals("AB")
												|| !practicalSecMark.equals("NE") || !practicalSecMark.equals("NA")
												|| !practicalSecMark.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("practical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " practical Sec. Marks is: " + practicalSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " practical Sec. Marks is: " + practicalSecMark);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (practicalSecMark.trim().equals("AB") || practicalSecMark.trim().equals("NE")
												|| practicalSecMark.trim().equals("NA")
												|| practicalSecMark.trim().equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("practical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " practical Sec. Marks is: " + practicalSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " practical Sec. Marks is: " + practicalSecMark);

										} else {

											ExtentTest testCaseScenario = testCaseName
													.createNode("practical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " practical Sec. Marks is: " + practicalSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " practical Sec. Marks is: " + practicalSecMark);

										}
									}

									try {
										if (!vivaSecMark.equals("NA") || !vivaSecMark.equals("AB")
												|| !vivaSecMark.equals("NE") || !vivaSecMark.equals("NA")
												|| !vivaSecMark.equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("viva Sec. Marks Validation for the Subject " + subject
															+ " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Viva Sec. Marks is: "
															+ vivaSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Viva Sec. Marks is: " + vivaSecMark);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (vivaSecMark.trim().equals("AB") || vivaSecMark.trim().equals("NE")
												|| vivaSecMark.trim().equals("NA")
												|| vivaSecMark.trim().equals("NE (AT)")) {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Viva Sec. Marks Validation for the Subject " + subject
															+ " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " elective Sec. Marks is: " + vivaSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " elective Sec. Marks is: " + vivaSecMark);

										} else {

											ExtentTest testCaseScenario = testCaseName
													.createNode("Viva Sec. Marks Validation for the Subject " + subject
															+ " Test case has started running");
											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " elective Sec. Marks is: " + vivaSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " elective Sec. Marks is: " + vivaSecMark);

										}
									}
									try {

										if (!totalPracticalSecMarks.equals("NA") || !totalPracticalSecMarks.equals("AB")
												|| !totalPracticalSecMarks.equals("NE")
												|| !totalPracticalSecMarks.equals("NA")
												|| !totalPracticalSecMarks.equals("NE (AT)")) {
											praticalTotalMaxMark = Double.parseDouble(totalPracticalMaxMarks);
											praticalTotalSecMark = Double.parseDouble(totalPracticalSecMarks);
											verifyScore(praticalTotalSecMark, praticalTotalMaxMark, 0.50);
											// Use the value
											// Check pratical internal Sec. Marks
											checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													totalPracticalSecMarks, praticalTotalMaxMark, testCaseName);
										}
									} catch (NumberFormatException e) {

										if (totalPracticalSecMarks.equals("AB") || totalPracticalSecMarks.equals("NE")
												|| totalPracticalSecMarks.equals("NA")
												|| totalPracticalSecMarks.equals("NE (AT)")) {
											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;

											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO, "\n The Following Registration number "
													+ Regno + " for the Subject " + subject
													+ " Practical Univ Sec. Marks is:" + totalPracticalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + totalPracticalSecMarks);

											// Handle gracefully, e.g., assign default value or log an error

										} else {

											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;
											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO, "\n The Following Registration number "
													+ Regno + " for the Subject " + subject
													+ " Practical Univ Sec. Marks is:" + totalPracticalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + totalPracticalSecMarks);

										}

									}

									try {
										if (!grandTotalSecMarks.equals("NA") || !grandTotalSecMarks.equals("AB")
												|| !grandTotalSecMarks.equals("NE") || !grandTotalSecMarks.equals("NA")
												|| !grandTotalSecMarks.equals("NE (AT)")) {
											grandTotalMaxMark = Double.parseDouble(grandTotalMaxMarks);
											ExamTotalScore = Double.parseDouble(grandTotalSecMarks);

											verifyScore(ExamTotalScore, grandTotalMaxMark, 0.50);
											// Check Grand Total Sec. Marks (assumed max marks as 200)
											checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													grandTotalSecMarks, grandTotalMaxMark, testCaseName);
											System.out.println("==============");
											// securedMarks(Regno, examTotal, testCaseName);
											System.out.println("==============");
										} // Use the value
									} catch (NumberFormatException e) {
										if (grandTotalSecMarks.equals("AB") || grandTotalSecMarks.equals("NE")
												|| grandTotalSecMarks.equals("NA")
												|| grandTotalSecMarks.equals("NE (AT)")) {
											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Grand Total Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ grandTotalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Grand Total Sec. Marks is:" + grandTotalSecMarks);

										}

										else {

											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Grand Total Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ grandTotalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Grand Total Sec. Marks is:" + grandTotalSecMarks);
										}

									}

								}
									}}
							
							
							else if (courseSubjectNameRegexPatternMatcher.group().contains("MBBS")) {
						
							
							Pattern mbbsMarksPattern = Pattern.compile("(?<subject>[A-Z &]+)\\s+"
									+ "(?<marks>(?:(?:\\d+|---|NA|AB|NE|\\(AT\\))\\s*(?:\\(F\\))?\\s*)+)"
									+ "(?<result>Pass|Fail|Distinction|First Class|AP)");
							Matcher mbbsMarksPatternmatcher = mbbsMarksPattern.matcher(text);

	
							while (mbbsMarksPatternmatcher.find()) {
						
								try {

							
								System.out.println("Yes");
								subject = mbbsMarksPatternmatcher.group(1);
								String marks = mbbsMarksPatternmatcher.group(2);
								String[] splitMarks = marks.replace("\n", " ").trim().split("\\s+");

								System.out.println("Marks:" + marks);

//							    syso
//							    String[] parts = marks.trim().split("\\s+");

								String internalMaxMark = splitMarks[0];
								String internalSecMark = splitMarks[1];
								String paper1MaxMark = splitMarks[2];
								String paper1SecMark = splitMarks[3];
								String paper2MaxMark = splitMarks[4];
								String paper2SecMark = splitMarks[5];
								String totalPaper1PlusPaper2MaxMark = null;
								String totalPaper1PlusPaper2SecMark = null;
								String practicalMaxMark = null;

								String practicalSecMark = null;
								String grandTotalMaxMark = null;
								String grandTotalMinMarks = null;
								String grandTotalSecMarks = null;
								try {
									if (splitMarks[10].matches("\\([A-Z]+\\)")
											&& splitMarks[11].matches("\\([A-Z]+\\)")) {
										totalPaper1PlusPaper2MaxMark = splitMarks[6] + splitMarks[7];
										totalPaper1PlusPaper2SecMark = splitMarks[8];
										practicalMaxMark = splitMarks[9];
										practicalSecMark = splitMarks[9] + splitMarks[10];
										grandTotalMaxMarks = splitMarks[11];
										grandTotalMinMarks = splitMarks[12];
										grandTotalSecMarks = splitMarks[13] + splitMarks[14];

//									System.out.println("totalPaper1PlusPaper2MaxMark1: " +totalPaper1PlusPaper2MaxMark);
//									System.out.println("totalPaper1PlusPaper2SecMark1: " +totalPaper1PlusPaper2SecMark);
//									System.out.println("practicalMaxMark1: " +practicalMaxMark);
//								    System.out.println("Practical Secured1: " + practicalSecMark);
//									System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
//									System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
//									System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);
									} else if (splitMarks[10].matches("\\([A-Z]+\\)")
											&& splitMarks[14].matches("\\([A-Z]+\\)")) {

										totalPaper1PlusPaper2MaxMark = splitMarks[6];
										totalPaper1PlusPaper2SecMark = splitMarks[7];
										practicalMaxMark = splitMarks[8];
										practicalSecMark = splitMarks[9] + splitMarks[10];
										grandTotalMaxMarks = splitMarks[11];
										grandTotalMinMarks = splitMarks[12];
										grandTotalSecMarks = splitMarks[13] + splitMarks[14];
									}

									else if (splitMarks[13].matches("\\([A-Z]+\\)")) {

										totalPaper1PlusPaper2MaxMark = splitMarks[6];
										totalPaper1PlusPaper2SecMark = splitMarks[7];
										practicalMaxMark = splitMarks[8];
										practicalSecMark = splitMarks[9];
										grandTotalMaxMarks = splitMarks[10];
										grandTotalMinMarks = splitMarks[11];
										grandTotalSecMarks = splitMarks[12];

//									System.out.println("totalPaper1PlusPaper2MaxMark1: " +totalPaper1PlusPaper2MaxMark);
//									System.out.println("totalPaper1PlusPaper2SecMark1: " +totalPaper1PlusPaper2SecMark);
//									System.out.println("practicalMaxMark1: " +practicalMaxMark);
//								    System.out.println("Practical Secured1: " + practicalSecMark);
//									System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
//									System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
//									System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);
									} else if (splitMarks[8].matches("\\([A-Z]+\\)")
											&& splitMarks[11].matches("\\([A-Z]+\\)")) {

										totalPaper1PlusPaper2MaxMark = splitMarks[6];
										totalPaper1PlusPaper2SecMark = splitMarks[7];
										practicalMaxMark = splitMarks[9];
										practicalSecMark = splitMarks[10] + splitMarks[11];
										grandTotalMaxMarks = splitMarks[12];
										grandTotalMinMarks = splitMarks[13];
										grandTotalSecMarks = splitMarks[14];

//									System.out.println("totalPaper1PlusPaper2MaxMark1: " +totalPaper1PlusPaper2MaxMark);
//									System.out.println("totalPaper1PlusPaper2SecMark1: " +totalPaper1PlusPaper2SecMark);
//									System.out.println("practicalMaxMark1: " +practicalMaxMark);
//								    System.out.println("Practical Secured1: " + practicalSecMark);
//									System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
//									System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
//									System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);
									}

									else {
										totalPaper1PlusPaper2MaxMark = splitMarks[6];
										totalPaper1PlusPaper2SecMark = splitMarks[7];
										practicalMaxMark = splitMarks[9];
										practicalSecMark = splitMarks[10];
										grandTotalMaxMarks = splitMarks[11];
										grandTotalMinMarks = splitMarks[12];
										grandTotalSecMarks = splitMarks[13];

//									System.out.println("totalPaper1PlusPaper2MaxMark: " +totalPaper1PlusPaper2MaxMark);
//									System.out.println("totalPaper1PlusPaper2SecMark " +totalPaper1PlusPaper2SecMark);
//									System.out.println("practicalMaxMark: " +practicalMaxMark);
//								    System.out.println("Practical Secured: " + practicalSecMark);
//								    System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
//				
//									System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
//									System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);
									}
								} catch (Exception e) {
									totalPaper1PlusPaper2MaxMark = splitMarks[6];
									totalPaper1PlusPaper2SecMark = splitMarks[7];
									practicalMaxMark = splitMarks[8];
									practicalSecMark = splitMarks[9];
									grandTotalMaxMarks = splitMarks[10];
									grandTotalMinMarks = splitMarks[11];
									grandTotalSecMarks = splitMarks[12];

//									System.out.println("totalPaper1PlusPaper2MaxMark: " +totalPaper1PlusPaper2MaxMark);
//									System.out.println("totalPaper1PlusPaper2SecMark " +totalPaper1PlusPaper2SecMark);
//									System.out.println("practicalMaxMark: " +practicalMaxMark);
//								    System.out.println("Practical Secured: " + practicalSecMark);
//								    System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
//				
//									System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
//									System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);

								}

								// System.out.println("jsdfhdkfjd" +
								// mark1+mark2+mark3+mark4+mark5+mark6+mark7+mark8+mark9 );
								// System.out.println("praticalSecMark:" + praticalSecMark);

								// might be null
								// String grandTotalMinMarks =mbbsMarksPatternmatcher.group(4);
								// String grandTotalSecMarks
								// =mbbsMarksPatternmatcher.group(5).replaceAll("\\s+", "");

								status = mbbsMarksPatternmatcher.group(3).trim();
								System.out.println("==============");
								System.out.println("Subject:" + subject);
								System.out.println("Marks:" + marks);
								System.out.println("Internal Max: " + internalMaxMark);
								System.out.println("Internal Secured: " + internalSecMark);
								System.out.println("Paper 1 Max: " + paper1MaxMark);
								System.out.println("Paper 1 Secured: " + paper1SecMark);
								System.out.println("Paper 2 Max: " + paper2MaxMark);
								System.out.println("Paper 2 Secured: " + paper2SecMark);
								System.out.println("Total Paper 1+2 Max: " + totalPaper1PlusPaper2MaxMark);
								System.out.println("Total Paper 1+2 Secured: " + totalPaper1PlusPaper2SecMark);
								System.out.println("Practical Max: " + practicalMaxMark);
								System.out.println("Practical Secured: " + practicalSecMark);

								System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
								System.out.println("Grand Total Min Mark: " + grandTotalMinMarks);
								System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);
								System.out.println("Status: " + status);
								System.out.println("==============");

								paper1Mark = 0.0;
								paper2Mark = 0.0;
								paper3Mark = 0.0;
								praticalTotalSecMark = 0.0;
								ExamTotalScore = 0.0;
								Paper1 = 0.0;
								Paper2 = 0.0;
								Paper3 = 0.0;
								PraticalExamTotal = 0.0;
								double paper1SecMarks =0.0;
								double paper2SecMarks =0.0;

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {

										if (!internalSecMark.equals("NA") || !internalSecMark.equals("AB")
												|| !internalSecMark.equals("NE")
												|| !internalSecMark.equals("NE (AT)")) {
											theoryInternalMaxMark = Double.parseDouble(internalMaxMark);
											paper1Mark = Double.parseDouble(internalSecMark);

											verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);

											checkMarks(Regno, "Theory Internal Sec Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal, internalSecMark,
													theoryInternalMaxMark, testCaseName);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (internalSecMark.equals("AB") || internalSecMark.equals("NE")
												|| internalSecMark.equals("NA") || internalSecMark.equals("NE (AT)")) {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ internalSecMark);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ internalSecMark);

										}

										else {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);
											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ internalSecMark);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ internalSecMark);

										}

									}

									try {
										if (!paper1SecMark.equals("NA") || !paper1SecMark.equals("AB")
												|| !paper1SecMark.equals("NE") || !paper1SecMark.equals("NA")
												|| !paper1SecMark.equals("NE (AT)")) {
											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
										paper1SecMarks = Double.parseDouble(paper1SecMark);

											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1 Sec. Marks is: "
															+ paper1SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + paper1SecMark);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (paper1SecMark.equals("AB") || paper1SecMark.equals("NE")
												|| paper1SecMark.equals("NA") || paper1SecMark.equals("NE (AT)")) {
											paper1SecMarks = 0.0;

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1 Sec. Marks is: "
															+ paper1SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + paper1SecMark);

										} else {

											paper1SecMarks = 0.0;

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper1 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper1 Sec. Marks is: "
															+ paper1SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper1 Sec. Marks is: " + paper1SecMark);

										}
									}
									try {
										if (!paper2SecMark.equals("NA") || !paper2SecMark.equals("AB")
												|| !paper2SecMark.equals("NE") || !paper2SecMark.equals("NA")
												|| !paper2SecMark.equals("NE (AT)")) {

											paper2SecMarks = Double.parseDouble(paper2SecMark);
											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper2 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper2 Sec. Marks is: "
															+ paper2SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper2 Sec. Marks is: " + paper2SecMark);
										}
				
									} catch (NumberFormatException e) {

										if (paper2SecMark.equals("AB") || paper2SecMark.equals("NE")
												|| paper2SecMark.equals("NA") || paper2SecMark.equals("NE (AT)")) {
											paper2SecMarks = 0.0;

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper2 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper2 Sec. Marks is: "
															+ paper2SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper2 Sec. Marks is: " + paper2SecMark);

										} else {

											paper2SecMarks = 0.0;

											ExtentTest testCaseScenario = testCaseName
													.createNode("Paper2 Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " Paper2. Sec Marks is: "
															+ paper2SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Paper2 Sec. Marks is: " + paper2SecMark);

										}

									}

									try {
										if (!totalPaper1PlusPaper2SecMark.equals("NA")
												|| totalPaper1PlusPaper2SecMark.equals("AB")
												|| totalPaper1PlusPaper2SecMark.equals("NE")
												|| totalPaper1PlusPaper2SecMark.equals("NA")
												|| totalPaper1PlusPaper2SecMark.equals("NE (AT)")) {

											theoryMaxMark = Double.parseDouble(totalPaper1PlusPaper2MaxMark);
											paper2Mark = Double.parseDouble(totalPaper1PlusPaper2SecMark);

											double totalSecMarks = paper1SecMarks + paper2SecMarks;

											System.out.println(totalSecMarks);

											if (String.valueOf(totalSecMarks).contains(totalPaper1PlusPaper2SecMark)) {

												ExtentTest testCaseScenario = testCaseName.createNode(
														"Total Paper1 Plus Paper2 Sec Mark Sec. Marks Validation for the Subject "
																+ subject + " Test case has started running");

												testCaseScenario.log(Status.PASS,
														"\n The Following Registration number " + Regno
																+ " for the Subject " + subject
																+ " TTotal Paper1 Plus Paper2 Sec Mark Sec. Marks is: "
																+ totalSecMarks);

												System.out.println("\nThe Following Registration number " + Regno
														+ " Total Paper1 Plus Paper2 Sec Mark is: " + totalSecMarks);

											} else {
												ExtentTest testCaseScenario = testCaseName.createNode(
														"Total Paper1 Plus Paper2 Sec Mark Sec. Marks Validation for the Subject "
																+ subject + " Test case has started running");

												testCaseScenario.log(Status.FAIL,
														"\n The Following Registration number " + Regno
																+ " for the Subject " + subject
																+ " TTotal Paper1 Plus Paper2 Sec Mark Sec. Marks is: "
																+ totalSecMarks);

												System.out.println("\nThe Following Registration number " + Regno
														+ " Total Paper1 Plus Paper2 Sec Mark is: " + totalSecMarks);

											}

											verifyScore(paper2Mark, theoryMaxMark, 0.40);

											// Check Theory (Univ) Sec. Marks
											checkMarks(Regno, "Theory (Univ) Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													totalPaper1PlusPaper2SecMark, theoryMaxMark, testCaseName);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (totalPaper1PlusPaper2SecMark.equals("AB")
												|| totalPaper1PlusPaper2SecMark.equals("NE")
												|| totalPaper1PlusPaper2SecMark.equals("NA")
												|| totalPaper1PlusPaper2SecMark.equals("NE (AT)")) {
											paper2Mark = 0.0;
											Paper2 = 0.0;
											System.out.println(paper2Mark);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Total Paper1 Plus Paper2 Sec Mark Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " TTotal Paper1 Plus Paper2 Sec Mark Sec. Marks is: "
															+ totalPaper1PlusPaper2SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Total Paper1 Plus Paper2 Sec Mark is: "
													+ totalPaper1PlusPaper2SecMark);

										} else {
											paper2Mark = 0.0;
											Paper2 = 0.0;
											System.out.println(paper2Mark);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Total Paper1 Plus Paper2 Sec Mark Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Total Paper1 Plus Paper2 Sec Mark is: "
															+ totalPaper1PlusPaper2SecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Total Paper1 Plus Paper2 Sec Mark is: "
													+ totalPaper1PlusPaper2SecMark);

										}

									}

									try {

										if (!practicalSecMark.equals("NA") || !practicalSecMark.equals("AB")
												|| !practicalSecMark.equals("NE") || !practicalSecMark.equals("NA")
												|| !practicalSecMark.equals("NE (AT)")) {
											praticalTotalMaxMark = Double.parseDouble(practicalMaxMark);
											praticalTotalSecMark = Double.parseDouble(practicalSecMark);
											verifyScore(praticalTotalSecMark, praticalTotalMaxMark, 0.40);
											// Use the value
											// Check pratical internal Sec. Marks
											checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													practicalSecMark, praticalTotalMaxMark, testCaseName);
										}
									} catch (NumberFormatException e) {

										if (practicalSecMark.equals("AB") || practicalSecMark.equals("NE")
												|| practicalSecMark.equals("NA")
												|| practicalSecMark.equals("NE (AT)")) {
											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;

											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Univ Sec. Marks is:" + practicalSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + practicalSecMark);

										} else {

											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;
											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Univ Sec. Marks is:" + practicalSecMark);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + practicalSecMark);

										}

									}

									try {
										if (!grandTotalSecMarks.equals("NA") || !grandTotalSecMarks.equals("AB")
												|| !grandTotalSecMarks.equals("NE") || !grandTotalSecMarks.equals("NA")
												|| !grandTotalSecMarks.equals("NE (AT)")) {
											grandTotalMaxMark = Double.parseDouble(grandTotalMaxMarks);
											ExamTotalScore = Double.parseDouble(grandTotalSecMarks);
											Paper1 = 0.0;
											paper1Mark = 0.0;
											paper1MinMark = 0.0;
											verifyScore(ExamTotalScore, grandTotalMaxMark, 0.50);
											// Check Grand Total Sec. Marks (assumed max marks as 200)
											checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													grandTotalSecMarks, grandTotalMaxMark, testCaseName);
											System.out.println("==============");
											// securedMarks(Regno, examTotal, testCaseName);
											System.out.println("==============");
										} // Use the value
									} catch (NumberFormatException e) {
										if (grandTotalSecMarks.equals("AB") || grandTotalSecMarks.equals("NE")
												|| grandTotalSecMarks.equals("NA")
												|| grandTotalSecMarks.equals("NE (AT)")) {
											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Theory plus pratical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ grandTotalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Theory plus Pratical Sec. Marks is:" + grandTotalSecMarks);

										}

										else {

											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Theory plus pratical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ grandTotalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Theory plus Pratical Sec. Marks is:" + grandTotalSecMarks);
										}

									}

								}
							}

				 // Extract and print each row

					// Extract and print each row


//							
//							if (matcher2.group().contains("MBBS")) {
//								System.out.println("Yes");
//							    String subject = mbbsMarksPatternmatcher.group(1);
//							    String marks = mbbsMarksPatternmatcher.group(2);
//							    
//							    String[] parts = marks.trim().split("\\s+");
//
//							    String internalMaxMark = parts[0];
//							    String internalSecMark = parts[1];
//							    String paper1MaxMark = parts[2];
//							    String paper1SecMark = parts[3];
//							    String paper2MaxMark = parts[4];
//							    String paper2SecMark = parts[5];
//							    String totalPaper1PlusPaper2MaxMark = parts[6];
//							    String totalPaper1PlusPaper2SecMark= parts[7];
//							    String praticalMaxMark = parts[8];
//							    String praticalSecMark = parts[9];
//							
//				//			    System.out.println("jsdfhdkfjd" + mark1+mark2+mark3+mark4+mark5+mark6+mark7+mark8+mark9 );
//							
//							    
//							    String grandTotalMaxMark = mbbsMarksPatternmatcher.group(3); // might be null
//							    String grandTotalMinMark =mbbsMarksPatternmatcher.group(4);
//							    String grandTotalSecMark =mbbsMarksPatternmatcher.group(5);
//							    String status = mbbsMarksPatternmatcher.group(6); 
//							    
//							System.out.println("Subject:" + subject);
//							System.out.println("Marks:" + marks);
//							System.out.println("Internal Max: " + internalMaxMark);
//							System.out.println("Internal Secured: " + internalSecMark);
//							System.out.println("Paper 1 Max: " + paper1MaxMark);
//							System.out.println("Paper 1 Secured: " + paper1SecMark);
//							System.out.println("Paper 2 Max: " + paper2MaxMark);
//							System.out.println("Paper 2 Secured: " + paper2SecMark);
//							System.out.println("Total Paper 1+2 Max: " + totalPaper1PlusPaper2MaxMark);
//							System.out.println("Total Paper 1+2 Secured: " + totalPaper1PlusPaper2SecMark);
//							System.out.println("Practical Max: " + praticalMaxMark);
//							System.out.println("Practical Secured: " + praticalSecMark);
//
//							System.out.println("Grand Total Max Mark: " + grandTotalMaxMark);
//							System.out.println("Grand Total Min Mark: " + grandTotalMinMark);
//							System.out.println("Grand Total Secured Mark: " + grandTotalSecMark);
//							System.out.println("Status: " + status);
//				
//							}	
						
							if (courseSubjectNameRegexPatternMatcher.group().contains("B.Sc")) {
								Pattern marksPattern = Pattern.compile(
									    "^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
									    + "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*"                           // Subject Name
									    + "(?:\\(([^)]+)\\))?\\s*"                                                            // Specialization (optional)
									    
									    // Theory Internal Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Internal Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Practical Internal Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Internal Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    
									    // Theory + Practical Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory + Practical Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Status
									    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
									    Pattern.MULTILINE
									);

								Matcher matcher = marksPattern.matcher(text);

								while (matcher.find()) {

									testCaseScenario1.log(Status.INFO, "Pattern process sucessful");

									try {
								System.out.println("==============");
								subject = (matcher.group(2) == null) ? matcher.group(1).replaceAll("\\s+", " ").trim()
										: (matcher.group(1) + " " + matcher.group(2)).replaceAll("\\s+", " ").trim();

								String theoryInternalMaxMarks = matcher.group(3);
								String	theoryInternalSecMarks = matcher.group(4);
								String theoryUnivMaxMarks = matcher.group(5);
								String theoryUnivSecMarks = matcher.group(6);
								String	practicalInternalMaxMarks = matcher.group(7);
								String	practicalInternalSecMarks = matcher.group(8);
								String	practicalUnivMaxMarks = matcher.group(9);
								String	practicalUnivSecMarks = matcher.group(10);
								String	theoryPracticalMaxMarks = matcher.group(11);
								String	theoryPracticalSecMarks = matcher.group(12);
								status = matcher.group(13);

								System.out.println("subject: " + subject);

								// Printing the required format
								System.out.println("Theory Internal Max. Marks: " + theoryInternalMaxMarks);

								System.out.println("Theory Internal Sec. Marks: " + theoryInternalSecMarks);

								System.out.println("Theory (Univ) Max. Marks: " + theoryUnivMaxMarks);

								System.out.println("Theory (Univ) Sec. Marks: " + theoryUnivSecMarks);

								System.out.println("Practical Internal Max. Marks: " + practicalInternalMaxMarks);

								System.out.println("Practical Internal Sec. Marks: " + practicalInternalSecMarks);

								System.out.println("Practical (Univ) Max. Marks: " + practicalUnivMaxMarks);
								System.out.println("Practical (Univ) Sec. Marks: " + practicalUnivSecMarks);
								System.out.println("Theory + Practical Max. Marks: " + theoryPracticalMaxMarks);
								System.out.println("Theory + Practical Sec. Marks: " + theoryPracticalSecMarks);
								System.out.println("Status: " + status);

								paper1Mark = 0.0;
								paper2Mark = 0.0;
								paper3Mark = 0.0;
								praticalTotalSecMark = 0.0;
								ExamTotalScore = 0.0;
								Paper1 = 0.0;
								Paper2 = 0.0;
								Paper3 = 0.0;
								PraticalExamTotal = 0.0;

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									if (matcher1.group().trim().equals("B.Sc. MLT")) {

										try {
											validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.35, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
									}
										
									try {
											validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.40, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
									}
									try {
										validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
												  theoryExam,praticalExam, subject,
										        examTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.35, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
								}	
									try {
										validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
												  theoryExam,praticalExam, subject,
										        examTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
								}
									try {
										validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
												  theoryExam,praticalExam, subject,
										        examTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
								}

									} //if

									else if (matcher1.group().trim().equals("Post Basic B.Sc Nursing")||matcher1.group().trim().equals("Post Basic B.Sc. Nursing")) {
										try {
											validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
									}
										
										try {
											validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
									}
										try {
											validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
									}
										try {
											validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
									}
										try {
											validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
									}
									}// else if 	
									
									else {
										try {
											validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.00, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
									}
										
									try {
											validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
													  theoryExam,praticalExam, subject,
											        examTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.00, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
									}
									try {
										validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.00, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
								}	
									try {
										validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
												 theoryExam,praticalExam, subject,
										        examTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
								}
									try {
										validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
								}
	
									} //else
									
								
								}// if
								
								
								}//try
									catch(Exception e) {
									
									}
								}//while
							}

//For MSC course		

							else if (matcher1.group().contains("M.Sc.")) {
								Pattern marksPattern = Pattern.compile(
									    "^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
									    + "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*"                           // Subject Name
									    + "(?:\\(([^)]+)\\))?\\s*"                                                            // Specialization (optional)
									    
									    // Theory Internal Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Internal Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Practical Internal Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Internal Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    
									    // Theory + Practical Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory + Practical Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Status
									    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
									    Pattern.MULTILINE
									);

								Matcher matcher = marksPattern.matcher(text);

								while (matcher.find()) {

									testCaseScenario1.log(Status.INFO, "Pattern process sucessful");

									try {

								System.out.println("==============");
								subject = (matcher.group(2) == null) ? matcher.group(1).replaceAll("\\s+", " ").trim()
										: (matcher.group(1) + " " + matcher.group(2)).replaceAll("\\s+", " ").trim();

								String theoryInternalMaxMarks = matcher.group(3);
								String theoryUnivMaxMarks = matcher.group(4);
								String theoryTotalMaxMarks = matcher.group(5);
								String theoryTotalSecMarks = matcher.group(6);
								String 	practicalInternalMaxMarks = matcher.group(7);
								String practicalUnivMaxMarks = matcher.group(8);
								String	practicalTotalMaxMarks = matcher.group(9);
								String	practicalTotalSecMarks = matcher.group(10);
								String	theoryPracticalMaxMarks = matcher.group(11);
								String	theoryPracticalSecMarks = matcher.group(12);
								status = matcher.group(13);

								System.out.println("subject: " + matcher.group(1).trim());

								// Printing the required format
								System.out.println("Theory Internal Max. Marks: " + theoryInternalMaxMarks);

								System.out.println("Theory (Univ) Max. Marks: " + theoryUnivMaxMarks);

								System.out.println("Theory Total Max. Marks: " + theoryTotalMaxMarks);

								System.out.println("Theory Total Sec. Marks: " + theoryTotalSecMarks);

								System.out.println("Practical Internal Max. Marks: " + practicalInternalMaxMarks);

								System.out.println("Practical (Univ) Max. Marks: " + practicalUnivMaxMarks);

								System.out.println("Practical Total Max. Marks: " + practicalTotalMaxMarks);

								System.out.println("Practical Total Sec. Marks: " + practicalTotalSecMarks);
								System.out.println("Theory + Practical Max. Marks: " + theoryPracticalMaxMarks);
								System.out.println("Theory + Practical Sec. Marks: " + theoryPracticalSecMarks);
								System.out.println("Status: " + status);

								paper1Mark = 0.0;
								paper2Mark = 0.0;
								paper3Mark = 0.0;
								praticalTotalSecMark = 0.0;
								ExamTotalScore = 0.0;
								Paper1 = 0.0;
								Paper2 = 0.0;
								Paper3 = 0.0;
								PraticalExamTotal = 0.0;

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {

										if (!theoryTotalSecMarks.equals("NA") || !theoryTotalSecMarks.equals("AB")
												|| !theoryTotalSecMarks.equals("NE")
												|| !theoryTotalSecMarks.equals("NE (AT)")
												||!theoryTotalSecMarks.trim().equals("---")) {
											theoryInternalMaxMark = Double.parseDouble(theoryTotalMaxMarks);
											paper1Mark = Double.parseDouble(theoryTotalSecMarks);
											verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);

										}
										checkMarks(Regno, "Theory Total Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal, theoryTotalSecMarks,
												theoryInternalMaxMark, testCaseName);
										// Use the value
									} catch (NumberFormatException e) {

										if (theoryTotalSecMarks.equals("AB") || theoryTotalSecMarks.equals("NE")
												|| theoryTotalSecMarks.equals("NA")
												|| theoryTotalSecMarks.equals("NE (AT)")
												||theoryTotalSecMarks.trim().equals("---")) {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory Total sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.PASS,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory Total sec marks is: "
															+ theoryTotalSecMarks);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory Total sec marks is: "
													+ theoryTotalSecMarks);

										}
									}

									try {

										if(!practicalTotalSecMarks.equals("AB") || !practicalTotalSecMarks.equals("NE")
												|| !practicalTotalSecMarks.equals("NA")
												|| !practicalTotalSecMarks.equals("NE (AT)")
												||!practicalTotalSecMarks.trim().equals("---")) {
											praticalTotalMaxMark = Double.parseDouble(practicalTotalMaxMarks);
											praticalTotalSecMark = Double.parseDouble(practicalTotalSecMarks);
											verifyScore(praticalTotalSecMark, praticalTotalMaxMark, 0.50);
										} // Use the value
										// Check pratical internal Sec. Marks
										checkMarks(Regno, "Pratical Total Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal,
												practicalTotalSecMarks, praticalTotalMaxMark, testCaseName);

									} catch (NumberFormatException e) {

										if (practicalTotalSecMarks.equals("AB") || practicalTotalSecMarks.equals("NE")
												|| practicalTotalSecMarks.equals("NA")
												|| practicalTotalSecMarks.equals("NE (AT)")
												||practicalTotalSecMarks.trim().equals("---")) {
											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;
											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Total Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.PASS, "\n The Following Registration number "
													+ Regno + " for the Subject " + subject
													+ " Practical Total Sec. Marks is:" + practicalTotalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Total Sec. Marks is:" + practicalTotalSecMarks);

											// Handle gracefully, e.g., assign default value or log an error

										}
									}

									try {
										if (!theoryPracticalSecMarks.equals("AB") || !theoryPracticalSecMarks.equals("NE")
												|| !theoryPracticalSecMarks.equals("NA")
												|| !theoryPracticalSecMarks.equals("---")
												|| !theoryPracticalSecMarks.equals("NE (AT)")) {
											grandTotalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
											ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
										}
										// Check Grand Total Sec. Marks (assumed max marks as 200)
										checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal,
												theoryPracticalSecMarks, grandTotalMaxMark, testCaseName);
										System.out.println("==============");
										// securedMarks(Regno, examTotal, testCaseName);
										System.out.println("==============");

										// Use the value
									} catch (NumberFormatException e) {
										if (theoryPracticalSecMarks.equals("AB") || theoryPracticalSecMarks.equals("NE")
												|| theoryPracticalSecMarks.equals("NA")
												|| theoryPracticalSecMarks.equals("---")
												|| theoryPracticalSecMarks.equals("NE (AT)")) {
											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Theory plus pratical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.PASS,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Theory plus Pratical Sec. Marks is: "
															+ theoryPracticalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);

										}
									}

									// Check Theory Internal Sec. Marks

									// Stop after printing one subject
								}

							}
							
								catch(Exception e) {
									
							}
								}//else if
							}//while

							else if (matcher1.group().contains("BPT")) {

								Pattern bptPattern = Pattern.compile("^(.*?)\\s*-*\\s*" + // Subject Name (Group 1)

								// Subject Name (Group 1) - Captures multiple lines
										"(\\d{1,3})\\s*-*\\s*" + // Theory + Internal Max Marks (Group 2)
										"(NA|AB|AP|---|NR|\\(F\\)|NE\\(AT\\)|\\d{1,2}(?:\\(F\\))?)\\s+" + // Theory +
																											// Internal
																											// Secured
																											// Marks
																											// (Group 3)
										"(NA|AB|AP|---|NR|\\(F\\)|NE\\(AT\\)|\\d{1,2})\\s+" + // Practical Internal
																								// Marks (Group 4)
										"(NA|AB|AP|---|NR|\\(F\\)|NE\\(AT\\)|\\d{1,2})\\s+" + // Practical Marks (Group
																								// 5)
										"(NA|AB|AP|---|NR|\\(F\\)|NE\\(AT\\)|\\d{1,2})\\s+" + // Practical Viva Marks
																								// (Group 6)
										"(\\d{1,3})\\s*-*\\s*" + // Practical + Viva Max Marks (Group 7)
										"(\\d{1,3}(?:\\(F\\))?)\\s+" + // Practical + Viva Secured Marks (Group 8)
										"(\\d{1,3})\\s*-*\\s*" + // Grand Total Max Marks (Group 9)
										"(\\d{1,3}(?:\\(F\\))?)\\s+" + // Grand Total Secured Marks (Group 10)
										"(Pass|Fail|AB|AP|NA|NR|\\(F\\)|NE\\(AT\\))$", // Result Status (Group 11)
										Pattern.MULTILINE | Pattern.DOTALL);

								Matcher bptMatcher = bptPattern.matcher(text);

								System.out.println("jhdsgfjhdfgs");

								if (bptMatcher.find()) {

									System.out.println("==============");
									System.out.println(bptMatcher.group(0));

									System.out.println("Subject: " + bptMatcher.group(1));
									System.out.println("Theory + Internal Max Marks: " + bptMatcher.group(2));
									System.out.println("Theory + Internal Secured Marks: " + bptMatcher.group(3));
									System.out.println("Practical Internal Marks: " + bptMatcher.group(4));
									System.out.println("Practical Marks: " + bptMatcher.group(5));
									System.out.println("Practical Viva Marks: " + bptMatcher.group(6));
									System.out.println("Practical + Viva Max Marks: " + bptMatcher.group(7));
									System.out.println("Practical + Viva Secured Marks: " +bptMatcher.group(8));
									System.out.println("Grand Total Max Marks: " + bptMatcher.group(9));
									System.out.println("Grand Total Secured Marks: " + bptMatcher.group(10));
									System.out.println("Result Status: " + bptMatcher.group(11));
									System.out.println("----------------------------");

								}

							}

							else if (matcher1.group().equals("B.Sc. MLT")) {

								Pattern marksPattern = Pattern.compile(
									    "^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
									    + "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*"                           // Subject Name
									    + "(?:\\(([^)]+)\\))?\\s*"                                                            // Specialization (optional)
									    
									    // Theory Internal Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Internal Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory Univ Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Practical Internal Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Internal Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Max (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Practical Univ Sec (optional)
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    
									    // Theory + Practical Max
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									    // Theory + Practical Sec
									    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"
									    
									    // Status
									    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
									    Pattern.MULTILINE
									);

								Matcher matcher = marksPattern.matcher(text);

								while (matcher.find()) {

									testCaseScenario1.log(Status.INFO, "Pattern process sucessful");

									try {

								
								System.out.println("==============");
								subject = (matcher.group(2) == null) ? matcher.group(1).replaceAll("\\s+", " ").trim()
										: (matcher.group(1) + " " + matcher.group(2)).replaceAll("\\s+", " ").trim();

								String	theoryInternalMaxMarks = matcher.group(3);
								String		theoryInternalSecMarks = matcher.group(4);
								String		theoryUnivMaxMarks = matcher.group(5);
								String		theoryUnivSecMarks = matcher.group(6);
								String		practicalInternalMaxMarks = matcher.group(7);
								String		practicalInternalSecMarks = matcher.group(8);
								String		practicalUnivMaxMarks = matcher.group(9);
								String		practicalUnivSecMarks = matcher.group(10);
								String		theoryPracticalMaxMarks = matcher.group(11);
								String		theoryPracticalSecMarks = matcher.group(12);
								status = matcher.group(13);

								System.out.println("subject: " + subject);

								// Printing the required format
								System.out.println("Theory Internal Max. Marks: " + theoryInternalMaxMarks);

								System.out.println("Theory Internal Sec. Marks: " + theoryInternalSecMarks);

								System.out.println("Theory (Univ) Max. Marks: " + theoryUnivMaxMarks);

								System.out.println("Theory (Univ) Sec. Marks: " + theoryUnivSecMarks);

								System.out.println("Practical Internal Max. Marks: " + practicalInternalMaxMarks);

								System.out.println("Practical Internal Sec. Marks: " + practicalInternalSecMarks);

								System.out.println("Practical (Univ) Max. Marks: " + practicalUnivMaxMarks);
								System.out.println("Practical (Univ) Sec. Marks: " + practicalUnivSecMarks);
								System.out.println("Theory + Practical Max. Marks: " + theoryPracticalMaxMarks);
								System.out.println("Theory + Practical Sec. Marks: " + theoryPracticalSecMarks);
								System.out.println("Status: " + status);

								paper1Mark = 0.0;
								paper2Mark = 0.0;
								paper3Mark = 0.0;
								praticalTotalSecMark = 0.0;
								ExamTotalScore = 0.0;
								Paper1 = 0.0;
								Paper2 = 0.0;
								Paper3 = 0.0;
								PraticalExamTotal = 0.0;

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {

										if (!theoryInternalSecMarks.equals("NA") || !practicalUnivSecMarks.equals("AB")
												|| !practicalUnivSecMarks.equals("NE")
												|| !practicalUnivSecMarks.equals("NE (AT)")) {
											theoryInternalMaxMark = Double.parseDouble(theoryInternalMaxMarks);
											paper1Mark = Double.parseDouble(theoryInternalSecMarks);

											verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);

											checkMarks(Regno, "Theory Internal Sec Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													theoryInternalSecMarks, theoryInternalMaxMark, testCaseName);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (theoryInternalSecMarks.equals("AB") || theoryInternalSecMarks.equals("NE")
												|| theoryInternalSecMarks.equals("NA")
												|| theoryInternalSecMarks.equals("NE (AT)")) {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ theoryInternalSecMarks);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ theoryInternalSecMarks);

										}

										else {
											paper1Mark = 0.0;
											Paper1 = 0.0;
											System.out.println(paper1Mark);
											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory internal sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"The following Register number " + Regno + " for the subject "
															+ subject + " theory internal sec marks is: "
															+ theoryInternalSecMarks);

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " theory internal sec marks is: "
													+ theoryInternalSecMarks);

										}

									}

									try {
										if (!theoryUnivSecMarks.equals("NA")) {
											theoryMaxMark = Double.parseDouble(theoryUnivMaxMarks);
											paper2Mark = Double.parseDouble(theoryUnivSecMarks);

											verifyScore(paper2Mark, theoryMaxMark, 0.50);

											// Check Theory (Univ) Sec. Marks
											checkMarks(Regno, "Theory (Univ) Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													theoryUnivSecMarks, theoryMaxMark, testCaseName);
										}
										// Use the value
									} catch (NumberFormatException e) {

										if (theoryUnivSecMarks.equals("AB") || theoryUnivSecMarks.equals("NE")
												|| theoryUnivSecMarks.equals("NA")
												|| theoryUnivSecMarks.equals("NE (AT)")) {
											paper2Mark = 0.0;
											Paper2 = 0.0;
											System.out.println(paper2Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);

										} else {
											paper2Mark = 0.0;
											Paper2 = 0.0;
											System.out.println(paper2Mark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Theory Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);

										}

									}
									// Handle gracefully, e.g., assign default value or log an error

									try {
										if (!practicalInternalSecMarks.equals("NA")) {

											praticalMaxMark = Double.parseDouble(practicalInternalMaxMarks);
											paper3Mark = Double.parseDouble(practicalInternalSecMarks);
											Paper3 = Double.parseDouble(practicalInternalSecMarks);
											verifyScore(paper3Mark, praticalMaxMark, 0.50);
//									System.out.println("paper 3 mark in check marks :" + paper3Mark);
											//
//									System.out.println("paper 3 mark in check marks :" + Paper3);
											//
//									System.out.println(Paper3);

											// Use the value
											// Check pratical internal Sec. Marks
											checkMarks(Regno, "Pratical Internal Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													practicalInternalSecMarks, praticalMaxMark, testCaseName);
										}
									} catch (NumberFormatException e) {

										// if the any of the marks is this data need to convert to 0 or else it will
										// thow error

										if (practicalInternalSecMarks.equals("AB")
												|| practicalInternalSecMarks.equals("NE")
												|| practicalInternalSecMarks.equals("NA")
												|| practicalInternalSecMarks.equals("NE (AT)")) {
											paper3Mark = 0.0;
											Paper3 = 0.0;

											System.out.println(paper3Mark);
											ExtentTest testCaseScenario = testCaseName.createNode(
													"Pratical internal Sec. Marks Validation for the Subject " + subject
															+ " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Internal Sec. Marks is: "
															+ practicalInternalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Internal Sec. Marks is:" + practicalInternalSecMarks);

										} else {
											paper3Mark = 0.0;
											Paper3 = 0.0;

											System.out.println(paper3Mark);
											ExtentTest testCaseScenario = testCaseName.createNode(
													"Pratical internal Sec. Marks Validation for the Subject " + subject
															+ " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Internal Sec. Marks is: "
															+ practicalInternalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Internal Sec. Marks is:" + practicalInternalSecMarks);

										}
									}

									try {

										if (!practicalUnivSecMarks.equals("NA")) {
											praticalTotalMaxMark = Double.parseDouble(practicalUnivMaxMarks);
											praticalTotalSecMark = Double.parseDouble(practicalUnivSecMarks);
											verifyScore(praticalTotalSecMark, praticalTotalMaxMark, 0.50);
											// Use the value
											// Check pratical internal Sec. Marks
											checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
													praticalExam, theoryExam, subjectToFind, examTotal,
													practicalUnivSecMarks, praticalTotalMaxMark, testCaseName);
										}
									} catch (NumberFormatException e) {

										if (practicalUnivSecMarks.equals("AB") || practicalUnivSecMarks.equals("NE")
												|| practicalUnivSecMarks.equals("NA")
												|| practicalUnivSecMarks.equals("NE (AT)")) {
											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;
											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);

											// Handle gracefully, e.g., assign default value or log an error

										} else {

											praticalTotalSecMark = 0.0;
											PraticalExamTotal = 0.0;
											System.out.println(praticalTotalSecMark);

											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);

										}

									}

									try {
										if (!theoryPracticalSecMarks.equals("NA")) {
											grandTotalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
											ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
										}
										// Check Grand Total Sec. Marks (assumed max marks as 200)
										checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal,
												theoryPracticalSecMarks, grandTotalMaxMark, testCaseName);
										System.out.println("==============");
										// securedMarks(Regno, examTotal, testCaseName);
										System.out.println("==============");
										// Use the value
									} catch (NumberFormatException e) {
										if (theoryPracticalSecMarks.equals("AB") || theoryPracticalSecMarks.equals("NE")
												|| theoryPracticalSecMarks.equals("NA")
												|| theoryPracticalSecMarks.equals("NE (AT)")) {
											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Theory plus pratical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ theoryPracticalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);

										}

										else {

											ExamTotalScore = 0.0;
											System.out.println(ExamTotalScore);

											ExtentTest testCaseScenario = testCaseName.createNode(
													"Theory plus pratical Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.INFO,
													"\n The Following Registration number " + Regno
															+ " for the Subject " + subject + " marks is: "
															+ theoryPracticalSecMarks);

											System.out.println("\nThe Following Registration number " + Regno
													+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										}

									}

								}

							}//try
								
								
								catch(Exception e){
									
									
								}
							}
								// else if
							
					
					}
							
							else if(matcher1.group().contains("BDS")) {
							     Pattern marksPattern =

							    		 Pattern.compile(
							    				    "(?m)^\\s*(?!Theory|Result|Subject|Paper)([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 

							        			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							 
													+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        				
													+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        				
						        			    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
							        		 
							        			    + "(AP|Pass|Fail)",
							        			    Pattern.DOTALL | Pattern.MULTILINE
							        			);
							     
							     
							     
							 	Matcher matcher = marksPattern.matcher(text);

							 	while (matcher.find()) {
							 		
							 		
							 		subject =matcher.group(1);
								
								System.out.println("bdsSubject:" +subject);

//								
								String bdsTheoryThPlusIntVivaMaxMark =matcher.group(2);
								
								String bdsTheoryInt =matcher.group(3);
								String bdsTheoryTh = matcher.group(4);
								String bdsTheoryThViva = matcher.group(5);
								String bdsTheoryThPlusIntVivaSecMark = matcher.group(6);
					   String practicalPlusIntMaxMark = matcher.group(7);
					  
					   String practicalInt = matcher.group(8);
					   String bdsPracticalPractical = matcher.group(9);
					   String practicalVivaPR = matcher.group(10);
					   String practicalTotalSecMarks = matcher.group(11);				   

					  String thPlusPracticalMaxMark =  matcher.group(12);
					  String thPlusPracticalSecMark = matcher.group(13);
						status =	 matcher.group(14); 


					System.out.println("==============");
						    
					System.out.println("Subject: " + subject);

					System.out.println("TH + int Viva Max Marks: " + bdsTheoryThPlusIntVivaMaxMark);
					System.out.println("Theory Int Marks: " + bdsTheoryInt);
					System.out.println("Theory TH Marks: " + bdsTheoryTh);
					System.out.println("Theory Viva Marks: " + bdsTheoryThViva);
					System.out.println("TH + int Viva Sec Marks: " + bdsTheoryThPlusIntVivaSecMark);
					System.out.println("Practical + int Max Marks: " + practicalPlusIntMaxMark);
					System.out.println("Practical + int Marks: " + practicalInt);
					System.out.println("Practical Practical: " + bdsPracticalPractical);
					System.out.println("Practical Viva PR: " + practicalVivaPR);
					System.out.println("Practical + Viva Sec. Marks: " + practicalTotalSecMarks);
					System.out.println("Theory + Practical Max Marks: " + thPlusPracticalMaxMark);
					System.out.println("Theory + Practical Sec. Marks: " + thPlusPracticalSecMark);
					System.out.println("Result: " + status);
					System.out.println("==============");


					if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {
					
					
					try {

						nonValidateMarks(Regno,"Theory Int sec. marks", subject,
								bdsTheoryInt, testCaseName);		
				}
				
				catch(Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode("Theory Int sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);
										
				}
					
					try {
						
						nonValidateMarks(Regno,"Therory TH sec. marks", subject,
								bdsTheoryTh, testCaseName);		
				}
				
				catch(Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode("Therory TH sec. marks validation for the subject " + subject +" Test case has started running");

					testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


					System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);
						
				}
					
					try {
						
						nonValidateMarks(Regno,"Therory TH sec. marks", subject,
								bdsTheoryThViva, testCaseName);		
				}
				
				catch(Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode("Therory Viva sec. marks validation for the subject " + subject +" Test case has started running");

					testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


					System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);
							
				}
					
			
					try {
						
						
						
						double theoryInt = objectToDataType(bdsTheoryInt);
						
						double theoryTh = objectToDataType(bdsTheoryTh);
						
						double theoryViva =objectToDataType(bdsTheoryThViva);
								
						double finalTheoryMark= theoryInt + theoryTh +theoryViva;
						
			
							if (finalTheoryMark == objectToDataType(bdsTheoryThPlusIntVivaSecMark)) {
							
								ExtentTest testCaseScenario = testCaseName.createNode("TH + int + Viva Sec Marks for the Subject "+ subject +" Validation Test case has started running");
		
								System.out.println(
										"Both addtion of INT+TH+VIVA  " +  objectToDataType(bdsTheoryThPlusIntVivaSecMark) + " and pdf " +finalTheoryMark + " value for the following Register "
												+ Regno + " number data are same mark");
								testCaseScenario.log(Status.PASS,
										"Both addtion of INT+TH+VIVA  " +  objectToDataType(bdsTheoryThPlusIntVivaSecMark) + " and pdf " +finalTheoryMark + " value for the following Register "
												+ Regno + " number data are same mark");

							}

							else {
								ExtentTest testCaseScenario = testCaseName.createNode("Theory TH + int + Viva Sec Marks for the Subject "+ subject +" Validation Test case has started running");
								
								System.out.println(
										"Both addtion of Theory INT+TH+VIVA  " +  objectToDataType(bdsTheoryThPlusIntVivaSecMark) + " and pdf " +finalTheoryMark + " value for the following Register "
												+ Regno + " number data are same mark");
								testCaseScenario.log(Status.FAIL,
										"Both addtion of Theory INT+TH+VIVA  " +  objectToDataType(bdsTheoryThPlusIntVivaSecMark) + " and pdf " +finalTheoryMark + " value for the following Register "
												+ Regno + " number data are same mark",
												MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							}

						} catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode("TH + int + Viva Sec Marks for the Subject "+ subject +" Validation Test case has started running");
							
							System.out.println(
									"The following " + Regno + " registration number Theory INT+TH+VIVA  is" +bdsTheoryThPlusIntVivaSecMark);
							testCaseScenario.log(Status.INFO,
									"The following " + Regno + " registration number Theory INT+TH+VIVA  is" +bdsTheoryThPlusIntVivaSecMark);

						}
						
						try {
						validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
								  theoryExam,praticalExam, subject,
						        examTotal, bdsTheoryThPlusIntVivaSecMark, bdsTheoryThPlusIntVivaMaxMark, 0.50, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario = testCaseName.createNode(
							  " Paper1 Sec. Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Paper1 Sec. Marks: " + bdsTheoryThPlusIntVivaSecMark,
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
					
					
					try {

						nonValidateMarks(Regno,"Practical Int sec. marks", subject,
								practicalInt, testCaseName);		
				}
				
				catch(Exception e) {

					ExtentTest testCaseScenario = testCaseName.createNode("Practical Int sec. marks validation for the subject " + subject +" Test case has started running");

					testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);
								
				}
				
					try {

						nonValidateMarks(Regno,"Pratical pratical sec. marks", subject,
								bdsPracticalPractical, testCaseName);		
				}
				
				catch(Exception e) {

					ExtentTest testCaseScenario = testCaseName.createNode("Pratical pratical sec. marks validation for the subject " + subject +" Test case has started running");

					testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


					System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);
					
				}
					try {

						nonValidateMarks(Regno,"Pratical viva sec. marks", subject,
								practicalVivaPR, testCaseName);		
				}
				
				catch(Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec. marks is: " + practicalVivaPR,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec. marks is: " + practicalVivaPR);
										
				}

						
						
						
						try {
							
							double praticalIntMark = objectToDataType(practicalInt);
							
							double bdsPracticalPracticalMark = objectToDataType(bdsPracticalPractical);

									
							double finalPraticalMark= praticalIntMark + bdsPracticalPracticalMark;
						
							
							if (finalPraticalMark == objectToDataType(practicalTotalSecMarks)) {
							
								ExtentTest testCaseScenario = testCaseName.createNode("Practical int + Viva + Pratical Sec Marks for the Subject "+ subject +" Validation Test case has started running");
		
								System.out.println(
										"Both addtion of Practical INT+Pratical+VIVA  " + finalPraticalMark  + " and pdf " +objectToDataType(practicalTotalSecMarks) + " value for the following Register "
												+ Regno + " number data are same mark");
								testCaseScenario.log(Status.PASS,
										"Both addtion of Practical INT+Pratical+VIVA  " +  finalPraticalMark + " and pdf " +objectToDataType(practicalTotalSecMarks) + " value for the following Register "
												+ Regno + " number data are same mark");

							}

							else {
								ExtentTest testCaseScenario = testCaseName.createNode("TH + int + Viva Sec Marks for the Subject "+ subject +" Validation Test case has started running");
								
								System.out.println(
										"Both addtion of INT+TH+VIVA  " + finalPraticalMark + " and pdf " +objectToDataType(practicalTotalSecMarks) + " value for the following Register "
												+ Regno + " number data are same mark");
								testCaseScenario.log(Status.FAIL,
										"Both addtion of INT+TH+VIVA  " +  finalPraticalMark + " and pdf " +objectToDataType(practicalTotalSecMarks) + " value for the following Register "
												+ Regno + " number data are same mark",
												MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							}

						} catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode("TH + int + Viva Sec Marks for the Subject "+ subject +" Validation Test case has started running");
							
							System.out.println(
									"The following " + Regno + " registration number Pratical INT+TH+VIVA  is" +bdsTheoryThPlusIntVivaSecMark);
							testCaseScenario.log(Status.INFO,
									"The following " + Regno + " registration number Pratical INT+TH+VIVA  is" +bdsTheoryThPlusIntVivaSecMark);


						}
						try {
						
						validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
								  theoryExam,praticalExam, subject,
						        examTotal, practicalTotalSecMarks, practicalPlusIntMaxMark, 0.50, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario = testCaseName.createNode(
							  " Pratical Total Sec Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Pratical Total Sec Marks: " + practicalTotalSecMarks,
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
					
					
						System.out.println(thPlusPracticalSecMark);
						
						try {
							
							validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
									  theoryExam,praticalExam, subject,
							        examTotal, thPlusPracticalSecMark, thPlusPracticalMaxMark, 0.50, testCaseName);		
					}
					
					catch(Exception e) {
						 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
							
							
						 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
								 + " Theory plus Pratical Sec. Marks is: " + thPlusPracticalSecMark,						
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						 System.out.println("\nThe Following Registration number " + Regno
									+ " Theory plus Pratical Sec. Marks is:" + thPlusPracticalSecMark);
					}
					
					
					
							 	}					
					
							} //while
										
										
							}
							
						 	else {
								 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
									
									
								 testCaseScenario.log(Status.FAIL,"No match found",						
											MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						 		
						 	}
							
				
				}}}

	}// ig

	public void processFourSubjectPatternPdf(Object Regno, File latestFile, Object paper1, Object paper2, Object paper3,Object paper4,
			 Object theoryExam,Object praticalExam, Object examTotal, String subjectToFind, ExtentTest testCaseName)
			throws IOException {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					// System.out.println(text);

					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");
					// Extract registration number

					Pattern registrationPattern = Pattern.compile("Registration No :\\s*(\\d+)");
					Pattern securedMarksPattern = Pattern.compile("Secured Marks\\s*:\\s*(\\d+)");

					Matcher regMatcher = registrationPattern.matcher(text);
					Matcher securedMatcher = securedMarksPattern.matcher(text);

					if (securedMatcher.find()) {
						String securedMarks = securedMatcher.group(1); // Extract the number
						securedMark = Long.parseLong(securedMarks);

						System.out.println("Secured Marks: " + securedMark);

					} else {
						System.out.println("No Secured match found!");
						System.out.println("FAIL");
					}

					String subjectRegex = "\\b(PG|MDS|BNYS|BHMS|UNANI|BAMS|BUMS|MBBS|M\\.P\\.H)\\b";

					String subjectAyurvedaPart2Regex = "(?i)(M\\.P\\.T\\.)";

					// Matches M.D., M.D.AYURVEDA, M.S. followed by AYURVEDA and specialization in
					// brackets
					String ayurvedaPart2Regex = "M\\.(?:D(?:\\.A)?\\.?|S\\.)\\s*AYURVEDA\\s*\\([A-Z &]+\\)";

					Pattern subjectPattern = Pattern.compile(subjectRegex, Pattern.MULTILINE);
					Matcher subjectMatcher = subjectPattern.matcher(text);
					Pattern subjectAyurvedaPart2RegexPattern = Pattern.compile(subjectAyurvedaPart2Regex,
							Pattern.MULTILINE);
					Matcher subjectAyurvedaPart2RegexMatcher = subjectAyurvedaPart2RegexPattern.matcher(text);
					Pattern ayurvedaPart2RegexPattern = Pattern.compile(ayurvedaPart2Regex, Pattern.MULTILINE);
					Matcher ayurvedaPart2RegexMatcher = ayurvedaPart2RegexPattern.matcher(text);

					if (subjectMatcher.find()) {
						System.out.println("Match found Course: " + subjectMatcher.group());
						// subjectMatcher.reset();
					}

					else if (subjectAyurvedaPart2RegexMatcher.find()) {
						System.out.println("Match found Course1: " + subjectAyurvedaPart2RegexMatcher.group());
						// subjectAyurvedaPart2RegexMatcher.reset();
					}

					else if (ayurvedaPart2RegexMatcher.find()) {

						System.out.println("Match found Course2: " + ayurvedaPart2RegexMatcher.group());
						subject = ayurvedaPart2RegexMatcher.group();
					}

					else {

						System.out.println("FAIL");
						System.out.println("No match found.");
					}

					Pattern fourPattern = Pattern.compile(
//			        		"(?m)^(?!.*\\b(Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\b)\\s*" +
//			        				"([A-Za-z &'’\\-(),]+(?:\\R[A-Za-z &'’\\-(),]+)*)"+// Ignore if "Fail" appears at the beginning
//			        				
							"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
									+
////previous crt one
//									"([A-Za-z &'\\-\\(\\),]+(?:\\n[A-Za-z &'\\-\\(\\),]+)*)\\s+" +
//									"(?:\\(([^)]+)\\))?\\s*" + // Group 2: Specialization (Optional, inside parentheses)			
//				
				//For MPH Y2 Reg 16 Command this pattern and run on it to match with Rc PDF

			"([A-Z &'\\-\\(\\),]+(?:\\n[A-Z &'\\-/(\\),]+)*)\\s+(?:\\(([^)]+)\\))?"+
			
						
						"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 3: Theory Max
																									// Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 4: Theory Sec
																									// Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 5: Practical
																									// Max Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 6: Practical
																									// Sec Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 7: Viva Max
																									// Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 8: Viva Sec
																									// Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 9: Grand Total
																									// Max Marks
									"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s*" + // Group 10: Grand
																									// Total Sec Marks
									"\\b(Pass|Fail|AP|NE|AB)\\b", // Group 11: Status (at the END)
							Pattern.MULTILINE // Enables multi-line matching
					);
		
					/*
					 * correct pattern previoly Pattern fourPattern = Pattern.compile(
					 * "([A-Za-z &'\\-()]+)\\s+" + // Subject name (Group 1)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Theory Max
					 * Marks (Group 2) "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" +
					 * // Theory Sec Marks (Group 3)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Practical Max
					 * Marks (Group 4) "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" +
					 * // Practical Sec Marks (Group 5)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + //
					 * Practical+Viva Max Marks (Group 6)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + //
					 * Practical+Viva Sec Marks (Group 7)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Grand Total
					 * Max Marks (Group 8)
					 * "(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s*" + // Grand Total
					 * Sec Marks (Group 9) "(Pass|Fail|AP|NE|AB)" // Status (Group 10) );
					 */
					subjectMatcher.reset(); // Reset matcher
					subjectAyurvedaPart2RegexMatcher.reset(); // Reset matcher

					Matcher fourPatternMatcher = fourPattern.matcher(text);
					boolean foundSubject = subjectMatcher.find();
					boolean foundSubject1 = subjectAyurvedaPart2RegexMatcher.find();

					System.out.println("Foundsubject " + foundSubject);
					System.out.println("Foundsubject1 " + foundSubject1);
					while (fourPatternMatcher.find()) {

					
					

						System.out.println("Foundsubject " + foundSubject);
						System.out.println("Foundsubject1 " + foundSubject1);

						if ((foundSubject1 && subjectAyurvedaPart2RegexMatcher.group().contains("M.P.T."))) {
							// Your processing logic here

							System.out.println("==============");

//									subject = fourPatternMatcher.group(1).trim();
							subject = (fourPatternMatcher.group(2) == null)
									? fourPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (fourPatternMatcher.group(1) + " " + fourPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();

							String theoryMaxMarks = fourPatternMatcher.group(3); // Take the last part

							String	theorySecMarks = fourPatternMatcher.group(4);
							String praticalMaxMarks = fourPatternMatcher.group(5);
							String praticalSecMarks = fourPatternMatcher.group(6);

							String	vivaMaxMarks = fourPatternMatcher.group(7);
							String vivaSecMarks = fourPatternMatcher.group(8);

							String theoryPraticalTotalMaxMarks = fourPatternMatcher.group(9);
							String theoryPraticalTotalSecMarks = fourPatternMatcher.group(10);
							String grandTotalMaxMarks = fourPatternMatcher.group(9);
							String grandTotalSecMarks = fourPatternMatcher.group(10);
							status = fourPatternMatcher.group(11);

							// Check if this is the subject we are looking for

							System.out.println("==============");

							System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
							System.out.println("Theory Max Marks: " + theoryMaxMarks);

							System.out.println("Theory Sec Marks: " + theorySecMarks);
							System.out.println("Practical Max Marks: " + praticalMaxMarks);

							// Check if group(6) exists (to avoid IndexOutOfBoundsException)
							if (fourPatternMatcher.groupCount() >= 6) {
								System.out.println("Practical Sec Marks: " + praticalSecMarks);
							} else {
								System.out.println("Practical Sec Marks: NA"); // Handle cases where it's missing
							}

							// Practical Total Marks
							System.out.println("Viva Max Marks: " + vivaMaxMarks);
							System.out.println("Viva Total Sec Marks: " + vivaSecMarks);

							// Grand Total
							System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
							System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

							// Status
							System.out.println("Status: " + status);
							System.out.println("==============");

							paper1Mark = 0.0;
							paper2Mark = 0.0;
							paper3Mark = 0.0;
							praticalTotalSecMark = 0.0;
							ExamTotalScore = 0.0;
							Paper1 = 0.0;
							Paper2 = 0.0;
							Paper3 = 0.0;
							PraticalExamTotal = 0.0;

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {

								try {

									if (!theorySecMarks.equals("NA") || !theorySecMarks.equals("AB")
											|| !theorySecMarks.equals("NE") || !theorySecMarks.equals("NE (AT)")) {

										theoryInternalMaxMark = Double.parseDouble(theoryMaxMarks);
										paper1Mark = Double.parseDouble(theorySecMarks);
										verifyScore(paper1Mark, theoryInternalMaxMark, 0.50);
										checkMarks(Regno, "Theory Sec Marks", paper1, paper2, paper3,paper4, praticalExam,
												theoryExam, subjectToFind, examTotal, theorySecMarks,
												theoryInternalMaxMark, testCaseName);
									}

									else {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n FAIL Please check The Following Registration number " + Regno
														+ " Pratical Internal Sec Marks: " + theorySecMarks);

										System.out.println("\n please check The Following Registration number " + Regno
												+ " Pratical Internal Sec. Marks: " + theorySecMarks);

									}

									// Use the value

								} catch (NumberFormatException e) {
									if (theorySecMarks.equals("AB") || theorySecMarks.equals("NE")
											|| theorySecMarks.equals("NA") || theorySecMarks.equals(" NA")
											|| theorySecMarks.equals("---") || theorySecMarks.equals("NE (AT)")) {
										paper1Mark = 0.0;
										Paper1 = 0.0;
										System.out.println(paper1Mark);

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following Registration number " + Regno
												+ " Pratical Internal Sec Marks: " + theorySecMarks);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Pratical Internal Sec. Marks: " + theorySecMarks);
									}

									else {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " Pratical Internal Sec Marks: " + theorySecMarks);

										System.out.println("\n please check The Following Registration number " + Regno
												+ " Pratical Internal Sec. Marks: " + theorySecMarks);

									}

								}
								try {

									if ((!vivaSecMarks.contains("NA")) || (!vivaSecMarks.equals("NA "))) {
										praticalTotalMaxMark = Double.parseDouble(vivaMaxMarks);
										praticalTotalSecMark = Double.parseDouble(vivaSecMarks);
										verifyScore(praticalTotalSecMark, praticalTotalMaxMark, 0.50);
										checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal, theorySecMarks,
												theoryInternalMaxMark, testCaseName);
									} // Use the value
									// Check pratical internal Sec. Marks
									else {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical. Marks Validation for the Subject " + subject
														+ " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject + " Practical Marks is:"
														+ vivaSecMarks);

										System.out.println("\n Please check The Following Registration number " + Regno
												+ " Practical Marks is:" + vivaSecMarks);
									}

								} catch (NumberFormatException e) {

									if (vivaSecMarks.equals("AB") || vivaSecMarks.equals("NE")
											|| vivaSecMarks.equals("NA") || vivaSecMarks.equals("NA ")
											|| vivaSecMarks.equals("---") || vivaSecMarks.equals("NE (AT)")) {
										praticalTotalSecMark = 0.0;
										PraticalExamTotal = 0.0;
										System.out.println(praticalTotalSecMark);

										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical. Marks Validation for the Subject " + subject
														+ " Test case has started running");

										testCaseScenario.log(Status.INFO,
												"\n The Following Registration number " + Regno + " for the Subject "
														+ subject + " Practical Marks is:" + vivaSecMarks);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Practical Marks is:" + vivaSecMarks);

										// Handle gracefully, e.g., assign default value or log an error

									}

									else {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical. Marks Validation for the Subject " + subject
														+ " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject + " Practical Marks is:"
														+ vivaSecMarks);

										System.out.println("\n Please check The Following Registration number " + Regno
												+ " Practical Marks is:" + vivaSecMarks);
									}

								}

								try {

									if (!grandTotalSecMarks.equals("NA") || !grandTotalSecMarks.equals("AB")
											|| !grandTotalSecMarks.equals("NE")
											|| !grandTotalSecMarks.equals("NE (AT)")) {

										grandTotalMaxMark = Double.parseDouble(grandTotalMaxMarks);
										ExamTotalScore = Double.parseDouble(grandTotalSecMarks);
										verifyScore(ExamTotalScore, grandTotalMaxMark, 0.50);
										checkMarks(Regno, " Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
												praticalExam, theoryExam, subjectToFind, examTotal, grandTotalSecMarks,
												grandTotalMaxMark, testCaseName);
									}
									// Check Grand Total Sec. Marks (assumed max marks as 200)

									else {

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n please check The Following a Registration number " + Regno
														+ " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

										System.out.println("\n please check The Following a Registration number "
												+ Regno + " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

									}
								}
								// Use the value }

								catch (NumberFormatException e) {

									if (grandTotalSecMarks.equals("AB") || grandTotalSecMarks.equals("NE")
											|| grandTotalSecMarks.equals("NA") || grandTotalSecMarks.equals(" NA")
											|| grandTotalSecMarks.equals("---")
											|| grandTotalSecMarks.equals("NE (AT)")) {
										ExamTotalScore = 0.0;
										System.out.println(ExamTotalScore);

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following a Registration number "
												+ Regno + " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

										System.out.println("\n The Following a Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

									}

									else {

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory internal sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n please check The Following a Registration number " + Regno
														+ " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

										System.out.println("\n please check The Following a Registration number "
												+ Regno + " Theory plus Pratical Sec. Marks is: " + grandTotalSecMarks);

									}
								}

							}

						}

						// Store result of find()

						else if (((foundSubject && subjectMatcher.group().trim().contains("BAMS"))
							
								|| (foundSubject && subjectMatcher.group().contains("BHMS"))
								|| (foundSubject && subjectMatcher.group().contains("MBBS"))
								|| (foundSubject && subjectMatcher.group().contains("M.P.H")))
								|| (foundSubject && subjectAyurvedaPart2RegexMatcher.group().contains("AYURVEDA"))) {
							// Your logic here

							System.out.println("==============");

							subject = (fourPatternMatcher.group(2) == null)
									? fourPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (fourPatternMatcher.group(1) + " " + fourPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();

							String	theoryMaxMarks = fourPatternMatcher.group(3); // Take the last part

							String	theorySecMarks = fourPatternMatcher.group(4);
							String praticalMaxMarks = fourPatternMatcher.group(5);
							String	praticalSecMarks = fourPatternMatcher.group(6);

							String	vivaMaxMarks = fourPatternMatcher.group(7);
							String	vivaSecMarks = fourPatternMatcher.group(8);

							String		grandTotalMaxMarks = fourPatternMatcher.group(9);
							String		grandTotalSecMarks = fourPatternMatcher.group(10);
							status = fourPatternMatcher.group(11);

							// Check if this is the subject we are looking for

							System.out.println("==============");

							System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
							System.out.println("Theory Max Marks: " + theoryMaxMarks);

							System.out.println("Theory Sec Marks: " + theorySecMarks);
							System.out.println("Practical Max Marks: " + praticalMaxMarks);

							// Check if group(6) exists (to avoid IndexOutOfBoundsException)
							if (fourPatternMatcher.groupCount() >= 6) {
								System.out.println("Practical Sec Marks: " + praticalSecMarks);
							} else {
								System.out.println("Practical Sec Marks: NA"); // Handle cases where it's missing
							}

							// Practical Total Marks
							System.out.println("Viva Max Marks: " + vivaMaxMarks);
							System.out.println("Viva Total Sec Marks: " + vivaSecMarks);

							// Grand Total
							System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
							System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

							// Status
							System.out.println("Status: " + status);
							System.out.println("==============");

							paper1Mark = 0.0;
							paper2Mark = 0.0;
							paper3Mark = 0.0;
							praticalTotalSecMark = 0.0;
							ExamTotalScore = 0.0;
							Paper1 = 0.0;
							Paper2 = 0.0;
							Paper3 = 0.0;
							PraticalExamTotal = 0.0;

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {

							try {
									validateMarks(Regno,"Theory Total Sec. Marks", paper1, paper2, paper3,paper4,
											 theoryExam,praticalExam,subject,
									        examTotal, theorySecMarks, theoryMaxMarks, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Theory internal sec. Marks: " + theorySecMarks);							
							}
							
							try {
								validateMarks(Regno,"Theory (Univ) Sec. Marks", paper1, paper2, paper3,paper4,
										 theoryExam,praticalExam, subject,
								        examTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		
						}
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Theory (Univ) Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Theory (Univ) Sec. Marks " + praticalSecMarks);							
							}
							
							
							try {
								
								if (subjectMatcher.group().contains("MBBS")) {
									validateMarks(Regno,"Pratical Internal Sec. Marks", paper1, paper2, paper3,paper4,
											 theoryExam,praticalExam, subject,
									        examTotal, vivaSecMarks, vivaMaxMarks, 0.35, testCaseName);		
							} else {
								validateMarks(Regno,"Pratical Internal Sec. Marks", paper1, paper2, paper3,paper4,
										 theoryExam,praticalExam, subject,
								        examTotal, vivaSecMarks, vivaMaxMarks, 0.50, testCaseName);		
					}
			
						}
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Pratical Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Pratical Internal Sec. Marks: " + vivaSecMarks);							
							}
									
							try {
								validateMarks(Regno,"Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
										 theoryExam,praticalExam, subject,
								        examTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		
						}
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Theory plus pratical Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Theory plus pratical Sec. Marks: " + grandTotalSecMarks);							
							}
							
							}
						
							
						}
					} // while


					System.out.println("Foundsubject " + foundSubject);
					System.out.println("Foundsubject1 " + foundSubject1);
					
					System.out.println("Foundsubject " + foundSubject);
					if (subjectMatcher.group().contains("PG")) {
						
						
						Pattern pgSubjectPattern = Pattern.compile("(.+)\\s*Subject\\s*:|Subject\\s*(?:Name)?\\s*:\\s*(.+)",
								Pattern.CASE_INSENSITIVE);

						Matcher pgSubjectPatternMatcher = pgSubjectPattern.matcher(text);

						if (pgSubjectPatternMatcher.find()) {
							// Check which group is non-null and capture the correct subject name
							String subjectName = pgSubjectPatternMatcher.group(1) != null ? pgSubjectPatternMatcher.group(1)
									: pgSubjectPatternMatcher.group(2);

							subject = subjectName;
							System.out.println("Subject: " + subject);
						}
	
						
						
						System.out.println("subject"+subject);
						
								String paperMaxMark ="100";
								String practicalTotalMaxMark = "400";
								String theoryTotalMaxMark = "400";

								String grandTotalMaxMarks = "800";
								
//								Pattern ayurvedaPart2Pattern = Pattern
//										.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(Pass|Fail)");
								Pattern ayurvedaPart2Pattern = Pattern.compile(
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(\\d+|AB|NA|NR|NE(?:\\s*\\(AT\\))?)\\s*(?:\\(\\s*F\\s*\\))?\\s+" +
									    "(Pass|Fail)"
									);
								
								Matcher ayurvedaPart2PatternMatcher = ayurvedaPart2Pattern.matcher(text);


								if (ayurvedaPart2PatternMatcher.find()) {
									
									String theoryPaper1 = ayurvedaPart2PatternMatcher.group(1);
									String theoryPaper2 = ayurvedaPart2PatternMatcher.group(2);
									String theoryPaper3 = ayurvedaPart2PatternMatcher.group(3);
									String theoryPaper4 = ayurvedaPart2PatternMatcher.group(4);
									String theoryTotalSecMark = ayurvedaPart2PatternMatcher.group(5);
									String practicalTotalSecMark = ayurvedaPart2PatternMatcher.group(6);
									String grandTotalSecMarks = ayurvedaPart2PatternMatcher.group(7);
									status = ayurvedaPart2PatternMatcher.group(8);

									System.out.println("Subject: " + subject);
									System.out.println("Theory Paper 1: " + theoryPaper1);
									System.out.println("Theory Paper 2: " + theoryPaper2);
									System.out.println("Theory Paper 3: " + theoryPaper3);
									System.out.println("Theory Paper 4: " + theoryPaper4);
									System.out.println("Theory Total Max mark: " + theoryTotalMaxMark);

									System.out.println("Theory Total Sec Mark: " + theoryTotalSecMark);
									System.out.println("Practical Total Max Mark: " + practicalTotalMaxMark);

									System.out.println("Practical Total Sec Mark: " + practicalTotalSecMark);

									System.out.println("Grand Total Max mark: " + grandTotalMaxMarks);

									System.out.println("Grand Total: " + grandTotalSecMarks);
									System.out.println("Result: " + status);

									if ((status.trim().equals("Pass") || status.trim().equals("Fail")
											|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
										 ExtentTest testCaseScenario1 = testCaseName.createNode(
												  " Subject validation for excel subject name  " + subjectToFind + " and pdf subject name "+ subject +" test case has started");
										  testCaseScenario1.log(Status.PASS,
													" The Following Subject validation for excel subject name  " + subjectToFind +" and pdf subject name "+ subject +" are equals" );			
										
										try {
											validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
													 theoryExam,praticalExam, subject,
											        examTotal, theoryPaper1, paperMaxMark, 0.50, testCaseName);		
									}
									
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Paper1 Sec Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Paper1 Sec Marks: " + theoryPaper1);							
										}	
							
												
												try {
													validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
															 theoryExam,praticalExam, subject,
													        examTotal, theoryPaper2, paperMaxMark, 0.50, testCaseName);		
											}
											
											catch(Exception e) {
												  ExtentTest testCaseScenario = testCaseName.createNode(
														  " Paper2 Sec Marks validation for subject " + subjectToFind + " test case has started");
												  testCaseScenario.log(Status.FAIL,
															"\n Please check The Following Registration number " + Regno
																	+ "Paper2 Sec Marks: " + theoryPaper2);							
											}	
												
												
												try {
													validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
															 theoryExam,praticalExam, subject,
													        examTotal, theoryPaper3, paperMaxMark, 0.50, testCaseName);		
											}
											
											catch(Exception e) {
												  ExtentTest testCaseScenario = testCaseName.createNode(
														  " Paper3 Sec Marks validation for subject " + subjectToFind + " test case has started");
												  testCaseScenario.log(Status.FAIL,
															"\n Please check The Following Registration number " + Regno
																	+ "Paper3 Sec Marks: " + theoryPaper2);							
											}	
												
												try {
													validateMarks(Regno,"Paper4 Sec Marks", paper1, paper2, paper3,paper4,
															 theoryExam,praticalExam,subject,
													        examTotal, theoryPaper4, paperMaxMark, 0.50, testCaseName);		
											}
											
											catch(Exception e) {
												  ExtentTest testCaseScenario = testCaseName.createNode(
														  " Paper4 Sec Marks validation for subject " + subjectToFind + " test case has started");
												  testCaseScenario.log(Status.FAIL,
															"\n Please check The Following Registration number " + Regno
																	+ "Paper4 Sec Marks: " + theoryPaper2);							
											}	
												
										
								

										try {

											if (!theoryTotalSecMark.equals("NA") || !theoryTotalSecMark.equals("AB")
													|| !theoryTotalSecMark.equals("NE")
													|| !theoryTotalSecMark.equals("NE (AT)")) {
													validateMarks(Regno,"Theory Total Sec. Marks", paper1, paper2, paper3,paper4,
															 theoryExam,praticalExam, subject,
													        examTotal, theoryTotalSecMark, theoryTotalMaxMark, 0.50, testCaseName);		
											
											}}
									
											catch(Exception e) {
												  ExtentTest testCaseScenario = testCaseName.createNode(
														  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
												  testCaseScenario.log(Status.FAIL,
															"\n Please check The Following Registration number " + Regno
																	+ "Theory internal sec. Marks: " + theoryTotalSecMark);							
											}

											
											try {
												validateMarks(Regno,"Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
														 theoryExam,praticalExam, subject,
												        examTotal, practicalTotalSecMark, practicalTotalMaxMark, 0.50, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory internal sec. Marks: " + theoryTotalSecMark);							
										}

											try {
												validateMarks(Regno,"Grand Total Sec. Marks", paper1, paper2, paper3,paper4,
														 theoryExam,praticalExam, subject,
												        examTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		
												
												System.out.println("==============");
												// securedMarks(Regno, examTotal, testCaseName);
												System.out.println("==============");
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory internal sec. Marks: " + theoryTotalSecMark);							
										}
											
									
									}//if
									else {
										 ExtentTest testCaseScenario = testCaseName.createNode(
												  " Subject validation for excel subject name  " + subjectToFind + " and pdf subject name "+ subject +" test case has started");
										  testCaseScenario.log(Status.FAIL,
													" Please check The Following Subject validation for excel subject name  " + subjectToFind +" and pdf subject name "+ subject + " are not equals");			
									}
									
									}
								else {
									System.out.println("text"+text);
								}
								}//if
					
					if (ayurvedaPart2RegexMatcher.group().contains("AYURVEDA")) {

//				    	  	
						Pattern paperPattern = Pattern.compile(
								"(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");

						Matcher paperMatcher = paperPattern.matcher(text);

						String practicalTotalMaxMark = null;
						String practicalTotalMinMark = null;

						String theoryTotalMaxMark = null;
						String theoryTotalMinMark = null;

						String grandTotalMaxMarks = null;
						String grandTotalMinMarks = null;

						while (paperMatcher.find()) {

							System.out.println("paperMatcher.group(); " + paperMatcher.group());

							System.out.println("paperMatcher.group(1); " + paperMatcher.group(1));
							System.out.println("paperMatcher.group(2); " + paperMatcher.group(2));
							System.out.println("paperMatcher.group(3); " + paperMatcher.group(3));
							System.out.println("paperMatcher.group(4); " + paperMatcher.group(4));
							System.out.println("paperMatcher.group(5); " + paperMatcher.group(5));
							System.out.println("paperMatcher.group(6); " + paperMatcher.group(6));
							System.out.println("paperMatcher.group(7); " + paperMatcher.group(7));

							System.out.println("end");

							if (paperMatcher.group(6) != null) {

								theoryTotalMaxMark = paperMatcher.group(6);
								theoryTotalMinMark = paperMatcher.group(7);

								System.out.println("Grand Total Max Marks: " + paperMatcher.group(6) + ", Min Marks: "
										+ paperMatcher.group(7));
							}

							else if (((paperMatcher.group(4).equals("100") || (paperMatcher.group(4).equals("200")))
									&& paperMatcher.group(4) != null)) {
								System.out.println(paperMatcher.group(4) + " Max Marks: " + paperMatcher.group(5)
										+ ", Min Marks: ");
								practicalTotalMaxMark = paperMatcher.group(4);
								practicalTotalMinMark = paperMatcher.group(5);

								continue;
							} else if (((paperMatcher.group(4).equals("500") || (paperMatcher.group(4).equals("600")))
									&& paperMatcher.group(4) != null)) {
								System.out.println("Theory Max Marks: " + paperMatcher.group(4) + ", Min Marks: "
										+ paperMatcher.group(5));
								grandTotalMaxMarks = paperMatcher.group(4);
								grandTotalMinMarks = paperMatcher.group(5);
							}

						}
						
						Pattern ayurvedaPart2Pattern = Pattern
								.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(Pass)");

						Matcher ayurvedaPart2PatternMatcher = ayurvedaPart2Pattern.matcher(text);


						if (ayurvedaPart2PatternMatcher.find()) {
							String theoryPaper1 = ayurvedaPart2PatternMatcher.group(1);
							String theoryPaper2 = ayurvedaPart2PatternMatcher.group(2);
							String theoryPaper3 = ayurvedaPart2PatternMatcher.group(3);
							String theoryPaper4 = ayurvedaPart2PatternMatcher.group(4);
							String theoryTotalSecMark = ayurvedaPart2PatternMatcher.group(5);
							String practicalTotalSecMark = ayurvedaPart2PatternMatcher.group(6);
							String grandTotalSecMarks = ayurvedaPart2PatternMatcher.group(7);
							status = ayurvedaPart2PatternMatcher.group(8);

							System.out.println("Subject: " + subject);
							System.out.println("Theory Paper 1: " + theoryPaper1);
							System.out.println("Theory Paper 2: " + theoryPaper2);
							System.out.println("Theory Paper 3: " + theoryPaper3);
							System.out.println("Theory Paper 4: " + theoryPaper4);
							System.out.println("Theory Total Max mark: " + theoryTotalMaxMark);
							System.out.println("Theory Total Min mark: " + theoryTotalMinMark);
							System.out.println("Theory Total Sec Mark: " + theoryTotalSecMark);
							System.out.println("Practical Total Max Mark: " + practicalTotalMaxMark);
							System.out.println("Practical Total Min Mark: " + practicalTotalMinMark);
							System.out.println("Practical Total Sec Mark: " + practicalTotalSecMark);

							System.out.println("Grand Total Max mark: " + grandTotalMaxMarks);
							System.out.println("Grand Total Min mark: " + grandTotalMinMarks);

							System.out.println("Grand Total: " + grandTotalSecMarks);
							System.out.println("Result: " + status);

							paper1Mark = 0.0;
							paper2Mark = 0.0;
							paper3Mark = 0.0;
							praticalTotalSecMark = 0.0;
							ExamTotalScore = 0.0;
							Paper1 = 0.0;
							Paper2 = 0.0;
							Paper3 = 0.0;
							PraticalExamTotal = 0.0;
							double theoryPaper1Sec = 0.0;
							double theoryPaper2Sec = 0.0;
							double theoryPaper3Sec = 0.0;
							double theoryPaper4Sec = 0.0;

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {

								try {

									if (!theoryPaper1.equals("NA") || !theoryPaper1.equals("AB")
											|| !theoryPaper1.equals("NE") || !theoryPaper1.equals("NE (AT)")) {

										theoryPaper1Sec = Double.parseDouble(theoryPaper1);

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 1 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.PASS, "\nThe Following Registration number " + Regno
												+ " Paper 1 Sec Marks: " + theoryPaper1);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 1 Sec. Marks: " + theoryPaper1);
									}

									// Use the value

								} catch (NumberFormatException e) {
									if (theoryPaper1.equals("AB") || theoryPaper1.equals("NE")
											|| theoryPaper1.equals("NA") || theoryPaper1.equals(" NA")
											|| theoryPaper1.equals("---") || theoryPaper1.equals("NE (AT)")) {
										theoryPaper1Sec = 0.0;

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 1 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following Registration number " + Regno
												+ " Paper 1 Sec Marks: " + theoryPaper1);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 1 Sec Marks: " + theoryPaper1);
									}

									else {
										theoryPaper1Sec = 0.0;

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 1 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " Paper 1 Sec Marks: " + theoryPaper1);
										
										System.out.println("\n please check The Following Registration number " + Regno
												+ " Paper 1 Sec Marks: " + theoryPaper1);
									}

								}

								try {

									if (!theoryPaper2.equals("NA") || !theoryPaper2.equals("AB")
											|| !theoryPaper2.equals("NE") || !theoryPaper2.equals("NE (AT)")) {

										theoryPaper2Sec = Double.parseDouble(theoryPaper2);
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 2 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.PASS, "\nThe Following Registration number " + Regno
												+ " Paper 2 Sec Marks: " + theoryPaper2);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 2 Sec. Marks: " + theoryPaper2);
									}


								} catch (NumberFormatException e) {
									if (theoryPaper2.equals("AB") || theoryPaper2.equals("NE")
											|| theoryPaper2.equals("NA") || theoryPaper2.equals(" NA")
											|| theoryPaper2.equals("---") || theoryPaper2.equals("NE (AT)")) {
										theoryPaper2Sec = 0.0;

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 2 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following Registration number " + Regno
												+ " Paper 2 Sec Marks: " + theoryPaper2);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 2 Sec Marks: " + theoryPaper2);
									}

									else {
										theoryPaper2Sec = 0.0;
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 2 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " Paper 2 Sec Marks: " + theoryPaper2);

										System.out.println("\n please check The Following Registration number " + Regno
												+ " Paper 2 Sec Marks: " + theoryPaper2);

									}

								}
								try {

									if (!theoryPaper3.equals("NA") || !theoryPaper3.equals("AB")
											|| !theoryPaper3.equals("NE") || !theoryPaper3.equals("NE (AT)")) {

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 3 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										theoryPaper3Sec = Double.parseDouble(theoryPaper3);
										testCaseScenario.log(Status.PASS, "\nThe Following Registration number " + Regno
												+ " Paper 3 Sec Marks: " + theoryPaper3);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 3 Sec. Marks: " + theoryPaper3);
									}

									// Use the value

								} catch (NumberFormatException e) {
									if (theoryPaper3.equals("AB") || theoryPaper3.equals("NE")
											|| theoryPaper3.equals("NA") || theoryPaper3.equals(" NA")
											|| theoryPaper3.equals("---") || theoryPaper3.equals("NE (AT)")) {

										theoryPaper3Sec = 0.0;
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 3 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following Registration number " + Regno
												+ " Paper 3 Sec Marks: " + theoryPaper3);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 3 Sec Marks: " + theoryPaper3);
									}

									else {
										theoryPaper3Sec = 0.0;
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 3 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " Paper 3 Sec Marks: " + theoryPaper3);

										System.out.println("\n please check The Following Registration number " + Regno
												+ " Paper 3 Sec Marks: " + theoryPaper3);

									}

								}
								try {

									if (!theoryPaper4.equals("NA") || !theoryPaper4.equals("AB")
											|| !theoryPaper4.equals("NE") || !theoryPaper4.equals("NE (AT)")) {

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 4 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										theoryPaper4Sec = Double.parseDouble(theoryPaper4);
										testCaseScenario.log(Status.PASS, "\nThe Following Registration number " + Regno
												+ " Paper 4 Sec Marks: " + theoryPaper4);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 4 Sec. Marks: " + theoryPaper4);
									}

									// Use the value

								} catch (NumberFormatException e) {
									if (theoryPaper4.equals("AB") || theoryPaper4.equals("NE")
											|| theoryPaper4.equals("NA") || theoryPaper4.equals(" NA")
											|| theoryPaper4.equals("---") || theoryPaper4.equals("NE (AT)")) {

										theoryPaper4Sec = 0.0;

										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 4 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.INFO, "\nThe Following Registration number " + Regno
												+ " Paper 4 Sec Marks: " + theoryPaper4);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Paper 4 Sec Marks: " + theoryPaper4);
									}

									else {
										theoryPaper4Sec = 0.0;
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Paper 4 sec. marks validation for the subject "
														+ subject + " Test case has started running");

										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " Paper 4 Sec Marks: " + theoryPaper4);

										System.out.println("\n please check The Following Registration number " + Regno
												+ " Paper 4 Sec Marks: " + theoryPaper4);

									}

								}

								try {

									if (!theoryTotalSecMark.equals("NA") || !theoryTotalSecMark.equals("AB")
											|| !theoryTotalSecMark.equals("NE")
											|| !theoryTotalSecMark.equals("NE (AT)")) {
										theoryInternalMaxMark = Double.parseDouble(theoryTotalMaxMark);
										paper1Mark = Double.parseDouble(theoryTotalSecMark);

										double theory = theoryPaper1Sec + theoryPaper2Sec + theoryPaper3Sec
												+ theoryPaper4Sec;

										if (theory == paper1Mark) {
											ExtentTest testCaseScenario = testCaseName
													.createNode("theory total marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario.log(Status.PASS,
													"Both theory papers total and theory total mark are equals");

										}

										else {
											ExtentTest testCaseScenario = testCaseName
													.createNode("theory total marks validation for the subject "
															+ subject + " Test case has started running");
											testCaseScenario.log(Status.FAIL,
													"Both theory papers total and theory total mark are not equals");
										}
		
											validateMarks(Regno,"Theory Total Sec. Marks", paper1, paper2, paper3,paper4,
													 theoryExam,praticalExam, subject,
											        examTotal, theoryTotalSecMark, theoryTotalMaxMark, 0.50, testCaseName);		
									
									}}
							
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory internal sec. Marks: " + theoryTotalSecMark);							
									}

									
									try {
										validateMarks(Regno,"Pratical Univ Sec. Marks", paper1, paper2, paper3,paper4,
												 theoryExam,praticalExam, subject,
										        examTotal, practicalTotalSecMark, practicalTotalMaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory internal sec. Marks: " + practicalTotalSecMark);							
								}

									try {
										validateMarks(Regno,"Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		
										
										System.out.println("==============");
										// securedMarks(Regno, examTotal, testCaseName);
										System.out.println("==============");
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory internal sec. Marks: " + grandTotalSecMarks);							
								}
							}
						}//if
						
						
								
						
					}		// if
					
		

				} // for
			}
			}
	}
	public void processOneSubjectPaternPdf(File latestFile, Object Regno, Object paper1, Object paper2, Object paper3,Object paper4,
			Object theoryExam, Object praticalExam, Object examTotal, ExtentTest testCaseName, String subjectToFind)
			throws IOException {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");

					// Extract registration number
					Pattern registrationPattern = Pattern.compile("Registration No:\\s*(\\d+)");

					Matcher regMatcher = registrationPattern.matcher(text);

					Pattern subjectPattern = Pattern.compile("(.+)\\s*Subject\\s*:|Subject\\s*(?:Name)?\\s*:\\s*(.+)",
							Pattern.CASE_INSENSITIVE);

					Matcher subjectMatcher = subjectPattern.matcher(text);

					if (regMatcher.find()) {
					long	registrationNumber = Long.parseLong(regMatcher.group(1));
						System.out.println("Registration No: " + registrationNumber);
					}

					if (subjectMatcher.find()) {
						// Check which group is non-null and capture the correct subject name
						String subjectName = subjectMatcher.group(1) != null ? subjectMatcher.group(1)
								: subjectMatcher.group(2);

						subject = subjectName;
						System.out.println("Subject: " + subject);
					}

					// Pattern paperPattern = Pattern.compile(
					// "(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");

					String regex =  "(?i)(?:M\\.D\\. HOMOEOPATHY|MDS|BNYS|UNANI|BAMS|BUMS|PG|M\\.S\\.\\s*AYURVEDA|M\\.D\\.\\s*AYURVEDA|M\\.P\\.T\\.)\\s+";
				

					Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
					Matcher matcher1 = pattern.matcher(text);


					if (matcher1.find()) {

						System.out.println("Match found Course: " + matcher1.group());

					}
					else {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subject + " No match found");

						System.out.println("No match found.");
					}

					// Previous crt pattern

//				        Pattern oneSubjectPattern = Pattern.compile(
//
////				        		"([A-Za-z &'\\-\\(\\),]+(?:\\n[A-Za-z &'\\-\\(\\),]+)*)\\s+" +  // Subject name
//
//				        		"(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total).*\\n)*([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+"+  
//
//				        	"((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" +  // First mark
//				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +  // Second mark (optional)
//				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +  // Third mark (optional)
//				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +  // Fourth mark (optional)
//				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +  // Fifth mark (optional)
//				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +  // Sixth mark (optional)
//				        	    "(?:-+\\s*)?" +  // Handle extra dashes before the final result
//				        	    "(Pass|Fail|AP)"  // Final result
//				        	);
//

					String regexExtra = "Max\\.Marks:\\s*(\\d+)\\s+Min\\.Marks:\\s*(\\d+)";

					// Pattern for additional Max.Marks and Min.Marks entries
					Pattern patternExtra = Pattern.compile(regexExtra);
					Matcher matcherExtra = patternExtra.matcher(text);

					String paper1MaxMark = null;
					String paper1MinMark = null;
					String paper2MaxMark = null;
					String paper2MinMark = null;
					String paper3MaxMark = null;
					String paper3MinMark = null;
					String TotalMaxMark = null;
					String TotalMinMark = null;
					String practicalMaxMark = null;
					String practicalMinMark = null;

					String pgGrandTotalMaxMark = null;
					String pgGrandTotalMinMark = null;

					int paperCount = 1;
					while (matcherExtra.find()) {

						if (paperCount == 1) {
							paper1MaxMark = matcherExtra.group(1);
							paper1MinMark = matcherExtra.group(2);

							System.out.println("Paper1 Max Marks: " + paper1MaxMark);
							System.out.println("Paper1 Min Marks: " + paper1MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 2) {
							paper2MaxMark = matcherExtra.group(1);
							paper2MinMark = matcherExtra.group(2);

							System.out.println("Paper2 Max Marks: " + paper1MaxMark);
							System.out.println("Paper2 Min Marks: " + paper1MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 3) {
							paper3MaxMark = matcherExtra.group(1);
							paper3MinMark = matcherExtra.group(2);

							System.out.println("Paper3 Max Marks: " + paper3MaxMark);
							System.out.println("Paper3 Min Marks: " + paper3MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 4) {
							TotalMaxMark = matcherExtra.group(1);
							TotalMinMark = matcherExtra.group(2);

							System.out.println("Total Max Marks: " + TotalMaxMark);
							System.out.println("Total Min Marks: " + TotalMinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 5) {
							practicalMaxMark = matcherExtra.group(1);
							practicalMinMark = matcherExtra.group(2);

							System.out.println("Practical Max Marks: " + practicalMaxMark);
							System.out.println("Practical Min Marks: " + practicalMinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 6) {
							pgGrandTotalMaxMark = matcherExtra.group(1);
							pgGrandTotalMinMark = matcherExtra.group(2);

							System.out.println("Grand Total Max Marks: " + pgGrandTotalMaxMark);
							System.out.println("Grand Total Min Marks: " + pgGrandTotalMinMark);
							System.out.println("-----------------");
						}

						paperCount++;
					}
					/*
					 * Pattern marksPattern = Pattern.compile("(\\d+)(?:\\s+\\(F\\))?\\s+" +
					 * "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(\\d+)(?:\\s+\\(F\\))?\\s+" +
					 * "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(\\d+)(?:\\s+\\(F\\))?\\s+" +
					 * "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(Pass|Fail)");
					 */
					

					Pattern marksPattern = Pattern.compile("(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(Pass|Fail|AP)");


					Matcher marksMatcher = marksPattern.matcher(text);

				
					if (marksMatcher.find()) {

						try {
			

							
							if (matcher1.group().contains("PG")){
								String paper1Marks = marksMatcher.group(1);
								String paper2Marks = marksMatcher.group(2);
								String paper3Marks = marksMatcher.group(3);
								String theoryTotals = marksMatcher.group(4);
								String praticalTotals = marksMatcher.group(5);
								String grandTotals = marksMatcher.group(6);
								status = marksMatcher.group(7);

								System.out.println("Paper I Max mark: " + paper1MaxMark);
								System.out.println("Paper I: " + paper1Marks);
								System.out.println("Paper I Min mark: " + paper1MinMark);

								System.out.println("Paper II Max mark: " + paper2MaxMark);
								System.out.println("Paper II: " + paper2Marks);
								System.out.println("Paper II Min mark: " + paper2MinMark);

								System.out.println("Paper III Max mark: " + paper3MaxMark);
								System.out.println("Paper III: " + paper3Marks);
								System.out.println("Paper III Min mark: " + paper3MinMark);

								System.out.println("Theory Total Max mark: " + TotalMaxMark);
								System.out.println("Theory Total: " + theoryTotals);
								System.out.println("Theory Total Min mark: " + TotalMinMark);

								System.out.println("==============");
								System.out.println("Practical Total Max mark: " + practicalMaxMark);

								System.out.println("Practical Total: " + praticalTotals);

								System.out.println("Practical Total Min Mark: " + practicalMinMark);

								System.out.println("===============");
								System.out.println("Grand Total Max mark: " + pgGrandTotalMaxMark);

								System.out.println("Grand Total: " + grandTotals);

								System.out.println("Grand Total Min Mark: " + pgGrandTotalMinMark);

								System.out.println("Result: " + status);

								System.out.println("---------------------------------------------------");
								paper1Mark = 0.0;
								Paper1 = 0.0;
								praticalTotalSecMark = 0.0;
								PraticalExamTotal = 0.0;
								ExamTotalScore = 0.0;
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {
				
											try {
												validateMarks(Regno,"Theory Sec Marks", paper1, paper2, paper3,paper4,
												        praticalExam, theoryExam, subject,
												        examTotal, paper1Marks, paper1MaxMark, 0.40, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory internal sec. Marks: " + paper1Marks);							
										}	
											try {
												validateMarks(Regno,"Theory (Univ) Sec. Marks", paper1, paper2, paper3,paper4,
												        praticalExam, theoryExam, subject,
												        examTotal, paper2Marks, paper2MaxMark, 0.40, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory internal sec. Marks: " + paper2Marks);							
										}	
										
											try {
												validateMarks(Regno,"Pratical Internal Sec. Marks", paper1, paper2, paper3,paper4,
												        praticalExam, theoryExam, subject,
												        examTotal, paper3Marks, paper3MaxMark, 0.40, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario = testCaseName.createNode(
													  " Pratical Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory internal sec. Marks: " + paper3Marks);							
										}	
										
										try {
											validateMarks(Regno,"Pratical Total Sec. Marks", paper1, paper2, paper3,paper4,
											        praticalExam, theoryExam, subject,
											        examTotal, praticalTotals, practicalMaxMark, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical. Marks Validation for the Subject " + subject
														+ " Test case has started running");

										testCaseScenario.log(Status.INFO,
												"\n The Following Registration number " + Regno
														+ " for the Subject " + subject + " Practical Marks is:"
														+ praticalTotals);

										System.out.println("\nThe Following Registration number " + Regno
												+ " Practical Marks is:" + praticalTotals);

									}	
										try {
											validateMarks(Regno,"Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
											        praticalExam, theoryExam, subject,
											        examTotal, theoryTotals, TotalMaxMark, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Theory plus pratical Sec. Marks. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory plus pratical Sec. Marks: " + theoryTotals);							
									}		
				
								}
								try {
									validateMarks(Regno,"Theory plus pratical Sec Total. Marks", paper1, paper2, paper3,paper4,
									        praticalExam, theoryExam, subject,
									        examTotal, grandTotals, pgGrandTotalMaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								ExtentTest testCaseScenario = testCaseName
										.createNode("grand total Marks Validation for the Subject "
												+ subject + " Test case has started running");

								testCaseScenario.log(Status.INFO,
										"\n The Following Registration number " + Regno
												+ " for the Subject " + subject + " grand total Marks is:"
												+ grandTotals);

								System.out.println("\nThe Following Registration number " + Regno
										+ " grand total Marks is:" + grandTotals);						
							}		
								
								} // if part
							
							else if (matcher1.group().contains("MDS")) {

								paper1MaxMark="100";
								String paper1Marks = marksMatcher.group(1);
								String paper2Marks = marksMatcher.group(2);
								String paper3Marks = marksMatcher.group(3);
								String theoryTotalSecMarks = marksMatcher.group(4);
								String practicalVivaSecMarks = marksMatcher.group(5);
								String grandTotalMark = marksMatcher.group(6);
								status = marksMatcher.group(7);
								
								System.out.println("paper1MaxMark"+paper1MaxMark);
								
								System.out.println("Paper I: " + paper1Marks);
								System.out.println("Paper II: " + paper2Marks);
								System.out.println("Paper III: " + paper3Marks);
								System.out.println("Theory Total: " + theoryTotalSecMarks);

								System.out.println("==============");

								System.out.println("Practical Total: " + practicalVivaSecMarks);

								System.out.println("===============");

								System.out.println("Grand Total: " + grandTotalMark);

								System.out.println("Result: " + status);

								System.out.println("---------------------------------------------------");

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {
									  ExtentTest testCaseScenario1 = testCaseName.createNode(
											  " Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" test case has started");
									  testCaseScenario1.log(Status.PASS," Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" are equals");	
								
									  try {
										validateMarks(Regno,"Theory Sec Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, paper1Marks, paper1MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Paper1 Sec Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Paper1 sec. Marks: " + paper1Marks);							
								}	
							
									try {
										validateMarks(Regno,"Theory (Univ) Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, paper2Marks, paper1MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Paper2 Sec Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Paper2 sec. Marks: " + paper2Marks);							
								}	
									
									try {
										validateMarks(Regno,"Pratical Internal Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, paper3Marks, paper1MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Paper3 Sec Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Paper3 sec. Marks: " + paper3Marks);							
								}	
								// Use the value
									
									
									try {
										validateMarks(Regno,"Theory Mark Total Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, theoryTotalSecMarks, paper2MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Mark Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory Mark Total Sec. Marks: " + theoryTotalSecMarks);							
								}	
									
									
									try {
										
	if((paper1Marks.trim().equals("AB")||paper2Marks.trim().equals("AB")||paper3Marks.trim().equals("AB") )&& !practicalVivaSecMarks.trim().equals("NE (AT)") ) {
											
											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");		
											
											testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of theory internal sec mark is: "+ theoryTotalSecMarks +" Practical Univ Sec. Marks is:" + practicalVivaSecMarks
															,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

											System.out.println("\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ "of theory internal sec mark "+ theoryTotalSecMarks+" Practical Univ Sec. Marks is:" + practicalVivaSecMarks);

										}else {
											
											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");		
											
											testCaseScenario.log(Status.PASS,
													"The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of theory internal sec mark is: "+ theoryTotalSecMarks+" Practical Univ Sec. Marks is:" + practicalVivaSecMarks);

											System.out.println(	"The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ "of theory internal sec mark "+ theoryTotalSecMarks+" Practical Univ Sec. Marks is:" + practicalVivaSecMarks);

										}
										
										validateMarks(Regno,"Pratical Total Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, practicalVivaSecMarks, paper2MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									ExtentTest testCaseScenario = testCaseName
											.createNode("Pratical. Marks Validation for the Subject " + subject
													+ " Test case has started running");
									testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject + " Practical Marks is:"
													+ practicalVivaSecMarks);

									System.out.println("\n Please check The Following Registration number " + Regno
											+ " Practical Marks is:" + practicalVivaSecMarks);						
								}	
						
									
									try {
										
										validateMarks(Regno,"Theory plus pratical Sec Total. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, paper3Marks, paper1MaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Paper3 Sec Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Paper3 sec. Marks: " + paper3Marks);							
								}	
						
								}
								else {
									  ExtentTest testCaseScenario1 = testCaseName.createNode(
											  " Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" test case has started");
									  testCaseScenario1.log(Status.FAIL," Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" are not equals");	
								
								}
							}
						
							else if ( matcher1.group().trim().contains("AYURVEDA")&& !text.contains("SA-I")) {
								
								Pattern ayurvedaSubjectPattern = Pattern.compile(
										
										
									    "([A-Za-z &'()\\-,\\.]+?)\\s+" + // Subject, with lowercase and dots allowed
									    
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +
									    "(Pass|Fail|AP)");
									

						Matcher ayurvedaSubjectPatternMatcher = ayurvedaSubjectPattern.matcher(text);
							
								while (ayurvedaSubjectPatternMatcher.find()) {
								    System.out.println(ayurvedaSubjectPatternMatcher);
								
								System.out.println("AYURVEDA.group1" +ayurvedaSubjectPatternMatcher.group() );
								System.out.println("---------------------------------------------------");
										
								subject = ayurvedaSubjectPatternMatcher.group(1);
								String theroryMaxMark = ayurvedaSubjectPatternMatcher.group(2);
								String therorySecMark = ayurvedaSubjectPatternMatcher.group(3);
								String praticalVivaMaxMark = ayurvedaSubjectPatternMatcher.group(4);
								String praticalVivaSecMark = ayurvedaSubjectPatternMatcher.group(5);
								String grandTotalMaxMarks = ayurvedaSubjectPatternMatcher.group(6);
								String grandTotalSecMarks = ayurvedaSubjectPatternMatcher.group(7);
								status = ayurvedaSubjectPatternMatcher.group(8);
								
								System.out.println("Subject: " +subject);
								System.out.println("Theory Max Mark: " + theroryMaxMark);
								System.out.println("Theory Secured Mark: " + therorySecMark);
								System.out.println("Practical/Viva Max Mark: " + praticalVivaMaxMark);
								System.out.println("Practical/Viva Secured Mark: " + praticalVivaSecMark);
								System.out.println("Grand Total Max Mark: " + grandTotalMaxMarks);
								System.out.println("Grand Total Secured Mark: " + grandTotalSecMarks);

								System.out.println("Result: " + status);

								System.out.println("---------------------------------------------------");
								
								
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {
										validateMarks(Regno,"Theory Sec Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, therorySecMark, theroryMaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory internal sec. Marks: " + therorySecMark);							
								}

								try {

									if (!praticalVivaSecMark.trim().equals("NA") || !praticalVivaSecMark.trim().equals("AB")
											|| !praticalVivaSecMark.trim().equals("NE") || !praticalVivaSecMark.trim().equals("NE (AT)")
											||!praticalVivaSecMark.trim().equals("---")
											) {
										
										if(therorySecMark.trim().equals("AB") && !praticalVivaSecMark.trim().equals("NE (AT)") ) {
											
											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");		
											
											testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of theory internal sec mark is: "+ therorySecMark +" Practical Univ Sec. Marks is:" + praticalVivaSecMark
															,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

											System.out.println("\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ "of theory internal sec mark "+ therorySecMark+" Practical Univ Sec. Marks is:" + praticalVivaSecMark);

										}else {
											
											ExtentTest testCaseScenario = testCaseName
													.createNode("Pratical Univ Sec. Marks Validation for the Subject "
															+ subject + " Test case has started running");		
											
											testCaseScenario.log(Status.PASS,
													"The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of theory internal sec mark is: "+ therorySecMark+" Practical Univ Sec. Marks is:" + praticalVivaSecMark);

											System.out.println(	"The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ "of theory internal sec mark "+ therorySecMark+" Practical Univ Sec. Marks is:" + praticalVivaSecMark);

										}

											validateMarks(Regno,"Pratical Total Sec. Marks", paper1, paper2, paper3,paper4,
											        praticalExam, theoryExam, subject,
											        examTotal, praticalVivaSecMark, praticalVivaMaxMark, 0.50, testCaseName);		
									}}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario = testCaseName.createNode(
												  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Pratical Total Sec. Marks validation: " + praticalVivaSecMark);							
									}
					
							 
								}
		
								}
								System.out.println("---------------------------------------------------");

							}
							
							
							else if ( matcher1.group().trim().contains("AYURVEDA")&& text.contains("SA-")) {
							
								
								Pattern ayurvedaReg24SubjectPattern = Pattern.compile(

										"([A-Za-z &'()\\-,\\.]+?)\\s+" +                                   // Subject
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 1st mark
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 2nd mark
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 3rd mark
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 4th mark
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 5th mark
										"(NA|---|\\d+)(?:\\s*\\(F\\))?\\s+" +                              // 6th mark
										"(Pass|Fail|AP)" +                                                // Final result
										"(?:\\s+(Pass|Fail|First Class|Distinction|AP))?" );                // Optional classification

										Matcher ayurvedaReg24SubjectPatternMatcher = ayurvedaReg24SubjectPattern.matcher(text);

								while (ayurvedaReg24SubjectPatternMatcher.find()) {
								    System.out.println(ayurvedaReg24SubjectPatternMatcher.group(1));
								
								System.out.println("AYURVEDA.group1" +ayurvedaReg24SubjectPatternMatcher.group() );
								System.out.println("---------------------------------------------------");
										
								subject = ayurvedaReg24SubjectPatternMatcher.group(1);
								String theroryMaxMark = ayurvedaReg24SubjectPatternMatcher.group(2);
								String therorySecMark = ayurvedaReg24SubjectPatternMatcher.group(3);
								String sgpaMaxMark = ayurvedaReg24SubjectPatternMatcher.group(4);
								String sgpaSecMark = ayurvedaReg24SubjectPatternMatcher.group(5);
								String creditMaxMark = ayurvedaReg24SubjectPatternMatcher.group(6);
								String creditSecMark = ayurvedaReg24SubjectPatternMatcher.group(7);
								status = ayurvedaReg24SubjectPatternMatcher.group(8);
								String finalStatus =ayurvedaReg24SubjectPatternMatcher.group(9);
								
								System.out.println("Subject: " +subject);
								System.out.println("Theory Max Mark: " + theroryMaxMark);
								System.out.println("Theory Secured Mark: " + therorySecMark);
								System.out.println("SGPA Max Mark: " + sgpaMaxMark);
								System.out.println("SGPA Secured Mark: " + sgpaSecMark);
								System.out.println("Credit Max Mark: " + creditMaxMark);
								System.out.println("Credit Secured Mark: " + creditSecMark);

								System.out.println("Result: " + status);
								System.out.println("Final Result: " + finalStatus);

								System.out.println("---------------------------------------------------");
								
								
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

									try {
										validateMarks(Regno,"Theory Sec Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, therorySecMark, theroryMaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory internal sec. Marks: " + therorySecMark);							
								}
																	
							try {
										validateMarks(Regno,"SGPA Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, sgpaSecMark, sgpaMaxMark, 0.00, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " SGPA Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "SGPA Sec. Marks: " + therorySecMark);							
								}
					
										
						try {
									validateMarks(Regno,"Credits Sec. Marks", paper1, paper2, paper3,paper4,
									        praticalExam, theoryExam, subject,
									        examTotal, creditSecMark, creditMaxMark, 0.00, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario = testCaseName.createNode(
										  " Credits Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Credits Sec. Marks: " + therorySecMark);							
							}
						
						
						
									try {
										
										paper2Mark = 0.0;
										Paper2 = 0.0;
										
										paper3Mark = 0.0;
										Paper3 = 0.0;
										
										validateMarks(Regno,"Theory plus pratical Sec. Marks", paper1, paper2, paper3,paper4,
										        praticalExam, theoryExam, subject,
										        examTotal, therorySecMark, theroryMaxMark, 0.50, testCaseName);		
								}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory plus pratical Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory plus pratical Sec. Marks: " + therorySecMark);							
								}	

								
							}
								}}
							else if (matcher1.group().contains("BNYS") || matcher1.group().contains("UNANI")
									|| matcher1.group().contains("BAMS") || matcher1.group().contains("BUMS")
									|| matcher1.group().trim().contains("HOMOEOPATHY")
						
									
									) {

								Pattern oneSubjectPattern = Pattern.compile(
										"(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|Pass).*\\n)*"
												+ // Ignore header lines
												"([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" + // Capture subject names,
																										// allowing multi-line
																										// subjects
												"((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" + // First mark (mandatory)
												"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Second mark (optional)
												"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Third mark (optional)
												"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Fourth mark (optional)
												"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Fifth mark (optional)
												"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Sixth mark (optional)
												"(?:-+\\s*)?" + // Handle extra dashes before the final result
												"(Pass|Fail|AP)" // Final result
								);

								Matcher paperMatcher = oneSubjectPattern.matcher(text);		
									
								
								while(paperMatcher.find()) {

								if (matcher1.group().trim().contains("M.D. HOMOEOPATHY"))
								
										
										 {

									System.out.println("PaperMatcher2: " + paperMatcher.group(1));

									String subjectInput = paperMatcher.group(1);

									System.out.println("subjectInput: "+subjectInput);
									
									
									

									String subjectInputSplit[] = subjectInput.split("\\r?\\n");

									//need to debug
									
									System.out.println("subject check"+subjectInputSplit[0]);
									System.out.println("---------------------------------------------------");
									if(!subjectInputSplit[0].contains("Four Hundred Forty Six")&& matcher1.group().trim().contains("UNANI")) {
										
										subject = subjectInputSplit[0];
										System.out.println("Subject Name :"+ subject);
									}
									else if(!subjectInputSplit[0].contains("KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES")&& matcher1.group().trim().contains("M.D. HOMOEOPATHY")) {
									
										
										subject = paperMatcher.group(1).replaceAll("\\s+", " ").trim();
						
										
										System.out.println(subject);
									}
									else {
									
									
										subject = subjectInputSplit[1];

										System.out.println("Subject Name :"+ subject);
									
									}
								}

								else {
									subject = paperMatcher.group(1).replaceAll("\\s+", " ").trim();
									System.out.println(subject);

								}

							String	theoryTotalMaxMarks = paperMatcher.group(2);
							String	theoryTotalSecMarks = paperMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
										? paperMatcher.group(3)
										: paperMatcher.group(3).replaceAll("[^0-9]", "");
							String	practicalVivaMaxMarks = paperMatcher.group(4);
							String	practicalVivaSecMarks = paperMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
										? paperMatcher.group(5)
										: paperMatcher.group(5).replaceAll("[^0-9]", "");

							String	theoryPracticalMaxMarks = paperMatcher.group(6);
							String	theoryPracticalSecMarks = paperMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
										? paperMatcher.group(7)
										: paperMatcher.group(7).replaceAll("[^0-9]", "");
								status = paperMatcher.group(8);

								System.out.println("Subject: " + subject);
								System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
								System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
								System.out.println("Practical Viva Max Marks: " + practicalVivaMaxMarks);
								System.out.println("Practical Viva Secured Marks: " + practicalVivaSecMarks);
								System.out.println("Theory plus Practical Max Marks: " + theoryPracticalMaxMarks);
								System.out.println("Theory plus Practical Secured Marks: " + theoryPracticalSecMarks);
								System.out.println("Status: " + status);

								System.out.println("---------------------------------------------------");
	System.out.println(subject.trim().equals(subjectToFind.trim()));
	
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
								
									
									//Need to set as zero or else it the number will overlap with others
									paper1Mark = 0.0;
									paper2Mark = 0.0;
									paper3Mark = 0.0;
									paper4Mark = 0.0;
									praticalTotalSecMark = 0.0;
									ExamTotalScore = 0.0;
									Paper1 = 0.0;
									Paper2 = 0.0;
									Paper3 = 0.0;
									Paper4 = 0.0;
									PraticalExamTotal = 0.0;
									grandTotal=0.0;
									
									
									
									try {
									validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									       theoryExam, praticalExam,subject,
									        examTotal, theoryTotalSecMarks, theoryTotalMaxMarks, 0.50, testCaseName);
									}
									catch(Exception e) {
										ExtentTest testCaseScenario = testCaseName
												.createNode("Theory Total Sec. Marks "
														+ subject + " Test case has started running");		
										
										
										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "+ theoryTotalSecMarks 
														,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

										
										
									}

									try {
									if(theoryTotalSecMarks.trim().equals("AB") && !practicalVivaSecMarks.trim().equals("NE (AT)") ) {
										
										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical plus viva Sec. Marks Validation for the Subject "
														+ subject + " Test case has started running");		
										
										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "+ theoryTotalSecMarks +" Pratical plus viva Sec is:" + practicalVivaSecMarks
														,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

										System.out.println("\n Please check The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ "of theory internal sec mark "+ theoryTotalSecMarks+" Pratical plus viva Sec is :" + practicalVivaSecMarks);

									}else {
										
										ExtentTest testCaseScenario = testCaseName
												.createNode("Pratical Univ Sec. Marks Validation for the Subject "
														+ subject + " Test case has started running");		
										
										testCaseScenario.log(Status.PASS,
												"The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "+ theoryTotalSecMarks+" Pratical plus viva Sec mark is : " + practicalVivaSecMarks);

										System.out.println(	"The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ "of theory internal sec mark "+ theoryTotalSecMarks+" Pratical plus viva Sec mark is:" + practicalVivaSecMarks);
									try {
										
									
										validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
										         theoryExam,praticalExam, subject,
										        examTotal, practicalVivaSecMarks, practicalVivaMaxMarks, 0.50, testCaseName);
									}//try
									
									catch(Exception e) {

										  testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of Pratical Univ Sec mark is: "+ theoryTotalSecMarks 
															,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				
									}//catch	
									}//else
									}//try
									catch(Exception e) {
										 ExtentTest testCaseScenario = testCaseName.createNode(
												  " Pratical Univ Sec validation for subject " + subjectToFind + " test case has started");
										
											testCaseScenario.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of theory internal sec mark is: "+ theoryTotalSecMarks +" Pratical plus viva Sec is:" + practicalVivaSecMarks
															,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

										  
										  
									}	
									
										
									
							try {
									validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											  theoryExam,praticalExam,subject,
									        examTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);
								
									}
								
								catch(Exception e) {
									  ExtentTest testCaseScenario = testCaseName.createNode(
											  " Theory Plus pratical Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Theory Plus pratical Sec Marks: " + theoryPracticalSecMarks);							
								}
								
								}//if
							}//while
							
							} // else if part
							
						}

						catch (Exception e) {
						
							System.out.println(text);
							
							e.printStackTrace();
					
						}

					}

					if (page == totalPages) {
						break;
					}

				}

			}

			catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			System.out.println("No PDF file found.");
		}

	}

	public void processTwoPatternPdf(File latestFile, Object Regno, Object paper1, Object paper2, Object paper3,Object paper4,
			Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, ExtentTest testCaseName, String subjectToFind) {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");

				
					String courseNameRegex = "(MDS)\\s+";

					Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
					Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

					if (courseNameRegexPatternMatcher.find()) {
						
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.PASS, "MathchFound: " + courseNameRegexPatternMatcher.group(1));

					
					
						System.out.println("MathchFound: " + courseNameRegexPatternMatcher.group(1)); // Prints "MDS"
					}

					else {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subject + " No match found");

						System.out.println("No match found.");
					}
		
				if (courseNameRegexPatternMatcher.group().contains("MDS")) {

							try {
								ExtentTest testCaseScenario = testCaseName.createNode(
										"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

								testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has started running");

								
								
							
							String subjectRegex = "(?:Subject Name:|Subject :)\\s*([A-Z\\s]+)"; // Capturing group added
							Pattern subjectPattern = Pattern.compile(subjectRegex, Pattern.MULTILINE);
							Matcher subjectMatcher = subjectPattern.matcher(text);

							if (subjectMatcher.find()) {
								System.out.println("yes");
								System.out.println("Subject: " + subjectMatcher.group(1)); // Extracted subject nam jf
								subject = subjectMatcher.group(1);

							}
							else {
								System.out.println(text);
							}

							Pattern mdsMaxMinPattern = Pattern.compile(
									"(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");

							Matcher mdsMaxMinPatternMatcher = mdsMaxMinPattern.matcher(text);
							
							String paper1MaxMark = null;
							String paper1MinMark = null;

							String grandTotalMaxMarks = null;
							String grandTotalMinMarks = null;

							while (mdsMaxMinPatternMatcher.find()) {

								System.out.println("PaperMatcher1: " + mdsMaxMinPatternMatcher.find());

								if (mdsMaxMinPatternMatcher.group() != null) {
									System.out.println(mdsMaxMinPatternMatcher.group() + " Max Marks: " + mdsMaxMinPatternMatcher.group(2)
											+ ", Min Marks: " + mdsMaxMinPatternMatcher.group(3));
									paper1MaxMark = mdsMaxMinPatternMatcher.group(4);
									paper1MinMark = mdsMaxMinPatternMatcher.group(5);

									grandTotalMaxMarks = mdsMaxMinPatternMatcher.group(4);
									grandTotalMinMarks = mdsMaxMinPatternMatcher.group(5);

								}							}
							
		
							
							Pattern mdsMarksPattern = Pattern.compile(
								    "(\\d+)\\s*(?:\\(\\s*F\\s*\\))?\\s+(\\d+)\\s*(?:\\(\\s*F\\s*\\))?\\s+(Pass|Fail|AP)"
								);
							Matcher mdsMarksMatcher = mdsMarksPattern.matcher(text);
							
							if(mdsMarksMatcher.find()) {
							
								
								
							String paper1Marks = mdsMarksMatcher.group(1);


							String grandTotalMarks = mdsMarksMatcher.group(2);

							status = mdsMarksMatcher.group(3);
							
							System.out.println("Subject: " + subject);
							System.out.println("Theory Paper1 Max Marks: " + paper1MaxMark);
							System.out.println("Theory Paper1 Min Marks: " + paper1MinMark);
							System.out.println("Theory Paper1 Obtained Marks: " + paper1Marks);

							System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
							System.out.println("Grand Total Min Marks: " + grandTotalMinMarks);
							System.out.println("Grand Total Obtained Marks: " + grandTotalMarks);

							System.out.println("Result: " + status);
							
							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
							try {
									validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,
											grandTotal, paper1Marks, paper1MaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  " Paper1 Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ " Paper1 Sec Marks " + paper1Marks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

													
							}
								
						    try {
								validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,
										grandTotal, paper1Marks, paper1MaxMark, 0.50, testCaseName);		
						}
						
						catch(Exception e) {
							  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
									  "Theory Total Sec Marks validation for subject " + subjectToFind + " test case has started");
							  testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ "Theory Total Sec Marks"  + grandTotalMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
						}
						    try {
									validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,
											grandTotal, paper1Marks, paper1MaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + grandTotalMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
							}
							} // if bracket
						}
						}
							catch (Exception e) {
								ExtentTest testCaseScenario = testCaseName.createNode(
										"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

								testCaseScenario.log(Status.FAIL, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has fail to started running" 	,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
								testCaseScenario.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

								System.out.println(text);
							}
					}//MDS IF
						else if(courseNameRegexPatternMatcher.group().contains("POST DOCTORAL FELLOWSHIP")) {
							
							System.out.println("yes");
							
							
							
						}
					

					if (page == totalPages) {
						break;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No PDF file found.");
		}
	}

	public void processPdfCourseNamePatternPdf(File latestFile, Object Regno, Object paper1, Object paper2,
			Object paper3,Object paper4, Object theoryExam, Object praticalExam, Object examTotal, ExtentTest testCaseName,
			String subjectToFind) {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();
				System.out.println("Total Pages: " + totalPages);
				System.out.println("---------------------------------------------------");

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");

					String courseNameRegex = "(POST DOCTORAL FELLOWSHIP)\\s+";

					Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
					Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

					if (courseNameRegexPatternMatcher.find()) {
						
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.PASS, "MathchFound: " + courseNameRegexPatternMatcher.group(1));

					
					
						System.out.println("MathchFound: " + courseNameRegexPatternMatcher.group(1)); // Prints "MDS"
					}

					else {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subject + " No match found");

						System.out.println("No match found.");
					}
					
					
					if (courseNameRegexPatternMatcher.group().contains("POST DOCTORAL FELLOWSHIP")) {
						
						
						String subjectRegex = "(?:Subject Name:|Subject :)\\s*([A-Z\\s]+)"; // Capturing group added
						Pattern subjectPattern = Pattern.compile(subjectRegex, Pattern.MULTILINE);
						Matcher subjectMatcher = subjectPattern.matcher(text);

						if (subjectMatcher.find()) {
							System.out.println("yes");
							System.out.println("Subject: " + subjectMatcher.group(1)); // Extracted subject nam jf
							String[] subjects = subjectMatcher.group(1).split("\n");

							subject = subjects[0];

							System.out.println(subject);

						}
						

						Pattern pdfMarkPattern = Pattern
								.compile("(?i)(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(Pass|Fail)");

						Matcher pdfMarkPatternMatcher = pdfMarkPattern.matcher(text);

						
						
						
						if (pdfMarkPatternMatcher.find()) {
						
						String paper1Mark = pdfMarkPatternMatcher.group(1);
						String paper2Mark = pdfMarkPatternMatcher.group(2);
						String totalMark = pdfMarkPatternMatcher.group(3);
						String praticalClinicalAndVivaMark = pdfMarkPatternMatcher.group(4);
						String grandTotal = pdfMarkPatternMatcher.group(5);
						status = pdfMarkPatternMatcher.group(6);

						System.out.println("Match found!");

						System.out.println("Subject :" + subject);
						System.out.println("Paper 1: " + paper1Mark);
						System.out.println("Paper 2: " + paper2Mark);
						System.out.println("Total : " + totalMark);
						System.out.println(
								"Pratical Clinical and Viva voce Mark : " + praticalClinicalAndVivaMark);
						System.out.println("Group Total: " + grandTotal);
						System.out.println("Status " + status);

						pdfMarkValidation(Regno, subject, paper1Mark, paper2Mark, totalMark, praticalClinicalAndVivaMark,
								grandTotal, testCaseName);

					} else {
						ExtentTest testCaseScenario = testCaseName
								.createNode("Pattern validation for the following " + Regno
										+ " Test case has started running");

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number "
								+ Regno + " for the subject " + subjectToFind + " No match found");

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subject + " No match found");

						System.out.println("No match found.");
					}

						
				

					} else {
						System.out.println("Marks Result not found.");

						System.out.println("---------------------------------------------------");
					}
					if (page == totalPages) {
						break;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No PDF file found.");
		}
	}

	// Method to check if a student passed or failed in Paper 1
	public void checkPaper1Result(Object regno, Object paper1, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {
			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Paper1 Validation Test case has started running");
				paper1MinMark = minMark;
				Paper1 = objectToDataType(paper1);
				System.out.println(Paper1);

				try {
					if (paper1Mark == Paper1) {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark
								+ " for the following Register " + regno + " number data are same for paper 1 mark");
						testCaseScenario.log(Status.PASS, "Both Excel " + Paper1 + " and Pdf " + paper1Mark
								+ " for the following Register " + regno + " number data are same for paper 1 mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file for paper 1 mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same for paper 1 mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (paper1Mark < paper1MinMark && Paper1 < paper1MinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Paper1 exam with marks: " + paper1Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Paper1 exam with marks: " + paper1Mark);

					} else if (paper1Mark >= paper1MinMark && Paper1 >= paper1MinMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Paper1 exam with marks: " + paper1Mark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in Paper1 exam with marks: " + paper1Mark);

					} else {
						System.out.println("Pdf mark is " + paper1Mark + " Excel mark is " + Paper1);
						testCaseScenario.log(Status.FAIL, "Pdf mark is " + paper1Mark + " Excel mark is " + Paper1
								+ " for the following " + regno + " number");

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
			} else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Method to check if a student passed or failed in Paper 2
	public void checkPaper2Result(Object regno, Object paper2, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {

			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Paper2 Validation Test case has started running");

				Paper2 = objectToDataType(paper2);
				paper2MinMark = minMark;
				try {
					if (paper2Mark == Paper2) {
						System.out.println("Both Excel " + Paper2 + " and Pdf " + paper2Mark
								+ " for the following Register " + regno + " number data are same for paper 2 mark");
						testCaseScenario.log(Status.PASS, "Both Excel " + Paper2 + " and Pdf " + paper2Mark
								+ " for the following Register " + regno + " number data are same for paper 2 mark");

					}

					else {
						System.out.println("Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file for paper 1 mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following " + regno
										+ " number data are not same for paper 2 mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (paper2Mark == Paper2 && paper2Mark < paper2MinMark && Paper2 < paper2MinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Paper2 exam with marks: " + paper2Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Paper2 exam with marks: " + paper2Mark);

					} else if (paper2Mark == Paper2 && paper2Mark >= paper2MinMark && Paper2 >= paper2MinMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Paper2 exam with marks: " + paper2Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ "is passed in Paper2 exam with marks:" + paper2Mark);

					} else {
						System.out.println("Pdf mark is " + paper2Mark + " Excel mark is" + Paper2);
						testCaseScenario.log(Status.FAIL, "Pdf mark is " + paper2Mark + " Excel mark is" + Paper2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
			} else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Method to check if a student passed or failed in Paper 3
	
	// Method to check if a student passed or failed in the Theory Exam (Paper1 +
	// Paper2 + Paper3)
	public void TheoryTotalSecMarksValidation(Object regno,String markName, String obtainedMarks,Object theoryExam, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {

			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Therory Exam Toal Result Validation Test case has started running");
				theroryExamTotalMinMark = minMark;
				TheroryExamTotal = Paper1 + Paper2 + Paper3 + Paper4;

				theoryTotal = paper1Mark + paper2Mark + paper3Mark +paper4Mark;
				System.out.println(paper1Mark);
				System.out.println(paper2Mark);
				System.out.println(paper3Mark);
				System.out.println(paper4Mark);

				System.out.println(obtainedMarks);
				
				Double theroryExamTotal = objectToDataType(obtainedMarks);

				System.out.println(theoryTotal);

				try {
					if (theoryTotal == theroryExamTotal && theoryTotal == TheroryExamTotal) {

						System.out.println("Both Excel " + theroryExamTotal + " and Pdf " + theoryTotal
								+ " for the following Register " + regno
								+ " number data are same for theory total mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + theroryExamTotal + " and Pdf " + theoryTotal
										+ " for the following Register " + regno
										+ " number data are same for theory total mark");

					}

					else {

						System.out.println("Both Excel " + theroryExamTotal + " and Pdf " + theoryTotal
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for theory total mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + theroryExamTotal + " and Pdf " + theoryTotal + " for the following "
										+ regno + " number data are not same for theory total mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}

				try {

					if (paper1Mark < paper1MinMark || paper2Mark < paper2MinMark || paper3Mark < paper3MinMark
							|| Paper1 < paper1MinMark || Paper2 < paper2MinMark || Paper3 < paper3MinMark||Paper4 < paper4MinMark) {
						System.out.println("The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the Theory exam:"
								+ theoryTotal);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the Theory exam:"
								+ theoryTotal);

					}

					else if (theoryTotal < theroryExamTotalMinMark && TheroryExamTotal < theroryExamTotalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Theory Exam with total marks: " + theoryTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Theory Exam with total marks: " + theoryTotal);

					} else if (theoryTotal >= theroryExamTotalMinMark && TheroryExamTotal >= theroryExamTotalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Theory Exam with total marks: " + theoryTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in Theory Exam with total marks: " + theoryTotal);
					}

					else {
						System.out.println("Pdf mark is " + theoryTotal + " Excel mark is" + TheroryExamTotal);
						testCaseScenario.log(Status.FAIL,
								"Pdf mark is " + theoryTotal + " Excel mark is" + TheroryExamTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

			} else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Method to check if a student passed or failed in the Practical Exam
	public void checkPraticalExamResult(Object regno, Object praticalExam, ExtentTest testCaseName,
			String subjectToFind) throws IOException {

		try {

			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Pratical Exam Total Result Validation Test case has started running");

				PraticalExamTotal = objectToDataType(praticalExam);
				praticalMinMark = minMark;
				try {
					if (praticalTotalSecMark == PraticalExamTotal) {

						System.out.println("Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
								+ " for the following Register " + regno
								+ " number data are same for pratical total mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following Register " + regno
										+ " number data are same for pratical total mark");

					}

					else {

						System.out.println("Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for pratical total mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following " + regno
										+ " number data are not same for pratical total mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {

					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (praticalTotalSecMark < praticalMinMark && PraticalExamTotal < praticalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Practical Exam with marks: " + praticalTotalSecMark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Practical Exam with marks: " + praticalTotalSecMark);
					} else if (praticalTotalSecMark >= praticalMinMark && PraticalExamTotal >= praticalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Practical Exam with marks: " + praticalTotalSecMark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in Practical Exam with marks: " + praticalTotalSecMark);
					} else {
						System.out
								.println("Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
						testCaseScenario.log(Status.FAIL,
								"Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
				} catch (Exception e) {

					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}

			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Method to check the final result (Theory Exam + Practical Exam)
	public void checkFinalExamResult(Object regno, Object examTotal, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {

			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Grand Total Exam Result Validation Test case has started running");
				// Calculate total score (Theory + Practical)
				ExamTotalScore = TheroryExamTotal + PraticalExamTotal;

				System.out.println("TheroryExamTotal " + TheroryExamTotal);
				System.out.println("PraticalExamTotal" + PraticalExamTotal);

				System.out.println("ExamTotalScore " + ExamTotalScore);

				grandTotal = theoryTotal + praticalTotalSecMark;
				System.out.println("theoryTotal " + theoryTotal);
				System.out.println("praticalTotalSecMark " + praticalTotalSecMark);

				System.out.println("grandTotal " + grandTotal);

				double grandTotalScore = objectToDataType(examTotal);

				System.out.println(examTotal);

				try {
					if (grandTotal == ExamTotalScore) {

						System.out.println("Both Excel " + grandTotalScore + " and Pdf " + grandTotal
								+ " for the following Register " + regno
								+ " number data are same for grand total mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + grandTotalScore + " and Pdf " + grandTotal
										+ " for the following Register " + regno
										+ " number data are same for grand total mark");
					}

					else {

						System.out.println("Both Excel " + ExamTotalScore + " and Pdf " + grandTotal
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for grand total mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + ExamTotalScore + " and Pdf " + grandTotal + " for the following "
										+ regno + " number data are not same for grand total mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (paper1Mark < paper1MinMark || paper2Mark < paper2MinMark || paper3Mark < paper3MinMark
							|| Paper1 < paper1MinMark || Paper2 < paper2MinMark || Paper3 < paper3MinMark
							|| PraticalExamTotal < praticalMinMark || praticalTotalSecMark < praticalMinMark
							|| grandTotal < grandTotalMinMark || grandTotal < grandTotalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the final exam:"
								+ grandTotal);

						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno
										+ " has failed in one or more papers and is therefore failed in the final exam:"
										+ grandTotal);

					}
					// If total marks are above 300 but failed in individual papers, still fail
					else if (grandTotal < grandTotalMinMark && ExamTotalScore < grandTotalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed with total score: " + grandTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with total score: " + grandTotal);

					} else if (grandTotal >= grandTotalMinMark && ExamTotalScore >= grandTotalMinMark) {

						System.out.println("The following Registration number " + regno
								+ " is passed with total score: " + grandTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed with total score: " + grandTotal);

					} else {
						System.out.println("Pdf mark is " + grandTotal + " Excel mark is" + ExamTotalScore);
						testCaseScenario.log(Status.FAIL,
								"Pdf mark is " + grandTotal + " Excel mark is" + ExamTotalScore,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {

					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
			} else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void processPdfBasedOnCoursePattern(File latestFile, Object Regno, Object paper1, Object paper2,
			Object paper3,Object paper4, Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, String subjectToFind,
			ExtentTest testCaseName) {
		
		ExtentTest testCaseScenario = testCaseName.createNode("Processing Pdf Based On Subject Pattern");
		
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String pdfText = stripper.getText(document);

					// System.out.println(pdfText);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");

			
					Pattern bhms22Pattern = Pattern.compile("^([\\s\\S]*?)\\s+" + // 1. Subject Name
							"(HomUG-[A-Z]+(?:-[A-Z]+)?)\\s+" + // 2. Subject Code
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 3. Mark 1
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 4. Mark 2
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 5. Mark 3
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 6. Mark 4
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 7. Mark 5
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 8. Mark 6
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 9. Mark 7
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 10. Mark 8
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 11. Mark 9
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 12. Mark 10
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 13. Mark 11
							"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)" + // 14. Mark 12
							"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 15. Mark 13 (optional)
							"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 16. Mark 14 (optional)
							"(?:\\s+([A-Z]))?" + // 17. Optional grade letter
							"\\s+(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // 18. Final Result
							Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

					Matcher bhms22PatternnMatcher = bhms22Pattern.matcher(text);

					Pattern eightSubjectPattern = Pattern.compile(
						    "^(.*?)\\s+" + // Subject name (multi-word, greedy)
						    
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Theory Internal Max
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Theory Internal Sec
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Theory Univ Max
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Theory Univ Sec
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Practical Internal Max
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Practical Internal Sec
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Practical Univ Max
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Practical Univ Sec
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Total Max
						    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?)(?:\\s*\\(\\s*F\\s*\\))?\\s+" + // Total Sec

						    "(AP|Pass(?:\\(G\\))?|Fail|First Class|Distinction|AB|NE|NR|NE\\s*\\(?\\s*AT\\s*\\)?|---)$", // Status
						    Pattern.MULTILINE
						);

					Matcher eightSubjectPatternMatcher = eightSubjectPattern.matcher(text);

					Pattern fourPattern = Pattern.compile("^((?:.*?)\\s+)?" + // Optional subject (0 or 1 occurrence of
																				// subject name followed by space)
							"((?:\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s*\\(\\s*F\\s*\\))?\\s+){6,7}"	+																		// fields
							"((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"+																	
							// Optional total marks field (e.g., 100 or 55)
							"(AP|Pass|Fail|AB|NE|NR|NE\\(AT\\)|---)$", // Status
							Pattern.MULTILINE);
					Matcher fourPatternMatcher = fourPattern.matcher(text);

					// Check for a match with the "three" pattern first
					Pattern oneSubjectPattern = Pattern.compile("(\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+)"
						    + "((?:\\d+|---)(?:\\s*\\(\\s*F\\s*\\))?\\s+){6,7}"
						    + "(Pass|Fail|AP)");


					Matcher oneSubjectPatternMatcher = oneSubjectPattern.matcher(text);

					Pattern thirdSubjectPattern = Pattern.compile("([A-Z &]+)\\s+" // Subject Name
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // First number or ---
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Second number or ---
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Third number or ---
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Fourth number or ---
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Fifth number or ---
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Sixth number or ---
							+ "(Pass|Fail|AP)"); // Final status
					Matcher thirdSubjectPatternMatcher = thirdSubjectPattern.matcher(text);

					
					Pattern twoPattern = Pattern.compile("(\\d+\\s*\\(\\s*F\\s*\\)|\\d+)\\s+(\\d+\\s*\\(\\s*F\\s*\\)|\\d+)\\s+(Fail|Pass|AP)");

					
					Matcher twoPatternMatcher = twoPattern.matcher(text);
		
					
					
					Pattern bscNursingPattern = Pattern.compile("^([\\w &]+ \\d+ \\(Theory\\))\\s+" + // Capture subject																					// "(Theory)"
							"((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" + // Capture first column (e.g., 25 or special values)
							"((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" + // Capture second column (e.g., 19 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)\\s+" + // Capture third column (e.g., 75 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)\\s+" + // Capture fourth column (e.g., 51 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)\\s+" + // Capture fifth column (e.g., 100 or special values)
							"((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" + // Capture sixth column (e.g., 70 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)?\\s+" + // Capture seventh column (e.g., 70 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)?\\s+" + // Capture eighth column (e.g., B+ or special values)
							"((?:\\w\\+?|NE|NR|NA|NEAT)?)?\\s+" + // Capture ninth column (e.g., 7 or special values)
							"((?:\\d+|NE|NR|NA|NEAT)?)?$" // Capture last column (optional, e.g., 7 or special values)
					);

					Matcher bscNursingPatternMatcher = bscNursingPattern.matcher(text);

					Pattern pdfPattern = Pattern
							.compile("(?i)(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(Pass|Fail)");

					Matcher pdfPatternMatcher = pdfPattern.matcher(text);

					if (bhms22PatternnMatcher.find()) {

						System.out.println("BHMS22 Reg_Pattern detected");
						
						testCaseScenario.log(Status.INFO, "BHMS22 Reg_Pattern detected");
						processBHMSRegulation22SubjectPatternPdf(Regno, latestFile, paper1, paper2, paper3,paper4,
								theoryExamTotal, practicalExamTotal, grandTotal, subjectToFind, testCaseName);
						break;
						
					}

					else if (eightSubjectPatternMatcher.find()) {

						System.out.println("Pattern matched: 8 subject pattern detected.");
						testCaseScenario.log(Status.INFO,"Pattern matched: 8 subject pattern detected.");
						processEightSubjectPatternPdf(Regno, latestFile, paper1, paper2, paper3,paper4, theoryExamTotal,
								practicalExamTotal, grandTotal, subjectToFind, testCaseName);
						// processFourPatternValidation(Regno,paper1,paper2,paper3,theroryExam,praticalExam,examTotal,testCaseName);
						// Exit once the matching method is called
						return;

					}

					else if (fourPatternMatcher.find()) {

						System.out.println("Pattern matched: Four patterns detected.");
						System.out.println("Matched by FourPattern: " + fourPatternMatcher.group());
						testCaseScenario.log(Status.INFO,"Matched by FourPattern: " + fourPatternMatcher.group());
						processFourSubjectPatternPdf(Regno, latestFile, paper1, paper2, paper3,paper4,theoryExamTotal, practicalExamTotal,
								grandTotal, subjectToFind, testCaseName);
						// Exit once the matching method is called

						return;

					}

					else if (oneSubjectPatternMatcher.find()) {
						// If it matches, call the method for oneSubject patterns

						System.out.println("Pattern matched: 1 subject pattern detected.");
						testCaseScenario.log(Status.INFO,"Pattern matched: 1 subject pattern detected.");
						processOneSubjectPaternPdf(latestFile, Regno, paper1, paper2, paper3,paper4, theoryExamTotal, practicalExamTotal,
								grandTotal, testCaseName, subjectToFind);

						// Exit once the matching method is called
						return;
					}
					// Otherwise, check for the "two" pattern
					else if (thirdSubjectPatternMatcher.find()) {
						System.out.println("Pattern matched: Third patterns detected.");
						testCaseScenario.log(Status.INFO,"Pattern matched: Third patterns detected.");
						processOneSubjectPaternPdf(latestFile, Regno, paper1, paper2, paper3,paper4, theoryExamTotal, practicalExamTotal,
								grandTotal, testCaseName, subjectToFind);

						// Exit once the matching method is called
						return;

					}

					else if (twoPatternMatcher.find()) {
						// If it matches, call the method for two patterns
						System.out.println("Pattern matched: Two patterns detected.");
						testCaseScenario.log(Status.INFO,"Pattern matched: Two patterns detected.");
//					
						KnrReportEnrollmentPageCourseNameValidation  CourseNameValidation = new KnrReportEnrollmentPageCourseNameValidation();

						CourseNameValidation.processMDSPattern(latestFile, Regno, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						//						processTwoPatternPdf(latestFile, Regno, paper1, paper2, paper3,paper4, theoryExamTotal, practicalExamTotal,
//								grandTotal, testCaseName, subjectToFind);
						// Exit once the matching method is called
						return;
					}

					else if (bscNursingPatternMatcher.find()) {

						System.out.println("Pattern matched: Bsc nusing patterns detected.");

					}

					else if (pdfPatternMatcher.find()) {
						System.out.println("New PDF pattern find and dected ");
						processPdfCourseNamePatternPdf(latestFile, Regno, paper1, paper2, paper3,paper4, theoryExamTotal,
								practicalExamTotal, grandTotal, testCaseName, subjectToFind);

					}

					else {

						System.out.println("New pattern dected");
						testCaseScenario.log(Status.FAIL,"New pattern dected");
					}

				}
			} catch (Exception e) {
			}
		} else {
			System.out.println("No PDF file found.");
		}
	}

	public void gracePaper1Check(Object regno,String markName,Object paper1,String subjectToFind,String marks, ExtentTest testCaseName ) {
		
//		gracecheck(regno,"Theory Internal Sec Marks",paper1, grace, subjectToFind,
//				theoryInternalMaxMark, testCaseName);
		try {

			if (subject.trim().equals(subjectToFind.trim())) {

				paper1MinMark = minMark;

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " for the Subject " + subjectToFind + " Validation Test case has started running");

				Paper1 = objectToDataType(paper1);

				try {
					if (paper1Mark == Paper1) {
						System.out.println("Both " + Paper1 + " and " + paper1Mark + " for the following Register "
								+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS, "Both " + Paper1 + " and " + paper1Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same marks",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {
					

					System.out.println("marks: " + marks);
					
					if(((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) || marks.contains("G")) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with grace marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with grace marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					}
					
					else if ((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) {
						System.out.println(" The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else if ((paper1Mark >= paper1MinMark) && (Paper1 >= paper1MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper1Mark + " Excel mark is" + Paper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
	
					
				}
				catch(Exception e) {
					
					
				}
			}
		}//try
		catch(Exception e) {
			
		}
	}
	public void Paper1SecMarksValidation(Object regno, String markName,String obtainedMarks, Object paper1, String subjectToFind,
			ExtentTest testCaseScenario1) throws IOException {

		try {
		
			if (subject.trim().equals(subjectToFind.trim())) {

				paper1MinMark = minMark;

				ExtentTest testCaseScenario = testCaseScenario1.createNode(
						markName + " for the Subject " + subjectToFind + " Validation Test case has started running");

				Paper1 = objectToDataType(paper1);
				paper1Mark =objectToDataType(obtainedMarks);

				try {
					if (paper1Mark == Paper1) {
						System.out.println("Both " + Paper1 + " and " + paper1Mark + " for the following Register "
								+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS, "Both " + Paper1 + " and " + paper1Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same marks",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) {
						System.out.println(" The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else if ((paper1Mark >= paper1MinMark) && (Paper1 >= paper1MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper1Mark + " Excel mark is" + Paper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
			}
		}

		catch (Exception e) {
			System.out.println(
					"Check the files for the following " + regno + " registration number and subject" + subjectToFind);

		}

	}

// Method to check if a student passed or failed in Paper 2
	public void Paper2SecMarksValidation(Object regno, String markName,String obtainedMarks, Object paper2, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.trim().equals(subjectToFind.trim())) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				Paper2 = objectToDataType(paper2);
				paper2Mark = Double.parseDouble(obtainedMarks);
				paper2MinMark = minMark;

				try {
					if (paper2Mark == Paper2) {

						System.out.println(
								"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following Register "
										+ regno + " number data are same for Theory Univ Sec Marks");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following Register "
										+ regno + " number data are same for Theory Univ Sec mark");

					}

					else {
						System.out.println("Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following "
								+ regno
								+ " data are not same please check Excel file or Pdf file for Theory Univ Sec mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following " + regno
										+ " number data are not same for Theory Univ Sec mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((paper2Mark < paper2MinMark) && (Paper2 < paper2MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

					} else if ((paper2Mark >= paper2MinMark) && (Paper2 >= paper2MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ "is passed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper2Mark + " Excel mark is" + Paper2);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper2Mark + " Excel mark is" + Paper2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void Paper3SecMarksValidation(Object regno, String markName,String obtainedMarks, Object paper3, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.trim().equals(subjectToFind.trim())) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				paper3MinMark = minMark;

				Paper3 = objectToDataType(paper3);
				paper3Mark = Double.parseDouble(obtainedMarks);

				System.out.println("paper3MinMark: " + paper3MinMark);

				try {
					if (paper3Mark == Paper3) {

						System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark
								+ " for the following Register " + regno + " number data are same Marks");
						testCaseScenario.log(Status.PASS, "Both Excel " + Paper3 + " and Pdf " + paper3Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following "
								+ regno + " data are not same mark please check Excel file or Pdf file ");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following " + regno
										+ " number data are not same mark ",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((paper3Mark < paper3MinMark) && (Paper3 < paper3MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

					} else if ((paper3Mark >= paper3MinMark) && (Paper3 >= paper3MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper3Mark + " Excel mark is" + Paper3);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper3Mark + " Excel mark is" + Paper3,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	

	public void Paper4SecMarksValidation(Object regno, String markName,String obtainedMarks, Object paper4, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.trim().equals(subjectToFind.trim())) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				paper4MinMark = minMark;

				Paper4 = objectToDataType(paper4);
				paper4Mark = Double.parseDouble(obtainedMarks);

				System.out.println("paper3MinMark: " + paper3MinMark);

				try {
					if (paper4Mark == Paper4) {

						System.out.println("Both Excel " + Paper4 + " and Pdf " + paper4Mark
								+ " for the following Register " + regno + " number data are same Marks");
						testCaseScenario.log(Status.PASS, "Both Excel " + Paper4 + " and Pdf " + paper4Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper4 + " and Pdf " + paper4Mark + " for the following "
								+ regno + " data are not same mark please check Excel file or Pdf file ");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper4 + " and Pdf " + paper4Mark + " for the following " + regno
										+ " number data are not same mark ",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((paper4Mark < paper4MinMark) && (Paper4 < paper4MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

					} else if ((paper4Mark >=  paper4MinMark) && (Paper4 >= paper4MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper4MinMark + " Excel mark is" + Paper4);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper4MinMark + " Excel mark is" + Paper4,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void praticalTotalSecMarksValidations(Object regno, String markName,String marks, Object praticalExam, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.equals(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation  for the Subject " + subjectToFind + " Test case has started running");

				practicalMinMark = minMark;
				System.out.println(minMark);

				PraticalExamTotal = objectToDataType(praticalExam);
				praticalTotalSecMark = Double.parseDouble(marks);
				
				System.out.println(PraticalExamTotal);
				
				System.out.println(praticalTotalSecMark);
				
				try {
					if (praticalTotalSecMark == PraticalExamTotal) {

						System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
								+ " for the following Register " + regno
								+ " number data are same for pratical sec mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following Register " + regno
										+ " number data are same for pratical sec mark");

					}

					else {

						System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for pratical sec mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following " + regno
										+ " number data are not same for pratical sec mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number where Pdf mark is "
									+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number where Pdf mark is "
									+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((praticalTotalSecMark < practicalMinMark) && (PraticalExamTotal < practicalMinMark)) {
						System.out.println(
								"The following Registration number " + regno + " is failed in Exam with marks in PDF: "
										+ praticalTotalSecMark + " and in Excel: " + PraticalExamTotal);
						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno + " is failed in Exam with marks in PDF: "
										+ praticalTotalSecMark + " and in Excel: " + PraticalExamTotal);

					} else if ((praticalTotalSecMark >= practicalMinMark) && (PraticalExamTotal >= minMark)) {
						System.out.println(
								"The following Registration number " + regno + " is passed in Exam with marks in PDF: "
										+ praticalTotalSecMark + "and in Excel: " + PraticalExamTotal);
						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno + " is passed in Exam with marks in PDF: "
										+ praticalTotalSecMark + "and in Excel: " + PraticalExamTotal);
					} else {
						System.out.println(
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {

					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void graceTheoryPlusPracticalSecMarksCheck(Object regno,String marksName,Object theroryExam,String subjectToFind,String marks, ExtentTest testCaseName ) {
		try {
			if (subject.trim().equals(subjectToFind.trim())) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						marksName + " Validation for the Subject" + subjectToFind + "Test case has started running");

				examTotalScoreMinMark = minMark;
				System.out.println(minMark);

				System.out.println(minMark);

				theoryTotal = objectToDataType(theroryExam);

				System.out.println(theoryTotal);
				try {
					if (theoryTotal == ExamTotalScore) {

						System.out.println("Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
								+ " for the following Register " + regno
								+ " number data are same for Theory Plus Practical Sec Mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
										+ " for the following Register " + regno
										+ " number data are same for  Theory Plus Practical Sec Mark");
					}

					else {

						System.out.println("Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for Theory Plus Practical Sec Mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore + " for the following "
										+ regno + " number data are not same for Theory Plus Practical Sec Mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {
					System.out.println("paper1Mark:" + paper1Mark);
					System.out.println("paper2Mark:" + paper2Mark);
					System.out.println("paper3Mark:" + paper3Mark);
					System.out.println("praticalTotalSecMark:" + praticalTotalSecMark);

					System.out.println("paper1MinMark: " + paper1MinMark);
					System.out.println("paper2 MinMark: " + paper2MinMark);
					System.out.println("paper3 MinMark: " + paper3MinMark);
					System.out.println("PraticalMinMark: " + practicalMinMark);

					if (
						    ((Paper1 != 0 && Paper1 < paper1MinMark) || (paper1Mark != 0 && paper1Mark < paper1MinMark)) ||
						    ((Paper2 != 0 && Paper2 < paper2MinMark) || (paper2Mark != 0 && paper2Mark < paper2MinMark)) ||
						    ((Paper3 != 0 && Paper3 < paper3MinMark) || (paper3Mark != 0 && paper3Mark < paper3MinMark)) ||
						    ((PraticalExamTotal != 0 && PraticalExamTotal < practicalMinMark) || (praticalTotalSecMark != 0 && praticalTotalSecMark < practicalMinMark)) ||
						    marks.contains("G")
						) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with grace marks of "+ paper3Mark +"  in PDF: " + ExamTotalScore + " and in Excel: " + theoryTotal);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with grace marks of "+ paper3Mark +" in PDF: " + ExamTotalScore + " and in Excel: " + theoryTotal);

						}
					
					
					else if (Paper1 != 0 && Paper1 < paper1MinMark || paper1Mark != 0 && paper1Mark < paper1MinMark
							|| Paper2 != 0 && Paper2 < paper2MinMark || paper2Mark != 0 && paper2Mark < paper2MinMark
							|| Paper3 != 0 && Paper3 < paper3MinMark || paper3Mark != 0 && paper3Mark < paper3MinMark
							|| PraticalExamTotal != 0 && PraticalExamTotal < practicalMinMark
							|| praticalTotalSecMark != 0 && praticalTotalSecMark < practicalMinMark) {

						System.out.println("The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the Theory exam marks in PDF: "
								+ ExamTotalScore + " and in Excel: " + theoryTotal);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the Theory exam marks in PDF: "
								+ ExamTotalScore + " and in Excel: " + theoryTotal);

					}

					else if ((theoryTotal < examTotalScoreMinMark) && (ExamTotalScore < examTotalScoreMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Exam with total marks in PDF: " + ExamTotalScore + " and in Excel: "
								+ theoryTotal);

						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno
										+ " is failed in Exam with total marks in PDF: " + ExamTotalScore
										+ " and in Excel: " + theoryTotal);

					} else if ((theoryTotal >= examTotalScoreMinMark) && (ExamTotalScore >= examTotalScoreMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Exam with total marks in PDF: " + ExamTotalScore + " and in Excel: "
								+ theoryTotal);
						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno
										+ " is passed in Exam with total marks in PDF: " + ExamTotalScore
										+ " and in Excel: " + theoryTotal);
					}

					else {
						System.out.println(
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ ExamTotalScore + " Excel mark is" + theoryTotal);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ ExamTotalScore + " Excel mark is" + theoryTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				grandTotalMarksValidation(regno, marksName, theroryExam, subjectToFind, testCaseName);

			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}
	// Method to check the final result (Theory Exam + Practical Exam)
	public void securedMarks(Object regno,String obtainedMarks, Object examTotal, ExtentTest testCaseName) throws IOException {

		ExtentTest testCaseScenario = testCaseName
				.createNode("Secured Marks Result Validation Test case has started running");

		grandTotal = objectToDataType(examTotal);
		securedMark = objectToDataType(obtainedMarks);

		System.out.println();
		
		try {
			if (grandTotal == securedMark) {

				System.out.println("Both Excel " + grandTotal + " and Pdf " + securedMark
						+ " for the following Register " + regno + " number data are same mark ");
				testCaseScenario.log(Status.PASS, "Both Excel " + grandTotal + " and Pdf " + securedMark
						+ " for the following Register " + regno + " number data are same mark ");
			}

			else {

				System.out.println("Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following "
						+ regno + " data are not same mark please check Excel file or Pdf file ");
				testCaseScenario.log(Status.FAIL,
						"Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following Register " + regno
								+ " number data are not same mark ",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}

		} catch (Exception e) {
			System.out.println("Check the files for the following " + regno + " registration number where Pdf mark is "
					+ securedMark + " Excel mark is" + grandTotal);
			testCaseScenario.log(Status.FAIL,
					"Check the files for the following " + regno + " registration number where Pdf mark is "
							+ securedMark + " Excel mark is" + grandTotal,
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}

	}

	// Method to check the final result (Theory Exam + Practical Exam)
	public void grandTotalMarksValidation(Object regno, String marksName, Object theroryExam, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.trim().equals(subjectToFind.trim())) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						"Grand Total Validation for the Subject " + subjectToFind + " Test case has started running");

				minMark = grandTotalMaxMark * 0.5;

				grandTotal = paper1Mark + paper2Mark + paper3Mark +paper4Mark +praticalTotalSecMark;

				TheroryExamTotal = Paper1 + Paper2 + Paper3 + Paper4+ PraticalExamTotal;

				System.out.println("Paper1Mark:" + paper1Mark);
				System.out.println("Paper2Mark:" + paper2Mark);
				System.out.println("Paper3Mark:" + paper3Mark);
				System.out.println("Paper4Mark:" + paper4Mark);
				System.out.println("Pratical total mark:" + praticalTotalSecMark);
				System.out.println("grandTotal:" + grandTotal);

				System.out.println("Paper1:" + Paper1);
				System.out.println("Paper2:" + Paper2);
				System.out.println("Paper3:" + Paper3);
				System.out.println("Paper4:" + Paper4);
				System.out.println("PraticalExamTotal" + PraticalExamTotal);
				System.out.println("TheoryExam:" + TheroryExamTotal);

				theoryTotal = objectToDataType(theroryExam);

				try {
					if (theoryTotal == grandTotal) {

						System.out.println("\n Both PDF file total value " + grandTotal
								+ " and Excel file total value  " + theoryTotal + " for the following Register " + regno
								+ " number data are same mark in PDF file");
						testCaseScenario.log(Status.PASS,
								"\n Both PDF file total value " + grandTotal + " and Excel file total value  "
										+ theoryTotal + " for the following Register " + regno
										+ " number data are same mark in PDF file");
					}

					else {

						System.out.println("\n Both PDF file total value " + grandTotal
								+ " and Excel file total value  " + theoryTotal + " for the following Register " + regno
								+ " number data are not same mark in PDF file");
						testCaseScenario.log(Status.FAIL,
								"\n Both PDF file total value " + grandTotal + " and Excel file total value  "
										+ theoryTotal + " for the following Register " + regno
										+ " number data are not same mark in PDF file",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (theoryTotal == TheroryExamTotal) {

						System.out.println("Both Excel file total value " + TheroryExamTotal
								+ " and PDF file total value  " + grandTotal + " for the following Register " + regno
								+ " number data are same mark in Excel file");
						testCaseScenario.log(Status.PASS,
								"Both Excel file total value " + TheroryExamTotal + " and PDF file total value  "
										+ grandTotal + " for the following Register " + regno
										+ " number data are same mark in Excel file");
					}

					else {

						System.out.println("Both Excel total value " + TheroryExamTotal + " and PDF file total value  "
								+ grandTotal + " for the following Register " + regno
								+ " number data are not same mark in Excel file");
						testCaseScenario.log(Status.FAIL,
								"Both Excel file total value " + TheroryExamTotal + " and PDF file total value  "
										+ grandTotal + " for the following Register " + regno
										+ " number data are not same mark in Excel file",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

			}

			else {

				System.out.println("Check the files for the following " + regno + " registration number and subject"
						+ subjectToFind);
			}
		}

		catch (Exception e) {
			e.printStackTrace();

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

	// Helper function to check if the marks are greater than 50% of max marks
	public void checkMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3,Object paper4,
			 Object theoryExam, Object praticalExam,String subjectToFind, Object examTotal, String obtainedMarks,
			double maxMarks, ExtentTest testCaseName) throws IOException {

		System.out.println(obtainedMarks);

		if ((obtainedMarks.equals("---") || obtainedMarks.equals("NA")) || obtainedMarks.equals("NE")) {
			System.out.println(markName + " Subject marks: Not available");
		} else {
			double marksValue = Double.parseDouble(obtainedMarks);

			if (marksValue > maxMarks * 0.5) {
			}

			try {

				if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("First Class")
						|| status.trim().equals("Distinction"))
						&& ( markName.trim().contains("Paper1 Sec Marks")
								)) {

					
					Paper1SecMarksValidation(regno, markName,obtainedMarks, paper1, subjectToFind, testCaseName);

				} else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&&markName.trim().contains("SGPA Sec. Marks")||markName.trim().contains("Paper2 Sec Marks")) {

					System.out.println(paper2);
					Paper2SecMarksValidation(regno, markName,obtainedMarks, paper2, subjectToFind, testCaseName);

				}

				else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&& markName.trim().contains("Credits Sec. Marks")||markName.trim().contains("Paper3 Sec Marks")) {

					Paper3SecMarksValidation(regno, markName,obtainedMarks, paper3, subjectToFind, testCaseName);
				}
				
				else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&& markName.trim().contains("Credits Sec. Marks")||markName.trim().contains("Paper4 Sec Marks")) {

					Paper4SecMarksValidation(regno, markName,obtainedMarks, paper4, subjectToFind, testCaseName);
				}
				
				else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&& markName.trim().contains("Theory Total Sec Marks")){
					System.out.println(obtainedMarks);
					System.out.println(theoryExam);
					
					TheoryTotalSecMarksValidation(regno, markName,obtainedMarks,theoryExam, testCaseName, subjectToFind);
				}

				

				else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&& markName.trim().contains("Pratical Univ Sec. Marks")
						|| markName.trim().contains("Pratical Total Sec Marks")) {
					
					System.out.println(praticalExam);
					

					praticalTotalSecMarksValidations(regno, markName,obtainedMarks, praticalExam, subjectToFind, testCaseName);
				}
				else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
						|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
						&& markName.trim().contains("Grand Total Sec Marks")) {
					
					grandTotalMarksValidation(regno, markName, examTotal, subjectToFind, testCaseName);

					System.out.println("==============");
				}

				else {
					System.out.println("==============");
			//		securedMarks(regno, examTotal, testCaseName);
					System.out.println("==============");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	// Method to check if the obtained marks meet the required percentage
	public static boolean verifyScore(double obtainedMarks, double totalMarks, double percentage) {
		// Calculate the minimum required marks
		minMark = totalMarks * percentage;

		// Print the assigned minimum marks
		System.out.println("Assigned Minimum Marks for " + (percentage * 100) + "% of " + totalMarks + ": " + minMark);

		// Return whether obtained marks meet or exceed the minimum required marks
		return obtainedMarks >= minMark;
	}

	public void pdfMarkValidation(Object regno, Object Subject, String pdfPaper1, String pdfPaper2, String pdfTotal,
			String praticalClinicalAndVivaMark, String grandTotal, ExtentTest testCaseName) {

		ExtentTest testCaseScenario = testCaseName.createNode(
				Subject + " Validation Test case has started runningfor the following " + regno + " register no");

		String isResult = null;

		// another way need to check with selva
		// Compare Paper 1 marks (Excel vs PDF)
		if (pdfPaper1.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Paper I " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("The following Register number Paper I " + regno + " is Failed for the subject "
					+ Subject + " PDF marks is: " + pdfPaper1);

		} else if (pdfPaper1.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper I " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper1);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  Paper I " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfPaper1);

		}

		if (pdfPaper2.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper II " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper2);

		} else if (pdfPaper2.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper II " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper2);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  Paper I " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfPaper2);

		}

		// Compare Total marks (Excel vs PDF)
		if (pdfPaper1.equals("B") && pdfPaper2.equals("B") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.FAIL, "The following Register number " + regno + " is Failed for the subject "
					+ Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);

		} else if (pdfPaper1.equals("A") && pdfPaper2.equals("A") && pdfTotal.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Passed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total " + regno + " is Passed for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		} else if (pdfPaper1.equals("A") && pdfPaper2.equals("B") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		}

		else if (pdfPaper1.equals("B") && pdfPaper2.equals("A") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfTotal);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfTotal);

		}

		if (praticalClinicalAndVivaMark.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number pratical mark " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ " PDF marks is: " + praticalClinicalAndVivaMark);

		} else if (praticalClinicalAndVivaMark.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number pratical mark " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + praticalClinicalAndVivaMark);
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  pratical mark " + regno
					+ " for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

		}
		if (pdfTotal.equals("B") && praticalClinicalAndVivaMark.equals("B") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.FAIL, "The following Register number " + regno + " is Failed for the subject "
					+ Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Fail";

		} else if (pdfTotal.equals("A") && praticalClinicalAndVivaMark.equals("A") && grandTotal.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Passed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " is Passed for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Pass";
		} else if (pdfTotal.equals("A") && praticalClinicalAndVivaMark.equals("B") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + grandTotal);

			isResult = "Fail";
		}

		else if (pdfTotal.equals("B") && praticalClinicalAndVivaMark.equals("A") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Fail";
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfTotal);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfTotal);

		}

		if (isResult.equals(status)) {

			testCaseScenario.log(Status.PASS,
					"The following Register number " + regno + " is " + isResult + " for the subject " + Subject);
			System.out.println(
					"The following Register number " + regno + " is " + isResult + " for the subject " + Subject);

		} else {
			testCaseScenario.log(Status.FAIL,
					"please check the fail for the following Register number " + regno + " and result is " + isResult);

			System.out.println("please check the fail for the following register number Total" + regno
					+ " for the subject " + Subject + " and result is " + isResult);

		}

	}
	
	public void nonValidateMarks(Object regno, String markName,String subjectToFind, String marks,
	         ExtentTest testCaseName) throws IOException {

	    Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)");

	    double sec ;
        double max;
	    
	    System.out.println("markss"+ marks);
	    
	    try {
	        if (!invalidValues.contains(marks.trim())) {

	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	        	    sec = 0.0; // Default fallback
	        	    max = 0.0; // Optional: or still parse maxMarks separately if needed

	            System.out.println("The following Register number " + regno + markName +" is: "
						+ marks);
	            
	            scenario.log(Status.PASS,
	            		"The following Register number " + regno +" "+ markName +" is: "
	    						+ marks);
	    	            
	    
	        }
	        else if (invalidValues.contains(marks.trim())) {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	        	    sec = 0.0; // Default fallback
	        	    max = 0.0; // Optional: or still parse maxMarks separately if needed


		            System.out.println("The following Register number " + regno + markName +" is: "
							+ marks);
		            
		            scenario.log(Status.INFO,
		            		"The following Register number " + regno +  markName +" is: "
		    						+ marks);
	        } else {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	            scenario.log(Status.FAIL, "\nPlease check Reg No: " + regno +
	                    " for Subject " + subjectToFind + " marks is: " + marks);
	            System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
	        }
	        } catch (NumberFormatException e) {
	        if (invalidValues.contains(marks.trim())) {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	     
	            System.out.println(" The Following Registration number " + regno
						+ " for the Subject " + subject +" and " + markName +" marks is: "
						+ marks);
	
	            scenario.log(Status.INFO,
						" The Following Registration number " + regno
								+ " for the Subject " + subject +" and " + markName +" marks is: "
								+ marks);
	        } else {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	            scenario.log(Status.FAIL, "\nPlease check Reg No: " + regno +
	                    " for Subject " + subjectToFind + " marks is: " + marks);
	            System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
	        }
	    }
	}
	
	public void validateMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3,Object paper4,
	        Object theoryExamTotal,  Object practicalExamTotal,String subjectToFind, Object grandTotal, String marks,
	        String maxMarks, double percentage, ExtentTest testCaseName) throws IOException {

	    Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)");

	    double sec ;
        double max;
	    
	    System.out.println("markss"+ marks);
	    
	    try {
	        if (!invalidValues.contains(marks.trim())) {

	             sec = Double.parseDouble(marks);
	             
	             System.out.println(sec);
	             max = Double.parseDouble(maxMarks);

	            verifyScore(sec, max, percentage);

	            checkMarks(regno, markName, paper1, paper2, paper3,paper4,
	            		theoryExamTotal,practicalExamTotal, subjectToFind,
	            		grandTotal, marks, max, testCaseName);
	        }
	        else if (invalidValues.contains(marks.trim())) {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	        	    sec = 0.0; // Default fallback
	        	    max = 0.0; // Optional: or still parse maxMarks separately if needed


		            System.out.println("The following Register number " + regno + markName +" is: "
							+ marks);
		            
		            scenario.log(Status.PASS,
		            		"The following Register number " + regno + markName +" is: "
		    						+ marks);
	        } else {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	            scenario.log(Status.FAIL, "\nPlease check Reg No: " + regno +
	                    " for Subject " + subjectToFind + " marks is: " + marks);
	            System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
	        }
	        } catch (NumberFormatException e) {
	        if (invalidValues.contains(marks.trim())) {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	     
	            System.out.println(" The Following Registration number " + regno
						+ " for the Subject " + subject +" and " + markName +" marks is: "
						+ marks);
	
	            scenario.log(Status.INFO,
						" The Following Registration number " + regno
								+ " for the Subject " + subject +" and " + markName +" marks is: "
								+ marks);
	        } else {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	            scenario.log(Status.FAIL, "\nPlease check Reg No: " + regno +
	                    " for Subject " + subjectToFind + " marks is: " + marks);
	            System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
	        }
	    }
	}
	
			
			}
