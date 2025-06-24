package knrPageModules;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;

import base.BasicFunctions;
import browsers.BrowserManager;

import pageObjMod.knrPom;

public class KnrLoginPage extends BasicFunctions {


    public void DirectSignIn() throws InterruptedException, IOException {
    	
//		ExtentReports report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\report.html",true);
//
//		ExtentTest test = report.startTest("ReportCardNavigation");	
//		
		
    	
    	try {
    	
			implicitWait(30);	
			explicitWait(knrPom.getInstanceLoginXP().signinBtn,30);
			click(knrPom.getInstanceLoginXP().signinBtn);
			
			if (knrPom.getInstanceLoginXP().invaidUserName.isDisplayed()) {

	//			test.log(LogStatus.PASS, "User is unable to enter with direct signin button ");
				 
			 System.out.println("User is unable to enter with direct signin button");
			 knrPom.getInstanceLoginXP().alertOkBtn.click();
		
	 }
    	}
    	
    	catch(Exception e){
    		e.printStackTrace();
			capture(driver);
   	}
    		
    	
       	
   	 
    		
    	}

	

	public void DirectPassEntry() throws InterruptedException, IOException {
		

      	 implicitWait(30);
		 explicitWait(knrPom.getInstanceLoginXP().userpass,30);
       	 click(knrPom.getInstanceLoginXP().userpass);
       	 implicitWait(30);
       	 
       	 sendKeys(knrPom.getInstanceLoginXP().userpass,"Coempt@bpu");
			    
       	implicitWait(30);	
   		explicitWait(knrPom.getInstanceLoginXP().signinBtn,30);
       	click(knrPom.getInstanceLoginXP().signinBtn);
    	implicitWait(30);	
       	
		if (knrPom.getInstanceLoginXP().invaidUserName.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct userpass");
			implicitWait(30);	
	   		explicitWait(knrPom.getInstanceLoginXP().alertOkBtn,30);
			knrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			knrPom.getInstanceLoginXP().userpass.clear();
			
			
	}}
	
	public void DirectUserEntry() throws InterruptedException, IOException {
		

     	 implicitWait(30);
		
     	 explicitWait(knrPom.getInstanceLoginXP().userName,30);
      	
     	 click(knrPom.getInstanceLoginXP().userName);
      	
      	 implicitWait(30);
      	 
      	 sendKeys(knrPom.getInstanceLoginXP().userName,"admin");
			    
      	implicitWait(30);	
  		explicitWait(knrPom.getInstanceLoginXP().signinBtn,30);
      	click(knrPom.getInstanceLoginXP().signinBtn);
      	
		if (knrPom.getInstanceLoginXP().invaidPassword.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct username");
			implicitWait(30);	
	   		explicitWait(knrPom.getInstanceLoginXP().alertOkBtn,30);
			knrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			knrPom.getInstanceLoginXP().userName.clear();
			
			
	}}
	
	public void LoginInFail() throws InterruptedException, IOException {
		

    	 implicitWait(30);
		
    	 explicitWait(knrPom.getInstanceLoginXP().userName,30);
     	
    	 click(knrPom.getInstanceLoginXP().userName);
     	
     	 implicitWait(30);
     	 
     	 sendKeys(knrPom.getInstanceLoginXP().userName,RandomStringUtils.randomAlphanumeric(8));
			    
     	 implicitWait(30);
		 explicitWait(knrPom.getInstanceLoginXP().userpass,30);
       	 click(knrPom.getInstanceLoginXP().userpass);
       	 implicitWait(30);
       	 
       	 sendKeys(knrPom.getInstanceLoginXP().userpass,RandomStringUtils.randomAlphanumeric(8));
			    
       	implicitWait(30);	
   		explicitWait(knrPom.getInstanceLoginXP().signinBtn,30);
       	click(knrPom.getInstanceLoginXP().signinBtn);
    	implicitWait(30);	
     	
		if (knrPom.getInstanceLoginXP().loginFail.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct username");
			implicitWait(30);	
	   		explicitWait(knrPom.getInstanceLoginXP().alertOkBtn,30);
			knrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			knrPom.getInstanceLoginXP().userName.clear();
			implicitWait(30);
			knrPom.getInstanceLoginXP().userpass.clear();
			
	}}
	
