package nizamProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import io.reactivex.rxjava3.subjects.Subject;

public class Nizam extends BasicFunctions {

    static String downloadsFolder = System.getProperty("user.home") + "/Downloads";


    String studentSemester;
    boolean isSemester1 = false;
    boolean isSemester2 = false;
   
    String totalTO100;
    String totalTM;
	String grade;
    String cgpa ; // 7.45 (characters at index 4-6)
    String sgpa ; // 9.00 (characters at index 8-10)
    String totalCredit ; // Last 2 digits = 18   
    String result;
    String studentRegno;
    double subjectMaxMarks;
    double subjectMinMarks;
    double internalMaxMarks;
    double internalSecMarks;
     
    double  externalMaxMarks;
    double externalMinMarks;
    double externalSecMarks;
    double modMarks;
    double graceMarks;
    double externalTotals;
    double subjectTotals;
    double gradePoint;
    ExtentTest testCaseScenario;
    double finalSGPA;
    List<Double> creditPointList = new ArrayList<>();
    List<Double> gradePointList = new ArrayList<>();
    
    double credit;
    
	String finalResultPDF = null;
	String finalResultCAL = null;
	ExtentTest subNode;
    boolean isPass;
    boolean isFail;
    public void readPdfData( ExtentReports extentReport ,ExtentTest testCaseName) throws IOException {
  	    	try {
        	
  	    		
	
        	// Find the latest PDF file
            File latestPDF = getLatestPDF();
            System.out.println("Downloads folder: " + downloadsFolder);
//            testCaseScenario.log(Status.PASS, "Latest PDF file is loaded successfully");

            
            //checks the latest page is not null
            if (latestPDF != null) {
       
            	//to load the latestfile
        			try (PDDocument document = PDDocument.load(latestPDF)) {
        				PDFTextStripper stripper = new PDFTextStripper();
        				int totalPages = document.getNumberOfPages();
        				System.out.println("Total Pages: " + totalPages);
        				System.out.println("---------------------------------------------------");

        				// Iterate through all pages and extract text
        		//		for (int page = 1; page <= totalPages; page++) {
        	
        
        	int semIndex=1;
        	int regIndex=1;
            int rowIndex = 1;
            
            //for pages
        	for (int page = 1;page<=totalPages;page++) {
        			
        	
        		testCaseName =extentReport.createTest("Nizam Actions for the following "+ page +" page");
        					stripper.setStartPage(page);
        					stripper.setEndPage(page);

        					
        					
        					//TO print the text
        					
        					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
        		     		System.out.println(text);
        					
        					System.out.println("Page " + page + ":");
        					System.out.println("---------------------------------------------------");
        					// Extract registration number
      
        					
            	System.out.println("Latest PDF file found: " + latestPDF.getName());

//            	String regno = "(?m)^\\d{10}Reg\\s+No\\s*:$";
            // 	String regno = "(?m)^\\d{10}"; //regno pattern
            	String htnoPattern = "HTNO\\s*:\\s*(\\d+)";

            	
                  Pattern regnoPattern = Pattern.compile(htnoPattern);
                  Matcher regnoMatcher = regnoPattern.matcher(text);
                   // Updated regex pattern
                  
                	String subjectAndMarksPattern =
                  		    "(?s)"  // Dot-all mode to allow line breaks
	                  		  + "\\d+\\s+"     +                            // Roll number or subject code
	                  		// "[A-Z\\s\\(\\)\\-]+?(?:II{0,2}|III?|IV|V|VI{0,3}|VII|VIII|IX|X)?(?:\\s+PR\\.)?\\s*\\n?\\s*"
//"[A-Z\\s\\(\\)\\-]+(?:\\sI{0,3})?(?:\\s+PR\\.)?\\s*\\n?\\s*"
"(?s)[A-Z\\s&\\(\\)\\-\\[\\]]+(?:\\s(?:I|II|III|IV|V|VI|VII|VIII|IX|X)(?:[A-Z])?)?(?:\\s*-?\\s*\\[RV\\])?(?:\\s+PR\\.?)?\\s*"              		

	                  	+	"(?:\\([A-Z]+\\))?\\s*"
           // Optional subject category like (AECC)
                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 1
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 2
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 3
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 4
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 5
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 6
	                  		  + "(?:\\d+|AB|-)\\s+"                          // Marks 7
	                  		  + "(?:\\d+|AB|-)\\s+"                        // Marks 8
	                  		  + "(?:\\d+|AB|-)\\s+"                        // Marks 9
	                  		  + "(?:\\d+|AB)\\s+"                          // Total
	                  		  + "\\d+\\s+"                                 // Grade points or 
	                  		  + "[A-Za-z]+\\s+"                            // Result (PASS, FAIL, etc.)
	                  		  + "[A-Za-z\\+]+\\s+"                         // Grade (A+, B, etc.)
	             
	                  		  +  "(?:\\d+|AB|-)\\s+" ;                                    // Final number (ID or subject code)

                    // Possibly subject or student ID

                          // Possibly an extra number
                       // ID
	                	
	                	// (18) Grade
                   // (14) Credit
                                                // (14) Credit

	
	Pattern pattern = Pattern.compile(subjectAndMarksPattern, Pattern.DOTALL | Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(text);

                  
                  while(regnoMatcher.find()) {
        	        	     	
                	studentRegno = regnoMatcher.group(0);
                 	System.out.println("=================");
                  	
                  	System.out.println(studentRegno);
                  
                    testCaseScenario = testCaseName.createNode("Nizam  Marks validation for the following "+ page +" page for the following regno "+ studentRegno); 			
        	          


//                  	String subjectAndMarksPattern =
//                  		    "(?s)"  // Dot-all mode to allow line breaks
//                  		  + "\\d+\\s+"                                 // Roll number or subject code
//                  		  + "[A-Z\\s\\(\\)\\-]+?I?\\s*\\n?\\s*"        // Subject name (single or multi-line), optionally ending with 'I'
//                  		  + "(?:\\([A-Z]+\\))?\\s*"                    // Optional subject category like (AECC)
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 1
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 2
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 3
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 4
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 5
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 6
//                  		  + "(?:\\d+|AB)\\s+"                          // Marks 7
//                  		  + "(?:\\d+|AB|-)\\s+"                        // Marks 8
//                  		  + "(?:\\d+|AB|-)\\s+"                        // Marks 9
//                  		  + "(?:\\d+|AB)\\s+"                          // Total
//                  		  + "\\d+\\s+"                                 // Grade points or credit
//                  		  + "[A-Za-z\\+]+\\s+"                         // Grade (A+, B, etc.)
//                  		  + "[A-Za-z]+\\s+"                            // Result (PASS, FAIL, etc.)
//                  		  + "\\d+";                                    // Final number (ID or subject code)


            


while (matcher.find()) {
	

	
	String allPatternTexts = matcher.group(0);
	System.out.println("allPatternTexts: " + allPatternTexts);
	String combined = allPatternTexts.replaceAll("\\s*\\n\\s*", " ").trim();

	System.out.println("combined"+ combined);
	String[] parts = combined.trim().split("\\s+");

	// Extract Semester
	String semester = parts[0];

	// Dynamically extract subject until first number
	StringBuilder subjectBuilder = new StringBuilder();
	int i = 1;
	while (i < parts.length && !parts[i].matches("\\d+")) {
	    subjectBuilder.append(parts[i]).append(" ");
	    i++;
	}
	String subject = subjectBuilder.toString().trim();

	// Now continue from index i to extract known positions
	int index = i;

	String subjectMaxMark = parts[index++];
	String subjectMinMark = parts[index++];
	String internalMaxMark = parts[index++];
	String internalSecMark = parts[index++];
	String externalMaxMark = parts[index++];
	String externalMinMark = parts[index++];
	String markSecured = parts[index++];
	
	

	String modMark = parts[index++];
	String graceMark = parts[index++];
	String externalTotal = parts[index++];
	String subjectTotal = parts[index++];
	result = parts[index++];
	String gradeLetters = parts[index++];
	String credits = parts[index++];
	
//	subjectMinMarks = Double.parseDouble(subjectMinMark);

//	externalMinMarks = Double.parseDouble(externalMinMark);
//	externalMarksSecured = Double.parseDouble(markSecured);
	
	String currentHTNO = "";
	List<Subject> currentSubjects = new ArrayList<>();
	Map<String, List<Subject>> studentMap = new HashMap<>();

	String[] lines = text.split("\\r?\\n");

	for (String line : lines) {
	    
	    Matcher htnoMatcher = regnoPattern.matcher(line);
	    if (htnoMatcher.find()) {
	        String newHTNO = htnoMatcher.group(1);
	        
	        // If we already had an HTNO, save its subjects
	        if (!currentHTNO.isEmpty()) {
	            studentMap.put(currentHTNO, currentSubjects);
	            currentSubjects = new ArrayList<>();
	        }
	        
	        currentHTNO = newHTNO;
	    }
	    
	    // If this is a subject line, parse and add
	  
	}

	// Save last HTNO
	if (!currentHTNO.isEmpty()) {
	    studentMap.put(currentHTNO, currentSubjects);
	}
	
	System.out.println("----------------------------------------");
	System.out.println("Semester: " +semester);
	System.out.println("Subject: " +subject);

	System.out.println("Subject Max Marks: " + subjectMaxMark);
	System.out.println("Subject Min Marks: " + subjectMinMark);
	System.out.println("Internal Max Marks: " + internalMaxMark);
	System.out.println("Internal Sec Marks: " + internalSecMark);
	System.out.println("External Max Marks: " + externalMaxMark);
	System.out.println("External Min Marks: " + externalMinMark);
	System.out.println("Marks Secured: " + markSecured);
	
	if(markSecured.equals("AB*")) {
		
		finalResultCAL = "MALPRACTICE";
	}
	
	
	System.out.println("Moderation Mark: " + modMark);
	System.out.println("Grace Mark: " + graceMark);
	System.out.println("External Total: " + externalTotal);
	System.out.println("Subject Total: " + subjectTotal);
	System.out.println("Result: " + result);
 		
	System.out.println("Grade: " + gradeLetters);
	System.out.println("Credits: " + credits);
	
	if(credits.equals("-")) {
		credit =0.0;
		
		creditPointList.add(credit);
	}
	
	else {
		
		credit = Double.parseDouble(credits);
		
		creditPointList.add(credit);			
	}
	

	System.out.println("----------------------------------------");		
	
	subNode = testCaseScenario.createNode(
			" Subject " + subject + " Validation Test case for the following regno "+ studentRegno+" has started running");
	

	
	Pattern finalResultPattern=	Pattern.compile("(?i)\\b(PROMOTED|PASSED|MALPRACTICE)\\b", Pattern.CASE_INSENSITIVE);
	Matcher finalResultPatternMatcher =finalResultPattern.matcher(text);

	if (finalResultPatternMatcher.find()) {
	System.out.println("yes");
	finalResultPDF = finalResultPatternMatcher.group(1);
//	    System.out.println("finalResult: " + finalResultPDF+ "pagenos" +page);
//	    subNode.log(Status.PASS,"finalResult: " + finalResultPDF+ "pagenos" +page);	
//
	    
}
else {
	System.out.println("bye");
	
}
	
	
	
	
	
	
	
	if ((result.trim().equals("Pass") ||result.trim().equals("PASS") || result.trim().equals("Fail") ||result.trim().equals("FAIL"))){
	

		

			 
			  
			  
		  
		 
		  
			try {
				
				if (!internalSecMark.equals("-")||!internalSecMark.equals("AB")||!internalSecMark.equals("NE")) {
			
				internalMaxMarks = Double.parseDouble(internalMaxMark);
				internalSecMarks = Double.parseDouble(internalSecMark);
		
				subNode.log(Status.PASS,"The following subject "+ subject +" internal sec marks is: " + internalSecMark);
				
				System.out.println("The following subject "+ subject +" internal sec marks is: " + internalSecMark);
				}
				// Use the value
			} catch (NumberFormatException e) {

				if (internalSecMark.equals("AB") || 
						internalSecMark.equals("-") || 
						internalSecMark.equals("NA") || 
						internalSecMark.equals("NE (AT)")) {
				
					internalSecMarks = 0.0;
					System.out.println(internalSecMarks);

		//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
				
					subNode.log(Status.INFO,"The following subject "+ subject +" internal sec marks is: " + internalSecMark);
				
				System.out.println("The following subject "+ subject +" internal sec marks is: " + internalSecMark);
					
			
				
				}
				
				else {

					internalSecMarks = 0.0;
					System.out.println(internalSecMarks);

		//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
				
					subNode.log(Status.INFO,"The following subject "+ subject +" internal sec marks is: " + internalSecMark);
				
				System.out.println("The following subject "+ subject +" internal sec marks is: " + internalSecMark);
					
			
				
		
				}}
			
try {
				
				if (!markSecured.equals("-")||!markSecured.equals("AB")||!markSecured.equals("NE")) {
			
				externalMinMarks = Double.parseDouble(externalMinMark);
				externalSecMarks = Double.parseDouble(markSecured);
		
				subNode.log(Status.PASS,"The following subject "+ subject +" external sec marks is: " + markSecured);
				
				System.out.println("The following subject "+ subject +" external sec marks is: " + markSecured);

				}
				// Use the value
			} catch (NumberFormatException e) {

				if (markSecured.equals("AB") || 
						markSecured.equals("-") || 
						markSecured.equals("NA") || 
						markSecured.equals("NE (AT)")) {
					
					
					if (markSecured.equals("AB") && modMark.equals("-")) {
						externalSecMarks = 0.0;
						System.out.println(externalSecMarks);

			//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
					
						subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + markSecured);
					
					System.out.println("The following subject "+ subject +" external sec marks is: " + markSecured);
						
						}
						else if (markSecured.equals("AB") && !modMark.equals("-")) {
						
							externalSecMarks = 0.0;
							System.out.println(externalSecMarks);

				//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
						
							subNode.log(Status.FAIL," please check the following subject "+ subject +" where external sec marks is: " + markSecured + "and modMarks is " +modMark);
						
						System.out.println( "please check the following subject "+ subject +" where external sec marks is: " + markSecured + "and modMarks is " +modMark);
							
						
						
						}
							
			
			
				
				}
				
				else {
					externalSecMarks = 0.0;
					System.out.println(externalSecMarks);

	//			ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
				
					subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + markSecured);
				
				System.out.println("The following subject "+ subject +" external sec marks is: " + markSecured);
							
				}
			}
			
try {
	
	if (!modMark.equals("-")||!modMark.equals("AB")||!modMark.equals("NE")) {


		modMarks = Double.parseDouble(modMark);

	subNode.log(Status.PASS,"The following subject "+ subject +" mod sec marks is: " + modMark);
	
	System.out.println("The following subject "+ subject +" mod sec marks is: " + modMark);

	}
	// Use the value
} catch (NumberFormatException e) {

	if (modMark.equals("AB") || 
			modMark.equals("-") || 
			modMark.equals("NA") || 
			modMark.equals("NE (AT)")) {
		modMarks = 0.0;
		System.out.println(externalSecMarks);

//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
	
		subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + modMark);
	
	System.out.println("The following subject "+ subject +" external sec marks is: " + modMark);
	

	}
	
	else {
		modMarks = 0.0;
		System.out.println(externalSecMarks);

//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
	
		subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + modMark);
	
	System.out.println("The following subject "+ subject +" external sec marks is: " + modMark);
				
	}
}	
try {
	
	if (!graceMark.equals("-")||!graceMark.equals("AB")||!graceMark.equals("NE")) {


		graceMarks = Double.parseDouble(graceMark);

	subNode.log(Status.PASS,"The following subject "+ subject +" grace sec marks is: " + graceMark);
	
	System.out.println("The following subject "+ subject +" grace sec marks is: " + graceMark);

	}
	// Use the value
} catch (NumberFormatException e) {

	if (graceMark.equals("AB") || 
			graceMark.equals("-") || 
			graceMark.equals("NA") || 
			graceMark.equals("NE (AT)")) {
	
		graceMarks = 0.0;
		System.out.println(graceMarks);

//	ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
	
		subNode.log(Status.INFO,"The following subject "+ subject +" grace sec marks is: " + graceMark);
	
	System.out.println("The following subject "+ subject +" grace sec marks is: " + graceMark);
		

	
	}
	
	else {
		graceMarks = 0.0;
		System.out.println(graceMarks);
//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
	
		subNode.log(Status.INFO,"The following subject "+ subject +" grace sec marks is: " + graceMark);
	
	System.out.println("The following subject "+ subject +" grace sec marks is: " + graceMark);
				
	}
}	
			try {
				
				if (!externalTotal.equals("-")||!externalTotal.equals("AB")||!externalTotal.equals("NE")) {
			
				externalMinMarks = Double.parseDouble(externalMinMark);
				externalTotals = Double.parseDouble(externalTotal);
		
				checkMarks("External total",result,subject,markSecured,externalMinMarks, subNode);
				}
				// Use the value
			} catch (NumberFormatException e) {

				if (externalTotal.equals("AB") || 
						externalTotal.equals("-") || 
						externalTotal.equals("NA") || 
						externalTotal.equals("NE (AT)")) {
				
					externalSecMarks = 0.0;
					System.out.println(externalSecMarks);

		//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
				
					subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + externalTotal);
				
				System.out.println("The following subject "+ subject +" external sec marks is: " + externalTotal);
					
			
				
				}
				
				else {
					externalSecMarks = 0.0;
					System.out.println(externalSecMarks);

	//			ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
				
					subNode.log(Status.INFO,"The following subject "+ subject +" external sec marks is: " + markSecured);
				
				System.out.println("The following subject "+ subject +" external sec marks is: " + markSecured);
							
				}}
			
				//subject total
				try {
					
					if (!subjectTotal.equals("-")||!subjectTotal.equals("AB")||!subjectTotal.equals("NE")) {
					subjectMaxMarks = Double.parseDouble(subjectMaxMark);
					subjectMinMarks =Double.parseDouble(subjectMinMark);	
					subjectTotals = Double.parseDouble(subjectTotal);
			
					checkMarks("subject total",result,subject,markSecured,subjectMinMarks, subNode);
					}
					// Use the value
				} catch (NumberFormatException e) {

					if (subjectTotal.equals("AB") || 
							subjectTotal.equals("-") || 
							subjectTotal.equals("NA") || 
							subjectTotal.equals("NE (AT)")) {
						subjectMaxMarks = Double.parseDouble(subjectMaxMark);
						subjectMinMarks=0.0;
						subjectTotals = 0.0;
						System.out.println(subjectTotals);

			//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
					
						subNode.log(Status.INFO,"The following subject "+ subject +" subject total marks is: " + subjectTotal);
					
					System.out.println("The following subject "+ subject +" subject total marks is: " + subjectTotal);
						
				
					
					}
					
					else {
						subjectMaxMarks = Double.parseDouble(subjectMaxMark);
						subjectMinMarks=0.0;
						subjectTotals = 0.0;
						System.out.println(subjectTotals);

			//		ExtentTest testCaseScenario = testCaseName.createNode("External sec. marks validation for the subject " + subject +" Test case has started running");
					
						subNode.log(Status.INFO,"The following subject "+ subject +" subject total marks is: " + subjectTotal);
					
					System.out.println("The following subject "+ subject +" subject total marks is: " + subjectTotal);
						
							
					}}
					
					try {
						
						System.out.println("Grand garde total is running");
						if (!gradeLetters.equals("NA")) {
							double percentage = (subjectTotals / subjectMaxMarks) * 100;
							
							getGrade(percentage);
					
						// Check Grand Total Sec. Marks (assumed max marks as 200)				
							checkMarks("Grade Letters",result,subject,gradeLetters,percentage, subNode);
							
						}
						else {
							if (gradeLetters.equals("AB") || 
									gradeLetters.equals("-") || 
									gradeLetters.equals("---") || 
									gradeLetters.equals("AP") ||
									gradeLetters.equals("NE (AT)")) {
								
							subNode.log(Status.INFO,"\n Please check The Following subject " + subject +" for the Subject "+ subject 
									 + " Grade letter  is: " + gradeLetters);
							
							 System.out.println("\n Please check The Following subject " + subject
										+ " Grade letter is:" + gradeLetters);	
						}}
					// Use the value
					} catch (NumberFormatException e) {
						if (gradeLetters.equals("AB") || 
								gradeLetters.equals("NE") || 
								gradeLetters.equals("---") || 
								gradeLetters.equals("AP") ||
								gradeLetters.equals("NE (AT)")) {
							
						
						

							subNode.log(Status.INFO,"\n Please check The Following subject " + subject +" for the Subject "+ subject 
									 + " Grade letter  is: " + gradeLetters);
							
							 System.out.println("\n Please check The Following subject " + subject
										+ " Grade letter is:" + gradeLetters);	
								

					}	
						
						else {
							subNode.log(Status.INFO,"\n Please check The Following subject " + subject +" for the Subject "+ subject 
									 + " Grade letter  is: " + gradeLetters);
							
							 System.out.println("\n Please check The Following subject " + subject
										+ " Grade letter is:" + gradeLetters);	
						}
						
					
				}
	
					
	
}// catch	
	
	     // Updated regex pattern
	    if(subject.equals("COMPUTER HARDWARE (SEC-II)")) {
	    	
	    	
	    	break;
	    }
                  }//if

	
	}//while
                  creditPointListTotalCreation(text,page,subNode);
                  
                  finalTextEtraction(text,page,subNode);
                  
                  calculateSGPA(creditPointList,  gradePointList);
  	    	    
                  }//for loop
        	
        	
	
        			}
        			
            }
      
            
  	    	}
    catch(Exception e){
        		System.out.println("Hi");
        		e.printStackTrace();
        	}}
    
  	    	
    public void creditPointListTotalCreation(String text,int page,ExtentTest subNode) { 
    String htnoPatterns = "HTNO\\s*:\\s*(\\d+)";

	
    Pattern regnoPatterns = Pattern.compile(htnoPatterns);
    Matcher regnoMatchers = regnoPatterns.matcher(text);
     // Updated regex pattern
    if(regnoMatchers.find()) {
//  	testCaseScenario	  = testCaseName
//            .createNode(" Credit validation for the following "+ page +" page");	
  	String studentRegnos = regnoMatchers.group(0);
   	System.out.println("=================");


  System.out.println(studentRegnos);
    	System.out.println(studentRegno);
      
    	if(studentRegnos.matches(studentRegno)) {
    		double creditPointListValueSum = 0;
    		double gradePointListValueSum = 0;
    		
    		for (Double creditPointListValue : creditPointList) {
    			creditPointListValueSum = creditPointListValueSum + creditPointListValue;
    		}
    		
    		for (Double gradePointListValue : gradePointList) {
    			gradePointListValueSum = gradePointListValueSum + gradePointListValue;
    			
    		}
    		
    		
    		subNode.log(Status.FAIL,"creditPointList: " + creditPointList+ " pagenos: " +page);	
    		System.out.println("creditPointListValueSum: " + creditPointListValueSum);
    		subNode.log(Status.FAIL,"TotalcreditPointList: " + creditPointListValueSum+ " pagenos: " +page);	
    		
    		subNode.log(Status.FAIL,"gradePointList: " + gradePointList+ " pagenos: " +page);	
    		System.out.println("gradePointListValueSum: " + gradePointListValueSum);
    		subNode.log(Status.FAIL,"TotalgradePointList: " + gradePointListValueSum+ " pagenos: " +page);	
    		calculateSGPA(creditPointList,  gradePointList);
    		creditPointList.clear();
    		gradePointList.clear();
    	}
    
    	
    }
    }
    
    public void externaltotalMarkValidation(String result,String subject , String markSecured,double externalMinMark,ExtentTest subNode) throws IOException {

		try {
	   		
			
			if ((result.trim().equalsIgnoreCase("Pass")) || (result.trim().equalsIgnoreCase("Fail"))){
				
			
		System.out.println(externalSecMarks+graceMarks+modMarks);
		
		double externalTotalMarks = externalSecMarks+graceMarks+modMarks;
		System.out.println(externalTotalMarks);
		
		try {
			if (externalTotalMarks == externalTotals) {
				
				System.out.println(" The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ externalTotalMarks +"  is equals with externalTotal : " + externalTotals );
				subNode.log(Status.PASS," The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ externalTotalMarks +"  is equals with externalTotal : " + externalTotals  );
			}
			
			else {
				System.out.println(" The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ externalTotalMarks +"  is not equals with externalTotal : " + externalTotals );
				subNode.log(Status.FAIL," The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ externalTotalMarks +"  is not equals with externalTotal : " + externalTotals  );
			}
		}
		catch(Exception e) {
				subNode.log(Status.FAIL,"Please check the following subject " +subject+ e.getMessage());
		}
		
		
	
try {
	
	System.out.println("externalMinMarks:" +externalMinMarks);
	System.out.println("externalSecMarks:" +externalSecMarks);
	System.out.println("externalTotalMarks:" +externalTotalMarks);
	
	
				if (externalTotals < externalMinMarks ) {
					System.out.println(" The following subject " + subject
							+ " is failed with marks in PDF: " + externalTotals );

					subNode.log(Status.PASS," The following subject " + subject
							+ " is failed with marks in PDF: " + externalTotals );

				} else if (externalTotals >= externalMinMarks) {
					System.out.println(" The following subject " + subject
							+ " is Passed with marks in PDF: " + externalTotals );

					subNode.log(Status.PASS," The following subject " + subject
							+ " is Passed with marks in PDF: " + externalTotals );

				} else {
					System.out.println( "Check the files for the subject " + subject );
					testCaseScenario.log(Status.FAIL, "Check the files for the following " + subject 
							);

				}

			} catch (Exception e) {
				System.out.println( "Check the files for the subject " + subject );
				testCaseScenario.log(Status.FAIL, "Check the files for the following " + subject +"externalMinMarks " +externalMinMarks);

			}

		}
			
		
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
    }			
		
    public void subjecttotalMarkValidation(String result,String subject , String markSecured,double minMark,ExtentTest subNode) throws IOException {
    	
		try {
	   		
			
			if ((result.trim().equalsIgnoreCase("Pass")) || (result.trim().equalsIgnoreCase("Fail"))){
				
			
		System.out.println(externalSecMarks+graceMarks+modMarks);
		
		double subjectTotalMarks = externalTotals + internalSecMarks;
		System.out.println(subjectTotalMarks);
		
		try {
			if (subjectTotalMarks == subjectTotals) {
				
				System.out.println(" The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ subjectTotalMarks +"  is equals with externalTotal : " + subjectTotals );
				subNode.log(Status.PASS," The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ subjectTotalMarks +"  is equals with externalTotal : " + subjectTotals  );
			}
			
			else {
				System.out.println(" The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ subjectTotalMarks +"  is not equals with externalTotal : " + subjectTotals );
				subNode.log(Status.FAIL," The following subject " + subject
						+ " mark secured plus mod marks plus grace marks with in PDF "+ subjectTotalMarks +"  is not equals with externalTotal : " + subjectTotals  );
			}
		}
		catch(Exception e) {
				subNode.log(Status.FAIL,"Please check the following subject " +subject+ e.getMessage() );
		}
		
		
	
try {
	if (externalTotals < externalMinMarks ) {
		System.out.println(" The following subject " + subject
				+ " is failed in external sec marks therfore failed in external total with marks in PDF: " + externalTotals );

		subNode.log(Status.PASS," The following subject " + subject
				+ " is failed in external sec marks therfore failed in external total with marks in PDF: " + externalTotals );
		
         isFail = true;
         isPass = false;
         System.out.println(isFail);
         System.out.println(isPass);
	}
	
else if (subjectTotals < subjectMinMarks && subjectTotalMarks < subjectMinMarks ) {
					System.out.println(" The following subject " + subject
							+ " is failed with marks in PDF: " + subjectTotalMarks );

					subNode.log(Status.PASS," The following subject " + subject
							+ " is failed with marks in PDF: " + subjectTotalMarks );

			         isFail = true;
			         isPass = false;
			         System.out.println(isFail);
			         System.out.println(isPass);
				} else if ((subjectTotals >= subjectMinMarks) && (subjectTotalMarks >= subjectMinMarks)) {
					System.out.println(" The following subject " + subject
							+ " is Passed with marks in PDF: " + subjectTotalMarks );

					subNode.log(Status.PASS," The following subject " + subject
							+ " is Passed with marks in PDF: " + subjectTotalMarks );

			         isFail = false;
			         isPass = true;
			         System.out.println(isFail);
			         System.out.println(isPass);
				} else {
					System.out.println( "Check the files for the subject " + subject );
					testCaseScenario.log(Status.FAIL, "Check the files for the following " + subject 
							);

				}

			} catch (Exception e) {
				System.out.println( "Check the files for the subject " + subject );
				testCaseScenario.log(Status.FAIL, "Check the files for the following " + subject 
						);

			}

		}
			
		
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
				}

		
	

    

	public void gradeLetterValidation( String results,String subjects,String gradeLetter,ExtentTest subNode) throws IOException {


		try {
			
			
			if ((result.trim().equalsIgnoreCase("Pass")) || (result.trim().equalsIgnoreCase("Fail"))){

		
				System.out.println("grade" + grade);
				
				System.out.println("gradeLetter" +gradeLetter);

				try {
					if (grade.equals(gradeLetter)) {
						System.out.println("Both grade letter from PDF file " + gradeLetter + " and from calucation " + grade
								+ " for the following subject " + subjects + "  data are same " );
						subNode.log(Status.PASS, "Both grade letter from PDF file " + gradeLetter + " and from calucation " + grade
								+ " for the following subject " + subjects + " data are same " +gradePoint);

					}

					else {
						System.out.println("Both grade letter from PDF file " + gradeLetter + " and from calucation " + grade
								+ " for the following subject " + subjects + "  data are not same");
						subNode.log(Status.PASS, "Both grade letter from PDF file " + gradeLetter + " and from calucation " + grade
								+ " for the following subject " + subjects + " data are not same");

					}

				} catch (Exception e) {
					System.out.println("Check the files for the following " + subjects
							+ " subject " + e.getMessage());
					subNode.log(Status.FAIL,
							"Check the files for the following " + subjects + " subject "
									+ e.getMessage()
							);

				}
			
				
					}}
					 catch (Exception e) {
							System.out.println("Check the files for the following " + subjects
									+ " subject " + e.getMessage());
							subNode.log(Status.FAIL,
									"Check the files for the following " + subjects + " subject "
											+ e.getMessage()
									);

						e.printStackTrace();
							
							
}
		finalResultCalculation(subNode);	
	}
    
    
    
    

    // Find the latest PDF file
    public File getLatestPDF() {
        File folder = new File(downloadsFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            return null;
        }

        File[] pdfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles == null || pdfFiles.length == 0) {
            return null;
        }

        return Arrays.stream(pdfFiles)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }

    // Validate the PDF
    public boolean validatePDF(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            return !document.isEncrypted();
        } catch (IOException e) {
            System.out.println("Error validating PDF: " + e.getMessage());
            return false;
        }
    }
    
 // Helper function to check if the marks are greater than 50% of max marks
	public void checkMarks(String markName, String result,String subject, String markSecured,
			double minMarks, ExtentTest testCaseName) throws IOException {

		System.out.println(markSecured);


		System.out.println(markName);

	
//            System.out.println("checking " + markName + " for the following "+ regno);


//            	System.out.println("Marks: of following register number " + regno  +" student subject is " +markName +" (greater than 50% of max marks)");
//           	testCaseScenario.log(Status.PASS,"Marks: of following register number"+regno + " student subject is " + markName +"(greater than 50% of max marks)");
//            
			
				// System.out.println("Marks: " + marksValue + " (not greater than 50% of max
				// marks)");
				// testCaseScenario.log(Status.PASS,"Marks: " + marksValue + " (not greater than
				// 50% of max marks)");
			

			try {

				if ((result.trim().equalsIgnoreCase("Pass") || result.trim().equalsIgnoreCase("Fail")) &&
	 (markName.trim().contains("External total"))) {

		
					externaltotalMarkValidation(result,subject,markSecured,minMarks,testCaseName);
		
	 }	
				else if ((result.trim().equalsIgnoreCase("Pass") || result.trim().equalsIgnoreCase("Fail")) &&
						 (markName.trim().contains("subject total"))) {
					subjecttotalMarkValidation (result,subject,markSecured,minMarks,testCaseName);
				}
				
				else if ((result.trim().equalsIgnoreCase("Pass") || result.trim().equalsIgnoreCase("Fail")) &&
						 (markName.trim().contains("Grade Letters"))) {
					 gradeLetterValidation(result,subject,markSecured,testCaseName);
					 
					
				}
					
}
			 catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	


	public String getGrade(double percentage) {
	    
	    
	    if (percentage >= 80) {
	        grade = "O";
	        gradePoint=10;
	        
	        gradePointList.add(gradePoint);
	    } else if (percentage >= 75) {
	        grade = "A+";
	        gradePoint=9;
	        gradePointList.add(gradePoint);
	        
	    } else if (percentage >= 60) {
	        grade = "A";
	        gradePoint=8;
	        gradePointList.add(gradePoint);
	    } else if (percentage >= 55) {
	        grade = "B+";
	        gradePoint=7;
	        gradePointList.add(gradePoint);
	    } else if (percentage >= 50) {
	        grade = "B";
	        gradePoint=6;
	        gradePointList.add(gradePoint);
	    } else if (percentage >= 45) {
	        grade = "C";
	        gradePoint=5;
	        gradePointList.add(gradePoint);
	    } else if (percentage < 45 && percentage >= 40) {
	        grade = "D";
	        gradePoint=4;
	        gradePointList.add(gradePoint);
	    }else if (percentage < 40 && percentage >= 0) {
	        grade = "F";
	        gradePoint=0;
	        gradePointList.add(gradePoint);
	    }  
	    else {
	        grade = "Invalid";
	    }
	    
	    System.out.println("Percentage: " + percentage + "%, Grade: " + grade);
	    return grade;
	}


public void finalResultCalculation(ExtentTest subNode) {
	 // Check if any value is "Fail"
  

    if (isFail) {
        System.out.println("Promoted");

        finalResultCAL ="PROMOTED";
        
        subNode.log(Status.PASS,"finalResultPDF: "+ finalResultPDF + " finalResultCAL: "+ finalResultPDF);	
    } else if(isPass) {
        System.out.println("Passed");
        
        finalResultCAL = "PASSED";
        subNode.log(Status.PASS,"finalResultPDF: "+ finalResultPDF + " finalResultCAL: "+ finalResultPDF);	
    }
    else if (finalResultCAL.contains("MALPRACTICE")) {
       	
    	System.out.println("MalPractice");
        subNode.log(Status.PASS,"finalResultPDF: "+ finalResultPDF + " finalResultCAL: "+ finalResultPDF);	
    }
    else {
    	System.out.println("FAIL");
        subNode.log(Status.FAIL,"finalResultPDF: "+ finalResultPDF + " finalResultCAL: "+ finalResultPDF);
    }
    
}

public void finalTextEtraction(String text,int page,ExtentTest subNode) {
	

//previous right pattern
            
 //   String pattern = "(?<=Sem Credits SGPA Total Result\\s*)\\d+\\s+(-\\s+-|\\d+\\s+(\\d+\\.\\d+|\\d+)\\s+\\d+)\\s+\\w+";

	String pattern = "(?<=Sem Credits SGPA Total Result\\s*)\\d+\\s+(-\\s+-|\\d+\\s+(\\d+\\.\\d+|\\d+)\\s+\\d+)\\s+\\w+";
        	
	
	// Compile the pattern and match it against the text
    Pattern compiledPattern = Pattern.compile(pattern);
    Matcher matcher = compiledPattern.matcher(text);
    
    // Check if the pattern matches and print the result
    if (matcher.find()) {
        System.out.println(matcher.group());
        
        System.out.println("matcher.group()" + matcher.group());
        
        System.out.println("matcher.group(0)"+matcher.group(0));
      
        String[] parts = matcher.group(0).split(" ");  // Split by space

        // Loop through and print each part
        for (String part : parts) {
            System.out.println(part);
        }
        
        String semester = parts[0]; 
        
        String credits = parts[1];
       
        double sgpa;
        if(parts[2].contains("-")) {
        	
        sgpa =0.0;
        }
        else {
         sgpa =Double.parseDouble(parts[2]);
        }
        String total = parts[3];

    	subNode.log(Status.WARNING,"creditsLetter: "+ matcher.group() +"  pagenos: " +page);
    	
    	subNode.log(Status.WARNING,"semester: " +semester);
    	subNode.log(Status.WARNING,"credits: " +credits);
    	subNode.log(Status.WARNING,"sgpa: " +sgpa);
    	subNode.log(Status.WARNING,"total: " +total);
    	
    	
    	System.out.println(creditPointList);
    	

    		subNode.log(Status.FAIL,"sgpa: " +sgpa +"finalSGPA" +finalSGPA) ;		
    		
    	
    	
    	
    	

    } else {
        System.out.println("No match found.");
    	subNode.log(Status.FAIL,"matcher.group(0):"+matcher.group(0)+ "  pagenos: " +page);

    }
	
}






    public double calculateSGPA(List<Double> creditPoints, List<Double> gradePoints) {
        // Check if the lists are of the same size
        if (creditPoints.size() != gradePoints.size()) {
            throw new IllegalArgumentException("Credit points list and grade points list must have the same size.");
        }

        // Calculate total credit points using streams
        double totalCreditPoints = creditPoints.stream().mapToDouble(Double::doubleValue).sum();
        
        // Calculate weighted grade points using a for loop
        double weightedGradePoints = 0;
        
        
        System.out.println("creditPoints"+creditPoints);
        
        System.out.println("gradePoints"+gradePoints);
        
        
        
        for (int i = 0; i < creditPoints.size(); i++) {
        	
        	System.out.println("creditPoints" +creditPoints.get(i));
        	
        	System.out.println("gradePoints" +gradePoints.get(i));
        	
        	double multiple =creditPoints.get(i) * gradePoints.get(i);
        	
        	System.out.println("multiple" +multiple);
        	
            weightedGradePoints += creditPoints.get(i) * gradePoints.get(i);
            System.out.println("weightedGradePoints"+weightedGradePoints);
            
        }

        // Calculate SGPA and assign it to finalSGPA variable
        finalSGPA = weightedGradePoints / totalCreditPoints;
System.out.println("finalSGPA"+finalSGPA);
        
        // Return the finalSGPA
        return finalSGPA;
    }











}


