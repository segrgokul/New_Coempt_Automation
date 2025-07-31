package knrPageModules;

import java.io.IOException;
import java.util.Set;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;

public class KnrRportEnrollmentPaperSecValidation extends BasicFunctions{
	
	static ExtentTest test;

	public double Paper1 =0.0;
	public double Paper2 ;
	public double Paper3;
	public double Paper4 = 0.0;
	
	double theoryExcelExamTotal = 0.0;
	double PraticalExamTotal = 0.0;
	public double ExamTotalScor = 0.0;

	public double securedMark = 0.0;
	public double paper1Mark = 0.0;

	public double paper2Mark;
	public double paper3Mark = 0.0;
	public double paper4Mark = 0.0;
	double praticalMinMark =0.0;

	double paper1MinMark =0.0;
	double paper2MinMark=0.0;
	double paper3MinMark =0.0;
	double paper4MinMark=0.0;
	double practicalMinMark=0.0;
	double theroryExamTotalMinMark =0.0;
	double examTotalScoreMinMark = 0.0;
	double pdfGrandTotalMinMark = 0.0;

	double theoryMinMark = 0.0;
	double theoryMaxMark = 0.0;
	double grandTotalMinMark = 0.0;
	double grandTotalMaxMark = 0.0;
	double theoryPdfExamTotal = 0.0;
	double praticalTotal = 0.0;
	double excelGrandTotal = 0.0;
	double pdfGrandTotal = 0.0;
	// four pattern
	double theorySecMark = 0.0;

	double praticalMaxMark = 0.0;

	double praticalVivaMaxMark = 0.0;
	double praticalTotalMaxMark = 0.0;
	double praticalTotalSecMark = 0.0;
	double grandTotalSecMark = 0.0;

