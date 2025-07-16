package knrEnrollmentPageReportCardPatternIdentity;

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
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BAMS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BHMS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MDS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MD_Unani_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_PDF_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_PG_Diploma_CoursePattern;


public class KnrReportCardPatternIdentify extends BasicFunctions{
	KnrReportCard_MDS_CoursePattern MDS = new KnrReportCard_MDS_CoursePattern();
	KnrReportCard_PDF_CoursePattern PDF = new KnrReportCard_PDF_CoursePattern();
	KnrReportCard_PG_Diploma_CoursePattern PG_Diploma = new KnrReportCard_PG_Diploma_CoursePattern();
	KnrReportCard_MD_Unani_CoursePattern MD_Unani = new KnrReportCard_MD_Unani_CoursePattern();  
	KnrReportCard_BAMS_CoursePattern BAMS = new KnrReportCard_BAMS_CoursePattern();
	KnrReportCard_BHMS_CoursePattern BHMS = new KnrReportCard_BHMS_CoursePattern();
	
	
	
	
	public void processPdfBasedOnCoursePattern(File latestFile, String Regno,  String semester,String regulation,Object paper1, Object paper2,
			Object paper3,Object paper4, Object theoryExamTotal, Object practicalExamTotal, Object grandTotal, String subjectToFind,
			ExtentTest testCaseName) throws IOException {
		
		
		if (latestFile != null) {
			ExtentTest testCaseScenario = testCaseName.createNode(
					"Pattern validation for the following " + Regno + " Test case has started running");

			try (PDDocument document = PDDocument.load(latestFile)) {
				PDFTextStripper stripper = new PDFTextStripper();
				int totalPages = document.getNumberOfPages();

				// Iterate through all pages and extract text
				for (int page = 1; page <= totalPages; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					String pdfText = stripper.getText(document);

					// System.out.println(pdfText);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");

					
					String courseNameRegex = "(?i)\\b(MDS|POST DOCTORAL FELLOWSHIP|PG Diploma|M\\.D\\.\\s*UNANI|BAMS|BHMS)\\s*";

					Pattern courseNameRegexPattern = Pattern.compile(courseNameRegex, Pattern.MULTILINE);
					Matcher courseNameRegexPatternMatcher = courseNameRegexPattern.matcher(text);

					if (courseNameRegexPatternMatcher.find()) {
						
						System.out.println("MathchFound: " + courseNameRegexPatternMatcher.group(1)); // Prints "MDS"
						
						testCaseScenario.log(Status.PASS, "MathchFound: " + courseNameRegexPatternMatcher.group(1));

					
					
					}

					else {

				
						testCaseScenario.log(Status.FAIL, " Please check the The following Register number " + Regno
								+ " for the subject " + subjectToFind + " No match found",MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						System.out.println("FAIL Please check the The following Register number " + Regno
								+ " No match found");

						System.out.println("No match found.");
					}
					
				
					try {
						ExtentTest testCaseScenario1 = testCaseScenario.createNode("Processing Pdf Based On Subject Pattern");
						
						
					if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("MDS")) {
						
						
						System.out.println("Pattern matched: MDS patterns detected.");
						
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
					
						MDS.processMDSPatternPdf(latestFile, Regno,semester,regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
					
						
					}
					
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("POST DOCTORAL FELLOWSHIP")) {
						
						
						System.out.println("Pattern matched: PDF patterns detected.");
						
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
					
						PDF.processPDFPatternPdf(latestFile, Regno,semester,regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
					
						
					}
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("PG Diploma")) {
						
						
						System.out.println("Pattern matched: PG patterns detected.");
						
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
					
						PG_Diploma.processPG_DiplomaPatternPdf(latestFile, Regno,semester,regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
					
						
					}
					
					else if (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.UNANI")) {
						System.out.println("Pattern matched: MD_Unani patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						MD_Unani.processMD_UnaniPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BAMS")) {

					System.out.println("Pattern matched: BAMS patterns detected.");
					testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
					BAMS.processBAMSPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
					
					}
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BHMS")) {

						System.out.println("Pattern matched: BHMS patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						BHMS.processBHMSPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						}
					
					}
					catch(Exception e){
						
					}
					
				}
			}}
			
					
	}}
