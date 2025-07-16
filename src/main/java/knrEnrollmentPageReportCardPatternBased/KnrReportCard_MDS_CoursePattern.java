package knrEnrollmentPageReportCardPatternBased;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import knrPageModules.KnrReportEnrollmentPageValidation;

public class KnrReportCard_MDS_CoursePattern  extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

	public void processMDSPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(MDS)\\s*";

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
		
					//checking course name along with Semester and regulation
				if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("MDS"))&&(semester.trim().equalsIgnoreCase("PART-I") ) &&(regulation.trim().contains("2018")) ) {
			
						
							try {
								ExtentTest testCaseScenario = testCaseName.createNode(
										"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

								testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has started running");

								
								
							
								String subjectRegex = "(?:Subject Name:|Subject :)\\s*([A-Z ,&()\\-/]+(?:\\R\\s{2,}[A-Z ,&()\\-/]+)?)";

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
							
		
							
							Pattern mdsPart1Reg18MarksPattern = Pattern.compile(
								    "(\\d+)\\s*(?:\\(\\s*F\\s*\\))?\\s+(\\d+)\\s*(?:\\(\\s*F\\s*\\))?\\s+(Pass|Fail|AP)"
								);
							Matcher mdsPart1Reg18MarksMatcher = mdsPart1Reg18MarksPattern.matcher(text);
							
							if(mdsPart1Reg18MarksMatcher.find()) {
							
								
								
							String paper1Marks = mdsPart1Reg18MarksMatcher.group(1);


							String grandTotalMarks = mdsPart1Reg18MarksMatcher.group(2);

							status = mdsPart1Reg18MarksMatcher.group(3);
							
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
						    	

						    	PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
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
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
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
							else {
								  ExtentTest testCaseScenario1 = testCaseName.createNode(
										  " Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" test case has started");
								  testCaseScenario1.log(Status.FAIL," Validation for subject in excel " + subjectToFind + " and pdf subject " + subject +" are not equals");	
							
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
					}//MDS Part-1 IF
				else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("MDS"))&&(semester.trim().equalsIgnoreCase("PART-II") ) &&(regulation.trim().contains("2018")) ) {
				
					try {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

					
						String subjectRegex = "(?:Subject Name:|Subject :)\\s*([A-Z ,&()\\-/]+(?:\\R\\s{2,}[A-Z ,&()\\-/]+)?)";

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

							System.out.println("yes");
							Pattern mdsPart2Reg18MarksPattern = Pattern.compile("(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(NA|---|\\d+)(?:\\s*\\(\\s*F\\s*\\))?\\s+"
								    + "(Pass|Fail|AP)");


							Matcher mdsPart2Reg18MarksMatcher = mdsPart2Reg18MarksPattern.matcher(text);
							
							if (mdsPart2Reg18MarksMatcher.find()) {
									
									
									
									String mdsPart2Reg18MarksMaxMinRegex = "Max\\.Marks:\\s*(\\d+)\\s+Min\\.Marks:\\s*(\\d+)";

									// Pattern for additional Max.Marks and Min.Marks entries
									Pattern mdsPart2Reg18MarksMaxMinRegexPattern = Pattern.compile(mdsPart2Reg18MarksMaxMinRegex);
									Matcher mdsPart2Reg18MarksMaxMinRegexPatternMatcher = mdsPart2Reg18MarksMaxMinRegexPattern.matcher(text);


									String mdsPart2RegTotalMaxMark = null;
									String mdsPart2RegTotalMinMark = null;
									String mdsPart2Reg18PracticalMaxMark = null;
									String mdsPart2Reg18PracticalMinMark = null;

									String mdsPart2Reg18GrandTotalMaxMark = null;
									String mdsPart2Reg18GrandTotalMinMark = null;

									int paperCount = 1;
									while (mdsPart2Reg18MarksMaxMinRegexPatternMatcher.find()) {
										
										String paper1MaxMark="100";
										if (paperCount == 1) {
											mdsPart2RegTotalMaxMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(1);
											mdsPart2RegTotalMinMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(2);

											System.out.println("Mds Part 2 Reg 18 Total Max Marks: " + mdsPart2RegTotalMaxMark);
											System.out.println("Mds Part 2 Reg 18 Total Min Marks: " + mdsPart2RegTotalMinMark);
											System.out.println("-----------------");
										}

										else if (paperCount == 2) {
											mdsPart2Reg18PracticalMaxMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(1);
											mdsPart2Reg18PracticalMinMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(2);

											System.out.println("Paper2 Max Marks: " + mdsPart2Reg18PracticalMaxMark);
											System.out.println("Paper2 Min Marks: " + mdsPart2Reg18PracticalMinMark);
											System.out.println("-----------------");
										}

										else if (paperCount == 3) {
											mdsPart2Reg18GrandTotalMaxMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(1);
											mdsPart2Reg18GrandTotalMinMark = mdsPart2Reg18MarksMaxMinRegexPatternMatcher.group(2);

											System.out.println("Paper3 Max Marks: " + mdsPart2Reg18GrandTotalMaxMark);
											System.out.println("Paper3 Min Marks: " + mdsPart2Reg18GrandTotalMinMark);
											System.out.println("-----------------");
										}

									

										paperCount++;
									}
									
								String paper1MaxMark="100";
								String paper1Marks = mdsPart2Reg18MarksMatcher.group(1);
								String paper2Marks = mdsPart2Reg18MarksMatcher.group(2);
								String paper3Marks = mdsPart2Reg18MarksMatcher.group(3);
								String theoryTotalSecMarks = mdsPart2Reg18MarksMatcher.group(4);
								String practicalVivaSecMarks = mdsPart2Reg18MarksMatcher.group(5);
								String grandTotalMark = mdsPart2Reg18MarksMatcher.group(6);
								status = mdsPart2Reg18MarksMatcher.group(7);
								
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

								System.out.println(subjectToFind);
								
								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
							
									testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has started running");
		
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
								// Use the value
									
									
										   try {
										    	

										    	PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
														theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
														grandTotal, theoryTotalSecMarks, mdsPart2RegTotalMaxMark, 0.50, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													  "Theory Total Sec Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario1.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Theory Total Sec Marks"  + theoryTotalSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																			
										}
									
								 try {
									    	

									    	PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
														theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
														grandTotal, practicalVivaSecMarks, mdsPart2Reg18PracticalMaxMark, 0.50, testCaseName);		
									}
										
								catch(Exception e) {
											  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													  "Pratical Total Sec Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario1.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Pratical Total Sec Marks"  + practicalVivaSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																		
										}
									 
									 
									 try {
									    	

									    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
														theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
														grandTotal, grandTotalMark, mdsPart2Reg18GrandTotalMaxMark, 0.50, testCaseName);		
										}
										
										catch(Exception e) {
											  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
													  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
											  testCaseScenario1.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno
																+ "Grand Total Sec Marks"  + grandTotalMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																		
										}
						
								}//IF 
								
							}
					}
					catch (Exception e) {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

						testCaseScenario.log(Status.FAIL, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has fail to started running" 	,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						testCaseScenario.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println(text);
					}
							
							
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
}
