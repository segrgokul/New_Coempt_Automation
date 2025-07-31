
package scevtPageModules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; // Set a higher limit

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import pageObjMod.SctevtPom;

public class Scte_VtResultPageSID_07 extends BasicFunctions {

	String status;
	ArrayList<String> windowHandles = null;
	Set<String> processedRegNos = new HashSet<>();
	double remainingGraceMark = 0.0; // Grace calucation with excel

	public void ScTEVT_ResultProcess(String regno, String sycode, ExtentTest testCaseName)
			throws InterruptedException, IOException, AWTException {

//			ExtentReports report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\report.html",true);

		implicitWait(30);

		WebElement btnSemResult;
		String semester = sycode.toString();
		switch (semester) {
		case "1":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn1stSemResult;
			break;
		case "2":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn2ndSemResult;
			break;

		case "3":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn3rdSemResult;
			break;
		case "4":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn4thSemResult;
			break;
		case "5":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn5thSemResult;

			break;
		case "6":
			btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn6thSemResult;

			break;
		default:
			throw new IllegalArgumentException("Invalid Semester: " + semester);
		}

		try {
			ExtentTest testCaseScenario = testCaseName
					.createNode(regno + "Result Page action Test case for " + sycode + " semester has started running");

			if (btnSemResult.isDisplayed()) {

				explicitWait(btnSemResult, 30);
				click(btnSemResult);

				implicitWait(30);

				Thread.sleep(4000);
				// Switch to the second window (new tab)

				windowHandles = new ArrayList<>(driver.getWindowHandles());

				driver.switchTo().window(windowHandles.get(1));

//				if(!SctevtPom.getInstanceSctevtResultXpaths().rollNoTB.isDisplayed()) {
//					
//				
//					
//					   System.out.println("An error occurred for the following regno: " +regno);
//		      	        testCaseScenario.log(Status.WARNING, "Error occurred for the following regno: " +regno,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//		      	  
//		      	        driver.navigate().refresh();
//					
//					driver.close();
//
//					// Switch back to the parent window (index 0 in the list)
//					driver.switchTo().window(windowHandles.get(0));
//				}

				implicitWait(50);
				explicitWait(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB, 30);

				// click(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB);

				sendKeys(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB, regno);

				testCaseScenario.log(Status.INFO,
						"Regno: " + regno + " and Sem: " + sycode + " has entered sucessfully");

				// click(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB);

				implicitWait(50);
				explicitWait(SctevtPom.getInstanceSctevtResultXpaths().dobTB, 30);
				SctevtPom.getInstanceSctevtResultXpaths().dobTB.click();

				if (SctevtPom.getInstanceSctevtResultXpaths().dobTB.isDisplayed()) {

					implicitWait(3);
					click(SctevtPom.getInstanceSctevtResultXpaths().dobTB);

					implicitWait(3);

					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);

					implicitWait(3);

					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);
					implicitWait(3);

					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);

					implicitWait(3);

					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevYear);
					implicitWait(3);
					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevMonth);

					implicitWait(3);
					click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevDate);

					implicitWait(3);
					click(SctevtPom.getInstanceSctevtResultXpaths().btnViewStudentResults);
				} // if

			} // if

		} // try
		catch (Exception e) {

			ExtentTest testCaseScenario = testCaseName
					.createNode(regno + "Result Page action Test case for " + sycode + " semester has started running");

			testCaseScenario.log(Status.FAIL,
					"Error occurred for the following regno: " + regno + " and semester " + sycode,
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		}

	}

	public void ScTEVT_ResultProcess1(String regNo, String examSemester, Map<String, String> subjectsAndMarks,
			ExtentTest testCaseName) throws InterruptedException, IOException, AWTException {

//			ExtentTest testCaseScenario1 = testCaseScenario
//					.createNode( regno + " Validation Test case for "+ sycode +" semester has started running");
//			
		ExtentTest testCaseScenario = testCaseName.createNode(
				"SCET&VT Result process Action for the semester " + examSemester + " and the reg: " + regNo);

		implicitWait(1000);

		try {

			ExtentTest testCaseScenario1 = testCaseScenario.createNode(
					"GraceMark calculation for TH mark with internal mark the following register number " + regNo);

			WebElement uiElement = driver.findElement(By.xpath("//h6[@id='result-st']"));
			// Fetch UI backlog result

			explicitWait(uiElement, 10);

			String resultText = uiElement.getText();

			String[] parts = resultText.split("\\s*:\\s*");

			if (parts.length > 1) {
				System.out.println("UI result:" + parts[1].trim());

				String uiBacklog = parts[1];

				// Print "Pass" after trimming spaces

				WebElement sem = driver.findElement(By.xpath("//span[@id='lblBranch']"));

				String semesters = sem.getText();

				System.out.println("semesters" + semesters);

				testCaseScenario.log(Status.INFO, "semesters" + semesters);

				Thread.sleep(5000);

				scrollTillEnd(driver);

//	      	List<WebElement> cells = driver.findElements(By.xpath("//div[@class='card card-preview']//tbody/tr"));

				// Find all rows in the table
				List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr	"));
				double needed = 0.0;
				StringBuilder result = new StringBuilder("Result: ");
				boolean hasBacklog1 = false;
				List<WebElement> thCells = driver
						.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[7]"));
				List<WebElement> subjectCells = driver
						.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[2]"));
				List<WebElement> subjectCodeAndThCells = driver
						.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[td[1] and td[7]]"));
				List<Double> neededList = new ArrayList<>();
				// Find all rows in the table

				int count = 0; // Counter to track rows processed

				try {
					// Locate total marks element
					WebElement totalMarks = driver
							.findElement(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[last()]/td[last()]"));

					// Wait for results
					explicitWait(uiElement, 50);

					// Extract and parse total marks
					String totalText = totalMarks.getText().trim();
					int totalMark = Integer.parseInt(totalText);

					System.out.println("Total Marks: " + totalMark);

					WebElement uiSessionalMarks = driver
							.findElement(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[last()-1]/td[last()]"));
					String uiSessionalMarkText = uiSessionalMarks.getText().trim();
					int uiSessionalMark = Integer.parseInt(uiSessionalMarkText);

					System.out.println("Total Marks: " + totalMark);

					// Find subject rows
					List<WebElement> subjectRows = driver
							.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr"));
					boolean hasBacklog = false;
					StringBuilder backlogSubjects = new StringBuilder();
					int totalSubjects = subjectRows.size();
					int absentCount = 0;
					String formattedScriptBacklog = ""; // Initialize at the beginning
					StringBuilder mpSubjects = new StringBuilder(); // To store subjects with "-"
					int totalMarksSum = 0; // Track total marks
					boolean hasMP = false; // Track if MP condition is met
					boolean comparisonDone = false; // Ensure comparison is done only once

					count = 0;
					for (WebElement row : subjectRows) {
						String subjectCode = row.findElement(By.xpath("./td[1]")).getText().trim(); // e.g., TH1 or PR1
						String subjectNames = row.findElement(By.xpath("./td[2]")).getText().trim(); // e.g.,
																										// Mathematics
						String maxMarksTHText = row.findElement(By.xpath("./td[3]")).getText().trim();
						String passMarksText = row.findElement(By.xpath("./td[6]")).getText().trim(); // Passing marks
						String marksTHText = row.findElement(By.xpath("./td[7]")).getText().trim(); // Theory marks
						String marksIAText = row.findElement(By.xpath("./td[8]")).getText().trim(); // Internal
																									// Assessment marks
						String marksTotalText = row.findElement(By.xpath("./td[9]")).getText().trim(); // Practical
																										// marks

						String subjectDetails = subjectCode + " - " + subjectNames;

						System.out.println("========================");
						System.out.println("Subject Code: " + subjectCode);
						System.out.println("Subject Name: " + subjectNames);
						System.out.println("Pass Marks: " + passMarksText);
						System.out.println("Theory Marks: " + marksTHText);
						System.out.println("IA Marks: " + marksIAText);
						System.out.println("Total Marks: " + marksTotalText);
						String subjectName = "";
						String expectedMark = "";
						String sessionalMarkExcel = "";

						for (Map.Entry<String, String> expectedEntry : subjectsAndMarks.entrySet()) {
							subjectName = expectedEntry.getKey();
							System.out.println(subjectName);

							String[] marks = expectedEntry.getValue().split(";\\s*");

							expectedMark = marks[0];
							sessionalMarkExcel = marks[1];

							System.out.println(subjectName + "subjectName");
							System.out.println(expectedMark + "expectedMark");
							System.out.println(sessionalMarkExcel + "sessionalMarkExcel");

							String newMark;

							System.out.println("========================");

							if (subjectCode.equals(subjectName)) {
								System.out.println("yes");

								if (expectedMark.contains("AB")) {

									newMark = expectedMark.replace(expectedMark, "A");

								} else {
									newMark = expectedMark.replaceAll("\\.0+$", "");

								}

								System.out.println(marksTHText);
								System.out.println(newMark);
								System.out.println(expectedMark);

								System.out.println(marksTHText.equals((newMark)));

								if (marksTHText.equals((newMark))) {
									System.out.println(subjectDetails + " subject Th Mark " + marksTHText
											+ " from UI is Equals with Excel Mark " + newMark);
									testCaseScenario.log(Status.PASS, subjectDetails + " subject Th Mark " + marksTHText
											+ " from UI is Equals with Excel Mark " + newMark);

									if (semesters.contains("Semester - Regular Exam")) {

										if (Double.parseDouble(newMark) < 25
												|| Double.parseDouble(marksTotalText) < 35) {

//      	        					System.out.println(subjectDetails + " subject Th Mark " +marksTHText +" from UI is not Equals with Excel Mark " + newMark);
//      	        						testCaseScenario.log(Status.PASS, subjectDetails + " subject Th Mark " +marksTHText +" from UI is Not Equals with Excel Mark " + newMark+
//      	        									"The following students has ",
//      	        									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//     		 
											// ExtentTest testCaseScenario1 = testCaseScenario.createNode("GraceMark
											// calculation for TH mark with internal mark the following register number
											// "+regNo);

											int subjectMarksTH = Integer.parseInt(marksTHText);
											int subjectMarksIA = Integer.parseInt(marksIAText);

											testCaseScenario1.log(Status.INFO, "Regular grace logic.");

											testCaseScenario1.log(Status.INFO, "UI Sessional Mark: " + uiSessionalMark);
											testCaseScenario1.log(Status.INFO,
													"Excel Sessional Mark: " + sessionalMarkExcel);

											if (sessionalMarkExcel != null && uiSessionalMarkText != null) {
												String regNoWithSemester = regNo + "_SEM" + examSemester;
												if (!processedRegNos.contains(regNoWithSemester)) {
													remainingGraceMark = Double.parseDouble(sessionalMarkExcel)
															- Double.parseDouble(uiSessionalMarkText);

													testCaseScenario1.log(Status.INFO,
															"Initial grace marks available in Excel sessional mark: "
																	+ remainingGraceMark);

													if (Double.parseDouble(sessionalMarkExcel) == Double
															.parseDouble(uiSessionalMarkText)) {
														testCaseScenario1.log(Status.WARNING, "Both UI SessionalMark "
																+ sessionalMarkExcel + " and Excel SessionalMark "
																+ uiSessionalMarkText
																+ " are equals: due to that remaining grace mark is : "
																+ remainingGraceMark,
																MediaEntityBuilder
																		.createScreenCaptureFromPath(
																				BasicFunctions.capture(driver))
																		.build());

													}
												}

												if (remainingGraceMark > 14) {

													testCaseScenario1.log(Status.FAIL,
															"❌ Sessional mark difference exceeds 15 for RegNo: "
																	+ regNo);
													break;

												}
											}

											if (marksTHText.equals(newMark)) {

												System.out.println(subjectCode + " subject Th Mark " + marksTHText
														+ " from UI is MATCH: with Excel Mark " + newMark);
												testCaseScenario1.log(Status.PASS,
														subjectCode + " subject Th Mark " + marksTHText
																+ " from UI is MATCH: with Excel Mark " + newMark);
												break;
											} else {

												System.out.println(subjectCode + " subject Th Mark " + marksTHText
														+ " from UI is MISMATCH: with Excel Mark " + newMark
														+ " => Expected: " + newMark + ", Found: " + marksTHText);
												testCaseScenario1.log(Status.PASS,
														subjectCode + " subject Th Mark " + marksTHText
																+ " from UI is MISMATCH: with Excel Mark " + newMark
																+ " =>  Expected: " + newMark + ", Found: "
																+ marksTHText);

												double excelMark = Double.parseDouble(newMark);
												double neededForTH = 25.0 - excelMark;
												System.out.println(excelMark + subjectMarksIA);
												double neededForTotal = 35.0 - (excelMark + subjectMarksIA);

												System.out.println(excelMark);
// 	                          System.out.println(iaMarks);
// 	                          System.out.println(neededForTotal);

												if ((neededForTH <= 0) && (neededForTotal <= 0)) {
													testCaseScenario1.log(Status.PASS,
															subjectName + "Th mark is already >= 25 " + newMark
																	+ " and Total mark is already >=35 "
																	+ marksTotalText);

												}

												testCaseScenario1.log(Status.INFO,
														"Checking grace for TH mark " + subjectName + ": needed="
																+ neededForTH + ", available=" + remainingGraceMark);

												double adjustedTH = excelMark;
												if (remainingGraceMark >= neededForTH) {
													adjustedTH = excelMark + neededForTH;
													remainingGraceMark -= neededForTH;
												} else {
													adjustedTH = excelMark + remainingGraceMark;
													remainingGraceMark = 0;
												}

												if (Double.parseDouble(marksTHText) == adjustedTH) {

													System.out.println(subjectName + " subject Th Mark " + marksTHText
															+ " from UI is MATCH after giving with grace: => "
															+ adjustedTH);
													testCaseScenario1.log(Status.PASS,
															subjectName + " subject Th Mark " + marksTHText
																	+ " from UI is MATCH after giving with grace: => "
																	+ adjustedTH);

													formattedScriptBacklog = "Pass(G)";

												} else {

													// testCaseScenario.log(Status.FAIL, " MISMATCH even after grace: "
													// + subjectName + " => Expected: " + adjusted + ", Found: " +
													// actualMark);
													System.out.println(subjectName + " subject Th Mark " + marksTHText
															+ " from UI is MISMATCH even after giving with grace: =>  Expected: "
															+ adjustedTH + ", Found: " + marksTHText);
													testCaseScenario1.log(Status.PASS, subjectName + " subject Th Mark "
															+ marksTHText
															+ " from UI is MISMATCH even after giving with grace: =>  Expected: "
															+ adjustedTH + ", Found: " + marksTHText);

													testCaseScenario1.log(Status.INFO,
															"Checking for grace for Total mark with internal mark the following register number"
																	+ regNo);

													if (marksIAText != null) {

														System.out.println(
																"IA Mark for " + subjectName + ": " + subjectMarksIA);
														testCaseScenario1.log(Status.INFO,
																"IA Mark for " + subjectName + ": " + subjectMarksIA
																		+ " following register number " + regNo);
														testCaseScenario1.log(Status.PASS, "IA Mark for " + subjectName
																+ ": before giving grace with adjustedTh mark = "
																+ adjustedTH + " and IA mark = " + subjectMarksIA
																+ " => and Total = " + (adjustedTH + subjectMarksIA)
																+ " following register number " + regNo);

														// You can now use iaMark in your validation here
													} else {
														System.out.println("No IA mark found for " + subjectName);
														testCaseScenario1.log(Status.INFO,
																"No IA mark found for " + subjectName,
																MediaEntityBuilder
																		.createScreenCaptureFromPath(
																				BasicFunctions.capture(driver))
																		.build());

													}

													System.out.println(adjustedTH + subjectMarksIA);

													System.out.println("neededForTotal: " + neededForTotal);

//                        System.out.println(iaMarks);
//                        System.out.println(neededForTotal);

													if (neededForTotal <= 0) {
														testCaseScenario1.log(Status.PASS,
																subjectName + "Total mark is already >= 35" + newMark);
														continue;
													}

													testCaseScenario1.log(Status.INFO,
															"Checking grace for Total mark " + subjectName + ": needed="
																	+ neededForTotal + ", available="
																	+ remainingGraceMark);

													System.out.println("adjustedTH" + adjustedTH);
													double adjustedTotal = adjustedTH;
													if (remainingGraceMark >= neededForTotal) {
														adjustedTotal = adjustedTH + neededForTotal + subjectMarksIA;
														remainingGraceMark -= neededForTotal;
													} else {
														adjustedTotal = adjustedTH + remainingGraceMark
																+ subjectMarksIA;
														remainingGraceMark = 0;
													}

													System.out.println("adjustedTotal" + adjustedTotal);
													System.out.println("remainingGraceMark" + remainingGraceMark);
													System.out.println("neededForTotal" + neededForTotal);
													System.out.println("UI TH mark: " + marksTHText);
													System.out.println("UI IA mark: " + subjectMarksIA);

													double uiTotalMark = Double.parseDouble(marksTHText)
															+ subjectMarksIA;

													if (uiTotalMark == adjustedTotal) {

														System.out.println(subjectName + " subject Total Mark with IA  "
																+ uiTotalMark
																+ " from UI is MATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.PASS, subjectName
																+ " subject Total Mark with IT " + uiTotalMark
																+ " from UI is MATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.PASS, "Total Mark for "
																+ subjectName
																+ ": after giving grace with marksTHText mark = "
																+ marksTHText + " and IA mark = " + subjectMarksIA
																+ " => and Total = " + adjustedTotal
																+ " following register number " + regNo);

														formattedScriptBacklog = "Pass(G)";

													} else {
														System.out.println(subjectName
																+ "  subject Total Mark with IA  " + uiTotalMark
																+ " from UI is MISMATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.FAIL, subjectName
																+ "  subject Total Mark with IA  " + uiTotalMark
																+ " from UI is MISMATCH after giving with grace: => "
																+ adjustedTotal);

													}
												}

												testCaseScenario1.log(Status.INFO,
														"Remaining grace: " + remainingGraceMark);
												System.out.println("Remaining grace: " + remainingGraceMark);

											} // else

										} else if (Double.parseDouble(newMark) >= 25
												&& Double.parseDouble(marksTotalText) >= 35) {
											testCaseScenario.log(Status.PASS,
													subjectDetails + " subject Th Mark " + marksTHText
															+ " is already >= 25  mark is: " + newMark
															+ " and subject Total Mark "
															+ (Double.parseDouble(marksTHText)
																	+ Double.parseDouble(marksIAText))
															+ " is already >= 35  mark is: " + marksTotalText);
										} else {
											testCaseScenario.log(Status.SKIP,
													subjectDetails + " subject Th Mark " + marksTHText
															+ " from UI is Not Equals with Excel Mark " + newMark
															+ "The following students has ",
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}
									}

								} else {
									System.out.println(subjectDetails + " subject Th Mark " + marksTHText
											+ " from UI is Not Equals with Excel Mark " + newMark);
									testCaseScenario.log(Status.INFO,
											subjectDetails + " subject Th Mark " + marksTHText
													+ " from UI is Not Equals with Excel Mark " + newMark,
											MediaEntityBuilder
													.createScreenCaptureFromPath(BasicFunctions.capture(driver))
													.build());
									if (semesters.contains("Semester - Regular Exam")) {

										if (Double.parseDouble(newMark) < 28) {

//	        					System.out.println(subjectDetails + " subject Th Mark " +marksTHText +" from UI is not Equals with Excel Mark " + newMark);
//	        						testCaseScenario.log(Status.PASS, subjectDetails + " subject Th Mark " +marksTHText +" from UI is Not Equals with Excel Mark " + newMark+
//	        									"The following students has ",
//	        									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//		 

											int subjectMarksTH = Integer.parseInt(marksTHText);
											int subjectMarksIA = Integer.parseInt(marksIAText);

											testCaseScenario1.log(Status.INFO, "Regular grace logic.");

											testCaseScenario1.log(Status.INFO, "UI Sessional Mark: " + uiSessionalMark);
											testCaseScenario1.log(Status.INFO,
													"Excel Sessional Mark: " + sessionalMarkExcel);
											System.out.println("Regular grace logic.");

											System.out.println("remainingGraceMark " + remainingGraceMark);
											System.out.println("UI Sessional Mark: " + uiSessionalMark);
											System.out.println("Excel Sessional Mark: " + sessionalMarkExcel);
											if (sessionalMarkExcel != null && uiSessionalMarkText != null
													&& remainingGraceMark == 0) {
												String regNoWithSemester = regNo + "_SEM" + examSemester;
												if (!processedRegNos.contains(regNoWithSemester)) {
													remainingGraceMark = Double.parseDouble(sessionalMarkExcel)
															- Double.parseDouble(uiSessionalMarkText);

													testCaseScenario1.log(Status.INFO,
															"Initial grace marks available in Excel sessional mark: "
																	+ remainingGraceMark);

													if (Double.parseDouble(sessionalMarkExcel) == Double
															.parseDouble(uiSessionalMarkText)) {
														testCaseScenario1.log(Status.WARNING, "Both UI SessionalMark "
																+ sessionalMarkExcel + " and Excel SessionalMark "
																+ uiSessionalMarkText
																+ " are equals: due to that remaining grace mark is : "
																+ remainingGraceMark,
																MediaEntityBuilder
																		.createScreenCaptureFromPath(
																				BasicFunctions.capture(driver))
																		.build());

													}
												}

												if (remainingGraceMark > 14) {

													testCaseScenario1.log(Status.FAIL,
															"❌ Sessional mark difference exceeds 15 for RegNo: "
																	+ regNo);
													break;

												}
											}

											if (marksTHText.equals(newMark)) {

												System.out.println(subjectCode + " subject Th Mark " + marksTHText
														+ " from UI is MATCH: with Excel Mark " + newMark);
												testCaseScenario1.log(Status.PASS,
														subjectCode + " subject Th Mark " + marksTHText
																+ " from UI is MATCH: with Excel Mark " + newMark);
												break;
											} else {

												System.out.println(subjectCode + " subject Th Mark " + marksTHText
														+ " from UI is MISMATCH: with Excel Mark " + newMark
														+ " => Expected: " + newMark + ", Found: " + marksTHText);
												testCaseScenario1.log(Status.PASS,
														subjectCode + " subject Th Mark " + marksTHText
																+ " from UI is MISMATCH: with Excel Mark " + newMark
																+ " =>  Expected: " + newMark + ", Found: "
																+ marksTHText);

												double excelMark = Double.parseDouble(newMark);
												double neededForTH = 25.0 - excelMark;

												System.out.println(excelMark + subjectMarksIA);
												double neededForTotal = 35.0 - (excelMark + subjectMarksIA);

												System.out.println(excelMark);
//	 	                          System.out.println(iaMarks);
//	 	                          System.out.println(neededForTotal);

												if ((neededForTH <= 0) && (neededForTotal <= 0)) {
													testCaseScenario1.log(Status.PASS,
															subjectName + "Th mark is already >= 25 " + newMark
																	+ " and Total mark is already >=35 "
																	+ marksTotalText);

												}
												testCaseScenario1.log(Status.INFO,
														"Checking grace for TH mark " + subjectName + ": needed="
																+ neededForTH + ", available=" + remainingGraceMark);

												double adjustedTH = excelMark;
												if (remainingGraceMark >= neededForTH) {
													adjustedTH = excelMark + neededForTH;
													remainingGraceMark -= neededForTH;
												} else {
													adjustedTH = excelMark + remainingGraceMark;
													remainingGraceMark = 0;
												}

												if (Double.parseDouble(marksTHText) == adjustedTH) {

													System.out.println(subjectName + " subject Th Mark " + marksTHText
															+ " from UI is MATCH after giving with grace: => "
															+ adjustedTH);
													testCaseScenario1.log(Status.PASS,
															subjectName + " subject Th Mark " + marksTHText
																	+ " from UI is MATCH after giving with grace: => "
																	+ adjustedTH);

													formattedScriptBacklog = "Pass(G)";

												} else {

													// testCaseScenario.log(Status.FAIL, " MISMATCH even after grace: "
													// + subjectName + " => Expected: " + adjusted + ", Found: " +
													// actualMark);
													System.out.println(subjectName + " subject Th Mark " + marksTHText
															+ " from UI is MISMATCH even after giving with grace: =>  Expected: "
															+ adjustedTH + ", Found: " + marksTHText);
													testCaseScenario1.log(Status.PASS, subjectName + " subject Th Mark "
															+ marksTHText
															+ " from UI is MISMATCH even after giving with grace: =>  Expected: "
															+ adjustedTH + ", Found: " + marksTHText);

													testCaseScenario1.log(Status.INFO,
															"Checking for grace for Total mark with internal mark the following register number"
																	+ regNo);

													if (marksIAText != null) {

														System.out.println(
																"IA Mark for " + subjectName + ": " + subjectMarksIA);
														testCaseScenario1.log(Status.INFO,
																"IA Mark for " + subjectName + ": " + subjectMarksIA
																		+ " following register number " + regNo);
														testCaseScenario1.log(Status.PASS, "IA Mark for " + subjectName
																+ ": before giving grace with adjustedTh mark = "
																+ adjustedTH + " and IA mark = " + subjectMarksIA
																+ " => and Total = " + (adjustedTH + subjectMarksIA)
																+ " following register number " + regNo);

														// You can now use iaMark in your validation here
													} else {
														System.out.println("No IA mark found for " + subjectName);
														testCaseScenario1.log(Status.INFO,
																"No IA mark found for " + subjectName,
																MediaEntityBuilder
																		.createScreenCaptureFromPath(
																				BasicFunctions.capture(driver))
																		.build());

													}

													System.out.println(adjustedTH + subjectMarksIA);

													System.out.println("neededForTotal: " + neededForTotal);

//                      System.out.println(iaMarks);
//                      System.out.println(neededForTotal);

													if (neededForTotal <= 0) {
														testCaseScenario1.log(Status.PASS,
																subjectName + "Total mark is already >= 35" + newMark);
														continue;
													}

													testCaseScenario1.log(Status.INFO,
															"Checking grace for Total mark " + subjectName + ": needed="
																	+ neededForTotal + ", available="
																	+ remainingGraceMark);

													System.out.println("adjustedTH" + adjustedTH);
													double adjustedTotal = adjustedTH;
													if (remainingGraceMark >= neededForTotal) {
														adjustedTotal = adjustedTH + neededForTotal + subjectMarksIA;
														remainingGraceMark -= neededForTotal;
													} else {
														adjustedTotal = adjustedTH + remainingGraceMark
																+ subjectMarksIA;
														remainingGraceMark = 0;
													}

													System.out.println("adjustedTotal" + adjustedTotal);
													System.out.println("remainingGraceMark" + remainingGraceMark);
													System.out.println("neededForTotal" + neededForTotal);
													System.out.println("UI TH mark: " + marksTHText);
													System.out.println("UI IA mark: " + subjectMarksIA);

													double uiTotalMark = Double.parseDouble(marksTHText)
															+ subjectMarksIA;

													if (uiTotalMark == adjustedTotal) {

														System.out.println(subjectName + " subject Total Mark with IA  "
																+ uiTotalMark
																+ " from UI is MATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.PASS, subjectName
																+ " subject Total Mark with IT " + uiTotalMark
																+ " from UI is MATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.PASS, "Total Mark for "
																+ subjectName
																+ ": after giving grace with marksTHText mark = "
																+ marksTHText + " and IA mark = " + subjectMarksIA
																+ " => and Total = " + adjustedTotal
																+ " following register number " + regNo);

														formattedScriptBacklog = "Pass(G)";

													} else {
														System.out.println(subjectName
																+ "  subject Total Mark with IA  " + uiTotalMark
																+ " from UI is MISMATCH after giving with grace: => "
																+ adjustedTotal);
														testCaseScenario1.log(Status.FAIL, subjectName
																+ "  subject Total Mark with IA  " + uiTotalMark
																+ " from UI is MISMATCH after giving with grace: => "
																+ adjustedTotal);

													}
												}

											} // for

										}
									} else if (semesters.contains("Semester - Ex-Regular Exam")) {

										testCaseScenario1.log(Status.SKIP,
												"Grace mark logic skip for the following Register No: " + regNo
														+ " Due to " + semesters + "name");

									}

								}

							}
						}

						if (!marksTotalText.isEmpty() && !passMarksText.isEmpty()) {
							if (marksTHText.equalsIgnoreCase("A") || marksIAText.equalsIgnoreCase("A")
									|| marksTotalText.equalsIgnoreCase("A")) {
								hasBacklog = true;
								needed = 0.0;
								backlogSubjects.append(subjectCode).append(",");
								System.out.println(subjectDetails + " : Absent with Total Mark " + marksTotalText);
								neededList.add(needed);
								System.out.println(needed);

								testCaseScenario.log(Status.PASS,
										subjectDetails + " : Absent with Total Mark " + marksTotalText);
							} else if (marksTHText.equals("-") || marksIAText.equals("-")
									|| marksTotalText.equals("-")) {
								hasMP = true;
								mpSubjects.append(subjectCode).append(" - ").append(subjectDetails).append(" : -\n");
								System.out.println(subjectDetails + " : Failed with Th mark " + marksTHText
										+ " and Total Marks " + marksTotalText);
								testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark "
										+ marksTHText + " and Total Marks " + marksTotalText);
								needed = 0.0;
								neededList.add(needed);
							} else {
								int passMarks = Integer.parseInt(passMarksText);
								int totalSubjectMarks;

								System.out.println(marksIAText.isEmpty());
								System.out.println(marksTHText.isEmpty());

								if ((marksIAText.isEmpty() && marksTHText.isEmpty())

								) {

									totalSubjectMarks = Integer.parseInt(marksTotalText);

									totalMarksSum += totalSubjectMarks;

									if (totalSubjectMarks < passMarks) {
										hasBacklog = true;
										backlogSubjects.append(subjectCode).append(",");
										System.out.println(
												subjectDetails + " : Failed with Total Marks " + totalSubjectMarks);
										testCaseScenario.log(Status.PASS,
												subjectDetails + " : Failed with Total Marks " + totalSubjectMarks);
									} else {
										System.out.println(
												subjectDetails + " : Passed with Total Marks " + totalSubjectMarks);
										testCaseScenario.log(Status.PASS,
												subjectDetails + " : Passed with Total Marks " + totalSubjectMarks);
									}
								} else {
									int subjectMarksTH = Integer.parseInt(marksTHText);
									int subjectMarksIA = Integer.parseInt(marksIAText);
									int subjectMaxMarksTH = Integer.parseInt(maxMarksTHText);

									totalSubjectMarks = subjectMarksTH + subjectMarksIA;

									if (subjectMarksTH == 0 && uiBacklog.contains("DI")) {
										if (totalMarksSum > 280
												&& Integer.parseInt(subjectCode.replaceAll("\\D", "")) > 280) {
											marksTHText = "DI-" + subjectCode;
										} else {
											marksTHText = "Fail";
											hasBacklog = true;
											backlogSubjects.append(subjectCode).append(",");
										}
										System.out
												.println(subjectDetails + " : TH mark is 0, updated to " + marksTHText);
										testCaseScenario.log(Status.PASS,
												subjectDetails + " : TH mark is 0, updated to " + marksTHText);
									}

									else if (semesters.contains("Semester - Ex-Regular Exam - Mercy Exam")) {

										if (subjectMaxMarksTH == 80) {
											if (subjectMarksTH < 28 || totalSubjectMarks < passMarks) {

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ "  and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);

											} else {
												System.out.println(subjectDetails + " : Passed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Passed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
											}
										} else if (subjectMaxMarksTH == 70) {
											if (subjectMarksTH < 25 || totalSubjectMarks < passMarks) {

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);

											} else {
												System.out.println(subjectDetails + " : Passed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Passed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
											}

										} else {
											System.out.println(subjectDetails + " : Failed with Th mark "
													+ subjectMarksTH + " and IA Marks " + subjectMarksIA
													+ " and Total Marks " + totalSubjectMarks);
											testCaseScenario.log(Status.FAIL,
													subjectDetails + " : Failed with Th mark " + subjectMarksTH
															+ " and IA Marks " + subjectMarksIA + " and Total Marks "
															+ totalSubjectMarks);

										}

									} // else if

									else if (semesters.contains("1st Semester - Regular Exam ")) {
										if (subjectMarksTH < 25 || totalSubjectMarks < passMarks) {

											hasBacklog = true;
											backlogSubjects.append(subjectCode).append(",");
											System.out.println(subjectDetails + " : Failed with Th mark "
													+ subjectMarksTH + " and IA Marks " + subjectMarksIA
													+ " and Total Marks " + totalSubjectMarks);
											testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark "
													+ subjectMarksTH + " and Total Marks " + totalSubjectMarks);

										} else {
											System.out.println(subjectDetails + " : Passed with Th mark "
													+ subjectMarksTH + " and IA Marks " + subjectMarksIA
													+ " and Total Marks " + totalSubjectMarks);
											testCaseScenario.log(Status.PASS,
													subjectDetails + " : Passed with Th mark " + subjectMarksTH
															+ " and IA Marks " + subjectMarksIA + " and Total Marks "
															+ totalSubjectMarks);
										}
									} else if (subjectMarksTH < 25 || totalSubjectMarks < passMarks) {

										if (subjectMarksTH >= 18 && subjectMarksTH < 25) {

											needed = 25 - subjectMarksTH;
											System.out.println("needed:" + needed);
											System.out.println(needed);

											testCaseScenario.log(Status.PASS,
													"subjectMarksTH: " + subjectMarksTH + " needed: " + needed);

											if (needed <= 7) {
												System.out.println("The following student " + subjectCode
														+ " is lesser than or equal to 7 " + needed);
												testCaseScenario.log(Status.PASS, "The following student " + subjectCode
														+ " is lesser than or equal to Seven " + needed);

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
												System.out.println("needed:" + needed);
												neededList.add(needed);
											} else {
												System.out.println("The following student " + subjectCode
														+ " is not lesser than or equal to 8 " + needed);
												testCaseScenario.log(Status.PASS, "The following student " + subjectCode
														+ " is not lesser than or equal to eight " + needed);

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
												neededList.add(needed);
												System.out.println("needed:" + needed);
											}

										} else if (subjectMarksTH > 25 && totalSubjectMarks < 35) {
											needed = 35 - totalSubjectMarks;
											System.out.println("needed:" + needed);
											System.out.println(needed);

											testCaseScenario.log(Status.PASS,
													"subjectMarksTotal: " + totalSubjectMarks + " needed: " + needed);

											if (needed <= 7) {
												System.out.println("The following student " + subjectCode
														+ " is lesser than or equal to 8 " + needed);
												testCaseScenario.log(Status.PASS, "The following student " + subjectCode
														+ " is lesser than or equal to eight " + needed);

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
												System.out.println("needed:" + needed);
												neededList.add(needed);
											} else {
												System.out.println("The following student " + subjectCode
														+ " is not lesser than or equal to 8 " + needed);
												testCaseScenario.log(Status.PASS, "The following student " + subjectCode
														+ " is not lesser than or equal to eight " + needed);

												hasBacklog = true;
												backlogSubjects.append(subjectCode).append(",");
												System.out.println(subjectDetails + " : Failed with Th mark "
														+ subjectMarksTH + " and IA Marks " + subjectMarksIA
														+ " and Total Marks " + totalSubjectMarks);
												testCaseScenario.log(Status.PASS,
														subjectDetails + " : Failed with Th mark " + subjectMarksTH
																+ " and IA Marks " + subjectMarksIA
																+ " and Total Marks " + totalSubjectMarks);
												neededList.add(needed);
												System.out.println("needed:" + needed);
											}
										}

										else {
											hasBacklog = true;
											needed = 0.0;
											backlogSubjects.append(subjectCode).append(",");
											System.out.println(subjectDetails + " : Failed with Th mark "
													+ subjectMarksTH + " and IA Marks " + subjectMarksIA
													+ " and Total Marks " + totalSubjectMarks);
											testCaseScenario.log(Status.PASS,
													subjectDetails + " : Failed with Th mark " + subjectMarksTH
															+ " and IA Marks " + subjectMarksIA + " and Total Marks "
															+ totalSubjectMarks);
											neededList.add(needed);
											System.out.println("needed:" + needed);
										}

//	      	                
//	      	                    hasBacklog = true;
//	      	                    backlogSubjects.append(subjectCode).append(",");
//	      	                    System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
//	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
//
//	      	                	

									} else {
										System.out.println(subjectDetails + " : Passed with Th mark " + subjectMarksTH
												+ " and IA Marks " + subjectMarksIA + " and Total Marks "
												+ totalSubjectMarks);
										testCaseScenario.log(Status.PASS,
												subjectDetails + " : Passed with Th mark " + subjectMarksTH
														+ " and IA Marks " + subjectMarksIA + " and Total Marks "
														+ totalSubjectMarks);
									}
								}
							}
						}
					}

					testCaseScenario.log(Status.PASS, "neededList " + neededList);
					double totalNeededList = 0.0;

					if (!neededList.contains(0.0)) {
						for (int i = 0; i < neededList.size(); i++) {
							totalNeededList += neededList.get(i);

							System.out.println(totalNeededList);

						}
						testCaseScenario.log(Status.PASS, "SummaCheck1 " + totalNeededList);
						System.out.println(totalNeededList);
					} else {
						testCaseScenario.log(Status.PASS, "SummaCheck2 " + totalNeededList);
						System.out.println(totalNeededList);
					}
					// Process formattedScriptBacklog based on backlogSubjects
					if (formattedScriptBacklog.isEmpty()) {
						formattedScriptBacklog = backlogSubjects.toString().trim().replaceAll(", $", "");
					}

					if (!formattedScriptBacklog.isEmpty() && !formattedScriptBacklog.startsWith("DI")) {
						String[] subjects = formattedScriptBacklog.split(",");
						if (formattedScriptBacklog.contains("Pass(G)")) {
							formattedScriptBacklog = formattedScriptBacklog;

						} else if (!subjects[0].startsWith("Back-")) {
							subjects[0] = "Back-" + subjects[0].trim();
						}

						for (int i = 1; i < subjects.length; i++) {
							subjects[i] = subjects[i].replace("Back-", "").trim();
						}

						formattedScriptBacklog = String.join(",", subjects);
					}

					if (formattedScriptBacklog.isEmpty()) {
						formattedScriptBacklog = "Pass";
					}

					// Check total marks condition only after processing all subjects
					if (hasMP) {
						formattedScriptBacklog = "MP";
					} else if (totalMark < 280) {
						formattedScriptBacklog = "Fail";
					} else if (formattedScriptBacklog.startsWith("Back-") && totalMarksSum >= 280) {
						formattedScriptBacklog = formattedScriptBacklog.replace("Back-", "");
					}

					// Print subjects with "-" values only once
					if (mpSubjects.length() > 0) {
						System.out.println("Subjects with '-':\n" + mpSubjects.toString().trim());
					}

					// comparing fr remaining grace mark

					testCaseScenario1.log(Status.INFO, "Remaining grace: " + remainingGraceMark);
					System.out.println("Remaining grace: " + remainingGraceMark);

					if (remainingGraceMark == 0) {
						testCaseScenario1.log(Status.PASS,
								"Grace mark for RegNo: " + regNo + " is equals zero " + remainingGraceMark);

					} else if (remainingGraceMark != 0) {
						testCaseScenario1.log(Status.FAIL,
								"Grace mark for RegNo: " + regNo + " is not equals zero " + remainingGraceMark,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					} else {
						testCaseScenario1.log(Status.FAIL,
								"Please check the Grace mark for RegNo: " + regNo + " remaining grace mark "
										+ remainingGraceMark,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					// Compare with UI only once
					if (!comparisonDone) {
						if (formattedScriptBacklog.equalsIgnoreCase(uiBacklog)) {
							System.out.println("Backlog comparison PASS: Script - " + formattedScriptBacklog
									+ " | UI - " + uiBacklog);
							testCaseScenario.log(Status.PASS, "Backlog comparison PASS: Script - "
									+ formattedScriptBacklog + " | UI - " + uiBacklog);

							if (formattedScriptBacklog.contains("Back")) {

								if (totalNeededList > 0 && totalNeededList <= 15) {
									testCaseScenario.log(Status.FAIL,
											"Please check the Backlog comparison PASS: Script - "
													+ formattedScriptBacklog + " | UI - " + uiBacklog
													+ " Total needed List value is: " + totalNeededList,
											MediaEntityBuilder
													.createScreenCaptureFromPath(BasicFunctions.capture(driver))
													.build());
									System.out.println("Please check the Backlog comparison PASS: Script - "
											+ formattedScriptBacklog + " | UI - " + uiBacklog
											+ " Total needed List value is: " + totalNeededList);

								} else {
									testCaseScenario.log(Status.PASS,
											"The Backlog comparison PASS: Script - " + formattedScriptBacklog
													+ " | UI - " + uiBacklog + " Total needed List value is: "
													+ totalNeededList);
									System.out.println("The Backlog comparison PASS: Script - " + formattedScriptBacklog
											+ " | UI - " + uiBacklog + " Total needed List value is: "
											+ totalNeededList);

								}

							}

						}

						else if ((formattedScriptBacklog.contentEquals(uiBacklog))) {

							System.out.println("Backlog comparison INFO: Script - " + formattedScriptBacklog
									+ " | UI - " + uiBacklog);
							testCaseScenario.log(Status.SKIP, "Backlog comparison INFO: Script - "
									+ formattedScriptBacklog + " | UI - " + uiBacklog);

						}

						else if ((formattedScriptBacklog.equals("Pass")) && (uiBacklog.equals("Pass(G)"))) {

							System.out.println("Backlog comparison INFO: Script - " + formattedScriptBacklog
									+ " | UI - " + uiBacklog);
							testCaseScenario.log(Status.SKIP, "Backlog comparison INFO: Script - "
									+ formattedScriptBacklog + " | UI - " + uiBacklog);

						}

						else {
							System.out.println("Backlog comparison FAILED: Script - " + formattedScriptBacklog
									+ " | UI - " + uiBacklog);
							testCaseScenario.log(Status.FAIL,
									"Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - "
											+ uiBacklog,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
											.build());
						}
						comparisonDone = true;
					}

				} catch (Exception e1) {
					System.out.println("An error occurred: " + e1.getMessage());
					testCaseScenario.log(Status.WARNING,
							"Error occurred while fetching total marks: " + e1.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					e1.printStackTrace();
				}

			}
			driver.close();

			// Switch back to the parent window (index 0 in the list)
			driver.switchTo().window(windowHandles.get(0));

		} catch (Exception e) {

			if (SctevtPom.getInstanceSctevtResultXpaths().btnAlertOk.isDisplayed()) {

				implicitWait(30);
				testCaseScenario.log(Status.FAIL, "Please check the following regno " + regNo,
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				click(SctevtPom.getInstanceSctevtResultXpaths().btnAlertOk);

				System.out.println("Please check the following regno " + regNo);

				// Switch back to the parent window (index 0 in the list)
				// driver.switchTo().window(windowHandles.get(0));

				driver.close();

				// Switch back to the parent window (index 0 in the list)
				driver.switchTo().window(windowHandles.get(0));

				if (!SctevtPom.getInstanceSctevtResultXpaths().rollNoTB.isDisplayed()) {

					System.out.println("An error occurred for the following regno: " + regNo);
					testCaseScenario.log(Status.FAIL, "Error occurred for the following regno: " + regNo,
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					driver.navigate().refresh();

					driver.close();

					// Switch back to the parent window (index 0 in the list)
					driver.switchTo().window(windowHandles.get(0));
				}

			}

			e.printStackTrace();
			driver.navigate().refresh();

			testCaseScenario.log(Status.FAIL, "Please check the following regno " + regNo + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//			driver.close();
//			// Switch back to the parent window (index 0 in the list)
//			driver.switchTo().window(windowHandles.get(0));
		}
	}

	// catch

	public String processText(String thText) {
		// Trim the text to remove extra spaces
		String trimmedText = thText.trim();

		// Check for exact match for "A" (Absent)
		if (trimmedText.equalsIgnoreCase("A")) {
			return "A"; // Mark as absent
		}

		// Extract digits (marks) from the text
		String number = trimmedText.replaceAll("\\D", "");

		// Extract subject code (assuming it's the first non-numeric word)
		String subjectCode = trimmedText.replaceAll("\\d", "").trim();

		if (!number.isEmpty()) {
			int num = Integer.parseInt(number);
			// If number is greater than or equal to 25, it's a Pass, otherwise "Back-" +
			// subjectCode
			return (num >= 25) ? "Pass" : "Back-" + subjectCode;
		}

		return "Unknown"; // Default case if no valid number is found
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

	public void checkingGrace() {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr	"));

		StringBuilder result = new StringBuilder("Result: ");
		boolean hasBacklog1 = false;
		List<WebElement> thCells = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[7]"));
		List<WebElement> subjectCells = driver
				.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[2]"));
		List<WebElement> subjectCodeAndThCells = driver
				.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[td[1] and td[7]]"));

	}

}
