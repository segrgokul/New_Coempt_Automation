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
import pageObjMod.SctevtPom;

	public class ScevtLoginPage  extends BasicFunctions {

			

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
	
					
					
					explicitWait(SctevtPom.getInstanceSctevtLoginXpaths().userName,10);
				      	
			      	 
			      	 click(SctevtPom.getInstanceSctevtLoginXpaths().userName);
				
							
				  	 sendKeys(SctevtPom.getInstanceSctevtLoginXpaths().userName,admin);
				  	 
				  	 
				 	explicitWait(SctevtPom.getInstanceSctevtLoginXpaths().passWord,10);
	
			      	 
			      	 click(SctevtPom.getInstanceSctevtLoginXpaths().passWord);
	
					
				  
				  	 
				  	 sendKeys(SctevtPom.getInstanceSctevtLoginXpaths().passWord,passWord);
					
					 	System.out.println(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

					Thread.sleep(30000); //30 sec it will wait for capctha entering
					
			
				 	System.out.println(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

					
					explicitWait(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin,10);
			      	
				 	System.out.println(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin.isDisplayed());			

				//	scrollTillWebElement(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin);
						
					 click(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin);
					   	 
				   	 try {
				   	 while(SctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
					
				   		 if(SctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
							
							click(SctevtPom.getInstanceSctevtLoginXpaths().okBtn);	
					
							Thread.sleep(10000); //30 sec it will wait for capctha entering
						 	explicitWait(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin,10);
					      	
							 click(SctevtPom.getInstanceSctevtLoginXpaths().btnLogin);			
							 
							}
				   		 else {
				   			break;
				   		}}}
				   	 catch(Exception e) {
				   		Thread.sleep(10000); //30 sec it will wait for capctha entering
				   		 if(SctevtPom.getInstanceSctevtLoginXpaths().userIconArea.isDisplayed()) {
								testCaseScenario.log(Status.PASS, "Login page validation for "+ admin +" Validation is entered sucessfully");
									
							 }
							 else {
									testCaseScenario.log(Status.FAIL, "Login page validation for "+ admin +" Validation is not entered sucessfully" ,MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
									  
							 } 
				   	 }
						
				
				}
				catch(Exception e) {
				if(SctevtPom.getInstanceSctevtLoginXpaths().okBtn.isDisplayed()) {
							
							click(SctevtPom.getInstanceSctevtLoginXpaths().okBtn);	
							
								
							testCaseScenario.log(Status.FAIL, "Login page validation for "+ admin +" Validation is not entered sucessfully" + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(BasicFunctions.capture(driver)).build());
						 	 
							}
					
			
				}
			  }
			   		
	
		}

	

