package knrPageModules;



import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
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
import pageObjMod.knrPom;


public class KnrReportEnrollmentPageForAddtionalParameter  extends BasicFunctions {
	static ExtentTest test;
	
    String theoryInternalMaxMarks;
	String theoryInternalSecMarks; 
	String theoryUnivMaxMarks ;
	String theoryUnivSecMarks ;
	String practicalInternalMaxMarks;
	String practicalInternalSecMarks;
	String practicalUnivMaxMarks;
	String practicalUnivSecMarks;
	String theoryPracticalMaxMarks;
	String theoryPracticalSecMarks;
	String theoryTotalMaxMarks;
	String theoryTotalSecMarks;
	String practicalTotalMaxMarks;
	String practicalTotalSecMarks;
	String practicalVivaMaxMarks;	
	String practicalVivaSecMarks;
	
	
	  Matcher matcher1 ;
	
	
	
	
	double finalPaper1Mark;
	double finalPaper2Mark;
	double Paper1;
	double Paper2;
	double Paper3;
	double TheroryExamTotal;
	double PraticalExamTotal;
	public double ExamTotalScore;
	public Object[][] data1;
	public Object[][] data2;

	long registrationNumber;
	long securedMark;
	double paper1Mark;
	double paper2Mark;
	double paper3Mark;

	double praticalMinMark;

	static double minMark;
	double paper1MinMark;
	double paper2MinMark;
	double paper3MinMark;
	
	double practicalMinMark;
	double theroryExamTotalMinMark;
	double examTotalScoreMinMark;
	

	double theoryMinMark;
	double theoryMaxMark;
	double grandTotalMinMark;
	double grandTotalMaxMark;
	double theoryTotal;
	double praticalTotal;
	double grandTotal;
	String subject;
	// four pattern
	double theorySecMark;

	double praticalMaxMark;

	double praticalVivaMaxMark;
	double praticalTotalMaxMark;
	double praticalTotalSecMark;
	double grandTotalSecMark;

	String status;

	double theoryInternalMaxMark;
	double theoryInternalSecMark;

	private static boolean isTestCaseEnrollSet1 = false;
	
	KnrReportEnrollmentPage KnrEnrollmentPage = new KnrReportEnrollmentPage();

	public void ReportCardNavigation(ExtentTest testCaseName) throws IOException, InterruptedException, AWTException {

		KnrEnrollmentPage.ReportCardNavigation();
		KnrEnrollmentPage.ReportCardEnrollNavigation(testCaseName);
		
	}

