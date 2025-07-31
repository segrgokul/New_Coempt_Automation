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

public class KnrReportCard_MD_Homeo_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	

	public void process_MD_Homeo_PatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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
				System.out.println(text);
					System.out.println("Page " + page + ":");
					System.out.println("---------------------------------------------------");

					String courseNameRegex = "(?i)M\\.?\\s*D\\.?\\s*HOMOEOPATHY\\.?\\s*";
		
					Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
					Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

					if (courseNameRegexPatternMatcher.find()) {
						
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validastion for the following " + Regno + " Test case has started running");
						System.out.println("Mathch Found: " + courseNameRegexPatternMatcher.group()); // Prints "MDS"
						
						testCaseScenario.log(Status.PASS, "MathchFound: " + courseNameRegexPatternMatcher.group());


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
					
					if (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.HOMOEOPATHY")&&(semester.trim().equalsIgnoreCase("Part-I") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							System.out.println(text);
				
							Pattern mdHomoeopathyP1R16SubjectRegexPattern = Pattern.compile(
								    "(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|^\\s*Pass\\s*$).*\\n)*" + // Skip specific headers
								    "([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" + // Subject should not start with 'Pass'
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + 
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
								    "((?:\\d+|NA|AB|NE|NR|NE(?:\\s*\\(AT\\))?|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
								    "(?:-+\\s*)?" + 
								    "(Pass|Fail|AP)"
								);

			
					Matcher mdHomoeopathyP1R16SubjectRegexPatternMatcher = mdHomoeopathyP1R16SubjectRegexPattern.matcher(text);		
		
					while(mdHomoeopathyP1R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						System.out.println("==============");

						subject = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(1)
					              .replaceAll("\\s*\\n\\s*", " ")  // replaces newline and surrounding spaces with single space
					              .trim();       
						String theoryMaxMarks =mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(2);
						
	
						
						String theorySecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	praticalMaxMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	grandTotalSecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(8);
			
						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

							System.out.println("Practical Sec Marks: " + praticalSecMarks);
						// Grand Total
						System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
						System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

						// Status
						System.out.println("Status: " + status);
						testCaseScenario.log(Status.INFO, "Subject: " + subject);
						testCaseScenario.log(Status.INFO, "Theory Max Marks: " + theoryMaxMarks);
						testCaseScenario.log(Status.INFO, "Theory Sec Marks: " + theorySecMarks);
						testCaseScenario.log(Status.INFO, "Practical Max Marks: " + praticalMaxMarks);
						testCaseScenario.log(Status.INFO, "Practical Sec Marks: " + praticalSecMarks);
						testCaseScenario.log(Status.INFO, "Grand Total Max Marks: " + grandTotalMaxMarks);
						testCaseScenario.log(Status.INFO, "Grand Total Sec Marks: " + grandTotalSecMarks);
						testCaseScenario.log(Status.INFO, "Status: " + status);
						
						if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
				
							try {
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
						     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theorySecMarks, theoryMaxMarks, 0.50, testCaseName);		


															}
							catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory Sec. Marks "
								+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
											
										}
							

								try {
								if(theorySecMarks.trim().equals("AB") && !praticalSecMarks.trim().equals("NE (AT)") ) {
									
									ExtentTest testCaseScenario1 = testCaseName
											.createNode("Pratical plus viva Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ " of theory internal sec mark is: "+ theorySecMarks +" Pratical plus viva Sec is:" + praticalSecMarks
													,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									System.out.println("\n Please check The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ "of theory internal sec mark "+ theorySecMarks+" Pratical plus viva Sec is :" + praticalSecMarks);

								}else {
									
									ExtentTest testCaseScenario1 = testCaseName
											.createNode("Pratical Univ Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.PASS,
											"The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ " of theory internal sec mark is: "+ theorySecMarks+" Pratical plus viva Sec mark is : " + praticalSecMarks);

									System.out.println(	"The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ "of theory internal sec mark "+ theorySecMarks+" Pratical plus viva Sec mark is:" + praticalSecMarks);
									
									try {
										
										
										PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal,subject,subjectToFind,status,
										grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		
								
									
					
								}
									catch(Exception e) {
										  ExtentTest testCaseScenario2 = testCaseScenario.createNode(
												  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario2.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Pratical Total Sec. Marks: " + praticalSecMarks);							
									}
									

								}//else
								}//try
								catch(Exception e) {
									 ExtentTest testCaseScenario1 = testCaseScenario.createNode(
											  " Pratical Univ Sec validation for subject " + subjectToFind + " test case has started");
									
										testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "+ theorySecMarks +" Pratical plus viva Sec is:" + praticalSecMarks
														,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									  
									  
								}	
								
									
						
								

								 try {
								    	

								    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks"  + grandTotalSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																	
									}
						
						} // if bracket

					}//try
				catch(Exception e){
					testCaseScenario.log(Status.FAIL, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has not matched");
}
				}//while
				
			
				
			}//try
				catch (Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

					testCaseScenario.log(Status.FAIL, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has fail to started running" 	,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					testCaseScenario.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					System.out.println(text);
				}
		}//IF_HEMEO_Part-1
				
					else if (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.HOMOEOPATHY")&&(semester.trim().equalsIgnoreCase("Part-II") ) &&(regulation.trim().contains("2016")) ) {
						
						Pattern mdHomoeopathyP1R16SubjectRegexPattern = Pattern.compile(
							    "(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|" +
							    "Theory Practical Grand Total|^\\s*Pass\\s*$|^\\s*Already\\s*$).*\\n)*" +  // Skip known headers or result rows
							    "(?!Pass\\b|Already\\b)" +                                                // Don't start subject with "Pass" or "Already"
							    "([A-Z][A-Za-z &'()\\-,]+(?:\\n[A-Z][A-Za-z &'()\\-,]+)*)\\s+" +           // Capture subject (possibly multiline)
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 1st mark
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 2nd mark
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 3rd mark
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 4th mark
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 5th mark
							    "((?:\\d+|NA|AB|NE|NR|NE\\s*\\(AT\\)|---|AP)?(?:\\s*\\(F\\))?)\\s*" +      // 6th mark
							    "(?:-+\\s*)?" +
							    "(Pass|Fail|AP)"                                                           // Final result
							);


							try {
								ExtentTest testCaseScenario = testCaseName.createNode(
										"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
								System.out.println(text);
					

			
					Matcher mdHomoeopathyP1R16SubjectRegexPatternMatcher = mdHomoeopathyP1R16SubjectRegexPattern.matcher(text);		
		
					while(mdHomoeopathyP1R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						System.out.println("==============");

						subject = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(1)
					              .replaceAll("\\s*\\n\\s*", " ")  // replaces newline and surrounding spaces with single space
					              .trim();       
						String theoryMaxMarks =mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(2);
						
	
						
						String theorySecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	praticalMaxMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	grandTotalSecMarks = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = mdHomoeopathyP1R16SubjectRegexPatternMatcher.group(8);
			
						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

							System.out.println("Practical Sec Marks: " + praticalSecMarks);
						// Grand Total
						System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
						System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

						// Status
						System.out.println("Status: " + status);
						testCaseScenario.log(Status.INFO, "Subject: " + subject);
						testCaseScenario.log(Status.INFO, "Theory Max Marks: " + theoryMaxMarks);
						testCaseScenario.log(Status.INFO, "Theory Sec Marks: " + theorySecMarks);
						testCaseScenario.log(Status.INFO, "Practical Max Marks: " + praticalMaxMarks);
						testCaseScenario.log(Status.INFO, "Practical Sec Marks: " + praticalSecMarks);
						testCaseScenario.log(Status.INFO, "Grand Total Max Marks: " + grandTotalMaxMarks);
						testCaseScenario.log(Status.INFO, "Grand Total Sec Marks: " + grandTotalSecMarks);
						testCaseScenario.log(Status.INFO, "Status: " + status);
						try {
							testCaseScenario.log(Status.PASS, "Excel Subject name and PDF Subject name validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has Started");

						
						if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
				
							try {
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
						     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theorySecMarks, theoryMaxMarks, 0.50, testCaseName);		


															}
							catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory Sec. Marks "
								+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
											
										}
							

								try {
								if(theorySecMarks.trim().equals("AB") && !praticalSecMarks.trim().equals("NE (AT)") ) {
									
									ExtentTest testCaseScenario1 = testCaseName
											.createNode("Pratical plus viva Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ " of theory internal sec mark is: "+ theorySecMarks +" Pratical plus viva Sec is:" + praticalSecMarks
													,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									System.out.println("\n Please check The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ "of theory internal sec mark "+ theorySecMarks+" Pratical plus viva Sec is :" + praticalSecMarks);

								}else {
									
									ExtentTest testCaseScenario1 = testCaseName
											.createNode("Pratical Univ Sec. Marks Validation for the Subject "
													+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.PASS,
											"The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ " of theory internal sec mark is: "+ theorySecMarks+" Pratical plus viva Sec mark is : " + praticalSecMarks);

									System.out.println(	"The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ "of theory internal sec mark "+ theorySecMarks+" Pratical plus viva Sec mark is:" + praticalSecMarks);
									
									try {
										
										
										PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal,subject,subjectToFind,status,
										grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		
								
									
					
								}
									catch(Exception e) {
										  ExtentTest testCaseScenario2 = testCaseScenario.createNode(
												  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario2.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Pratical Total Sec. Marks: " + praticalSecMarks);							
									}
									

								}//else
								}//try
								catch(Exception e) {
									 ExtentTest testCaseScenario1 = testCaseScenario.createNode(
											  " Pratical Univ Sec validation for subject " + subjectToFind + " test case has started");
									
										testCaseScenario.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ " for the Subject " + subject
														+ " of theory internal sec mark is: "+ theorySecMarks +" Pratical plus viva Sec is:" + praticalSecMarks
														,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									  
									  
								}	
								
									
						
								

								 try {
								    	

								    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		
									}
									
									catch(Exception e) {
										  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
												  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
										  testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno
															+ "Grand Total Sec Marks"  + grandTotalSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																	
									}
						
						} // if bracket
						}
						catch(Exception e){
						testCaseScenario.log(Status.FAIL, "Excel Subject name and PDF Subject name validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has not Started");
						}

					}//try
				catch(Exception e){
					testCaseScenario.log(Status.FAIL, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has not matched");
}
				}//while
				
			
				
			}//try
				catch (Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case");

					testCaseScenario.log(Status.FAIL, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group(1)+" of the  following " + Regno + " Test case has fail to started running" 	,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					testCaseScenario.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					System.out.println(text);
				}
		}//IF_UNANI_Year2
					
					if (page == totalPages) {
						break;
					}
				}//for
				}//try
			 catch (Exception e) {
				e.printStackTrace();
				testCaseName.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
		}//if
		else {
			System.out.println("No PDF file found.");
		}
	}//method		
}

