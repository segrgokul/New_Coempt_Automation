
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
import knrPageModules.KnrRportEnrollmentPaperSecValidation;

public class KnrReportCard_BHMS_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	
	
	public void processBHMSPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(BHMS)\\s*";

					
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
					
					
					
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BHMS"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							System.out.println(text);
					
	
							Pattern bhmsY1R16SubjectRegexPattern = Pattern.compile(
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


			
					Matcher bhmsY1R16SubjectRegexPatternMatcher = bhmsY1R16SubjectRegexPattern.matcher(text);		
		
					while(bhmsY1R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						System.out.println("==============");

						String subjectCHeck =bhmsY1R16SubjectRegexPatternMatcher.group(1);
						String sub =bhmsY1R16SubjectRegexPatternMatcher.group(2);
						
						System.out.println(sub + " " +subjectCHeck);
						
//								subject = fourPatternMatcher.group(1).trim();
						subject = (bhmsY1R16SubjectRegexPatternMatcher.group(2) == null)
								? bhmsY1R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
								: (bhmsY1R16SubjectRegexPatternMatcher.group(1) + " " + bhmsY1R16SubjectRegexPatternMatcher.group(2))
										.replaceAll("\\s+", " ").trim();
						
						String theoryMaxMarks = bhmsY1R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	theorySecMarks = bhmsY1R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalMaxMarks = bhmsY1R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = bhmsY1R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	vivaMaxMarks = bhmsY1R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String vivaSecMarks = bhmsY1R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = bhmsY1R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalSecMarks = bhmsY1R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = bhmsY1R16SubjectRegexPatternMatcher.group(11);
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
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BHMS"))&&(semester.trim().equalsIgnoreCase("Year 3") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					

							Pattern bhmsY3R16SubjectRegexPattern = Pattern.compile(
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


						Matcher bhmsY3R16SubjectRegexPatternMatcher = bhmsY3R16SubjectRegexPattern.matcher(text);		
			
						while(bhmsY3R16SubjectRegexPatternMatcher.find()) {
							try {
							testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

							System.out.println("==============");

							String subjectCHeck =bhmsY3R16SubjectRegexPatternMatcher.group(1);
							String sub =bhmsY3R16SubjectRegexPatternMatcher.group(2);
							
							System.out.println(sub + " " +subjectCHeck);
							
//									subject = fourPatternMatcher.group(1).trim();
							subject = (bhmsY3R16SubjectRegexPatternMatcher.group(2) == null)
									? bhmsY3R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (bhmsY3R16SubjectRegexPatternMatcher.group(1) + " " + bhmsY3R16SubjectRegexPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();
							
							String theoryMaxMarks = bhmsY3R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part

							String	theorySecMarks = bhmsY3R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalMaxMarks = bhmsY3R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalSecMarks = bhmsY3R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F);

							String	vivaMaxMarks = bhmsY3R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String vivaSecMarks = bhmsY3R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalMaxMarks = bhmsY3R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalSecMarks = bhmsY3R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							status = bhmsY3R16SubjectRegexPatternMatcher.group(11);
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
					
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BHMS"))&&(semester.trim().equalsIgnoreCase("Year 4") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
							Pattern bhmsY4R16SubjectRegexPattern = Pattern.compile(
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


						Matcher bhmsY4R16SubjectRegexPatternMatcher = bhmsY4R16SubjectRegexPattern.matcher(text);		
			
						while(bhmsY4R16SubjectRegexPatternMatcher.find()) {
							try {
							testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

							System.out.println("==============");

							String subjectCHeck =bhmsY4R16SubjectRegexPatternMatcher.group(1);
							String sub =bhmsY4R16SubjectRegexPatternMatcher.group(2);
							
							System.out.println(sub + " " +subjectCHeck);
							
//									subject = fourPatternMatcher.group(1).trim();
							subject = (bhmsY4R16SubjectRegexPatternMatcher.group(2) == null)
									? bhmsY4R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
									: (bhmsY4R16SubjectRegexPatternMatcher.group(1) + " " + bhmsY4R16SubjectRegexPatternMatcher.group(2))
											.replaceAll("\\s+", " ").trim();
							
							String theoryMaxMarks = bhmsY4R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part

							String	theorySecMarks = bhmsY4R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalMaxMarks = bhmsY4R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String praticalSecMarks = bhmsY4R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F);

							String	vivaMaxMarks = bhmsY4R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String vivaSecMarks = bhmsY4R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalMaxMarks = bhmsY4R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							String grandTotalSecMarks = bhmsY4R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F);
							status = bhmsY4R16SubjectRegexPatternMatcher.group(11);
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
					}
										
					else	if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BHMS"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2022")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
						
						Pattern bhms22MarksPattern = Pattern.compile(
								
								
//								"^([\\s\\S]*?)\\s+" + // 1. Subject Name
							//	"^([A-Z][A-Z\\s\\-&()\\.]+?)\\s+" +  // New: only captures uppercase subject lines
								"^(?!.*Theory Marks Practical or Clinical Marks Result Max Obtained Sub total)" + // Skip lines with this header
								"([A-Z][A-Z\\s\\-&()\\.]+?)\\s+" + 
								"(HomUG-[A-Z]+(?:-[A-Z]+)?)\\s+" + // 2. Subject Code
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 3. Mark 1
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 4. Mark 2
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 5. Mark 3
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 6. Mark 4
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 7. Mark 5
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 8. Mark 6
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 9. Mark 7
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 10. Mark 8
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 11. Mark 9
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 12. Mark 10
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)\\s+" + // 13. Mark 11
								"(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*)" + // 14. Mark 12
								"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 15. Mark 13 (optional)
								"(?:\\s+(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\)|\\*))?" + // 16. Mark 14 (optional)
								"(?:\\s+([A-Z]))?" + // 17. Optional grade letter
								"\\s+(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$", // 18. Final Result
								Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

						Matcher bhms22MarksPatternMatcher = bhms22MarksPattern.matcher(text);
					
						while (bhms22MarksPatternMatcher.find()) {
							try {
							
							if(bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim().contains("Theory Marks Practical or Clinical Marks Result Max Obtained Sub total")) {
								subject =  bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").replaceAll(
									    "Theory Marks Practical or Clinical Marks Result Max Obtained Sub total", ""
										).trim();
								
							}
							else {
								subject = bhms22MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim();
							}
							
							String subjectCode =bhms22MarksPatternMatcher.group(2);
							String theoryMaxMarks = bhms22MarksPatternMatcher.group(3);
							String theoryMinMarks = bhms22MarksPatternMatcher.group(4);
							String theoryObtainedMarks = bhms22MarksPatternMatcher.group(5);
							String practicalMaxPracticalMarks = bhms22MarksPatternMatcher.group(6);
							String practicalMaxVivaMarks = bhms22MarksPatternMatcher.group(7);
							String practicalMaxIAMarks = bhms22MarksPatternMatcher.group(8);
							String practicalObtainedPracticalMarks = bhms22MarksPatternMatcher.group(9);
							String practicalObtainedVivaMarks = bhms22MarksPatternMatcher.group(10);
							String practicalObtainedIAMarks = bhms22MarksPatternMatcher.group(11);
							String subTotalMaxMarks = bhms22MarksPatternMatcher.group(12);
							String subTotalMinMarks= bhms22MarksPatternMatcher.group(13);
							String subTotalObtainedMarks = bhms22MarksPatternMatcher.group(14);
							String subjectTotal = bhms22MarksPatternMatcher.group(15);
							String finalResult2 = bhms22MarksPatternMatcher.group(16);
							String finalResult3 = bhms22MarksPatternMatcher.group(17);
							status= bhms22MarksPatternMatcher.group(18);
							String finalTheoryObtainedMarks =null;
							String finalSubjectTotal = null;
							System.out.println("=====================================================================");
							
							System.out.println("Subject                     : " + subject);
							System.out.println("Subject Code                : " + subjectCode);
							System.out.println("Theory Max Marks            : " + theoryMaxMarks);
							System.out.println("Theory Min Marks            : " + theoryMinMarks);
							System.out.println("Theory Obtained Marks       : " + theoryObtainedMarks);
							System.out.println("Practical Max Practical Marks     : " + practicalMaxPracticalMarks);
							System.out.println("Practical Max Viva Marks          : " + practicalMaxVivaMarks);
							System.out.println("Practical Max IA Marks      : " + practicalMaxIAMarks);
							System.out.println("Practical Obtained Practical Marks: " + practicalObtainedPracticalMarks);
							System.out.println("Practical Obtained Viva Marks     : " + practicalObtainedVivaMarks);
							System.out.println("Practical Obtained IA Marks : " + practicalObtainedIAMarks);
							System.out.println("Sub Total Max Marks         : " + subTotalMaxMarks);
							System.out.println("Sub Total Min Marks          : " + subTotalMinMarks);
							System.out.println("Sub Total obtained Marks      : " + subTotalObtainedMarks);
							System.out.println("Subject Total      : " + subjectTotal);
							System.out.println("Final Result2      : " + finalResult2);
							System.out.println("Final Result3      : " + finalResult3);
							System.out.println("Final Result4      : " + status);
		
							
							Pattern pattern = Pattern.compile("Elective-\\d+\\s+(.*)\\s+([A-F])$",
	                             Pattern.MULTILINE);
							   Matcher matcher = pattern.matcher(text);
							while (matcher.find()) {
								
								String elective = matcher.group(0).trim();
								String subject = matcher.group(1).trim();
								String grade = matcher.group(2);
								
								System.out.println("Elective: " +elective);
								System.out.println("Elective Subject: " + subject ); 
								System.out.println("Grade: " +grade);
									
							}
							System.out.println("=====================================================================");
							
							try {
							if(!finalResult3.equals("null")) {
								
								finalTheoryObtainedMarks = theoryObtainedMarks+finalResult3;
								
								finalSubjectTotal = subjectTotal+finalResult3;
								
								System.out.println("finalTheoryObtainedMarks: " +finalTheoryObtainedMarks);
								System.out.println("finalSubjectTotal: " + finalSubjectTotal);
								}
							}
							catch(Exception e) {
									finalTheoryObtainedMarks = theoryObtainedMarks;								
									finalSubjectTotal =subjectTotal;
									
									System.out.println("finalTheoryObtainedMarks: " +finalTheoryObtainedMarks);
									System.out.println("finalSubjectTotal: " + finalSubjectTotal);

								}
							

							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) & subject.equals(subjectToFind)) {

								String checkingForNeededGraceMarks;
								
											try {

												if (finalTheoryObtainedMarks.contains("G")&& finalSubjectTotal.contains("G")) {
													PageValidation.validateMarks(Regno,"Paper1 Grace Sec Marks", paper1, paper2, paper3,paper4,
															theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
															grandTotal, finalTheoryObtainedMarks, theoryMaxMarks, 0.50, testCaseName);
													
													//	
													
												}else {
													PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
															theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
															grandTotal, theoryObtainedMarks, theoryMaxMarks, 0.50, testCaseName);		
												}
												
													}
											catch(Exception e) {
												ExtentTest testCaseScenario1 = testCaseScenario
														.createNode("Paper1 Grace Sec Marks "
											+ subject + " Test case has started running");		
												testCaseScenario1.log(Status.FAIL,
														"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ finalTheoryObtainedMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
											}
		
											
										if (finalTheoryObtainedMarks.contains("G")&& finalSubjectTotal.contains("G")) {
													 
									   double	 checkingForNeededGraceMark =  Double.parseDouble(theoryMinMarks) - Double.parseDouble(theoryObtainedMarks);
													 checkingForNeededGraceMarks = String.valueOf(checkingForNeededGraceMark);
							
													 PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
																theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
																grandTotal, checkingForNeededGraceMarks, theoryMaxMarks, 0.00, testCaseName);	
															}
										
									
													
											
												
										
										try {

											PageValidation.nonValidateMarks(Regno,"Practical Obtained Pratical Sec. Marks", subject,subjectToFind,
													practicalObtainedPracticalMarks,practicalMaxPracticalMarks,0.0, testCaseName);		
									}	

									catch(Exception e) {
										ExtentTest testCaseScenario1 = testCaseName
												.createNode("Practical Obtained Pratical Sec. Marks Validation for the Subject "
														+ subject + " Test case has started running");
										testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Obtained Pratical Sec. Marks is: " + practicalObtainedPracticalMarks,
												MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

										
										System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Obtained Pratical Sec. Marks is: " + practicalObtainedPracticalMarks);
															
									}				
										
										try {

											PageValidation.nonValidateMarks(Regno,"Practical Obtained Viva Sec. Marks", subject,subjectToFind,
													practicalObtainedVivaMarks,practicalMaxVivaMarks,0.0, testCaseName);		
									}	

									catch(Exception e) {
										ExtentTest testCaseScenario1 = testCaseName
												.createNode("Practical Obtained Viva Sec. Marks Validation for the Subject "
														+ subject + " Test case has started running");
										testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Obtained Viva Sec. Marks is: " + practicalObtainedVivaMarks,
												MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

										
										System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Practical Obtained Viva Sec. Marks is: " + practicalObtainedVivaMarks);
															
									}		
										
										try {
											PageValidation.validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, practicalObtainedIAMarks, practicalMaxIAMarks, 0.50, testCaseName);		


										}
										catch(Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Paper3 Sec Marks "
										+ subject + " Test case has started running");		
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory sec mark is: "+ practicalObtainedIAMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
					}
										try {
											
											PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
													theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
													grandTotal, subTotalObtainedMarks, subTotalMaxMarks, 0.50, testCaseName);		


										}
										catch(Exception e) {
											ExtentTest testCaseScenario1 = testCaseScenario
													.createNode("Pratical Sec. Marks "
										+ subject + " Test case has started running");		
											testCaseScenario1.log(Status.FAIL,
													"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of pravtical sec mark is: "+ subTotalObtainedMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

									}
	
										 try {
											 
											 String grandTotalMaxMarks;
											 if (finalSubjectTotal.contains("G")) {
													
													double grandTotalMaxMark = Double.parseDouble(theoryMaxMarks) + Double.parseDouble(subTotalMaxMarks) ;
													
													grandTotalMaxMarks = String.valueOf(grandTotalMaxMark);
												
											 }else {
													double grandTotalMaxMark = Double.parseDouble(theoryMaxMarks) + Double.parseDouble(subTotalMaxMarks) ;
													grandTotalMaxMarks = String.valueOf(grandTotalMaxMark);
									
											 }
											
												if (finalTheoryObtainedMarks.contains("G")&& finalSubjectTotal.contains("G")) {
													PageValidation.validateMarks(Regno,"Grand Total Grace Sec Marks", paper1, paper2, paper3,paper4,
															theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
															grandTotal, finalSubjectTotal, grandTotalMaxMarks, 0.50, testCaseName);
													
													//	
													
												}else {
													PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
															theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
															grandTotal, subjectTotal, grandTotalMaxMarks, 0.50, testCaseName);		
												}
												
										    	
											}
											
											catch(Exception e) {
												  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
														  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
												  testCaseScenario1.log(Status.FAIL,
															"\n Please check The Following Registration number " + Regno
																	+ "Grand Total Sec Marks"  + subjectTotal,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

																			
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
				testCaseName.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
		}//if
		else {
			System.out.println("No PDF file found.");
		}
	}//method		
}
