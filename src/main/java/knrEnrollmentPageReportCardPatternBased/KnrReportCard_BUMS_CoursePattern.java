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

public class KnrReportCard_BUMS_CoursePattern extends BasicFunctions{


String subject;	
String status;

KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();


public void processBUMSPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
		Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, ExtentTest testCaseName, String subjectToFind) throws IOException {
	if (latestFile != null) {
		try (PDDocument document = PDDocument.load(latestFile)) {
			PDFTextStripper stripper = new PDFTextStripper();
			int totalPages = document.getNumberOfPages();
			System.out.println("Total Pages: " + totalPages);
			System.out.println("---------------------------------------------------");

			// Iterate through all pages and extract text
			for (int page = 1; page <= 1; page++) {
				stripper.setStartPage(page);
				stripper.setEndPage(page);

				String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
				System.out.println("Page " + page + ":");
				System.out.println("---------------------------------------------------");

				String courseNameRegex = "(?i)(BUMS)\\s*";

				
				Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
				Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

				if (courseNameRegexPatternMatcher.find()) {
					
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the following " + Regno + " Test case has started running");
					System.out.println("MathchFound: " + courseNameRegexPatternMatcher.group());
					
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
				
				
				
			if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2016")) ) {
					try {
						ExtentTest testCaseScenario = testCaseName.createNode(
								"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
			

						Pattern bumsY2R16SubjectRegexPattern = Pattern.compile(
							    "(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|Pass).*\\n)*" +  // Ignore header lines
							    "([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" +  // Multi-line subject names
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" +
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
							    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
							    "(?:-+\\s*)?" +
							    "(Pass|Fail|AP)"
							);



							
						
				Matcher bumsY2R16SubjectRegexPatternMatcher = bumsY2R16SubjectRegexPattern.matcher(text);		
	
				while(bumsY2R16SubjectRegexPatternMatcher.find()) {
					try {
					testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
					subject =bumsY2R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
					

					String	theoryTotalMaxMarks = bumsY2R16SubjectRegexPatternMatcher.group(2);
					String	theoryTotalSecMarks = bumsY2R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
								? bumsY2R16SubjectRegexPatternMatcher.group(3)
								: bumsY2R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "");
					String	practicalMaxMarks = bumsY2R16SubjectRegexPatternMatcher.group(4);
					String	practicalSecMarks = bumsY2R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
								? bumsY2R16SubjectRegexPatternMatcher.group(5)
								: bumsY2R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "");

					String	grandTotalMaxMarks = bumsY2R16SubjectRegexPatternMatcher.group(6);
					String	grandTotalSecMarks = bumsY2R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
								? bumsY2R16SubjectRegexPatternMatcher.group(7)
								: bumsY2R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "");
						status = bumsY2R16SubjectRegexPatternMatcher.group(8);

						System.out.println("Subject: " + subject);
						System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
						System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
						System.out.println("Practical Viva Max Marks: " + practicalMaxMarks);
						System.out.println("Practical Viva Secured Marks: " + practicalSecMarks);
						System.out.println("Theory plus Practical Max Marks: " + grandTotalMaxMarks);
						System.out.println("Theory plus Practical Secured Marks: " + grandTotalSecMarks);
						System.out.println("Status: " + status);

						System.out.println("---------------------------------------------------");
System.out.println(subject.trim().equals(subjectToFind.trim()));

						if ((status.trim().equals("Pass") || status.trim().equals("Fail")
								|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
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
							if(theoryTotalSecMarks.trim().equals("AB") && !practicalSecMarks.trim().equals("NE (AT)") ) {
								
								
								ExtentTest testCaseScenario1 = testCaseName
										.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
												+ subject + " Test case has started running");		
																	
								testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ " of theory sec mark is: "+ theoryTotalSecMarks +" Pratical Sec is:" + practicalSecMarks
												,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

								System.out.println("\n Please check The Following Registration number " + Regno
										+ " for the Subject " + subject
										+ "of theory internal sec mark "+ theoryTotalSecMarks+" Pratical plus viva Sec is :" + practicalSecMarks);

							}else {
								
								ExtentTest testCaseScenario1 = testCaseName
										.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
												+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ " of theory  mark is: "+ theoryTotalSecMarks+" Pratical Sec mark is : " + practicalSecMarks);

								System.out.println(	"The Following Registration number " + Regno
										+ " for the Subject " + subject);
							}
							
							}
							catch(Exception e) {

								  testCaseScenario.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ " for the Subject " + subject
													+ " of Pratical Univ Sec mark is: "+ theoryTotalSecMarks 
													,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
							}//catch	
							try {
								
								PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, practicalSecMarks, practicalMaxMarks, 0.50, testCaseName);		


							}
							catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Pratical Sec. Marks "
							+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ practicalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
					testCaseScenario.log(Status.FAIL,e.getMessage());
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
		}// IF_BUMMS_Year2
			else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS"))&&(semester.trim().equalsIgnoreCase("Year 3") ) &&(regulation.trim().contains("2016")) ) {
				try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
		

					Pattern bumsY3R16SubjectRegexPattern = Pattern.compile(
						    "(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|Pass).*\\n)*" +  // Ignore header lines
						    "([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" +  // Multi-line subject names
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "(?:-+\\s*)?" +
						    "(Pass|Fail|AP)"
						);



						
					
			Matcher bumsY3R16SubjectRegexPatternMatcher = bumsY3R16SubjectRegexPattern.matcher(text);		

			while(bumsY3R16SubjectRegexPatternMatcher.find()) {
				try {
				testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
				subject =bumsY3R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
				

				String	theoryTotalMaxMarks = bumsY3R16SubjectRegexPatternMatcher.group(2);
				String	theoryTotalSecMarks = bumsY3R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
							? bumsY3R16SubjectRegexPatternMatcher.group(3)
							: bumsY3R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "");
				String	practicalMaxMarks = bumsY3R16SubjectRegexPatternMatcher.group(4);
				String	practicalSecMarks = bumsY3R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
							? bumsY3R16SubjectRegexPatternMatcher.group(5)
							: bumsY3R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "");

				String	grandTotalMaxMarks = bumsY3R16SubjectRegexPatternMatcher.group(6);
				String	grandTotalSecMarks = bumsY3R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
							? bumsY3R16SubjectRegexPatternMatcher.group(7)
							: bumsY3R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "");
					status = bumsY3R16SubjectRegexPatternMatcher.group(8);

					System.out.println("Subject: " + subject);
					System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
					System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
					System.out.println("Practical Viva Max Marks: " + practicalMaxMarks);
					System.out.println("Practical Viva Secured Marks: " + practicalSecMarks);
					System.out.println("Theory plus Practical Max Marks: " + grandTotalMaxMarks);
					System.out.println("Theory plus Practical Secured Marks: " + grandTotalSecMarks);
					System.out.println("Status: " + status);

					System.out.println("---------------------------------------------------");
System.out.println(subject.trim().equals(subjectToFind.trim()));

					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
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
						if(theoryTotalSecMarks.trim().equals("AB") && !practicalSecMarks.trim().equals("NE (AT)") ) {
							
							
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
											+ subject + " Test case has started running");		
																
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ " of theory sec mark is: "+ theoryTotalSecMarks +" Pratical Sec is:" + practicalSecMarks
											,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println("\n Please check The Following Registration number " + Regno
									+ " for the Subject " + subject
									+ "of theory internal sec mark "+ theoryTotalSecMarks+" Pratical plus viva Sec is :" + practicalSecMarks);

						}else {
							
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
											+ subject + " Test case has started running");		
							
							testCaseScenario1.log(Status.PASS,
									"The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ " of theory  mark is: "+ theoryTotalSecMarks+" Pratical Sec mark is : " + practicalSecMarks);

							System.out.println(	"The Following Registration number " + Regno
									+ " for the Subject " + subject);
						}
						
						}
						catch(Exception e) {

							  testCaseScenario.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ " of Pratical Univ Sec mark is: "+ theoryTotalSecMarks 
												,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

	
						}//catch	
						try {
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalSecMarks, practicalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Pratical Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ practicalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
				testCaseScenario.log(Status.FAIL,e.getMessage());
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
	}//Else IF_BuMS_Year3	
			
			else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS"))&&(semester.trim().equalsIgnoreCase("Year 4") ) &&(regulation.trim().contains("2016")) ) {
				try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
		

					Pattern bumsY4R16SubjectRegexPattern = Pattern.compile(
						    "(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|Pass).*\\n)*" +  // Ignore header lines
						    "([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" +  // Multi-line subject names
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "((?:\\d+|NA|AB|NE(?:\\s*\\(AT\\))?|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" +
						    "(?:-+\\s*)?" +
						    "(Pass|Fail|AP)"
						);



						
					
			Matcher bumsY4R16SubjectRegexPatternMatcher = bumsY4R16SubjectRegexPattern.matcher(text);		

			while(bumsY4R16SubjectRegexPatternMatcher.find()) {
				try {
				testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
				subject =bumsY4R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
				

				String	theoryTotalMaxMarks = bumsY4R16SubjectRegexPatternMatcher.group(2);
				String	theoryTotalSecMarks = bumsY4R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
							? bumsY4R16SubjectRegexPatternMatcher.group(3)
							: bumsY4R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "");
				String	practicalMaxMarks = bumsY4R16SubjectRegexPatternMatcher.group(4);
				String	practicalSecMarks = bumsY4R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
							? bumsY4R16SubjectRegexPatternMatcher.group(5)
							: bumsY4R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "");

				String	grandTotalMaxMarks = bumsY4R16SubjectRegexPatternMatcher.group(6);
				String	grandTotalSecMarks = bumsY4R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
							? bumsY4R16SubjectRegexPatternMatcher.group(7)
							: bumsY4R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "");
					status = bumsY4R16SubjectRegexPatternMatcher.group(8);

					System.out.println("Subject: " + subject);
					System.out.println("Theory Total Max Marks: " + theoryTotalMaxMarks);
					System.out.println("Theory Total Secured Marks: " + theoryTotalSecMarks);
					System.out.println("Practical Viva Max Marks: " + practicalMaxMarks);
					System.out.println("Practical Viva Secured Marks: " + practicalSecMarks);
					System.out.println("Theory plus Practical Max Marks: " + grandTotalMaxMarks);
					System.out.println("Theory plus Practical Secured Marks: " + grandTotalSecMarks);
					System.out.println("Status: " + status);

					System.out.println("---------------------------------------------------");
