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
	private static boolean isTestCaseCourseSet = false;
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
	   
		 explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().studentHistory,120);
	 	 	 
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
        explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().studentHistory,40);
	 	
        WebElement sem = driver.findElement(By.xpath("//h5[@class='nk-block-title' and text()='SEMESTER - " + semester + "']"));
        scrollTillWebElement(sem);

        // Step 1: Get current exam data (headings and marks)
        List<WebElement> currentHeadingNames = driver.findElements(By.xpath(
                "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
                        "/following-sibling::div//table[@class='table table-orders'])[last()]//th"));

        List<WebElement> currentExamMarks = driver.findElements(By.xpath(
                "(//h5[normalize-space(text())='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]" +
                        "/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td"));
        Map<String, Double> iaMarkMap = new HashMap<>();
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

        Map<String, Double> theoryMarksMap = new HashMap<>();
        double totalMarkWithIA=0.0;
        double iaMark=0.0;
        
        for (Map.Entry<String, String> entry : currentexamData.entrySet()) {
            String key = entry.getKey();
            String valueStr = entry.getValue();
            System.out.println(key);
            System.out.println(valueStr);

            
            
            // Skip non-subject keys
            if (key.contains("IA")||key.equalsIgnoreCase("PR") || key.equalsIgnoreCase("Sessional") ||
                key.equalsIgnoreCase("Total") || key.equalsIgnoreCase("Result") ||
                key.equalsIgnoreCase("ExamName")) {
                continue;
            }

            // Process both TH and IA together
            if (key.contains("TH")) {
           
                String thStr = currentexamData.get(key);        // TH1
                String iaStr = currentexamData.get(key + "IA"); // TH1 IA
                String baseSubject = key.replace("IA", "").trim();  // TH1 or TH1 IA → TH1
                
                System.out.println(thStr);
                System.out.println(iaStr);
                System.out.println(baseSubject);
                
                if (!thStr.equals("A") && !iaStr.equals("A")) {
                    try {
                        double totalThMark = Double.parseDouble(thStr);
                        iaMark = Double.parseDouble(iaStr);
                        totalMarkWithIA = totalThMark + iaMark;
                        System.out.println(totalThMark);
                        System.out.println(iaMark);
                        iaMarkMap.put(baseSubject, iaMark);  // store each IA mark
                        System.out.println(totalMarkWithIA);
                        
                        // Optional: check pass/fail with totalWithIA
                        if (totalThMark < 28 || totalMarkWithIA <35 ) {
                            hasBacklog = true;
                            backlogSubjects.append(baseSubject).append(",");
                            System.out.println("The following register number " + regNo + " failed in " + baseSubject +" with Th mark " + totalThMark + " and Total Marks (with IA): " + totalMarkWithIA );
                            testCaseScenario.log(Status.PASS, "The following register number " + regNo + " failed in " + baseSubject +" with Th mark " + totalThMark + " and Total Marks (with IA): " + totalMarkWithIA );
                        } else if(totalThMark >= 28 || totalMarkWithIA >= 35 ) {
                            System.out.println("The following register number " + regNo + " Passed in " + baseSubject +" with Th mark " + totalThMark + " and Total Marks (with IA): " + totalMarkWithIA );
                            testCaseScenario.log(Status.PASS,"The following register number " + regNo + " Passed in " + baseSubject +" with Th mark " + totalThMark + " and Total Marks (with IA): " + totalMarkWithIA );
                        }
                        else {
                        	
                        	   testCaseScenario.log(Status.FAIL,"Please check The following register number " + regNo + " failed in " + baseSubject +" with Th mark " + totalThMark + " and Total Marks (with IA): " + totalMarkWithIA ,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
                        	
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid number format in TH or IA for: " + baseSubject);
                        testCaseScenario.log(Status.FAIL,
                            "⚠️ Invalid number format in TH or IA for: " + baseSubject);
                    }
                }
    	    	else if(valueStr.equals("A")) {
    	    		
	    			hasBacklog = true;
      	            absentCount++;
      	            backlogSubjects.append(key).append(",");

	            System.out.println("The following register number " +regNo+" for the Subject " + key + " has Absent " + valueStr);
	            testCaseScenario.log(Status.PASS, "The following register number " +regNo+" for the Subject " + key + " has Absent " + valueStr);
        	   	
	    	}
	    		
            }
      
        }
              
        double remainingGraceMark = 0.0;
        //Grace calucation with excel
     
      	       
      	    	
      	         // Prepare grace mark if Pass(G)
      	  
      	          if ("Pass(G)".equals(currentResultValue)) {
      	        	  List<WebElement> previousHeadings = driver.findElements(By.xpath(
      	                  "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th"));

      	              List<WebElement> previousMarks = driver.findElements(By.xpath(
      	                  "(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[1]/td"));
      	            
       	        	
       	        
      	   
      	              List<WebElement> tableRowSize = driver.findElements(By.xpath("(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr"));
      	              
      	              boolean hasPreviousSessional = previousHeadings.stream()
      	                  .anyMatch(heading -> heading.getText().trim().equalsIgnoreCase("Sessional"));

      	              System.out.println("tableRowSize.size(): "+ tableRowSize.size());
      	              
      	              if(tableRowSize.size()>=2) {//checking it really have s
      	            	 testCaseScenario.log(Status.INFO, "previous Sessional heading found. EX-Regular grace logic.");

      	              if (hasPreviousSessional) {
      	                  Map<String, String> previousExamData = new LinkedHashMap<>();
      	                  for (int i = 0; i < Math.min(previousHeadings.size(), previousMarks.size()); i++) {
      	                      previousExamData.put(previousHeadings.get(i).getText().trim(), previousMarks.get(i).getText().trim());
      	                  }

      	                  String previousSessionalMark = previousExamData.get("Sessional");

      	            testCaseScenario.log(Status.INFO, "Current Sessional Mark: " + currentSessionalMark);
      	              testCaseScenario.log(Status.INFO, "previous Sessional Mark: " + previousSessionalMark);
      	             

      	                  if (previousSessionalMark != null && currentSessionalMark != null) {
      	                      remainingGraceMark = Double.parseDouble(previousSessionalMark) - Double.parseDouble(currentSessionalMark);
      	                    testCaseScenario.log(Status.INFO, "Initial grace marks available: " + remainingGraceMark);

      	                      if (remainingGraceMark > 15) {
      	                    	testCaseScenario.log(Status.FAIL, "❌ Sessional mark difference exceeds 15 for RegNo: " + regNo);
      	                          return;
      	                      }
      	                  }
      	              } 
      	              
      	              
      	              }
      	              //for regular cases
      	              else {
      	            	testCaseScenario.log(Status.INFO, "No previous Sessional heading found. Regular grace logic.");

      	              String sessionalMarkExcel="";

      	              
      	              for (Map.Entry<String, String> expectedEntry : subjectsAndMarks.entrySet()) {
        	              String subjectName = expectedEntry.getKey();
     
        		 	   String[] marks = expectedEntry.getValue().split(";\\s*");
        		 	   
        		 	   String expectedMark = marks[0];
        		 	   sessionalMarkExcel = marks[1];

         	            testCaseScenario.log(Status.INFO, "Current Sessional Mark: " + currentSessionalMark);
         	              testCaseScenario.log(Status.INFO, "Excel Sessional Mark: " + sessionalMarkExcel);
         	       
         	              
         	              System.out.println( "Current Sessional Mark: " + currentSessionalMark);
         	              System.out.println( "Excel Sessional Mark: " + sessionalMarkExcel);
         	              
        	              String expectedHeading = objectToDataType(subjectName);

        	              System.out.println("subjectName: " +subjectName);
        	              System.out.println("expectedMark: " +expectedMark);
        	              System.out.println("expectedHeading: "+ expectedHeading);
        	              System.out.println("sessionalMarkExcel: "+ sessionalMarkExcel);
       	    	   }
      	              if (sessionalMarkExcel != null && currentSessionalMark != null) {
  	                      remainingGraceMark = Double.parseDouble(sessionalMarkExcel) - Double.parseDouble(currentSessionalMark);
  	                    testCaseScenario.log(Status.INFO, "Initial grace marks available in Excel sessional mark: " + remainingGraceMark);

  	                      if (remainingGraceMark > 15) {
  	                    	testCaseScenario.log(Status.FAIL, "❌ Sessional mark difference exceeds 15 for RegNo: " + regNo);
  	                          return;
  	                      }
  	                  }

      	              }
      	          }//if 
      	          
      	          
      	        if ("Pass(G)".equals(currentResultValue)) {
      	        ExtentTest testCaseScenario1  = testCaseScenario.createNode("GraceMark calculation for TH mark with internal mark the following register number "+regNo);
                
      	          // Step 2: Match each expected subject
      	          for (Map.Entry<String, String> expectedEntry : subjectsAndMarks.entrySet()) {
      	              String subjectName = expectedEntry.getKey();
      	              System.out.println(subjectName);
      	         
      	          
      	              
      		 	   String[] marks = expectedEntry.getValue().split(";\\s*");
      		 	   
      		 	   String expectedMark = marks[0];
      		 	   String sessionalMarkExcel = marks[1];

      	              String expectedHeading = objectToDataType(subjectName);

      	              System.out.println("subjectName: " +subjectName);
      	              System.out.println("expectedMark: " +expectedMark);
      	              System.out.println("expectedHeading: "+ expectedHeading);
      	              System.out.println("sessionalMarkExcel: "+ sessionalMarkExcel);
      	              
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
      	                          double neededForTH = 28.0 - uiMark;
      	                        
      	                          
      	                          System.out.println(uiMark);
//      	                          System.out.println(iaMarks);
//      	                          System.out.println(neededForTotal);
      	                          
      	                          if (neededForTH <= 0) {
      	                        	testCaseScenario1.log(Status.PASS, subjectName + "Th mark is already >= 28" +newMark );
      	                              continue;
      	                          }

      	                        testCaseScenario1.log(Status.INFO, "Checking grace for TH mark " + subjectName + ": needed=" + neededForTH + ", available=" + remainingGraceMark);

      	                          double adjustedTH = uiMark;
      	                          if (remainingGraceMark >= neededForTH) {
      	                        	adjustedTH = uiMark + neededForTH;
      	                              remainingGraceMark -= neededForTH;
      	                          } else {
      	                        	adjustedTH = uiMark + remainingGraceMark;
      	                              remainingGraceMark = 0;
      	                          }

      	                          if (Double.parseDouble(actualMark) == adjustedTH) {
      	                          
      	                            System.out.println(subjectName + " subject Th Mark " +adjustedTH +" from UI is MATCH after giving with grace: => " + adjustedTH);
      	                          testCaseScenario1.log(Status.PASS, subjectName + " subject Th Mark " +adjustedTH +" from UI is MATCH after giving with grace: => " + adjustedTH);
      	      	                  
      	      	                    formattedScriptBacklog = "Pass(G)";

      	                             
      	                              
      	                          } 
      	                      
      	                          else {
      	                        	  
      	                     //         testCaseScenario.log(Status.FAIL, " MISMATCH even after grace: " + subjectName + " => Expected: " + adjusted + ", Found: " + actualMark);
      	                            System.out.println(subjectName + " subject Th Mark " +adjustedTH +" from UI is MISMATCH even after giving with grace: =>  Expected: " + adjustedTH + ", Found: " + actualMark);
      	                          testCaseScenario1.log(Status.FAIL, subjectName + " subject Th Mark " +adjustedTH +" from UI is MISMATCH even after giving with grace: =>  Expected: " + adjustedTH + ", Found: " + actualMark);
      	                      
//      	                        ExtentTest testCaseScenario2  = testCaseScenario1.createNode("GraceMark calculation for Total mark with internal mark the following register number"+regNo);

      	                          
      	                          testCaseScenario1.log(Status.INFO, "Checking for grace for Total mark with internal mark the following register number"+regNo);
      	      	            double iaMarks=0.0;
      	      	                    if (iaMarkMap.containsKey(subjectName)) {
      	          	               iaMarks = iaMarkMap.get(subjectName);
      	          	              System.out.println("IA Mark for " + subjectName + ": " + iaMarks);
      	          	          testCaseScenario1.log(Status.INFO,"IA Mark for " + subjectName + ": " + iaMarks+" following register number"+regNo);     	          	              
      	          	              
      	          	              // You can now use iaMark in your validation here
      	          	          } else {
      	          	              System.out.println("No IA mark found for " + subjectName);
      	          	          testCaseScenario1.log(Status.INFO,"No IA mark found for " + subjectName,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());     	          	              
        	          	        
      	          	          }
      	      	           
      	      	                   System.out.println(adjustedTH+iaMarks);
    	                          double neededForTotal = 35.0 - (adjustedTH+iaMarks);
    	                        
    	                    
    	                          
    	                          System.out.println("neededForTotal: "+neededForTotal);
    	                
//    	                          System.out.println(iaMarks);
//    	                          System.out.println(neededForTotal);
    	                          
    	                          if (neededForTotal <= 0) {
    	                        	  testCaseScenario1.log(Status.PASS, subjectName + "Total mark is already >= above 35 :" +newMark );
    	                              continue;
    	                          }

    	                          testCaseScenario1.log(Status.INFO, "Checking grace for Total mark " + subjectName + ": needed=" + neededForTotal + ", available=" + remainingGraceMark);

    	                          System.out.println("adjustedTH"+adjustedTH);
    	                          double adjustedTotal = adjustedTH;
    	                          if (remainingGraceMark >= neededForTotal) {
    	                        	  adjustedTotal = adjustedTH + neededForTotal+iaMarks;
    	                              remainingGraceMark -= neededForTotal;
    	                          } else {
    	                        	  adjustedTotal = adjustedTH + remainingGraceMark+iaMarks;
    	                              remainingGraceMark = 0;
    	                          }
    	                  
    	                          System.out.println("adjustedTotal"+adjustedTotal);
    	                          System.out.println("remainingGraceMark"+remainingGraceMark);
    	                          System.out.println("neededForTotal"+ neededForTotal);
    	                          System.out.println(uiMark);
    	                          System.out.println(iaMarks);
    	                          System.out.println(remainingGraceMark);
    	                          double uiTotalMark = Double.parseDouble(actualMark)+iaMarks;
 	                             

    	                          if (uiTotalMark == adjustedTotal) {
    	                          
    	                            System.out.println(subjectName + " subject Total Mark with IA  " +uiTotalMark +" from UI is MATCH after giving with grace: => " + adjustedTotal);
    	                            testCaseScenario1.log(Status.PASS, subjectName + " subject Total Mark with IT " +uiTotalMark +" from UI is MATCH after giving with grace: => " + adjustedTotal);
    	      	                  
    	      	                    formattedScriptBacklog = "Pass(G)";
    	      	                  
    	                          
    	                          } else {
    	                        	  System.out.println(subjectName + "  subject Total Mark with IA  " + uiTotalMark +" from UI is MISMATCH after giving with grace: => " + adjustedTotal);
    	                        	  testCaseScenario1.log(Status.FAIL, subjectName + "  subject Total Mark with IA  " + uiTotalMark +" from UI is MISMATCH after giving with grace: => " + adjustedTotal);
      	      	                    
    	                          }
      	      	                    
      	      	                    
      	                          }

      	                        testCaseScenario1.log(Status.INFO, "Remaining grace: " + remainingGraceMark);
      	                      System.out.println( "Remaining grace: " + remainingGraceMark);
      	                      
      	                	if (remainingGraceMark == 0) {
      	                  	testCaseScenario1.log(Status.PASS, "Grace mark for RegNo: " + regNo +" is equals zero "+ remainingGraceMark);
      	                      
      	                    }
      	              	 else if (remainingGraceMark != 0) {
      	                   	testCaseScenario1.log(Status.INFO, "Grace mark for RegNo: " + regNo +" is not equals zero "+ remainingGraceMark);
      	                       
      	                     }
      	              	 else {
      	              		testCaseScenario1.log(Status.FAIL, "Please check the Grace mark for RegNo: " + regNo +" remaining grace mark "+ remainingGraceMark);
      	                   
      	              	 }
      	                      }
      	                  }
      	              } else {
      	            	testCaseScenario.log(Status.FAIL, "❌ Heading not found in UI: " + expectedHeading);
      	              }
      	          }//for
      	        }//if
      	       
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
       	    	
       	       ExtentTest testCaseScenarioForBacklog = testCaseName
                       .createNode("Result page backlog comparison for the " + semester + " of the following register number " + regNo + " — Test case started");

       	    	
       	        if (formattedScriptBacklog.equalsIgnoreCase(currentResultValue)) {
       	            System.out.println("Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
       	         testCaseScenarioForBacklog.log(Status.PASS, "Backlog comparison PASS: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
       	        }
       	        
      	        else {
      	            System.out.println("Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue);
      	          testCaseScenarioForBacklog.log(Status.FAIL, "Backlog comparison FAILED: Script - " + formattedScriptBacklog + " | UI - " + currentResultValue,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
      	        }
      	        comparisonDone = true;
      	    }
   
         

    } catch (Exception e) {
        System.out.println("❌ Exception in regnoEnter1: " + e.getMessage());
        e.printStackTrace();
        testCaseName.log(Status.FAIL, "Exception: " + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
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