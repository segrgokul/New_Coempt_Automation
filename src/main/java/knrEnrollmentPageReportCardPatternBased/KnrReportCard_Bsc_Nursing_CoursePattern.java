package knrEnrollmentPageReportCardPatternBased;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import knrPageModules.KnrReportEnrollmentPageValidation;

public class KnrReportCard_Bsc_Nursing_CoursePattern  extends BasicFunctions {

	String subject;
	String status;

	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();


public void process_Bsc_NursingPatternPdf(File latestFile, String Regno, String semester,
		String regulation, Object paper1, Object paper2, Object paper3, Object paper4, Object theoryExamTotal,
		Object practicalExamTotal, Object grandTotal, ExtentTest testCaseName, String subjectToFind)
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

				String courseNameRegex = "(?i)(B\\.\\s*Sc\\.\\s*Nursing)\\s*";

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

				
				if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "")
						.equalsIgnoreCase("B.Sc.Nursing")) && (semester.trim().equalsIgnoreCase("Year 2"))
						&& (regulation.trim().contains("2016"))) {
					try {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
										+ " of the  following " + Regno + " Test case");
						System.out.println(text);

						Pattern BscNursingY2R16SubjectRegexPattern = Pattern.compile(
								"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
										+ "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*" // Subject Name
										+ "(?:\\(([^)]+)\\))?\\s*" // Specialization (optional)

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
								Pattern.MULTILINE);

						Matcher BscNursingY2R16SubjectRegexPatternMatcher = BscNursingY2R16SubjectRegexPattern
								.matcher(text);

						while (BscNursingY2R16SubjectRegexPatternMatcher.find()) {

							try {
								testCaseScenario.log(Status.PASS,
										"Subject Pattern validation for the course name "
												+ courseNameRegexPatternMatcher.group() + " of the  following "
												+ Regno + " has matched");

								System.out.println("==============");
								subject = (BscNursingY2R16SubjectRegexPatternMatcher.group(2) == null)
										? BscNursingY2R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ")
												.trim()
										: (BscNursingY2R16SubjectRegexPatternMatcher.group(1) + " "
												+ BscNursingY2R16SubjectRegexPatternMatcher.group(2))
												.replaceAll("\\s+", " ").trim();

								String theoryInternalMaxMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(3);
								String theoryInternalSecMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(4);
								String theoryUnivMaxMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(5);
								String theoryUnivSecMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(6);
								String practicalInternalMaxMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(7);
								String practicalInternalSecMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(8);
								String practicalUnivMaxMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(9);
								String practicalUnivSecMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(10);
								String theoryPracticalMaxMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(11);
								String theoryPracticalSecMarks = BscNursingY2R16SubjectRegexPatternMatcher.group(12);
								status = BscNursingY2R16SubjectRegexPatternMatcher.group(13);

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

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP"))
										&& subject.replaceAll("\\s+", "")
												.equals(subjectToFind.replaceAll("\\s+", ""))) {

									try {

								
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryInternalSecMarks,
													theoryInternalMaxMarks, 0.50, testCaseName);
										
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												"Paper1 sec. Marks " + subject + " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "
														+ theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

					
											PageValidation.validateMarks(Regno, "Paper2 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryUnivSecMarks,
													theoryUnivMaxMarks, 0.50, testCaseName);
									

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Theory Univ Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory univ sec mark is: " + theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Paper3 Sec Marks", paper1, paper2,
												paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
												subjectToFind, status, grandTotal, practicalInternalSecMarks,
												practicalInternalMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Internal Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pratical internal sec mark is: "
														+ practicalInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
												paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
												subject, subjectToFind, status, grandTotal, practicalUnivSecMarks,
												practicalUnivMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pravtical sec mark is: " + practicalUnivSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

								
											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal,
													theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50,
													testCaseName);
									
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Grand Total Sec Marks validation for subject "
														+ subjectToFind + " test case has started");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Grand Total Sec Marks" + theoryPracticalSecMarks,
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
				} // IF_BSC_Nursing_Year2
				
				
				
				else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "")
						.equalsIgnoreCase("B.Sc.Nursing")) && (semester.trim().equalsIgnoreCase("Year 3"))
						&& (regulation.trim().contains("2016"))) {
					try {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
										+ " of the  following " + Regno + " Test case");
						System.out.println(text);

						Pattern BscNursingY3R16SubjectRegexPattern = Pattern.compile(
								"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
										+ "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*" // Subject Name
										+ "(?:\\(([^)]+)\\))?\\s*" // Specialization (optional)

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
								Pattern.MULTILINE);

						Matcher BscNursingY3R16SubjectRegexPatternMatcher = BscNursingY3R16SubjectRegexPattern
								.matcher(text);

						while (BscNursingY3R16SubjectRegexPatternMatcher.find()) {

							try {
								testCaseScenario.log(Status.PASS,
										"Subject Pattern validation for the course name "
												+ courseNameRegexPatternMatcher.group() + " of the  following "
												+ Regno + " has matched");

								System.out.println("==============");
								subject = (BscNursingY3R16SubjectRegexPatternMatcher.group(2) == null)
										? BscNursingY3R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ")
												.trim()
										: (BscNursingY3R16SubjectRegexPatternMatcher.group(1) + " "
												+ BscNursingY3R16SubjectRegexPatternMatcher.group(2))
												.replaceAll("\\s+", " ").trim();

								String theoryInternalMaxMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(3);
								String theoryInternalSecMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(4);
								String theoryUnivMaxMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(5);
								String theoryUnivSecMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(6);
								String practicalInternalMaxMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(7);
								String practicalInternalSecMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(8);
								String practicalUnivMaxMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(9);
								String practicalUnivSecMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(10);
								String theoryPracticalMaxMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(11);
								String theoryPracticalSecMarks = BscNursingY3R16SubjectRegexPatternMatcher.group(12);
								status = BscNursingY3R16SubjectRegexPatternMatcher.group(13);

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

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP"))
										&& subject.replaceAll("\\s+", "")
												.equals(subjectToFind.replaceAll("\\s+", ""))) {

									try {

								
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryInternalSecMarks,
													theoryInternalMaxMarks, 0.50, testCaseName);
										
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												"Paper1 sec. Marks " + subject + " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "
														+ theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

					
											PageValidation.validateMarks(Regno, "Paper2 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryUnivSecMarks,
													theoryUnivMaxMarks, 0.50, testCaseName);
									

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Theory Univ Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory univ sec mark is: " + theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Paper3 Sec Marks", paper1, paper2,
												paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
												subjectToFind, status, grandTotal, practicalInternalSecMarks,
												practicalInternalMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Internal Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pratical internal sec mark is: "
														+ practicalInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
												paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
												subject, subjectToFind, status, grandTotal, practicalUnivSecMarks,
												practicalUnivMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pravtical sec mark is: " + practicalUnivSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

								
											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal,
													theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50,
													testCaseName);
									
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Grand Total Sec Marks validation for subject "
														+ subjectToFind + " test case has started");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Grand Total Sec Marks" + theoryPracticalSecMarks,
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
				} // ELseIF_BscNuring_Year3
				else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "")
						.equalsIgnoreCase("B.Sc.Nursing")) && (semester.trim().equalsIgnoreCase("Year 4"))
						&& (regulation.trim().contains("2016"))) {
					try {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
										+ " of the  following " + Regno + " Test case");
						System.out.println(text);

						Pattern BscNursingY4R16SubjectRegexPattern = Pattern.compile(
								"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
										+ "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*" // Subject Name
										+ "(?:\\(([^)]+)\\))?\\s*" // Specialization (optional)

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
								Pattern.MULTILINE);

						Matcher BscNursingY4R16SubjectRegexPatternMatcher = BscNursingY4R16SubjectRegexPattern
								.matcher(text);

						while (BscNursingY4R16SubjectRegexPatternMatcher.find()) {

							try {
								testCaseScenario.log(Status.PASS,
										"Subject Pattern validation for the course name "
												+ courseNameRegexPatternMatcher.group() + " of the  following "
												+ Regno + " has matched");

								System.out.println("==============");
								subject = (BscNursingY4R16SubjectRegexPatternMatcher.group(2) == null)
										? BscNursingY4R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ")
												.trim()
										: (BscNursingY4R16SubjectRegexPatternMatcher.group(1) + " "
												+ BscNursingY4R16SubjectRegexPatternMatcher.group(2))
												.replaceAll("\\s+", " ").trim();

								String theoryInternalMaxMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(3);
								String theoryInternalSecMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(4);
								String theoryUnivMaxMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(5);
								String theoryUnivSecMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(6);
								String practicalInternalMaxMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(7);
								String practicalInternalSecMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(8);
								String practicalUnivMaxMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(9);
								String practicalUnivSecMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(10);
								String theoryPracticalMaxMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(11);
								String theoryPracticalSecMarks = BscNursingY4R16SubjectRegexPatternMatcher.group(12);
								status = BscNursingY4R16SubjectRegexPatternMatcher.group(13);

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

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP"))
										&& subject.replaceAll("\\s+", "")
												.equals(subjectToFind.replaceAll("\\s+", ""))) {

									try {

								
											PageValidation.validateMarks(Regno, "Paper1 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryInternalSecMarks,
													theoryInternalMaxMarks, 0.50, testCaseName);
										
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												"Paper1 sec. Marks " + subject + " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "
														+ theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

					
											PageValidation.validateMarks(Regno, "Paper2 Sec Marks", paper1, paper2,
													paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
													subjectToFind, status, grandTotal, theoryUnivSecMarks,
													theoryUnivMaxMarks, 0.50, testCaseName);
									

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Theory Univ Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory univ sec mark is: " + theoryInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Paper3 Sec Marks", paper1, paper2,
												paper3, paper4, theoryExamTotal, practicalExamTotal, subject,
												subjectToFind, status, grandTotal, practicalInternalSecMarks,
												practicalInternalMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Internal Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pratical internal sec mark is: "
														+ practicalInternalSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

										PageValidation.validateMarks(Regno, "Pratical Total Sec Marks", paper1,
												paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
												subject, subjectToFind, status, grandTotal, practicalUnivSecMarks,
												practicalUnivMaxMarks, 0.50, testCaseName);

									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Pratical Sec. Marks " + subject
														+ " Test case has started running");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of pravtical sec mark is: " + practicalUnivSecMarks,
												MediaEntityBuilder
														.createScreenCaptureFromPath(BasicFunctions.capture(driver))
														.build());

									}

									try {

								
											PageValidation.validateMarks(Regno, "Grand Total Sec Marks", paper1,
													paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
													subject, subjectToFind, status, grandTotal,
													theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50,
													testCaseName);
									
									} catch (Exception e) {
										ExtentTest testCaseScenario1 = testCaseScenario
												.createNode("Grand Total Sec Marks validation for subject "
														+ subjectToFind + " test case has started");
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Grand Total Sec Marks" + theoryPracticalSecMarks,
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
				} // ELSeIF_BscNuring_Year4
					
				
				
				
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