	public void EnrollmentRegNo(Object regno, ExtentTest testCaseName) throws IOException {

	    ExtentTest testCaseScenario = testCaseName
	            .createNode("Report card Enrollment wise for the following register number: " + regno);

	    implicitWait(3);

	    try {
	        explicitWait(knrPom.getInstanceEnrollXP().enrollNo, 30);
	        click(knrPom.getInstanceEnrollXP().enrollNo);

	        if (knrPom.getInstanceEnrollXP().enrollNo.isDisplayed()) {
	            implicitWait(3);
	            explicitWait(knrPom.getInstanceEnrollXP().enrollNo, 30);
	            sendKeys(knrPom.getInstanceEnrollXP().enrollNo, String.valueOf(regno));
	            testCaseScenario.log(Status.PASS, "Enrollment wise " + regno + " Register number has entered successfully");

	        } else {
	            // If enrollNo is not displayed, try alternative action
	            try {
	                WebElement yearSessionOptionSelect = driver
	                        .findElement(By.xpath("//li[@role='option' and text()='Select']"));

	                if (yearSessionOptionSelect.isDisplayed()) {
	                    testCaseScenario.log(Status.FAIL, "Enrollment wise " + regno + " Register number has not entered",
	                            MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	                }
	            } catch (NoSuchElementException e) {
	                testCaseScenario.log(Status.FAIL, "Alternative year session option not found.");
	            }
	        }

	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}

	public static void EnrollmentExamDate(Object examDate, ExtentTest testCaseName) throws IOException {

	    ExtentTest testCaseScenario = testCaseName.createNode("Exam Date Test case has started running");

	    implicitWait(3);

	    try {
	        explicitWait(knrPom.getInstanceEnrollXP().examSeries, 30);
	        click(knrPom.getInstanceEnrollXP().examSeries);

	        implicitWait(3);
	        explicitWait(knrPom.getInstanceEnrollXP().examSeries, 30);
	        click(knrPom.getInstanceEnrollXP().examSeries);

	        implicitWait(3);
	        explicitWait(knrPom.getInstanceEnrollXP().examSeries, 30);

	        if (knrPom.getInstanceEnrollXP().examSeries.isDisplayed()) {
	            click(knrPom.getInstanceEnrollXP().examSeries);

	            implicitWait(50);

	            try {
	                // Try to find the exam date option
	                WebElement examDateOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + examDate + "']"));
	                explicitWait(examDateOption, 30);

	                if (examDateOption.isDisplayed()) {
	                    explicitWait(examDateOption, 30);
	                    implicitWait(3);
	                    click(examDateOption);
	                    testCaseScenario.log(Status.PASS, "Exam date has entered successfully");
	                }

	            } catch (NoSuchElementException e) {
	                // If exam date option is not found, log failure
	                testCaseScenario.log(Status.FAIL, "Exam date selection failed: Element not found");
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
	                testCaseScenario.log(Status.FAIL, "Alternative year session option not found.");
	            }
	        }

	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}




	public static void EnrollmentAwardName(Object awardName, ExtentTest testCaseName) throws IOException {

	    ExtentTest testCaseScenario = testCaseName.createNode("Award Name Test case has started running");

	    implicitWait(3);
	    explicitWait(knrPom.getInstanceEnrollXP().awardName, 30);

	    try {
	        if (knrPom.getInstanceEnrollXP().awardName.isDisplayed()) {
	            click(knrPom.getInstanceEnrollXP().awardName);

	            try {
	                // Try to find the award option
	                WebElement awardOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + awardName + "']"));

	                if (awardOption.isDisplayed()) {
	                    explicitWait(awardOption, 30);
	                    implicitWait(3);
	                    click(awardOption);
	                    testCaseScenario.log(Status.PASS, "Award name has entered successfully");
	                }

	            } catch (NoSuchElementException e) {
	                // If award option is not found, log failure
	                testCaseScenario.log(Status.FAIL, "Award name selection failed: Element not found");
	            }

	        } else {
	            // If award name dropdown is not displayed, try alternative action
	            try {
	                WebElement yearSessionOptionSelect = driver
	                        .findElement(By.xpath("//li[@role='option' and text()='Select']"));

	                if (yearSessionOptionSelect.isDisplayed()) {
	                    testCaseScenario.log(Status.FAIL, "Award name has not entered",
	                            MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	                }
	            } catch (NoSuchElementException e) {
	                testCaseScenario.log(Status.FAIL, "Alternative year session option not found.");
	            }
	        }

	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}


	public static void EnrollmentSemester(Object semester, ExtentTest testCaseName) throws IOException {

	    ExtentTest testCaseScenario = testCaseName.createNode("Semester Test case has started running");

	    implicitWait(3);
	    explicitWait(knrPom.getInstanceEnrollXP().yearSession, 30);

	    try {
	        if (knrPom.getInstanceEnrollXP().yearSession.isDisplayed()) {
	            click(knrPom.getInstanceEnrollXP().yearSession);
	            implicitWait(3);

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
	                testCaseScenario.log(Status.FAIL, "Semester selection failed: Element not found");
	            }

	        } else {
	            testCaseScenario.log(Status.FAIL, "Year session dropdown is not displayed");
	        }
	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}


	public static void EnrollmentRegulation(Object regulation, ExtentTest testCaseName) throws IOException {

	    ExtentTest testCaseScenario = testCaseName.createNode("Regulation Test case has started running");

	    implicitWait(3);
	    explicitWait(knrPom.getInstanceEnrollXP().regulation, 30);

	    try {
	        if (knrPom.getInstanceEnrollXP().regulation.isDisplayed()) {
	            click(knrPom.getInstanceEnrollXP().regulation);
	            implicitWait(3);

	            try {
	                // Try to find the regulation option
	                WebElement regulationOption = driver
	                        .findElement(By.xpath("//li[@role='option' and text()='" + regulation + "']"));

	                if (regulationOption.isDisplayed()) {
	                    explicitWait(regulationOption, 30);
	                    implicitWait(3);
	                    click(regulationOption);
	                    testCaseScenario.log(Status.PASS, "Regulation has entered successfully");
	                }

	            } catch (NoSuchElementException e) {
	                // If regulation option is not found, log failure
	                testCaseScenario.log(Status.FAIL, "Regulation selection failed: Element not found");
	            }

	        } else {
	            // If regulation dropdown is not displayed, try alternative action
	            try {
	                WebElement yearSessionOptionSelect = driver
	                        .findElement(By.xpath("//li[@role='option' and text()='Select']"));

	                if (yearSessionOptionSelect.isDisplayed()) {
	                    testCaseScenario.log(Status.FAIL, "Regulation has not entered",
	                            MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	                }
	            } catch (NoSuchElementException e) {
	                testCaseScenario.log(Status.FAIL, "Alternative year session option not found.");
	            }
	        }

	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}
		

	public static void EnrollmentExamType(Object examType, ExtentTest testCaseName)
	        throws InterruptedException, IOException {

	    ExtentTest testCaseScenario = testCaseName.createNode("Exam Type Test case has started running");

	    implicitWait(3);
	    explicitWait(knrPom.getInstanceEnrollXP().examType, 30);

	    try {
	        if (knrPom.getInstanceEnrollXP().examType.isDisplayed()) {
	            click(knrPom.getInstanceEnrollXP().examType);
	            implicitWait(3);

	            try {
	                // Try to find the exam type option
	                WebElement examTypesOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + examType + "']"));

	                if (examTypesOption.isDisplayed()) {
	                    explicitWait(examTypesOption, 30);
	                    implicitWait(3);
	                    click(examTypesOption);
	                    testCaseScenario.log(Status.PASS, "Exam Type has entered successfully");
	                }

	            } catch (NoSuchElementException e) {
	                // If exam type option is not found, log failure
	                testCaseScenario.log(Status.FAIL, "Exam Type selection failed: Element not found");
	            }

	        } else {
	            // If exam type dropdown is not displayed, try alternative action
	            try {
	                WebElement yearSessionOptionSelect = driver
	                        .findElement(By.xpath("//li[@role='option' and text()='Select']"));

	                if (yearSessionOptionSelect.isDisplayed()) {
	                    testCaseScenario.log(Status.FAIL, "Exam Type has not entered",
	                            MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	                }
	            } catch (NoSuchElementException e) {
	                testCaseScenario.log(Status.FAIL, "Alternative year session option not found.");
	            }
	        }

	    } catch (Exception e) {
	        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
	    }
	}

	public void submitButton(ExtentTest testCaseName) throws InterruptedException, IOException {

		ExtentTest testCaseScenario = testCaseName.createNode(" Submit Button Test case has started running ");

		try {
		implicitWait(5);
		explicitWait(knrPom.getInstanceEnrollXP().submitBtn, 10);
		if (knrPom.getInstanceEnrollXP().submitBtn.isDisplayed()) {

			click(knrPom.getInstanceEnrollXP().submitBtn);
			   testCaseScenario.log(Status.PASS, "Submit button click sucessfully");
		
			   
			   Thread.sleep(5000);
	
			
			   
				try {
					   if(knrPom.getInstanceEnrollXP().okBtn.isDisplayed()) {
						   explicitWait( knrPom.getInstanceEnrollXP().okBtn, 10);
						   System.out.println(knrPom.getInstanceEnrollXP().okBtn.isDisplayed());
						   testCaseScenario.log(Status.INFO, "Facing error to generate the pdf check if he NE case ",
				                    MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
							explicitWait(knrPom.getInstanceEnrollXP().okBtn, 30);
						   click(knrPom.getInstanceEnrollXP().okBtn);
							implicitWait(3);
						driver.navigate().refresh();
					}}
					   
					   catch(Exception e){
						   
						   System.out.println("able to geanerater");
					   }
				
			
			}
			
			else {
	            testCaseScenario.log(Status.FAIL, "Submit button not found or not clickable",
	                    MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	        }
	
	
	
		
		
		
		
		
			
		}
		
		
		 catch (Exception e) {
		        testCaseScenario.log(Status.FAIL, "Unexpected error: " + e.getMessage());
		    }
		}





	public void DownloadReport() {

		try {

			Actions action = new Actions(driver);

			Thread.sleep(2500);

			implicitWait(3);

//			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportTo).perform();

			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportTo).click().perform();

			implicitWait(3);

//			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportToPdf).perform();

			action.moveToElement(knrPom.getInstanceEnrollXP().reportCardExportToPdf).click().perform();

			implicitWait(3);

			Thread.sleep(7000);

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

	public void downloadPdfReportValidation(ExtentTest testCaseName, Object regno, Object paper1, Object paper2,
			Object paper3, Object theoryExam, Object praticalExam, Object examTotal, String subjectToFind,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva)
			throws InterruptedException, IOException, AWTException {


		try {
		// Wait for the PDF file to download
			String downloadDir = System.getProperty("user.home") + "/Downloads"; // Downloads folder
			File dir = new File(downloadDir);
			File latestFile = null;
			long startTime = System.currentTimeMillis();
			// Wait for the file to download
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
			
				if (knrPom.getInstanceEnrollXP().okBtn.isDisplayed()) {
					KnrEnrollmentPage.monitorNetworkDuringTest(testCaseName);
					ExtentTest testCaseScenario = testCaseName
							.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

					
					System.out.println(knrPom.getInstanceEnrollXP().okBtn.isDisplayed());
			
					testCaseScenario.log(Status.SKIP, "Facing error to generate the pdf for following regno: " +regno + " and subject: "+ subjectToFind+"check wherther",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					explicitWait(knrPom.getInstanceEnrollXP().okBtn, 30);
					click(knrPom.getInstanceEnrollXP().okBtn);
					implicitWait(3);
					driver.navigate().refresh();
			
				}		
				
				
				
			}

			catch (Exception e) {
				
				if (driver.getCurrentUrl().endsWith(".pdf")) {
			
					KnrEnrollmentPage.monitorNetworkDuringTest(testCaseName);
					
					ExtentTest testCaseScenario = testCaseName
						.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

				System.out.println("able to geanerater");
				testCaseScenario.log(Status.PASS, "Able to generate ");

			System.out.println(subjectToFind+"subjectToFind");
			// Method to match the paterns
			// Method to match the paterns
			processPdfBasedOnSubjectPattern(latestFile, regno, paper1, paper2, paper3, theoryExam, praticalExam,
					examTotal, subjectToFind,theoryInt,theoryTh,praticalInt,praticalPractical, praticalViva, testCaseName);
						}
			
				}
			
		}
		

		catch (Exception e) {
			
			if (knrPom.getInstanceEnrollXP().enrollNo.isDisplayed()) {
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Pdf Report Validation Test case has started running for the following register number " + regno +" and "+ subjectToFind);

				KnrEnrollmentPage.monitorNetworkDuringTest(testCaseName);
				testCaseScenario.log(Status.SKIP, "Facing error to generate the pdf for following regno: " +regno + " and subject: "+ subjectToFind+"check wherther",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				implicitWait(3);		
			}
			
		}
	}

		
	

	public void processEightSubjectPatternPdf(Object Regno, File latestFile, Object paper1, Object paper2,
			Object paper3, Object praticalExam, Object theoryExam, Object examTotal, String subjectToFind,
			ExtentTest testCaseName,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva) throws IOException {
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

					//TO print the text
					
					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
				//	System.out.println(text);
					
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
			        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
						
						testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found");
						
						System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
					
			        	
			        	System.out.println("No match found.");
					}

					List<String> subjects = new ArrayList<>();
			        List<String> marksLines = new ArrayList<>();
			        
			        StringBuilder subjectBuilder = new StringBuilder();
			        boolean isSubjectSection = false; // Flag to detect subjects

			        for (String line : text.split("\n")) {
			            line = line.trim();
			            if (line.isEmpty()) continue; // Skip empty lines

			            // If a line starts with "Theory" or "Note", stop adding subjects
			            if (line.startsWith("Theory") || line.startsWith("Note") || line.startsWith("❖")) {
			                isSubjectSection = false;
			                continue;
			            }

			            // If the line has NO numbers and is not a header, it's a subject
			            if (!line.matches(".*\\d+.*") && !line.contains(":")) {
			                isSubjectSection = true;
			                subjectBuilder.append(line).append(" ");
			            } else {
			                if (isSubjectSection && subjectBuilder.length() > 0) {
			                    subjects.add(subjectBuilder.toString().trim());
			                    subjectBuilder.setLength(0); // Reset for next subject
			                    isSubjectSection = false;
			                }
			            }
			        }

			        // Print Extracted Subjects
			     
			    
			      //  String regex = "(M\\.Sc\\.|B\\.Sc\\.|BPT)\\s+";
			        String regex = "(BPT|BNYS|BDS)\\s+";
			        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			        matcher1 = pattern.matcher(text);

			        if (matcher1.find()) {
			            System.out.println("Match found Course: " + matcher1.group());
			        } else {
			        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
						
						testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found",	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
						System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
					
			        	
			        	System.out.println("No match found.");
			        }

			      /*  Pattern marksPattern = Pattern.compile(
			                "^(?:([^\\d\\n].*?)\\s+)?" + // Subject name (optional, matches if it does not start with a number)
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Internal Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Total Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Obtained Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Internal
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Viva
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Max
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Sec
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Max
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Sec
			                "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // Final Status
			                Pattern.MULTILINE);
*/
			        
			        Pattern marksPattern =
		//	      
	//		    "^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"+ 

			//				"([A-Za-z &'\\-\\(\\),]+(?:\\n[A-Za-z &'\\-\\(\\),]+)*)\\s+"+ 			
			//		  		  "^(?:([^\\d\\n].*?)\\s+)?" +
		//	"(?m)^(?:((?:[^\\d\\n].*?(?:\\R[^\\d\\n].*?)*)?)\\s+)?"+      	    
		
			        		Pattern.compile(
		        			     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
			        			
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
			        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			        			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			        			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
			        			    Pattern.DOTALL | Pattern.MULTILINE
			        			);
			        	
			    


			        
			        
			        
			        /*
//			        // Match "M.Sc." or "B.Sc."
//			        Pattern marksPattern = Pattern.compile(
//			                
//			                "^(?:([^\\d\\n].*?)\\s+)?" + // Optional Subject (Matches only if it does not start with a number)
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // int
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Th
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // th + int max mark Total Marks
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // th + int sec mark  Obtained Marks
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // pr int 
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // pratical 
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // viva
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // practical pr in viva max mark
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // practical pr in viva sec mark
//			                
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // theory+practical max mark
//			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // theory+practical sec mark
//			                "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // Final result Status
//			                Pattern.MULTILINE);
//*/

					Matcher matcher = marksPattern.matcher(text);

					
					
					
					Pattern bdsPattern = Pattern.compile(
						    "(?<subject>(?:[A-Z ,]+(?:\\n)?)+)\\s+" + 
						    "((?:\\d+|---|NA|AB|NE(?:\\s*\\(AT\\))?|NR)\\s*(?:\\(\\s*F\\s*\\))?\\s*)+",
						    Pattern.MULTILINE
						);
		
					
				
						
						try {
						
						
						if (matcher1.group().contains("B.Sc.")) {
				
						System.out.println("==============");
						subject = matcher.group(1).trim();

						theoryInternalMaxMarks = matcher.group(2);
						theoryInternalSecMarks = matcher.group(3);
						theoryUnivMaxMarks = matcher.group(4);
						theoryUnivSecMarks = matcher.group(5);
						practicalInternalMaxMarks = matcher.group(6);
						practicalInternalSecMarks = matcher.group(7);
						practicalUnivMaxMarks = matcher.group(8);
						practicalUnivSecMarks = matcher.group(9);
						theoryPracticalMaxMarks = matcher.group(10);
						theoryPracticalSecMarks = matcher.group(11);
						status = matcher.group(12);

						System.out.println("subject: " + matcher.group(1).trim());

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
						
						
					
						
						paper1Mark =0.0;
						paper2Mark=0.0;
						paper3Mark =0.0;
						praticalTotalSecMark =0.0;
						ExamTotalScore =0.0;
						Paper1  =0.0;
						Paper2=0.0;
						Paper3 =0.0;
						PraticalExamTotal =0.0;
						
						
						if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {

							try {
								
								if (!theoryInternalSecMarks.equals("NA")||!practicalUnivSecMarks.equals("AB")||!practicalUnivSecMarks.equals("NE") ||!  practicalUnivSecMarks.equals("NE (AT)")) {
								theoryInternalMaxMark = Double.parseDouble(theoryInternalMaxMarks);
								paper1Mark = Double.parseDouble(theoryInternalSecMarks) ;
								
								checkMarks(Regno, "Theory Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, theoryInternalSecMarks,
										theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
								else {
									
									ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
									
									testCaseScenario.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
									
									System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
										
									
								}	
								
								// Use the value
							} catch (NumberFormatException e) {

								if (theoryInternalSecMarks.equals("AB") || 
										theoryInternalSecMarks.equals("NE") || 
										theoryInternalSecMarks.equals("---") || 
										theoryInternalSecMarks.equals("NA") || 
										theoryInternalSecMarks.equals("NE (AT)")) {
									paper1Mark = 0.0;
									Paper1 = 0.0;
									System.out.println(paper1Mark);

								ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
								
								testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
								
								System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
									
							
								
								}
								else {
									
									ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
									
									testCaseScenario.log(Status.FAIL,"Please check The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
									
									System.out.println("Please check The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
										
									
								}
								
							
							
							
							}

							try {
								if (!theoryUnivSecMarks.equals("NA")) {
								theoryMaxMark = Double.parseDouble(theoryUnivMaxMarks);
								paper2Mark = Double.parseDouble(theoryUnivSecMarks);
								
								
								// Check Theory (Univ) Sec. Marks
								checkMarks(Regno, "Theory (Univ) Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, theoryUnivSecMarks, theoryMaxMark,
										testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n  Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
									
									 System.out.println("\nPlease check The Following Registration number " + Regno
												+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
								}	// Use the value
							} catch (NumberFormatException e) {

								if (theoryUnivSecMarks.equals("AB") || 
										theoryUnivSecMarks.equals("NE") || 
									    theoryUnivSecMarks.equals("---") || 
									    theoryUnivSecMarks.equals("NA") || 
									    theoryUnivSecMarks.equals("NE (AT)")) {
									paper2Mark = 0.0;
									Paper2 =0.0;
									System.out.println(paper2Mark);
								
							
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
									
									
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
											 
								}
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n  Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
									
									 System.out.println("\nPlease check The Following Registration number " + Regno
												+ " Therory Univ Sec. Marks is: " + theoryUnivSecMarks);
								}

								}
								// Handle gracefully, e.g., assign default value or log an error

							

							try {
								if (!practicalInternalSecMarks.equals("NA")) {

								praticalMaxMark = Double.parseDouble(practicalInternalMaxMarks);
								paper3Mark = Double.parseDouble(practicalInternalSecMarks);
								Paper3 = Double.parseDouble(practicalInternalSecMarks);
								
//								System.out.println("paper 3 mark in check marks :" + paper3Mark);
//
//								System.out.println("paper 3 mark in check marks :" + Paper3);
//
//								System.out.println(Paper3);
								
								checkMarks(Regno, "Pratical Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, practicalInternalSecMarks, praticalMaxMark,
										testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

								}
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical internal Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Internal Sec. Marks is: " + practicalInternalSecMarks);
									
									 System.out.println("\n Please check The Following Registration number " + Regno
												+ " Practical Internal Sec. Marks is:" + practicalInternalSecMarks);		
								}
								// Use the value
								// Check pratical internal Sec. Marks
								
							} catch (NumberFormatException e) {
							
								//if the any of the marks is this data need to convert to 0 or else it will thow error
								
								if (practicalInternalSecMarks.equals("AB") || 
										practicalInternalSecMarks.equals("NE") || 
										practicalInternalSecMarks.equals("---") || 
										practicalInternalSecMarks.equals("NA")||
										practicalInternalSecMarks.equals("NE (AT)")) {
									paper3Mark = 0.0;
									Paper3 =0.0;
									
									System.out.println(paper3Mark);
									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical internal Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Internal Sec. Marks is: " + practicalInternalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Practical Internal Sec. Marks is:" + practicalInternalSecMarks);
											 
								}
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical internal Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Internal Sec. Marks is: " + practicalInternalSecMarks);
									
									 System.out.println("\n Please check The Following Registration number " + Regno
												+ " Practical Internal Sec. Marks is:" + practicalInternalSecMarks);		
								}
								
								
								
							}

							try {
								
								if (!practicalUnivSecMarks.equals("NA")) {
								praticalTotalMaxMark = Double.parseDouble(practicalUnivMaxMarks);
								praticalTotalSecMark = Double.parseDouble(practicalUnivSecMarks);
									// Use the value
								// Check pratical internal Sec. Marks
								checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, practicalUnivSecMarks,
										praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
								else {
									
									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
									
									 System.out.println("\nPlease check The Following Registration number " + Regno
												+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
										
								}
								
								
							} catch (NumberFormatException e) {

								if (practicalUnivSecMarks.equals("AB") || 
										practicalUnivSecMarks.equals("NE") || 
									    practicalUnivSecMarks.equals("---") || 
									    practicalUnivSecMarks.equals("NA")||
									    practicalUnivSecMarks.equals("NE (AT)")) {
									praticalTotalSecMark = 0.0;
									PraticalExamTotal =0.0;
									System.out.println(praticalTotalSecMark);
								

								 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
									
									
								 testCaseScenario.log(Status.PASS,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
										 + " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
								
								 System.out.println("\nThe Following Registration number " + Regno
											+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
									
								// Handle gracefully, e.g., assign default value or log an error

							}
								
								else {
									
									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n Please check The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
									
									 System.out.println("\nPlease check The Following Registration number " + Regno
												+ " Practical Univ Sec. Marks is:" + practicalUnivSecMarks);
										
								}
							
							}

							try {
								if (!theoryPracticalSecMarks.equals("NA")) {
								grandTotalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
								ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
								
								// Check Grand Total Sec. Marks (assumed max marks as 200)
								checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
										praticalExam, theoryExam, subjectToFind, examTotal, theoryPracticalSecMarks,
										grandTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
								
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										

								}
								
								// Use the value
							} catch (NumberFormatException e) {
								if (theoryPracticalSecMarks.equals("AB") || 
										theoryPracticalSecMarks.equals("NE") || 
										theoryPracticalSecMarks.equals("---") || 
										theoryPracticalSecMarks.equals("NA") || 
														
										theoryPracticalSecMarks.equals("NE (AT)")) {
									ExamTotalScore = 0.0;
									System.out.println(ExamTotalScore);
								

									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										

							}
								
								else {
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.FAIL,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										

								}
								
							}

							// Check Theory Internal Sec. Marks

							// Stop after printing one subject
						}

						}
				

//For MSC course		
			
			
	else if (matcher1.group().contains("M.Sc.")) {
							
							System.out.println("==============");
							subject = matcher.group(1).trim();

							theoryInternalMaxMarks = matcher.group(2);
							theoryUnivMaxMarks = matcher.group(3);
							theoryTotalMaxMarks = matcher.group(4);
							theoryTotalSecMarks = matcher.group(5);
							practicalInternalMaxMarks = matcher.group(6);
							practicalUnivMaxMarks = matcher.group(7);
							practicalTotalMaxMarks = matcher.group(8);
							practicalTotalSecMarks = matcher.group(9);
							theoryPracticalMaxMarks = matcher.group(10);
							theoryPracticalSecMarks = matcher.group(11);
							status = matcher.group(12);

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
							
						paper1Mark =0.0;
						paper2Mark=0.0;
						paper3Mark =0.0;
						praticalTotalSecMark =0.0;
						ExamTotalScore =0.0;
						Paper1  =0.0;
						Paper2=0.0;
						Paper3 =0.0;
						PraticalExamTotal =0.0;
						
						
						
						if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {

							try {
								
								if (!theoryTotalSecMarks.equals("NA")||!theoryTotalSecMarks.equals("AB")||!theoryTotalSecMarks.equals("NE") ||!  theoryTotalSecMarks.equals("NE (AT)")) {
								theoryInternalMaxMark = Double.parseDouble(theoryTotalMaxMarks);
								paper1Mark = Double.parseDouble(theoryTotalSecMarks) ;
								
								checkMarks(Regno, "Theory Total Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, theoryTotalSecMarks,
										theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}	// Use the value
							} catch (NumberFormatException e) {

								if (theoryTotalSecMarks.equals("AB") || 
										theoryTotalSecMarks.equals("NE") || 
										theoryTotalSecMarks.equals("---") || 
										theoryTotalSecMarks.equals("NE (AT)")) {
									paper1Mark = 0.0;
									Paper1 = 0.0;
									System.out.println(paper1Mark);

								ExtentTest testCaseScenario = testCaseName.createNode("Theory Total sec. marks validation for the subject " + subject +" Test case has started running");
								
								testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory Total sec marks is: " + theoryTotalSecMarks);
								
								System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory Total sec marks is: " + theoryTotalSecMarks);
									
							
								
								}
								
								else{
									
									ExtentTest testCaseScenario = testCaseName.createNode("Theory Total sec. marks validation for the subject " + subject +" Test case has started running");
									
									testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory Total sec marks is: " + theoryTotalSecMarks);
									
									System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory Total sec marks is: " + theoryTotalSecMarks);
										}
								}

							try {
								
								if (!practicalTotalSecMarks.equals("NA")) {
								praticalTotalMaxMark = Double.parseDouble(practicalTotalMaxMarks);
								praticalTotalSecMark = Double.parseDouble(practicalTotalSecMarks);
									// Use the value
								// Check pratical internal Sec. Marks
								checkMarks(Regno, "Pratical Total Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, practicalTotalSecMarks,
										praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
							} catch (NumberFormatException e) {

								if (practicalTotalSecMarks.equals("AB") || 
										practicalTotalSecMarks.equals("NE") || 
										practicalTotalSecMarks.equals("---") || 
										practicalTotalSecMarks.equals("NE (AT)")) {
									praticalTotalSecMark = 0.0;
									PraticalExamTotal =0.0;
									System.out.println(praticalTotalSecMark);
								

									 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Total Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Practical Total Sec. Marks is:" + practicalTotalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Practical Total Sec. Marks is:" + practicalTotalSecMarks);
										
								// Handle gracefully, e.g., assign default value or log an error

							}
								
								else{
									
								
								}
								}

							try {
								if (!theoryPracticalSecMarks.equals("NA")) {
									theoryInternalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
								ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
								
								// Check Grand Total Sec. Marks (assumed max marks as 200)
								checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
										praticalExam, theoryExam, subjectToFind, examTotal, theoryPracticalSecMarks,
										theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								}
								// Use the value
							} catch (NumberFormatException e) {
								if (theoryPracticalSecMarks.equals("AB") || 
										theoryPracticalSecMarks.equals("NE") || 
										theoryPracticalSecMarks.equals("---") || 
										theoryPracticalSecMarks.equals("NE (AT)")) {
									ExamTotalScore = 0.0;
									System.out.println(ExamTotalScore);
								

									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										

							}
								
								else{
									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
							
								
								}
								}
								
							

							// Check Theory Internal Sec. Marks

							// Stop after printing one subject
						}
						
						}
						
						
						
						
	else if (matcher1.group().contains("BPT") ) {
		   Pattern bptMarksPattern =

			Pattern.compile(
//	     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
				
			"(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-/]+)*)\\s+"		
					
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

		    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
		    Pattern.DOTALL | Pattern.MULTILINE
		);
		   
			Matcher bptmatcher = bptMarksPattern.matcher(text);

		   
		System.out.println("jhdsgfjhdfgs");
		
		while (bptmatcher.find()) {
		
		subject = bptmatcher.group(1).replaceAll("\\s+", " ").trim();
		System.out.println("==============");

		String theoryIntMark =bptmatcher.group(2);
	   String theoryThMark = bptmatcher.group(3);
		   String theoryThPlusIntMaxMark = bptmatcher.group(4);
		   String theoryThPlusIntSecMark = bptmatcher.group(5);
		   String practicalPrInt = bptmatcher.group(6);
		   String practicalPractical = bptmatcher.group(7);
		   String practicalViva = bptmatcher.group(8);
		   String practicalPrIntPlusPracticalPlusVivaMaxMark = bptmatcher.group(9);
		  String practicalPrIntPlusPracticalPlusVivaSecMark = bptmatcher.group(10);
		  String theoryPlusPracticalMaxMark =  bptmatcher.group(11);
		  String theoryPlusPracticalSecMark = bptmatcher.group(12);
			status =	 bptmatcher.group(13); 
		   

			System.out.println("==============");
		
			
			System.out.println("Subjects: " +subject);
	
			
			System.out.println("Theory Int. Marks: " + theoryIntMark);
			System.out.println("Theory Th. Marks: " + theoryThMark);
			System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
			System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
			System.out.println("Practical Pr. Int.: " + practicalPrInt);
			System.out.println("Practical Practical: " + practicalPractical);
			System.out.println("Practical Viva: " + practicalViva);
			System.out.println("Practical Pr. Int. + Practical + Viva Max Marks: " + practicalPrIntPlusPracticalPlusVivaMaxMark);
			System.out.println("Practical Pr. Int. + Practical + Viva Sec. Marks: " + practicalPrIntPlusPracticalPlusVivaSecMark);
			System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
			System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
			System.out.println("Result: " + status);
			System.out.println("==============");
			
	            paper1Mark =0.0;
				paper2Mark=0.0;
				paper3Mark =0.0;
				praticalTotalSecMark =0.0;
				ExamTotalScore =0.0;
				Paper1  =0.0;
				Paper2=0.0;
				Paper3 =0.0;
				PraticalExamTotal =0.0;
				
				
				if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {

					try {
						
						if (!theoryThPlusIntSecMark.equals("NA")||!theoryThPlusIntSecMark.equals("AB")||!theoryThPlusIntSecMark.equals("NE") ||!  theoryThPlusIntSecMark.equals("NE (AT)")||!theoryThPlusIntSecMark.equals("NA ") ) {
						theoryInternalMaxMark = Double.parseDouble(theoryThPlusIntMaxMark);
						paper1Mark = Double.parseDouble(theoryThPlusIntSecMark) ;
						verifyScore(paper1Mark,theoryInternalMaxMark,0.50);
						
						checkMarks(Regno, "Theory Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
								theoryExam, subjectToFind, examTotal, theoryThPlusIntSecMark,
								theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

						}
				
				
						
					// Use the value
					} catch (NumberFormatException e) {

						if (theoryThPlusIntSecMark.equals("AB") || 
								theoryThPlusIntSecMark.equals("NE") || 
								theoryThPlusIntSecMark.equals("---") || 
								theoryThPlusIntSecMark.equals("NA") ||
								
								theoryThPlusIntSecMark.equals("NE (AT)")) {
							paper1Mark = 0.0;
							Paper1 = 0.0;
							System.out.println(paper1Mark);

						ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThPlusIntSecMark);
						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThPlusIntSecMark);
							
					
						
						}
						else {
							ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
							
							testCaseScenario.log(Status.FAIL,"Please check The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThPlusIntSecMark);
							
							System.out.println("Please check The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThPlusIntSecMark);

						}
					
					}
					try {
						
						if (!practicalPrIntPlusPracticalPlusVivaSecMark.equals("NA")||!practicalPrIntPlusPracticalPlusVivaSecMark.equals("NA ")) {

						praticalTotalMaxMark = Double.parseDouble(practicalPrIntPlusPracticalPlusVivaMaxMark);
						praticalTotalSecMark = Double.parseDouble(practicalPrIntPlusPracticalPlusVivaSecMark);
						
						verifyScore(praticalTotalSecMark,praticalTotalMaxMark,0.50);
						checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
								theoryExam, subjectToFind, examTotal, practicalPrIntPlusPracticalPlusVivaSecMark,
								praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
						
						}	// Use the value
						// Check pratical internal Sec. Marks
					
						
					} catch (NumberFormatException e) {

						if (practicalPrIntPlusPracticalPlusVivaSecMark.equals("AB") || 
								
								practicalPrIntPlusPracticalPlusVivaSecMark.equals("NE") || 
								practicalPrIntPlusPracticalPlusVivaSecMark.equals("---") || 
								practicalPrIntPlusPracticalPlusVivaSecMark.equals("NA") || 
								practicalPrIntPlusPracticalPlusVivaSecMark.equals("NE (AT)")) {
							praticalTotalSecMark = 0.0;
							PraticalExamTotal =0.0;
							System.out.println(praticalTotalSecMark);
						

						 ExtentTest testCaseScenario = testCaseName.createNode("Pratical +pr int+ viva Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
							
							
						 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
								 + " Pratical +pr int+ viva Sec Marks is:" + practicalPrIntPlusPracticalPlusVivaSecMark);
						
						 System.out.println("\nThe Following Registration number " + Regno
									+ " Pratical +pr int+ viva Sec is:" + practicalPrIntPlusPracticalPlusVivaSecMark);
							
						// Handle gracefully, e.g., assign default value or log an error

						}
						else {

							 ExtentTest testCaseScenario = testCaseName.createNode("Pratical +pr int+ viva Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
								
								
							 testCaseScenario.log(Status.FAIL,"\n Please check  The Following Registration number " + Regno +" for the Subject "+ subject 
									 + " Pratical +pr int+ viva Sec Marks is:" + practicalPrIntPlusPracticalPlusVivaSecMark);
							
							 System.out.println("\nPlease check The Following Registration number " + Regno
										+ " Pratical +pr int+ viva Sec is:" + practicalPrIntPlusPracticalPlusVivaSecMark);
								
						}
						
					
					}

					try {
						if (!theoryPlusPracticalSecMark.equals("NA")||!theoryPlusPracticalSecMark.equals("NA ")) {
							theoryInternalMaxMark = Double.parseDouble(theoryPlusPracticalMaxMark);
						ExamTotalScore = Double.parseDouble(theoryPlusPracticalSecMark);
						
						verifyScore(ExamTotalScore,theoryInternalMaxMark,0.50);
						// Check Grand Total Sec. Marks (assumed max marks as 200)
						checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
								praticalExam, theoryExam, subjectToFind, examTotal, theoryPlusPracticalSecMark,
								theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

						}
					
						// Use the value
					} catch (NumberFormatException e) {
						if (theoryPlusPracticalSecMark.equals("AB") || 
								theoryPlusPracticalSecMark.equals("NE") || 
						theoryPlusPracticalSecMark.equals("---") || theoryPlusPracticalSecMark.equals("NA") ||
								theoryPlusPracticalSecMark.equals("NE (AT)")) {
							ExamTotalScore = 0.0;
							System.out.println(ExamTotalScore);
						

							 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
								
								
							 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
									 + " Theory plus Pratical Sec. Marks is: " + theoryPlusPracticalSecMark);
							
							 System.out.println("\nThe Following Registration number " + Regno
										+ " Theory plus Pratical Sec. Marks is:" + theoryPlusPracticalSecMark);
								

					}
						
						else {

							 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
								
								
							 testCaseScenario.log(Status.INFO,"\n Please check The Following Registration number " + Regno +" for the Subject "+ subject 
									 + " Theory plus Pratical Sec. Marks is: " + theoryPlusPracticalSecMark);
							
							 System.out.println("\nPlease check The Following Registration number " + Regno
										+ " Theory plus Pratical Sec. Marks is:" + theoryPlusPracticalSecMark);
								
						}
					
					}
				}	
						
				}}
						
	else if(matcher1.group().contains("BNYS")) {
		
		
	      Pattern bnysMarksPattern =	Pattern.compile(
			     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
   			
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
   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
   			    Pattern.DOTALL | Pattern.MULTILINE
   			);
		
		Matcher bnysMatcher = bnysMarksPattern.matcher(text);
		
		while(bnysMatcher.find()){
		
		
		subject =  bnysMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
		String intMaxMarks = bnysMatcher.group(2);
		String intSecMarks = bnysMatcher.group(3);
		String p1 =bnysMatcher.group(4);
		String p2 =bnysMatcher.group(5);
		   String theoryThMaxMark = bnysMatcher.group(6);
			   String theoryThSecMark = bnysMatcher.group(7);
			   String theoryThPlusIntMaxMark = bnysMatcher.group(8);
			   String theoryThPlusIntSecMark = bnysMatcher.group(9);
			  
			   String practicalPractical = bnysMatcher.group(10);
			   String practicalViva = bnysMatcher.group(11);
			   String practicalPlusVivaMaxMark = bnysMatcher.group(12);
			   String practicalPlusVivaSecMark = bnysMatcher.group(13);				   
	
			  String theoryPlusPracticalMaxMark =  bnysMatcher.group(14);
			  String theoryPlusPracticalSecMark = bnysMatcher.group(15);
				status =	 bnysMatcher.group(16); 

        
	    System.out.println("==============");
				    
        System.out.println("Subject: " + subject);
        
        System.out.println("Int Max Marks: " + intMaxMarks);
        System.out.println("Int Sec Marks: " + intSecMarks);
        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
		System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
		System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
		System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
		System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
		System.out.println("Practical Practical: " + practicalPractical);
		System.out.println("Practical Viva: " + practicalViva);
		System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
		System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
		System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
		System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
		System.out.println("Result: " + status);
		System.out.println("==============");
		

		
		if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {

			
			try {
				
				if (!p1.trim().equals("NA")||!p1.trim().equals("AB")||!p1.trim().equals("NE") ||!p1.equals("NE (AT)")) {
				
					ExtentTest testCaseScenario = testCaseName.createNode("Paper1 sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Paper1 sec mark is: " + p1);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  sec marks is: " + p1);
						
						
				}
				// Use the value
			} catch (NumberFormatException e) {

				if (p1.trim().equals("AB") || p1.trim().equals("NA") ||
						p1.trim().equals("NE") || 
						p1.trim().equals("---") || 
						p1.equals("NE (AT)")) {
				ExtentTest testCaseScenario = testCaseName.createNode("Paper1 sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Paper1 sec mark is: " + p1);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  sec marks is: " + p1);
				
				}}
			
			
			try {
				
				if (!p2.trim().equals("NA")||!p2.trim().equals("AB")||!p2.trim().equals("NE") ||!  p2.trim().equals("NE (AT)")) {
				
					ExtentTest testCaseScenario = testCaseName.createNode("Paper2 sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Paper2 sec mark is: " + p2);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  sec marks is: " + p2);
						
						
				}
				// Use the value
			} catch (NumberFormatException e) {

				if (p2.trim().equals("AB") || p2.trim().equals("NA") ||
						p2.trim().equals("NE") || 
						p2.trim().equals("---") || 
						p2.equals("NE (AT)")) {
					ExtentTest testCaseScenario = testCaseName.createNode("Paper2 sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Paper2 sec mark is: " + p2);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  sec marks is: " + p2);
				
				}}
			
			
			
			try {
				
				if (!theoryThSecMark.equals("NA")||!theoryThSecMark.equals("AB")||!theoryThSecMark.equals("NE") ||!  theoryThSecMark.equals("NE (AT)")) {
				theoryInternalMaxMark = Double.parseDouble(theoryThMaxMark);
				paper1Mark = Double.parseDouble(theoryThSecMark) ;
				
				
				System.out.println(theoryThMaxMark);
				System.out.println(theoryThSecMark);
				
				
				 verifyScore(paper1Mark,theoryInternalMaxMark,0.40);
			
			
			
				
				checkMarks(Regno, "Theory Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
						theoryExam, subjectToFind, examTotal, theoryThSecMark,
						theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
				}	// Use the value
			} catch (NumberFormatException e) {

				if (theoryThSecMark.equals("AB") || 
						theoryThSecMark.equals("NE") || 
						theoryThSecMark.equals("---") || 
						theoryThSecMark.equals("NE (AT)")) {
					paper1Mark = 0.0;
					Paper1 = 0.0;
					System.out.println(paper1Mark);

				ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
				
				testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThSecMark);
				
				System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThSecMark);
					
			
				
				}
				else {
					paper1Mark = 0.0;
					Paper1 = 0.0;
					System.out.println(paper1Mark);

				ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
				
				testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThSecMark);
				
				System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryThSecMark);
											
					
				}
			
			}

		
			try {
				if (!intSecMarks.equals("NA")) {
				theoryMaxMark = Double.parseDouble(intMaxMarks);
				paper2Mark = Double.parseDouble(intSecMarks);
				 verifyScore(paper2Mark,theoryMaxMark,0.35);
				
				// Check Theory (Univ) Sec. Marks
				checkMarks(Regno, "Theory (Univ) Sec. Marks", paper1, paper2, paper3, praticalExam,
						theoryExam, subjectToFind, examTotal, intSecMarks, theoryMaxMark,
						testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

				}	// Use the value
			} catch (NumberFormatException e) {

				if (intSecMarks.equals("AB") || 
						intSecMarks.equals("NE") || 
						intSecMarks.equals("---") || 
						intSecMarks.equals("NE (AT)")) {
					paper2Mark = 0.0;
					Paper2 =0.0;
					System.out.println(paper2Mark);
				
			
					 ExtentTest testCaseScenario = testCaseName.createNode("Theory Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
					
					
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Therory Univ Sec. Marks is: " + intSecMarks);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Therory Univ Sec. Marks is: " + intSecMarks);
							 
				}
				
				else {
					paper2Mark = 0.0;
					Paper2 =0.0;
					System.out.println(paper2Mark);
				
			
					 ExtentTest testCaseScenario = testCaseName.createNode("Theory Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
					
					
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Therory Univ Sec. Marks is: " + intSecMarks);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Therory Univ Sec. Marks is: " + intSecMarks);
							 
				}

				}
		
			try {
				if (!theoryThPlusIntSecMark.equals("NA")) {

				praticalMaxMark = Double.parseDouble(theoryThPlusIntMaxMark);
				paper3Mark = Double.parseDouble(theoryThPlusIntSecMark);
				Paper3 = Double.parseDouble(theoryThPlusIntSecMark);
				 verifyScore(paper3Mark,praticalMaxMark,0.40);
				
			
//				System.out.println("paper 3 mark in check marks :" + paper3Mark);
//
//				System.out.println("paper 3 mark in check marks :" + Paper3);
//
//				System.out.println(Paper3);
				
				// Use the value
				// Check pratical internal Sec. Marks
				checkMarks(Regno, "Pratical Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
						theoryExam, subjectToFind, examTotal, theoryThPlusIntSecMark, praticalMaxMark,
						testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
				}
			} catch (NumberFormatException e) {
			
				//if the any of the marks is this data need to convert to 0 or else it will thow error
				
				if (theoryThPlusIntSecMark.equals("AB") || 
						theoryThPlusIntSecMark.equals("NE") || 
						theoryThPlusIntSecMark.equals("---") || 
						theoryThPlusIntSecMark.equals("NE (AT)")) {
					paper3Mark = 0.0;
					Paper3 =0.0;
					
					System.out.println(paper3Mark);
					 ExtentTest testCaseScenario = testCaseName.createNode("Pratical internal Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
						
						
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Practical Internal Sec. Marks is: " + theoryThPlusIntSecMark);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Practical Internal Sec. Marks is:" + theoryThPlusIntSecMark);
							 
				}else {
					
					paper3Mark = 0.0;
					Paper3 =0.0;
					
					System.out.println(paper3Mark);
					 ExtentTest testCaseScenario = testCaseName.createNode("Pratical internal Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
						
						
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Practical Internal Sec. Marks is: " + theoryThPlusIntSecMark);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Practical Internal Sec. Marks is:" + theoryThPlusIntSecMark);
	
					
				}
			}

		
			
		try {
				
				if (!practicalPractical.equals("NA")||!practicalPractical.equals("AB")||!practicalPractical.equals("NE") ||!  practicalPractical.equals("NE (AT)")) {
				
					ExtentTest testCaseScenario = testCaseName.createNode("Pratical sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" pratical sec mark is: " + practicalPractical);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  pratical sec marks is: " + practicalPractical);
						
						
				}
				// Use the value
			} catch (NumberFormatException e) {

				if (practicalPractical.trim().equals("AB") || practicalPractical.trim().equals("NA") ||
						practicalPractical.trim().equals("NE") || 
						practicalPractical.trim().equals("---") || 
						practicalPractical.equals("NE (AT)")) {
					ExtentTest testCaseScenario = testCaseName.createNode("Pratical sec. marks validation for the subject " + subject +" Test case has started running");
					
					testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" pratical sec mark is: " + practicalPractical);
					
					System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  pratical sec marks is: " + practicalPractical);
					
				}}
				
		
		try {
			
			if (!practicalViva.trim().equals("NA")||!practicalViva.trim().equals("AB")||!practicalViva.trim().equals("NE") ||!  practicalViva.equals("NE (AT)")) {
			
				ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");
				
				testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" pratical viva sec mark is: " + practicalViva);
				
				System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  pratical viva sec marks is: " + practicalViva);
					
					
			}
			// Use the value
		} catch (NumberFormatException e) {

			if (practicalViva.trim().equals("AB") || practicalViva.trim().equals("NA") ||
					practicalViva.trim().equals("NE") || 
					practicalViva.trim().equals("---") || 
					practicalViva.equals("NE (AT)")) {
				ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");
				
				testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" pratical viva sec mark is: " + practicalViva);
				
				System.out.println("The following Register number " + Regno +" for the subject "+ subject +"  pratical viva sec marks is: " + practicalViva);
					
			}}
				
			try {
				
				if (!practicalPlusVivaSecMark.equals("NA")) {
				praticalTotalMaxMark = Double.parseDouble(practicalPlusVivaMaxMark);
				praticalTotalSecMark = Double.parseDouble(practicalPlusVivaSecMark);
				 verifyScore(praticalTotalSecMark,praticalTotalMaxMark,0.50);
					// Use the value
				// Check pratical internal Sec. Marks
				checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
						theoryExam, subjectToFind, examTotal, practicalPlusVivaSecMark,
						praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
				}
			} catch (NumberFormatException e) {

				if (practicalPlusVivaSecMark.equals("AB") || 
						practicalPlusVivaSecMark.equals("NE") || 
						practicalPlusVivaSecMark.equals("---") || 
						practicalPlusVivaSecMark.equals("NE (AT)")) {
					praticalTotalSecMark = 0.0;
					PraticalExamTotal =0.0;
					System.out.println(praticalTotalSecMark);
				

				 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
					
					
				 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
						 + " Practical Univ Sec. Marks is:" + practicalPlusVivaSecMark);
				
				 System.out.println("\nThe Following Registration number " + Regno
							+ " Practical Univ Sec. Marks is:" + practicalPlusVivaSecMark);
					
				// Handle gracefully, e.g., assign default value or log an error

				}
				
				else {
					praticalTotalSecMark = 0.0;
					PraticalExamTotal =0.0;
					System.out.println(praticalTotalSecMark);
				
					ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
						
						
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Practical Univ Sec. Marks is:" + practicalPlusVivaSecMark);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Practical Univ Sec. Marks is:" + practicalPlusVivaSecMark);
						
					
				}
			}

			try {
				if (!theoryPlusPracticalSecMark.equals("NA")) {
					theoryInternalMaxMark = Double.parseDouble(theoryPlusPracticalMaxMark);
				ExamTotalScore = Double.parseDouble(theoryPlusPracticalSecMark);
			
				 verifyScore(ExamTotalScore,theoryInternalMaxMark,0.50);
				
				// Check Grand Total Sec. Marks (assumed max marks as 200)
				checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
						praticalExam, theoryExam, subjectToFind, examTotal, theoryPlusPracticalSecMark,
						theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
				}
				// Use the value
			} catch (NumberFormatException e) {
				if (theoryPlusPracticalSecMark.equals("AB") || 
						theoryPlusPracticalSecMark.equals("NE") || 
theoryPlusPracticalSecMark.equals("---") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") || theoryPlusPracticalSecMark.equals("NA") ||
						theoryPlusPracticalSecMark.equals("NE (AT)")) {
					ExamTotalScore = 0.0;
					System.out.println(ExamTotalScore);
				

					 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
						
						
					 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
							 + " Theory plus Pratical Sec. Marks is: " + theoryPlusPracticalSecMark);
					
					 System.out.println("\nThe Following Registration number " + Regno
								+ " Theory plus Pratical Sec. Marks is:" + theoryPlusPracticalSecMark);
						

			}else{
				
				ExamTotalScore = 0.0;
				System.out.println(ExamTotalScore);
			

				 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
					
					
				 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
						 + " Theory plus Pratical Sec. Marks is: " + theoryPlusPracticalSecMark);
				
				 System.out.println("\nThe Following Registration number " + Regno
							+ " Theory plus Pratical Sec. Marks is:" + theoryPlusPracticalSecMark);
					}
				
				
			}
			
			catch(Exception e1) {
				
				
				
			}
		

		}//while	
		
	}
		
	}//else if
						
		
		else if(matcher1.group().contains("BDS")) {

			String bdsSubject =matcher.group(1);
			
	//		System.out.println("bdsSubject:" +bdsSubject);
			
		

			String[] bdsSubjects = bdsSubject.split("\\n");
			
			System.out.println(bdsSubjects[0]);
			System.out.println(bdsSubjects[1]);
			String subjectName = bdsSubjects[bdsSubjects.length - 1];  // second-to-last line
		//	System.out.println(subjectName);

			
	
			String[] bdsSubjectsWithMark = subjectName.split("(?=\\d+$)");  // Splits before the number
			
			subject = bdsSubjectsWithMark[0];
			System.out.println(bdsSubjectsWithMark[0]); // ORAL MEDICINE AND RADIOLOGY
		//	System.out.println(bdsSubjectsWithMark[1]); // 100
		
			
			System.out.println("subejekjsdkj" + bdsSubjectsWithMark);
			
			String bdsTheoryThPlusIntVivaMaxMark = bdsSubjectsWithMark[1];
			
			String bdsTheoryInt =matcher.group(2);
			String bdsTheoryTh = matcher.group(3);
			String bdsTheoryThViva = matcher.group(4);
			String bdsTheoryThPlusIntVivaSecMark = matcher.group(5);
   String practicalPlusIntMaxMark = matcher.group(6);
  
   String practicalInt = matcher.group(7);
   String bdsPracticalPractical = matcher.group(8);
   String practicalVivaPR = matcher.group(9);
   String practicalTotalSecMarks = matcher.group(10);				   

  String thPlusPracticalMaxMark =  matcher.group(11);
  String thPlusPracticalSecMark = matcher.group(12);
	status =	 matcher.group(13); 


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
	
	if (!bdsTheoryInt.equals("NA")||!bdsTheoryInt.equals("AB")||!bdsTheoryInt.equals("NE") ||!  bdsTheoryInt.equals("NE (AT)")) {
	
		ExtentTest testCaseScenario = testCaseName.createNode("Therory Int sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);
		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);
			
			
	}
	// Use the value
} catch (NumberFormatException e) {

	if (bdsTheoryInt.trim().equals("AB") || 
			bdsTheoryInt.trim().equals("NE") || 
			bdsTheoryInt.trim().equals("---") || 
			bdsTheoryInt.equals("NE (AT)")) {
		ExtentTest testCaseScenario = testCaseName.createNode("Therory Int sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);
		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);
		

	
	}}

try {
if (!bdsTheoryTh.equals("NA")||!bdsTheoryTh.equals("AB")||!bdsTheoryTh.equals("NE") ||!  bdsTheoryTh.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory TH sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);
}	
	

//Use the value
} catch (NumberFormatException e) {

if (bdsTheoryTh.trim().equals("AB") || 
	bdsTheoryTh.trim().equals("NE") || 
	bdsTheoryTh.trim().equals("---") || 
	bdsTheoryTh.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory TH sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);
}	}

try {
if (!bdsTheoryThViva.equals("NA")||!bdsTheoryThViva.equals("AB")||!bdsTheoryThViva.equals("NE") ||!bdsTheoryThViva.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory Viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);
}	
	

//Use the value
} catch (NumberFormatException e) {

if (bdsTheoryThViva.trim().equals("AB") || 
	bdsTheoryThViva.trim().equals("NE") || 
	bdsTheoryThViva.trim().equals("---") || 
	bdsTheoryThViva.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Therory Viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);
}	
}

try {

if (!bdsTheoryThPlusIntVivaSecMark.equals("NA")) {
	theoryInternalMaxMark = Double.parseDouble(bdsTheoryThPlusIntVivaMaxMark);
	paper1Mark = Double.parseDouble(bdsTheoryThPlusIntVivaSecMark) ;
	
	 verifyScore(paper1Mark,theoryInternalMaxMark,0.50);
	

	
	checkMarks(Regno, "Theory Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
			theoryExam, subjectToFind, examTotal, bdsTheoryThPlusIntVivaSecMark,
			theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
}

} catch (NumberFormatException e) {

if (bdsTheoryThPlusIntVivaSecMark.equals("AB") || 
		bdsTheoryThPlusIntVivaSecMark.equals("NE") || 
		bdsTheoryThPlusIntVivaSecMark.equals("---") || 
		bdsTheoryThPlusIntVivaSecMark.equals("NE (AT)")) {
	paper1Mark = 0.0;
	Paper1 = 0.0;
	System.out.println(paper1Mark);
	
	 ExtentTest testCaseScenario = testCaseName.createNode("Th+int viva Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
		
		
	 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
			 + " Th + int viva Sec. Marks is:" + bdsTheoryThPlusIntVivaSecMark);
	
	 System.out.println("\nThe Following Registration number " + Regno
				+ " Th + int viva Sec. Marks is:" + bdsTheoryThPlusIntVivaSecMark);



}}


try {
if (!practicalInt.equals("NA")||!practicalInt.equals("AB")||!practicalInt.equals("NE") ||!practicalInt.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Practical Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);
}	
	

//Use the value
} catch (NumberFormatException e) {

if (practicalInt.trim().equals("AB") || 
	practicalInt.trim().equals("NE") || 
	practicalInt.trim().equals("---") || 
	practicalInt.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Practical Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);



}}


try {
if (!bdsPracticalPractical.equals("NA")||!bdsPracticalPractical.equals("AB")||!bdsPracticalPractical.equals("NE") ||!bdsPracticalPractical.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical pratical sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);
}	
	

//Use the value
} catch (NumberFormatException e) {

if (bdsPracticalPractical.trim().equals("AB") || 
	bdsPracticalPractical.trim().equals("NE") || 
	bdsPracticalPractical.trim().equals("---") || 
	bdsPracticalPractical.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Pratical pratical sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);


}}

try {
if (!practicalVivaPR.equals("NA")||!practicalVivaPR.equals("AB")||!practicalVivaPR.equals("NE") ||!practicalVivaPR.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);
}	
	

//Use the value
} catch (NumberFormatException e) {

if (practicalVivaPR.trim().equals("AB") || 
	practicalVivaPR.trim().equals("NE") || 
	practicalVivaPR.trim().equals("---") || 
	practicalVivaPR.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);
}	

}

try {
	
	if (!practicalTotalSecMarks.equals("NA")||!practicalTotalSecMarks.equals("AB")||!practicalTotalSecMarks.equals("NE") ||!  practicalTotalSecMarks.equals("NE (AT)")) {
	
	
	praticalTotalMaxMark = Double.parseDouble(practicalPlusIntMaxMark);
	praticalTotalSecMark = Double.parseDouble(practicalTotalSecMarks);
	
	 verifyScore(praticalTotalSecMark,praticalTotalMaxMark,0.50);
	}	// Use the value
	// Check pratical internal Sec. Marks
	checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
			theoryExam, subjectToFind, examTotal, practicalTotalSecMarks,
			praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

	// Use the value
} catch (NumberFormatException e) {

	if (practicalTotalSecMarks.equals("AB") || 
			practicalTotalSecMarks.equals("NE") || 
			practicalTotalSecMarks.equals("---") || 
			practicalTotalSecMarks.equals("NE (AT)")) {

	praticalTotalSecMark = 0.0;
	PraticalExamTotal =0.0;
	System.out.println(praticalTotalSecMark);


	ExtentTest testCaseScenario = testCaseName.createNode("Pratical Total sec. marks validation for the subject " + subject +" Test case has started running");
	
	testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" pratical total sec marks is: " + practicalTotalSecMarks);
	
	System.out.println("The following Register number " + Regno +" for the subject "+ subject +" pratical total sec marks is: " + practicalTotalSecMarks);
		
	// Handle gracefully, e.g., assign default value or log an error
	
	}}


