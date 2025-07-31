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

public class KnrReportCard_MD_Unani_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	
	
	public void processMD_UnaniPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(?:M\\.D\\.\\s*UNANI)\\s+";

					
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

						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found",MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found");

						System.out.println("No match found.");
					}
				
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.UNANI"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case has started running");

							Pattern M_D_Unani_Y1_R16_Pattern = Pattern.compile(
								    "(?m)(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|" +
								    "Theory Practical Grand Total|Note:?.*|^\\d+).*\\n)*" +
								    "^\\s*((?:[A-Z ,&()\\-/]+(?:\\n\\s*)?)+?)\\s+" + // group(1): Subject line(s)
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "(?:-+\\s*)?" +
								    "(Pass|Fail|AP)$"
								);
					Matcher M_D_Unani_Y1_R16_PatternMatcher = M_D_Unani_Y1_R16_Pattern.matcher(text);		
						
				
					while(M_D_Unani_Y1_R16_PatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						
				
					//		System.out.println(text);
					

						subject = M_D_Unani_Y1_R16_PatternMatcher.group(1)  // your matched subject name
						         .replaceAll("\\s*\\n\\s*", " ")      // Replace line breaks and surrounding spaces with single space
						         .replaceAll("\\s{2,}", " ")          // Collapse multiple spaces
						         .replaceAll("\\(PAPER-\\s*([IVX]+)\\)", "(PAPER-$1)")  // Fix space in "(PAPER- III)"
						         .trim();
						System.out.println(subject);
						System.out.println(subjectToFind);
						
						System.out.println(subject.trim().equals(subjectToFind.trim()));
				

				String	theoryTotalMaxMarks = M_D_Unani_Y1_R16_PatternMatcher.group(2);
				String	theoryTotalSecMarks = M_D_Unani_Y1_R16_PatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y1_R16_PatternMatcher.group(3)
							: M_D_Unani_Y1_R16_PatternMatcher.group(3).replaceAll("[^0-9]", "");
				String	practicalVivaMaxMarks = M_D_Unani_Y1_R16_PatternMatcher.group(4);
				String	practicalVivaSecMarks = M_D_Unani_Y1_R16_PatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y1_R16_PatternMatcher.group(5)
							: M_D_Unani_Y1_R16_PatternMatcher.group(5).replaceAll("[^0-9]", "");

				String	theoryPracticalMaxMarks = M_D_Unani_Y1_R16_PatternMatcher.group(6);
				String	theoryPracticalSecMarks = M_D_Unani_Y1_R16_PatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y1_R16_PatternMatcher.group(7)
							: M_D_Unani_Y1_R16_PatternMatcher.group(7).replaceAll("[^0-9]", "");
					status = M_D_Unani_Y1_R16_PatternMatcher.group(8);

					System.out.println("Subject: " + subject);
					System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
					System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
					System.out.println("Practical Viva Max Marks: " + practicalVivaMaxMarks);
					System.out.println("Practical Viva Secured Marks: " + practicalVivaSecMarks);
					System.out.println("Theory plus Practical Max Marks: " + theoryPracticalMaxMarks);
					System.out.println("Theory plus Practical Secured Marks: " + theoryPracticalSecMarks);
					System.out.println("Status: " + status);

					System.out.println("---------------------------------------------------");
				
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {

						try {
		
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryTotalSecMarks, theoryTotalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theoryTotalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						try {
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalVivaSecMarks, practicalVivaMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Pratical plus viva Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ practicalVivaSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						
						 try {
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + theoryPracticalSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
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
			}//IF_UNANI_Year1
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.UNANI"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case has started running");

							Pattern M_D_Unani_Y2_R16_Pattern = Pattern.compile(
								    "(?m)(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|" +
								    "Theory Practical Grand Total|Note:?.*|^\\d+).*\\n)*" +
								    "^\\s*((?:[A-Z ,&()\\-/]+(?:\\n\\s*)?)+\\(\\s*PAPER\\s*-?\\s*[IVX]+\\s*\\))\\s*" + // group(1): entire subject including (PAPER-III)
								    "^?\\s*((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s+" +
								    "(?:-+\\s*)?" +
								    "(Pass|Fail|AP)$"
								);

					Matcher M_D_Unani_Y2_R16_PatternMatcher = M_D_Unani_Y2_R16_Pattern.matcher(text);		
						
				
					while(M_D_Unani_Y2_R16_PatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						
				
					//		System.out.println(text);
					

						subject = M_D_Unani_Y2_R16_PatternMatcher.group(1)  // your matched subject name
						         .replaceAll("\\s*\\n\\s*", " ")      // Replace line breaks and surrounding spaces with single space
						         .replaceAll("\\s{2,}", " ")          // Collapse multiple spaces
						         .replaceAll("\\(PAPER-\\s*([IVX]+)\\)", "(PAPER-$1)")  // Fix space in "(PAPER- III)"
						         .trim();
						System.out.println(subject);
						System.out.println(subjectToFind);
						
						System.out.println(subject.trim().equals(subjectToFind.trim()));
				

				String	theoryTotalMaxMarks = M_D_Unani_Y2_R16_PatternMatcher.group(2);
				String	theoryTotalSecMarks = M_D_Unani_Y2_R16_PatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y2_R16_PatternMatcher.group(3)
							: M_D_Unani_Y2_R16_PatternMatcher.group(3).replaceAll("[^0-9]", "");
				String	practicalVivaMaxMarks = M_D_Unani_Y2_R16_PatternMatcher.group(4);
				String	practicalVivaSecMarks = M_D_Unani_Y2_R16_PatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y2_R16_PatternMatcher.group(5)
							: M_D_Unani_Y2_R16_PatternMatcher.group(5).replaceAll("[^0-9]", "");

				String	theoryPracticalMaxMarks = M_D_Unani_Y2_R16_PatternMatcher.group(6);
				String	theoryPracticalSecMarks = M_D_Unani_Y2_R16_PatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
							? M_D_Unani_Y2_R16_PatternMatcher.group(7)
							: M_D_Unani_Y2_R16_PatternMatcher.group(7).replaceAll("[^0-9]", "");
					status = M_D_Unani_Y2_R16_PatternMatcher.group(8);

					System.out.println("Subject: " + subject);
					System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
					System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
					System.out.println("Practical Viva Max Marks: " + practicalVivaMaxMarks);
					System.out.println("Practical Viva Secured Marks: " + practicalVivaSecMarks);
					System.out.println("Theory plus Practical Max Marks: " + theoryPracticalMaxMarks);
					System.out.println("Theory plus Practical Secured Marks: " + theoryPracticalSecMarks);
					System.out.println("Status: " + status);

					System.out.println("---------------------------------------------------");
				
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {

						try {
		
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryTotalSecMarks, theoryTotalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theoryTotalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						try {
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalVivaSecMarks, practicalVivaMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Pratical plus viva Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ practicalVivaSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						
						 try {
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + theoryPracticalSecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
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
			}//IF_UNANI_Year2
					
				
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.UNANI"))&&(semester.trim().equalsIgnoreCase("SA-I") ) &&(regulation.trim().contains("2024")) ) {		
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

							testCaseScenario.log(Status.PASS, "Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case has started running");
	
							Pattern mdUnaniSA1Reg24SubjectPattern = Pattern.compile(
								    "([A-Za-z &'()\\-,\\.]+?)\\s+" +                                           // Subject
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 1st mark
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 2nd mark
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 3rd mark
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 4th mark
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 5th mark
								    "(NA|NE(?:\\s*\\(AT\\))?|AB|---|\\d+)(?:\\s*\\(F\\))?\\s+" +               // 6th mark
								    "(Pass|Fail|AP)" +                                                        // Final result
								    "(?:\\s+(Pass|Fail|First Class|Distinction|AP))?"                         // Optional classification
								);

								Matcher mdUnaniSA1Reg24SubjectPatternMatcher = mdUnaniSA1Reg24SubjectPattern.matcher(text);

						while (mdUnaniSA1Reg24SubjectPatternMatcher.find()) {
							try {
								testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						    System.out.println(mdUnaniSA1Reg24SubjectPatternMatcher.group(1));
						
						System.out.println("AYURVEDA.group1" +mdUnaniSA1Reg24SubjectPatternMatcher.group() );
						System.out.println("---------------------------------------------------");
								
						subject = mdUnaniSA1Reg24SubjectPatternMatcher.group(1);
						String theoryMaxMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(2);
						String theorySecMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(3);
						String sgpaMaxMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(4);
						String sgpaSecMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(5);
						String creditMaxMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(6);
						String creditSecMark = mdUnaniSA1Reg24SubjectPatternMatcher.group(7);
						status = mdUnaniSA1Reg24SubjectPatternMatcher.group(8);
						String finalStatus =mdUnaniSA1Reg24SubjectPatternMatcher.group(9);
						
						System.out.println("Subject: " +subject);
						System.out.println("Theory Max Mark: " + theoryMaxMark);
						System.out.println("Theory Secured Mark: " + theorySecMark);
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
								
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, theorySecMark, theoryMaxMark, 0.50, testCaseName);		


							}
							catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Theory Sec. Marks "
							+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theorySecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
		}
		
															
					try {
							PageValidation.validateMarks(Regno,"SGPA Sec. Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal, practicalExamTotal, subject,subjectToFind,status,
										grandTotal, sgpaSecMark, sgpaMaxMark, 0.00, testCaseName);		
						
					
					}
						
						catch(Exception e) {
							  ExtentTest testCaseScenario1 = testCaseName.createNode(
									  " SGPA Sec. Marks validation for subject " + subjectToFind + " test case has started");
							  testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ "SGPA Sec. Marks: " + sgpaSecMark,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
								
						}
			
								
					try {
					PageValidation.validateMarks(Regno,"Credits Sec. Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal, practicalExamTotal, subject,subjectToFind,status,
									grandTotal, creditSecMark, creditMaxMark, 0.00, testCaseName);		
					}
					
					catch(Exception e) {
						  ExtentTest testCaseScenario1 = testCaseName.createNode(
								  " Credits Sec. Marks validation for subject " + subjectToFind + " test case has started");
						  testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno
											+ "Credits Sec. Marks: " + creditSecMark,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
							
					}
			
							

						
					}//if 
						}//try
							catch(Exception e) {
								
							}
						
						}//while	
						
						}//try
						
					
						catch(Exception e) {
							
						}
						
					}//else if 

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
	














}//class