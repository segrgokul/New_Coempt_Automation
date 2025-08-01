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
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BDS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BHMS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BNYS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_BUMS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_Bsc_MLT_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_Bsc_Nursing_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_Bsc_Post_Basic_Nursing_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MDS_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MD_Homeo_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MD_MS_AYURVEDA_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MD_Unani_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_MPH_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_PDF_CoursePattern;
import knrEnrollmentPageReportCardPatternBased.KnrReportCard_PG_Diploma_CoursePattern;


public class KnrReportCardPatternIdentify extends BasicFunctions{
	KnrReportCard_MDS_CoursePattern MDS = new KnrReportCard_MDS_CoursePattern();
	KnrReportCard_PDF_CoursePattern PDF = new KnrReportCard_PDF_CoursePattern();
	KnrReportCard_PG_Diploma_CoursePattern PG_Diploma = new KnrReportCard_PG_Diploma_CoursePattern();
	KnrReportCard_MD_Unani_CoursePattern MD_Unani = new KnrReportCard_MD_Unani_CoursePattern();  
	KnrReportCard_BAMS_CoursePattern BAMS = new KnrReportCard_BAMS_CoursePattern();
	KnrReportCard_BHMS_CoursePattern BHMS = new KnrReportCard_BHMS_CoursePattern();
	KnrReportCard_BUMS_CoursePattern BUMS = new KnrReportCard_BUMS_CoursePattern();
	KnrReportCard_BNYS_CoursePattern BNYS = new KnrReportCard_BNYS_CoursePattern();
	KnrReportCard_Bsc_MLT_CoursePattern Bsc_MLT = new KnrReportCard_Bsc_MLT_CoursePattern();
	KnrReportCard_MD_MS_AYURVEDA_CoursePattern MD_MS_AYURVEDA = new KnrReportCard_MD_MS_AYURVEDA_CoursePattern();
	KnrReportCard_MD_Homeo_CoursePattern MD_HOMOEOPATHY = new KnrReportCard_MD_Homeo_CoursePattern();
	KnrReportCard_MPH_CoursePattern MPH = new KnrReportCard_MPH_CoursePattern();
	KnrReportCard_BDS_CoursePattern BDS = new KnrReportCard_BDS_CoursePattern();
	KnrReportCard_Bsc_Post_Basic_Nursing_CoursePattern Bsc_PBN = new KnrReportCard_Bsc_Post_Basic_Nursing_CoursePattern();
	KnrReportCard_Bsc_Nursing_CoursePattern BSC_Nursing = new KnrReportCard_Bsc_Nursing_CoursePattern();
	
	
	
	
	
	
	
	
	
	
	
	
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
				for (int page = 1; page <= 1; page++) {
					stripper.setStartPage(page);
					stripper.setEndPage(page);

					// System.out.println(pdfText);

					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");

					
					String courseNameRegex = "(?i)\\b("
						    + "MDS|"
						    + "POST\\s*DOCTORAL\\s*FELLOWSHIP|"
						    + "PG\\s*Diploma|"
						    + "M\\.D\\.\\s*UNANI|"
						    + "M\\.D\\.\\s*HOMOEOPATHY|"
						    + "BAMS|"
						    + "BHMS|"
						    + "BUMS|"
						    + "BNYS|"
						    + "B\\.?\\s*Sc\\.?\\s*MLT|"
						    + "B\\.\\s*Sc\\.\\s*Nursing|"
						    + "Post\\s*Basic\\s*B\\.\\s*Sc\\.\\s*Nursing|"
						    + "M\\.(?:D(?:\\.A)?\\.?|S\\.)\\s*AYURVEDA\\s*\\([A-Z &]+\\)|"
						    + "M\\.P\\.H"
						    + ")\\s*";

					// brackets
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
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BUMS")) {

						System.out.println("Pattern matched: BUMS patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						BUMS.processBUMSPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						}
					
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BNYS")) {

						System.out.println("Pattern matched: BNYS patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						BNYS.processBNYSPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						}
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("B.Sc.MLT")) {

						System.out.println("Pattern matched: Bsc_MLT patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						Bsc_MLT.processBsc_MLTPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						}
					else if(courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").contains("AYURVEDA")) {

						System.out.println("Pattern matched: MD/MS AYURVEDA patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						MD_MS_AYURVEDA.processMD_MS_AYURVEDAPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
						}
					
					else if (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.D.HOMOEOPATHY")) {
						System.out.println("Pattern matched: MD_HOMOEOPATHY patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						MD_HOMOEOPATHY.process_MD_Homeo_PatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					else if (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("M.P.H")) {
						System.out.println("Pattern matched: M.P.H patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						MPH.processMPHPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					else if  (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("BDS")) {
						System.out.println("Pattern matched: BDS patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						BDS.processBDSPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					else if  (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("PostBasicB.Sc.Nursing")) {
						System.out.println("Pattern matched: Post Basic B.Sc.Nursing patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						Bsc_PBN.process_Bsc_Post_Basic_NursingPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					else if  (courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("B.Sc.Nursing")) {
						System.out.println("Pattern matched: B.Sc.Nursing patterns detected.");
						testCaseScenario1.log(Status.INFO,"Pattern matched: "+courseNameRegexPatternMatcher.group()+ "found");
						BSC_Nursing.process_Bsc_NursingPatternPdf(latestFile, Regno, semester, regulation, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal, grandTotal, testCaseName, subjectToFind);
						
					}
					}
					
					catch(Exception e){
						testCaseName.log(Status.FAIL, e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
					
				}
			}}
			
					
	}}