try {
	if (!thPlusPracticalSecMark.equals("NA")) {
		theoryInternalMaxMark = Double.parseDouble(thPlusPracticalMaxMark);
	ExamTotalScore = Double.parseDouble(thPlusPracticalSecMark);

	 verifyScore(ExamTotalScore,theoryInternalMaxMark,0.50);
	
	// Check Grand Total Sec. Marks (assumed max marks as 200)
	checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
			praticalExam, theoryExam, subjectToFind, examTotal, thPlusPracticalSecMark,
			theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
	}
	// Use the value
} catch (NumberFormatException e) {
	if (thPlusPracticalSecMark.equals("AB") || 
			thPlusPracticalSecMark.equals("NE") || 
			thPlusPracticalSecMark.equals("---") || 
			thPlusPracticalSecMark.equals("NE (AT)")) {
		ExamTotalScore = 0.0;
		System.out.println(ExamTotalScore);
	

		 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
			
			
		 testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
				 + " Theory plus Pratical Sec. Marks is: " + thPlusPracticalSecMark);
		
		 System.out.println("\nThe Following Registration number " + Regno
					+ " Theory plus Pratical Sec. Marks is:" + thPlusPracticalSecMark);
			

}}







}
						
		}}
						
						catch(Exception e) {
					
						} //try
					
					}						
					}
