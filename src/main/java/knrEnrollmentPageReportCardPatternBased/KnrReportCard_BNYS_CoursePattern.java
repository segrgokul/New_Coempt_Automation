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

public class KnrReportCard_BNYS_CoursePattern extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();
	
	
	public void processBNYSPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

					String courseNameRegex = "(?i)(BNYS)\\s+";

					
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
					
					if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 1 Part-I" )) &&(regulation.trim().contains("2016")) ) {
					
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					
					
				     Pattern bnysY1P1R16MarksPattern =	Pattern.compile(
						     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
			   			
			   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   				
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   				
						    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
			   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
			   			    Pattern.DOTALL | Pattern.MULTILINE
			   			);
					
					Matcher bnysY1P1R16MarksPatternMatcher = bnysY1P1R16MarksPattern.matcher(text);
					
					while(bnysY1P1R16MarksPatternMatcher.find()){
					
					
					subject =  bnysY1P1R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
					String intMaxMarks = bnysY1P1R16MarksPatternMatcher.group(2);
					String intSecMarks = bnysY1P1R16MarksPatternMatcher.group(3);
					String p1 =bnysY1P1R16MarksPatternMatcher.group(4);
					String p2 =bnysY1P1R16MarksPatternMatcher.group(5);
					   String theoryThMaxMark = bnysY1P1R16MarksPatternMatcher.group(6);
						   String theoryThSecMark = bnysY1P1R16MarksPatternMatcher.group(7);
						   String theoryThPlusIntMaxMark = bnysY1P1R16MarksPatternMatcher.group(8);
						   String theoryThPlusIntSecMark = bnysY1P1R16MarksPatternMatcher.group(9);
						  
						   String practicalPractical = bnysY1P1R16MarksPatternMatcher.group(10);
						   String practicalViva = bnysY1P1R16MarksPatternMatcher.group(11);
						   String practicalPlusVivaMaxMark = bnysY1P1R16MarksPatternMatcher.group(12);
						   String practicalPlusVivaSecMark = bnysY1P1R16MarksPatternMatcher.group(13);				   
				
						  String theoryPlusPracticalMaxMark =  bnysY1P1R16MarksPatternMatcher.group(14);
						  String theoryPlusPracticalSecMark = bnysY1P1R16MarksPatternMatcher.group(15);
							status =	 bnysY1P1R16MarksPatternMatcher.group(16); 

			        
				    System.out.println("==============");
							    
			        System.out.println("Subject: " + subject);
			        
			        System.out.println("Int Max Marks: " + intMaxMarks);
			        System.out.println("Int Sec Marks: " + intSecMarks);
			        System.out.println("P1: " + p1);
			        System.out.println("P2: " + p2);
					System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
					System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
					System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
					System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
					System.out.println("Practical Practical: " + practicalPractical);
					System.out.println("Practical Viva: " + practicalViva);
					System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
					System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
					System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
					System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
					System.out.println("Result: " + status);
					System.out.println("==============");
					
					
					
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
					
						
						try {

							PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
									intSecMarks,intMaxMarks,0.35, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
											
					}
						
						
						
						
						
						try {

							PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
									p1,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
									p2,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
											
					}
						
					
						
						
						try {
							
						
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(p2.trim())) {
							
								
								System.out.println(p1);
							double p1Mark = Double.parseDouble(p1);
							double p2Mark = Double.parseDouble(p2);
							double intMark = Double.parseDouble(intSecMarks);							
							if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
							
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


						}
							else 	if (invalidValues.contains(p2.trim())) {
								System.out.println(p1);
								double p1Mark = Double.parseDouble(p1);
								double p2Mark = 0.0;
								double intMark = Double.parseDouble(intSecMarks);					
								
								if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
									
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.PASS,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								else {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								
								
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
													"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory plus int Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						try {

							PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
									practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
									practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
											
					}
						
						
						try {
							
							
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(practicalViva.trim())) {
							
								
					
							double practicalPracticalMark = Double.parseDouble(practicalPractical);
							double praticalVivaMark = Double.parseDouble(practicalViva);
										
							if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started srunning");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
						
							}
							
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


						}
								
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
									
								
								
								testCaseScenario1.log(Status.FAIL,
										"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory plus int Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						
						 try {
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
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
					}//BNYS_Y1_Part-1_R16
					
					
					else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 1 Part-II" )) &&(regulation.trim().contains("2016")) ) {
						
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					
					
							
							
						     Pattern bnysY1P2R16MarksPattern =	Pattern.compile(
								     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
					   			
					   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

											+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   				
											+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   				
								    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
					   			    Pattern.DOTALL | Pattern.MULTILINE
					   			);
							
							Matcher bnysY1P2R16MarksPatternMatcher = bnysY1P2R16MarksPattern.matcher(text);
							
							while(bnysY1P2R16MarksPatternMatcher.find()){
							
							
							subject =  bnysY1P2R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
							String intMaxMarks = bnysY1P2R16MarksPatternMatcher.group(2);
							String intSecMarks = bnysY1P2R16MarksPatternMatcher.group(3);
							String p1 =bnysY1P2R16MarksPatternMatcher.group(4);
							String p2 =bnysY1P2R16MarksPatternMatcher.group(5);
							   String theoryThMaxMark = bnysY1P2R16MarksPatternMatcher.group(6);
								   String theoryThSecMark = bnysY1P2R16MarksPatternMatcher.group(7);
								   String theoryThPlusIntMaxMark = bnysY1P2R16MarksPatternMatcher.group(8);
								   String theoryThPlusIntSecMark = bnysY1P2R16MarksPatternMatcher.group(9);
								  
								   String practicalPractical = bnysY1P2R16MarksPatternMatcher.group(10);
								   String practicalViva = bnysY1P2R16MarksPatternMatcher.group(11);
								   String practicalPlusVivaMaxMark = bnysY1P2R16MarksPatternMatcher.group(12);
								   String practicalPlusVivaSecMark = bnysY1P2R16MarksPatternMatcher.group(13);				   
						
								  String theoryPlusPracticalMaxMark =  bnysY1P2R16MarksPatternMatcher.group(14);
								  String theoryPlusPracticalSecMark = bnysY1P2R16MarksPatternMatcher.group(15);
									status =	 bnysY1P2R16MarksPatternMatcher.group(16); 
			        
				    System.out.println("==============");
							    
			        System.out.println("Subject: " + subject);
			        
			        System.out.println("Int Max Marks: " + intMaxMarks);
			        System.out.println("Int Sec Marks: " + intSecMarks);
			        System.out.println("P1: " + p1);
			        System.out.println("P2: " + p2);
					System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
					System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
					System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
					System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
					System.out.println("Practical Practical: " + practicalPractical);
					System.out.println("Practical Viva: " + practicalViva);
					System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
					System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
					System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
					System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
					System.out.println("Result: " + status);
					System.out.println("==============");
					
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
					
						
						try {

							PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
									intSecMarks,intMaxMarks,0.35, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
											
					}
						
						
						
						
						
						try {

							PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
									p1,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
									p2,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
											
					}
						
					
						
						
						try {
							
						
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(p2.trim())) {
							
								
								System.out.println(p1);
							double p1Mark = Double.parseDouble(p1);
							double p2Mark = Double.parseDouble(p2);
							double intMark = Double.parseDouble(intSecMarks);							
							if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
							
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


						}
							else 	if (invalidValues.contains(p2.trim())) {
								System.out.println(p1);
								double p1Mark = Double.parseDouble(p1);
								double p2Mark = 0.0;
								double intMark = Double.parseDouble(intSecMarks);					
								
								if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
									
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.PASS,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								else {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								
								
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
													"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Paper 1 Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						try {

							PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
									practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
									practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
											
					}
						
						
						try {
							
							
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(practicalViva.trim())) {
							
								
					
							double practicalPracticalMark = Double.parseDouble(practicalPractical);
							double praticalVivaMark = Double.parseDouble(practicalViva);
										
							if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started srunning");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
						
							}
							
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


						}
								
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
									
								
								
								testCaseScenario1.log(Status.FAIL,
										"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory plus int Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						
						 try {
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
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
					
						
					
					}//else if BNYS_Y1_P2_R16
					
	else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 2 Part-I" )) &&(regulation.trim().contains("2016")) ) {
						
						try {
							ExtentTest testCaseScenario = testCaseName.createNode(
									"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");
				
					
					
							
							
						     Pattern bnysY2P1R16MarksPattern =	Pattern.compile(
								     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
					   			
					   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

											+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   				
											+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   				
								    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
					   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
					   			    Pattern.DOTALL | Pattern.MULTILINE
					   			);
							
							Matcher bnysY2P1R16MarksPatternMatcher = bnysY2P1R16MarksPattern.matcher(text);
							
							while(bnysY2P1R16MarksPatternMatcher.find()){
							
							
							subject =  bnysY2P1R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
							String intMaxMarks = bnysY2P1R16MarksPatternMatcher.group(2);
							String intSecMarks = bnysY2P1R16MarksPatternMatcher.group(3);
							String p1 =bnysY2P1R16MarksPatternMatcher.group(4);
							String p2 =bnysY2P1R16MarksPatternMatcher.group(5);
							   String theoryThMaxMark = bnysY2P1R16MarksPatternMatcher.group(6);
								   String theoryThSecMark = bnysY2P1R16MarksPatternMatcher.group(7);
								   String theoryThPlusIntMaxMark = bnysY2P1R16MarksPatternMatcher.group(8);
								   String theoryThPlusIntSecMark = bnysY2P1R16MarksPatternMatcher.group(9);
								  
								   String practicalPractical = bnysY2P1R16MarksPatternMatcher.group(10);
								   String practicalViva = bnysY2P1R16MarksPatternMatcher.group(11);
								   String practicalPlusVivaMaxMark = bnysY2P1R16MarksPatternMatcher.group(12);
								   String practicalPlusVivaSecMark = bnysY2P1R16MarksPatternMatcher.group(13);				   
						
								  String theoryPlusPracticalMaxMark =  bnysY2P1R16MarksPatternMatcher.group(14);
								  String theoryPlusPracticalSecMark = bnysY2P1R16MarksPatternMatcher.group(15);
									status =	 bnysY2P1R16MarksPatternMatcher.group(16); 
			        
				    System.out.println("==============");
							    
			        System.out.println("Subject: " + subject);
			        
			        System.out.println("Int Max Marks: " + intMaxMarks);
			        System.out.println("Int Sec Marks: " + intSecMarks);
			        System.out.println("P1: " + p1);
			        System.out.println("P2: " + p2);
					System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
					System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
					System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
					System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
					System.out.println("Practical Practical: " + practicalPractical);
					System.out.println("Practical Viva: " + practicalViva);
					System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
					System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
					System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
					System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
					System.out.println("Result: " + status);
					System.out.println("==============");
					
					if ((status.trim().equals("Pass") || status.trim().equals("Fail")
							|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
					
						
						try {

							PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
									intSecMarks,intMaxMarks,0.35, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
											
					}
						
						
						
						
						
						try {

							PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
									p1,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
									p2,theoryThMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
											
					}
						
					
						
						
						try {
							
						
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(p2.trim())) {
							
								
								System.out.println(p1);
							double p1Mark = Double.parseDouble(p1);
							double p2Mark = Double.parseDouble(p2);
							double intMark = Double.parseDouble(intSecMarks);							
							if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
							
							PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


						}
							else 	if (invalidValues.contains(p2.trim())) {
								System.out.println(p1);
								double p1Mark = Double.parseDouble(p1);
								double p2Mark = 0.0;
								double intMark = Double.parseDouble(intSecMarks);					
								
								if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
									
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									
									testCaseScenario1.log(Status.PASS,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								else {
									ExtentTest testCaseScenario1 = testCaseScenario
											.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
								+ subject + " Test case has started running");		
									testCaseScenario1.log(Status.FAIL,
											"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				
								}
								
								
								PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
										theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
										grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
													"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Paper 1 Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						try {

							PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
									practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
											
					}
			
						try {

							PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
									practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
					}
					
					catch(Exception e) {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
						
						testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						
						System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
											
					}
						
						
						try {
							
							
							Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
							if (!invalidValues.contains(practicalViva.trim())) {
							
								
					
							double practicalPracticalMark = Double.parseDouble(practicalPractical);
							double praticalVivaMark = Double.parseDouble(practicalViva);
										
							if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
								
								
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started srunning");		
								
								testCaseScenario1.log(Status.PASS,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
								
								testCaseScenario1.log(Status.FAIL,
										"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
						
							}
							
							
							PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
									theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
									grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


						}
								
							else {
								ExtentTest testCaseScenario1 = testCaseScenario
										.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
							+ subject + " Test case has started running");		
									
								
								
								testCaseScenario1.log(Status.FAIL,
										"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			
							}
							
						
						}
						catch(Exception e) {
							ExtentTest testCaseScenario1 = testCaseScenario
									.createNode("Theory plus int Sec. Marks "
						+ subject + " Test case has started running");		
							testCaseScenario1.log(Status.FAIL,
									"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
	}
						
						
						 try {
						    	

						    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
											theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
											grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
							}
							
							catch(Exception e) {
								  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
										  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
								  testCaseScenario1.log(Status.FAIL,
											"\n Please check The Following Registration number " + Regno
													+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

															
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
					
						
					
					}//else if bnys_Y2_P1_r16
								
	else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 2 Part-II" )) &&(regulation.trim().contains("2016")) ) {
		
		try {
			ExtentTest testCaseScenario = testCaseName.createNode(
					"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

	
	
			
			
		     Pattern bnysY2P2R16MarksPattern =	Pattern.compile(
				     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
	   			
	   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
				    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
	   			    Pattern.DOTALL | Pattern.MULTILINE
	   			);
			
			Matcher bnysY2P2R16MarksPatternMatcher = bnysY2P2R16MarksPattern.matcher(text);
			
			while(bnysY2P2R16MarksPatternMatcher.find()){
			
			
			subject =  bnysY2P2R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
			String intMaxMarks = bnysY2P2R16MarksPatternMatcher.group(2);
			String intSecMarks = bnysY2P2R16MarksPatternMatcher.group(3);
			String p1 =bnysY2P2R16MarksPatternMatcher.group(4);
			String p2 =bnysY2P2R16MarksPatternMatcher.group(5);
			   String theoryThMaxMark = bnysY2P2R16MarksPatternMatcher.group(6);
				   String theoryThSecMark = bnysY2P2R16MarksPatternMatcher.group(7);
				   String theoryThPlusIntMaxMark = bnysY2P2R16MarksPatternMatcher.group(8);
				   String theoryThPlusIntSecMark = bnysY2P2R16MarksPatternMatcher.group(9);
				  
				   String practicalPractical = bnysY2P2R16MarksPatternMatcher.group(10);
				   String practicalViva = bnysY2P2R16MarksPatternMatcher.group(11);
				   String practicalPlusVivaMaxMark = bnysY2P2R16MarksPatternMatcher.group(12);
				   String practicalPlusVivaSecMark = bnysY2P2R16MarksPatternMatcher.group(13);				   
		
				  String theoryPlusPracticalMaxMark =  bnysY2P2R16MarksPatternMatcher.group(14);
				  String theoryPlusPracticalSecMark = bnysY2P2R16MarksPatternMatcher.group(15);
					status =	 bnysY2P2R16MarksPatternMatcher.group(16); 
    
    System.out.println("==============");
			    
    System.out.println("Subject: " + subject);
    
    System.out.println("Int Max Marks: " + intMaxMarks);
    System.out.println("Int Sec Marks: " + intSecMarks);
    System.out.println("P1: " + p1);
    System.out.println("P2: " + p2);
	System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
	System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
	System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
	System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
	System.out.println("Practical Practical: " + practicalPractical);
	System.out.println("Practical Viva: " + practicalViva);
	System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
	System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
	System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
	System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
	System.out.println("Result: " + status);
	System.out.println("==============");
	
	if ((status.trim().equals("Pass") || status.trim().equals("Fail")
			|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
	
		
		try {

			PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
					intSecMarks,intMaxMarks,0.35, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
							
	}
		
		
		
		
		
		try {

			PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
					p1,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
					p2,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
							
	}
		
	
		
		
		try {
			
		
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(p2.trim())) {
			
				
				System.out.println(p1);
			double p1Mark = Double.parseDouble(p1);
			double p2Mark = Double.parseDouble(p2);
			double intMark = Double.parseDouble(intSecMarks);							
			if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
			
			PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


		}
			else 	if (invalidValues.contains(p2.trim())) {
				System.out.println(p1);
				double p1Mark = Double.parseDouble(p1);
				double p2Mark = 0.0;
				double intMark = Double.parseDouble(intSecMarks);					
				
				if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
					
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					
					testCaseScenario1.log(Status.PASS,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				else {
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					testCaseScenario1.log(Status.FAIL,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				
				
				PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
						theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
						grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
									"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Paper 1 Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		try {

			PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
					practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
					practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
							
	}
		
		
		try {
			
			
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(practicalViva.trim())) {
			
				
	
			double practicalPracticalMark = Double.parseDouble(practicalPractical);
			double praticalVivaMark = Double.parseDouble(practicalViva);
						
			if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started srunning");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
			}
			
			
			PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


		}
				
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
					
				
				
				testCaseScenario1.log(Status.FAIL,
						"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Theory plus int Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		
		 try {
		    	

		    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
						  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

											
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
	
		
	
	}//else if bnys_Y2_P2_r16
				
	else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 3 Part-I" )) &&(regulation.trim().contains("2016")) ) {
		
		try {
			ExtentTest testCaseScenario = testCaseName.createNode(
					"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

	
	
			
			
		     Pattern bnysY3P1R16MarksPattern =	Pattern.compile(
				     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
	   			
	   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
				    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
	   			    Pattern.DOTALL | Pattern.MULTILINE
	   			);
			
			Matcher bnysY3P1R16MarksPatternMatcher = bnysY3P1R16MarksPattern.matcher(text);
			
			while(bnysY3P1R16MarksPatternMatcher.find()){
			
			
			subject =  bnysY3P1R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
			String intMaxMarks = bnysY3P1R16MarksPatternMatcher.group(2);
			String intSecMarks = bnysY3P1R16MarksPatternMatcher.group(3);
			String p1 =bnysY3P1R16MarksPatternMatcher.group(4);
			String p2 =bnysY3P1R16MarksPatternMatcher.group(5);
			   String theoryThMaxMark = bnysY3P1R16MarksPatternMatcher.group(6);
				   String theoryThSecMark = bnysY3P1R16MarksPatternMatcher.group(7);
				   String theoryThPlusIntMaxMark = bnysY3P1R16MarksPatternMatcher.group(8);
				   String theoryThPlusIntSecMark = bnysY3P1R16MarksPatternMatcher.group(9);
				  
				   String practicalPractical = bnysY3P1R16MarksPatternMatcher.group(10);
				   String practicalViva = bnysY3P1R16MarksPatternMatcher.group(11);
				   String practicalPlusVivaMaxMark = bnysY3P1R16MarksPatternMatcher.group(12);
				   String practicalPlusVivaSecMark = bnysY3P1R16MarksPatternMatcher.group(13);				   
		
				  String theoryPlusPracticalMaxMark =  bnysY3P1R16MarksPatternMatcher.group(14);
				  String theoryPlusPracticalSecMark = bnysY3P1R16MarksPatternMatcher.group(15);
					status =	 bnysY3P1R16MarksPatternMatcher.group(16); 
    
    System.out.println("==============");
			    
    System.out.println("Subject: " + subject);
    
    System.out.println("Int Max Marks: " + intMaxMarks);
    System.out.println("Int Sec Marks: " + intSecMarks);
    System.out.println("P1: " + p1);
    System.out.println("P2: " + p2);
	System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
	System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
	System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
	System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
	System.out.println("Practical Practical: " + practicalPractical);
	System.out.println("Practical Viva: " + practicalViva);
	System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
	System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
	System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
	System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
	System.out.println("Result: " + status);
	System.out.println("==============");
	
	System.out.println("subjectToFind"+ subjectToFind);
	
	if ((status.trim().equals("Pass") || status.trim().equals("Fail")
			|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
	
		
		try {

			PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
					intSecMarks,intMaxMarks,0.35, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
							
	}
		
		
		
		
		
		try {

			PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
					p1,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
					p2,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
							
	}
		
	
		
		
		try {
			
		
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(p2.trim())) {
			
				
				System.out.println(p1);
			double p1Mark = Double.parseDouble(p1);
			double p2Mark = Double.parseDouble(p2);
			double intMark = Double.parseDouble(intSecMarks);							
			if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
			
			PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


		}
			else 	if (invalidValues.contains(p2.trim())) {
				System.out.println(p1);
				double p1Mark = Double.parseDouble(p1);
				double p2Mark = 0.0;
				double intMark = Double.parseDouble(intSecMarks);					
				
				if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
					
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					
					testCaseScenario1.log(Status.PASS,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				else {
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					testCaseScenario1.log(Status.FAIL,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				
				
				PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
						theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
						grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
									"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Paper 1 Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		try {

			PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
					practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
					practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
							
	}
		
		
		try {
			
			
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(practicalViva.trim())) {
			
				
	
			double practicalPracticalMark = Double.parseDouble(practicalPractical);
			double praticalVivaMark = Double.parseDouble(practicalViva);
						
			if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started srunning");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
			}
			
			
			PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


		}
				
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
					
				
				
				testCaseScenario1.log(Status.FAIL,
						"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Theory plus int Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		
		 try {
		    	

		    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
						  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

											
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
	
		
	
	}//else if bnys_Y3_P1_r16			
					
else if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS"))&&(semester.trim().equalsIgnoreCase("Year 3 Part-II" )) &&(regulation.trim().contains("2016")) ) {
		
		try {
			ExtentTest testCaseScenario = testCaseName.createNode(
					"Pattern validation for the course name "+courseNameRegexPatternMatcher.group()+" of the  following " + Regno + " Test case");

	
	
			
			
		     Pattern bnysY3P2R16MarksPattern =	Pattern.compile(
				     "(?m)^\\s*([A-Z ,&'()\\-/]+(?:\\R\\s*[A-Z ,&'()\\-]+)*?)\\s+" 
	   			
	   			   	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"

							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
							+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   				
				    	+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))\\s*(?:\\(\\s*F\\s*\\))?\\s*"
	   			    + "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
	   			    Pattern.DOTALL | Pattern.MULTILINE
	   			);
			
			Matcher bnysY3P2R16MarksPatternMatcher = bnysY3P2R16MarksPattern.matcher(text);
			
			while(bnysY3P2R16MarksPatternMatcher.find()){
			
			
			subject =  bnysY3P2R16MarksPatternMatcher.group(1).replaceAll("\\s*\\R\\s*", " ").trim();
			String intMaxMarks = bnysY3P2R16MarksPatternMatcher.group(2);
			String intSecMarks = bnysY3P2R16MarksPatternMatcher.group(3);
			String p1 =bnysY3P2R16MarksPatternMatcher.group(4);
			String p2 =bnysY3P2R16MarksPatternMatcher.group(5);
			   String theoryThMaxMark = bnysY3P2R16MarksPatternMatcher.group(6);
				   String theoryThSecMark = bnysY3P2R16MarksPatternMatcher.group(7);
				   String theoryThPlusIntMaxMark = bnysY3P2R16MarksPatternMatcher.group(8);
				   String theoryThPlusIntSecMark = bnysY3P2R16MarksPatternMatcher.group(9);
				  
				   String practicalPractical = bnysY3P2R16MarksPatternMatcher.group(10);
				   String practicalViva = bnysY3P2R16MarksPatternMatcher.group(11);
				   String practicalPlusVivaMaxMark = bnysY3P2R16MarksPatternMatcher.group(12);
				   String practicalPlusVivaSecMark = bnysY3P2R16MarksPatternMatcher.group(13);				   
		
				  String theoryPlusPracticalMaxMark =  bnysY3P2R16MarksPatternMatcher.group(14);
				  String theoryPlusPracticalSecMark = bnysY3P2R16MarksPatternMatcher.group(15);
					status =	 bnysY3P2R16MarksPatternMatcher.group(16); 
    
    System.out.println("==============");
			    
    System.out.println("Subject: " + subject);
    
    System.out.println("Int Max Marks: " + intMaxMarks);
    System.out.println("Int Sec Marks: " + intSecMarks);
    System.out.println("P1: " + p1);
    System.out.println("P2: " + p2);
	System.out.println("Theory Th. Max Marks: " + theoryThMaxMark);
	System.out.println("Theory Th. Sec Marks: " + theoryThSecMark);
	System.out.println("Theory Th. + Int. Max Marks: " + theoryThPlusIntMaxMark);
	System.out.println("Theory Th. + Int. Sec. Marks: " + theoryThPlusIntSecMark);
	System.out.println("Practical Practical: " + practicalPractical);
	System.out.println("Practical Viva: " + practicalViva);
	System.out.println("Practical + Viva Max Marks: " + practicalPlusVivaMaxMark);
	System.out.println("Practical + Viva Sec. Marks: " + practicalPlusVivaSecMark);
	System.out.println("Theory + Practical Max Marks: " + theoryPlusPracticalMaxMark);
	System.out.println("Theory + Practical Sec. Marks: " + theoryPlusPracticalSecMark);
	System.out.println("Result: " + status);
	System.out.println("==============");
	
	System.out.println("subjectToFind"+ subjectToFind);
	
	if ((status.trim().equals("Pass") || status.trim().equals("Fail")
			|| status.trim().equals("AP")) && subject.replaceAll("\\s+", "").equals(subjectToFind.replaceAll("\\s+", ""))) {
	
		
		try {

			PageValidation.nonValidateMarks(Regno,"Int Sec Marks", subject,subjectToFind,
					intSecMarks,intMaxMarks,0.35, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Int Sec Marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Int Sec Marks is: " + intSecMarks);
							
	}
		
		
		
		
		
		try {

			PageValidation.nonValidateMarks(Regno,"P1 sec. marks ", subject,subjectToFind,
					p1,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P1 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P1 sec. marks is: " + p1);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"P2 sec. marks ", subject,subjectToFind,
					p2,theoryThMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("P2 sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" P2 sec. marks is: " + p2);
							
	}
		
	
		
		
		try {
			
		
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(p2.trim())) {
			
				
				System.out.println(p1);
			double p1Mark = Double.parseDouble(p1);
			double p2Mark = Double.parseDouble(p2);
			double intMark = Double.parseDouble(intSecMarks);							
			if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark +intMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
			
			PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


		}
			else 	if (invalidValues.contains(p2.trim())) {
				System.out.println(p1);
				double p1Mark = Double.parseDouble(p1);
				double p2Mark = 0.0;
				double intMark = Double.parseDouble(intSecMarks);					
				
				if(Double.parseDouble(theoryThPlusIntSecMark) == (p1Mark +p2Mark+intMark)) {
					
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					
					testCaseScenario1.log(Status.PASS,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				else {
					ExtentTest testCaseScenario1 = testCaseScenario
							.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
				+ subject + " Test case has started running");		
					testCaseScenario1.log(Status.FAIL,
							"The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " +(p1Mark +p2Mark) + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}
				
				
				PageValidation.validateMarks(Regno,"Paper1 Sec Marks", paper1, paper2, paper3,paper4,
						theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
						grandTotal, theoryThPlusIntSecMark, theoryThPlusIntMaxMark, 0.40, testCaseName);		


			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of P1 plus P2 and Theory plus int Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
									"Please check The Following Registration number P1 plus P2 and Theory plus int Sec. Marks " + Regno+ " for the Subject " + "is not equals "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Paper 1 Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of paper 1 sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		try {

			PageValidation.nonValidateMarks(Regno,"Pratical Pratical sec. marks ", subject,subjectToFind,
					practicalPractical,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Pratical Pratical sec. marks sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks sec. marks is: " + practicalPractical,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" Pratical Pratical sec. marks is: " + practicalViva);
							
	}

		try {

			PageValidation.nonValidateMarks(Regno,"Practical Viva sec. marks ", subject,subjectToFind,
					practicalViva,practicalPlusVivaMaxMark,0.00, testCaseName);		
	}
	
	catch(Exception e) {
		ExtentTest testCaseScenario1 = testCaseScenario.createNode("Practical Viva sec. marks validation for the subject " + subject +" Test case has started running");
		
		testCaseScenario1.log(Status.FAIL,"The following Register number " + Regno +" for the subject "+ subject +" Practical Viva sec. marks is: " + practicalViva,
				MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
		System.out.println("The following Register number " + Regno +" for the subject "+ subject +" practical Viva sec. marks is: " + practicalViva);
							
	}
		
		
		try {
			
			
			Set<String> invalidValues = Set.of("NA", "AB", "NE", "---", "NE (AT)");
			if (!invalidValues.contains(practicalViva.trim())) {
			
				
	
			double practicalPracticalMark = Double.parseDouble(practicalPractical);
			double praticalVivaMark = Double.parseDouble(practicalViva);
						
			if(Double.parseDouble(practicalPlusVivaSecMark) == (practicalPracticalMark +praticalVivaMark)) {
				
				
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started srunning");		
				
				testCaseScenario1.log(Status.PASS,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
				
				testCaseScenario1.log(Status.FAIL,
						"The Following Registration number practical Practical and pratical Viva Sec. Marks " +(practicalPracticalMark +praticalVivaMark) + Regno+ " for the Subject " + "is not equals "+ practicalPlusVivaSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		
			}
			
			
			PageValidation.validateMarks(Regno,"Pratical Total Sec Marks", paper1, paper2, paper3,paper4,
					theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
					grandTotal, practicalPlusVivaSecMark, practicalPlusVivaMaxMark, 0.50, testCaseName);		


		}
				
			else {
				ExtentTest testCaseScenario1 = testCaseScenario
						.createNode("Validation of practical Practical  and pratical Viva Sec. Marks for the  "
			+ subject + " Test case has started running");		
					
				
				
				testCaseScenario1.log(Status.FAIL,
						"Please check the Following Registration number  "+ Regno + "for the "+ subject  +" subject  "+ theoryPlusPracticalSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

			}
			
		
		}
		catch(Exception e) {
			ExtentTest testCaseScenario1 = testCaseScenario
					.createNode("Theory plus int Sec. Marks "
		+ subject + " Test case has started running");		
			testCaseScenario1.log(Status.FAIL,
					"\n Please check The Following Registration number " + Regno+ " for the Subject " + subject+ " of theory plus sec sec mark is: "+ theoryThPlusIntSecMark ,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

}
		
		
		 try {
		    	

		    	PageValidation.validateMarks(Regno,"Grand Total Sec Marks", paper1, paper2, paper3,paper4,
							theoryExamTotal,practicalExamTotal, subject,subjectToFind,status,
							grandTotal, theoryPlusPracticalSecMark, theoryPlusPracticalMaxMark, 0.50, testCaseName);		
			}
			
			catch(Exception e) {
				  ExtentTest testCaseScenario1 = testCaseScenario.createNode(
						  "Grand Total Sec Marks validation for subject " + subjectToFind + " test case has started");
				  testCaseScenario1.log(Status.FAIL,
							"\n Please check The Following Registration number " + Regno
									+ "Grand Total Sec Marks"  + theoryPlusPracticalSecMark,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

											
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
	
		
	
	}//else if bnys_Y3_P2_r16							
					
					
					
					
					
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
