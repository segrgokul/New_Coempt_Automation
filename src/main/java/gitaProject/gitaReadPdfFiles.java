package gitaProject;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class gitaReadPdfFiles {

    static String downloadsFolder = System.getProperty("user.home") + "/Downloads";

    String studentSemester;
    boolean isSemester1 = false;
    boolean isSemester2 = false;
   
    String totalTO100;
    String totalTM;

    String cgpa ; // 7.45 (characters at index 4-6)
    String sgpa ; // 9.00 (characters at index 8-10)
    String totalCredit ; // Last 2 digits = 18   
    String dummy;
    public void readPdfData(ExtentTest testCaseName) throws IOException {
    	 ExtentTest testCaseScenario = testCaseName
		            .createNode("Read PDF write Excel Test case has started running");

    	try {
        	
        	
        	// Find the latest PDF file
            File latestPDF = getLatestPDF();
            System.out.println("Downloads folder: " + downloadsFolder);
            testCaseScenario.log(Status.PASS, "Latest PDF file is loaded successfully");

            
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
        			
        					
        		 			
        					stripper.setStartPage(page);
        					stripper.setEndPage(page);

        					
        					
        					//TO print the text
        					
        					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
        		//			System.out.println(text);
        					
        					System.out.println("Page " + page + ":");
        					System.out.println("---------------------------------------------------");
        					// Extract registration number
      
        					
            	System.out.println("Latest PDF file found: " + latestPDF.getName());

//            	String regno = "(?m)^\\d{10}Reg\\s+No\\s*:$";
             	String regno = "(?m)^\\d{10}"; //regno pattern

                  Pattern regnoPattern = Pattern.compile(regno);
                  Matcher regnoMatcher = regnoPattern.matcher(text);
                   // Updated regex pattern
                  if(regnoMatcher.find()) {
                  	
                	String studentRegno = regnoMatcher.group(0);
                 	System.out.println("=================");
                  	
                  	System.out.println("studentRegno :"+studentRegno);

                 
//subjects and marks pattern                
String subjectAndMarks = "(\\d+)"                          // (1) Course Number
+ "\\s+([A-Za-z.&\\s/\\-]+[A-Za-z])"    // (2) Subject Name
+ "\\s+([A-Z0-9]+(?:\\s+[A-Z0-9]+)?)"    // (3) Subject Code (simplified)
+ "\\s+(\\d+)"                          // (4) Credit Hours
+ "\\s+([TP])"                          // (5) Subject Type
+ "\\s+(\\d+|-)"                        // (6) Internal Marks 1
+ "\\s+(\\d+|-)"                        // (7) Internal Marks 2
+ "\\s+(\\d+|-)"                        // (8) Internal Marks 3
+ "\\s+(\\d+|-)"                        // (9) Internal Marks 4
+ "(?:\\s+(-|\\d+))?"                   // (10) Optional Field 1
+ "(?:\\s+(-|\\d+))?"                   // (11) Optional Field 2
+ "(?:\\s+(-|\\d+))?"                   // (12) Optional Field 3
+ "(?:\\s+(-|\\d+))?"                   // (13) Optional Field 4
+ "\\s+(\\d+|-)"                        // (14) Final Marks 1
+ "(?:\\s+(-|\\d+))?"                   // (15) Final Marks 2
+ "\\s+(\\d+)"                          // (16) Final Marks 3
+ "\\s+(\\d+)"                          // (17) Total Marks
+ "\\s+([A-Z])";                        // (18) Grade

Pattern subjectAndMarkspattern = Pattern.compile(subjectAndMarks, Pattern.DOTALL);
Matcher subjectAndMarksmatcher = subjectAndMarkspattern.matcher(text);

String currentSemester = null;  // Stores the actual semester name

String lastTotalCredits = null;
String lastSGPACGPA = null;
String currentSGPACGPA1 = null;
Pattern semPattern = Pattern.compile("Semester\\s*[IVXLCDM]+");
Matcher semMatcher = semPattern.matcher(text);


Pattern totalCreditPattern = Pattern.compile("(\\d{1,2})\\s*Total\\s*Credit\\s*:");
Matcher totalCreditsMatcher = totalCreditPattern.matcher(text);

Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*(\\d+)\\s*(\\d*\\.\\d+)\\s*(\\d*\\.\\d+).*SGPA:.*CGPA:.*Total:");

Matcher sgpaCgpaMatcher = pattern.matcher(text);


// Store semester positions in the text
Map<Integer, String> semesterPositions = new TreeMap<>();
Map<Integer, String> totalCreditsPositions = new TreeMap<>();
Map<Integer, String> sgpaCgpaPositions = new TreeMap<>();
while (semMatcher.find()) {
    semesterPositions.put(semMatcher.start(), semMatcher.group());
}

while (totalCreditsMatcher.find()) {
	
	totalCreditsPositions.put(totalCreditsMatcher.start(), totalCreditsMatcher.group());
}

while (sgpaCgpaMatcher.find()) {
    sgpaCgpaPositions.put(sgpaCgpaMatcher.start(), sgpaCgpaMatcher.group());
}
// Start processing subjects
while (subjectAndMarksmatcher.find() ) {
    int matchStart = subjectAndMarksmatcher.start(); // Get the start position of the current match
    
    // Assign semester dynamically based on subject position
    for (Map.Entry<Integer, String> entry : semesterPositions.entrySet()) {
        if (matchStart >= entry.getKey()) {
            currentSemester = entry.getValue();  // Update semester
        } else {
            break;
        }
    }
    
    Iterator<Map.Entry<Integer, String>> iterator = totalCreditsPositions.entrySet().iterator();

    Map.Entry<Integer, String> current = null;
    Map.Entry<Integer, String> next = null;

    if (iterator.hasNext()) {
        current = iterator.next();
    }

    while (iterator.hasNext()) {
        next = iterator.next();

        if (lastTotalCredits == null) {
            if (matchStart <= current.getKey()) {
                lastTotalCredits = current.getValue();
            }
     
        } else if (matchStart >= current.getKey()) {
            lastTotalCredits = next.getValue();

            // Print the next value if available
//            System.out.println("Next value after " + lastTotalCredits + " is: " + next.getValue());
            
        } else {
            break;
        }
    
    
        current = next;
    }
    // Process SGPA and CGPA
    Iterator<Map.Entry<Integer, String>> sgpaCgpaIterator = sgpaCgpaPositions.entrySet().iterator();
    Map.Entry<Integer, String> currentSGPACGPA = null;
    Map.Entry<Integer, String> nextSGPACGPA = null;

    if (sgpaCgpaIterator.hasNext()) {
        currentSGPACGPA = sgpaCgpaIterator.next();
 //       System.out.println("currentSGPACGPA: " + currentSGPACGPA);
       
    }
    else if(!sgpaCgpaIterator.hasNext()) {
        currentSGPACGPA = sgpaCgpaIterator.next();
}
    else {
    	System.out.println("Better");
    }
  
    
    while (sgpaCgpaIterator.hasNext()) {
        nextSGPACGPA = sgpaCgpaIterator.next();
     	
        
 //       System.out.println("Next value after " + lastSGPACGPA + " is: " + nextSGPACGPA.getValue());
        if (lastSGPACGPA == null) {
            if (matchStart <= currentSGPACGPA.getKey()) {
                lastSGPACGPA = currentSGPACGPA.getValue();
            }
            else if(matchStart == currentSGPACGPA.getKey()) {
          	  lastSGPACGPA = currentSGPACGPA.getValue();
 //         	  System.out.println("Next value after " + lastSGPACGPA + " is: " + nextSGPACGPA.getValue());
          	
          }
            else {
            	
            	System.out.println("HI");
            	
            }
            
            
        } else if (matchStart >= currentSGPACGPA.getKey()) {
            lastSGPACGPA = nextSGPACGPA.getValue();
            
     //       System.out.println("Next value after " + lastSGPACGPA + " is: " + nextSGPACGPA.getValue());
        }
        
        
        
        
        else {
            break;
        }
        currentSGPACGPA = nextSGPACGPA;
    }
    currentSGPACGPA1 =currentSGPACGPA.getValue();

    // Ensure we always have a valid semester
    if (currentSemester == null) {
        continue;  // Skip this subject if no semester was detected before it
    }
    
    
    String serialNo = subjectAndMarksmatcher.group(1);
    String subjectNames = subjectAndMarksmatcher.group(2).trim();
    String subjectCode = subjectAndMarksmatcher.group(3);
    
   // String extraSubjectCode = subjectAndMarksmatcher.group();
    String credit = subjectAndMarksmatcher.group(4);
    String type = subjectAndMarksmatcher.group(5);
    String st = subjectAndMarksmatcher.group(6);
    String qt = subjectAndMarksmatcher.group(7);
    String as = subjectAndMarksmatcher.group(8);
    String mte = subjectAndMarksmatcher.group(9);
    String emptyColumn1 = subjectAndMarksmatcher.group(10) != null ? subjectAndMarksmatcher.group(10) : "N/A";
    String emptyColumn2 = subjectAndMarksmatcher.group(11) != null ? subjectAndMarksmatcher.group(11) : "N/A";
    String emptyColumn3 = subjectAndMarksmatcher.group(12) != null ? subjectAndMarksmatcher.group(12) : "N/A";
    String to40 = subjectAndMarksmatcher.group(13);
    String endSemExam = subjectAndMarksmatcher.group(14); //15 skipped due to pattern
    String to100 = subjectAndMarksmatcher.group(16);
    String totalMarks = subjectAndMarksmatcher.group(17);
    String grade = subjectAndMarksmatcher.group(18);

    System.out.println("=================");

    System.out.println("studentRegno: " + studentRegno);
    System.out.println("studentSemester: " + currentSemester);  // Correct Semester Name
    System.out.println("Serial No: " + serialNo);
    System.out.println("Subject Name: " + subjectNames);
    System.out.println("Subject Code: " + subjectCode);
//    System.out.println("Extra Subject Code: " + extraSubjectCode);
    System.out.println("Cr: " + credit);
    System.out.println("Type (T/P): " + type);
    System.out.println("ST (5): " + st);
    System.out.println("QT (5): " + qt);
    System.out.println("AS (5): " + as);
    System.out.println("MTE (25): " + mte);
    System.out.println("Empty Column1: " + emptyColumn1);
    System.out.println("Empty Column2: " + emptyColumn2);
    System.out.println("Empty Column3: " + emptyColumn3);
    System.out.println("TO (40): " + to40);
    System.out.println("End Sem Exam (60): " + endSemExam);
    System.out.println("TO (100): " + to100);
    System.out.println("Total Marks: " + totalMarks);
    System.out.println("Grade: " + grade);
    System.out.println("=================");
    
    
 //   System.out.println("currentSGPACGPA: " + currentSGPACGPA);
 //   System.out.println("Total Credit: " + lastTotalCredits.replaceAll("(\\d{1,2}).*", "$1"));

   
    
//    System.out.println(lastSGPACGPA+"lastSGPACGPA");
    
    if (lastSGPACGPA != null||currentSGPACGPA !=null) {
        // Assuming lastSGPACGPA contains something like "245 3007.459.0018Total Credit : SGPA: CGPA: Total:"
    	String source = (lastSGPACGPA != null && !lastSGPACGPA.isEmpty()) ? lastSGPACGPA : currentSGPACGPA1;
    	String[] parts = source.split("[\\s.]+");

        
        
//        System.out.println(lastSGPACGPA+"lastSGPACGPA");
        
      
        
        if(parts[2].length() == 4) {
        totalTO100 = parts[1];
        totalTM = parts[2].substring(0,3);
   
        
        System.out.println("totalTO100: " +totalTO100 );
        System.out.println("totalTM: " +totalTM);
        
        cgpa =parts[2].substring(3) +"." +parts[3].substring(0, 2);
 //       System.out.println("mofdlknsdkjdh" +cgpa);
        
        System.out.println("CGPA: " + cgpa);
        if(parts[3].substring(1,3).contains("10")) {
//        System.out.println("mofdlknsdkkjsdfhsdfjhkjdh" +parts[3].substring(1,3) +"." +parts[4].substring(0, 2));
        sgpa =parts[3].substring(1,3) +"." +parts[4].substring(0, 2);
        }
        else {
        	
 //       	  System.out.println("mofdlknsdkkjsdfhsdfnbvbnnvbjhkjdh" +parts[3].substring(2) +"." +parts[4].substring(0, 2)); 	
        	  sgpa =parts[3].substring(2) +"." +parts[4].substring(0, 2);
        }
        
        System.out.println("SGPA: " + sgpa);   
        }
        
        
        else if(parts[2].length() == 5) {
        	totalTO100 = parts[1];
        	totalTM = parts[2].substring(0,4);
        	   cgpa = parts[3];
   
            System.out.println("totalTO100: " +totalTO100 );
            System.out.println("totalTM: " +totalTM);
            cgpa =parts[2].substring(4) +"." +parts[3].substring(0, 2);
//            System.out.println("mofdlknsdkjdh" +parts[2].substring(4) +"." +parts[3].substring(0, 2));
            System.out.println("CGPA: " + cgpa);
            if(parts[3].substring(1,3).contains("10")) {
 //               System.out.println("mofdlknsdkkjsdfhsdfjhkjdh" +parts[3].substring(1,3) +"." +parts[4].substring(0, 2));
                sgpa = parts[3].substring(1,3) +"." +parts[4].substring(0, 2);
                }
                else {
                	
 //               	  System.out.println("mofdlknsdkkjsdfgdfhsdfjhkjdh" +parts[3].substring(2) +"." +parts[4].substring(0, 2)); 
                	  sgpa = parts[3].substring(2) +"." +parts[4].substring(0, 2);
                }
                
            
          System.out.println("SGPA: " + sgpa);
            }
        
        else if(parts[2].length() == 6) {
        	totalTO100 = parts[1];
        	totalTM = parts[2].substring(0,4);
        	  cgpa =parts[2].substring(4) +"." +parts[3].substring(0, 2);
               sgpa = parts[4];
            System.out.println("totalTO100: " +totalTO100 );
            System.out.println("totalTM: " +totalTM);
//            System.out.println("mofdlknsdkjdh" +parts[2].substring(3) +"." +parts[3].substring(0, 2));
            System.out.println("CGPA: " + cgpa);
            
            if(parts[3].substring(1,3).contains("10")) {
 //               System.out.println("mofdlknsdkkjsdfhsdfjhkjdh" +parts[3].substring(1,3) +"." +parts[4].substring(0, 2));
                sgpa =parts[3].substring(1,3) +"." +parts[4].substring(0, 2);
                }
                else {
                	
//                	  System.out.println("mofdlknsdkkjxcfvsdfhsdfjhkjdh" +parts[3].substring(2) +"." +parts[4].substring(0, 2)); 
                	  sgpa = parts[3].substring(2) +"." +parts[4].substring(0, 2);
                }
                
          System.out.println("SGPA: " + sgpa);
            }
        
        else {
        	
        	System.out.println(parts[2].length()+"Length of the part");	
        }
        
        
 //       System.out.println(parts[3]+"fkjhdskjfd");
 //       System.out.println(parts[4]+"dsjfhfkjhsk");
        
        totalCredit = parts[4].substring(2, 4); 
        System.out.println("Total Credit: " + totalCredit);
        System.out.println("=================");
        
//        if (parts.length >= 3) {
//        	
//        	dummy = String.valueOf(parts[2].length());
//        	
//        	 if(parts[2].length() == 21) {
//        		 System.out.println("dskjfkdjh :" + lastSGPACGPA);		
//         		totalTM = parts[2].substring(0,4);
//         		   cgpa = parts[2].substring(4,8);
//         		   totalTO100  = parts[1];
//                    sgpa = parts[2].substring(8, 12);
//                    totalCredit = parts[2].substring(12, 14);
//         	}
//        	
//        else if(parts[2].length() == 19) {
//        	
//        	
//        	
//        		totalTM = parts[2].substring(0,4);
//        		   cgpa = parts[2].substring(4,8);
//        		   totalTO100  = parts[1];
//                   sgpa = parts[2].substring(8, 12);
//                   totalCredit = parts[2].substring(12, 14);
//        	}
//        	else if (parts[2].length() == 18) {
//        		
//        		totalTM = parts[2].substring(0,3);
//        		cgpa = parts[2].substring(3,7);
//        		totalTO100  = parts[1];
//               sgpa = parts[2].substring(7, 11);
//               totalCredit = parts[2].substring(11, 13);     	
//     	}else {
//     		System.out.println(parts[2].length()+"Length of the part");
//     	}
//        	
//        	
       
     
//           String sgpa = parts[2].substring(8, 12); // 9.00 (characters at index 8-10)
  //         String totalCredit = parts[2].substring(parts[2].length() - 12); // Last 2 digits = 18

//            System.out.println("TotalMaxMark : " + totalTM);
//            System.out.println("TotalSecMark: " + totalTO100);
//            System.out.println("CGPA: " + cgpa);
//          System.out.println("SGPA: " + sgpa);
//            System.out.println("Total Credit: " + totalCredit);
        }

    else {
    	System.out.println("FAIL Next value after " + lastSGPACGPA + " is: " + nextSGPACGPA.getValue());
    	System.out.println("hjdsa");
    }
    System.out.println("----------------------------------------------------");

     
        
        
        
    
    
    
    
    
    // Writing data to Excel
    gitaCreateAndWriteExcel.writeExcelData(
        rowIndex, studentRegno, currentSemester, serialNo, subjectNames, subjectCode, credit, type, 
        st, qt, as, mte, emptyColumn1, emptyColumn2, emptyColumn3, to40, endSemExam, to100, totalMarks, grade,
        totalCredit,sgpa,cgpa,totalTO100,totalTM);

    rowIndex++;
}//subjects and marks pattern while loop ends
         
} //regno if patttern ends   
            
}//for loop ends for page
  
}	//load the latestfile end
                   
} //checks the latest page is not null ends
            
            else {
                System.out.println("No PDF files found in the Downloads folder.");
            }
        }//tryEnds
        
        catch (Exception e) {
            e.printStackTrace();
            testCaseScenario.log(Status.FAIL, e.getMessage());
       }
        
    
    
    }//tryEnds
       

        
    

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
}
