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

public class KnrReportCard_PG_Diploma_CoursePattern extends BasicFunctions {
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

	public void processPG_DiplomaPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
			Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, ExtentTest testCaseName, String subjectToFind) throws IOException {
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

				
					String courseNameRegex = "(?i)\\b(PG\\s+Diploma)\\b\\s*";


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
								+ " for the subject " + subjectToFind + " No match found",MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("No match found.");
					}
		
				if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("PG Diploma"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {

				try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

					testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has started running");

					
					
					String subjectRegex = "([A-Z ,&()\\-/]{5,})\\s*Subject\\s*:";

				Pattern subjectPattern = Pattern.compile(subjectRegex, Pattern.MULTILINE);
				Matcher subjectMatcher = subjectPattern.matcher(text);

				if (subjectMatcher.find()) {
					System.out.println("yes");
					System.out.println("Subject: " + subjectMatcher.group(1)); // Extracted subject nam jf
					subject = subjectMatcher.group(1);
					ExtentTest testCaseScenario1 = testCaseScenario.createNode(
							"Subject Pattern validation for the following " + Regno + " Test case has started running");
				
					testCaseScenario1.log(Status.PASS, "Subject name MathchFound: " + subjectMatcher.group(1));

				}
				else {
					ExtentTest testCaseScenario1 = testCaseScenario.createNode(
							"Subject Pattern validation for the following " + Regno + " Test case has started running");
					testCaseScenario1.log(Status.FAIL, "Subject name Not Found: " + subjectMatcher.group(1));
				}
				
				Pattern pgDiplomaY1R16Part3MarksPattern = Pattern.compile("(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
					    + "(Pass|Fail|AP)");


				Matcher pgDiplomaY1R16Part3MarksPatternMatcher = pgDiplomaY1R16Part3MarksPattern.matcher(text);

			
				if (pgDiplomaY1R16Part3MarksPatternMatcher.find()) {
					String pgDiplomaY1R16MaxMinRegex = "Max\\.Marks:\\s*(\\d+)\\s+Min\\.Marks:\\s*(\\d+)";

					// Pattern for additional Max.Marks and Min.Marks entries
					Pattern pgDiplomaY1R16MaxMinRegexPattern = Pattern.compile(pgDiplomaY1R16MaxMinRegex);
					Matcher pgDiplomaY1R16MaxMinRegexPatternMatcher = pgDiplomaY1R16MaxMinRegexPattern.matcher(text);

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
					while (pgDiplomaY1R16MaxMinRegexPatternMatcher.find()) {

						if (paperCount == 1) {
							paper1MaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							paper1MinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Paper1 Max Marks: " + paper1MaxMark);
							System.out.println("Paper1 Min Marks: " + paper1MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 2) {
							paper2MaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							paper2MinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Paper2 Max Marks: " + paper2MaxMark);
							System.out.println("Paper2 Min Marks: " + paper2MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 3) {
							paper3MaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							paper3MinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Paper3 Max Marks: " + paper3MaxMark);
							System.out.println("Paper3 Min Marks: " + paper3MinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 4) {
							TotalMaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							TotalMinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Total Max Marks: " + TotalMaxMark);
							System.out.println("Total Min Marks: " + TotalMinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 5) {
							practicalMaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							practicalMinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Practical Max Marks: " + practicalMaxMark);
							System.out.println("Practical Min Marks: " + practicalMinMark);
							System.out.println("-----------------");
						}

						else if (paperCount == 6) {
							pgGrandTotalMaxMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(1);
							pgGrandTotalMinMark = pgDiplomaY1R16MaxMinRegexPatternMatcher.group(2);

							System.out.println("Grand Total Max Marks: " + pgGrandTotalMaxMark);
							System.out.println("Grand Total Min Marks: " + pgGrandTotalMinMark);
							System.out.println("-----------------");
						}

						paperCount++;
					}
					
					
					
					Pattern marksPattern = Pattern.compile("(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
						    + "(Pass|Fail|AP)");


					Matcher marksMatcher = marksPattern.matcher(text);

				
					if (marksMatcher.find()) {

		
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
					
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {
					

									try {
									

									PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
												theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
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
										

										PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, paper2Marks, paper1MaxMark, 0.50, testCaseName);		
								
									
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  " Paper2 Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Paper2 sec. Marks: " + paper2Marks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());						
									}	
									
													
									try {
										

										PageValidation.validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, paper3Marks, paper1MaxMark, 0.50, testCaseName);		
								
									
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  " Paper3 Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Paper3 sec. Marks: " + paper2Marks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());							
									}
									
									   try {
									    	

									    	PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, theoryTotals, TotalMaxMark, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  "Theory Total Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Theory Total Sec Marks"  + theoryTotals,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																		
									}
								
							 try {
								    	

								    	PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, praticalTotals, practicalMaxMark, 0.50, testCaseName);		
								}
									
							catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks"  + praticalTotals,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																	
									}
								 
								 
								 try {
								    	

								    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, grandTotals, pgGrandTotalMaxMark, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks"  + grandTotals,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																	
									}
					
						
								}
								else {
									  ExtentTest testCaseScenario1 = testCaseName.createNode(
											  " Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" test case has started");
									  testCaseScenario1.log(Status.FAIL," Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" are not equals",MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());	
								
								}
							}
					}
		
				
				
				
			}
				catch (Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

					testCaseScenario.log(Status.FAIL, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has fail to started running" 	,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					testCaseScenario.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					System.out.println(text);
				}
		}//PG_Degree
				
				

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
}