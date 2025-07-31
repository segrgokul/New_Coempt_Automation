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

public class KnrReportCard_MD_MS_AYURVEDA_CoursePattern  extends BasicFunctions {
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	

	public void processMD_MS_AYURVEDAPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)M\\.(?:D(?:\\.A)?\\.?|S\\.)\\s*AYURVEDA\\s*\\([A-Z &]+\\)\\s+";

					
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
					
		
						
					
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").contains("AYURVEDA"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						
						try{
							
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							Pattern md_MS_AYURVEDAY1P1R16MarksPattern = Pattern.compile(
								    "^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
								    + "([A-Z &'/\\-]+(?:\\s+[A-Z &'/\\-]+)*)\\s+"                                  // Group 1: Subject
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 2: Theory Max
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 3: Theory Sec
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 4: Practical Max
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 5: Practical Sec
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 6: Grand Total Max
								    + "((?:\\d+\\s*(?:\\(F\\))?|F|---|NE\\s*\\(AT\\)|NE|NA|AB))\\s+"              // Group 7: Grand Total Sec
								    + "(Pass|Fail|AP|NE|AB)\\b",                                                  // Group 8: Result
								    Pattern.MULTILINE
								);





						Matcher md_MS_AYURVEDAY1P1R16MarksPatternMatcher = md_MS_AYURVEDAY1P1R16MarksPattern.matcher(text);

						while (md_MS_AYURVEDAY1P1R16MarksPatternMatcher.find()) {
							try {
								subject = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(1);
								System.out.println(subject);

								String theoryMaxMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(2).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
								String theorySecMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(3).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
								String praticalMaxMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(4).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
								String praticalSecMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(5).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
								String grandTotalMaxMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(6).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
								String grandTotalSecMarks = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(7).replaceAll("\\s*\\(\\s*F\\s*\\)", "");

								status = md_MS_AYURVEDAY1P1R16MarksPatternMatcher.group(8);

								// Check if this is the subject we are looking for

				

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
			

								if ((status.trim().equals("Pass") || status.trim().equals("Fail")
										|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {

									
									try {
									PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
							     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theorySecMarks, theoryMaxMarks, 0.50, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Theory Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
								
								
								try {
									
								
									PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal,subject,subjectToFind,status,
									grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		
							
								
				
							}
								catch(Exception e) {
									  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
											  " Pratical Total Sec. Marks validation for subject " + subjectToFind + " test case has started");
									  testCaseScenario1.log(Status.FAIL,
												"\n Please check The Following Registration number " + Regno
														+ "Pratical Total Sec. Marks: " + praticalSecMarks);							
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
							
									
								
						
					} //if bracket
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
				}//if md_MS_AYURVEDAY1P1R16
	
					
					else	if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").contains("AYURVEDA"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2016")) ) {
				
						try{
							
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
						
							
							
						Pattern md_MS_AYURVEDAY2P2R16SubjectPattern = Pattern.compile("Subject\\s*(?:Name)?\\s*:\\s*(.+)",
								Pattern.CASE_INSENSITIVE);

						Matcher md_MS_AYURVEDAY2P2R16SubjectPatternMatcher = md_MS_AYURVEDAY2P2R16SubjectPattern.matcher(text);

						if (md_MS_AYURVEDAY2P2R16SubjectPatternMatcher.find()) {
							// Check which group is non-null and capture the correct subject name
							subject = md_MS_AYURVEDAY2P2R16SubjectPatternMatcher.group(1);

							System.out.println("Subject: " + subject);
							
							testCaseScenario.log(Status.INFO, "Subject: " + subject);
							
						}
						
						Pattern md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPattern = Pattern.compile(
								"(Paper\\s[IVX]+)\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Total\\s+Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)|Max\\.Marks:(\\d+)\\s+Min\\.Marks:(\\d+)");

						Matcher md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPattern.matcher(text);

						String practicalTotalMaxMark = null;
						String practicalTotalMinMark = null;

						String theoryTotalMaxMark = null;
						String theoryTotalMinMark = null;

						String grandTotalMaxMarks = null;
						String grandTotalMinMarks = null;

						while (md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.find()) {

			

							if (md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(6) != null) {

								theoryTotalMaxMark = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(6);
								theoryTotalMinMark = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(7);

								System.out.println("Total Max Marks is : " + md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(6) + ", Min Marks: "
										+ md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(7));
							}

							else if (((md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4).equals("100") || (md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4).equals("200")))
									&& md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4) != null)) {
								System.out.println("Pratical Max mark is : " + md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4) + " and min  Marks is : " + md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(5));
					
								practicalTotalMaxMark = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4);
								practicalTotalMinMark = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(5);

								continue;
							} else if (((md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4).equals("500") || (md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4).equals("600")))
									&& md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4) != null)) {
								System.out.println("Grand Total Max Marks: " + md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4) + ", Min Marks: "
										+ md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(5));
								grandTotalMaxMarks = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(4);
								grandTotalMinMarks = md_MS_AYURVEDAY2P2R16SubjectMaxMinMarkPatternMatcher.group(5);
							}

						}
					
						Pattern md_MS_AYURVEDAY2P2R16SubjectMarkPattern = Pattern.compile(
							    "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "([\\d]+(?:\\s*\\(\\s*F\\s*\\))?|AB|NE(?:\\s*\\(\\s*AT\\s*\\))?|NR|---)\\s+"
							  + "(Pass|Fail|AP)"
							);

						Matcher md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher = md_MS_AYURVEDAY2P2R16SubjectMarkPattern.matcher(text);


						if (md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.find()) {
							
							
							try {
								testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						
						
							String theoryPaper1 = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(1).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String theoryPaper2 = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(2).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String theoryPaper3 = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(3).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String theoryPaper4 = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(4).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String theoryTotalSecMark = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(5).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String practicalTotalSecMark = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(6).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String grandTotalSecMarks = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(7).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							status = md_MS_AYURVEDAY2P2R16SubjectMarkPatternMatcher.group(8);

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
							
							testCaseScenario.log(Status.INFO, "Subject                    : " + subject);
							testCaseScenario.log(Status.INFO, "Theory Paper 1             : " + theoryPaper1);
							testCaseScenario.log(Status.INFO, "Theory Paper 2             : " + theoryPaper2);
							testCaseScenario.log(Status.INFO, "Theory Paper 3             : " + theoryPaper3);
							testCaseScenario.log(Status.INFO, "Theory Paper 4             : " + theoryPaper4);
							testCaseScenario.log(Status.INFO, "Theory Total Max Marks     : " + theoryTotalMaxMark);
							testCaseScenario.log(Status.INFO, "Theory Total Min Marks     : " + theoryTotalMinMark);
							testCaseScenario.log(Status.INFO, "Theory Total Secured Marks : " + theoryTotalSecMark);
							testCaseScenario.log(Status.INFO, "Practical Max Marks        : " + practicalTotalMaxMark);
							testCaseScenario.log(Status.INFO, "Practical Min Marks        : " + practicalTotalMinMark);
							testCaseScenario.log(Status.INFO, "Practical Secured Marks    : " + practicalTotalSecMark);
							testCaseScenario.log(Status.INFO, "Grand Total Max Marks      : " + grandTotalMaxMarks);
							testCaseScenario.log(Status.INFO, "Grand Total Min Marks      : " + grandTotalMinMarks);
							testCaseScenario.log(Status.INFO, "Grand Total Secured Marks  : " + grandTotalSecMarks);
							testCaseScenario.log(Status.INFO, "Result                     : " + status);

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {

								
								try {
									PageValidation.nonValidateMarks(Regno,"Paper1 Sec Marks",
										     subject,subjectToFind,theoryPaper1, practicalTotalMinMark, 0.00, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Paper1 Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryPaper1 ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
								
								try {
									PageValidation.nonValidateMarks(Regno,"Paper2 Sec Marks",
										     subject,subjectToFind,theoryPaper2, practicalTotalMinMark, 0.00, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Paper2 Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 2 sec mark is: "+ theoryPaper2 ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
								
								try {
									PageValidation.nonValidateMarks(Regno,"Paper3 Sec Marks",
							     subject,subjectToFind,theoryPaper3, practicalTotalMinMark, 0.00, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Paper3 Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 3 sec mark is: "+ theoryPaper3 ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
								
								try {
									PageValidation.nonValidateMarks(Regno,"Paper4 Sec Marks", subject,subjectToFind
									, theoryPaper4, practicalTotalMinMark, 0.00, testCaseName);		

																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Paper4 Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 4 sec mark is: "+ theoryPaper4 ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
								
								try {
									PageValidation.validateMarks(Regno,"Theory Total Sec Marks", paper1, paper2, paper3,paper4,
							     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryTotalSecMark, theoryTotalMaxMark, 0.50, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Theory Total Sec Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Theory Total Sec Marks is: "+ theoryTotalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
										
								try {
									PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
							     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalTotalSecMark, practicalTotalMaxMark, 0.50, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Pratical Total Sec Marks"
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Pratical Total Sec Marks is: "+ practicalTotalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
										
								try {
									PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, grandTotalSecMarks, grandTotalMaxMarks, 0.50, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Grand Total Sec Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Grand Total Sec Marks is: "+ grandTotalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
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
						}//ELSE IF_M.S. AYURVEDA_Year2
				
		else	if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").contains("AYURVEDA"))&&(semester.trim().equalsIgnoreCase("SA-I") ) &&(regulation.trim().contains("2024")) ) {
						
						try{
							
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
						
							
							Pattern md_MS_AYURVEDAY1P1R24SubjectMarkPattern = Pattern.compile(
								    "([A-Za-z &'()\\-,\\.]+?)\\s+" +                                        // Group 1: Subject
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 2: 1st mark
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 3: 2nd mark
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 4: 3rd mark
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 5: 4th mark
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 6: 5th mark
								    "((?:NA|NR|NE\\s*\\(AT\\)|NE|---|\\d+)(?:\\s*\\(F\\))?)\\s+" +          // Group 7: 6th mark
								    "(Pass|Fail|AP)" +                                                     // Group 8: Final result
								    "(?:\\s+(Pass|Fail|First Class|Distinction|AP))?"                      // Group 9: Optional classification
								);
     

									Matcher md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher = md_MS_AYURVEDAY1P1R24SubjectMarkPattern.matcher(text);

							while (md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.find()) {
						
								try {
									testCaseScenario.log(Status.PASS, "Subect Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

								
								System.out.println(md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(1));
							
							System.out.println("AYURVEDA.group1" +md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group() );
							System.out.println("---------------------------------------------------");
									
							subject = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(1);
							String theoryMaxMarks = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(2).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String theorySecMarks = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(3).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String sgpaMaxMark = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(4).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String sgpaSecMark = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(5).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String creditMaxMark = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(6).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String creditSecMark = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(7).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							status = md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(8).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							String finalStatus =md_MS_AYURVEDAY1P1R24SubjectMarkPatternMatcher.group(9).replaceAll("\\s*\\(\\s*F\\s*\\)", "");
							
							System.out.println("Subject: " +subject);
							System.out.println("Theory Max Mark: " + theoryMaxMarks);
							System.out.println("Theory Secured Mark: " + theorySecMarks);
							System.out.println("SGPA Max Mark: " + sgpaMaxMark);
							System.out.println("SGPA Secured Mark: " + sgpaSecMark);
							System.out.println("Credit Max Mark: " + creditMaxMark);
							System.out.println("Credit Secured Mark: " + creditSecMark);

							System.out.println("Result: " + status);
							System.out.println("Final Result: " + finalStatus);

							System.out.println("---------------------------------------------------");
							
							
					
							
							
					
						
				

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {

								
								try {
									PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
							     	theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theorySecMarks, theoryMaxMarks, 0.50, testCaseName);		


																}
								catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Theory Sec. Marks "
									+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
												
											}
			
									try {
										PageValidation.nonValidateMarks(Regno,"SGPA Sec Marks",
											     subject,subjectToFind,sgpaSecMark, sgpaMaxMark, 0.00, testCaseName);		


																	}
									catch(Exception e) {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("SGPA Sec Marks "
										+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of SGPA Sec Marks is: "+ sgpaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
													
												}	
									
									
									try {
										PageValidation.nonValidateMarks(Regno,"Credit Secured Marks",
											     subject,subjectToFind,creditSecMark, creditMaxMark, 0.00, testCaseName);		


																	}
									catch(Exception e) {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Credit Secured Marks "
										+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Credit Secured Mark: is: "+ sgpaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
													
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
						}//ELSE IF_M.S. AYURVEDA_Year2		
					
					
					
					

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