//	    System.out.println("==============");
//     	securedMarks(Regno,examTotal,testCaseName);
//        System.out.println("==============");
	}
				
		}
	
	
	public void bdsPatternProcess(Object Regno, File latestFile, Object paper1, Object paper2,
			Object paper3, Object praticalExam, Object theoryExam, Object examTotal, String subjectToFind,
			ExtentTest testCaseName,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva) throws IOException, Exception {
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

					//TO print the text
					
					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					System.out.println(text);
					
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
			        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
						
						testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found");
						
						System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
					
			        	
			        	System.out.println("No match found.");
					}

			    
			      //  String regex = "(M\\.Sc\\.|B\\.Sc\\.|BPT)\\s+";
			        String regex = "(BDS)\\s+";
			        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			        matcher1 = pattern.matcher(text);

			        if (matcher1.find()) {
			            System.out.println("Match found Course: " + matcher1.group());
			        } else {
			        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
						
						testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found",	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
						System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
					
			        	
			        	System.out.println("No match found.");
			        }

						
			        
			        // Match "M.Sc." or "B.Sc."
			        Pattern bdsPattern = Pattern.compile(
			        	
//			        	
			         
			        			//    "(?s)^(?:((?:\\d{1,3}\\s*\\R)?(?:(?!Registration No|Date|Name of the Exam|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical)[^\\d\\n].*?(?:\\R(?!Registration No|Date|Name of the Exam|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical)[^\\d\\n].*?)*)?)\\s+)?"
			        	//	"(?m)^(?!.*(?:Registration No|Date|Name of the Exam|Secured Marks|Secured Marks in Words|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical)).+"
			        	  "(?m)^(?!.*(?:Registration No|Date|Name of the Exam|Secured Marks|Secured Marks in Words|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical))" +
			        	    "(.+?)\\s+" 	
			        	
			        	+"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
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
				        			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
				        			    Pattern.DOTALL | Pattern.MULTILINE
				        			);

			    	Matcher matcher = bdsPattern.matcher(text);
			  
			
			   while (matcher.find()) {	    	
			    			 if(matcher1.group().contains("BDS")) {
			 

			    					
			    					System.out.println("theroryInt: " + theoryInt);
			    					System.out.println("theroryTh: " + theoryTh);
			    					System.out.println("praticalInt: " + praticalInt);
			    					System.out.println("praticalPractical: " + praticalPractical);
			    					System.out.println("praticalVivas: " + praticalViva);
//			 System.out.println("sad" + matcher.group());

		
			 String bdsSubject =matcher.group(1);
			    					
			    					
			    					
			System.out.println("bdsSubject:" +bdsSubject);
			    					
			    				

			String[] bdsSubjects = bdsSubject.split("\\n");		
			
			
			
			String bdsTheoryThPlusIntVivaMaxMark="";
			
			System.out.println(bdsSubjects[0]);
		//	System.out.println(bdsSubjects[1]);	
		//	System.out.println(bdsSubjects[2]);
			
			if(bdsSubjects[2] !=null) {
				
				
				subject = bdsSubjects[0]+" "+bdsSubjects[1];
				bdsTheoryThPlusIntVivaMaxMark = bdsSubjects[2];
				
				System.out.println(bdsTheoryThPlusIntVivaMaxMark+"bdsTheoryThPlusIntVivaMaxMark");
			}
			else {
				
				System.out.println("subject"+subject);
				

				
	
				String subjectName = bdsSubjects[bdsSubjects.length - 1];  // second-to-last line
				//	System.out.println(subjectName);

				String[] bdsSubjectsWithMark = subjectName.split(" (?=\\d+$)");  // Splits before the number
				
				subject = bdsSubjectsWithMark[0];
				System.out.println(bdsSubjectsWithMark[0]); // ORAL MEDICINE AND RADIOLOGY
		//		System.out.println(bdsSubjectsWithMark[1]); // 100
			
				System.out.println("subejekjsdkj" + bdsSubjectsWithMark);
				
				bdsTheoryThPlusIntVivaMaxMark = bdsSubjectsWithMark[0];
			
			}
			
	
			
	
		
			
		
			
			String bdsTheoryInt =matcher.group(2);
			String bdsTheoryTh = matcher.group(3);
			String bdsTheoryThViva = matcher.group(4);
			String bdsTheoryThPlusIntVivaSecMark = matcher.group(5);
   String practicalPlusIntMaxMark = matcher.group(6);
  
   String practicalInt = matcher.group(7);
   String bdsPracticalPractical = matcher.group(8);
   String practicalVivaPR = matcher.group(9);
   String practicalTotalSecMarks = matcher.group(10);				   

  String thPlusPracticalMaxMark =  matcher.group(11);
  String thPlusPracticalSecMark = matcher.group(12);
	status =	 matcher.group(13); 
	
	int check = matcher.group().length();
	
	System.out.println(check+"check");

	 System.out.println("Match found at: " + matcher.start() + " to " + matcher.end());	 
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

if (!bdsTheoryInt.trim().equals("NA")||!bdsTheoryInt.trim().equals("AB")||!bdsTheoryInt.trim().equals("NE") ||!  bdsTheoryInt.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);


}
//Use the value
} catch (NumberFormatException e) {

if (bdsTheoryInt.trim().equals("AB") || 
bdsTheoryInt.trim().equals("NE") || 
bdsTheoryInt.trim().equals("---") || 
bdsTheoryInt.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Therory Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Int sec marks is: " + bdsTheoryInt);



}}

try {
if (!bdsTheoryTh.trim().equals("NA")||!bdsTheoryTh.trim().equals("AB")||!bdsTheoryTh.trim().equals("NE") ||!  bdsTheoryTh.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory TH sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);
}	


//Use the value
} catch (NumberFormatException e) {

if (bdsTheoryTh.trim().equals("AB") || 
bdsTheoryTh.trim().equals("NE") || 
bdsTheoryTh.trim().equals("---") || 
bdsTheoryTh.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory TH sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory TH sec marks is: " + bdsTheoryTh);
}	}

try {
if (!bdsTheoryThViva.trim().equals("NA")||!bdsTheoryThViva.trim().equals("AB")||!bdsTheoryThViva.trim().equals("NE") ||!bdsTheoryThViva.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Therory Viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);
}	


//Use the value
} catch (NumberFormatException e) {

if (bdsTheoryThViva.trim().equals("AB") || 
bdsTheoryThViva.trim().equals("NE") || 
bdsTheoryThViva.trim().equals("---") || 
bdsTheoryThViva.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Therory Viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Therory Viva sec marks is: " + bdsTheoryThViva);
}	
}





try {

if (!bdsTheoryThPlusIntVivaSecMark.trim().equals("NA")||!bdsTheoryThPlusIntVivaSecMark.equals("AB") || 
		!bdsTheoryThPlusIntVivaSecMark.equals("NE") || 
		!bdsTheoryThPlusIntVivaSecMark.equals("---") || 
		!bdsTheoryThPlusIntVivaSecMark.equals("NE (AT)")) {
praticalTotalMaxMark = Double.parseDouble(bdsTheoryThPlusIntVivaMaxMark);
praticalTotalSecMark = Double.parseDouble(bdsTheoryThPlusIntVivaSecMark);

 verifyScore(praticalTotalSecMark,praticalTotalMaxMark,0.50);
}	// Use the value
// Check pratical internal Sec. Marks
checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
		theoryExam, subjectToFind, examTotal, bdsTheoryThPlusIntVivaSecMark,
		praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

} catch (NumberFormatException e) {

if (bdsTheoryThPlusIntVivaSecMark.equals("AB") || bdsTheoryThPlusIntVivaSecMark.equals("NA")||
		bdsTheoryThPlusIntVivaSecMark.equals("NE") || 
		bdsTheoryThPlusIntVivaSecMark.equals("---") || 
		bdsTheoryThPlusIntVivaSecMark.equals("NE (AT)")) {
	praticalTotalSecMark = 0.0;
	PraticalExamTotal =0.0;
	System.out.println(praticalTotalSecMark);


 ExtentTest testCaseScenario = testCaseName.createNode("Th+int viva Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
	
	
 testCaseScenario.log(Status.PASS,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
		 + " Th + int viva Sec. Marks is:" + bdsTheoryThPlusIntVivaSecMark);

 System.out.println("\nThe Following Registration number " + Regno
			+ " Th + int viva Sec. Marks is:" + bdsTheoryThPlusIntVivaSecMark);
	
// Handle gracefully, e.g., assign default value or log an error

}}





try {
if (!practicalInt.equals("NA")||!practicalInt.equals("AB")||!practicalInt.equals("NE") ||!practicalInt.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Practical Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);
}	


//Use the value
} catch (NumberFormatException e) {

if (practicalInt.trim().equals("AB") || 
practicalInt.trim().equals("NE") || 
practicalInt.trim().equals("---") || 
practicalInt.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Practical Int sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Int sec marks is: " + practicalInt);



}}


