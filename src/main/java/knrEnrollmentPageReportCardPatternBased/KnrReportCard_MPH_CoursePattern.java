
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

public class KnrReportCard_MPH_CoursePattern  extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

	public void processMPHPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

				
					String courseNameRegex = "(?i)(M\\.P\\.H)\\s*";
					
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
					
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.P.H"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							System.out.println(text);
					
	
							Pattern mphY1R16SubjectRegexPattern = Pattern.compile(
				        				
											"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
											+"([A-Z &'\\-\\(\\),]+(?:\\n[A-Z &'\\-/(\\),]+)*)\\s+(?:\\(([^)]+)\\))?"+
					
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 3: Theory Max
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 4: Theory Sec
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 5: Practical
																											// Max Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 6: Practical
																											// Sec Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 7: Viva Max
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 8: Viva Sec
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 9: Grand Total
																											// Max Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s*" + // Group 10: Grand
																											// Total Sec Marks
											"\\b(Pass|Fail|AP|NE|AB)\\b", // Group 11: Status (at the END)
									Pattern.MULTILINE); // Enables multi-line matching


			
					Matcher mphY1R16SubjectRegexPatternMatcher = mphY1R16SubjectRegexPattern.matcher(text);		
		
					while(mphY1R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						
//								subject = fourPatternMatcher.group(1).trim();
						subject = (mphY1R16SubjectRegexPatternMatcher.group(2) == null)
								? mphY1R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
								: (mphY1R16SubjectRegexPatternMatcher.group(1) + " " + mphY1R16SubjectRegexPatternMatcher.group(2))
										.replaceAll("\\s+", " ").trim();
						
						String theoryMaxMarks = mphY1R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	theorySecMarks = mphY1R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalMaxMarks = mphY1R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = mphY1R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	internalMaxMarks = mphY1R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String internalSecMarks = mphY1R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = mphY1R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalSecMarks = mphY1R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = mphY1R16SubjectRegexPatternMatcher.group(11);
						System.out.println("==============");

						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

							System.out.println("Practical Sec Marks: " + praticalSecMarks);
						// Practical Total Marks
						System.out.println("Viva Max Marks: " + internalMaxMarks);
						System.out.println("Viva Total Sec Marks: " + internalSecMarks);

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
									grandTotal,  internalSecMarks,  internalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Viva Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Viva sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
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
			}//IF_MPH_Year1
				
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.P.H"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
							System.out.println(text);
					
	
							Pattern mphY2R16SubjectRegexPattern = Pattern.compile(
				        				
											"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
											+"([A-Z &'\\-\\(\\),]+(?:\\n[A-Z &'\\-/(\\),]+)*)\\s+(?:\\(([^)]+)\\))?"+
					
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 3: Theory Max
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 4: Theory Sec
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 5: Practical
																											// Max Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 6: Practical
																											// Sec Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 7: Viva Max
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 8: Viva Sec
																											// Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s+" + // Group 9: Grand Total
																											// Max Marks
											"(\\d+|F|---|NE\\s*\\(AT\\)|NE|NA|AB)(?:\\s*\\(F\\))?\\s*" + // Group 10: Grand
																											// Total Sec Marks
											"\\b(Pass|Fail|AP|NE|AB)\\b", // Group 11: Status (at the END)
									Pattern.MULTILINE); // Enables multi-line matching


			
					Matcher mphY2R16SubjectRegexPatternMatcher = mphY2R16SubjectRegexPattern.matcher(text);		
		
					while(mphY2R16SubjectRegexPatternMatcher.find()) {
						try {
						testCaseScenario.log(Status.PASS, "Subject Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " has matched");

						
//								subject = fourPatternMatcher.group(1).trim();
						subject = (mphY2R16SubjectRegexPatternMatcher.group(2) == null)
								? mphY2R16SubjectRegexPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
								: (mphY2R16SubjectRegexPatternMatcher.group(1) + " " + mphY2R16SubjectRegexPatternMatcher.group(2))
										.replaceAll("\\s+", " ").trim();
						
						String theoryMaxMarks = mphY2R16SubjectRegexPatternMatcher.group(3).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part; // Take the last part

						String	theorySecMarks = mphY2R16SubjectRegexPatternMatcher.group(4).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalMaxMarks = mphY2R16SubjectRegexPatternMatcher.group(5).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String praticalSecMarks = mphY2R16SubjectRegexPatternMatcher.group(6).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;

						String	internalMaxMarks = mphY2R16SubjectRegexPatternMatcher.group(7).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String internalSecMarks = mphY2R16SubjectRegexPatternMatcher.group(8).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalMaxMarks = mphY2R16SubjectRegexPatternMatcher.group(9).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						String grandTotalSecMarks = mphY2R16SubjectRegexPatternMatcher.group(10).replaceAll("\\s*\\(F\\)", ""); // Removes (F); // Take the last part;
						status = mphY2R16SubjectRegexPatternMatcher.group(11);
						System.out.println("==============");

						System.out.println("Subject: " + subject); // Subject name + Theory Max Marks
						System.out.println("Theory Max Marks: " + theoryMaxMarks);

						System.out.println("Theory Sec Marks: " + theorySecMarks);
						System.out.println("Practical Max Marks: " + praticalMaxMarks);

							System.out.println("Practical Sec Marks: " + praticalSecMarks);
						// Practical Total Marks
						System.out.println("Viva Max Marks: " + internalMaxMarks);
						System.out.println("Viva Total Sec Marks: " + internalSecMarks);

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
									grandTotal,  internalSecMarks,  internalMaxMarks, 0.50, testCaseName);		


						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Viva Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of Viva sec mark is: "+ theorySecMarks ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
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
