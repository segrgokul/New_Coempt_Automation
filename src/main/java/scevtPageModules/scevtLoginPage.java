package scevtPageModules;


	import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasicFunctions;
import pageObjMod.sctevtPom;

	public class scevtLoginPage  extends BasicFunctions {

			

		WebElement th1 = null;
		WebElement th1IA = null;
		WebElement th2 = null;
		WebElement th2IA = null;
		WebElement th3 = null;
		WebElement th3IA = null;
		WebElement th4 = null;
		WebElement th4IA = null;
		WebElement pr1 = null;
		WebElement pr2 = null;
		WebElement pr3 = null;
		WebElement sessional = null;
		WebElement total = null;
		WebElement result = null;
			  public  void login(Object admin, Object passWord,ExtentTest testCaseName) throws InterruptedException, IOException, AWTException {
			    	
					ExtentTest testCaseScenario = testCaseName
							.createNode(" Login page validation for "+ admin +" Validation Test case for  has started running");

				try {
	
					
					
					explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().userName,10);
				      	
			      	 
			      	 click(sctevtPom.getInstanceSctevtLoginXpaths().userName);
				
							
				  	 sendKeys(sctevtPom.getInstanceSctevtLoginXpaths().userName,admin);
				  	 
				  	 
				 	explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().passWord,10);
	
			      	 
			      	 click(sctevtPom.getInstanceSctevtLoginXpaths().passWord);
	
					
				  
				  	 
				  	 sendKeys(sctevtPom.getInstanceSctevtLoginXpaths().passWord,passWord);
					
					 	System.out.println(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

					Thread.sleep(30000); //30 sec it will wait for capctha entering
					
			
				 	System.out.println(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

					
					explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin,10);
			      	
				 	System.out.println(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

				//	scrollTillWebElement(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin);
						
					 click(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin);
					   	 
				   	 try {
				   	 while(sctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
					
				   		 if(sctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
							
							click(sctevtPom.getInstanceSctevtLoginXpaths().okBtn);	
					
							Thread.sleep(10000); //30 sec it will wait for capctha entering
						 	explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin,10);
					      	
							 click(sctevtPom.getInstanceSctevtLoginXpaths().btnLogin);			
							 
							}
				   		 else {
				   			break;
				   		}}}
				   	 catch(Exception e) {
				   		Thread.sleep(10000); //30 sec it will wait for capctha entering
				   		 if(sctevtPom.getInstanceSctevtLoginXpaths().userIconArea.isDisplayed()) {
								testCaseScenario.log(Status.PASS, "Login page validation for "+ admin +" Validation is entered sucessfully");
									
							 }
							 else {
									testCaseScenario.log(Status.FAIL, "Login page validation for "+ admin +" Validation is not entered sucessfully" ,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
									  
							 } 
				   	 }
						
				
				}
				catch(Exception e) {
				if(sctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
							
							click(sctevtPom.getInstanceSctevtLoginXpaths().okBtn);	
							
								
							testCaseScenario.log(Status.FAIL, "Login page validation for "+ admin +" Validation is not entered sucessfully" + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						 	 
							}
					
			
				}
			  }
			   		
		 public void resultPageNavigation(Object admin, Object passWord,ExtentTest testCaseName) throws IOException {
			 
			 
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Result page navigation for "+ admin +"  Test case is sucessful");
		try {
			
			 
			   	
				
				if(sctevtPom.getInstanceSctevtLoginXpaths().resultsBtn.isDisplayed()) {
				explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().resultsBtn,10);
			      	
		      	
		      	 
		      	 click(sctevtPom.getInstanceSctevtLoginXpaths().resultsBtn);
		      	 
		
		      	explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().resultsExamHistoryBtn,10);
		      	
		     	
		     	 
		     	 click(sctevtPom.getInstanceSctevtLoginXpaths().resultsExamHistoryBtn);
		     	 
		     	testCaseScenario.log(Status.PASS, "Result page navigation for "+ admin +" is entered sucessfully");
				}
				
				
		}
		catch(Exception e) {
			testCaseScenario.log(Status.FAIL,e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
		}

		 }
		 public void regnoEnter(Object regno, Object semester ,ExtentTest testCaseName) throws IOException {
			 
			 
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");
		try {
			
			 
			   	
				
				if(sctevtPom.getInstanceSctevtLoginXpaths().rollNoTextBox.isDisplayed()) {
				explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().rollNoTextBox,10);
			      	
		   	
		   	 
		   	 click(sctevtPom.getInstanceSctevtLoginXpaths().rollNoTextBox);
		   	 
		
			
			  
		 	 
		 	 sendKeys(sctevtPom.getInstanceSctevtLoginXpaths().rollNoTextBox,regno);
		 	 
		   	
			 explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().btnViewStudentResultsList,10);
		      	
			  	
			   	 
			   	 click(sctevtPom.getInstanceSctevtLoginXpaths().btnViewStudentResultsList);
				 
			   	 try {
			   	
			
				 
			   	 if(sctevtPom.getInstanceSctevtLoginXpaths().loadingStudentHistoryAlert.isDisplayed()) {
			   	   
			   		 explicitWait(sctevtPom.getInstanceSctevtLoginXpaths().studentHistory,50);
			   	 	 	 
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
		 

		 public void regnoEnter1(Object regno, Object semester ,ExtentTest testCaseName) throws IOException {
			 
			 
				ExtentTest testCaseScenario = testCaseName
						.createNode(" Result page navigation for the "+ semester +"of the following register number "+ regno +"  Test case is sucessful");
		 
		 
				WebElement sem = driver.findElement(By.xpath("//h5[@class='nk-block-title' and text()='SEMESTER - " + semester + "']"));
				
				scrollTillWebElement(sem);
				//need to do this part
				
			    List<WebElement>   headingNames= driver.findElements(By.xpath("(//h5[text()='SEMESTER - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th"))	;	
				
			    for (WebElement heading :headingNames){
			    	
			    	System.out.println("headings:" +heading.getText());
			    }
			    
			//    assignExamHeaders(headingNames);
			 
			    
			    List<WebElement> examMarks=driver.findElements(By.xpath("(//h5[text()='SEMESTER  - " + semester + "']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td"));
			//    (//h5[text()='SEMESTER - 1']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td
			    for (WebElement marks: examMarks) {
			    	
			    	
			    	System.out.println("Marks: "+marks.getText());
			    	
			    	
			    }
			    
			    
		//-- for heading	//	(//h5[text()='SEMESTER - 1']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders'])[last()]//th
				
		// -- for last row		//(//h5[text()='SEMESTER - 1']/ancestor::div[contains(@class, 'nk-block-head')]/following-sibling::div//table[@class='table table-orders']//tbody[@class='tb-odr-body'])[3]/tr[last()]/td
		 }
		 public static Map<String, WebElement> assignExamHeaders(List<WebElement> examHeaders) {
		     Map<String, WebElement> examMap = new HashMap<>();

		     for (WebElement el : examHeaders) {
		         String text = el.getText().trim();
		         
		         System.out.println("Processing header: " + text);  // Debug statement

		         switch (text) {
		             case "TH1":
		                 examMap.put("TH1", el);
		                 System.out.println("Assigned TH1");  // Debug statement
		                 break;
		             case "TH1IA":
		                 examMap.put("TH1IA", el);
		                 System.out.println("Assigned TH1IA");  // Debug statement
		                 break;
		             case "TH2":
		                 examMap.put("TH2", el);
		                 System.out.println("Assigned TH2");  // Debug statement
		                 break;
		             case "TH2IA":
		                 examMap.put("TH2IA", el);
		                 System.out.println("Assigned TH2IA");  // Debug statement
		                 break;
		             case "TH3":
		                 examMap.put("TH3", el);
		                 System.out.println("Assigned TH3");  // Debug statement
		                 break;
		             case "TH3IA":
		                 examMap.put("TH3IA", el);
		                 System.out.println("Assigned TH3IA");  // Debug statement
		                 break;
		             case "TH4":
		                 examMap.put("TH4", el);
		                 System.out.println("Assigned TH4");  // Debug statement
		                 break;
		             case "TH4IA":
		                 examMap.put("TH4IA", el);
		                 System.out.println("Assigned TH4IA");  // Debug statement
		                 break;
		             case "PR1":
		                 examMap.put("PR1", el);
		                 System.out.println("Assigned PR1");  // Debug statement
		                 break;
		             case "PR2":
		                 examMap.put("PR2", el);
		                 System.out.println("Assigned PR2");  // Debug statement
		                 break;
		             case "PR3":
		                 examMap.put("PR3", el);
		                 System.out.println("Assigned PR3");  // Debug statement
		                 break;
		             case "Sessional":
		                 examMap.put("Sessional", el);
		                 System.out.println("Assigned Sessional");  // Debug statement
		                 break;
		             case "Total":
		                 examMap.put("Total", el);
		                 System.out.println("Assigned Total");  // Debug statement
		                 break;
		             case "Result":
		                 examMap.put("Result", el);
		                 System.out.println("Assigned Result");  // Debug statement
		                 break;
		             default:
		                 // Optionally handle unexpected headers
		                 System.out.println("Unexpected header: " + text);  // Debug statement
		                 break;
		         }
		     }

		     return examMap;
		 }
		}

	

