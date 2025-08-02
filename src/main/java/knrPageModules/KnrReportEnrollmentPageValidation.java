package knrPageModules;

import java.io.IOException;
import java.util.Set;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;

public class KnrReportEnrollmentPageValidation extends BasicFunctions {
	static ExtentTest test;

	static double minMark = 0.0;
	KnrRportEnrollmentPaperSecValidation PaperSecValidation = new KnrRportEnrollmentPaperSecValidation();

	KnrRportEnrollmentPaperSecValidation validationObj = new KnrRportEnrollmentPaperSecValidation();

	// Method to check if the obtained marks meet the required percentage
	public static boolean verifyScore(double obtainedMarks, double totalMarks, double percentage,ExtentTest testCaseName) {
		// Calculate the minimum required marks
		minMark = totalMarks * percentage;
		
		System.out.println(minMark);
		// Print the assigned minimum marks
		System.out.println("Assigned Minimum Marks for " + (percentage * 100) + "% of " + totalMarks + ": " + minMark);

		// Return whether obtained marks meet or exceed the minimum required marks
		return obtainedMarks >= minMark;
	}

	// Helper function to check if the marks are greater than 50% of max marks
	public void checkMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3, Object paper4,
			Object theoryExam, Object praticalExam, String subject, String subjectToFind, String status,
			Object grandTotal, String obtainedMarks, double maxMarks, double minMark, ExtentTest testCaseName)
			throws IOException {

		System.out.println(obtainedMarks + "obtainedMarks");

		if ((obtainedMarks.equals("---") || obtainedMarks.equals("NA")) || obtainedMarks.equals("NE")) {
			System.out.println(markName + " Subject marks: Not available");
		} else {
		}
		double marksValue = 0;
		if (obtainedMarks.contains("G")) {

			marksValue = Double.parseDouble(obtainedMarks.replaceAll("(?i)g", ""));
		}

		if (marksValue > maxMarks * 0.5) {
		}

		try {
			if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& (markName.trim().contains("Paper1 Sec Marks"))) {

				PaperSecValidation.Paper1SecMarksValidation(regno, markName, obtainedMarks, minMark, paper1, subject,
						subjectToFind, testCaseName);

			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& (markName.trim().contains("Paper1 Grace Sec Marks"))) {

				PaperSecValidation.gracePaper1SecMarksValidation(regno, markName, obtainedMarks, minMark, paper1,
						subject, subjectToFind, testCaseName);

			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& (markName.trim().contains("SGPA Sec. Marks") || markName.trim().contains("Paper2 Sec Marks"))) {

				System.out.println(paper2);
				PaperSecValidation.Paper2SecMarksValidation(regno, markName, obtainedMarks, minMark, paper2, subject,
						subjectToFind, testCaseName);

			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& (markName.trim().contains("Credits Sec. Marks")
							|| markName.trim().contains("Paper3 Sec Marks"))) {

				PaperSecValidation.Paper3SecMarksValidation(regno, markName, obtainedMarks, minMark, paper3, subject,
						subjectToFind, testCaseName);
			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& (markName.trim().contains("Credits Sec. Marks")
							|| markName.trim().contains("Paper4 Sec Marks"))) {

				PaperSecValidation.Paper4SecMarksValidation(regno, markName, obtainedMarks, minMark, paper4, subject,
						subjectToFind, testCaseName);
			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))

					&& markName.trim().contains("Theory Total Sec Marks")) {
				System.out.println(obtainedMarks);
				System.out.println(theoryExam);

				PaperSecValidation.TheoryTotalSecMarksValidation(regno, markName, obtainedMarks, minMark, theoryExam,
						subject, subjectToFind, testCaseName);
			}

			else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& markName.trim().contains("Pratical Univ Sec. Marks")
					|| markName.trim().contains("Pratical Total Sec Marks")) {

				System.out.println(praticalExam);

				PaperSecValidation.praticalTotalSecMarksValidations(regno, markName, obtainedMarks, minMark,
						praticalExam, subject, subjectToFind, testCaseName);
			} else if ((status.trim().equals("Pass") || status.trim().equals("Fail") || status.trim().equals("AP")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					&& markName.trim().contains("Grand Total Sec Marks")) {

				PaperSecValidation.grandTotalMarksValidation(regno, markName, obtainedMarks, minMark, grandTotal,
						subject, subjectToFind, testCaseName);

				System.out.println("==============");
			} else if ((status.trim().equals("Pass") || status.trim().equals("Fail")
					|| status.trim().equals("First Class") || status.trim().equals("Distinction"))
					|| status.trim().equals("AP") && (markName.trim().contains("Grand Total Grace Sec Marks"))) {

				PaperSecValidation.gracegrandTotalMarksValidationCheck(regno, status, obtainedMarks, minMark,
						theoryExam, subject, subjectToFind, testCaseName);

			}

			else {
				System.out.println("==============");
				// securedMarks(regno, examTotal, testCaseName);
				System.out.println("==============");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateMarks(Object regno, String markName, Object paper1, Object paper2, Object paper3, Object paper4,
			Object theoryExamTotal, Object practicalExamTotal, String subject, String subjectToFind, String status,
			Object grandTotal, String marks, String maxMarks, double percentage, ExtentTest testCaseName)
			throws IOException {

		Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");

		System.out.println("markssssss: " + marks);

		try {
			double max = 0.0;
			if (invalidValues.contains(marks.trim())) {
				max = 0.0;
			} else {
				max = Double.parseDouble(maxMarks.trim());
			}

			if (marks != null && !invalidValues.contains(marks.trim())) {
				// Case: Valid numeric marks
				double sec = Double.parseDouble(marks.trim());

				verifyScore(sec, max, percentage,testCaseName); // updates minMark internally
				System.out.println("minMarks: " + minMark);

				checkMarks(regno, markName, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
						subject, subjectToFind, status, grandTotal, String.valueOf(sec), max, minMark, testCaseName);

			} else if (marks != null && invalidValues.contains(marks.trim())) {
				// Case: AB / NE / NA etc
				System.out.println("The followig Ressgister number " + regno + " " + markName + " is: " + marks);

				checkMarks(regno, markName, paper1, paper2, paper3, paper4, theoryExamTotal, practicalExamTotal,
						subject, subjectToFind, status, grandTotal, marks, max, minMark, testCaseName);

			} else {
				// Case: Unexpected/null marks
				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");

				scenario.log(Status.FAIL,
						"\nPlease check Reg No: " + regno + " for Subject " + subjectToFind + " marks is: " + marks);
				System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
			}

		} catch (NumberFormatException e) {
			// Catch parsing issues for numeric conversion
			ExtentTest scenario = testCaseName
					.createNode(markName + " validation for subject " + subjectToFind + " test case has started");

			if (marks != null && invalidValues.contains(marks.trim())) {
				System.out.println("The Following Registration number " + regno + " for the Subject " + subject
						+ " and " + markName + " marks is: " + marks);

				scenario.log(Status.INFO, "The Following Registration number " + regno + " for the Subject " + subject
						+ " and " + markName + " marks is: " + marks);
			} else {
				scenario.log(Status.FAIL,
						"\nPlease check Reg No: " + regno + " for Subject " + subjectToFind + " marks is: " + marks);
				System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
			}
		}
	}

	public void nonValidateMarks(Object regno, String markName, String subject, String subjectToFind, String marks,
			String maxMarks, double percentage, ExtentTest testCaseName) throws IOException {

		Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)");

		double sec;
		double max;

		System.out.println("markss" + marks);

		try {
			if (!invalidValues.contains(marks.trim())) {

				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");
				sec = Double.parseDouble(marks);
				max = Double.parseDouble(maxMarks);
				verifyScore(sec, max, percentage,testCaseName);

				System.out.println("minMark:" + minMark);

				try {
					if ((sec < minMark)) {
						System.out.println(" The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + sec);

						scenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + sec);

					} else if ((sec >= minMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + sec);
						scenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + sec);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + sec);
						scenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ sec,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseName.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}

				System.out.println("The following Register number " + regno + markName + " is: " + marks);

				scenario.log(Status.PASS, "The following Register number " + regno + " " + markName + " is: " + marks);

			} else if (invalidValues.contains(marks.trim())) {
				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");
				sec = 0.0; // Default fallback
				max = 0.0; // Optional: or still parse maxMarks separately if needed

				System.out.println("The following Register number " + regno + markName + " is: " + marks);

				scenario.log(Status.INFO, "The following Register number " + regno + markName + " is: " + marks);
			} else {
				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");
				scenario.log(Status.FAIL,
						"\nPlease check Reg No: " + regno + " for Subject " + subjectToFind + " marks is: " + marks);
				System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
			}
		} catch (NumberFormatException e) {
			if (invalidValues.contains(marks.trim())) {
				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");

				System.out.println(" The Following Registration number " + regno + " for the Subject " + subject
						+ " and " + markName + " marks is: " + marks);

				scenario.log(Status.INFO, " The Following Registration number " + regno + " for the Subject " + subject
						+ " and " + markName + " marks is: " + marks);
			} else {
				ExtentTest scenario = testCaseName
						.createNode(markName + " validation for subject " + subjectToFind + " test case has started");
				scenario.log(Status.FAIL,
						"\nPlease check Reg No: " + regno + " for Subject " + subjectToFind + " marks is: " + marks);
				System.out.println("\nPlease check Reg No: " + regno + " marks is: " + marks);
			}
		}
	}

	public void pdfCompareMarks(Object regno, Object Subject, String pdfPaper1, String pdfPaper2, String pdfTotal,
			String praticalClinicalAndVivaMark, String grandTotal, String status, ExtentTest testCaseName) {

		ExtentTest testCaseScenario = testCaseName.createNode(
				Subject + " Validation Test case has started runningfor the following " + regno + " register no");

		String isResult = null;

		// another way need to check with selva
		// Compare Paper 1 marks (Excel vs PDF)
		if (pdfPaper1.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Paper I " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("The following Register number Paper I " + regno + " is Failed for the subject "
					+ Subject + " PDF marks is: " + pdfPaper1);

		} else if (pdfPaper1.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper I " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper1);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  Paper I " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfPaper1);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfPaper1);

		}

		if (pdfPaper2.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper II " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper2);

		} else if (pdfPaper2.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number  Paper II " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + pdfPaper2);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  Paper I " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfPaper2);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfPaper2);

		}

		// Compare Total marks (Excel vs PDF)
		if (pdfPaper1.equals("B") && pdfPaper2.equals("B") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.FAIL, "The following Register number " + regno + " is Failed for the subject "
					+ Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);

		} else if (pdfPaper1.equals("A") && pdfPaper2.equals("A") && pdfTotal.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Passed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total" + regno + " is Passed for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		} else if (pdfPaper1.equals("A") && pdfPaper2.equals("B") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		}

		else if (pdfPaper1.equals("B") && pdfPaper2.equals("A") && pdfTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + pdfTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + pdfTotal);
			;
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfTotal);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfTotal);

		}

		if (praticalClinicalAndVivaMark.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number pratical mark " + regno
					+ " is Failed for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ " PDF marks is: " + praticalClinicalAndVivaMark);

		} else if (praticalClinicalAndVivaMark.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number pratical mark " + regno
					+ " is Passed for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("The following Register number " + regno + " is Passed for the subject " + Subject
					+ " PDF marks is: " + praticalClinicalAndVivaMark);
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number  pratical mark " + regno
					+ " for the subject " + Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + praticalClinicalAndVivaMark);

		}
		if (pdfTotal.equals("B") && praticalClinicalAndVivaMark.equals("B") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.FAIL, "The following Register number " + regno + " is Failed for the subject "
					+ Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number " + regno + " is Failed for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Fail";

		} else if (pdfTotal.equals("A") && praticalClinicalAndVivaMark.equals("A") && grandTotal.equals("A")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Passed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " is Passed for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Pass";
		} else if (pdfTotal.equals("A") && praticalClinicalAndVivaMark.equals("B") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + grandTotal);

			isResult = "Fail";
		}

		else if (pdfTotal.equals("B") && praticalClinicalAndVivaMark.equals("A") && grandTotal.equals("B")) {
			testCaseScenario.log(Status.PASS, "The following Register number Total " + regno
					+ " is Failed for the subject " + Subject + "PDF marks is: " + grandTotal);

			System.out.println("The following Register number Total" + regno + " for the subject " + Subject
					+ "PDF marks is: " + grandTotal);
			isResult = "Fail";
		}

		else {
			testCaseScenario.log(Status.FAIL, "FAIL:Please check The following Register number " + regno
					+ " for the subject " + Subject + " PDF marks is: " + pdfTotal);

			System.out.println("FAIL:Please check The following Register number " + regno + " for the subject "
					+ Subject + " PDF marks is: " + pdfTotal);

		}

		if (isResult.equals(status)) {

			testCaseScenario.log(Status.PASS,
					"The following Register number " + regno + " is " + isResult + " for the subject " + Subject);
			System.out.println(
					"The following Register number " + regno + " is " + isResult + " for the subject " + Subject);

		} else {
			testCaseScenario.log(Status.FAIL,
					"please check the fail for the following Register number " + regno + " and result is " + isResult);

			System.out.println("please check the fail for the following register number Total" + regno
					+ " for the subject " + Subject + " and result is " + isResult);

		}

	}

}