	   public static void Login() throws InterruptedException, IOException, AWTException {
		   String  loginName = BrowserManager.properties.getProperty("login_name", "KNR_test").toLowerCase();
      	 if (loginName.contains("knr")){
             
      		String  username = BrowserManager.properties.getProperty(loginName + "_username");
      		String    password = BrowserManager.properties.getProperty(loginName + "_password");
           
        

          try {
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(knrPom.getInstanceLoginXP().userName, 30);
          	BasicFunctions.click(knrPom.getInstanceLoginXP().userName);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.sendKeys(knrPom.getInstanceLoginXP().userName, username);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(knrPom.getInstanceLoginXP().userpass, 30);
          	BasicFunctions.click(knrPom.getInstanceLoginXP().userpass);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.sendKeys(knrPom.getInstanceLoginXP().userpass, password);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(knrPom.getInstanceLoginXP().signinBtn, 30);
          	BasicFunctions.click(knrPom.getInstanceLoginXP().signinBtn);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(knrPom.getInstanceLoginXP().loginTags, 30);
              if (knrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
                  System.out.println("The Admin Login Page has logged in, and the landing page of KNRUHS is displayed.");
              }
          } catch (Exception e) {
          
              System.out.println("Login failed: " + e.getMessage());
          }
      	 }
	   }
	