	double theoryInternalMaxMark = 0.0;
	double theoryInternalSecMark = 0.0;
public void gracePaper1SecMarksValidation(Object regno,String markName,String obtainedMarks,double minMark,Object paper1,String subject,String subjectToFind, ExtentTest testCaseName ) {
//		gracecheck(regno,"Theory Internal Sec Marks",paper1, grace, subjectToFind,
//				theoryInternalMaxMark, testCaseName);
		try {

			if (subject.trim().equals(subjectToFind.trim())) {

				paper1MinMark = minMark;
				if (obtainedMarks.contains("G")) {
					
					paper1Mark	 = Double.parseDouble(obtainedMarks.replaceAll("(?i)g", "")); 
				}else {
					paper1Mark = Double.parseDouble(obtainedMarks); 
				}
				System.out.println(paper1MinMark);
				
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " for the Subject " + subjectToFind + " Validation Test case has started running");

				Paper1 = objectToDataType(paper1);
			
				try {
					if (paper1Mark == Paper1) {
						System.out.println("Both " + Paper1 + " and " + paper1Mark + " for the following Register "
								+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS, "Both " + Paper1 + " and " + paper1Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same marks",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {
					

					System.out.println("marks: " + obtainedMarks);
					
					if(((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) && obtainedMarks.contains("G")) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with grace marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with grace marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					}
					
					else if ((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) {
						System.out.println(" The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else if ((paper1Mark >= paper1MinMark) && (Paper1 >= paper1MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper1Mark + " Excel mark is" + Paper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
	
					
				}
				catch(Exception e) {
					
					
				}
			}
		}//try
		catch(Exception e) {
			
		}
	}
	
	public void Paper1SecMarksValidation(Object regno, String markName,String obtainedMarks,double minMark, Object paper1,String subject, String subjectToFind,
			ExtentTest testCaseName) throws IOException {

		try {
			Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
		
			System.out.println(subject.trim());
			System.out.println(subjectToFind);
			System.out.println(subject.trim().equalsIgnoreCase(subjectToFind));

			if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
					
					
					
			if (subject.trim().equals(subjectToFind.trim())) {

				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
						 
					 
				
				paper1MinMark = minMark;

				Paper1 = objectToDataType(paper1);
				
				paper1Mark = Double.parseDouble(obtainedMarks); 

				System.out.println(paper1MinMark);
				
			
				System.out.println("Paper1SecMarksValidation this = " + this);

				
				
			
				try {
					if (paper1Mark == Paper1) {
						System.out.println("Both " + Paper1 + " and " + paper1Mark + " for the following Register "
								+ regno + " number data are same mark");
						testCaseScenario.log(Status.PASS, "Both " + Paper1 + " and " + paper1Mark
								+ " for the following Register " + regno + " number data are same mark");

					}

					else {
						System.out.println("Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following "
								+ regno + " data are not same please check Excel file or Pdf file Marks");
						testCaseScenario.log(Status.FAIL,
								"Both Excel " + Paper1 + " and Pdf " + paper1Mark + " for the following " + regno
										+ " number data are not same marks",
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

				}

				try {
				if ((paper1Mark < paper1MinMark) && (Paper1 < paper1MinMark)) {
						System.out.println(" The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is failed in exam with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else if ((paper1Mark >= paper1MinMark) && (Paper1 >= paper1MinMark)) {
						System.out.println("The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);
						testCaseScenario.log(Status.PASS, "The following Registration number " + regno
								+ " is passed in exam with marks in PDF: " + paper1Mark + " and in Excel: " + Paper1);

					} else {
						System.out.println("Check the files for the following " + regno
								+ " registration number where Pdf mark is " + paper1Mark + " Excel mark is" + Paper1);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ paper1Mark + " Excel mark is" + Paper1,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				} catch (Exception e) {
					System.out.println(
							"Check the files for the following " + regno + " registration number " + e.getMessage());
					testCaseScenario.log(Status.FAIL,
							"Check the files for the following " + regno + " registration number " + e.getMessage(),
							MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				}
			}
			else {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
				
				   testCaseScenario.log(Status.FAIL,
				              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

				   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
						+ subjectToFind + "and in PDF " +subject);
			}
			
			 }
			 
		else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
			ExtentTest testCaseScenario = testCaseName.createNode(
					markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
			 
					paper1MinMark = minMark;
					 
					Paper1 = 0.0;
					
					paper1Mark = 0.0;
					
					      // Case: AB / NE / NA etc
					 
					      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
					      testCaseScenario.log(Status.INFO,
					              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

			 }
		}

		catch (Exception e) {
			ExtentTest testCaseScenario = testCaseName.createNode(
					markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

	
				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}

	}
	// Method to check if a student passed or failed in Paper 2
		public void Paper2SecMarksValidation(Object regno, String markName,String obtainedMarks,double minMark, Object paper2,String subject, String subjectToFind,
				ExtentTest testCaseName) throws IOException {

			try {
				Set<String> invalidValues = Set.of("AB","AB ", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
			
		
				System.out.println("obtainedMarksPaper2 "+obtainedMarks);
				if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
						
						
						
				if (subject.trim().equals(subjectToFind.trim())) {

					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
		 
				
					Paper2 = objectToDataType(paper2);
					paper2Mark = Double.parseDouble(obtainedMarks);
					paper2MinMark = minMark;

					try {
						if (paper2Mark == Paper2) {

							System.out.println(
									"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following Register "
											+ regno + " number data are same for Theory Univ Sec Marks");
							testCaseScenario.log(Status.PASS,
									"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following Register "
											+ regno + " number data are same for Theory Univ Sec mark");

						}

						else {
							System.out.println("Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following "
									+ regno
									+ " data are not same please check Excel file or Pdf file for Theory Univ Sec mark");
							testCaseScenario.log(Status.FAIL,
									"Both Excel " + Paper2 + " and Pdf " + paper2Mark + " for the following " + regno
											+ " number data are not same for Theory Univ Sec mark",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if ((paper2Mark < paper2MinMark) && (Paper2 < paper2MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

						} else if ((paper2Mark >= paper2MinMark) && (Paper2 >= paper2MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is passed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ "is passed in exam with marks in PDF: " + paper2Mark + " and in Excel: " + Paper2);

						} else {
							System.out.println("Check the files for the following " + regno
									+ " registration number where Pdf mark is " + paper2Mark + " Excel mark is" + Paper2);
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number where Pdf mark is "
											+ paper2Mark + " Excel mark is" + Paper2,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
				}

	

					else {
						ExtentTest testCaseScenario = testCaseName.createNode(
								markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
						
						   testCaseScenario.log(Status.FAIL,
						              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

						   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
								+ subjectToFind + "and in PDF " +subject);
					

				}
			
			 }
			 
			else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
	 
						paper2MinMark = minMark;
						 
						Paper2 = 0.0;
						
						paper2Mark = 0.0;
						
						      // Case: AB / NE / NA etc
						 
						      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
						      testCaseScenario.log(Status.INFO,
						              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

				 }
				
			
			}
			catch (Exception e) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		

			}
		}

		public void Paper3SecMarksValidation(Object regno, String markName,String obtainedMarks,double minMark, Object paper3,String subject, String subjectToFind,
				ExtentTest testCaseName) throws IOException {

			try {
				Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
			
			
				if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
						
						
						
				if (subject.trim().equals(subjectToFind.trim())) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

						 
				
					Paper3 = objectToDataType(paper3);
					paper3Mark = Double.parseDouble(obtainedMarks);
					paper3MinMark = minMark;

					try {
						if (paper3Mark == Paper3) {

							System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark
									+ " for the following Register " + regno + " number data are same Marks");
							testCaseScenario.log(Status.PASS, "Both Excel " + Paper3 + " and Pdf " + paper3Mark
									+ " for the following Register " + regno + " number data are same mark");

						}

						else {
							System.out.println("Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following "
									+ regno + " data are not same mark please check Excel file or Pdf file ");
							testCaseScenario.log(Status.FAIL,
									"Both Excel " + Paper3 + " and Pdf " + paper3Mark + " for the following " + regno
											+ " number data are not same mark ",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if ((paper3Mark < paper3MinMark) && (Paper3 < paper3MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

						} else if ((paper3Mark >= paper3MinMark) && (Paper3 >= paper3MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is passed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is passed in exam with marks in PDF: " + paper3Mark + " and in Excel: " + Paper3);

						} else {
							System.out.println("Check the files for the following " + regno
									+ " registration number where Pdf mark is " + paper3Mark + " Excel mark is" + Paper3);
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number where Pdf mark is "
											+ paper3Mark + " Excel mark is" + Paper3,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
				}

				else {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
					
					   testCaseScenario.log(Status.FAIL,
					              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

					   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
							+ subjectToFind + "and in PDF " +subject);
				

			}
		
				
				 }
				 
				else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
		 
							paper3MinMark = minMark;
							 
							Paper3 = 0.0;
							
							paper3Mark = 0.0;
							
							      // Case: AB / NE / NA etc
							 
							      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
							      testCaseScenario.log(Status.INFO,
							              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

					 }
			
			}
			catch (Exception e) {
				e.printStackTrace();
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
			}
		}
		
		

		public void Paper4SecMarksValidation(Object regno, String markName,String obtainedMarks,double minMark, Object paper4,String subject, String subjectToFind,
				ExtentTest testCaseName) throws IOException {


		
				try {
					Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
				
				
					if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
							
							
							
					if (subject.trim().equals(subjectToFind.trim())) {

							 
						ExtentTest testCaseScenario = testCaseName.createNode(
								markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

						Paper4 = objectToDataType(paper4);
						paper4Mark = Double.parseDouble(obtainedMarks);
						paper4MinMark = minMark;
						
						
					try {
						if (paper4Mark == Paper4) {

							System.out.println("Both Excel " + Paper4 + " and Pdf " + paper4Mark
									+ " for the following Register " + regno + " number data are same Marks");
							testCaseScenario.log(Status.PASS, "Both Excel " + Paper4 + " and Pdf " + paper4Mark
									+ " for the following Register " + regno + " number data are same mark");

						}

						else {
							System.out.println("Both Excel " + Paper4 + " and Pdf " + paper4Mark + " for the following "
									+ regno + " data are not same mark please check Excel file or Pdf file ");
							testCaseScenario.log(Status.FAIL,
									"Both Excel " + Paper4 + " and Pdf " + paper4Mark + " for the following " + regno
											+ " number data are not same mark ",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if ((paper4Mark < paper4MinMark) && (Paper4 < paper4MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is failed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

						} else if ((paper4Mark >=  paper4MinMark) && (Paper4 >= paper4MinMark)) {
							System.out.println("The following Registration number " + regno
									+ " is passed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is passed in exam with marks in PDF: " + paper4Mark + " and in Excel: " + Paper4);

						} else {
							System.out.println("Check the files for the following " + regno
									+ " registration number where Pdf mark is " + paper4MinMark + " Excel mark is" + Paper4);
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number where Pdf mark is "
											+ paper4MinMark + " Excel mark is" + Paper4,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}
				}

					else {
						ExtentTest testCaseScenario = testCaseName.createNode(
								markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
						
						   testCaseScenario.log(Status.FAIL,
						              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

						   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
								+ subjectToFind + "and in PDF " +subject);
					

				}
			
					 }
					 
					else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
						ExtentTest testCaseScenario = testCaseName.createNode(
								markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
		 
								paper4MinMark = minMark;
								 
								Paper4 = 0.0;
								
								paper4Mark = 0.0;
								
								      // Case: AB / NE / NA etc
								 
								      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
								      testCaseScenario.log(Status.INFO,
								              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

						 }
				
				}

			catch (Exception e) {
				e.printStackTrace();
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		

			}
		}
		public void TheoryTotalSecMarksValidation(Object regno,String markName, String obtainedMarks,double minMark,Object theoryExam, String subject, String subjectToFind,ExtentTest testCaseName)
				throws IOException {

			try {
		
				if (subject.trim().equals(subjectToFind.trim())) {
			
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
	
					
					theroryExamTotalMinMark = minMark;
					theoryExcelExamTotal = Paper1 + Paper2 + Paper3 + Paper4;
					System.out.println(theoryExcelExamTotal);

					theoryPdfExamTotal = paper1Mark + paper2Mark + paper3Mark +paper4Mark;
					System.out.println(theoryPdfExamTotal);
					System.out.println(paper1Mark);
					System.out.println(paper2Mark);
					System.out.println(paper3Mark);
					System.out.println(paper4Mark);

					System.out.println(obtainedMarks);
					
					Double theroryExamTotal = objectToDataType(obtainedMarks);

					System.out.println(theoryPdfExamTotal);

					try {
						if (theoryPdfExamTotal == theroryExamTotal && theoryPdfExamTotal == theoryExcelExamTotal) {

							System.out.println("Both Excel " + theroryExamTotal + " and Pdf " + theoryPdfExamTotal
									+ " for the following Register " + regno
									+ " number data are same for theory total mark");
							testCaseScenario.log(Status.PASS,
									"Both Excel " + theroryExamTotal + " and Pdf " + theoryPdfExamTotal
											+ " for the following Register " + regno
											+ " number data are same for theory total mark");

						}

						else {

							System.out.println("Both Excel " + theroryExamTotal + " and Pdf " + theoryPdfExamTotal
									+ " for the following " + regno
									+ " data are not same please check Excel file or Pdf file for theory total mark");
							testCaseScenario.log(Status.FAIL,
									"Both Excel " + theroryExamTotal + " and Pdf " + theoryPdfExamTotal + " for the following "
											+ regno + " number data are not same for theory total mark",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
					}

					try {

						if (paper1Mark < paper1MinMark || paper2Mark < paper2MinMark || paper3Mark < paper3MinMark
								|| Paper1 < paper1MinMark || Paper2 < paper2MinMark || Paper3 < paper3MinMark||Paper4 < paper4MinMark) {
							System.out.println("The following Registration number " + regno
									+ " has failed in one or more papers and is therefore failed in the Theory exam:"
									+ theoryPdfExamTotal);
							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " has failed in one or more papers and is therefore failed in the Theory exam:"
									+ theoryPdfExamTotal);

						}

						else if (theoryPdfExamTotal < theroryExamTotalMinMark && theoryExcelExamTotal < theroryExamTotalMinMark) {
							System.out.println("The following Registration number " + regno
									+ " is failed in Theory Exam with total marks: " + theoryPdfExamTotal);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is failed in Theory Exam with total marks: " + theoryPdfExamTotal);

						} else if (theoryPdfExamTotal >= theroryExamTotalMinMark && theoryExcelExamTotal >= theroryExamTotalMinMark) {
							System.out.println("The following Registration number " + regno
									+ " is passed in Theory Exam with total marks: " + theoryPdfExamTotal);

							testCaseScenario.log(Status.PASS, "The following Registration number " + regno
									+ " is passed in Theory Exam with total marks: " + theoryPdfExamTotal);
						}

						else {
							System.out.println("Pdf mark is " + theoryPdfExamTotal + " Excel mark is" + theoryExcelExamTotal);
							testCaseScenario.log(Status.FAIL,
									"Pdf mark is " + theoryPdfExamTotal + " Excel mark is" + theoryExcelExamTotal,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				}	else {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
					
					   testCaseScenario.log(Status.FAIL,
					              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

					   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
							+ subjectToFind + "and in PDF " +subject);
				

			}
		
			}

			catch (Exception e) {
				e.printStackTrace();
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		

			}
		}
		public void praticalTotalSecMarksValidations(Object regno, String markName,String obtainedMarks,double minMark, Object praticalExam,String subject, String subjectToFind,
				ExtentTest testCaseName) throws IOException {

			try {
				Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
			
				if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
						
						
						
				if (subject.trim().equals(subjectToFind.trim())) {

					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

						 
						 
					practicalMinMark = minMark;
					System.out.println(minMark);

					PraticalExamTotal = objectToDataType(praticalExam);
					praticalTotalSecMark = Double.parseDouble(obtainedMarks);
					
				
			
				
					
					System.out.println(PraticalExamTotal);
					
					System.out.println(praticalTotalSecMark);
					
					try {
						if (praticalTotalSecMark == PraticalExamTotal) {

							System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
									+ " for the following Register " + regno
									+ " number data are same for pratical sec mark");
							testCaseScenario.log(Status.PASS,
									"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
											+ " for the following Register " + regno
											+ " number data are same for pratical sec mark");

						}

						else {

							System.out.println("Both Excel " + praticalTotalSecMark + " and Pdf " + praticalTotalSecMark
									+ " for the following " + regno
									+ " data are not same please check Excel file or Pdf file for pratical sec mark");
							testCaseScenario.log(Status.FAIL,
									"Both Excel " + PraticalExamTotal + " and Pdf " + praticalTotalSecMark
											+ " for the following " + regno
											+ " number data are not same for pratical sec mark",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number where Pdf mark is "
										+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if ((praticalTotalSecMark < practicalMinMark) && (PraticalExamTotal < practicalMinMark)) {
							System.out.println(
									"The following Registration number " + regno + " is failed in Exam with marks in PDF: "
											+ praticalTotalSecMark + " and in Excel: " + PraticalExamTotal);
							testCaseScenario.log(Status.PASS,
									"The following Registration number " + regno + " is failed in Exam with marks in PDF: "
											+ praticalTotalSecMark + " and in Excel: " + PraticalExamTotal);

						} else if ((praticalTotalSecMark >= practicalMinMark) && (PraticalExamTotal >= minMark)) {
							System.out.println(
									"The following Registration number " + regno + " is passed in Exam with marks in PDF: "
											+ praticalTotalSecMark + "and in Excel: " + PraticalExamTotal);
							testCaseScenario.log(Status.PASS,
									"The following Registration number " + regno + " is passed in Exam with marks in PDF: "
											+ praticalTotalSecMark + "and in Excel: " + PraticalExamTotal);
						} else {
							System.out.println(
									"Check the files for the following " + regno + " registration number where Pdf mark is "
											+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal);
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number where Pdf mark is "
											+ praticalTotalSecMark + " Excel mark is" + PraticalExamTotal,
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}

					} catch (Exception e) {

						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				}

				else {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
					
					   testCaseScenario.log(Status.FAIL,
					              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

					   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
							+ subjectToFind + "and in PDF " +subject);
				

			}
		
			
			 }
			 
			else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

					 
				practicalMinMark = minMark;
				System.out.println(minMark);

				PraticalExamTotal = 0.0;
				praticalTotalSecMark = 0.0;
				
					      // Case: AB / NE / NA etc
						 
						      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
						      testCaseScenario.log(Status.INFO,
						              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

				 }
			}
			catch (Exception e) {
				e.printStackTrace();
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				System.out.println(
						"Check the files for the following " + regno + " registration number " + e.getMessage());
				testCaseScenario.log(Status.FAIL,
						"Check the files for the following " + regno + " registration number " + e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		
			}
		}
		// Method to check the final result (Theory Exam + Practical Exam)
		public void grandTotalMarksValidation(Object regno, String markName, String obtainedMarks,double minMark,Object grandTotal, String subject,String subjectToFind,
				ExtentTest testCaseName) throws IOException {


			try {
	
					Set<String> invalidValues = Set.of("AB", "NE", "NA", "NA ", " NA", "---", "NE (AT)", "NE(AT)");
				
				if (obtainedMarks != null && !invalidValues.contains(obtainedMarks.trim())) {
							
							
						
				if (subject.trim().equals(subjectToFind.trim())) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");

				
					System.out.println(obtainedMarks);
					
					pdfGrandTotalMinMark = minMark;
					excelGrandTotal = paper1Mark + paper2Mark + paper3Mark +paper4Mark +praticalTotalSecMark;

					pdfGrandTotal = Paper1 + Paper2 + Paper3 + Paper4+ PraticalExamTotal;

					System.out.println("Paper1Mark:" + paper1Mark);
					System.out.println("Paper2Mark:" + paper2Mark);
					System.out.println("Paper3Mark:" + paper3Mark);
					System.out.println("Paper4Mark:" + paper4Mark);
					System.out.println("Pratical total mark:" + praticalTotalSecMark);
					System.out.println("excelGrandTotal:" + excelGrandTotal);

					System.out.println("Paper1:" + Paper1);
					System.out.println("Paper2:" + Paper2);
					System.out.println("Paper3:" + Paper3);
					System.out.println("Paper4:" + Paper4);
					System.out.println("PraticalExamTotal" + PraticalExamTotal);
					System.out.println("pdfGrandTotal:" + pdfGrandTotal);


					try {
						if ((pdfGrandTotal == excelGrandTotal) && (objectToDataType(grandTotal) == excelGrandTotal) ) {

							System.out.println("\n Both PDF file total value " + excelGrandTotal
									+ " and Excel file total value  " + pdfGrandTotal + " and excel value of EP "+objectToDataType(grandTotal)+" for the following Register " + regno
									+ " number data are same mark in PDF file");
							testCaseScenario.log(Status.PASS,
									"\n Both PDF file total value " + excelGrandTotal + " and Excel file total value  "
											+ pdfGrandTotal + " and excel value of EP "+objectToDataType(grandTotal)+" for the following Register " + regno
											+ " number data are same mark in PDF file");
						}

						else {

							System.out.println("\n Both PDF file total value " + excelGrandTotal
									+ " and Excel file total value  " + pdfGrandTotal + " and excel value of EP "+objectToDataType(grandTotal)+"  for the following Register " + regno
									+ " number data are not same mark in PDF file");
							testCaseScenario.log(Status.FAIL,
									"\n Both PDF file total value " + excelGrandTotal + " and Excel file total value  "
											+ pdfGrandTotal  + " and excel value of EP "+objectToDataType(grandTotal)+ " for the following Register " + regno
											+ " number data are not same mark in PDF file",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if ((excelGrandTotal == pdfGrandTotal)  && (objectToDataType(grandTotal) == excelGrandTotal)) {

							System.out.println("Both Excel file total value " + pdfGrandTotal
									+ " and PDF file total value  " + excelGrandTotal + " and excel value of EP "+objectToDataType(grandTotal)+" for the following Register " + regno
									+ " number data are same mark in Excel file");
							testCaseScenario.log(Status.PASS,
									"Both Excel file total value " + pdfGrandTotal  + " and excel value of EP "+objectToDataType(grandTotal)+" and PDF file total value  "
											+ excelGrandTotal + " for the following Register " + regno
											+ " number data are same mark in Excel file");
						}

						else {

							System.out.println("Both Excel total value " + pdfGrandTotal + " and excel value of EP "+objectToDataType(grandTotal)+" and PDF file total value  "
									+ excelGrandTotal + " for the following Register " + regno
									+ " number data are not same mark in Excel file");
							testCaseScenario.log(Status.FAIL,
									"Both Excel file total value " + pdfGrandTotal  + " and excel value of EP "+objectToDataType(grandTotal)+" and PDF file total value  "
											+ excelGrandTotal + " for the following Register " + regno
											+ " number data are not same mark in Excel file",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}
						
						try {
					 if (Paper1 != 0 && Paper1 < paper1MinMark || paper1Mark != 0 && paper1Mark < paper1MinMark
									|| Paper2 != 0 && Paper2 < paper2MinMark || paper2Mark != 0 && paper2Mark < paper2MinMark
									|| Paper3 != 0 && Paper3 < paper3MinMark || paper3Mark != 0 && paper3Mark < paper3MinMark
									|| PraticalExamTotal != 0 && PraticalExamTotal < practicalMinMark
									|| praticalTotalSecMark != 0 && praticalTotalSecMark < practicalMinMark) {

								System.out.println("The following Registration number " + regno
										+ " has failed in one or more papers and is therefore failed in the grand total exam marks in PDF: "
										+ pdfGrandTotal + " and in Excel: " + excelGrandTotal);
								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " has failed in one or more papers and is therefore failed in the grand total exam marks in PDF: "
										+ pdfGrandTotal + " and in Excel: " + excelGrandTotal);

							}

							else if ((pdfGrandTotal < pdfGrandTotalMinMark) && (excelGrandTotal < pdfGrandTotalMinMark) && (objectToDataType(grandTotal) <pdfGrandTotalMinMark)) {
								System.out.println("The following Registration number " + regno
										+ " is failed in Grand Total with marks  in PDF Total:"+ pdfGrandTotal + " and in Excel Total : " + excelGrandTotal +"and in Excel Ep " +objectToDataType(grandTotal));

								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " is failed in Grand Total with marks  in PDF Total:"+ pdfGrandTotal + " and in Excel Total : " + excelGrandTotal +"and in Excel Ep " +objectToDataType(grandTotal));

							} else if ((pdfGrandTotal >= pdfGrandTotalMinMark) && (excelGrandTotal >= pdfGrandTotalMinMark) && (objectToDataType(grandTotal) >=pdfGrandTotalMinMark)) {
								System.out.println("The following Registration number " + regno
										+ " is passed in Grand Total with marks  in PDF Total:"+ pdfGrandTotal + " and in Excel Total : " + excelGrandTotal +"and in Excel Ep " +objectToDataType(grandTotal));

								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " is passed in Grand Total with marks  in PDF Total:"+ pdfGrandTotal + " and in Excel Total : " + excelGrandTotal +"and in Excel Ep " +objectToDataType(grandTotal));
							}

							else {
								System.out.println("Pdf mark is " + pdfGrandTotal + " Excel mark is" + excelGrandTotal);
								testCaseScenario.log(Status.FAIL,
										"Pdf total mark is " + pdfGrandTotal + " Excel total mark is" + excelGrandTotal + "and Excel EP value is "+objectToDataType(grandTotal),
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							}

						} catch (Exception e) {
							System.out.println(
									"Check the files for the following " + regno + " registration number " + e.getMessage());
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number " + e.getMessage(),
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				}

				else {
					ExtentTest testCaseScenario = testCaseName.createNode(
							markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
					
					   testCaseScenario.log(Status.FAIL,
					              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

					   testCaseScenario.log(Status.FAIL,"Check the subject for the following " + regno + " registration number where subject in excel "
							+ subjectToFind + "and in PDF " +subject);
				

			}
		
			}	 
			 
			else if (obtainedMarks != null && invalidValues.contains(obtainedMarks.trim())) {
				ExtentTest testCaseScenario = testCaseName.createNode(
						markName + " Validation for the Subject " + subjectToFind + "Test case has started running");
	 
				pdfGrandTotalMinMark = minMark;
				System.out.println(minMark);

				excelGrandTotal = 0.0;
				pdfGrandTotal = 0.0;
				
					      // Case: AB / NE / NA etc
						 
						      System.out.println("The following Register number " + regno + " " + markName + " is: " + obtainedMarks);
						      testCaseScenario.log(Status.INFO,
						              "The following Register number " + regno + " " + markName + " is: " + obtainedMarks);

				 }
			}
			catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		public void gracegrandTotalMarksValidationCheck(Object regno,String marksName,String obtainedMarks,double minMark,Object theoryExam,String subject,String subjectToFind, ExtentTest testCaseName ) {
	
			
			
			try {
				if (subject.trim().equals(subjectToFind.trim())) {
					ExtentTest testCaseScenario = testCaseName.createNode(
							"Grand Total Validation for the Subject " + subjectToFind + " Test case has started running");

					if (obtainedMarks.contains("G")) {
						paper3Mark =0.0;
						paper3Mark	 = 0.0; 
					}
					else {
						paper3Mark =0.0;
						paper3Mark	 = 0.0; 
					}
				
					System.out.println(obtainedMarks);
					
					pdfGrandTotalMinMark = minMark;
					excelGrandTotal = paper1Mark + paper2Mark + paper3Mark +paper4Mark +praticalTotalSecMark;

					pdfGrandTotal = Paper1 + Paper2 + Paper3 + Paper4+ PraticalExamTotal;

					System.out.println("Paper1Mark:" + paper1Mark);
					System.out.println("Paper2Mark:" + paper2Mark);
					System.out.println("Paper3Mark:" + paper3Mark);
					System.out.println("Paper4Mark:" + paper4Mark);
					System.out.println("Pratical total mark:" + praticalTotalSecMark);
					System.out.println("excelGrandTotal:" + excelGrandTotal);

					System.out.println("Paper1:" + Paper1);
					System.out.println("Paper2:" + Paper2);
					System.out.println("Paper3:" + Paper3);
					System.out.println("Paper4:" + Paper4);
					System.out.println("PraticalExamTotal" + PraticalExamTotal);
					System.out.println("pdfGrandTotal:" + pdfGrandTotal);


					try {
						if (pdfGrandTotal == excelGrandTotal) {

							System.out.println("\n Both PDF file total value " + excelGrandTotal
									+ " and Excel file total value  " + pdfGrandTotal + " for the following Register " + regno
									+ " number data are same mark in PDF file");
							testCaseScenario.log(Status.PASS,
									"\n Both PDF file total value " + excelGrandTotal + " and Excel file total value  "
											+ pdfGrandTotal + " for the following Register " + regno
											+ " number data are same mark in PDF file");
						}

						else {

							System.out.println("\n Both PDF file total value " + excelGrandTotal
									+ " and Excel file total value  " + pdfGrandTotal + " for the following Register " + regno
									+ " number data are not same mark in PDF file");
							testCaseScenario.log(Status.FAIL,
									"\n Both PDF file total value " + excelGrandTotal + " and Excel file total value  "
											+ pdfGrandTotal + " for the following Register " + regno
											+ " number data are not same mark in PDF file",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

					try {

						if (excelGrandTotal == pdfGrandTotal) {

							System.out.println("Both Excel file total value " + pdfGrandTotal
									+ " and PDF file total value  " + excelGrandTotal + " for the following Register " + regno
									+ " number data are same mark in Excel file");
							testCaseScenario.log(Status.PASS,
									"Both Excel file total value " + pdfGrandTotal + " and PDF file total value  "
											+ excelGrandTotal + " for the following Register " + regno
											+ " number data are same mark in Excel file");
						}

						else {

							System.out.println("Both Excel total value " + pdfGrandTotal + " and PDF file total value  "
									+ excelGrandTotal + " for the following Register " + regno
									+ " number data are not same mark in Excel file");
							testCaseScenario.log(Status.FAIL,
									"Both Excel file total value " + pdfGrandTotal + " and PDF file total value  "
											+ excelGrandTotal + " for the following Register " + regno
											+ " number data are not same mark in Excel file",
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						}
						
						try {
							
							if (
								    ((Paper1 != 0 && Paper1 < paper1MinMark) || (paper1Mark != 0 && paper1Mark < paper1MinMark)) ||
								    ((Paper2 != 0 && Paper2 < paper2MinMark) || (paper2Mark != 0 && paper2Mark < paper2MinMark)) ||
								    ((Paper3 != 0 && Paper3 < paper3MinMark) || (paper3Mark != 0 && paper3Mark < paper3MinMark)) ||
								    ((PraticalExamTotal != 0 && PraticalExamTotal < practicalMinMark) || (praticalTotalSecMark != 0 && praticalTotalSecMark < practicalMinMark)) &&
								    obtainedMarks.contains("G")
								) {
								System.out.println("The following Registration number " + regno
										+ " is passed in with grace marks of "+ paper2Mark +"  in PDF: " + pdfGrandTotal + " and in Excel: " + excelGrandTotal);
								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " is passed in with grace marks of "+ paper2Mark +" in PDF: " + pdfGrandTotal + " and in Excel: " + excelGrandTotal);

								}
							
							
							else if (Paper1 != 0 && Paper1 < paper1MinMark || paper1Mark != 0 && paper1Mark < paper1MinMark
									|| Paper2 != 0 && Paper2 < paper2MinMark || paper2Mark != 0 && paper2Mark < paper2MinMark
									|| Paper3 != 0 && Paper3 < paper3MinMark || paper3Mark != 0 && paper3Mark < paper3MinMark
									|| PraticalExamTotal != 0 && PraticalExamTotal < practicalMinMark
									|| praticalTotalSecMark != 0 && praticalTotalSecMark < practicalMinMark) {

								System.out.println("The following Registration number " + regno
										+ " has failed in one or more papers and is therefore failed in the grand total exam marks in PDF: "
										+ pdfGrandTotal + " and in Excel: " + excelGrandTotal);
								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " has failed in one or more papers and is therefore failed in the grand total exam marks in PDF: "
										+ pdfGrandTotal + " and in Excel: " + excelGrandTotal);

							}

							else if (pdfGrandTotal < pdfGrandTotalMinMark && excelGrandTotal < pdfGrandTotalMinMark) {
								System.out.println("The following Registration number " + regno
										+ " is failed in Grand Total with marks: " + pdfGrandTotal);

								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " is failed in Grand Total with marks:  " + pdfGrandTotal);

							} else if (pdfGrandTotal >= pdfGrandTotalMinMark && excelGrandTotal >= pdfGrandTotalMinMark) {
								System.out.println("The following Registration number " + regno
										+ " is passed in Grand Total with marks:  " + pdfGrandTotal);

								testCaseScenario.log(Status.PASS, "The following Registration number " + regno
										+ " is passed in Grand Total with marks:  " + pdfGrandTotal);
							}

							else {
								System.out.println("Pdf mark is " + pdfGrandTotal + " Excel mark is" + excelGrandTotal);
								testCaseScenario.log(Status.FAIL,
										"Pdf mark is " + pdfGrandTotal + " Excel mark is" + excelGrandTotal,
										MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

							}

						} catch (Exception e) {
							System.out.println(
									"Check the files for the following " + regno + " registration number " + e.getMessage());
							testCaseScenario.log(Status.FAIL,
									"Check the files for the following " + regno + " registration number " + e.getMessage(),
									MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

						}

					} catch (Exception e) {
						System.out.println(
								"Check the files for the following " + regno + " registration number " + e.getMessage());
						testCaseScenario.log(Status.FAIL,
								"Check the files for the following " + regno + " registration number " + e.getMessage(),
								MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());

					}

				}

				else {

					System.out.println("Check the files for the following " + regno + " registration number and subject"
							+ subjectToFind);
				}
			}

			catch (Exception e) {
				e.printStackTrace();

			}
		}

		public double objectToDataType(Object obj) {
			if (obj == null) {
				throw new IllegalArgumentException("Object cannot be null");
			}

			if (obj instanceof Double) {
				return (Double) obj;
			} else if (obj instanceof Integer) {
				return ((Integer) obj).doubleValue();
			} else if (obj instanceof Float) {
				return ((Float) obj).doubleValue();
			} else if (obj instanceof Long) {
				return ((Long) obj).doubleValue();
			} else if (obj instanceof String) {
				String str = (String) obj;
				try {
					return Double.parseDouble(str);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("String value cannot be converted to double: " + str);
				}
			} else {
				throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getSimpleName());
			}
		}
		public void resetAllMarks() {
		    Paper1 = 0.0;
		    Paper2 = 0.0;
		    Paper3 = 0.0;
		    Paper4 = 0.0;

		    theoryExcelExamTotal = 0.0;
		    PraticalExamTotal = 0.0;
		    ExamTotalScor = 0.0;

		    securedMark = 0.0;
		    paper1Mark = 0.0;
		    paper2Mark = 0.0;
		    paper3Mark = 0.0;
		    paper4Mark = 0.0;

		    praticalMinMark = 0.0;
		    paper1MinMark = 0.0;
		    paper2MinMark = 0.0;
		    paper3MinMark = 0.0;
		    paper4MinMark = 0.0;
		    practicalMinMark = 0.0;

		    theroryExamTotalMinMark = 0.0;
		    examTotalScoreMinMark = 0.0;
		    pdfGrandTotalMinMark = 0.0;

		    theoryMinMark = 0.0;
		    theoryMaxMark = 0.0;
		    grandTotalMinMark = 0.0;
		    grandTotalMaxMark = 0.0;
		    theoryPdfExamTotal = 0.0;
		    praticalTotal = 0.0;
		    excelGrandTotal = 0.0;
		    pdfGrandTotal = 0.0;

		    theorySecMark = 0.0;

		    praticalMaxMark = 0.0;
		    praticalVivaMaxMark = 0.0;
		    praticalTotalMaxMark = 0.0;
		    praticalTotalSecMark = 0.0;
		    grandTotalSecMark = 0.0;

		    theoryInternalMaxMark = 0.0;
		    theoryInternalSecMark = 0.0;
		}

}
