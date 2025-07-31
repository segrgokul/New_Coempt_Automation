package scevtPageModules;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import gitaProject.GitaCreateAndWriteExcel;

public class Scevt_RC {
	
	 static String downloadsFolder = System.getProperty("user.home") + "/Downloads";
	 
		String studentRegno ="";
		
	    String branch ="";   
  	    String semester ="";
  	    String studentName ="";
  	  String result="";
	  public void readPdfData(ExtentTest testCaseName) throws IOException {
	  
	    	try {
	        	
	        	
	        	// Find the latest PDF file
	            File latestPDF = getLatestPDF();
	            System.out.println("Downloads folder: " + downloadsFolder);
	     

	            
	            //checks the latest page is not null
	            if (latestPDF != null) {
	       
	            	//to load the latestfile
	        			try (PDDocument document = PDDocument.load(latestPDF)) {
	        				PDFTextStripper stripper = new PDFTextStripper();
	        				int totalPages = document.getNumberOfPages();
	        				System.out.println("Total Pages: " + totalPages);
	        				System.out.println("---------------------------------------------------");


	            
	            //for pages
	        	for (int page = 1;page<=totalPages;page++) {
	        			
	        
	        			
	        		 			
	        					stripper.setStartPage(page);
	        					stripper.setEndPage(page);

	        					
	        					System.out.println("Page " + page + ":");
	        					System.out.println("---------------------------------------------------");
	        					// Extract registration number
	        					//TO print the text
	        					
	        					String text = stripper.getText(document).replaceAll("[\r\n]+", "\n");
	        					System.out.println(text);
	        					
	        					System.out.println("---------------------------------------------------");
	      
	        					
	            	System.out.println("Latest PDF file found: " + latestPDF.getName());
	            	
	            	
	            	
	            	String regno = "\\b([A-Z0-9]+)REGISTRATION NO\\s*:"; //regno pattern

	                  Pattern regnoPattern = Pattern.compile(regno);
	                  Matcher regnoMatcher = regnoPattern.matcher(text);
	                   // Updated regex pattern
	                  if(regnoMatcher.find()) {
	                  	
	                	studentRegno = regnoMatcher.group(0).replaceAll("REGISTRATION NO\\s*:\\s*$", "");
	                 	System.out.println("=================");
	                  	
	                  	System.out.println("studentRegno :"+studentRegno);
	         
	               	 ExtentTest testCaseScenario = testCaseName
	     			            .createNode("Read PDF for the following page no: "+ page +" and studentRegno is : "+studentRegno);
	      			
	     

	                  	
	                  	Pattern namePattern = Pattern.compile("(([A-Z](?:\\.|)?|[A-Z][a-z]+)(\\s+([A-Z](?:\\.|)?|[A-Z][a-z]+))*)NAME\\s*:");


	                  	Matcher matcher = namePattern.matcher(text);
	                  	if (matcher.find()) {
	                  	    studentName = matcher.group(1);
	                  	    System.out.println("Student Name: " + studentName);
	                  	    
	                      	testCaseScenario.log(Status.PASS,"The following page no: "+ page +" Student Name: "+studentName);
	                  	}
	                  	
	                  	Pattern semBranchPattern = Pattern.compile("(.+?),\\s*(\\d+(?:st|nd|rd|th)? Semester)SEMESTER & BRANCH\\s*:");

	                  	Matcher semBranchPatternMatcher = semBranchPattern.matcher(text);
	                  	if (semBranchPatternMatcher.find()) {
	                  	    branch = semBranchPatternMatcher.group(1);   // Civil Engineering
	                  	    semester = semBranchPatternMatcher.group(2); // 6th Semester
	                  	    System.out.println("Branch: " + branch);
	                  	    System.out.println("Semester: " + semester);
	                  	    
	                  	  	testCaseScenario.log(Status.PASS,"The following page no: "+ page +" Branch Name: "+branch);
	                  	  	testCaseScenario.log(Status.PASS,"The following page no: "+ page +" Semester Name: "+semester);
	                  	}
	                  	
	                  	
	                  	
	                  	Pattern resultPattern = Pattern.compile("Result\\s*:\\s*(.+)", Pattern.CASE_INSENSITIVE);
	                  	Matcher resultPatternMatcher = resultPattern.matcher(text);
	                  	if (resultPatternMatcher.find()) {
	                  	    result = resultPatternMatcher.group(1).trim();
	         
	                   	  	testCaseScenario.log(Status.PASS,"The following page no: "+ page +" Result : "+result);
	                  	}
	                  	
	                  	
	                  
	                  
	                  
	                    // Writing data to Excel
//	                  	Scevt_RC_CreateAndWriteExcel.scevtWriteExcelData(
//	                  			page, studentRegno, studentName, branch, semester, result);
//	                  	
	       
	          
	                  	if((result.equals("Pass"))||(result.equals("Pass(G)"))) {
	                
	                  		ExtentTest testCaseScenario1 = testCaseScenario
	         			            .createNode("Read PDF for the following page no: "+ page +" and studentRegno is : "+studentRegno);

	                  	 	testCaseScenario1.log(Status.FAIL,"The following page no: "+ page +" studentRegno " +studentRegno+ " studentName "+studentName+" result" + result);
	                  	
	                  		
	                  	 	Scevt_RC_CreateAndWriteExcel.scevtWriteExcelData1(
		                  			page, studentRegno, studentName,result);
		                  	
	                  	
	                  	
	                 	}
	                  
	                  }
	            	
	            	
	        	}
	        			}}
	    	}
	    	catch(Exception e) {
	    		
	    	}
}
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
	  

}