	public void Logout() throws IOException {		
		

		implicitWait(30);	
   		explicitWait(knrPom.getInstanceLoginXP().loginTags,30);		
		
			if (knrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
				
				implicitWait(30);	
		   		explicitWait(knrPom.getInstanceLoginXP().loginTags,30);
				
				click(knrPom.getInstanceLoginXP().loginTags);
				
				implicitWait(30);	
		   		explicitWait(knrPom.getInstanceLoginXP().signOutBtn,30);
		   		click(knrPom.getInstanceLoginXP().signOutBtn);
			
				
				
			}
			}
			
	
//	if (invaidUserName.isDisplayed()) {
//		WebElement alertOkBtn = driver.findElement(By.xpath("//button[@type='button' and text()='OK']"));
//		
//		System.out.println("The Admin Login Page has unable to login with invalid userpass");
//		alertOkBtn.click();
//		
//		WebElement signoutBtn = driver.findElement(By.xpath("//span[contains(text(),'Sign out')]")));
//		signoutBtn.click();
//	
//	
//		
//	}
//	
	
//	
//	public void ReportCardCheck(long regno,String examdate,String awardName,String semester,long regulation,String examType) {		
//		
//
//		implicitWait(30);	
//   		explicitWait(knrPom.getInstanceLoginXP().loginTags,30);		
//		
//			if (knrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
//				
//				implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().loginTags,30);
//				
//		   		jsScroll(knrPom.getInstanceLoginXP().reportCardOption);
//			
//				implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().reportCardOption,30);
//		   		click(knrPom.getInstanceLoginXP().reportCardOption);
//			
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().reportCardEnroll,30);
//				
//	    		jsScroll(knrPom.getInstanceLoginXP().reportCardEnroll);
//				
//				implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().reportCardEnroll,30);
//		   		click(knrPom.getInstanceLoginXP().reportCardEnroll);
//		
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().enrollNo,30);
//        		click(knrPom.getInstanceLoginXP().enrollNo);
//		   		
//		   		String str = String.valueOf(regno);
//		   		System.out.println(str); 
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().enrollNo,30);
//		   		sendKeys(knrPom.getInstanceLoginXP().enrollNo, str);
//		   		implicitWait(30);	
//////		   		explicitWait(knrPom.getInstanceLoginXP().submitBtn,30);
//////		   		click(knrPom.getInstanceLoginXP().submitBtn);
////		   		
//		   		explicitWait(knrPom.getInstanceLoginXP().examSeries,30);
//		   		click(knrPom.getInstanceLoginXP().examSeries);
//		   		
//		   		explicitWait(knrPom.getInstanceLoginXP().examSeries,30);
//		   		click(knrPom.getInstanceLoginXP().examSeries);
////		   		
//		   		explicitWait(knrPom.getInstanceLoginXP().examSeries,30);
//		   		click(knrPom.getInstanceLoginXP().examSeries);
////		   	
////		if(knrPom.getInstanceLoginXP().alertOk.isDisplayed()) {
////		   		
////		   		implicitWait(30);	
////		   		explicitWait(knrPom.getInstanceLoginXP().alertOk,30);
////		   		click(knrPom.getInstanceLoginXP().alertOk);
////			}
//
//		
//		   		
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().examSeries,30);
//    	   		click(knrPom.getInstanceLoginXP().examSeries);
////		   		implicitWait(30);	
////		   		
////		   	//li[contains(@class,'select2-results__option')
//		   		WebElement examDateOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + examdate + "']"));
//		   		explicitWait(examDateOption,30);
//		   		
//		   		click(examDateOption);
//		    	
////		   		dropDownClick(knrPom.getInstanceLoginXP().examSeriesOption,examdate);
//		   		
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().awardName,30);
//		   		click(knrPom.getInstanceLoginXP().awardName);
////
////		   	//li[contains(@class,'select2-results__option') and text()!='Select']
////		   	// Construct XPath with the dynamic text
//		    	WebElement awardOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + awardName + "']"));
//		    	click(awardOption);
//
////
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().yearSession,30);
//		   		click(knrPom.getInstanceLoginXP().yearSession);
////		   		
////		   		implicitWait(30);	
////		   		
////		   	//li[contains(@class,'select2-results__option')
//		   		WebElement yearSessionOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + semester + "']"));
//		    	click(yearSessionOption);		    	
////		    	
//		    	implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().regulation,30);
//		   		click(knrPom.getInstanceLoginXP().regulation);
//		   		implicitWait(30);	
//		    	
//		   		WebElement regulationOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + regulation + "']"));
//		    	click(regulationOption);		    	
//		    	
//	    	
//		    	implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().examType,30);
//		   		click(knrPom.getInstanceLoginXP().examType);
//	    		implicitWait(30);	
//		    	
//		   		WebElement examTypeOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + examType + "']"));
//		    	click(examTypeOption);	
//		    	
//		    	implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().submitBtn,30);
//		   		click(knrPom.getInstanceLoginXP().submitBtn);
//	    	
//     	   		String str = String.valueOf(regno);
//		   		System.out.println(str); 
//		   		implicitWait(30);	
//		   		explicitWait(knrPom.getInstanceLoginXP().enrollNo,30);
//		   		sendKeys(knrPom.getInstanceLoginXP().enrollNo, str );
//	   		
//}}
	
	
//			
	public void SignIn() throws InterruptedException {	
			//input[@id='username']

//	Actions actions = new Actions(driver);
//	actions.moveToElement(knrPom.getInstanceLoginXP().userName).click().perform();

//
//		WebElement check= driver.findElement(By.xpath("//label[@class='form-label']"));
//		
//		check.getText();
//		System.out.println(check);
		
	//fluentWait(knrPom.getInstanceLoginXP().userName);
//	
//	
//	
//
//	
//	
//		Thread.sleep(5000);
//    fluentWait(knrPom.getInstanceLoginXP().userName);
//	click( knrPom.getInstanceLoginXP().userName);
//	implicitWait( knrPom.getInstanceLoginXP().userName, 5);
//	sendKeys(knrPom.getInstanceLoginXP().userName, "admin");
//	
//	
		

			

//	implicitWait( knrPom.getInstanceLoginXP().userpass, 10);
	
	}
	
	


	
	



  
}

	


    
    