System.out.println(subject.trim().equals(subjectToFind.trim()));

					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
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
						if(theoryTotalSecMarks.trim().equals("AB") && !practicalSecMarks.trim().equals("NE (AT)") ) {
							
							
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
											+ subject + " Test case has started running");		
																
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ " of theory sec mark is: "+ theoryTotalSecMarks +" Pratical Sec is:" + practicalSecMarks
											,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							System.out.println("\n Please check The Following Registration number " + Regno
									+ " for the Subject " + subject
									+ "of theory internal sec mark "+ theoryTotalSecMarks+" Pratical plus viva Sec is :" + practicalSecMarks);

						}else {
							
							ExtentTest testCaseScenario1 = testCaseName
									.createNode("Theory and Pratical Sec. Marks Validation for the Subject "
											+ subject + " Test case has started running");		
							
							testCaseScenario1.log(Status.PASS,
									"The Following Registration number " + Regno
											+ " for the Subject " + subject
											+ " of theory  mark is: "+ theoryTotalSecMarks+" Pratical Sec mark is : " + practicalSecMarks);

							System.out.println(	"The Following Registration number " + Regno
									+ " for the Subject " + subject);
						}
						
						}
						catch(Exception e) {

							  testCaseScenario.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ " for the Subject " + subject
												+ " of Pratical Univ Sec mark is: "+ theoryTotalSecMarks 
												,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

	
						}//catch	
						try {
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalSecMarks, practicalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Pratical Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ practicalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
				testCaseScenario.log(Status.FAIL,e.getMessage());
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
	}//Else IF_BuMS_Year4	
			
			
			else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2021")) ) {
				try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
		
