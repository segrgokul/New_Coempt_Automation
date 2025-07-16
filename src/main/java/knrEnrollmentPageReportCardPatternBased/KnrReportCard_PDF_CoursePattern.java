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

public class KnrReportCard_PDF_CoursePattern  extends BasicFunctions{
	String subject;	
	String status;
	
	KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

	public void processPDFPatternPdf(File latestFile, String Regno, String semester,String regulation, Object paper1, Object paper2, Object paper3,Object paper4,
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

				
					String courseNameRegex = "(?i)(POST DOCTORAL FELLOWSHIP)\\s*";
					
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
		
				if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("POST DOCTORAL FELLOWSHIP"))&&(semester.trim().equalsIgnoreCase("Final") ) &&(regulation.trim().contains("2016")) ) {

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

							Pattern pdfPattern = Pattern
									.compile("(?i)(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(A|B)\\s+(Pass|Fail)");

				
							Matcher pdfMatcher = pdfPattern.matcher(text);
							
							if (pdfMatcher.find()) {
							
								
								
								String paper1Mark = pdfMatcher.group(1);
								String paper2Mark = pdfMatcher.group(2);
								String totalMark = pdfMatcher.group(3);
								String praticalClinicalAndVivaMark = pdfMatcher.group(4);
								String grandTotalMark = pdfMatcher.group(5);
								status = pdfMatcher.group(6);

								System.out.println("Match found!");

								System.out.println("Subject :" + subject);
								System.out.println("Paper 1: " + paper1Mark);
								System.out.println("Paper 2: " + paper2Mark);
								System.out.println("Total : " + totalMark);
								System.out.println(
										"Pratical Clinical and Viva voce Mark : " + praticalClinicalAndVivaMark);
								System.out.println("Group Total: " + grandTotalMark);
								System.out.println("Status: " + status);

							
							if ((status.trim().equals("Pass") || status.trim().equals("Fail")
									|| status.trim().equals("AP")) && subject.trim().equals(subjectToFind.trim())) {
	
							PageValidation.pdfCompareMarks(Regno, subject, paper1Mark, paper2Mark, totalMark, praticalClinicalAndVivaMark,
									grandTotalMark,status, testCaseScenario);
							}
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
					}
				
				
				
				
				
				
				}}}}
}