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

public class KnrReportCard_BAMS_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	
	
	public void processBAMSPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(BAMS)\\s+";

					
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
					
					
					
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							System.out.println(text);
					
	
							Pattern bamsY1R16SubjectRegexPattern = Pattern.compile(
								    "^([A-Z &'\\-(),/]+(?:\\s+[A-Z &'\\-(),/]+)*)\\s+" + // Subject name
								    "(?:\\(([^)]+)\\))?\\s*" + // Optional (paper name)
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 1
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 2
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 3
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 4
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 5
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 6
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 7
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 8
								    "(Pass|Fail|AP|NE|AB)\\b", // Final result
								    Pattern.MULTILINE
								);


			
					Matcher bamsY1R16SubjectRegexPatternMatcher = bamsY1R16SubjectRegexPattern.matcher(text);		
		
					while(bamsY1R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						System.out.println("==============");

						String subjectCHeck =bamsY1R16SubjectRegexPatternMatcher.group(1);
						String sub =bamsY1R16SubjectRegexPatternMatcher.group(2);
						
						System.out.println(sub + " " +subjectCHeck);
						
//								subject = fourPatternMatcher.group(1).trim();
						subject = (bamsY1R16SubjectRegexPatternMatcher.group(2) == null)
								? bamsY1R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
								: (bamsY1R16SubjectRegexPatternMatcher.group(1) + " " + bamsY1R16SubjectRegexPatternMatcher.group(2))
										.replaceAll("\\s+", " ").trim();
						
						String theoryMaxMarks = bamsY1R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	theorySecMarks = bamsY1R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalMaxMarks = bamsY1R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = bamsY1R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	vivaMaxMarks = bamsY1R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String vivaSecMarks = bamsY1R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = bamsY1R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalSecMarks = bamsY1R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = bamsY1R16SubjectRegexPatternMatcher.group(11);
						System.out.println("==============");

						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

							System.out.println("Practical Sec Marks: " + praticalSecMarks);
						// Practical Total Marks
						System.out.println("Viva Max Marks: " + vivaMaxMarks);
						System.out.println("Viva Total Sec Marks: " + vivaSecMarks);

						// Grand Total
						System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
						System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

						// Status
						System.out.println("Status: " + status);
						System.out.println("==============");
					System.out.println("---------------------------------------------------");
				
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
					
						
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
							
							PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal,  vivaSecMarks,  vivaMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Viva Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Viva sec mark is: "+ vivaSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
	
	try {
		
		PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
				theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
				grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		


	}
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario
				.createNode("Pratical Sec. Marks "
	+ subject + " Test case has started running");		
		testCaseScenario1.log(Status.FAIL,
				"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ praticalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
			}//IF_UNANI_Year2
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 3") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					

							Pattern bamsY3R16SubjectRegexPattern = Pattern.compile(
								    "^([A-Z &'\\-(),/]+(?:\\s+[A-Z &'\\-(),/]+)*)\\s+" + // Subject name
								    "(?:\\(([^)]+)\\))?\\s*" + // Optional (paper name)
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 1
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 2
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 3
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 4
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 5
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 6
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 7
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 8
								    "(Pass|Fail|AP|NE|AB)\\b", // Final result
								    Pattern.MULTILINE
								);


						Matcher bamsY3R16SubjectRegexPatternMatcher = bamsY3R16SubjectRegexPattern.matcher(text);		
			
						while(bamsY3R16SubjectRegexPatternMatcher.find()) {
							try {
							testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

							System.out.println("==============");

							String subjectCHeck =bamsY3R16SubjectRegexPatternMatcher.group(1);
							String sub =bamsY3R16SubjectRegexPatternMatcher.group(2);
							
							System.out.println(sub + " " +subjectCHeck);
							
//									subject = fourPatternMatcher.group(1).trim();
							subject = (bamsY3R16SubjectRegexPatternMatcher.group(2) == null)
									? bamsY3R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (bamsY3R16SubjectRegexPatternMatcher.group(1) + " " + bamsY3R16SubjectRegexPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();
							
							String theoryMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part

							String	theorySecMarks = bamsY3R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F);

							String	vivaMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String vivaSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							status = bamsY3R16SubjectRegexPatternMatcher.group(11);
							System.out.println("==============");

							System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
							System.out.println("Theory Max Marks: " + theoryMaxMarks);

							System.out.println("Theory Sec Marks: " + theorySecMarks);
							System.out.println("Practical Max Marks: " + praticalMaxMarks);

								System.out.println("Practical Sec Marks: " + praticalSecMarks);
							// Practical Total Marks
							System.out.println("Viva Max Marks: " + vivaMaxMarks);
							System.out.println("Viva Total Sec Marks: " + vivaSecMarks);

							// Grand Total
							System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
							System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

							// Status
							System.out.println("Status: " + status);
							System.out.println("==============");
						System.out.println("---------------------------------------------------");
System.out.println(subject.trim().equals(subjectToFind.trim()));

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
									
									PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal,  vivaSecMarks,  vivaMaxMarks, 0.50, testCaseName);		


								}
								catch(Exception e) {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Viva Sec. Marks "
								+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Viva sec mark is: "+ vivaSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
			}
			
			try {
				
				PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
						theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
						grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		


			}
			catch(Exception e) {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Pratical Sec. Marks "
			+ subject + " Test case has started running");		
				testCaseScenario1.log(Status.FAIL,
						"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ praticalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
			}//Else IF_BAMS_Year3
					
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 4") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
	
							Pattern bamsY4R16SubjectRegexPattern = Pattern.compile(
									"(?:(?:KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Theory Practical Grand Total|Pass).*\\n)*"
											+ // Ignore header lines
											"([A-Za-z &'()\\-,]+(?:\\n[A-Za-z &'()\\-,]+)*)\\s+" + // Capture subject names,
																									// allowing multi-line
																									// subjects
											"((?:\\d+|NA|AB|NE|NR|---|AP)(?:\\s*\\(F\\))?)\\s*" + // First mark (mandatory)
											"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Second mark (optional)
											"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Third mark (optional)
											"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Fourth mark (optional)
											"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Fifth mark (optional)
											"((?:\\d+|NA|AB|NE|NR|---|AP)?(?:\\s*\\(F\\))?)?\\s*" + // Sixth mark (optional)
											"(?:-+\\s*)?" + // Handle extra dashes before the final result
											"(Pass|Fail|AP)" // Final result
							);


								
							
					Matcher bamsY4R16SubjectRegexPatternMatcher = bamsY4R16SubjectRegexPattern.matcher(text);		
		
					while(bamsY4R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
						subject =bamsY4R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
						

						String	theoryTotalMaxMarks = bamsY4R16SubjectRegexPatternMatcher.group(2);
						String	theoryTotalSecMarks = bamsY4R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "").isEmpty()
									? bamsY4R16SubjectRegexPatternMatcher.group(3)
									: bamsY4R16SubjectRegexPatternMatcher.group(3).replaceAll("[^0-9]", "");
						String	practicalMaxMarks = bamsY4R16SubjectRegexPatternMatcher.group(4);
						String	practicalSecMarks = bamsY4R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "").isEmpty()
									? bamsY4R16SubjectRegexPatternMatcher.group(5)
									: bamsY4R16SubjectRegexPatternMatcher.group(5).replaceAll("[^0-9]", "");

						String	grandTotalMaxMarks = bamsY4R16SubjectRegexPatternMatcher.group(6);
						String	grandTotalSecMarks = bamsY4R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "").isEmpty()
									? bamsY4R16SubjectRegexPatternMatcher.group(7)
									: bamsY4R16SubjectRegexPatternMatcher.group(7).replaceAll("[^0-9]", "");
							status = bamsY4R16SubjectRegexPatternMatcher.group(8);

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
			}//Else IF_BAMS_Year4
				
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 3") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					

							Pattern bamsY3R16SubjectRegexPattern = Pattern.compile(
								    "^([A-Z &'\\-(),/]+(?:\\s+[A-Z &'\\-(),/]+)*)\\s+" + // Subject name
								    "(?:\\(([^)]+)\\))?\\s*" + // Optional (paper name)
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 1
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 2
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 3
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 4
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 5
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 6
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 7
								    "((?:\\d+\\s*\\(F\\)|\\d+|F|---|NA|NE(?:\\s*\\(AT\\))?|AB))\\s+" + // Mark 8
								    "(Pass|Fail|AP|NE|AB)\\b", // Final result
								    Pattern.MULTILINE
								);


						Matcher bamsY3R16SubjectRegexPatternMatcher = bamsY3R16SubjectRegexPattern.matcher(text);		
			
						while(bamsY3R16SubjectRegexPatternMatcher.find()) {
							try {
							testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

							System.out.println("==============");

							String subjectCHeck =bamsY3R16SubjectRegexPatternMatcher.group(1);
							String sub =bamsY3R16SubjectRegexPatternMatcher.group(2);
							
							System.out.println(sub + " " +subjectCHeck);
							
//									subject = fourPatternMatcher.group(1).trim();
							subject = (bamsY3R16SubjectRegexPatternMatcher.group(2) == null)
									? bamsY3R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (bamsY3R16SubjectRegexPatternMatcher.group(1) + " " + bamsY3R16SubjectRegexPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();
							
							String theoryMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part

							String	theorySecMarks = bamsY3R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F);

							String	vivaMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String vivaSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalMaxMarks = bamsY3R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalSecMarks = bamsY3R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							status = bamsY3R16SubjectRegexPatternMatcher.group(11);
							System.out.println("==============");

							System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
							System.out.println("Theory Max Marks: " + theoryMaxMarks);

							System.out.println("Theory Sec Marks: " + theorySecMarks);
							System.out.println("Practical Max Marks: " + praticalMaxMarks);

								System.out.println("Practical Sec Marks: " + praticalSecMarks);
							// Practical Total Marks
							System.out.println("Viva Max Marks: " + vivaMaxMarks);
							System.out.println("Viva Total Sec Marks: " + vivaSecMarks);

							// Grand Total
							System.out.println("Grand Total Max Marks: " + grandTotalMaxMarks);
							System.out.println("Grand Total Sec Marks: " + grandTotalSecMarks);

							// Status
							System.out.println("Status: " + status);
							System.out.println("==============");
						System.out.println("---------------------------------------------------");
System.out.println(subject.trim().equals(subjectToFind.trim()));

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
									
									PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal,  vivaSecMarks,  vivaMaxMarks, 0.50, testCaseName);		


								}
								catch(Exception e) {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Viva Sec. Marks "
								+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Viva sec mark is: "+ vivaSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
			}
			
			
			try {
				
				PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
						theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
						grandTotal, praticalSecMarks, praticalMaxMarks, 0.50, testCaseName);		


			}
			catch(Exception e) {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Pratical Sec. Marks "
			+ subject + " Test case has started running");		
				testCaseScenario1.log(Status.FAIL,
						"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ praticalSecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

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
			}//Else IF_BAMS_Year3
					
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2021")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
	System.out.println(text);
							Pattern bamsY1R21SubjectRegexPattern = Pattern.compile(
            				"^(?!Fail|Pass|AP|NE|AB|Theory|KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Practical|Secured Marks in Words: |Grand Total|Controller of Examinations|Principal)\\s*"+
            						"([A-Z][A-Za-z &,\\-]+(?:\\s+[A-Za-z &,\\-]+)*?(?:-[1-9])?)\\s+"
							+ "(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 2: Theory Int Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 3: Theory Int Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 4: Theory Univ Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 5: Theory Univ Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 6: Prac Int Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 7: Prac Int Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 8: Prac Univ Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 9: Prac Univ Sec
							"(\\d+|NA|NE|AB|---)\\s+" + // Group 10: Theory + Prac Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 11: Theory + Prac Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 12: Grand Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 13: Grand Sec
							"(Pass|Fail|AP)", Pattern.MULTILINE);

							Matcher bamsY1R21SubjectRegexPatternMatcher = bamsY1R21SubjectRegexPattern.matcher(text);

					while(bamsY1R21SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
						subject = bamsY1R21SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

						String theoryPaper1 = bamsY1R21SubjectRegexPatternMatcher.group(2);
						String theoryPaper2 = bamsY1R21SubjectRegexPatternMatcher.group(3);
						String totalTheoryMaxMarks = bamsY1R21SubjectRegexPatternMatcher.group(4);
						String totalTheorySecMarks = bamsY1R21SubjectRegexPatternMatcher.group(5);
						String internalAssessmentSecMark = bamsY1R21SubjectRegexPatternMatcher.group(6);
						String electiveSecMark = bamsY1R21SubjectRegexPatternMatcher.group(7);
						String practicalSecMark = bamsY1R21SubjectRegexPatternMatcher.group(8);
						String vivaSecMark = bamsY1R21SubjectRegexPatternMatcher.group(9);
						String totalPracticalMaxMarks = bamsY1R21SubjectRegexPatternMatcher.group(10);
						String totalPracticalSecMarks = bamsY1R21SubjectRegexPatternMatcher.group(11);
						String grandTotalMaxMarks = bamsY1R21SubjectRegexPatternMatcher.group(12);
						String grandTotalSecMarks = bamsY1R21SubjectRegexPatternMatcher.group(13);
						status = bamsY1R21SubjectRegexPatternMatcher.group(14);

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
											theoryPaper1, testCaseName);		
							}
							
							catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 1 marks validation for the subject " + subject +" Test case has started running");
								
								testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1,
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

								
								System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1);
													
							}
					
								try {

									PageValidation.nonValidateMarks(Regno,"Theory paper 2 marks", subject,subjectToFind,
											theoryPaper2, testCaseName);		
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
				internalAssessmentSecMark, testCaseName);		
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
					electiveSecMark, testCaseName);		
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
					practicalSecMark, testCaseName);		
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
					vivaSecMark, testCaseName);		
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
					}//Else IF_BAMS_Year1_R21
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2021")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
	System.out.println(text);
							Pattern bamsY2R21SubjectRegexPattern = Pattern.compile(
            				"^(?!Fail|Pass|AP|NE|AB|Theory|KALOJI NARAYANA RAO UNIVERSITY OF HEALTH SCIENCES|Practical|Secured Marks in Words: |Grand Total|Controller of Examinations|Principal)\\s*"+
            						"([A-Z][A-Za-z &,\\-]+(?:\\s+[A-Za-z &,\\-]+)*?(?:-[1-9])?)\\s+"
							+ "(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 2: Theory Int Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 3: Theory Int Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 4: Theory Univ Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 5: Theory Univ Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 6: Prac Int Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 7: Prac Int Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 8: Prac Univ Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 9: Prac Univ Sec
							"(\\d+|NA|NE|AB|---)\\s+" + // Group 10: Theory + Prac Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 11: Theory + Prac Sec
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 12: Grand Max
							"(\\d+|NA|NE|AB|---)(?:\\s+\\(F\\))?\\s+" + // Group 13: Grand Sec
							"(Pass|Fail|AP)", Pattern.MULTILINE);

							Matcher bamsY2R21SubjectRegexPatternMatcher = bamsY2R21SubjectRegexPattern.matcher(text);

					while(bamsY2R21SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");
						subject = bamsY2R21SubjectRegexPatternMatcher.group(1).replace("\n", " ").trim();

						String theoryPaper1 = bamsY2R21SubjectRegexPatternMatcher.group(2);
						String theoryPaper2 = bamsY2R21SubjectRegexPatternMatcher.group(3);
						String totalTheoryMaxMarks = bamsY2R21SubjectRegexPatternMatcher.group(4);
						String totalTheorySecMarks = bamsY2R21SubjectRegexPatternMatcher.group(5);
						String internalAssessmentSecMark = bamsY2R21SubjectRegexPatternMatcher.group(6);
						String electiveSecMark = bamsY2R21SubjectRegexPatternMatcher.group(7);
						String practicalSecMark = bamsY2R21SubjectRegexPatternMatcher.group(8);
						String vivaSecMark = bamsY2R21SubjectRegexPatternMatcher.group(9);
						String totalPracticalMaxMarks = bamsY2R21SubjectRegexPatternMatcher.group(10);
						String totalPracticalSecMarks = bamsY2R21SubjectRegexPatternMatcher.group(11);
						String grandTotalMaxMarks = bamsY2R21SubjectRegexPatternMatcher.group(12);
						String grandTotalSecMarks = bamsY2R21SubjectRegexPatternMatcher.group(13);
						status = bamsY2R21SubjectRegexPatternMatcher.group(14);

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
											theoryPaper1, testCaseName);		
							}
							
							catch(Exception e) {
								ExtentTest testCaseScenario1 = testCaseScenario.createNode("Theory paper 1 marks validation for the subject " + subject +" Test case has started running");
								
								testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1,
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

								
								System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Theory paper 1 marks is: " + theoryPaper1);
													
							}
					
								try {

									PageValidation.nonValidateMarks(Regno,"Theory paper 2 marks", subject,subjectToFind,
											theoryPaper2, testCaseName);		
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
				internalAssessmentSecMark, testCaseName);		
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
					electiveSecMark, testCaseName);		
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
					practicalSecMark, testCaseName);		
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
					vivaSecMark, testCaseName);		
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
					}
										
					
						
					
					
					if (page == totalPages) {
						break;
					}
				}//for
				}//try
			 catch (Exception e) {
				e.printStackTrace();
			}
		}//if
		else {
			System.out.println("No PDF file found.");
		}
	}//method		
}
