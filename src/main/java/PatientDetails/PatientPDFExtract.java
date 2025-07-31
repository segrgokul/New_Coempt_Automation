package PatientDetails;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import net.sourceforge.tess4j.*;
import scevtPageModules.Scevt_RC_CreateAndWriteExcel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
public class PatientPDFExtract {
	
	
	ExtentReports extentReport ;
	 ExtentSparkReporter report ;
	
	  
	public void method1( ExtentReports extentReport ) throws IOException {

		        try {
		            // Load PDF
		            File file = new File("C:/Users/User/Downloads/07172025 JISA.pdf");
		            PDDocument document = PDDocument.load(file);
		            PDFRenderer pdfRenderer = new PDFRenderer(document);
		        	int totalPages = document.getNumberOfPages();
		        	System.out.println("totalPages "+ totalPages);
		            // Set up Tesseract
		            Tesseract tesseract = new Tesseract();
		            tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); // Update if needed
		            tesseract.setLanguage("eng");
		 
		            //for pages
		        	for (int page = 0;page<totalPages;page++) {
		                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300); // 300 DPI for good accuracy
		                int pageno = (page + 1);
		                ExtentTest  testCaseName = extentReport.createTest("Read PDF for the following page no: "+ pageno );
		      			
		           
		                // Save image for debug (optional)
		                File imgFile = new File("page_" + page + ".png");
		                ImageIO.write(bufferedImage, "png", imgFile);
		 
		                // OCR
		                String text = tesseract.doOCR(bufferedImage).replaceAll("[\r\n]+", "\n");
		            	System.out.println("Page " + pageno + ":");
    					System.out.println("---------------------------------------------------");
    			
    					PDFTextStripper stripper = new PDFTextStripper();
    				//	String text1 = text.replaceAll("[\r\n]+", "\n");
    					stripper.setStartPage(page);
    					stripper.setEndPage(page);

    					String text1 = stripper.getText(document);
		            
    					 
		                
		                System.out.println(text1);
		                
		                
		             
		                String[] lines = text.split("\\r?\\n");
		            	String name = null ;
                		String dob = null ;
                		String gender = null;
                		String chart = null;
                		String ins = null;
                		String 	provider = null;    
                		 
		                for (String line : lines) {

		                    ExtentTest testCaseScenario = testCaseName
		     			            .createNode("Read PDF for the following page no: "+ pageno );
		              	  testCaseScenario.log(Status.PASS,"PAGE " + (pageno ) + ":\\n" +"Page Text : "+ line);
		             

		                  
		                    

		                    

    
//			                    Pattern namePattern = Pattern.compile(
//	                    	    "(?:\\bI)?([A-Z][a-zA-Z'\\-]*,\\s*[A-Z][a-zA-Z'\\-]*(?:\\s+[A-Z][a-zA-Z'\\-]*)*\\*{0,2})(?=\\s+(?:I?D?O?B):?)",
//	                    	    Pattern.CASE_INSENSITIVE);

		              	  
		              
		                  
	                        ExtentTest testCaseScenario1 = testCaseScenario
		     			            .createNode("Read PDF for the following page no: "+ pageno );
	                    		
                  		
	                		
	                		Pattern insPattern = Pattern.compile(
			                    	    "(?:[IJ]?INS1|INS|S1)\\s*[:;\\].]?\\s*(?<ins>.*?)(?=\\s*(?:[IJW]?VISIT|WISIT|ISIT)\\s*#[:;.]?)",
			                    	    Pattern.CASE_INSENSITIVE
			                    	);
			                    	Matcher insPatternMatcher = insPattern.matcher(line);

			                    	if (insPatternMatcher.find()) {
			                    		
			                    	    ins = insPatternMatcher.group("ins").trim();
			                    	    
			                    	     // Extract provider name
			                    	    Pattern doctorPattern = Pattern.compile("\\*?\\(?ACH[)}]?[)}]?[)}]?\\)?\\s*[A-Z]+[A-Z \\-]*PA-C", Pattern.CASE_INSENSITIVE);
			                    	    Matcher doctorMatcher = doctorPattern.matcher(line);

			                    
			                    	    if (doctorMatcher.find()) {
			                    	        provider = doctorMatcher.group().trim();
			                    	    }
			                    	    else {
			                         	    provider = "Not found";
			                    	    }

			                    	    // Logging
			                    	
			                    	    testCaseScenario1.log(Status.FAIL, "Page no " + pageno + " ins of the patient: " + ins);
			                    	    testCaseScenario1.log(Status.FAIL, "Page no " + pageno + " provider of the patient: " + provider);
			                    	
			                    	    System.out.println("Page no " + pageno + " ins of the patient: " + ins);
				                    	 
			                    	    System.out.println("Page no " + pageno + " provider of the patient: " + provider);
			                    	   
			                    	}
	                		
	                		
			                    	 
				                    	
	                		
	                		
//			                    	Pattern namePattern = Pattern.compile(
//					              		    "(?:\\bI)?([A-Z][a-zA-Z'\\-]*,\\s*[A-Z][a-zA-Z'\\-]*(?:[-\\s]+[A-Z][a-zA-Z'\\-]*)*\\*{0,2})(?=\\s+(?:I?D?O?B|OOB):?)",
//					              		    Pattern.CASE_INSENSITIVE);
//					                    Matcher namePatternMatcher = namePattern.matcher(line);
					           
			                    	Pattern namePattern = Pattern.compile(
			                    		    "^\\s*(?:I)?([A-Z][a-zA-Z'\\-]*,\\s*[A-Z][a-zA-Z'\\-]*(?:[-\\s]+[A-Z][a-zA-Z'\\-]*)*\\*{0,2})\\s+(?=I?D?O?B:|OOB:)",
			                    		    Pattern.CASE_INSENSITIVE);
			                        Matcher namePatternMatcher = namePattern.matcher(line);
							           
		                    if (namePatternMatcher.find()) {
		                   
		                    	
		              name   = namePatternMatcher.group();
		                    	System.out.println("Page no " +pageno + " Name of the patient " +name);
		                        testCaseScenario1.log(Status.FAIL, "Page no " +pageno + " Name of the patient " +name);
		                        
		                    
		                  
		                    
		                    
		                    
		            
		                    Pattern dobPattern = Pattern.compile(
		                    	    "(?:I?D?O?B:|IOB:|OOB:)\\s*(\\d{2}/\\d{2}/\\d{4})",
		                    	    Pattern.CASE_INSENSITIVE
		                    	);
		                    Matcher dobPatternMatcher = dobPattern.matcher(line);
		                    if (dobPatternMatcher.find()) {
		                    	
		                    	
		              dob   = dobPatternMatcher.group();
		                    	System.out.println("Page no " +pageno + " Name of the patient " +dob);
		                          testCaseScenario1.log(Status.FAIL, "Page no " +pageno + " dob of the patient " +dob);
		                       
		                    }
		                   
		                    
		                    Pattern chartPattern = Pattern.compile("CHART\\s*#\\s*[:]?\\s*(?<chart>\\d+)",
		                    	    
		                    	    Pattern.CASE_INSENSITIVE
		                    	);
 
		                    Matcher chartPatternMatcher = chartPattern.matcher(line);
		                    if (chartPatternMatcher.find()) {
		                    	
		                    	
		                    	chart   = chartPatternMatcher.group();
		                    	System.out.println("Page no " +pageno + " chart number " +chart);
		                        testCaseScenario1.log(Status.WARNING, "Page no " +pageno + " chart number " +chart);
		                        
		                    }
		                    
//		                    Pattern insPattern = Pattern.compile(
//		                    	    "\\b(?:[IJ]?INS1|S1)\\b\\s*[:;]?\\s*(?<ins>.*?)(?:\\s*SIT\\b.*)?$",
//		                    	    Pattern.CASE_INSENSITIVE
//		                    	);
		                 

		                    
		                    
		                    Pattern genderPattern = Pattern.compile("SEX:\\s*([MF])_?");
		                    Matcher genderPatternMatcher = genderPattern.matcher(line);
		                
		                    
		                    if (genderPatternMatcher.find()) {

		  		              gender   = genderPatternMatcher.group();
		  		                    	System.out.println("Page no " +pageno + " gender of the patient " +gender);
		  		                         testCaseScenario1.log(Status.FAIL, "Page no " +pageno + " gender of the patient " +gender);
		  		              
		  		                    }
		                    
		                    Scevt_RC_CreateAndWriteExcel.scevtWriteExcelData1(
  	                        		pageno, name,dob,gender,chart,ins,provider);
		            
		                    }
		                    
			                    	}
//		                	Pattern pattern = Pattern.compile(
//		                		    "(?:D?OB:|OB:)\\s*(?<dob>\\d{2}/\\d{2}/\\d{4})\\s*SEX:\\s*(?<sex>[MF])[_\\s|]*[jJ]?INS2:\\s*CHART\\s*#:\\s*(?<chart>\\d+)",
//		                		    Pattern.CASE_INSENSITIVE
//		                		);
//		                    
//		                    System.out.println("PAGE " + (pageno ) + ":\n" +"Page Text : "+ line);
//		                    testCaseScenario.log(Status.PASS,"PAGE " + (pageno ) + ":\\n" +"Page Text : "+ line);
//		                    Matcher matcher = pattern.matcher(line);
//		                    if (matcher.find()) {
//		                   //     String name = matcher.group("name").trim();
//		                        String dob = matcher.group("dob");
//		                        String gender = matcher.group("sex");
//		                        String chart = matcher.group("chart");
//		                        ExtentTest testCaseScenario1 = testCaseScenario
//			     			            .createNode("Read PDF for the following page no: "+ pageno );
//		               //         testCaseScenario1.log(Status.FAIL,"---------------------------------");
//		                        
//		                        System.out.println("-------------------------------");
//		                   //     System.out.println("Name: " + name);
//		                //        testCaseScenario1.log(Status.FAIL,"Name: " + name);
//		                        System.out.println("DOB: " + dob);
//		               //         testCaseScenario1.log(Status.FAIL,"DOB: " + dob);
//		                        System.out.println("Gender: " + gender);
//		               //         testCaseScenario1.log(Status.FAIL,"Gender: " + gender);
//		                        System.out.println("Chart #: " + chart);
//		              //          testCaseScenario1.log(Status.FAIL,"Chart #: " + chart);
//		              //        System.out.println("-------------------------------");
//		              //          testCaseScenario1.log(Status.FAIL,"---------------------------------");
//		                    
//		                      
//		                        Scevt_RC_CreateAndWriteExcel.scevtWriteExcelData(
//		                        		pageno, name, dob, gender, chart);
			                  
		                        

		                  
		               
		                
		              
		              
		                
		            }
		 
		            document.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
   	
   	
