package scevtPageModules;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; // Set a higher limit

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import pageObjMod.SctevtPom;

public class Scte_VtResultPage  extends BasicFunctions {

    String status;
	ArrayList<String> windowHandles =null;
	  public void ScTEVT_ResultProcess(String regno, String sycode,String subject,String mark,ExtentTest testCaseName) throws InterruptedException, IOException, AWTException {
	    	
//			ExtentReports report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\report.html",true);

		
			 	 implicitWait(30);

			 	 
					WebElement btnSemResult ;
				String semester = sycode.toString();
				switch (semester) {
				    case "1":
				        btnSemResult =SctevtPom.getInstanceSctevtResultXpaths().btn1stSemResult;
				        break;
				    case "2":
				        btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn2ndSemResult;
				        break;
				        
				    case "3":
				        btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn3rdSemResult;
				        break;
				    case "4":
				        btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn4thSemResult;
				        break;
				    case "5":
				        btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn5thSemResult;
				        
				        break;
				    case "6":
				        btnSemResult = SctevtPom.getInstanceSctevtResultXpaths().btn6thSemResult;
				 
				        break;
				    default:
				        throw new IllegalArgumentException("Invalid Semester: " + semester);
				}


				try {
					ExtentTest testCaseScenario = testCaseName
							.createNode( regno + "Result Page action Test case for "+ sycode +" semester has started running");
	
					if(btnSemResult.isDisplayed()) {
				
				
				explicitWait(btnSemResult,30);
				click(btnSemResult);
			
	
			   	 implicitWait(30);
					
	
					
				Thread.sleep(4000);
				// Switch to the second window (new tab)
			
				
				windowHandles = new ArrayList<>(driver.getWindowHandles());	
				
				
				driver.switchTo().window(windowHandles.get(1));

					
//				if(!SctevtPom.getInstanceSctevtResultXpaths().rollNoTB.isDisplayed()) {
//					
//				
//					
//					   System.out.println("An error occurred for the following regno: " +regno);
//		      	        testCaseScenario.log(Status.WARNING, "Error occurred for the following regno: " +regno,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
//		      	  
//		      	        driver.navigate().refresh();
//					
//					driver.close();
//
//					// Switch back to the parent window (index 0 in the list)
//					driver.switchTo().window(windowHandles.get(0));
//				}

				 implicitWait(50);	
					explicitWait(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB,30);	
	
					
			//		click(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB);

				  	 sendKeys(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB,regno);
			
				  	 testCaseScenario.log(Status.INFO, "Regno: " +regno + " and Sem: "+ sycode + " has entered sucessfully");
				      	  		
	//			 click(SctevtPom.getInstanceSctevtResultXpaths().rollNoTB);

		 
		 implicitWait(50);	
			explicitWait(SctevtPom.getInstanceSctevtResultXpaths().dobTB,30);		
				 SctevtPom.getInstanceSctevtResultXpaths().dobTB.click();
				 
			
				if (SctevtPom.getInstanceSctevtResultXpaths().dobTB.isDisplayed()) {
			
				   	 implicitWait(3);
		     	 click(SctevtPom.getInstanceSctevtResultXpaths().dobTB);
		      	
		      	 implicitWait(3);
		      	 
		     	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);
		      	 
		      	
		     	 implicitWait(3);
		      	 
		     	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);
		     	 implicitWait(3);
		      	 
		     	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrev);
		  	 
		     	 	implicitWait(3);
		      	 
		     	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevYear);
		      	implicitWait(3);
		      	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevMonth);
		      	 
		      	implicitWait(3);
		      	 click(SctevtPom.getInstanceSctevtResultXpaths().dobPrevDate);
		      	 
		     	implicitWait(3);
		      	 click(SctevtPom.getInstanceSctevtResultXpaths().btnViewStudentResults);
				}//if
					
					}//if
					
				}//try
					catch(Exception e) {
						
						ExtentTest testCaseScenario = testCaseName
								.createNode( regno + "Result Page action Test case for "+ sycode +" semester has started running");

			  	        testCaseScenario.log(Status.FAIL, "Error occurred for the following regno: " +regno +" and semester "+sycode,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
			 		   	
					}
		
	  }
	  
	  public void ScTEVT_ResultProcess1(String regNo, String examSemester,String subjectName,String mark,ExtentTest testCaseScenario) throws InterruptedException, IOException, AWTException {
			

//			ExtentTest testCaseScenario1 = testCaseScenario
//					.createNode( regno + " Validation Test case for "+ sycode +" semester has started running");
//			
     //    testCaseScenario.createNode("SCET&VT Result process Action for the semester " + examSemester +" and the reg: " + regNo +" and subject " + subjectName);
		
	     	 implicitWait(1000);
				
	      	 
	      	 try { 	
	      	 
	      	 

	      	
	      	 WebElement uiElement = driver.findElement(By.xpath("//h6[@id='result-st']"));
	         // Fetch UI backlog result
      	    

	     	explicitWait(uiElement,10);
	      	 
	      	 String resultText = uiElement.getText();

	      	String[] parts = resultText.split("\\s*:\\s*");
	      	
	      	
	      	
	      	if (parts.length > 1) {
	      	    System.out.println("UI result:"+parts[1].trim()); 
	      	    
	      	    String uiBacklog =parts[1];
	      	    
	      	    // Print "Pass" after trimming spaces
	      	
	                WebElement sem=	driver.findElement(By.xpath("//span[@id='lblBranch']"));
	  		   
	               String semesters = sem.getText();
	               
	               System.out.println("semesters"+semesters);
	                
	   	 Thread.sleep(5000);
      	 
	    	scrollTillEnd(driver);
	      	 
	      	 
	
//	      	List<WebElement> cells = driver.findElements(By.xpath("//div[@class='card card-preview']//tbody/tr"));

	        // Find all rows in the table
	        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr	"));

	        StringBuilder result = new StringBuilder("Result: ");
	        boolean hasBacklog1 = false;
	      	List<WebElement> thCells = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[7]"));
	      	List<WebElement> subjectCells = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr/td[2]"));
	      	List <WebElement> subjectCodeAndThCells = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[td[1] and td[7]]"));
	      	
	          // Find all rows in the table

	      	        int count = 0; // Counter to track rows processed
						
	      	      try {
	      	        // Locate total marks element
	      	        WebElement totalMarks = driver.findElement(By.xpath("//table[@id='tbl-results-marks']//tbody/tr[last()]/td[last()]"));

	      	        // Wait for results
	      	        explicitWait(uiElement, 50);

	      	        // Extract and parse total marks
	      	        String totalText = totalMarks.getText().trim();
	      	        int totalMark = Integer.parseInt(totalText);

	      	        System.out.println("Total Marks: " + totalMark);

	      	        // Find subject rows
	      	        List<WebElement> subjectRows = driver.findElements(By.xpath("//table[@id='tbl-results-marks']//tbody/tr"));
	      	        boolean hasBacklog = false;
	      	        StringBuilder backlogSubjects = new StringBuilder();
	      	        int totalSubjects = subjectRows.size();
	      	        int absentCount = 0;
	      	        String formattedScriptBacklog = ""; // Initialize at the beginning
		      	    StringBuilder mpSubjects = new StringBuilder(); // To store subjects with "-"
		      	    int totalMarksSum = 0; // Track total marks
		      	    boolean hasMP = false; // Track if MP condition is met
		      	    boolean comparisonDone = false; // Ensure comparison is done only once

	      	      for (WebElement row : subjectRows) {
	      	        String subjectCode = row.findElement(By.xpath("./td[1]")).getText().trim(); // e.g., TH1 or PR1
	      	        String subjectNames = row.findElement(By.xpath("./td[2]")).getText().trim(); // e.g., Mathematics
	      	        String maxMarksTHText=row.findElement(By.xpath("./td[3]")).getText().trim();
	      	        String passMarksText = row.findElement(By.xpath("./td[6]")).getText().trim(); // Passing marks
	      	        String marksTHText = row.findElement(By.xpath("./td[7]")).getText().trim(); // Theory marks
	      	        String marksIAText = row.findElement(By.xpath("./td[8]")).getText().trim(); // Internal Assessment marks
	      	        String marksTotalText = row.findElement(By.xpath("./td[9]")).getText().trim(); // Practical marks

	      	        String subjectDetails = subjectCode + " - " + subjectNames;
	    
	      	        System.out.println("========================");
	      	        System.out.println("Subject Code: " + subjectCode);
	      	    System.out.println("Subject Name: " + subjectNames);
	      	    System.out.println("Pass Marks: " + passMarksText);
	      	    System.out.println("Theory Marks: " + marksTHText);
	      	    System.out.println("IA Marks: " + marksIAText);
	      	    System.out.println("Total Marks: " + marksTotalText);
	      	  String newMark;
      	        System.out.println("========================");		      	    
	      	    
      	        if(subjectNames.equals(subjectName)) {
      	      	System.out.println("yes");
      	    
      	        if(mark.contains("AB")) {
      	        	
      	        	 newMark = mark.replace(mark, "A");
      	        	
      	        }
      	        else {
      	        	newMark=mark.replaceAll("\\.0+$", "");
      	        }
      	        
      	        System.out.println(marksTHText);
      	        System.out.println(newMark);
      	        System.out.println(mark);
      	        
      	        System.out.println(marksTHText.equals(newMark));
      	        
      	        	if(marksTHText.equals(newMark)) {
      	        	    System.out.println(subjectDetails + " subject Th Mark " +marksTHText +" from UI is Equals with Excel Mark " + newMark);
  	                    testCaseScenario.log(Status.PASS, subjectDetails + " subject Th Mark " +marksTHText +" from UI is Equals with Excel Mark " + newMark);
      	        		
      	        		
      	        	}
      	        	
      	       
      	        	
      	        	else if(!marksTHText.equals(newMark)&& (uiBacklog.equals("Pass(G)"))) {
      	        		
      	        	    testCaseScenario.log(Status.SKIP, subjectDetails + " subject Th Mark " +marksTHText +" from UI is Not Equals with Excel Mark " + newMark+
      	        	    		"The following students has ",
  	                    		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
  			      	  	
      	        	}
      	        	else {
      	        	  System.out.println(subjectDetails + " subject Th Mark " +marksTHText +" from UI is Not Equals with Excel Mark " + newMark);
  	                    testCaseScenario.log(Status.FAIL, subjectDetails + " subject Th Mark " +marksTHText +" from UI is Not Equals with Excel Mark " + newMark,
  	                    		MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
  			      	  
      	        		
      	        	}
      	        	
      	        }
	      	        

	      	    if (!marksTotalText.isEmpty() && !passMarksText.isEmpty()) {
	      	        if (marksTHText.equalsIgnoreCase("A") || marksIAText.equalsIgnoreCase("A") || marksTotalText.equalsIgnoreCase("A")) {  
	      	            hasBacklog = true;
	      	            absentCount++;
	      	            backlogSubjects.append(subjectCode).append(",");
	      	            System.out.println(subjectDetails + " : Absent with Total Mark " + marksTotalText);
	      	            testCaseScenario.log(Status.PASS, subjectDetails + " : Absent with Total Mark " + marksTotalText);
	      	        } else if (marksTHText.equals("-") || marksIAText.equals("-") || marksTotalText.equals("-")) {
	      	            hasMP = true;
	      	            mpSubjects.append(subjectCode).append(" - ").append(subjectDetails).append(" : -\n");
	      	          System.out.println(subjectDetails + " : Failed with Th mark " + marksTHText + " and Total Marks " + marksTotalText);
	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + marksTHText + " and Total Marks " + marksTotalText);
	      	        } else {
	      	            int passMarks = Integer.parseInt(passMarksText);
	      	            int totalSubjectMarks;
	      	            
	      	            System.out.println(marksIAText.isEmpty());
	      	            System.out.println(marksTHText.isEmpty());
	      	            
	      	            if ((marksIAText.isEmpty() && marksTHText.isEmpty())
	      	        
	      	            		){
	      	            	
	      	               totalSubjectMarks = Integer.parseInt(marksTotalText);
	      	               
	      	                totalMarksSum += totalSubjectMarks;
	      	                
	      	               
	      	                
	      	                if (totalSubjectMarks < passMarks) {
	      	                    hasBacklog = true;
	      	                    backlogSubjects.append(subjectCode).append(",");
	      	                    System.out.println(subjectDetails + " : Failed with Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Total Marks " + totalSubjectMarks);
	      	                } else {
	      	                    System.out.println(subjectDetails + " : Passed with Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Passed with Total Marks " + totalSubjectMarks);
	      	                }
	      	            } else {                    
	      	                int subjectMarksTH = Integer.parseInt(marksTHText);
	      	                int subjectMarksIA = Integer.parseInt(marksIAText);
	      	                int subjectMaxMarksTH = Integer.parseInt(maxMarksTHText);
	      	                
	      	                
	      	                totalSubjectMarks = subjectMarksTH + subjectMarksIA;
	      	              
	      	                
	      	                if (subjectMarksTH == 0) {
	      	                    if (totalMarksSum > 300 && Integer.parseInt(subjectCode.replaceAll("\\D", "")) > 300) {
	      	                        marksTHText = "DI-" + subjectCode;
	      	                    } else {
	      	                        marksTHText = "Fail";
	      	                        hasBacklog = true;
	      	                        backlogSubjects.append(subjectCode).append(",");
	      	                    }
	      	                    System.out.println(subjectDetails + " : TH mark is 0, updated to " + marksTHText);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : TH mark is 0, updated to " + marksTHText);
	      	                } 
	      	                
	      	              else if (semesters.contains("Semester - Ex-Regular Exam - Mercy Exam") ) {
	      	            	  
	      	            	 
	      	            	  
	      	            	 if(subjectMaxMarksTH ==80) { 
	      	                	if(subjectMarksTH < 28|| totalSubjectMarks < passMarks) {
	      	                  
	      	                		
	      	                		
	      	                    hasBacklog = true;
	      	                    backlogSubjects.append(subjectCode).append(",");
	      	                    System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);

	      	                	}
	      	                	else {
		      	                    System.out.println(subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                }
	      	                }
	      	            	 else if(subjectMaxMarksTH ==70) {
	      	            		if(subjectMarksTH < 25|| totalSubjectMarks < passMarks) {
			      	                  
	      	                		
	      	                		
		      	                    hasBacklog = true;
		      	                    backlogSubjects.append(subjectCode).append(",");
		      	                    System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	
		      	                	}
		      	                	else {
			      	                    System.out.println(subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
			      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
			      	                }
	      	              
	      	              }
	      	            	 else {
	      	            		  System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                    testCaseScenario.log(Status.FAIL, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	
	      	            	 }
	      	            	 
	      	              
	      	              }//else if
	      	                
	      	                
	      	                else if (semesters.contains("1st Semester - Regular Exam ") ) {
	      	                	if(subjectMarksTH < 25|| totalSubjectMarks < passMarks) {
	      	                  
	      	                    hasBacklog = true;
	      	                    backlogSubjects.append(subjectCode).append(",");
	      	                    System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);

	      	                	}
	      	                	else {
		      	                    System.out.println(subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
		      	                }
	      	                }
	      	                else if (subjectMarksTH < 28 || totalSubjectMarks < passMarks) {
	      	                	
	      	                	
	      	                
	      	                
	      	                
	      	                    hasBacklog = true;
	      	                    backlogSubjects.append(subjectCode).append(",");
	      	                    System.out.println(subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Failed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);

	      	                	
	      	                	
	      	                } else {
	      	                    System.out.println(subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	      	                    testCaseScenario.log(Status.PASS, subjectDetails + " : Passed with Th mark " + subjectMarksTH + " and Total Marks " + totalSubjectMarks);
	      	                }
	      	            }
	      	        }
	      	    }
	      	      }
	      	    // Process formattedScriptBacklog based on backlogSubjects
	      	    if (formattedScriptBacklog.isEmpty()) {
	      	        formattedScriptBacklog = backlogSubjects.toString().trim().replaceAll(", $", "");
	      	    }

	      	    if (!formattedScriptBacklog.isEmpty() && !formattedScriptBacklog.startsWith("DI")) {
	      	        String[] subjects = formattedScriptBacklog.split(",");
	      	        
	      	        if (!subjects[0].startsWith("Back-")) {
	      	            subjects[0] = "Back-" + subjects[0].trim();
	      	        }
	      	        
	      	        for (int i = 1; i < subjects.length; i++) {
	      	            subjects[i] = subjects[i].replace("Back-", "").trim();
	      	        }
	      	        
	      	        formattedScriptBacklog = String.join(",", subjects);
	      	    }

	      	    if (formattedScriptBacklog.isEmpty()) {
	      	        formattedScriptBacklog = "Pass";
	      	    }

	      	    // Check total marks condition only after processing all subjects
	      	    if (hasMP) {
	      	        formattedScriptBacklog = "MP";
	      	    } else if (totalMark < 300) {
	      	        formattedScriptBacklog = "Fail";
	      	    } else if (formattedScriptBacklog.startsWith("Back-") && totalMarksSum >= 300) {
	      	        formattedScriptBacklog = formattedScriptBacklog.replace("Back-", "");
	      	    }

	      	    // Print subjects with "-" values only once
	      	    if (mpSubjects.length() > 0) {
	      	        System.out.println("Subjects with '-':\n" + mpSubjects.toString().trim());
	      	    }

	      	    // Compare with UI only once
	      	    if (!comparisonDone) {
	      	        if (formattedScriptBacklog.equalsIgnoreCase(uiBacklog)) {
	      	            System.out.println("Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);
	      	            testCaseScenario.log(Status.PASS, "Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);
	      	        }
	      	        
	      	        else if ((formattedScriptBacklog.equals("Pass")) && (uiBacklog.equals("Pass(G)"))) {
	      	        	
	      	          System.out.println("Backlog comparison INFO: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);
	      	            testCaseScenario.log(Status.SKIP, "Backlog comparison INFO: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);

	      	        }
	      	        
	      	        
	      	        
	      	        else {
	      	            System.out.println("Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);
	      	            testCaseScenario.log(Status.FAIL, "Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + uiBacklog);
	      	        }
	      	        comparisonDone = true;
	      	    }
	      
	      	        
	      	    } catch (Exception e1) {
	      	        System.out.println("An error occurred: " + e1.getMessage());
	      	        testCaseScenario.log(Status.WARNING, "Error occurred while fetching total marks: " + e1.getMessage(),	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	      	  
	      	        e1.printStackTrace();
	      	    }

	
	 
	      	
	      			
	      	
	      	}    	
//		    	driver.close();
//
//				// Switch back to the parent window (index 0 in the list)
//				driver.switchTo().window(windowHandles.get(0));
//				
			
			    
	    		 
	    		 }				
				catch(Exception e){
					
					if(SctevtPom.getInstanceSctevtResultXpaths().btnAlertOk.isDisplayed()) {

				    	implicitWait(30);
				    	testCaseScenario.log(Status.FAIL,"Please check the following regno " + regNo,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
							
						click(SctevtPom.getInstanceSctevtResultXpaths().btnAlertOk);
						
						System.out.println("Please check the following regno " + regNo);
				

						// Switch back to the parent window (index 0 in the list)
				//		driver.switchTo().window(windowHandles.get(0));
						
						driver.close();

						// Switch back to the parent window (index 0 in the list)
						driver.switchTo().window(windowHandles.get(0));
						
			
						  if(!SctevtPom.getInstanceSctevtResultXpaths().rollNoTB.isDisplayed()) {
								
								
								
							   System.out.println("An error occurred for the following regno: " +regNo);
				     	        testCaseScenario.log(Status.FAIL, "Error occurred for the following regno: " +regNo +" and semester "+ subjectName,	MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
				     	  
				     	        driver.navigate().refresh();
							
							driver.close();

							// Switch back to the parent window (index 0 in the list)
							driver.switchTo().window(windowHandles.get(0));
						}
				
				}
					
					

			e.printStackTrace();
			driver.navigate().refresh();
			
		
			testCaseScenario.log(Status.FAIL,"Please check the following regno " + regNo+ e.getMessage());
//			driver.close();
//			// Switch back to the parent window (index 0 in the list)
//			driver.switchTo().window(windowHandles.get(0));
			}
	  }
		
			
						//catch
	  
	  public String processText(String thText) {
		    // Trim the text to remove extra spaces
		    String trimmedText = thText.trim();
		    
		    // Check for exact match for "A" (Absent)
		    if (trimmedText.equalsIgnoreCase("A")) {
		        return "A"; // Mark as absent
		    }

		    // Extract digits (marks) from the text
		    String number = trimmedText.replaceAll("\\D", "");
		    
		    // Extract subject code (assuming it's the first non-numeric word)
		    String subjectCode = trimmedText.replaceAll("\\d", "").trim();

		    if (!number.isEmpty()) {
		        int num = Integer.parseInt(number);
		        // If number is greater than or equal to 28, it's a Pass, otherwise "Back-" + subjectCode
		        return (num >= 28) ? "Pass" : "Back-" + subjectCode;
		    }

		    return "Unknown"; // Default case if no valid number is found
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
	    	
	    	
	    	
}