try {
if (!bdsPracticalPractical.equals("NA")||!bdsPracticalPractical.equals("AB")||!bdsPracticalPractical.equals("NE") ||!bdsPracticalPractical.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical pratical sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);
}	


//Use the value
} catch (NumberFormatException e) {

if (bdsPracticalPractical.trim().equals("AB") || 
bdsPracticalPractical.trim().equals("NE") || 
bdsPracticalPractical.trim().equals("---") || 
bdsPracticalPractical.equals("NE (AT)")) {
ExtentTest testCaseScenario = testCaseName.createNode("Pratical pratical sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical pratical sec marks is: " + bdsPracticalPractical);


}}

try {
if (!practicalVivaPR.trim().equals("NA")||!practicalVivaPR.trim().equals("AB")||!practicalVivaPR.trim().equals("NE") ||!practicalVivaPR.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);
}	


//Use the value
} catch (NumberFormatException e) {

if (practicalVivaPR.trim().equals("AB") || 
practicalVivaPR.trim().equals("NE") || 
practicalVivaPR.trim().equals("---") || 
practicalVivaPR.equals("NE (AT)")) {

ExtentTest testCaseScenario = testCaseName.createNode("Pratical viva sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.INFO,"The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical viva sec marks is: " + practicalVivaPR);
}	

}


try {

if (!practicalTotalSecMarks.equals("NA")||!practicalTotalSecMarks.equals("AB")||!practicalTotalSecMarks.equals("NE") ||!  practicalTotalSecMarks.equals("NE (AT)")) {
theoryInternalMaxMark = Double.parseDouble(practicalPlusIntMaxMark);
paper1Mark = Double.parseDouble(practicalTotalSecMarks) ;

 verifyScore(paper1Mark,theoryInternalMaxMark,0.50);
}



checkMarks(Regno, "Theory Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
		theoryExam, subjectToFind, examTotal, practicalTotalSecMarks,
		theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
// Use the value
} catch (NumberFormatException e) {

if (practicalTotalSecMarks.equals("AB") || 
		practicalTotalSecMarks.equals("NE") || 
		practicalTotalSecMarks.equals("---") || 
		practicalTotalSecMarks.equals("NE (AT)")) {
	paper1Mark = 0.0;
	Paper1 = 0.0;
	System.out.println(paper1Mark);

ExtentTest testCaseScenario = testCaseName.createNode("Pratical Total sec. marks validation for the subject " + subject +" Test case has started running");

testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" pratical total sec marks is: " + practicalTotalSecMarks);

System.out.println("The following Register number " + Regno +" for the subject "+ subject +" pratical total sec marks is: " + practicalTotalSecMarks);
	


}}



try {
if (!thPlusPracticalSecMark.equals("NA")) {
theoryInternalMaxMark = Double.parseDouble(thPlusPracticalMaxMark);
ExamTotalScore = Double.parseDouble(thPlusPracticalSecMark);

verifyScore(ExamTotalScore,theoryInternalMaxMark,0.50);

//Check Grand Total Sec. Marks (assumed max marks as 200)
checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
praticalExam, theoryExam, subjectToFind, examTotal, thPlusPracticalSecMark,
theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
}
//Use the value
} catch (NumberFormatException e) {
if (thPlusPracticalSecMark.equals("AB") || 
thPlusPracticalSecMark.equals("NE") || 
thPlusPracticalSecMark.equals("---") || 
thPlusPracticalSecMark.equals("NE (AT)")) {
ExamTotalScore = 0.0;
System.out.println(ExamTotalScore);


ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");


testCaseScenario.log(Status.INFO,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
	 + " Theory plus Pratical Sec. Marks is: " + thPlusPracticalSecMark);

System.out.println("\nThe Following Registration number " + Regno
		+ " Theory plus Pratical Sec. Marks is:" + thPlusPracticalSecMark);


}}







}//if
						
		 }//if
		 else {
			 System.out.println("check the pattern");
		 }
		 
}//while		
			
				
				}//for
				
			
			}//try
						
						catch(Exception e) {
							e.printStackTrace();
						}
			
		
		
		}//if
					
//	    System.out.println("==============");
//     	securedMarks(Regno,examTotal,testCaseName);
//        System.out.println("==============");

			
	}//method
		
		
	
		
		
		
		
	
	public void processFourSubjectPatternPdf(Object Regno, File latestFile, Object paper1, Object paper2, Object paper3,
			Object praticalExam, Object theoryExam, Object examTotal, String subjectToFind, ExtentTest testCaseName,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva)
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

					Pattern registrationPattern = Pattern.compile("Registration No :\\s*(\\d+)");
					Pattern securedMarksPattern = Pattern.compile("Secured Marks\\s*:\\s*(\\d+)");

					Matcher regMatcher = registrationPattern.matcher(text);
					Matcher securedMatcher = securedMarksPattern.matcher(text);

					if (regMatcher.find()) {
						// Capture the matched number
						String extractedNumber1 = regMatcher.group(1);
						registrationNumber = Long.parseLong(extractedNumber1);

						System.out.println("Registration No: " + registrationNumber);
					}

					if (securedMatcher.find()) {
						String securedMarks = securedMatcher.group(1); // Extract the number
						securedMark = Long.parseLong(securedMarks);

						System.out.println("Secured Marks: " + securedMark);

					} else {
			        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
						
						testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found");
						
						System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
					
			        	
			        	System.out.println("No match found.");
					}

					Pattern fourPattern = Pattern.compile("^(.*?)\\s+" + // Subject name (Group 1)
							"(\\d+)\\s+(\\d+|NA)\\s+" + // Theory Max Marks (Group 2) | Theory Sec Marks (Group 3)
							"(NA|\\d+)\\s+(NA|\\d+)\\s+" + // Practical Max Marks (Group 4) | Practical Sec Marks (Group
															// 5)
							"(NA|\\d+)\\s+(NA|\\d+)\\s+" + // Practical+Viva Max Marks (Group 6) | Practical+Viva Sec
															// Marks (Group
															// 7)
							"(\\d+)\\s+(\\d+)\\s+" + // Grand Total Max Marks (Group 8) | Grand Total Sec Marks (Group
														// 9)
							"(Pass|Fail|AP|NE|AB)", // Status
							Pattern.DOTALL);

					// Extract and print each row

					Matcher fourPatternMatcher = fourPattern.matcher(text);

					while (fourPatternMatcher.find()) {

						subject = fourPatternMatcher.group(1);

						String theoryMaxMarks = fourPatternMatcher.group(2); // Take the last part

						String theorySecMarks = fourPatternMatcher.group(3);
						String praticalMaxMarks = fourPatternMatcher.group(4);
						String praticalVivaMaxMarks = fourPatternMatcher.group(5);
						String praticalTotalMaxMarks = fourPatternMatcher.group(6);
						String praticalTotalSecMarks = fourPatternMatcher.group(7);
						String grandTotalMaxMarks = fourPatternMatcher.group(8);
						String grandTotalSecMarks = fourPatternMatcher.group(9);
						status = fourPatternMatcher.group(10);

						// Check if this is the subject we are looking for

						System.out.println("==============");

						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

						// Check if group(6) exists (to avoid IndexOutOfBoundsException)
						if (fourPatternMatcher.groupCount() >= 6) {
							System.out.println("Practical Viva Max Marks: " + praticalVivaMaxMarks);
						} else {
							System.out.println("Practical Viva Max Marks: NA"); // Handle cases where it's missing
						}

						// Practical Total Marks
						System.out.println("Practical Total Max Marks: " + praticalTotalMaxMarks);
						System.out.println("Practical Total Sec Marks: " + praticalTotalSecMarks);

						// Grand Total
						System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
						System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

						// Status
						System.out.println("Status: " + status);
						System.out.println("==============");

						if (status.equals("Pass") || status.equals("Fail") || status.equals("AP")) {

							try {
								theoryInternalMaxMark = Double.parseDouble(theoryMaxMarks);

								System.out.println(theoryInternalMaxMark);

								paper1Mark = Double.parseDouble(theorySecMarks);

								System.out.println(paper1Mark);

								checkMarks(Regno, "Theory Internal Sec Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, theorySecMarks, theoryInternalMaxMark,
										testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								// Use the value

							} catch (NumberFormatException e) {

								System.out.println("\nThe Following Registration number " + Regno
										+ " Pratical Internal Sec. Marks: " + theorySecMarks);
							}
							/*
							 * try { theoryMaxMark = Double.parseDouble(theoryUnivMaxMarks); paper2Mark =
							 * Double.parseDouble(theoryUnivSecMarks);
							 * 
							 * // Check Theory (Univ) Sec. Marks checkMarks(Regno,
							 * "Theory (Univ) Sec. Marks", paper1, paper2, paper3, praticalExam, theoryExam,
							 * subjectToFind, examTotal, theoryInternalSecMarks, theoryInternalMaxMark,
							 * testCaseName);
							 * 
							 * // Use the value } catch (NumberFormatException e) {
							 * 
							 * System.out.println("\nThe Following Registration number " + Regno +
							 * " Therory Univ Sec. Marks:" + theoryUnivSecMarks); // Handle gracefully,
							 * e.g., assign default value or log an error
							 * 
							 * }
							 * 
							 * try { praticalMaxMark = Double.parseDouble(practicalInternalMaxMarks);
							 * paper3Mark = Double.parseDouble(practicalInternalSecMarks); // Use the value
							 * // Check pratical internal Sec. Marks checkMarks(Regno,
							 * "Pratical Internal Sec. Marks", paper1, paper2, paper3, praticalExam,
							 * theoryExam, subjectToFind, examTotal, theoryInternalSecMarks,
							 * theoryInternalMaxMark, testCaseName);
							 * 
							 * } catch (NumberFormatException e) {
							 * System.out.println("\nThe Following Registration number " + Regno +
							 * " Pratical Internal Sec. Marks: " + practicalInternalSecMarks); // Handle
							 * gracefully, e.g., assign default value or log an error
							 * 
							 * }
							 * 
							 * try { praticalTotalMaxMark = Double.parseDouble(practicalUnivMaxMarks);
							 * praticalTotalSecMark = Double.parseDouble(practicalUnivSecMarks); // Use the
							 * value // Check pratical internal Sec. Marks checkMarks(Regno,
							 * "Pratical (Univ)Sec. Marks", paper1, paper2, paper3, praticalExam,
							 * theoryExam, subjectToFind, examTotal, practicalUnivSecMarks,
							 * theoryInternalMaxMark, testCaseName);
							 * 
							 * } catch (NumberFormatException e) {
							 * System.out.println("\nThe Following a Registration number " + Regno +
							 * " Pratical (Univ)Sec. Marks is: " + practicalUnivSecMarks);
							 * 
							 * // Handle gracefully, e.g., assign default value or log an error
							 * 
							 * }
							 * 
							 * try { grandTotalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
							 * ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
							 * 
							 * // Check Grand Total Sec. Marks (assumed max marks as 200) checkMarks(Regno,
							 * "\n Theory plus pratical Sec. Marks", paper1, paper2, paper3, praticalExam,
							 * theoryExam, subjectToFind, examTotal, theoryInternalSecMarks,
							 * theoryInternalMaxMark, testCaseName);
							 * 
							 * // Use the value } catch (NumberFormatException e) {
							 * 
							 * // Handle gracefully, e.g., assign default value or log an error
							 * System.out.println("\nThe Following a Registration number " + Regno +
							 * " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
							 * 
							 */ }

						// Check Theory Internal Sec. Marks

						// Stop after printing one subject
					}
				}

			}

		}

		// Check for invalid marks before parsing

