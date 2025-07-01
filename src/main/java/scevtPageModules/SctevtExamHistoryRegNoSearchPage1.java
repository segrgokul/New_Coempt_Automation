package scevtPageModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import browsers.BrowserManager;
import pageObjMod.SctevtPom;

public class SctevtExamHistoryRegNoSearchPage1 extends BasicFunctions {
	 public void resultPageNavigation(Object regno, Object semester,ExtentTest testCaseName) throws IOException {
		 
		 
			try {


				ExtentTest testCaseScenario = testCaseName
						.createNode(" Result page navigation for "+ regno +"  Test case is sucessful");

	
	if(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn.isDisplayed()) {
	explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn,10);
   	
	
	 
	 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn);
	 
	 scrollTillWebElement(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn);

	explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn,10);
	
	
	 
	 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn);
	 
	testCaseScenario.log(Status.PASS, "Exam History Register Number page navigation for "+ regno +" is entered sucessfully");
	}
	
	
}
catch(Exception e) {

ExtentTest testCaseScenario = testCaseName
		.createNode(" Result page navigation for "+ regno +"  Test case is sucessful");


testCaseScenario.log(Status.FAIL,e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
}

}
public void regnoEnter(Object regno, Object semester ,ExtentTest testCaseName) throws IOException {


	try {


		ExtentTest testCaseScenario = testCaseName
				.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");

	
	if(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().rollNoTextBox.isDisplayed()) {
	explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().rollNoTextBox,10);
   	

 
 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().rollNoTextBox);
 




sendKeys(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().rollNoTextBox,regno);


explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().btnViewStudentResultsList,10);
	
	
	 
	 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().btnViewStudentResultsList);
	 
	 try {
	

	 
	 if(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().loadingStudentHistoryAlert.isDisplayed()) {
	   
		 explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().studentHistory,80);
	 	 	 
		 System.out.println("yes");
	  	testCaseScenario.log(Status.PASS, "Following regisiter number "+ regno +" is displayed");
	 }
	 else {
		testCaseScenario.log(Status.FAIL, "Please check the following regisiter number "+ regno ,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
	 }
	 }
	 catch(Exception e){
		testCaseScenario.log(Status.FAIL, "Please check the following regisiter number "+ regno +e.getMessage() ,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		 
	 }


	}
	
	
}
catch(Exception e) {
	ExtentTest testCaseScenario = testCaseName
			.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");


testCaseScenario.log(Status.FAIL,e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
}


}
public void regnoValidation(Object regNo, Object semester, Map<String, String> subjectsAndMarks, ExtentTest testCaseName) throws IOException {
    try {
        ExtentTest testCaseScenario = testCaseName
                .createNode("Result page navigation for the " + semester + " of the following register number " + regNo + " — Test case started");

        WebElement sem = driver.findElement(By.xpath("//h5[@class='nk-block-title' and text()='SEMESTER - " + semester + "']"));
        scrollTillWebElement(sem);

        // Step 1: Get current exam data (headings and marks)
        List<WebElement> currentHeadingNames = driver.findElements(By.xpath(
                "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
                        "/following-sibling::div//table[@class='table table-orders'])[last()]//th"));

        List<WebElement> currentExamMarks = driver.findElements(By.xpath(
                "(//h5[normalize-space(text())='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
                        "/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td"));

        Map<String, String> currentexamData = new LinkedHashMap<>();
        int minSize = Math.min(currentHeadingNames.size(), currentExamMarks.size());
        boolean hasBacklog = false;
	        StringBuilder backlogSubjects = new StringBuilder();
	        String formattedScriptBacklog = ""; // Initialize at the beginning
	        int absentCount = 0;
	        boolean comparisonDone = false; // Ensure comparison is done only once     
	        
        for (int i = 0; i < minSize; i++) {
            currentexamData.put(currentHeadingNames.get(i).getText().trim(), currentExamMarks.get(i).getText().trim());
        }
        String currentTotalMark = currentexamData.get("Total");
        String currentSessionalMark = currentexamData.get("Sessional");
        String currentResultValue = currentexamData.get("Result");

        testCaseScenario.log(Status.INFO, "Current Result: " + currentResultValue);
        testCaseScenario.log(Status.INFO, "Current Sessional Mark: " + currentSessionalMark);
        testCaseScenario.log(Status.INFO, "Current Total Mark: " + currentTotalMark);
        // Log full current exam data

        for (Map.Entry<String, String> entry : currentexamData.entrySet()) {
    	    System.out.println(entry.getKey() + " => " + entry.getValue());
    	//    testCaseScenario.log(Status.PASS, entry.getKey() + " => " + entry.getValue());
    	    String key = entry.getKey();
    	    String valueStr = entry.getValue();

    	    // Logging
    	 //   System.out.println(key + " => " + valueStr);
    	 //   testCaseScenario.log(Status.PASS, key + " => " + valueStr);

    	    // Skip IA, Sessional, Total, Result, and ExamName
    	    if (key.contains("IA")||key.contains("PR") || key.equalsIgnoreCase("Sessional") ||
    	        key.equalsIgnoreCase("Total") || key.equalsIgnoreCase("Result") ||
    	        key.equalsIgnoreCase("ExamName")) {
    	        continue;
    	    }

    	    // Convert value to number and check <= 28
    	    double value =0.0;
    	    try {
    	    	if(!valueStr.equals("A")) {
        	        value = Double.parseDouble(valueStr);
        	        if (value < 28) {
        	            hasBacklog = true;
  	                    backlogSubjects.append(key).append(",");
  	    
        	        	
        	            System.out.println("The following register number" +regNo+" for the Subject " + key + " has failed marks: " + value);
        	            testCaseScenario.log(Status.PASS, "The following register number" +regNo+" for the Subject " + key + " has failed marks: " + value);
        	        }else if (value >= 28) {
        	            System.out.println("The following register number" +regNo+" for the Subject " + key + " has passed marks: " + value);
        	            testCaseScenario.log(Status.PASS, "The following register number" +regNo+" for the Subject " + key + " has passed marks: " + value);
        	   	
        	        }
        	        
        	        else {
        	            System.out.println("✅ Subject " + key + " passed with marks: " + value);
        	            testCaseScenario.log(Status.PASS, "✅ Subject " + key + " passed with marks: " + value);
                	   	
        	        }
    	    	}
    	    	else if(valueStr.equals("A")) {
    	    		
    	    			hasBacklog = true;
	      	            absentCount++;
	      	            backlogSubjects.append(key).append(",");

    	            System.out.println("The following register number" +regNo+" for the Subject " + key + " has Absent " + valueStr);
    	            testCaseScenario.log(Status.PASS, "The following register number" +regNo+" for the Subject " + key + " has Absent " + valueStr);
            	   	
    	    	}
    	    		
    	    	
    	      
    	    } catch (NumberFormatException e) {
    	        System.out.println("⚠️ Could not parse value for: " + key);
    	        testCaseScenario.log(Status.FAIL," Could not parse value for: " + key);
          	  
    	    }
        }
              
            
      
      	       if (currentResultValue.equals("Pass(G)")) {
    	        	
      	         // Prepare grace mark if Pass(G)
      	          double remainingGraceMark = 0.0;
      	          if ("Pass(G)".equals(currentResultValue)) {
      	              List<WebElement> previousHeadings = driver.findElements(By.xpath(
      	                  "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
      	                  "/following-sibling::div//table[@class='table table-orders'])[last()]//th"));

      	              List<WebElement> previousMarks = driver.findElements(By.xpath(
      	                  "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
      	                  "/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[1]/td"));

      	              boolean hasPreviousSessional = previousHeadings.stream()
      	                  .anyMatch(heading -> heading.getText().trim().equalsIgnoreCase("Sessional"));

      	              if (hasPreviousSessional) {
      	                  Map<String, String> previousExamData = new LinkedHashMap<>();
      	                  for (int i = 0; i < Math.min(previousHeadings.size(), previousMarks.size()); i++) {
      	                      previousExamData.put(previousHeadings.get(i).getText().trim(), previousMarks.get(i).getText().trim());
      	                  }

      	                  String previousSessionalMark = previousExamData.get("Sessional");

      	                  if (previousSessionalMark != null && currentSessionalMark != null) {
      	                      remainingGraceMark = Double.parseDouble(previousSessionalMark) - Double.parseDouble(currentSessionalMark);
      	                      testCaseScenario.log(Status.INFO, "Initial grace marks available: " + remainingGraceMark);

      	                      if (remainingGraceMark > 15) {
      	                          testCaseScenario.log(Status.FAIL, "❌ Sessional mark difference exceeds 15 for RegNo: " + regNo);
      	                          return;
      	                      }
      	                  }
      	              } else {
      	                  testCaseScenario.log(Status.INFO, "No previous Sessional heading found. Skipping grace logic.");
      	                  remainingGraceMark = 0.0;
      	              }
      	          }

      	          // Step 2: Match each expected subject
      	          for (Map.Entry<String, String> expectedEntry : subjectsAndMarks.entrySet()) {
      	              String subjectName = expectedEntry.getKey();
      	              String expectedMark = expectedEntry.getValue();
      	              String expectedHeading = objectToDataType(subjectName);

      	              System.out.println(subjectName);
      	              System.out.println(expectedMark);
      	              System.out.println(expectedHeading);
      	              
      	              if (currentexamData.containsKey(expectedHeading)) {
      	                  String actualMark = currentexamData.get(expectedHeading).trim();

      	                  String newMark;
      	        	        System.out.println("========================");		      	    
      	  	      	    
      	        	   
      	        	    
      	        	        if(expectedMark.contains("AB")) {
      	        	        	
      	        	        	 newMark = expectedMark.replace(expectedMark, "A");
      	        	        	
      	        	        }
      	        	        else {
      	        	        	newMark=expectedMark.replaceAll("\\.0+$", "");
      	        	        }
//      	            	        
//      	            	        System.out.println(marksTHText);
//      	            	        System.out.println(newMark);
//      	            	        System.out.println(mark);
//      	            	        
      	                  
      	                  
      	                  System.out.println(newMark);
      	                  System.out.println(actualMark);
      	                  
      	                  if (actualMark.equals(newMark)) {
      	                  	
      	             	    System.out.println(expectedHeading + " subject Th Mark " +actualMark +" from UI is MATCH: with Excel Mark " + newMark);
      	                    testCaseScenario.log(Status.PASS, expectedHeading + " subject Th Mark " +actualMark +" from UI is MATCH: with Excel Mark " + newMark);

      	                  } else {
      	                	  
      	                	   System.out.println(expectedHeading + " subject Th Mark " +actualMark +" from UI is MISMATCH: with Excel Mark " + newMark+" => Expected: " + newMark + ", Found: " + actualMark );
         	                    testCaseScenario.log(Status.PASS, expectedHeading + " subject Th Mark " +actualMark +" from UI is MISMATCH: with Excel Mark " + newMark+" =>  Expected: " + newMark + ", Found: " + actualMark );

      	                      // Try to apply grace if Pass(G)
      	                      if ("Pass(G)".equals(currentResultValue)) {
      	                          double uiMark = Double.parseDouble(expectedMark);
      	                          double needed = 28.0 - uiMark;

      	                          if (needed <= 0) {
      	                              testCaseScenario.log(Status.PASS, subjectName + " already >= 28" +newMark );
      	                              continue;
      	                          }

      	                          testCaseScenario.log(Status.INFO, "Checking grace for " + subjectName + ": needed=" + needed + ", available=" + remainingGraceMark);

      	                          double adjusted = uiMark;
      	                          if (remainingGraceMark >= needed) {
      	                              adjusted = uiMark + needed;
      	                              remainingGraceMark -= needed;
      	                          } else {
      	                              adjusted = uiMark + remainingGraceMark;
      	                              remainingGraceMark = 0;
      	                          }

      	                          if (Double.parseDouble(actualMark) == adjusted) {
      	                          
      	                            System.out.println(subjectName + " subject Th Mark " +adjusted +" from UI is MATCH after giving with grace: => " + adjusted);
      	      	                    testCaseScenario.log(Status.PASS, subjectName + " subject Th Mark " +adjusted +" from UI is MATCH after giving with grace: => " + adjusted);
      	      	                  
      	      	                    formattedScriptBacklog = "Pass(G)";

      	                              
      	                              
      	                          } else {
      	                     //         testCaseScenario.log(Status.FAIL, " MISMATCH even after grace: " + subjectName + " => Expected: " + adjusted + ", Found: " + actualMark);
      	                            System.out.println(subjectName + " subject Th Mark " +adjusted +" from UI is MISMATCH even after giving with grace: =>  Expected: " + adjusted + ", Found: " + actualMark);
      	      	                    testCaseScenario.log(Status.FAIL, subjectName + " subject Th Mark " +adjusted +" from UI is MISMATCH even after giving with grace: =>  Expected: " + adjusted + ", Found: " + actualMark);

      	                          }

      	                          testCaseScenario.log(Status.INFO, "Remaining grace: " + remainingGraceMark);
      	                      }
      	                  }
      	              } else {
      	                  testCaseScenario.log(Status.FAIL, "❌ Heading not found in UI: " + expectedHeading);
      	              }
      	          }

      	        }
      	        
      	     if (formattedScriptBacklog.isEmpty()) {
       	        formattedScriptBacklog = backlogSubjects.toString().trim().replaceAll(", $", "");
       	    }

       	    if (!formattedScriptBacklog.isEmpty() && !formattedScriptBacklog.startsWith("DI")) {
       	    	System.out.println("formattedScriptBacklog"+ formattedScriptBacklog);
       	    	
       	        String[] subjects = formattedScriptBacklog.split(",");
       	        
       	        if ((subjects[0].equals("Pass(G)"))) {
       	          
       	        }
       	        else if (!subjects[0].startsWith("Back-")) {
       	            subjects[0] = "Back-" + subjects[0].trim();
       	            System.out.println(" subjects[0"+ subjects[0]);
       	        }
       	        
       	        for (int i = 1; i < subjects.length; i++) {
       	            subjects[i] = subjects[i].replace("Back-", "").trim();
       	        }
       	        
       	        formattedScriptBacklog = String.join(",", subjects);
       	    }

       	    if (formattedScriptBacklog.isEmpty()) {
       	        formattedScriptBacklog = "Pass";
       	    }
   	        int totalMark = Integer.parseInt(currentTotalMark);
     	    if (totalMark < 300) {
     	        formattedScriptBacklog = "Fail";
     	    }

       	    // Compare with UI only once
       	    if (!comparisonDone) {
       	        if (formattedScriptBacklog.equalsIgnoreCase(currentResultValue)) {
       	            System.out.println("Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
       	            testCaseScenario.log(Status.PASS, "Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
       	        }
       	        
      	        else {
      	            System.out.println("Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
      	            testCaseScenario.log(Status.FAIL, "Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
      	        }
      	        comparisonDone = true;
      	    }
      
        

    } catch (Exception e) {
        System.out.println("❌ Exception in regnoEnter1: " + e.getMessage());
        e.printStackTrace();
        testCaseName.log(Status.FAIL, "Exception: " + e.getMessage());
    }
}




public String objectToDataType(Object obj) {
    if (obj == null) {
        throw new IllegalArgumentException("Object cannot be null");
    }

    if (obj instanceof String) {
        return (String) obj;
    } else if (obj instanceof Number) {
        return obj.toString(); // Handles Integer, Double, Float, Long, etc.
    } else {
        throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getSimpleName());
    }

//-- for heading	//	(//h5[text()='SEMESTER - 1']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th
	
// -- for last row		//(//h5[text()='SEMESTER - 1']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td
}












}