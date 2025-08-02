package knrEnrollmentPageReportCardPatternBased;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import knrPageModules.KnrReportEnrollmentPageValidation;

public class KnrReportCard_BDS_CoursePattern extends BasicFunctions {
	String subject;
	String status;

	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

	public void processBDSPatternPdf(File latestFile, String Regno, String semester, String regulation, Object paper1,
			Object paper2, Object paper3, Object paper4, Object theoryExamTotal, Object practicalExamTotal,
			Object grandTotal, ExtentTest testCaseName, String subjectToFind) throws IOException {
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

					String courseNameRegex = "(?i)(BDS)\\s+";

					Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
					Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

					if (courseNameRegexPatternMatcher.find()) {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");
						System.out.println("MathchFound: " + courseNameRegexPatternMatcher.group()); // Prints "MDS"

						testCaseScenario.log(Status.PASS, "MathchFound: " + courseNameRegexPatternMatcher.group());

					}

					else {

						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the following " + Regno + " Test case has started running");

						testCaseScenario.log(Status.FAIL,
								" Please check the The following Register number " + Regno + " for the subject "
										+ subjectToFind + " No match found",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("No match found.");
					}

					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BDS"))
							&& (semester.trim().equalsIgnoreCase("Year 1")) && (regulation.trim().contains("2016"))) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
											+ " of the  following " + Regno + " Test case");
							System.out.println(text);

							Pattern bdsY1R16SubjectRegexPattern = Pattern.compile(
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
									Pattern.DOTALL | Pattern.MULTILINE);

							Matcher bdsY1R16SubjectRegexPatternMatcher = bdsY1R16SubjectRegexPattern.matcher(text);