System.out.println(text);
Pattern bumsY1R21SubjectRegexPattern = Pattern.compile(
	    "(?:(?:Fail|Pass|AP|NE|AB|Theory|KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Practical|Secured Marks in Words: |Grand Total|Controller of Examinations|Principal).*\\n)*" + // Skip headers
	    "([A-Z][A-Za-z &'()\\-,]+(?:\\n[ A-Za-z&'()\\-,]+)*)\\s+" + // ✅ Group 1: Subject name (multi-line, relaxed)

	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 2: Theory Int Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 3: Theory Int Sec  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 4: Theory Univ Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 5: Theory Univ Sec  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 6: Prac Int Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 7: Prac Int Sec  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 8: Prac Univ Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 9: Prac Univ Sec  

	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)\\s+" +                  // Group 10: Theory + Prac Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 11: Theory + Prac Sec  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 12: Grand Max  
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 13: Grand Sec  

	    "(Pass|Fail|AP)",                                            // Group 14: Result
	    Pattern.MULTILINE
	);

					
					

					Matcher bumsY1R21SubjectRegexPatternMatcher = bumsY1R21SubjectRegexPattern.matcher(text);

			while(bumsY1R21SubjectRegexPatternMatcher.find()) {
				try {
				testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
				subject = bumsY1R21SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

				String theoryPaper1 = bumsY1R21SubjectRegexPatternMatcher.group(2);
				String theoryPaper2 = bumsY1R21SubjectRegexPatternMatcher.group(3);
				String totalTheoryMaxMarks = bumsY1R21SubjectRegexPatternMatcher.group(4);
				String totalTheorySecMarks = bumsY1R21SubjectRegexPatternMatcher.group(5);
				String internalAssessmentSecMark = bumsY1R21SubjectRegexPatternMatcher.group(6);
				String electiveSecMark = bumsY1R21SubjectRegexPatternMatcher.group(7);
				String practicalSecMark = bumsY1R21SubjectRegexPatternMatcher.group(8);
				String vivaSecMark = bumsY1R21SubjectRegexPatternMatcher.group(9);
				String totalPracticalMaxMarks = bumsY1R21SubjectRegexPatternMatcher.group(10);
				String totalPracticalSecMarks = bumsY1R21SubjectRegexPatternMatcher.group(11);
				String grandTotalMaxMarks = bumsY1R21SubjectRegexPatternMatcher.group(12);
				String grandTotalSecMarks = bumsY1R21SubjectRegexPatternMatcher.group(13);
				status = bumsY1R21SubjectRegexPatternMatcher.group(14);

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
							|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {

		
						try {

							PageValidation.nonValidateMarks(Regno,"Theory paper 1 marks", subject,subjectToFind,
									theoryPaper1,totalTheoryMaxMarks, 0.0, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 1 marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"Theory paper 2 marks", subject,subjectToFind,
									theoryPaper2,totalTheoryMaxMarks,0.0, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 2 marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 2 marks is: " + theoryPaper2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 2 marks is: " + theoryPaper2);
											
					}
			
			
try {
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, totalTheorySecMarks, totalTheoryMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ totalTheorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
try {

PageValidation.nonValidateMarks(Regno,"Internal Assessment Sec. Marks", subject,subjectToFind,
		internalAssessmentSecMark,totalTheoryMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName.createNode(
	"Internal Assessment Sec. Marks Validation for the Subject "
			+ subject + " Test case has started running");	
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Internal Assessment Sec. Marks is: " + theoryPaper2,
	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Internal Assessment Sec. Marks is: " + theoryPaper2);
				
}
try {

	PageValidation.nonValidateMarks(Regno,"Elective Sec. Marks", subject,subjectToFind,
			electiveSecMark,totalTheoryMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Elective Sec. Marks Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Elective Sec. Marks is: " + electiveSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Elective Sec. Marks is: " + electiveSecMark);
					
}
try {

	PageValidation.nonValidateMarks(Regno,"Practical Sec Mark", subject,subjectToFind,
			practicalSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Practical Sec Mark Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Practical Sec. Marks is: " + practicalSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Sec. Marks is: " + practicalSecMark);
					
}		
try {

	PageValidation.nonValidateMarks(Regno,"Viva Sec Mark", subject,subjectToFind,
			vivaSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Viva Sec Mark Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Viva Sec. Marks is: " + vivaSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Viva Sec. Marks is: " + vivaSecMark);
					
}	

 try {
    	

    	PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, totalTheorySecMarks, totalTheoryMaxMarks, 0.50, testCaseName);		
	}
	
	catch(Exception e) {
		  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
				  "Theory Total Sec Marks validation for subject " + subjectToFind + " test case has started");
		  testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno
							+ "Theory Total Sec Marks"  + totalTheorySecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									
	}

try {
	
	PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
			theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
			grandTotal, totalPracticalSecMarks, totalPracticalMaxMarks, 0.50, testCaseName);		


}
catch(Exception e) {
	ExtentTest testCaseScenario1 = testCaseScenario
			.createNode("Pratical Sec. Marks "
+ subject + " Test case has started running");		
	testCaseScenario1.log(Status.FAIL,
			"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ totalPracticalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
						testCaseScenario.log(Status.FAIL,e.getMessage());
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
			}//Else IF_BUMS_Year1_R21
			
			else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2021")) ) {
				try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
		
System.out.println(text);

Pattern bumsY2R21SubjectRegexPattern = Pattern.compile(
	    "(?:(?:Fail|Pass|AP|NE(?:\\s*\\(AT\\))?|AB|Theory|KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Practical|Secured Marks in Words: |Grand Total|Controller of Examinations|Principal).*\\n)*" + // Skip headers
	    "([A-Z][A-Za-z &'()\\-,]+(?:\\n[ A-Za-z&'()\\-,]+)*)\\s+" +  // Group 1: Subject name
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 2: Theory Int Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 3: Theory Int Sec
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 4: Theory Univ Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 5: Theory Univ Sec
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 6: Prac Int Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 7: Prac Int Sec
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 8: Prac Univ Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 9: Prac Univ Sec
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)\\s+" +                 // Group 10: Theory + Prac Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 11: Theory + Prac Sec
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 12: Grand Max
	    "(\\d+|NA|NE(?:\\s*\\(AT\\))?|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 13: Grand Sec
	    "(Pass|Fail|AP)",                                           // Group 14: Result
	    Pattern.MULTILINE
	);


					Matcher bumsY2R21SubjectRegexPatternMatcher = bumsY2R21SubjectRegexPattern.matcher(text);

			while(bumsY2R21SubjectRegexPatternMatcher.find()) {
				try {
				testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
				subject = bumsY2R21SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

				String theoryPaper1 = bumsY2R21SubjectRegexPatternMatcher.group(2);
				String theoryPaper2 = bumsY2R21SubjectRegexPatternMatcher.group(3);
				String totalTheoryMaxMarks = bumsY2R21SubjectRegexPatternMatcher.group(4);
				String totalTheorySecMarks = bumsY2R21SubjectRegexPatternMatcher.group(5);
				String internalAssessmentSecMark = bumsY2R21SubjectRegexPatternMatcher.group(6);
				String electiveSecMark = bumsY2R21SubjectRegexPatternMatcher.group(7);
				String practicalSecMark = bumsY2R21SubjectRegexPatternMatcher.group(8);
				String vivaSecMark = bumsY2R21SubjectRegexPatternMatcher.group(9);
				String totalPracticalMaxMarks = bumsY2R21SubjectRegexPatternMatcher.group(10);
				String totalPracticalSecMarks = bumsY2R21SubjectRegexPatternMatcher.group(11);
				String grandTotalMaxMarks = bumsY2R21SubjectRegexPatternMatcher.group(12);
				String grandTotalSecMarks = bumsY2R21SubjectRegexPatternMatcher.group(13);
				status = bumsY2R21SubjectRegexPatternMatcher.group(14);

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
							|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {

		
						try {

							PageValidation.nonValidateMarks(Regno,"Theory paper 1 marks", subject,subjectToFind,
									theoryPaper1,totalTheoryMaxMarks,0.0, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 1 marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"Theory paper 2 marks", subject,subjectToFind,
									theoryPaper2,totalTheoryMaxMarks,0.0, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 2 marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 2 marks is: " + theoryPaper2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 2 marks is: " + theoryPaper2);
											
					}
			
			
try {
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, totalTheorySecMarks, totalTheoryMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ totalTheorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
try {

PageValidation.nonValidateMarks(Regno,"Internal Assessment Sec. Marks", subject,subjectToFind,
		internalAssessmentSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName.createNode(
	"Internal Assessment Sec. Marks Validation for the Subject "
			+ subject + " Test case has started running");	
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Internal Assessment Sec. Marks is: " + theoryPaper2,
	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Internal Assessment Sec. Marks is: " + theoryPaper2);
				
}
try {

	PageValidation.nonValidateMarks(Regno,"Elective Sec. Marks", subject,subjectToFind,
			electiveSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Elective Sec. Marks Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Elective Sec. Marks is: " + electiveSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Elective Sec. Marks is: " + electiveSecMark);
					
}
try {

	PageValidation.nonValidateMarks(Regno,"Practical Sec Mark", subject,subjectToFind,
			practicalSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Practical Sec Mark Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Practical Sec. Marks is: " + practicalSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Sec. Marks is: " + practicalSecMark);
					
}		
try {

	PageValidation.nonValidateMarks(Regno,"Viva Sec Mark", subject,subjectToFind,
			vivaSecMark,totalPracticalMaxMarks,0.0, testCaseName);		
}	

catch(Exception e) {
ExtentTest testCaseScenario1 = testCaseName
		.createNode("Viva Sec Mark Validation for the Subject "
				+ subject + " Test case has started running");
testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +"Viva Sec. Marks is: " + vivaSecMark,
		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());


System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Viva Sec. Marks is: " + vivaSecMark);
					
}	

 try {
    	

    	PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, totalTheorySecMarks, totalTheoryMaxMarks, 0.50, testCaseName);		
	}
	
	catch(Exception e) {
		  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
				  "Theory Total Sec Marks validation for subject " + subjectToFind + " test case has started");
		  testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno
							+ "Theory Total Sec Marks"  + totalTheorySecMarks,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									
	}

try {
	
	PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
			theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
			grandTotal, totalPracticalSecMarks, totalPracticalMaxMarks, 0.50, testCaseName);		


}
catch(Exception e) {
	ExtentTest testCaseScenario1 = testCaseScenario
			.createNode("Pratical Sec. Marks "
+ subject + " Test case has started running");		
	testCaseScenario1.log(Status.FAIL,
			"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ totalPracticalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
						testCaseScenario.log(Status.FAIL,e.getMessage());
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
			}//Else IF_BUMS_Year1_R21	
			
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
			
		