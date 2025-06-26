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

public class SctevtExamHistoryRegNoSearchPage extends BasicFunctions {
	 public void resultPageNavigation(Object admin, Object passWord,ExtentTest testCaseName) throws IOException {
		 
		 
			try {


				ExtentTest testCaseScenario = testCaseName
						.createNode(" Result page navigation for "+ admin +"  Test case is sucessful");

	
	if(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn.isDisplayed()) {
	explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn,10);
   	
	
	 
	 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().resultsExamHistoryBtn);
	 
	 scrollTillWebElement(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn);

	explicitWait(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn,10);
	
	
	 
	 click(SctevtPom.getInstanceSctevtExamHistoryRegNoSearchPageXPaths().regNoSearchBtn);
	 
	testCaseScenario.log(Status.PASS, "Exam History Register Number page navigation for "+ admin +" is entered sucessfully");
	}
	
	
}
catch(Exception e) {

ExtentTest testCaseScenario = testCaseName
		.createNode(" Result page navigation for "+ admin +"  Test case is sucessful");


testCaseScenario.log(Status.FAIL,e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
}

}
public void regnoEnter(Object regno, Object semester ,ExtentTest testCaseName) throws IOException {


	ExtentTest testCaseScenario = testCaseName
			.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");
try {


	
	
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
testCaseScenario.log(Status.FAIL,e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
}


}


public void regnoEnter1(Object regno, Object semester,Object subjectName,Object semesterMark,ExtentTest testCaseName) throws IOException {


	try {
	ExtentTest testCaseScenario = testCaseName
			.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");


	WebElement sem = driver.findElement(By.xpath("//h5[@class='nk-block-title' and text()='SEMESTER - " + semester + "']"));
	
	scrollTillWebElement(sem);
	//need to do this part
	// Step 1: Get the headings
	List<WebElement> currentHeadingNames = driver.findElements(By.xpath("(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th"));

	List<String> currentHeadingsList = new ArrayList<>();
	List<String> currentMarksList = new ArrayList<>();
	
	for (WebElement CurrentHeading : currentHeadingNames) {
	    String text = CurrentHeading.getText().trim();
	    currentHeadingsList.add(text);
	    System.out.println("headings: " + text);
	  //  testCaseScenario.log(Status.PASS, "headings: " + text);
	}

	// Step 2: Get the marks
	List<WebElement> currentExamMarks = driver.findElements(By.xpath( "(//h5[normalize-space(text())='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td"));


	for (WebElement currentMarks : currentExamMarks) {
	    String text = currentMarks.getText().trim();
	    currentMarksList.add(text);
	    System.out.println("Marks: " + text);
	//    testCaseScenario.log(Status.PASS, "Marks: " + text);
	}

	// Step 3: Map headings to marks
	Map<String, String> currentexamData = new LinkedHashMap<>();
	int minSize = Math.min(currentHeadingsList.size(), currentMarksList.size());

	for (int i = 0; i < minSize; i++) {
		currentexamData.put(currentHeadingsList.get(i), currentMarksList.get(i));
	}
	
	String currentSessionalMark =currentexamData.get("Sessional");
	String currentResultValue = currentexamData.get("Result");

	System.out.println("currentSessionalMark"+currentSessionalMark);
	System.out.println("currentResultValue"+currentResultValue);
	
	if (currentResultValue != null) {
	    System.out.println("Extracted Result: " + currentResultValue);
	    testCaseScenario.log(Status.PASS, "Extracted Result: " + currentResultValue);
	} else {
	    System.out.println("Result key not found in examData");
	    testCaseScenario.log(Status.FAIL, "Result key not found in examData");
	}
	

	// Step 4: Log or use mapped data
	for (Map.Entry<String, String> entry : currentexamData.entrySet()) {
	    System.out.println(entry.getKey() + " => " + entry.getValue());
	    testCaseScenario.log(Status.PASS, entry.getKey() + " => " + entry.getValue());
	

	String expectedHeading = objectToDataType( subjectName);
	String expectedMark = objectToDataType( semesterMark);

	System.out.println(currentexamData);
	System.out.println(expectedHeading);
	
	
	if (currentexamData.containsKey(expectedHeading)) {
	    String actualMark = currentexamData.get(expectedHeading).trim();

	    double uiSemMark = Double.parseDouble(actualMark);
	    
	    if (actualMark.equals(expectedMark)) {
	        System.out.println("MATCH: " + expectedHeading + " => " + expectedMark);
	        testCaseScenario.log(Status.PASS, "The following "+ regno +" is MATCHED with Excel DATA " + expectedHeading + " => " + expectedMark);
	    } else {
	    	
	    	
	    	if (currentResultValue.equals("Pass(G)")) {
	    //		(//h5[text()='SEMESTER - 5']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[1]/td
	    		
	    		
	    				// Step 1: Get the headings
	    		List<WebElement> previousHeadingNames = driver.findElements(By.xpath("(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th"));


	    				List<String> previousHeadingsList = new ArrayList<>();
	    				List<String> previousMarksList = new ArrayList<>();
	    				
	    				for (WebElement previousHeading : previousHeadingNames) {
	    				    String text = previousHeading.getText().trim();
	    				    previousHeadingsList.add(text);
	    				    System.out.println("headings: " + text);
	    			//	    testCaseScenario.log(Status.PASS, "headings: " + text);
	    				}

	    				// Step 2: Get the marks
	    				List<WebElement> previousExamMarks = driver.findElements(By.xpath("(//h5[normalize-space(text())='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[1]/td"));

	    			
	    				for (WebElement previousmarks : previousExamMarks) {
	    				    String text = previousmarks.getText().trim();
	    				    previousMarksList.add(text);
	    				    System.out.println("Marks: " + text);
	    				//    testCaseScenario.log(Status.PASS, "Marks: " + text);
	    				}
	    				// Step 3: Map headings to marks
	    				Map<String, String> previousExamData = new LinkedHashMap<>();
	    				int previousMinSize = Math.min(previousHeadingsList.size(), previousMarksList.size());

	    				for (int i = 0; i < previousMinSize; i++) {
	    					previousExamData.put(previousHeadingsList.get(i), previousMarksList.get(i));
	    				}
	    				
	    				String previousSessionalMark =previousExamData.get("Sessional");
	    				System.out.println("previousSessionalMark"+previousSessionalMark);
	    				
	    				
	    				
	    				if(!previousSessionalMark.equals(currentSessionalMark)) {
	    					
	    					double minusMark =Double.parseDouble(previousSessionalMark) -Double.parseDouble(currentSessionalMark);
	    					
	    					double newMark = minusMark +Double.parseDouble(objectToDataType( semesterMark));
	    					
	    					System.out.println("newMark"+newMark);
	    				    testCaseScenario.log(Status.PASS, "newMark: " + newMark);
	    				
	    				    
	    				    System.out.println("actualMark"+actualMark);
	    				    if (uiSemMark == newMark) {
	    				        System.out.println("MATCH: " + expectedHeading + " => " + expectedMark);
	    				        testCaseScenario.log(Status.PASS, "The following register number "+ regno +" before giving sessional mark of "+ minusMark +" with Excel DATA is " + expectedHeading + " => " + expectedMark);
	    				        testCaseScenario.log(Status.PASS, "The following register number "+ regno +" After giving sessional mark of "+ minusMark +" with Excel DATA is " + expectedHeading + " => " + newMark);
	    				    }
	    				
	    				}
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    	}
	    	
//	        System.out.println("MISMATCH: " + expectedHeading + " => Expected: " + expectedMark + ", Found: " + actualMark);
//	        testCaseScenario.log(Status.FAIL, "MISMATCH: " + expectedHeading + " => Expected: " + expectedMark + ", Found: " + actualMark);
	    }
	} else {
	    System.out.println("Heading not found: " + expectedHeading);
	    testCaseScenario.log(Status.FAIL, "Heading not found: " + expectedHeading);
	}

	}
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		
		e.printStackTrace();
	}}
	
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
}}