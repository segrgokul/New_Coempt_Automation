package knrEnrollmentPageReportCardPatternBased;


import java.io.File;
import java.io.IOException;
import java.util.Set;
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

public class KnrReportCard_Bsc_MLT_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	

	public void processBsc_MLTPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(B\\.?\\s*Sc\\.?\\s*MLT)\\s+";

					
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
					
					
		if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("B.Sc.MLT"))&&(semester.trim().equalsIgnoreCase("Year 1") ) &&(regulation.trim().contains("2016")) ) {
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
	
							Pattern bscMltY1R16MarksPattern = Pattern.compile(
								    "^" +
								    // Skip blank lines and footer headings
								    "(?!\\s*$)" +
								    "(?!.*(?:Secured Marks|Grand Total|Result\\s*:|Controller|Principal|KALOJI|STATEMENT OF MARKS|Registration No|Name of the Exam|Name of the College|Name of the Candidate|Date :|INSTITUTE|COLLEGE|Warangal))\\s*" +

								    // Subject Name (multi-word support)
								    "([A-Z][A-Za-z &,\\-.()]{1,}(?:\\s+[A-Za-z &,\\-.()]{1,})*)\\s*" +

								    // Optional specialization (like (F))
								    "(?:\\(([^)]+)\\))?\\s*" +

								    // Theory Internal Max
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Theory Internal Sec
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Theory Univ Max
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Theory Univ Sec
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

								    // Practical Internal Max
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Practical Internal Sec
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Practical Univ Max
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Practical Univ Sec
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +

								    // Theory + Practical Max
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
								    // Theory + Practical Sec
								    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

								    // Final Status
								    "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)\\s*$",
								    Pattern.MULTILINE
								);


						Matcher bscMltY1R16MarksPatternMatcher = bscMltY1R16MarksPattern.matcher(text);

						while (bscMltY1R16MarksPatternMatcher.find()) {
					
				
	
							try {
						System.out.println("==============");
						subject = (bscMltY1R16MarksPatternMatcher.group(2) == null) ? bscMltY1R16MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
								: (bscMltY1R16MarksPatternMatcher.group(1) + " " + bscMltY1R16MarksPatternMatcher.group(2)).replaceAll("\\s+", " ").trim();

						String theoryInternalMaxMarks = bscMltY1R16MarksPatternMatcher.group(3);
						String	theoryInternalSecMarks = bscMltY1R16MarksPatternMatcher.group(4);
						String theoryUnivMaxMarks = bscMltY1R16MarksPatternMatcher.group(5);
						String theoryUnivSecMarks = bscMltY1R16MarksPatternMatcher.group(6);
						String	practicalInternalMaxMarks = bscMltY1R16MarksPatternMatcher.group(7);
						String	practicalInternalSecMarks = bscMltY1R16MarksPatternMatcher.group(8);
						String	practicalUnivMaxMarks = bscMltY1R16MarksPatternMatcher.group(9);
						String	practicalUnivSecMarks = bscMltY1R16MarksPatternMatcher.group(10);
						String	theoryPracticalMaxMarks = bscMltY1R16MarksPatternMatcher.group(11);
						String	theoryPracticalSecMarks = bscMltY1R16MarksPatternMatcher.group(12);
						status = bscMltY1R16MarksPatternMatcher.group(13);

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
								|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {

			
					
				
								try {
									PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.35, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseName.createNode(
										  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
							}
								
							try {
								PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.40, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseName.createNode(
										  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
							}
							try {
								PageValidation.validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.35, testCaseName);		
						}
						
						catch(Exception e) {
							  ExtentTest testCaseScenario1 = testCaseName.createNode(
									  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
							  testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
						}	
							try {
								PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal,  subject,subjectToFind,status,
										grandTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
						}
						
						catch(Exception e) {
							  ExtentTest testCaseScenario1 = testCaseName.createNode(
									  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
							  testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
						}
							try {
								PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
						}
						
						catch(Exception e) {
							  ExtentTest testCaseScenario1 = testCaseName.createNode(
									  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
							  testCaseScenario1.log(Status.FAIL,
										"\n Please check The Following Registration number " + Regno
												+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
						}
						
						}//if bracket
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
			}//if Bsc_MLT_Y1_R16		
		
		else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("B.Sc.MLT"))&&(semester.trim().equalsIgnoreCase("Year 2") ) &&(regulation.trim().contains("2016")) ) {
			try {
				ExtentTest testCaseScenario = testCaseName.createNode(
						"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

				Pattern bscMltY2R16MarksPattern = Pattern.compile(
					    "^" +
					    // Skip blank lines and footer headings
					    "(?!\\s*$)" +
					    "(?!.*(?:Secured Marks|Grand Total|Result\\s*:|Controller|Principal|KALOJI|STATEMENT OF MARKS|Registration No|Name of the Exam|Name of the College|Name of the Candidate|Date :|INSTITUTE|COLLEGE|Warangal))\\s*" +

					    // Subject Name (multi-word support)
					    "([A-Z][A-Za-z &,\\-.()]{1,}(?:\\s+[A-Za-z &,\\-.()]{1,})*)\\s*" +

					    // Optional specialization (like (F))
					    "(?:\\(([^)]+)\\))?\\s*" +

					    // Theory Internal Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Internal Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Univ Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Univ Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

					    // Practical Internal Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Internal Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Univ Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Univ Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +

					    // Theory + Practical Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory + Practical Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

					    // Final Status
					    "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)\\s*$",
					    Pattern.MULTILINE
					);

			Matcher bscMltY2R16MarksPatternMatcher = bscMltY2R16MarksPattern.matcher(text);

			while (bscMltY2R16MarksPatternMatcher.find()) {


				try {
			System.out.println("==============");
			subject = (bscMltY2R16MarksPatternMatcher.group(2) == null) ? bscMltY2R16MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
					: (bscMltY2R16MarksPatternMatcher.group(1) + " " + bscMltY2R16MarksPatternMatcher.group(2)).replaceAll("\\s+", " ").trim();

			String theoryInternalMaxMarks = bscMltY2R16MarksPatternMatcher.group(3);
			String	theoryInternalSecMarks = bscMltY2R16MarksPatternMatcher.group(4);
			String theoryUnivMaxMarks = bscMltY2R16MarksPatternMatcher.group(5);
			String theoryUnivSecMarks = bscMltY2R16MarksPatternMatcher.group(6);
			String	practicalInternalMaxMarks = bscMltY2R16MarksPatternMatcher.group(7);
			String	practicalInternalSecMarks = bscMltY2R16MarksPatternMatcher.group(8);
			String	practicalUnivMaxMarks = bscMltY2R16MarksPatternMatcher.group(9);
			String	practicalUnivSecMarks = bscMltY2R16MarksPatternMatcher.group(10);
			String	theoryPracticalMaxMarks = bscMltY2R16MarksPatternMatcher.group(11);
			String	theoryPracticalSecMarks = bscMltY2R16MarksPatternMatcher.group(12);
			status = bscMltY2R16MarksPatternMatcher.group(13);

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
					|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {


		
	
					try {
						PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
								theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.35, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario1 = testCaseName.createNode(
							  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
				}
					
				try {
					PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
								theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.40, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario1 = testCaseName.createNode(
							  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
				}
				try {
					PageValidation.validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.35, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
			}	
				try {
					PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal,  subject,subjectToFind,status,
							grandTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
			}
				try {
					PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
			}
			
			}//if bracket
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
}//else if Bsc_MLT_Y2_R16	
		
		
		
		else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("B.Sc.MLT"))&&(semester.trim().equalsIgnoreCase("Year 3") ) &&(regulation.trim().contains("2016")) ) {
			try {
				ExtentTest testCaseScenario = testCaseName.createNode(
						"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

				Pattern bscMltY3R16MarksPattern = Pattern.compile(
					    "^" +
					    // Skip blank lines and footer headings
					    "(?!\\s*$)" +
					    "(?!.*(?:Secured Marks|Grand Total|Result\\s*:|Controller|Principal|KALOJI|STATEMENT OF MARKS|Registration No|Name of the Exam|Name of the College|Name of the Candidate|Date :|INSTITUTE|COLLEGE|Warangal))\\s*" +

					    // Subject Name (multi-word support)
					    "([A-Z][A-Za-z &,\\-.()]{1,}(?:\\s+[A-Za-z &,\\-.()]{1,})*)\\s*" +

					    // Optional specialization (like (F))
					    "(?:\\(([^)]+)\\))?\\s*" +

					    // Theory Internal Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Internal Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Univ Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory Univ Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

					    // Practical Internal Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Internal Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Univ Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Practical Univ Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +

					    // Theory + Practical Max
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*" +
					    // Theory + Practical Sec
					    "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*" +

					    // Final Status
					    "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)\\s*$",
					    Pattern.MULTILINE
					);


				System.out.println("text" + text);
			Matcher bscMltY3R16MarksPatternMatcher = bscMltY3R16MarksPattern.matcher(text);

			while (bscMltY3R16MarksPatternMatcher.find()) {
		

				try {
			System.out.println("==============");
			subject = (bscMltY3R16MarksPatternMatcher.group(2) == null) ? bscMltY3R16MarksPatternMatcher.group(1).replaceAll("\\s+", " ").trim()
					: (bscMltY3R16MarksPatternMatcher.group(1) + " " + bscMltY3R16MarksPatternMatcher.group(2)).replaceAll("\\s+", " ").trim();

			String theoryInternalMaxMarks = bscMltY3R16MarksPatternMatcher.group(3);
			String	theoryInternalSecMarks = bscMltY3R16MarksPatternMatcher.group(4);
			String theoryUnivMaxMarks = bscMltY3R16MarksPatternMatcher.group(5);
			String theoryUnivSecMarks = bscMltY3R16MarksPatternMatcher.group(6);
			String	practicalInternalMaxMarks = bscMltY3R16MarksPatternMatcher.group(7);
			String	practicalInternalSecMarks = bscMltY3R16MarksPatternMatcher.group(8);
			String	practicalUnivMaxMarks = bscMltY3R16MarksPatternMatcher.group(9);
			String	practicalUnivSecMarks = bscMltY3R16MarksPatternMatcher.group(10);
			String	theoryPracticalMaxMarks = bscMltY3R16MarksPatternMatcher.group(11);
			String	theoryPracticalSecMarks = bscMltY3R16MarksPatternMatcher.group(12);
			status = bscMltY3R16MarksPatternMatcher.group(13);

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
					|| status.trim().equals("AP")) && subject.equals(subjectToFind)) {


		
	
					try {
						PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
								theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theoryInternalSecMarks, theoryInternalMaxMarks, 0.35, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario1 = testCaseName.createNode(
							  " Theory Internal Sec. Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Theory internal sec. Marks: " + theoryInternalSecMarks);							
				}
					
				try {
					PageValidation.validateMarks(Regno,"Paper2 Sec Marks", paper1, paper2, paper3,paper4,
								theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
								grandTotal, theoryUnivSecMarks, theoryUnivMaxMarks, 0.40, testCaseName);		
				}
				
				catch(Exception e) {
					  ExtentTest testCaseScenario1 = testCaseName.createNode(
							  " Theory Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
					  testCaseScenario1.log(Status.FAIL,
								"\n Please check The Following Registration number " + Regno
										+ "Theory Univ sec. Marks: " + theoryUnivSecMarks);							
				}
				try {
					PageValidation.validateMarks(Regno,"Paper3 Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, practicalInternalSecMarks, practicalInternalMaxMarks, 0.35, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical internal sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical internal sec. Marks: " + practicalInternalSecMarks);							
			}	
				try {
					PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal,  subject,subjectToFind,status,
							grandTotal, practicalUnivSecMarks, practicalUnivMaxMarks, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical Univ sec. Marks: " + practicalUnivSecMarks);							
			}
				try {
					PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, theoryPracticalSecMarks, theoryPracticalMaxMarks, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseName.createNode(
						  " Practical Univ sec. Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Practical Univ sec. Marks: " + theoryPracticalSecMarks);							
			}
			
			}//if bracket
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
}//else if Bsc_MLT_Y3_R16	
		
		
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