							while (bdsY1R16SubjectRegexPatternMatcher.find()) {

								try {
									testCaseScenario.log(Status.PASS,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has matched");

									System.out.println("==============");
									subject = bdsY1R16SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

									System.out.println("bdsSubject:" + subject);

//						
									String theoryThPlusIntVivaMaxMark = bdsY1R16SubjectRegexPatternMatcher.group(2);

									String theoryInt = bdsY1R16SubjectRegexPatternMatcher.group(3);
									String theoryTh = bdsY1R16SubjectRegexPatternMatcher.group(4);
									String theoryViva = bdsY1R16SubjectRegexPatternMatcher.group(5);
									String theoryThPlusIntVivaSecMark = bdsY1R16SubjectRegexPatternMatcher.group(6);
									String practicalPlusIntMaxMark = bdsY1R16SubjectRegexPatternMatcher.group(7);

									String practicalInt = bdsY1R16SubjectRegexPatternMatcher.group(8);
									String practicalPractical = bdsY1R16SubjectRegexPatternMatcher.group(9);
									String practicalVivaPR = bdsY1R16SubjectRegexPatternMatcher.group(10);
									String practicalTotalSecMarks = bdsY1R16SubjectRegexPatternMatcher.group(11);

									String thPlusPracticalMaxMark = bdsY1R16SubjectRegexPatternMatcher.group(12);
									String thPlusPracticalSecMark = bdsY1R16SubjectRegexPatternMatcher.group(13);
									status = bdsY1R16SubjectRegexPatternMatcher.group(14);

									System.out.println("==============");

									System.out.println("Subject: " + subject);

									System.out.println("TH + int Viva Max Marks: " + theoryThPlusIntVivaMaxMark);
									System.out.println("Theory Int Marks: " + theoryInt);
									System.out.println("Theory TH Marks: " + theoryTh);
									System.out.println("Theory Viva Marks: " + theoryViva);
									System.out.println("TH + int Viva Sec Marks: " + theoryThPlusIntVivaSecMark);
									System.out.println("Practical + int Max Marks: " + practicalPlusIntMaxMark);
									System.out.println("Practical + int Marks: " + practicalInt);
									System.out.println("Practical Practical: " + practicalPractical);
									System.out.println("Practical Viva PR: " + practicalVivaPR);
									System.out.println("Practical + Viva Sec. Marks: " + practicalTotalSecMarks);
									System.out.println("Theory + Practical Max Marks: " + thPlusPracticalMaxMark);
									System.out.println("Theory + Practical Sec. Marks: " + thPlusPracticalSecMark);
									System.out.println("Result: " + status);
									System.out.println("==============");
									if ((status.trim().equals("Pass") || status.trim().equals("Fail")
											|| status.trim().equals("AP"))
											&& subject.replaceAll("\\s+", "")
													.equals(subjectToFind.replaceAll("\\s+", ""))) {

										try {

											PageValidation.nonValidateMarks(Regno, "Theory Int sec. marks", subject,
													subjectToFind, theoryInt, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryTh, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory TH sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryViva, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory Viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double theoryIntMark = 0;
											double theoryThMark = 0;
											double theoryVivaMark = 0;
											double finalTheoryMark;

											if (invalidValues.contains(theoryInt)) {
												theoryIntMark = 0.0;

											} else if (!invalidValues.contains(theoryInt)) {
												theoryIntMark = Double.parseDouble(theoryInt);
											}

											if (invalidValues.contains(theoryTh)) {
												theoryThMark = 0.0;

											} else if (!invalidValues.contains(theoryTh)) {
												theoryThMark = Double.parseDouble(theoryTh);
											}

											if (invalidValues.contains(theoryViva)) {
												theoryVivaMark = 0.0;

											} else if (!invalidValues.contains(theoryViva)) {
												theoryVivaMark = Double.parseDouble(theoryViva);
											}

											finalTheoryMark = theoryIntMark + theoryThMark + theoryVivaMark;

											if (finalTheoryMark == Double.parseDouble(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",
														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											} else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.FAIL,
														"Please check the following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											}
										}

										try {
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryThPlusIntVivaSecMark,
													theoryThPlusIntVivaMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory + int + via Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject + " of theory sec mark is: "
															+ theoryThPlusIntVivaSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Practical Int sec. marks", subject,
													subjectToFind, practicalInt, practicalPlusIntMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Practical Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt);

										}

										try {
											PageValidation.nonValidateMarks(Regno, "Pratical pratical sec. marks",
													subject, subjectToFind, practicalPractical, practicalPlusIntMaxMark,
													0.0, testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													"Pratical pratical sec. marks validation for the subject " + subject
															+ " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical pratical sec marks is: "
															+ practicalPractical,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject
													+ " Pratical pratical sec marks is: " + practicalPractical);

										}
										try {

											PageValidation.nonValidateMarks(Regno, "Pratical viva sec. marks", subject,
													subjectToFind, practicalVivaPR, practicalPlusIntMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical viva sec. marks is: "
															+ practicalVivaPR,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " Pratical viva sec. marks is: "
													+ practicalVivaPR);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double praticalIntMark = 0;
											double practicalPracticalMark = 0;
											double praticalVivaMark = 0;
											double finalPraticalMark;

											if (invalidValues.contains(practicalInt)) {
												praticalIntMark = 0.0;

											} else if (!invalidValues.contains(practicalInt)) {
												praticalIntMark = Double.parseDouble(practicalInt);
											}

											if (invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = 0.0;

											} else if (!invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = Double.parseDouble(practicalPractical);
											}

											if (invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = 0.0;

											} else if (!invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = Double.parseDouble(practicalVivaPR);
											}

											finalPraticalMark = praticalIntMark + practicalPracticalMark
													+ praticalVivaMark;

											if (finalPraticalMark == Double.parseDouble(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",

														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											} else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Please check the following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"Please check the following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											}
										}

										try {

											PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal, practicalTotalSecMarks,
													practicalPlusIntMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of pravtical sec mark is: " + practicalTotalSecMarks,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, thPlusPracticalSecMark,
													thPlusPracticalMaxMark, 0.50, testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Grand Total Sec Marks validation for subject "
															+ subjectToFind + " test case has started");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks" + thPlusPracticalSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

									} // if bracket
								} // try
								catch (Exception e) {
									testCaseScenario.log(Status.FAIL,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has not matched");
								}
							} // while

						} // try
						catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.FAIL,
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case has fail to started running",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
											.build());
							testCaseScenario.log(Status.FAIL, e.getMessage(), MediaEntityBuilder
									.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println(text);
						}
					} // IF_BDS_Year1

					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BDS"))
							&& (semester.trim().equalsIgnoreCase("Year 2")) && (regulation.trim().contains("2016"))) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
											+ " of the  following " + Regno + " Test case");
							System.out.println(text);
							Pattern bdsY2R16SubjectRegexPattern = Pattern
									.compile("(?m)^\\s*(?!Theory|Result|Subject|Paper)"
											+ "([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-/]+)*)\\s+"
											+ "(?=\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))" // Lookahead: not part of
																						// subject, marks must start
																						// here
											+ "((?:(?:\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*){2,13})"
											+ "(AP|Pass|Fail)", Pattern.DOTALL | Pattern.MULTILINE);

							Matcher bdsY1R16SubjectRegexPatternMatcher = bdsY2R16SubjectRegexPattern.matcher(text);
							while (bdsY1R16SubjectRegexPatternMatcher.find()) {

								try {
									testCaseScenario.log(Status.PASS,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has matched");

									String subjecst = bdsY1R16SubjectRegexPatternMatcher.group(1).replaceAll("\\R", " ")
											.trim();
									String allMarks = bdsY1R16SubjectRegexPatternMatcher.group(2).trim();

									System.out.println("subjecst" + subjecst);
									System.out.println("allMarks" + allMarks);

									status = bdsY1R16SubjectRegexPatternMatcher.group(3).trim();

									String theoryThPlusIntVivaMaxMark = "";
									String theoryInt = "";
									String theoryTh = "";
									String theoryViva = "";
									String theoryThPlusIntVivaSecMark = "";
									String practicalPlusIntMaxMark = "";
									String practicalInt = "";
									String practicalPractical = "";
									String practicalVivaPR = "";
									String practicalTotalSecMarks = "";
									String thPlusPracticalMaxMark = "";
									String thPlusPracticalSecMark = "";

									// subject

						
									String[] subjectParts = bdsY1R16SubjectRegexPatternMatcher.group(1).trim()
											.split("\\s+");

									if (subjectParts.length >= 6
											&& subjectParts[subjectParts.length - 5].matches("NA|\\d+")
											&& subjectParts[subjectParts.length - 4].matches("NA|\\d+")
											&& subjectParts[subjectParts.length - 3].matches("NA|\\d+")
											&& subjectParts[subjectParts.length - 2].matches("NA|\\d+")
											&& subjectParts[subjectParts.length - 1].matches("NA|\\d+")) {

										subject = String.join(" ",
												Arrays.copyOfRange(subjectParts, 0, subjectParts.length - 5));
										theoryThPlusIntVivaMaxMark = subjectParts[subjectParts.length - 5];
										theoryInt = subjectParts[subjectParts.length - 4];
										theoryTh = subjectParts[subjectParts.length - 3];
										theoryViva = subjectParts[subjectParts.length - 2];
										theoryThPlusIntVivaSecMark = subjectParts[subjectParts.length - 1];

									} else {
										// Only subject
										subject = bdsY1R16SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();
										System.out.println("Unexpected number of marks: " + subjectParts.length);
										testCaseScenario.log(Status.INFO,
												"Unexpected number of marks: " + subjectParts.length);
									}

									// marks
									String[] marksParts = bdsY1R16SubjectRegexPatternMatcher.group(2).trim().split("\\s+");

									if (marksParts.length == 12) {
										theoryThPlusIntVivaMaxMark = marksParts[0];
										theoryInt = marksParts[1];
										theoryTh = marksParts[2];
										theoryViva = marksParts[3];
										theoryThPlusIntVivaSecMark = marksParts[4];
										practicalPlusIntMaxMark = marksParts[5];
										practicalInt = marksParts[6];
										practicalPractical = marksParts[7];
										practicalVivaPR = marksParts[8];
										practicalTotalSecMarks = marksParts[9];
										thPlusPracticalMaxMark = marksParts[10];
										thPlusPracticalSecMark = marksParts[11];

									}

									else if (marksParts.length == 7) {
										practicalPlusIntMaxMark = marksParts[0];
										practicalInt = marksParts[1];
										practicalPractical = marksParts[2];
										practicalVivaPR = marksParts[3];
										practicalTotalSecMarks = marksParts[4];
										thPlusPracticalMaxMark = marksParts[5];
										thPlusPracticalSecMark = marksParts[6];

									}

									else {
										System.out.println("Unexpected number of marks: " + marksParts.length);
									}

									System.out.println("==============");

									System.out.println("Subject: " + subject);

									System.out.println("TH + int Viva Max Marks: " + theoryThPlusIntVivaMaxMark);
									System.out.println("Theory Int Marks: " + theoryInt);
									System.out.println("Theory TH Marks: " + theoryTh);
									System.out.println("Theory Viva Marks: " + theoryViva);
									System.out.println("TH + int Viva Sec Marks: " + theoryThPlusIntVivaSecMark);
									System.out.println("Practical + int Max Marks: " + practicalPlusIntMaxMark);
									System.out.println("Practical + int Marks: " + practicalInt);
									System.out.println("Practical Practical: " + practicalPractical);
									System.out.println("Practical Viva PR: " + practicalVivaPR);
									System.out.println("Practical + Viva Sec. Marks: " + practicalTotalSecMarks);
									System.out.println("Theory + Practical Max Marks: " + thPlusPracticalMaxMark);
									System.out.println("Theory + Practical Sec. Marks: " + thPlusPracticalSecMark);
									System.out.println("Result: " + status);
									System.out.println("==============");

									testCaseScenario.log(Status.INFO, "Subject: " + subject);
									testCaseScenario.log(Status.INFO,
											"theoryThPlusIntVivaMaxMark: " + theoryThPlusIntVivaMaxMark);
									testCaseScenario.log(Status.INFO, "theoryInt: " + theoryInt);
									testCaseScenario.log(Status.INFO, "theoryTh: " + theoryTh);
									testCaseScenario.log(Status.INFO, "theoryViva: " + theoryViva);
									testCaseScenario.log(Status.INFO,
											"theoryThPlusIntVivaSecMark: " + theoryThPlusIntVivaSecMark);
									testCaseScenario.log(Status.INFO,
											"practicalPlusIntMaxMark: " + practicalPlusIntMaxMark);
									testCaseScenario.log(Status.INFO, "practicalInt: " + practicalInt);
									testCaseScenario.log(Status.INFO, "practicalPractical: " + practicalPractical);
									testCaseScenario.log(Status.INFO, "practicalVivaPR: " + practicalVivaPR);
									testCaseScenario.log(Status.INFO,
											"practicalTotalSecMarks: " + practicalTotalSecMarks);
									testCaseScenario.log(Status.INFO,
											"thPlusPracticalMaxMark: " + thPlusPracticalMaxMark);
									testCaseScenario.log(Status.INFO,
											"thPlusPracticalSecMark: " + thPlusPracticalSecMark);
							

									if ((status.trim().equals("Pass") || status.trim().equals("Fail")
											|| status.trim().equals("AP"))
											&& subject.replaceAll("\\s+", "")
													.equals(subjectToFind.replaceAll("\\s+", ""))) {

										try {

											PageValidation.nonValidateMarks(Regno, "Theory Int sec. marks", subject,
													subjectToFind, theoryInt, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryTh, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory TH sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryViva, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory Viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double theoryIntMark = 0;
											double theoryThMark = 0;
											double theoryVivaMark = 0;
											double finalTheoryMark;

											if (invalidValues.contains(theoryInt)) {
												theoryIntMark = 0.0;

											} else if (!invalidValues.contains(theoryInt)) {
												theoryIntMark = Double.parseDouble(theoryInt);
											}

											if (invalidValues.contains(theoryTh)) {
												theoryThMark = 0.0;

											} else if (!invalidValues.contains(theoryTh)) {
												theoryThMark = Double.parseDouble(theoryTh);
											}

											if (invalidValues.contains(theoryViva)) {
												theoryVivaMark = 0.0;

											} else if (!invalidValues.contains(theoryViva)) {
												theoryVivaMark = Double.parseDouble(theoryViva);
											}

											finalTheoryMark = theoryIntMark + theoryThMark + theoryVivaMark;

											if (finalTheoryMark == Double.parseDouble(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",
														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											} else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.FAIL,
														"Please check the following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											}
										}

										try {
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryThPlusIntVivaSecMark,
													theoryThPlusIntVivaMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory + int + via Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject + " of theory sec mark is: "
															+ theoryThPlusIntVivaSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Practical Int sec. marks", subject,
													subjectToFind, practicalInt, practicalPlusIntMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Practical Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt);

										}

										try {
											PageValidation.nonValidateMarks(Regno, "Pratical pratical sec. marks",
													subject, subjectToFind, practicalPractical, practicalPlusIntMaxMark,
													0.0, testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													"Pratical pratical sec. marks validation for the subject " + subject
															+ " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical pratical sec marks is: "
															+ practicalPractical,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject
													+ " Pratical pratical sec marks is: " + practicalPractical);

										}
										try {

											PageValidation.nonValidateMarks(Regno, "Pratical viva sec. marks", subject,
													subjectToFind, practicalVivaPR, practicalPlusIntMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical viva sec. marks is: "
															+ practicalVivaPR,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " Pratical viva sec. marks is: "
													+ practicalVivaPR);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double praticalIntMark = 0;
											double practicalPracticalMark = 0;
											double praticalVivaMark = 0;
											double finalPraticalMark;

											if (invalidValues.contains(practicalInt)) {
												praticalIntMark = 0.0;

											} else if (!invalidValues.contains(practicalInt)) {
												praticalIntMark = Double.parseDouble(practicalInt);
											}

											if (invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = 0.0;

											} else if (!invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = Double.parseDouble(practicalPractical);
											}

											if (invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = 0.0;

											} else if (!invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = Double.parseDouble(practicalVivaPR);
											}

											finalPraticalMark = praticalIntMark + practicalPracticalMark
													+ praticalVivaMark;

											if (finalPraticalMark == Double.parseDouble(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",

														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											} else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Please check the following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"Please check the following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											}
										}

										try {

											PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal, practicalTotalSecMarks,
													practicalPlusIntMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of pravtical sec mark is: " + practicalTotalSecMarks,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, thPlusPracticalSecMark,
													thPlusPracticalMaxMark, 0.50, testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Grand Total Sec Marks validation for subject "
															+ subjectToFind + " test case has started");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks" + thPlusPracticalSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

									} // if bracket
								} // try
								catch (Exception e) {
									testCaseScenario.log(Status.FAIL,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has not matched");
								}
							} // while

						} // try
						catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.FAIL,
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case has fail to started running",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
											.build());
							testCaseScenario.log(Status.FAIL, e.getMessage(), MediaEntityBuilder
									.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println(text);
						}
					} // ELSE IF_BDS_Year2

					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BDS"))
							&& (semester.trim().equalsIgnoreCase("Year 3")) && (regulation.trim().contains("2016"))) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
											+ " of the  following " + Regno + " Test case");
							System.out.println(text);

							Pattern bdsY3R16SubjectRegexPattern = Pattern.compile(
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
									Pattern.DOTALL | Pattern.MULTILINE);

							Matcher bdsY3R16SubjectRegexPatternMatcher = bdsY3R16SubjectRegexPattern.matcher(text);

							while (bdsY3R16SubjectRegexPatternMatcher.find()) {

								try {
									testCaseScenario.log(Status.PASS,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has matched");

									System.out.println("==============");
									subject = bdsY3R16SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

									System.out.println("bdsSubject:" + subject);

									testCaseScenario.log(Status.INFO, "Subject: " + subject);
//						
									String theoryThPlusIntVivaMaxMark = bdsY3R16SubjectRegexPatternMatcher.group(2);

									String theoryInt = bdsY3R16SubjectRegexPatternMatcher.group(3);
									String theoryTh = bdsY3R16SubjectRegexPatternMatcher.group(4);
									String theoryViva = bdsY3R16SubjectRegexPatternMatcher.group(5);
									String theoryThPlusIntVivaSecMark = bdsY3R16SubjectRegexPatternMatcher.group(6);
									String practicalPlusIntMaxMark = bdsY3R16SubjectRegexPatternMatcher.group(7);

									String practicalInt = bdsY3R16SubjectRegexPatternMatcher.group(8);
									String practicalPractical = bdsY3R16SubjectRegexPatternMatcher.group(9);
									String practicalVivaPR = bdsY3R16SubjectRegexPatternMatcher.group(10);
									String practicalTotalSecMarks = bdsY3R16SubjectRegexPatternMatcher.group(11);

									String thPlusPracticalMaxMark = bdsY3R16SubjectRegexPatternMatcher.group(12);
									String thPlusPracticalSecMark = bdsY3R16SubjectRegexPatternMatcher.group(13);
									status = bdsY3R16SubjectRegexPatternMatcher.group(14);

									System.out.println("==============");

									System.out.println("Subject: " + subject);

									System.out.println("TH + int Viva Max Marks: " + theoryThPlusIntVivaMaxMark);
									System.out.println("Theory Int Marks: " + theoryInt);
									System.out.println("Theory TH Marks: " + theoryTh);
									System.out.println("Theory Viva Marks: " + theoryViva);
									System.out.println("TH + int Viva Sec Marks: " + theoryThPlusIntVivaSecMark);
									System.out.println("Practical + int Max Marks: " + practicalPlusIntMaxMark);
									System.out.println("Practical + int Marks: " + practicalInt);
									System.out.println("Practical Practical: " + practicalPractical);
									System.out.println("Practical Viva PR: " + practicalVivaPR);
									System.out.println("Practical + Viva Sec. Marks: " + practicalTotalSecMarks);
									System.out.println("Theory + Practical Max Marks: " + thPlusPracticalMaxMark);
									System.out.println("Theory + Practical Sec. Marks: " + thPlusPracticalSecMark);
									System.out.println("Result: " + status);
									System.out.println("==============");
									if ((status.trim().equals("Pass") || status.trim().equals("Fail")
											|| status.trim().equals("AP"))
											&& subject.replaceAll("\\s+", "")
													.equals(subjectToFind.replaceAll("\\s+", ""))) {

										try {

											PageValidation.nonValidateMarks(Regno, "Theory Int sec. marks", subject,
													subjectToFind, theoryInt, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryTh, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory TH sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryViva, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory Viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double theoryIntMark = 0;
											double theoryThMark = 0;
											double theoryVivaMark = 0;
											double finalTheoryMark;

											if (invalidValues.contains(theoryInt)) {
												theoryIntMark = 0.0;

											} else if (!invalidValues.contains(theoryInt)) {
												theoryIntMark = Double.parseDouble(theoryInt);
											}

											if (invalidValues.contains(theoryTh)) {
												theoryThMark = 0.0;

											} else if (!invalidValues.contains(theoryTh)) {
												theoryThMark = Double.parseDouble(theoryTh);
											}

											if (invalidValues.contains(theoryViva)) {
												theoryVivaMark = 0.0;

											} else if (!invalidValues.contains(theoryViva)) {
												theoryVivaMark = Double.parseDouble(theoryViva);
											}

											finalTheoryMark = theoryIntMark + theoryThMark + theoryVivaMark;

											if (finalTheoryMark == Double.parseDouble(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",
														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											} else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.FAIL,
														"Please check the following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											}
										}

										try {
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryThPlusIntVivaSecMark,
													theoryThPlusIntVivaMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory + int + via Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject + " of theory sec mark is: "
															+ theoryThPlusIntVivaSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Practical Int sec. marks", subject,
													subjectToFind, practicalInt, practicalPlusIntMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Practical Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt);

										}

										try {
											PageValidation.nonValidateMarks(Regno, "Pratical pratical sec. marks",
													subject, subjectToFind, practicalPractical, practicalPlusIntMaxMark,
													0.0, testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													"Pratical pratical sec. marks validation for the subject " + subject
															+ " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical pratical sec marks is: "
															+ practicalPractical,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject
													+ " Pratical pratical sec marks is: " + practicalPractical);

										}
										try {

											PageValidation.nonValidateMarks(Regno, "Pratical viva sec. marks", subject,
													subjectToFind, practicalVivaPR, practicalPlusIntMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical viva sec. marks is: "
															+ practicalVivaPR,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " Pratical viva sec. marks is: "
													+ practicalVivaPR);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double praticalIntMark = 0;
											double practicalPracticalMark = 0;
											double praticalVivaMark = 0;
											double finalPraticalMark;

											if (invalidValues.contains(practicalInt)) {
												praticalIntMark = 0.0;

											} else if (!invalidValues.contains(practicalInt)) {
												praticalIntMark = Double.parseDouble(practicalInt);
											}

											if (invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = 0.0;

											} else if (!invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = Double.parseDouble(practicalPractical);
											}

											if (invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = 0.0;

											} else if (!invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = Double.parseDouble(practicalVivaPR);
											}

											finalPraticalMark = praticalIntMark + practicalPracticalMark
													+ praticalVivaMark;

											if (finalPraticalMark == Double.parseDouble(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",

														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											} else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Please check the following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"Please check the following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											}
										}

										try {

											PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal, practicalTotalSecMarks,
													practicalPlusIntMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of pravtical sec mark is: " + practicalTotalSecMarks,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, thPlusPracticalSecMark,
													thPlusPracticalMaxMark, 0.50, testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Grand Total Sec Marks validation for subject "
															+ subjectToFind + " test case has started");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks" + thPlusPracticalSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

									} // if bracket
								} // try
								catch (Exception e) {
									testCaseScenario.log(Status.FAIL,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has not matched");
								}
							} // while

						} // try
						catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.FAIL,
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case has fail to started running",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
											.build());
							testCaseScenario.log(Status.FAIL, e.getMessage(), MediaEntityBuilder
									.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println(text);
						}
					} // ELSE IF_BDS_Year3
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BDS"))
							&& (semester.trim().equalsIgnoreCase("Year 4")) && (regulation.trim().contains("2016"))) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
											+ " of the  following " + Regno + " Test case");
							System.out.println(text);

							Pattern bdsY4R16SubjectRegexPattern = Pattern.compile(
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
									Pattern.DOTALL | Pattern.MULTILINE);

							Matcher bdsY4R16SubjectRegexPatternMatcher = bdsY4R16SubjectRegexPattern.matcher(text);

							while (bdsY4R16SubjectRegexPatternMatcher.find()) {

								try {
									testCaseScenario.log(Status.PASS,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has matched");

									System.out.println("==============");
									subject = bdsY4R16SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

									System.out.println("bdsSubject:" + subject);

									testCaseScenario.log(Status.INFO, "Subject: " + subject);
//						
									String theoryThPlusIntVivaMaxMark = bdsY4R16SubjectRegexPatternMatcher.group(2);

									String theoryInt = bdsY4R16SubjectRegexPatternMatcher.group(3);
									String theoryTh = bdsY4R16SubjectRegexPatternMatcher.group(4);
									String theoryViva = bdsY4R16SubjectRegexPatternMatcher.group(5);
									String theoryThPlusIntVivaSecMark = bdsY4R16SubjectRegexPatternMatcher.group(6);
									String practicalPlusIntMaxMark = bdsY4R16SubjectRegexPatternMatcher.group(7);

									String practicalInt = bdsY4R16SubjectRegexPatternMatcher.group(8);
									String practicalPractical = bdsY4R16SubjectRegexPatternMatcher.group(9);
									String practicalVivaPR = bdsY4R16SubjectRegexPatternMatcher.group(10);
									String practicalTotalSecMarks = bdsY4R16SubjectRegexPatternMatcher.group(11);

									String thPlusPracticalMaxMark = bdsY4R16SubjectRegexPatternMatcher.group(12);
									String thPlusPracticalSecMark = bdsY4R16SubjectRegexPatternMatcher.group(13);
									status = bdsY4R16SubjectRegexPatternMatcher.group(14);

									System.out.println("==============");

									System.out.println("Subject: " + subject);

									System.out.println("TH + int Viva Max Marks: " + theoryThPlusIntVivaMaxMark);
									System.out.println("Theory Int Marks: " + theoryInt);
									System.out.println("Theory TH Marks: " + theoryTh);
									System.out.println("Theory Viva Marks: " + theoryViva);
									System.out.println("TH + int Viva Sec Marks: " + theoryThPlusIntVivaSecMark);
									System.out.println("Practical + int Max Marks: " + practicalPlusIntMaxMark);
									System.out.println("Practical + int Marks: " + practicalInt);
									System.out.println("Practical Practical: " + practicalPractical);
									System.out.println("Practical Viva PR: " + practicalVivaPR);
									System.out.println("Practical + Viva Sec. Marks: " + practicalTotalSecMarks);
									System.out.println("Theory + Practical Max Marks: " + thPlusPracticalMaxMark);
									System.out.println("Theory + Practical Sec. Marks: " + thPlusPracticalSecMark);
									System.out.println("Result: " + status);
									System.out.println("==============");
									if ((status.trim().equals("Pass") || status.trim().equals("Fail")
											|| status.trim().equals("AP"))
											&& subject.replaceAll("\\s+", "")
													.equals(subjectToFind.replaceAll("\\s+", ""))) {

										try {

											PageValidation.nonValidateMarks(Regno, "Theory Int sec. marks", subject,
													subjectToFind, theoryInt, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Int sec marks is: " + theoryInt);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryTh, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory TH sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory TH sec marks is: " + theoryTh);

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Therory TH sec. marks", subject,
													subjectToFind, theoryViva, theoryThPlusIntVivaMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Therory Viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Therory Viva sec marks is: " + theoryViva);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double theoryIntMark = 0;
											double theoryThMark = 0;
											double theoryVivaMark = 0;
											double finalTheoryMark;

											if (invalidValues.contains(theoryInt)) {
												theoryIntMark = 0.0;

											} else if (!invalidValues.contains(theoryInt)) {
												theoryIntMark = Double.parseDouble(theoryInt);
											}

											if (invalidValues.contains(theoryTh)) {
												theoryThMark = 0.0;

											} else if (!invalidValues.contains(theoryTh)) {
												theoryThMark = Double.parseDouble(theoryTh);
											}

											if (invalidValues.contains(theoryViva)) {
												theoryVivaMark = 0.0;

											} else if (!invalidValues.contains(theoryViva)) {
												theoryVivaMark = Double.parseDouble(theoryViva);
											}

											finalTheoryMark = theoryIntMark + theoryThMark + theoryVivaMark;

											if (finalTheoryMark == Double.parseDouble(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of theory INT " + theoryIntMark
														+ " TH " + theoryThMark + " VIVA  " + theoryVivaMark + "= "
														+ Double.parseDouble(theoryThPlusIntVivaSecMark) + " and pdf "
														+ finalTheoryMark + " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of theory INT " + theoryIntMark + " TH "
																+ theoryThMark + " VIVA  " + theoryVivaMark + "= "
																+ Double.parseDouble(theoryThPlusIntVivaSecMark)
																+ " and pdf " + finalTheoryMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",
														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(theoryThPlusIntVivaSecMark)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											} else {
												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Theory TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Theory INT+TH+VIVA  is"
														+ theoryThPlusIntVivaSecMark);
												testCaseScenario1.log(Status.FAIL,
														"Please check the following " + Regno
																+ " registration number Theory INT+TH+VIVA  is"
																+ theoryThPlusIntVivaSecMark);

											}
										}

										try {
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryThPlusIntVivaSecMark,
													theoryThPlusIntVivaMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Theory + int + via Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject + " of theory sec mark is: "
															+ theoryThPlusIntVivaSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.nonValidateMarks(Regno, "Practical Int sec. marks", subject,
													subjectToFind, practicalInt, practicalPlusIntMaxMark, 0.0,
													testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Practical Int sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println(
													"The following Register number " + Regno + " for the subject "
															+ subject + " Practical Int sec marks is: " + practicalInt);

										}

										try {
											PageValidation.nonValidateMarks(Regno, "Pratical pratical sec. marks",
													subject, subjectToFind, practicalPractical, practicalPlusIntMaxMark,
													0.0, testCaseName);

										}

										catch (Exception e) {

											ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													"Pratical pratical sec. marks validation for the subject " + subject
															+ " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical pratical sec marks is: "
															+ practicalPractical,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject
													+ " Pratical pratical sec marks is: " + practicalPractical);

										}
										try {

											PageValidation.nonValidateMarks(Regno, "Pratical viva sec. marks", subject,
													subjectToFind, practicalVivaPR, practicalPlusIntMaxMark, 0.0,
													testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical viva sec. marks validation for the subject "
															+ subject + " Test case has started running");

											testCaseScenario1.log(Status.FAIL,
													"The following Register number " + Regno + " for the subject "
															+ subject + " Pratical viva sec. marks is: "
															+ practicalVivaPR,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

											System.out.println("The following Register number " + Regno
													+ " for the subject " + subject + " Pratical viva sec. marks is: "
													+ practicalVivaPR);

										}

										try {

											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											double praticalIntMark = 0;
											double practicalPracticalMark = 0;
											double praticalVivaMark = 0;
											double finalPraticalMark;

											if (invalidValues.contains(practicalInt)) {
												praticalIntMark = 0.0;

											} else if (!invalidValues.contains(practicalInt)) {
												praticalIntMark = Double.parseDouble(practicalInt);
											}

											if (invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = 0.0;

											} else if (!invalidValues.contains(practicalPractical)) {
												practicalPracticalMark = Double.parseDouble(practicalPractical);
											}

											if (invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = 0.0;

											} else if (!invalidValues.contains(practicalVivaPR)) {
												praticalVivaMark = Double.parseDouble(practicalVivaPR);
											}

											finalPraticalMark = praticalIntMark + practicalPracticalMark
													+ praticalVivaMark;

											if (finalPraticalMark == Double.parseDouble(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are  same mark");
												testCaseScenario1.log(Status.PASS,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are same mark");

											}

											else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Both addtion of pratical INT " + praticalIntMark
														+ " TH " + practicalPracticalMark + " VIVA  " + praticalVivaMark
														+ "= " + Double.parseDouble(practicalTotalSecMarks)
														+ " and pdf " + finalPraticalMark
														+ " value for the following Register " + Regno
														+ " number data are not same mark");
												testCaseScenario1.log(Status.FAIL,
														"Both addtion of pratical INT " + praticalIntMark + " TH "
																+ practicalPracticalMark + " VIVA  " + praticalVivaMark
																+ "= " + Double.parseDouble(practicalTotalSecMarks)
																+ " and pdf " + finalPraticalMark
																+ " value for the following Register " + Regno
																+ " number data are not same mark",

														MediaEntityBuilder.createScreenCaptureFromPath(
																BasicFunctions.capture(driver)).build());

											}

										} catch (Exception e) {
											Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---",
													"NE (AT)");

											if (invalidValues.contains(practicalTotalSecMarks)) {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");

												System.out.println("The following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"The following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											} else {

												ExtentTest testCaseScenario1 = testCaseName.createNode(
														" Pratical TH + int + Viva Sec Marks for the Subject " + subject
																+ " Validation Test case has started running");
												System.out.println("Please check the following " + Regno
														+ " registration number Pratical INT+TH+VIVA  is"
														+ practicalTotalSecMarks);
												testCaseScenario1.log(Status.INFO,
														"Please check the following " + Regno
																+ " registration number Pratical INT+TH+VIVA  is"
																+ practicalTotalSecMarks);

											}
										}

										try {

											PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal, practicalTotalSecMarks,
													practicalPlusIntMaxMark, 0.50, testCaseName);

										} catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical Sec. Marks " + subject
															+ " Test case has started running");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ " for the Subject " + subject
															+ " of pravtical sec mark is: " + practicalTotalSecMarks,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

										try {

											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, thPlusPracticalSecMark,
													thPlusPracticalMaxMark, 0.50, testCaseName);
										}

										catch (Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Grand Total Sec Marks validation for subject "
															+ subjectToFind + " test case has started");
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks" + thPlusPracticalSecMark,
													MediaEntityBuilder
															.createScreenCaptureFromPath(BasicFunctions.capture(driver))
															.build());

										}

									} // if bracket
								} // try
								catch (Exception e) {
									testCaseScenario.log(Status.FAIL,
											"Subject Pattern validation for the course name "
													+ courseNameRegexPatternMatcher.group() + " of the  following "
													+ Regno + " has not matched");
								}
							} // while

						} // try
						catch (Exception e) {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.FAIL,
									"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
											+ " of the  following " + Regno + " Test case has fail to started running",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
											.build());
							testCaseScenario.log(Status.FAIL, e.getMessage(), MediaEntityBuilder
									.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println(text);
						}
					} // ELSE IF_BDS_Year4

					if (page == totalPages) {
						break;
					}
				} // for
			} // try
			catch (Exception e) {
				e.printStackTrace();
				testCaseName.log(Status.FAIL, e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
		} // if
		else {
			System.out.println("No PDF file found.");
		}
	}// method
}
