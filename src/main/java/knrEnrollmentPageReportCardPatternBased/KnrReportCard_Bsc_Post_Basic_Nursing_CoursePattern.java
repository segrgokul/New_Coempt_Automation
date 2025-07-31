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

public class KnrReportCard_Bsc_Post_Basic_Nursing_CoursePattern extends BasicFunctions {


String subject;
String status;

KnrReportEnrollmentPageValidation PageValidation = new KnrReportEnrollmentPageValidation();

public void process_Bsc_Post_Basic_NursingPatternPdf(File latestFile, String Regno, String semester, String regulation, Object paper1,
		Object paper2, Object paper3, Object paper4, Object theoryExamTotal, Object practicalExamTotal,
		Object grandTotal, ExtentTest testCaseName, String subjectToFind) throws IOException {
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

				String courseNameRegex = "(?i)(Post Basic B.Sc.Nursing)\\s+";

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

					testCaseScenario.log(Status.FAIL,
							" Please check the The following Register number " + Regno + " for the subject "
									+ subjectToFind + " No match found",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					System.out.println("FAIL Please check the The following Register number " + Regno
							+ " for the subject " + subjectToFind + " No match found");

					System.out.println("No match found.");
				}
				
				if ((courseNameRegexPatternMatcher.group().replaceAll("\\s+", "").equalsIgnoreCase("PostBasicB.Sc.Nursing"))
						&& (semester.trim().equalsIgnoreCase("Year 1")) && (regulation.trim().contains("2016"))) {
					try {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name " + courseNameRegexPatternMatcher.group()
									+ " of the  following " + Regno + " Test case");
					System.out.println(text);

		
					Pattern BscPBNY1R16SubjectRegexPattern = Pattern.compile(
							"^(?!Fail|Pass|AP|NE|AB|Theory|Practical|Grand Total|Controller of Examinations|Principal)\\s*"
									+ "([A-Z][A-Za-z &,\\-.()]+(?:\\s+[A-Za-z &,\\-.()]+)*)\\s*" // Subject Name
									+ "(?:\\(([^)]+)\\))?\\s*" // Specialization (optional)

									// Theory Internal Max
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Theory Internal Sec
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Theory Univ Max
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Theory Univ Sec
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"

									// Practical Internal Max (optional)
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Practical Internal Sec (optional)
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Practical Univ Max (optional)
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Practical Univ Sec (optional)
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"

									// Theory + Practical Max
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?\\s*"
									// Theory + Practical Sec
									+ "(\\d+|---|NA|AB|NE|NR|NE\\s*\\(AT\\))?(?:\\s*\\(F\\))?\\s*"

									// Status
									+ "(AP|Pass|Fail|AB|NE|NR|NE\\s*\\(AT\\)|---)$",
							Pattern.MULTILINE);

					Matcher matcher = BscPBNY1R16SubjectRegexPattern.matcher(text);

					while (matcher.find()) {

						try {
							testCaseScenario.log(Status.PASS,
									"Subject Pattern validation for the course name "
											+ courseNameRegexPatternMatcher.group() + " of the  following "
											+ Regno + " has matched");

							System.out.println("==============");
							subject = (matcher.group(2) == null) ? matcher.group(1).replaceAll("\\s+", " ").trim()
									: (matcher.group(1) + " " + matcher.group(2)).replaceAll("\\s+", " ").trim();

							String theoryInternalMaxMarks = matcher.group(3);
							String theoryInternalSecMarks = matcher.group(4);
							String theoryUnivMaxMarks = matcher.group(5);
							String theoryUnivSecMarks = matcher.group(6);
							String practicalInternalMaxMarks = matcher.group(7);
							String practicalInternalSecMarks = matcher.group(8);
							String practicalUnivMaxMarks = matcher.group(9);
							String practicalUnivSecMarks = matcher.group(10);
							String theoryPracticalMaxMarks = matcher.group(11);
							String theoryPracticalSecMarks = matcher.group(12);
							status = matcher.group(13);

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
							
							
							
							
						} // try
						catch (Exception e) {
							testCaseScenario.log(Status.FAIL,
									"Subject Pattern validation for the course name "
											+ courseNameRegexPatternMatcher.group() + " of the  following "
											+ Regno + " has not matched");
						}
					} // while

				} // try
				catch (Exception e) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
									+ " of the  following " + Regno + " Test case");

					testCaseScenario.log(Status.FAIL,
							"Pattern validation for the course name " + courseNameRegexPatternMatcher.group(1)
									+ " of the  following " + Regno + " Test case has fail to started running",
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver))
									.build());
					testCaseScenario.log(Status.FAIL, e.getMessage(), MediaEntityBuilder
							.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					System.out.println(text);
				}
			} // 
				if (page == totalPages) {
					break;
				}
			} // for
		} // try
		catch (Exception e) {
			e.printStackTrace();
			testCaseName.log(Status.FAIL, e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

		}
	} // if
	else {
		System.out.println("No PDF file found.");
	}
}// method
}