//	    System.out.println("==============");
//     	securedMarks(Regno,examTotal,testCaseName);
//        System.out.println("==============");
	}

	private boolean extractAndPrintSingleSubject(String text, String subjectToFind) {
		Pattern fourPattern = Pattern.compile("^(.*?)\\s+" + // Subject name (Group 1)
				"(\\d+)\\s+(\\d+|NA)\\s+" + // Theory Max Marks (Group 2) | Theory Sec Marks (Group 3)
				"(NA|\\d+)\\s+(NA|\\d+)\\s+" + // Practical Max Marks (Group 4) | Practical Sec Marks (Group 5)
				"(NA|\\d+)\\s+(NA|\\d+)\\s+" + // Practical+Viva Max Marks (Group 6) | Practical+Viva Sec Marks (Group
												// 7)
				"(\\d+)\\s+(\\d+)\\s+" + // Grand Total Max Marks (Group 8) | Grand Total Sec Marks (Group 9)
				"(Pass|Fail|AP|NE|AB)", // Status
				Pattern.DOTALL);
		Matcher fourPatternMatcher = fourPattern.matcher(text);

		while (fourPatternMatcher.find()) {

			String subject = fourPatternMatcher.group(1);

			String theoryMaxMarks = fourPatternMatcher.group(2); // Take the last part

			String theorySecMarks = fourPatternMatcher.group(3);
			String praticalMaxMarks = fourPatternMatcher.group(4);
			String praticalVivaMaxMarks = fourPatternMatcher.group(5);
			String praticalTotalMaxMarks = fourPatternMatcher.group(6);
			String praticalTotalSecMarks = fourPatternMatcher.group(7);
			String grandTotalMaxMarks = fourPatternMatcher.group(8);
			String grandTotalSecMarks = fourPatternMatcher.group(9);
			status = fourPatternMatcher.group(10);

			// Check if this is the subject we are looking for

			System.out.println("==============");

			System.out.println("Subject: " + fourPatternMatcher.group(1)); // Subject name + Theory Max Marks
			System.out.println("Theory Max Marks: " + theoryMaxMarks);
			System.out.println("Theory Sec Marks: " + fourPatternMatcher.group(3));
			System.out.println("Practical Max Marks: " + praticalMaxMarks);

			// Check if group(6) exists (to avoid IndexOutOfBoundsException)
			if (fourPatternMatcher.groupCount() >= 6) {
				System.out.println("Practical Viva Max Marks: " + praticalVivaMaxMarks);
			} else {
				System.out.println("Practical Viva Max Marks: NA"); // Handle cases where it's missing
			}

			// Practical Total Marks
			System.out.println("Practical Total Max Marks: " + praticalTotalMaxMarks);
			System.out.println("Practical Total Sec Marks: " + praticalTotalSecMarks);

			// Grand Total
			System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
			System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

			// Status
			System.out.println("Status: " + status);
			System.out.println("==============");

			// Stop after printing one subject

		}

		return false; // If the subject was not found
	}

	public void processOneSubjectPaternPdf(File latestFile,Object Regno, Object paper1, Object paper2, Object paper3,
			Object theoryExam, Object praticalExam, Object examTotal, ExtentTest testCaseName, String subjectToFind,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva)
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

					Pattern subjectPattern = Pattern.compile("Subject\\s*Name:\\s*(.+)", Pattern.CASE_INSENSITIVE);
					Matcher subjectMatcher = subjectPattern.matcher(text);

					if (regMatcher.find()) {
						registrationNumber = Long.parseLong(regMatcher.group(1));
						System.out.println("Registration No: " + registrationNumber);
					}

					if (subjectMatcher.find()) {
						subject = subjectMatcher.group(1);
						System.out.println("Subject: " + subject);
					}

					boolean isTheory = true; // Initially, assume the first pair is Theory
					boolean isPractical = false; // Initially, assume we haven't encountered Practical marks

					// Extract max/min marks

				//	Pattern paperPattern = Pattern.compile(
				//			"(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");
					
					String regex = "(MDS|BNYS|UNANI|BAMS|BAM S|BUMS)\\s+";


					  Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
				        Matcher matcher1 = pattern.matcher(text);
				        if (matcher1.find()) {
				            System.out.println("Match found Course: " + matcher1.group());
				        } else {
				        	ExtentTest testCaseScenario = testCaseName.createNode("Pattern validation for the following " + Regno + " Test case has started running");
							
							testCaseScenario.log(Status.FAIL," Please check the The following Register number " + Regno +" for the subject "+ subjectToFind +" No match found");
							
							System.out.println("FAIL Please check the The following Register number " + Regno +" for the subject "+ subject +" No match found");
						
				        	
				        	System.out.println("No match found.");
				        }
				    	
				        Pattern oneSubjectPattern = Pattern.compile(
				        	    "([A-Za-z &'\\-]+)\\s+" +  // Captures subject name (now allowing apostrophes & dashes)
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" +  // First mark
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)?\\s*" +  // Second mark (optional)
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)?\\s*" +  // Third mark (optional)
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)?\\s*" +  // Fourth mark (optional)
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)?\\s*" +  // Fifth mark (optional)
				        	    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)?\\s*" +  // Sixth mark (optional)
				        	    "(Pass|Fail|AP)"  // Final result
				        	);


					Matcher paperMatcher = oneSubjectPattern.matcher(text);

					// Matcher paperMatcher = paperPattern.matcher(text);

					System.out.println("\n=== Paper and Total Max/Min Marks ===");
				
					while (paperMatcher.find()) {
		
						// Check if the first group is not null (Paper group)
						if (paperMatcher.group(1) != null) {
							System.out.println(paperMatcher.group(1) + " Max Marks: " + paperMatcher.group(2));
						}
						// Check if the second group is not null (Theory Max Marks and Min Marks group)
						else if (paperMatcher.group(3) != null) {
							System.out.println("Theory Max Marks: " + paperMatcher.group(3) + ", Min Marks: "
									+ paperMatcher.group(4));
							theoryMaxMark = Integer.parseInt(paperMatcher.group(3));
							theoryMinMark = Integer.parseInt(paperMatcher.group(4));

							boolean theoryMinMarkText = (!paperMatcher.group(4).isEmpty());

							// System.out.println("djfhjkhdskdfskjhfkdfjh" +theoryMinMarkText);
						}
						// Loop over the remaining groups dynamically to detect Practical Marks or Grand
						// Total
						else if (paperMatcher.group(5) != null) {
							int i = 5; // Start from group 5
							while (i < paperMatcher.groupCount()) {
								String maxMarks = paperMatcher.group(i); // Max Marks
								String minMarks = paperMatcher.group(i + 1); // Min Marks

								if (maxMarks != null && minMarks != null) {
									// Depending on the current flag, print the appropriate message
									if (isTheory) {
										System.out.println(
												"Practical Max Marks: " + maxMarks + ", Min Marks: " + minMarks);

										praticalMinMark = Integer.parseInt(minMarks);
										praticalMaxMark = Integer.parseInt(maxMarks);

										isTheory = false; // Next set will be for Practical or Grand Total
									} else if (!isTheory && !isPractical) {
										System.out.println(
												"Grand Total Max Marks " + maxMarks + ", Min Marks: " + minMarks);
										grandTotalMinMark = Integer.parseInt(minMarks);
										grandTotalMaxMark = Integer.parseInt(maxMarks);

									} else {
										System.out.println("Total Max Marks: " + maxMarks + ", Min Marks: " + minMarks);
										System.out.println(grandTotalMinMark + grandTotalMaxMark);
										System.out.println("==============");
									}
									// checkPaper1Result(registrationNumber,paper1Mark);
								}

								i += 2; // Move to the next pair of Max/Min marks
							}

						}
					

					// Extract marks and result
					System.out.println("\n=== Marks and Results ===");
					Pattern marksPattern = Pattern.compile("(\\d+)(?:\\s+\\(F\\))?\\s+" + "(\\d+)(?:\\s+\\(F\\))?\\s+"
							+ "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(\\d+)(?:\\s+\\(F\\))?\\s+"
							+ "(\\d+)(?:\\s+\\(F\\))?\\s+" + "(Pass|Fail)");
					Matcher marksMatcher = marksPattern.matcher(text);

				
					while (marksMatcher.find()) {
						try {
							if (matcher1.group().contains("MDS")) {	
						
						paper1Mark = Integer.parseInt(marksMatcher.group(1));
						paper2Mark = Integer.parseInt(marksMatcher.group(2));
						paper3Mark = Integer.parseInt(marksMatcher.group(3));
						theoryTotal = Integer.parseInt(marksMatcher.group(4));
						praticalTotal = Integer.parseInt(marksMatcher.group(5));
						grandTotal = Integer.parseInt(marksMatcher.group(6));

						System.out.println("Paper I: " + paper1Mark);
						System.out.println("Paper II: " + paper2Mark);
						System.out.println("Paper III: " + paper3Mark);
						System.out.println("Theory Total: " + theoryTotal);

						System.out.println("==============");

			
						System.out.println("Practical Total: " + praticalTotal);

						System.out.println("===============");

						System.out.println("Grand Total: " + grandTotal);

						System.out.println("Result: " + marksMatcher.group(7));
					
						
					System.out.println("---------------------------------------------------");
						
					checkPaper1Result(Regno, paper1, testCaseName, subjectToFind);

					implicitWait(3);
					checkPaper2Result(Regno, paper2, testCaseName, subjectToFind);
					implicitWait(3);
					checkPaper3Result(Regno, paper3, testCaseName, subjectToFind);
					implicitWait(3);
					checkPraticalExamResult(Regno, praticalExam, testCaseName, subjectToFind);
					implicitWait(3);

					checkTheoryExamResult(Regno, theoryExam, testCaseName, subjectToFind);
					implicitWait(3);
					checkFinalExamResult(Regno, examTotal, testCaseName, subjectToFind);

					
					}
					
						
							else if (matcher1.group().contains("BNYS") || matcher1.group().contains("UNANI")|| matcher1.group().contains("BAMS")) {
							    // Your logic here
							

						
						subject= paperMatcher.group(1);
						theoryTotalMaxMarks =paperMatcher.group(2);
						theoryTotalSecMarks = paperMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()  ? paperMatcher.group(3): paperMatcher.group(3).replaceAll("[^0-9]", "");
						practicalVivaMaxMarks = paperMatcher.group(4);
						practicalVivaSecMarks =paperMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()  ? paperMatcher.group(5): paperMatcher.group(5).replaceAll("[^0-9]", "");
	                    
						theoryPracticalMaxMarks = paperMatcher.group(6);
						theoryPracticalSecMarks = paperMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()  ? paperMatcher.group(7): paperMatcher.group(7).replaceAll("[^0-9]", "");
						status = paperMatcher.group(8);	
						
							
						/*
						 * theoryTotalMaxMarks = Integer.parseInt(marksMatcher.group(1)); paper2Mark =
						 * Integer.parseInt(marksMatcher.group(2)); paper3Mark =
						 * Integer.parseInt(marksMatcher.group(3)); theoryTotal =
						 * Integer.parseInt(marksMatcher.group(4)); praticalTotal =
						 * Integer.parseInt(marksMatcher.group(5)); grandTotal =
						 * Integer.parseInt(marksMatcher.group(6));
						 */
						System.out.println("Subject: " + subject);
						System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
						System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
						System.out.println("Practical Viva Max Marks: " + practicalVivaMaxMarks);
						System.out.println("Practical Viva Secured Marks: " + practicalVivaSecMarks);
						System.out.println("Theory plus Practical Max Marks: " + theoryPracticalMaxMarks);
						System.out.println("Theory plus Practical Secured Marks: " + theoryPracticalSecMarks);
						System.out.println("Status: " + status);

							
						System.out.println("---------------------------------------------------");
						paper1Mark = 0.0;
						Paper1 = 0.0;
						praticalTotalSecMark = 0.0;
						PraticalExamTotal =0.0;
						ExamTotalScore = 0.0;
						if ((status.equals("Pass") || status.equals("Fail") || status.equals("AP"))& subject.equals(subjectToFind)) {

							try {
								
								if (!theoryTotalSecMarks.equals("NA")||!theoryTotalSecMarks.equals("AB")||!theoryTotalSecMarks.equals("NE") ||!  theoryTotalSecMarks.equals("NE (AT)")) {
								theoryInternalMaxMark = Double.parseDouble(theoryTotalMaxMarks);
								paper1Mark = Double.parseDouble(theoryTotalSecMarks) ;
								}
								checkMarks(Regno, "Theory Total Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, theoryTotalSecMarks,
										theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
								// Use the value
							} catch (NumberFormatException e) {

								if (theoryTotalSecMarks.equals("AB") || 
										theoryTotalSecMarks.equals("NE") || 
										theoryInternalSecMarks.equals("NA") || 
										theoryInternalSecMarks.equals("NE (AT)")) {
									paper1Mark = 0.0;
									Paper1 = 0.0;
									System.out.println(paper1Mark);

								ExtentTest testCaseScenario = testCaseName.createNode("Theory internal sec. marks validation for the subject " + subject +" Test case has started running");
								
								testCaseScenario.log(Status.PASS,"The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
								
								System.out.println("The following Register number " + Regno +" for the subject "+ subject +" theory internal sec marks is: " + theoryInternalSecMarks);
									
							
								
								}}
							
							
							
	try {
								
								if (!practicalVivaSecMarks.equals("NA")||!theoryTotalSecMarks.equals("AB")||!theoryTotalSecMarks.equals("NE") ||!  theoryTotalSecMarks.equals("NE (AT)")) {
								praticalTotalMaxMark = Double.parseDouble(practicalVivaMaxMarks);
								praticalTotalSecMark = Double.parseDouble(practicalVivaSecMarks);
								}	// Use the value
								// Check pratical internal Sec. Marks
								checkMarks(Regno, "Pratical Univ Sec. Marks", paper1, paper2, paper3, praticalExam,
										theoryExam, subjectToFind, examTotal, practicalVivaSecMarks,
										praticalTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

							} catch (NumberFormatException e) {

								if (practicalVivaSecMarks.equals("AB") || 
										practicalVivaSecMarks.equals("NE") || 
										practicalVivaSecMarks.equals("NA") || 
										practicalVivaSecMarks.equals("NE (AT)")) {
									praticalTotalSecMark = 0.0;
									PraticalExamTotal =0.0;
									
									System.out.println(praticalTotalSecMark);
								

								 ExtentTest testCaseScenario = testCaseName.createNode("Pratical Univ Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
									
									
								 testCaseScenario.log(Status.PASS,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
										 + " Practical Univ Sec. Marks is:" + practicalVivaSecMarks);
								
								 System.out.println("\nThe Following Registration number " + Regno
											+ " Practical Univ Sec. Marks is:" + practicalVivaSecMarks);
									
								// Handle gracefully, e.g., assign default value or log an error

							}}

							try {
								if (!theoryPracticalSecMarks.equals("NA")) {
								grandTotalMaxMark = Double.parseDouble(theoryPracticalMaxMarks);
								ExamTotalScore = Double.parseDouble(theoryPracticalSecMarks);
								}
								// Check Grand Total Sec. Marks (assumed max marks as 200)
								checkMarks(Regno, "Theory plus pratical Sec. Marks", paper1, paper2, paper3,
										praticalExam, theoryExam, subjectToFind, examTotal, theoryPracticalSecMarks,
										grandTotalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);

								// Use the value
							} catch (NumberFormatException e) {
								if (theoryPracticalSecMarks.equals("AB") || 
										theoryPracticalSecMarks.equals("NE") || 
										theoryPracticalSecMarks.equals("NA") || 
										theoryPracticalSecMarks.equals("NE (AT)")) {
									ExamTotalScore = 0.0;
									System.out.println(ExamTotalScore);
								

									 ExtentTest testCaseScenario = testCaseName.createNode("Theory plus pratical Sec. Marks Validation for the Subject "+ subject + " Test case has started running");
										
										
									 testCaseScenario.log(Status.PASS,"\n The Following Registration number " + Regno +" for the Subject "+ subject 
											 + " Theory plus Pratical Sec. Marks is: " + theoryPracticalSecMarks);
									
									 System.out.println("\nThe Following Registration number " + Regno
												+ " Theory plus Pratical Sec. Marks is:" + theoryPracticalSecMarks);
										

							}
								
							}

					}
					}
					}
					
					catch(Exception e) {
							
						}
						
							
					if (page == totalPages) {
						break;
					}
				
						
					}
					
					
						
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

	public void processTwoPatternPdf(File latestFile) {
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

					String normalizedText = stripper.getText(document).replaceAll("[\r\n]+", "\n");
					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");

					// Extract registration number
					Pattern registrationPattern = Pattern.compile("Registration No:\\s*(\\d+)");
					Matcher regMatcher = registrationPattern.matcher(normalizedText);
					if (regMatcher.find()) {
						String[] parts = regMatcher.group(1).split(":");
						for (int i = 0; i < parts.length; i++) {
							registrationNumber = Long.parseLong(parts[i]);

							// System.out.println("parts[" + i + "]: '" + parts[i] + "'");

							System.out.println("Registration No: " + registrationNumber);

						}

					}

					// Extract max/min marks
					System.out.println("\n=== Paper and Total Max/Min Marks ===");

					Pattern paperPattern = Pattern.compile(
							"(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");

					Matcher paperMatcher = paperPattern.matcher(normalizedText);
					while (paperMatcher.find()) {
						if (paperMatcher.group(1) != null) {
							System.out.println(paperMatcher.group(1) + " Max Marks: " + paperMatcher.group(2));
						} else if (paperMatcher.group(3) != null) {
							System.out.println("Theory Max Marks: " + paperMatcher.group(3) + ", Min Marks: "
									+ paperMatcher.group(4));
						} else if (paperMatcher.group(5) != null) {
							System.out.println("Grand Total Max Marks: " + paperMatcher.group(5) + ", Min Marks: "
									+ paperMatcher.group(6));
						}
					}

					// Extract marks and result based on the new pattern
					System.out.println("\n=== Marks and Results ===");
					Pattern marksPattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(Pass)");
					Matcher marksMatcher = marksPattern.matcher(normalizedText);
					if (marksMatcher.find()) {

						paper1Mark = Integer.parseInt(marksMatcher.group(1));
						grandTotal = Integer.parseInt(marksMatcher.group(2));

						System.out.println("First Number: " + paper1Mark);
						System.out.println("Grand Total: " + grandTotal);
						System.out.println("Result: " + marksMatcher.group(3));
					} else {
						System.out.println("Marks Result not found.");
					}

					System.out.println("---------------------------------------------------");

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
			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Paper1 Validation Test case has started running");

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
					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + registrationNumber + " registration number "
									+ e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (paper1Mark < 50 && Paper1 < 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is failed in Paper1 exam with marks: " + paper1Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ " is failed in Paper1 exam with marks: " + paper1Mark);

					} else if (paper1Mark >= 50 && Paper1 >= 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is passed in Paper1 exam with marks: " + paper1Mark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ " is passed in Paper1 exam with marks: " + paper1Mark);

					} else {
						System.out.println("Pdf mark is " + paper1Mark + " Excel mark is " + Paper1);
						testCaseScenario.log(Status.FAIL, "Pdf mark is " + paper1Mark + " Excel mark is " + Paper1
								+ " for the following " + regno + " number");

					}

				} catch (Exception e) {
					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + registrationNumber + " registration number "
									+ e.getMessage(),
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

			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Paper2 Validation Test case has started running");

				Paper2 = objectToDataType(paper2);

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
					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + registrationNumber + " registration number "
									+ e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if (paper2Mark == Paper2 && paper2Mark < 50 && Paper2 < 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is failed in Paper2 exam with marks: " + paper2Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ " is failed in Paper2 exam with marks: " + paper2Mark);

					} else if (paper2Mark == Paper2 && paper2Mark >= 50 && Paper2 >= 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is passed in Paper2 exam with marks: " + paper2Mark);

						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ "is passed in Paper2 exam with marks:" + paper2Mark);

					} else {
						System.out.println("Pdf mark is " + paper2Mark + " Excel mark is" + Paper2);
						testCaseScenario.log(Status.FAIL, "Pdf mark is " + paper2Mark + " Excel mark is" + Paper2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + registrationNumber + " registration number "
									+ e.getMessage(),
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
	public void checkPaper3Result(Object regno, Object paper3, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {
			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Paper3 Validation Test case has started running");

				Paper3 = objectToDataType(paper3);

				try {
					if (paper3Mark == Paper3) {
						System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark
								+ " for the following Register " + regno + " number data are same for paper 3 mark");
						testCaseScenario.log(Status.PASS, "Both Excel " + Paper3 + " and Pdf " + paper3Mark
								+ " for the following Register " + regno + " number data are same for paper 3 mark");
					}

					else {
						System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file for paper 3 mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following " + regno
										+ "number data are not same for paper 3 mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {

					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL, "Check the files for the following " + registrationNumber
							+ " registration number for paper 3 mark " + e.getMessage());

				}

				try {
					if (paper3Mark < 50 && Paper3 < 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is failed in Paper3 exam with marks: " + paper3Mark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ " is failed in Paper3 exam with marks: " + paper3Mark);

					} else if (paper3Mark >= 50 && Paper3 >= 50) {
						System.out.println("The following Registration number " + registrationNumber
								+ " is passed in Paper3 exam with marks: " + paper3Mark);
						testCaseScenario.log(Status.PASS, "The following Registration number " + registrationNumber
								+ " is passed in Paper3 exam with marks: " + paper3Mark);

					} else {
						System.out.println("Pdf mark is " + paper3Mark + " Excel mark is" + Paper3);
						testCaseScenario.log(Status.FAIL, "Pdf mark is " + paper3Mark + " Excel mark is" + Paper3,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
				} catch (Exception e) {
					System.out.println("Check the files for the following " + registrationNumber
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + registrationNumber + " registration number "
									+ e.getMessage(),
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

	// Method to check if a student passed or failed in the Theory Exam (Paper1 +
	// Paper2 + Paper3)
	public void checkTheoryExamResult(Object regno, Object theoryExam, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

		try {

			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Therory Exam Toal Result Validation Test case has started running");

				TheroryExamTotal = Paper1 + Paper2 + Paper3;
				//

				theoryTotal = paper1Mark + paper2Mark + paper3Mark;

				Double theroryExamTotal = objectToDataType(theoryExam);

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

		
					 if (theoryTotal < minMark && TheroryExamTotal < minMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Theory Exam with total marks: " + theoryTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Theory Exam with total marks: " + theoryTotal);

					} else if (theoryTotal >= minMark && TheroryExamTotal >= minMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Theory Exam with total marks: " + theoryTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in Theory Exam with total marks: " + theoryTotal);
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

			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Pratical Exam Total Result Validation Test case has started running");

				PraticalExamTotal = objectToDataType(praticalExam);

				try {
					if (praticalTotal == PraticalExamTotal) {

						System.out.println("Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotal
								+ " for the following Register " + regno
								+ " number data are same for pratical total mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotal
										+ " for the following Register " + regno
										+ " number data are same for pratical total mark");

					}

					else {

						System.out.println("Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotal
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file for pratical total mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotal + " for the following "
										+ regno + " number data are not same for pratical total mark",
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

					if (praticalTotal < praticalMinMark && PraticalExamTotal < praticalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed in Practical Exam with marks: " + praticalTotal);

					} else if (praticalTotal >= praticalMinMark && PraticalExamTotal >= praticalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Practical Exam with marks: " + praticalTotal);

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

			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode("Grand Total Exam Result Validation Test case has started running");
				// Calculate total score (Theory + Practical)
				ExamTotalScore = TheroryExamTotal + PraticalExamTotal;

				grandTotal = theoryTotal + praticalTotal;

				double grandTotalScore = objectToDataType(examTotal);

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

					// If total marks are above 300 but failed in individual papers, still fail
			 if (grandTotal < grandTotalMinMark && ExamTotalScore < grandTotalMinMark) {
						System.out.println("The following Registration number " + regno
								+ " is failed with total score: " + grandTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with total score: " + grandTotal);

					} else {

						System.out.println("The following Registration number " + regno
								+ " is passed with total score: " + grandTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed with total score: " + grandTotal);

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

	// Method to automatically detect which processing method to run based on
	// pattern
	public void processPdfBasedOnSubjectPattern(File latestFile, Object Regno, Object paper1, Object paper2,
			Object paper3, Object theoryExam, Object praticalExam, Object examTotal, String subjectToFind,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva,
			ExtentTest testCaseName) {
		if (latestFile != null) {
			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String pdfText = stripper.getText(document);

				//	 System.out.println(pdfText);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");


//					Pattern eightSubjectPattern = Pattern.compile("^(.*?)\\s+" + // subject
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory Internal Max Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory Internal Sec Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory Univ Max Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory Univ Sec Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Practical Internal Max Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Practical Internal Sec Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Practical Univ Max Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Practical Univ Sec Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory + Practical Max Marks
//							"(\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+" + // Theory + Practical Sec Marks
//							"(AP|Pass|Fail|AB|NE|NR|NE\\(AT\\)|---)$", // Status
////							Pattern.MULTILINE);
					Pattern eightSubjectPattern = Pattern.compile(
							
							
							"(?m)^(?:((?:(?!Registration No|SYEDA FARHA BEGUM MODERN INSTITUTE OF PHYSICAL MEDICINE AND REHABILITATION BPT III Year Examination held in the month of Feb-2025|Date|Name of the Exam|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical)[^\\d\\n].*?(?:\\R(?!Registration No|Date|Name of the Exam|Name of the College|Name of the Candidate|SL\\.NO|STATEMENT OF MARKS|Warangal|KALOJI|Result\\s*:|Max\\. Marks|Theory\\s+Practical)[^\\d\\n].*?)*)?)\\s+)?"
								    +
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Internal Marks
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory Marks
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Total Marks
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Obtained Marks
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Internal
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Viva
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Max
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Sec
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Max
												                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Sec
												                "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // Final Status
												                Pattern.MULTILINE);



					Matcher eightSubjectPatternMatcher = eightSubjectPattern.matcher(text);

					/*
					 * Pattern fourPattern = Pattern.compile("^((?:.*?)\\s+)?" + // Optional subject
					 * (0 or 1 occurrence of // subject name followed by space)
					 * "((?:\\d+|---|NA|AB|NE|NR|NE\\(AT\\))(?:\\s+\\(F\\))?\\s+){6,7}" + // Match 6
					 * or 7 marks // fields "(\\d+)?\\s+" + // Optional total marks field (e.g., 100
					 * or 55) "(AP|Pass|Fail|AB|NE|NR|NE\\(AT\\)|---)$", // Status
					 * Pattern.MULTILINE); Matcher fourPatternMatcher = fourPattern.matcher(text);
					 */
					// Check for a match with the "three" pattern first
					Pattern oneSubjectPattern = Pattern.compile("(\\d+)(?:\\s+\\(F\\))?\\s+"
						
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" 
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)"
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)"
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)"
							+ "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" 
							+ "(Pass|Fail|AP)");
					Matcher oneSubjectPatternMatcher = oneSubjectPattern.matcher(text);

					Pattern thirdSubjectPattern = Pattern.compile("([A-Z &]+)\\s+"  // Subject Name
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // First number or ---
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Second number or ---
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Third number or ---
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Fourth number or ---
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Fifth number or ---
					        + "((?:\\d+|---)(?:\\s+\\(F\\))?\\s+)" // Sixth number or ---
					        + "(Pass|Fail|AP)"); // Final status		
						 Matcher thirdSubjectPatternMatcher = thirdSubjectPattern.matcher(text);
					
					
					
					Pattern twoPattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(Pass)");
					Matcher twoPatternMatcher = twoPattern.matcher(text);
					
					
					Pattern bscNursingPattern = Pattern.compile(
						    "^([\\w &]+ \\d+ \\(Theory\\))\\s+" +  // Capture subject name with number and "(Theory)"
						    "((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" +  // Capture first column (e.g., 25 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" +  // Capture second column (e.g., 19 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)\\s+" +  // Capture third column (e.g., 75 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)\\s+" +  // Capture fourth column (e.g., 51 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)\\s+" +  // Capture fifth column (e.g., 100 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT|AP)?)\\s+" +  // Capture sixth column (e.g., 70 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)?\\s+" +  // Capture seventh column (e.g., 70 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)?\\s+" +  // Capture eighth column (e.g., B+ or special values)
						    "((?:\\w\\+?|NE|NR|NA|NEAT)?)?\\s+" +  // Capture ninth column (e.g., 7 or special values)
						    "((?:\\d+|NE|NR|NA|NEAT)?)?$"  // Capture last column (optional, e.g., 7 or special values)
						);

					Matcher bscNursingPatternMatcher = bscNursingPattern.matcher(text);

					
					
					Pattern bptPattern = Pattern.compile(   "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Internal Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Total Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Obtained Marks
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Internal
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Viva
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Max
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Practical Viva Sec
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Max
			                "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*" + // Theory+Practical Sec
			                "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$"); // Final Status
			                
			                
			                
					Matcher bptPatternMatcher = bptPattern.matcher(text);
					
					
					Pattern bdsPattern = Pattern.compile(
						    "(?<subject>(?:[A-Z ,]+(?:\\n)?)+)\\s+" + 
						    "((?:\\d+|---|NA|AB|NE(?:\\s*\\(AT\\))?|NR)\\s*(?:\\(\\s*F\\s*\\))?\\s*)+",
						    Pattern.MULTILINE
						);
					 

					Matcher bdsPatternMatcher = bdsPattern.matcher(text);
					
					if (eightSubjectPatternMatcher.find()) {

						System.out.println("Pattern matched: 8 subject pattern detected.");

						processEightSubjectPatternPdf(Regno, latestFile, paper1, paper2, paper3, praticalExam,
								theoryExam, examTotal, subjectToFind, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
						// processFourPatternValidation(Regno,paper1,paper2,paper3,theroryExam,praticalExam,examTotal,testCaseName);
						// Exit once the matching method is called
						return;

					}

					 
					
					else if (oneSubjectPatternMatcher.find()) {
						// If it matches, call the method for oneSubject patterns

						System.out.println("Pattern matched: 1 subject pattern detected.");

						processOneSubjectPaternPdf(latestFile,Regno, paper1, paper2, paper3, theoryExam, praticalExam,
								examTotal, testCaseName, subjectToFind,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);


						// Exit once the matching method is called
						return;
					}
					// Otherwise, check for the "two" pattern
					else if (thirdSubjectPatternMatcher.find()) {
						System.out.println("Pattern matched: Third patterns detected.");
						/*
						 * processOneSubjectPaternPdf(latestFile,Regno, paper1, paper2, paper3,
						 * theoryExam, praticalExam, examTotal, testCaseName, subjectToFind);
						 */
						// Exit once the matching method is called
						return;
						
					}
					
					
					else if (twoPatternMatcher.find()) {
						// If it matches, call the method for two patterns
						System.out.println("Pattern matched: Two patterns detected.");
						processTwoPatternPdf(latestFile);
						// Exit once the matching method is called
						return;
					}
					
					else if(bscNursingPatternMatcher.find()) {
						
						
						System.out.println("Pattern matched: Bsc nusing patterns detected.");
						
						
					}
					else if(bptPatternMatcher.find()) {
						
						System.out.println("Yes");
					}
					else if (bdsPatternMatcher.find()) {
						
						System.out.println("yeah");
						
						bdsPatternProcess(Regno, latestFile, paper1, paper2, paper3, praticalExam,
								theoryExam, examTotal, subjectToFind, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
						return;
					}
					else {
						System.out.println("New pattern found");
						processEightSubjectPatternPdf(Regno, latestFile, paper1, paper2, paper3, praticalExam,
								theoryExam, examTotal, subjectToFind, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No PDF file found.");
		}
	}

	public void processOneSubjectPaternValiation(Object Regno, Object paper1, Object paper2, Object paper3,
			Object theroryExam, Object praticalExam, Object examTotal, ExtentTest testCaseName, String subjectToFind)
			throws IOException {

	
	}

	public void processFourPatternValidation(Object Regno, Object paper1, Object paper2, Object paper3,
			Object theroryExam, Object praticalExam, Object examTotal, String subjectToFind, ExtentTest testCaseName)
			throws IOException {

		theorySecMarks(Regno, paper1, testCaseName);
		implicitWait(3);

	}

	public void processEightSubjectPatternValidation(Object Regno, Object paper1, Object paper2, Object paper3,
			Object theroryExam, Object praticalExam, Object examTotal, ExtentTest testCaseName) throws IOException {

	}

	public void theorySecMarks(Object Regno, Object paper1, ExtentTest testCaseName) throws IOException {

		ExtentTest testCaseScenario = testCaseName
				.createNode("Four Pattern Theory Sec Marks Validation Test case has started running");

		Paper1 = objectToDataType(paper1);
		System.out.println(Paper1);

		minMark = 0.5 * theoryMaxMark;

		System.out.println(minMark);
		try {
			if (theorySecMark == Paper1) {
				System.out.println("Both Excel " + Paper1 + " and Pdf " + theorySecMark + " for the following Register "
						+ Regno + " number data are same for paper 1 mark");
				testCaseScenario.log(Status.PASS, "Both Excel " + Paper1 + " and Pdf " + theorySecMark
						+ " for the following Register " + Regno + " number data are same for paper 1 mark");

			}

			else {
				System.out.println("Both Excel " + Paper1 + " and Pdf " + theorySecMark + " for the following " + Regno
						+ " data are not same please check Excel file or Pdf file for paper 1 mark");
				testCaseScenario.log(Status.FAIL,
						"Both Excel " + theorySecMark + " and Pdf " + paper1Mark + " for the following " + Regno
								+ " number data are not same for paper 1 mark",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}

		} catch (Exception e) {
			System.out.println("Check the files for the following " + Regno + " registration number " + e.getMessage());
			testCaseScenario.log(Status.FAIL,
					"Check the files for the following " + Regno + " registration number " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		}

		try {

			if ((theorySecMark < minMark) && (Paper1 < minMark)) {
				System.out.println("The following Registration number " + Regno
						+ " is failed in Paper1 exam with marks: " + theorySecMark);

				testCaseScenario.log(Status.PASS, "The following Registration number " + Regno
						+ " is failed in Paper1 exam with marks: " + theorySecMark);

			} else if ((theorySecMark >= minMark) && (Paper1 >= minMark)) {
				System.out.println("The following Registration number " + Regno
						+ " is passed in Paper1 exam with marks: " + theorySecMark);
				testCaseScenario.log(Status.PASS, "The following Registration number " + Regno
						+ " is passed in Paper1 exam with marks: " + theorySecMark);

			} else {
				System.out.println("Pdf mark is " + theorySecMark + " Excel mark is " + Paper1);
				testCaseScenario.log(Status.FAIL, "Pdf mark is " + theorySecMark + " Excel mark is " + Paper1
						+ " for the following " + Regno + " number");

			}

		} catch (Exception e) {
			System.out.println("Check the files for the following " + Regno + " registration number " + e.getMessage());
			testCaseScenario.log(Status.FAIL,
					"Check the files for the following " + Regno + " registration number " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}
	}

	public void theoryInternalSecMarks(Object regno, String markName, Object paper1, String subjectToFind,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva
			,ExtentTest testCaseName) throws IOException {

		try {
			
			
			if (subject.equalsIgnoreCase(subjectToFind)) {
				
		paper1MinMark =minMark;		
				
				System.out.println(minMark);
				
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " for the Subject " + subjectToFind + " Validation Test case has started running");

				Paper1 = objectToDataType(paper1);
				
				
				finalPaper1Mark = objectToDataType(theoryInt);
				
				finalPaper2Mark = objectToDataType(theoryTh);
				
				double finalTheoryMark = finalPaper1Mark +finalPaper2Mark;
				
				try {
					if (finalTheoryMark == Paper1) {
						System.out.println(
								"Both " + Paper1 + " and " + finalTheoryMark + " for the following Register "
										+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS,
								"Both " + Paper1 + " and " + finalTheoryMark + " for the following Register "
										+ regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and PDF " + finalTheoryMark + " for the following "
								+ regno
								+ " data are not same please check Excel file or Pdf file for Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and PDF " + finalTheoryMark + " for the following " + regno
										+ " number data are not same for marks",
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
					if (paper1Mark == Paper1) {
						System.out.println(
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following Register "
										+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following Register "
										+ regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno
								+ " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same for mark",
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
								+ " is failed in exam with marks in PDF: " + paper1Mark +" and in Excel: " +Paper1);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper1Mark +" and in Excel: " +Paper1);

					} else if ((paper1Mark >= paper1MinMark) && (Paper1 >= paper1MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper1Mark +" and in Excel: " +Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper1Mark +" and in Excel: " +Paper1);

					} else {
						System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1);
						testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1,
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
	public void theoryUnivSecMarks(Object regno, String markName, Object paper2, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.equalsIgnoreCase(subjectToFind)) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
				
				
				paper2MinMark = minMark;
				System.out.println(minMark);
				
				Paper2 = objectToDataType(paper2);

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
								+ " is failed in Theory Univ Sec mark exam with marks in PDF: " + paper2Mark +" and in Excel: " +Paper2);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in Theory Univ Sec mark exam with marks in PDF: " + paper2Mark +" and in Excel: " +Paper2);

					} else if ((paper2Mark >= paper2MinMark) && (Paper2 >= paper2MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in Theory Univ Sec mark exam with marks in PDF: " + paper2Mark +" and in Excel: " +Paper2);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ "is passed in Theory Univ Sec mark exam with marks in PDF: " + paper2Mark +" and in Excel: " +Paper2);

					} else {
						System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + paper2Mark + " Excel mark is" + Paper2);
						testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + paper2Mark + " Excel mark is" + Paper2,
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

	
	public void practicalInternalSecMarks(Object regno, String markName,Object paper1,Object paper2, Object paper3, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.equalsIgnoreCase(subjectToFind)) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
				
				paper3MinMark = minMark;
				System.out.println(minMark);
				Paper3 = objectToDataType(paper3);


				finalPaper1Mark = objectToDataType(paper1);
				
				finalPaper2Mark = objectToDataType(paper2);
				
				double finalTheoryMark = finalPaper1Mark +finalPaper2Mark;
				
				try {
					if (finalTheoryMark == Paper3) {
						System.out.println(
								"Both Excel " + Paper3 + " and paper1 + paper2 " + finalTheoryMark + " for the following Register "
										+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + Paper3 + " and paper1 + paper2  " + finalTheoryMark + " for the following Register "
										+ regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper3 + " and " + finalTheoryMark + " for the following "
								+ regno
								+ " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper3 + " and " + finalTheoryMark + " for the following " + regno
										+ " number data are not same for mark",
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
					if (paper3Mark == Paper3) {

						System.out.println(
								"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following Register "
										+ regno + " number data are same Marks");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following Register "
										+ regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following "
								+ regno
								+ " data are not same please check Excel file or Pdf file mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following " + regno
										+ " number data are not same mark",
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
								+ " is failed in exam with marks in PDF: " + paper3Mark +" and in Excel: " +Paper3);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper3Mark +" and in Excel: " +Paper3);


					} else if ((paper3Mark >= paper3MinMark) && (Paper3 >= paper3MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper3Mark +" and in Excel: " +Paper3);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper3Mark +" and in Excel: " +Paper3);

						
					} else {
						System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + paper3Mark + " Excel mark is" + Paper3);
						testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + paper3Mark + " Excel mark is" + Paper3,
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
	
	
	
	
	
	
	
	
	
//Method to check if a student passed or failed in Paper 3
	public void praticalUnivSecMarks(Object regno, String markName, Object praticalExam, String subjectToFind,Object theroryInt,Object theroryTh,Object praticalInt,Object praticalPractical, Object praticalViva,
			ExtentTest testCaseName) throws IOException {

		// Method to check if a student passed or failed in the Practical Exam

		try {
			if (subject.equals(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation  for the Subject " + subjectToFind + " Test case has started running");

				
				practicalMinMark = minMark;
		System.out.println(minMark);

		
		
		System.out.println("theroryInt: " + theroryInt);
		System.out.println("theroryTh: " + theroryTh);
		System.out.println("praticalInt: " + praticalInt);
		System.out.println("praticalPractical: " + praticalPractical);
		System.out.println("praticalViva: " + praticalViva);

		
				
				PraticalExamTotal = objectToDataType(praticalExam);
				
				System.out.println(PraticalExamTotal);
				double praticalIntMark = objectToDataType (praticalInt); 
				
				System.out.println("praticalIntMark:" +praticalIntMark);
				
				double praticalPracticalMark =		 Double.parseDouble((String) praticalPractical); 
		
				System.out.println("praticalPractical:" +praticalPracticalMark);
		
				double praticalVivaMark = 		
						 Double.parseDouble((String) praticalViva); 
				System.out.println("praticalViva:" +praticalViva);
				
				
				
				
				double finalPraticalMark = praticalIntMark +praticalPracticalMark +praticalVivaMark ;
				
				System.out.println(finalPraticalMark);
				
				try {
					if (finalPraticalMark == PraticalExamTotal) {
						System.out.println(
								"Both Excel " + PraticalExamTotal + " and addtion of Excel " + finalPraticalMark + " for the following Register "
										+ regno + " number data are same Marks");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + PraticalExamTotal + " and  addtion of  Excel praticalIntMark + praticalPracticalMark + praticalVivaMark " + finalPraticalMark + " for the following Register "
										+ regno + " number data are same Marks");

					}

					else {
						System.out.println(
								"Both Excel " + PraticalExamTotal + " and addtion of  Excel " + finalPraticalMark + " for the following Register "
										+ regno + " number data are not same Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + PraticalExamTotal + " and addtion of  Excel " + finalPraticalMark + " for the following Register "
										+ regno + " number data are not same Marks",
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
					if (praticalTotalSecMark == PraticalExamTotal) {

						System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
								+ " for the following Register " + regno
								+ " number data are same mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following Register " + regno
										+ " number data are same mark");

					}
					
					

					else {

						System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file ");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
										+ " for the following " + regno
										+ " number data are not same",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
					testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					if ((praticalTotalSecMark < practicalMinMark) && (PraticalExamTotal < practicalMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed with marks in PDF: " + praticalTotalSecMark +" and in Excel: " +PraticalExamTotal);
						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno
								+ " is failed with marks in PDF: " + praticalTotalSecMark +" and in Excel: " +PraticalExamTotal);
						
					} else if ((praticalTotalSecMark >= practicalMinMark) && (PraticalExamTotal >= practicalMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed with marks in PDF: " + praticalTotalSecMark +"and in Excel: " +PraticalExamTotal);
						testCaseScenario.log(Status.PASS,
								"The following Registration number " + regno
								+ " is passed with marks in PDF: " + praticalTotalSecMark +"and in Excel: " +PraticalExamTotal);
					}
					else {
						System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
						testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
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

//Method to check the final result (Theory Exam + Practical Exam)
	public void theoryPlusPracticalSecMarks(Object regno, String marksName, Object theroryExam, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.equalsIgnoreCase(subjectToFind)) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						marksName + " Validation for the Subject" + subjectToFind + "Test case has started running");


				examTotalScoreMinMark = minMark;
				System.out.println(minMark);
				
				
				theoryTotal = objectToDataType(theroryExam);

				System.out.println(theoryTotal);
				try {
					if (theoryTotal == ExamTotalScore) {

						System.out.println("Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
								+ " for the following Register " + regno
								+ " number data are same Mark");
						testCaseScenario.log(Status.PASS,
								"Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
										+ " for the following Register " + regno
										+ " number data are same Mark");
					}

					else {

						System.out.println("Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore
								+ " for the following " + regno
								+ " data are not same please check Excel file or Pdf file Mark");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + theoryTotal + " and Pdf " + ExamTotalScore + " for the following "
										+ regno + " number data are not same Mark",
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

					/// Need to check this secoru

					
					
					double excelTotal = Paper1 + Paper2 + Paper3 +PraticalExamTotal;
					double pdfTotal = paper1Mark + paper2Mark + paper3Mark +praticalTotalSecMark ;

					if (Paper1 != 0 && Paper1 <= paper1MinMark ||
						    paper1Mark != 0 && paper1Mark <= paper1MinMark ||
						    Paper2 != 0 && Paper2 <= paper2MinMark  ||
						    paper2Mark != 0 && paper2Mark <= paper2MinMark ||
						    Paper3 != 0 && Paper3 <= paper3MinMark ||
						    paper3Mark != 0 && paper3Mark <= paper3MinMark ||
						    PraticalExamTotal != 0 && PraticalExamTotal <= practicalMinMark ||
						    praticalTotalSecMark != 0 && praticalTotalSecMark <= practicalMinMark) 
						{

						System.out.println("The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the final exam mark in PDF: " + ExamTotalScore +" and in Excel: " + theoryTotal);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " has failed in one or more papers and is therefore failed in the final exam mark in PDF: " + ExamTotalScore +" and in Excel: " +theoryTotal);

					}



					else	if ((theoryTotal < examTotalScoreMinMark) && (ExamTotalScore < examTotalScoreMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is failed with total marks in PDF: " + ExamTotalScore +" and in Excel: " +theoryTotal);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with total marks in PDF: " + ExamTotalScore +" and in Excel: " +theoryTotal);

					} else if ((theoryTotal >= examTotalScoreMinMark) && (ExamTotalScore >= examTotalScoreMinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed with total marks in PDF: " + ExamTotalScore +" and in Excel: " +theoryTotal);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed with total marks in PDF: " + ExamTotalScore +" and in Excel: " +theoryTotal);
					}
					
					else {
						System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + ExamTotalScore + " Excel mark is" + theoryTotal);
						testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + ExamTotalScore + " Excel mark is" + theoryTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}
					
				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				theoryPlusPracticalTotalMarks(regno, marksName, theroryExam, subjectToFind, testCaseName);

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
	public void securedMarks(Object regno, Object examTotal, ExtentTest testCaseName) throws IOException {

		ExtentTest testCaseScenario = testCaseName
				.createNode("Secured Marks Result Validation Test case has started running");

		grandTotal = objectToDataType(examTotal);

		try {
			if (grandTotal == securedMark) {

				System.out
						.println("Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following Register "
								+ regno + " number data are same mark");
				testCaseScenario.log(Status.PASS,
						"Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following Register " + regno
								+ " number data are same mark");
			}

			else {

				System.out.println("Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following "
						+ regno
						+ " data are not same marks");
				testCaseScenario.log(Status.FAIL,
						"Both Excel " + grandTotal + " and Pdf " + securedMark + " for the following Register " + regno
								+ " number data are not same marks",
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}

		} catch (Exception e) {
			System.out.println( "Check the files for the following " + regno + " registration number where Pdf mark is " + securedMark + " Excel mark is" + grandTotal);
			testCaseScenario.log(Status.FAIL, "Check the files for the following " + regno + " registration number where Pdf mark is " + securedMark + " Excel mark is" + grandTotal,
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}

	}

	// Method to check the final result (Theory Exam + Practical Exam)
	public void theoryPlusPracticalTotalMarks(Object regno, String marksName, Object theroryExam, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			if (subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						"Grand Total Validation for the Subject" + subjectToFind + " Test case has started running");

				
				if(matcher1.group().contains("BNYS")) {
				
					paper3Mark =0.0;
					
					Paper3 =0.0;
					
					grandTotal = paper1Mark + paper2Mark + paper3Mark + praticalTotalSecMark;
					
					TheroryExamTotal = Paper1+Paper2+Paper3+PraticalExamTotal;

				}
				else {
					grandTotal = paper1Mark + paper2Mark + paper3Mark + praticalTotalSecMark;
					
					TheroryExamTotal = Paper1+Paper2+Paper3+PraticalExamTotal;
					
				}
				
	
				
				System.out.println("Paper1Mark:"+ paper1Mark);
				System.out.println("Paper2Mark:"+ paper2Mark);
				System.out.println("Paper3Mark:"+ paper3Mark);
				System.out.println("Pratical total mark:"+ praticalTotalSecMark);
				System.out.println("grandTotal:"+ grandTotal);
				
				System.out.println("Paper1:"+ Paper1);
				System.out.println("Paper2:"+ Paper2);
				System.out.println("Paper3:"+ Paper3);
				System.out.println("PraticalExamTotal"+PraticalExamTotal);
				System.out.println("TheoryExam:"+ TheroryExamTotal);
				
				
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
						testCaseScenario.log(Status.FAIL, "\n Both PDF file total value " + grandTotal
								+ " and Excel file total value  " + theoryTotal + " for the following Register " + regno
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
				  
				  System.out.println("Both Excel file total value " + TheroryExamTotal +
				  " and PDF file total value  " + grandTotal + " for the following Register " +
				  regno +
				  " number data are same mark in Excel file"
				  ); testCaseScenario.log(Status.PASS, "Both Excel file total value " +
				  TheroryExamTotal + " and PDF file total value  " + grandTotal +
				  " for the following Register " + regno +
				  " number data are same for mark in Excel file"
				  ); }
				  
				  else {
				  
				  System.out.println("Both Excel total value " + TheroryExamTotal +
				 " and PDF file total value  " + grandTotal + " for the following Register " +
				  regno +
				  " number data are not same mark in Excel file"
				  ); testCaseScenario.log(Status.FAIL, "Both Excel file total value " +
				  TheroryExamTotal + " and PDF file total value  " + grandTotal +
				  " for the following Register " + regno +
				  " number data are not same mark in Excel file"
				  ,
				  MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)
				  ).build()); }
				 
				  } catch (Exception e) { System.out.println(
				  "Check the files for the following " + regno + " registration number " +
				  e.getMessage()); testCaseScenario.log(Status.FAIL,
				 "Check the files for the following " + regno + " registration number " +
				  e.getMessage(),
				  MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)
				  ).build());
				  
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
	public void checkMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3,
			Object praticalExam, Object theoryExam, String subjectToFind, Object examTotal, String marks,
			double maxMarks, ExtentTest testCaseName,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva) throws IOException {

		System.out.println(marks);



		if ((marks.equals("---") || marks.equals("NA")) || marks.equals("NE")) {
			System.out.println(markName + " Subject marks: Not available");

			// testCaseScenario.log(Status.PASS, "The following " + regno + markName +"
			// Subject marks is Not available "+ marks +" and status is "+ status);

		} else {
			double marksValue = Double.parseDouble(marks);

//            System.out.println("checking " + markName + " for the following "+ regno);

			if (marksValue > maxMarks * 0.5) {

//            	System.out.println("Marks: of following register number " + regno  +" student subject is " +markName +" (greater than 50% of max marks)");
//           	testCaseScenario.log(Status.PASS,"Marks: of following register number"+regno + " student subject is " + markName +"(greater than 50% of max marks)");
//            
			} else {
				// System.out.println("Marks: " + marksValue + " (not greater than 50% of max
				// marks)");
				// testCaseScenario.log(Status.PASS,"Marks: " + marksValue + " (not greater than
				// 50% of max marks)");
			}

			try {

				if ((status.equals("Pass") || status.equals("Fail")) &&
					    (markName.contains("Theory Internal Sec. Marks") || markName.contains("Theory Total Sec. Marks"))) {

					
					theoryInternalSecMarks(regno, markName, paper1, subjectToFind,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva,testCaseName);
					
				} else if ((status.equals("Pass") || status.equals("Fail"))
						&& markName.contains("Theory (Univ) Sec. Marks")) {

					System.out.println(paper2);
					theoryUnivSecMarks(regno, markName, paper2, subjectToFind, testCaseName);

				}

				else if ((status.equals("Pass") || status.equals("Fail")) && markName.contains("Pratical Internal Sec. Marks")) {
					
				
					practicalInternalSecMarks(regno, markName,paper1,paper2, paper3, subjectToFind, testCaseName);
				}
				
				else if ((status.equals("Pass") || status.equals("Fail")) && markName.contains("Pratical Univ Sec. Marks")||markName.contains("Pratical Total Sec. Marks")) {

					praticalUnivSecMarks(regno, markName, praticalExam, subjectToFind,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva,testCaseName);
				}

				else if ((status.equals("Pass") || status.equals("Fail"))
						&& markName.contains("Theory plus pratical Sec. Marks")) {

					theoryPlusPracticalSecMarks(regno, markName, theoryExam, subjectToFind, testCaseName);

				}

				else {
					System.out.println("==============");
					securedMarks(regno, examTotal, testCaseName);
					System.out.println("==============");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void checkValidationResult(Object regno, Object examdate, Object awardName, Object semester, 
            Object regulation, Object examType, Object paper1, 
            Object paper2, Object paper3,Object praticalExam, Object theroryExam, 
             Object examTotal,String subjectToFind,ExtentTest testCaseName)
			throws IOException {

		Paper1= objectToDataType(paper1);
		ExamTotalScore = objectToDataType(theroryExam);
		
		String Subject = examdate.toString();
		System.out.println(Subject); // Output: "123"

	
	
		try {
			if (Subject.equalsIgnoreCase(subjectToFind)) {
				ExtentTest testCaseScenario = testCaseName
						.createNode( Subject + " Validation Test case has started running");



				try {
					if (Paper1 == ExamTotalScore) {
						System.out.println("Both Final procss score " + paper1 + " and score file " + theroryExam
								+ " for the following Register " + regno + " number data are same for theroryExam mark");
						testCaseScenario.log(Status.PASS, "Both Final procss score " + paper1 + " and score file " + theroryExam
								+ " for the following Register " + regno + " number data are same for theroryExam mark");

					}

					else {
						System.out.println("Both Final procss score " + paper1 + " and score file " + theroryExam
								+ " for the following Register " + regno + " number data are not same for theroryExam mark");
						testCaseScenario.log(Status.FAIL,
								"Both Final procss score " + paper1 + " and score file " + theroryExam
								+ " for the following Register " + regno + " number data are same for theroryExam mark",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println("Check the files for the following " + regno
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number "
									+ e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {

					String theoryExam =  theroryExam.toString();
					String practicalExam =  praticalExam.toString();
					
					int grandTotal = Integer.parseInt(theoryExam) + Integer.parseInt(practicalExam);
					
					System.out.println(grandTotal);
					System.out.println(examTotal);
					Double TotalMark=  objectToDataType(examTotal);
					
					if (grandTotal == TotalMark) {
						System.out.println("After adding both theoryExam plus praticalExam: " + grandTotal + " for the following " + regno
								+ " and subject code "+ subjectToFind +" is same as Ep marks: "+examTotal);

						testCaseScenario.log(Status.PASS, "After adding both theoryExam plus praticalExam: " + grandTotal + " for the following " + regno
								+ " and subject "+ subjectToFind +" is same as Ep marks: "+examTotal);


					} else {
						System.out.println("After adding both theoryExam plus praticalExam: " + grandTotal + " for the following" + regno
								+ " and subject "+ subjectToFind +" is same as Ep marks: "+examTotal);
						testCaseScenario.log(Status.FAIL,"After adding both theoryExam plus praticalExam: " + grandTotal + " for the following" + regno
								+ " and subject "+ subjectToFind +" is same as Ep marks: "+examTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

				} catch (Exception e) {
					System.out.println("Check the files for the following " + regno
							+ " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number "
									+ e.getMessage(),
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
    // Method to check if the obtained marks meet the required percentage
    public static boolean verifyScore(double obtainedMarks, double totalMarks, double percentage) {
        // Calculate the minimum required marks
        minMark = totalMarks * percentage;

        // Print the assigned minimum marks
        System.out.println("Assigned Minimum Marks for " + (percentage * 100) + "% of " + totalMarks + ": " + minMark);

        // Return whether obtained marks meet or exceed the minimum required marks
        return obtainedMarks >= minMark;
    }
    public void validateMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3,
	        Object practicalExam, Object theoryExam, String subjectToFind, Object examTotal, String marks,
	        String maxMarks, double percentage, ExtentTest testCaseName,Object theoryInt,Object theoryTh,Object praticalInt,Object praticalPractical, Object praticalViva) throws IOException {

	    Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)");

	    double sec ;
        double max;
	    
	    System.out.println("marks"+ marks);
	    
	    try {
	        if (!invalidValues.contains(marks.trim())) {

	             sec = Double.parseDouble(marks);
	             max = Double.parseDouble(maxMarks);

	            verifyScore(sec, max, percentage);
	        
	        
	        	checkMarks(regno, markName, paper1, paper2, paper3, practicalExam,
						theoryExam, subjectToFind, examTotal, theoryInternalSecMarks,
						theoryInternalMaxMark, testCaseName,theoryInt,theoryTh,praticalInt,praticalPractical,praticalViva);
	        }
	        else if (invalidValues.contains(marks.trim())) {
	            ExtentTest scenario = testCaseName.createNode(
	    	            markName + " validation for subject " + subjectToFind + " test case has started");
	        	    sec = 0.0; // Default fallback
	        	    max = 0.0; // Optional: or still parse maxMarks separately if needed

	            System.out.println("The following Register number " + regno + " for the subject "
						+ subject + " theory internal sec marks is: "
						+ marks);
	            
	            scenario.log(Status.INFO,
						"The following Register number " + regno + " for the subject "
								+ subject + " theory internal sec marks is: "
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

