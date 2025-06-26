package knrPageModules;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;

import base.BasicFunctions;
import browsers.BrowserManager;

import pageObjMod.KnrPom;

public class KnrLoginPage extends BasicFunctions {


    public void DirectSignIn() throws InterruptedException, IOException {
    	
//		ExtentReports report = new ExtentReports("D:\\Coempt_Automation\\coempt_automation\\src\\test\\resources\\reports\\report.html",true);
//
//		ExtentTest test = report.startTest("ReportCardNavigation");	
//		
		
    	
    	try {
    	
			implicitWait(30);	
			explicitWait(KnrPom.getInstanceLoginXP().signinBtn,30);
			click(KnrPom.getInstanceLoginXP().signinBtn);
			
			if (KnrPom.getInstanceLoginXP().invaidUserName.isDisplayed()) {

	//			test.log(LogStatus.PASS, "User is unable to enter with direct signin button ");
				 
			 System.out.println("User is unable to enter with direct signin button");
			 KnrPom.getInstanceLoginXP().alertOkBtn.click();
		
	 }
    	}
    	
    	catch(Exception e){
    		e.printStackTrace();
			capture(driver);
   	}
    		
    	
       	
   	 
    		
    	}

	

	public void DirectPassEntry() throws InterruptedException, IOException {
		

      	 implicitWait(30);
		 explicitWait(KnrPom.getInstanceLoginXP().userpass,30);
       	 click(KnrPom.getInstanceLoginXP().userpass);
       	 implicitWait(30);
       	 
       	 sendKeys(KnrPom.getInstanceLoginXP().userpass,"Coempt@bpu");
			    
       	implicitWait(30);	
   		explicitWait(KnrPom.getInstanceLoginXP().signinBtn,30);
       	click(KnrPom.getInstanceLoginXP().signinBtn);
    	implicitWait(30);	
       	
		if (KnrPom.getInstanceLoginXP().invaidUserName.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct userpass");
			implicitWait(30);	
	   		explicitWait(KnrPom.getInstanceLoginXP().alertOkBtn,30);
			KnrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			KnrPom.getInstanceLoginXP().userpass.clear();
			
			
	}}
	
	public void DirectUserEntry() throws InterruptedException, IOException {
		

     	 implicitWait(30);
		
     	 explicitWait(KnrPom.getInstanceLoginXP().userName,30);
      	
     	 click(KnrPom.getInstanceLoginXP().userName);
      	
      	 implicitWait(30);
      	 
      	 sendKeys(KnrPom.getInstanceLoginXP().userName,"admin");
			    
      	implicitWait(30);	
  		explicitWait(KnrPom.getInstanceLoginXP().signinBtn,30);
      	click(KnrPom.getInstanceLoginXP().signinBtn);
      	
		if (KnrPom.getInstanceLoginXP().invaidPassword.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct username");
			implicitWait(30);	
	   		explicitWait(KnrPom.getInstanceLoginXP().alertOkBtn,30);
			KnrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			KnrPom.getInstanceLoginXP().userName.clear();
			
			
	}}
	
	public void LoginInFail() throws InterruptedException, IOException {
		

    	 implicitWait(30);
		
    	 explicitWait(KnrPom.getInstanceLoginXP().userName,30);
     	
    	 click(KnrPom.getInstanceLoginXP().userName);
     	
     	 implicitWait(30);
     	 
     	 sendKeys(KnrPom.getInstanceLoginXP().userName,RandomStringUtils.randomAlphanumeric(8));
			    
     	 implicitWait(30);
		 explicitWait(KnrPom.getInstanceLoginXP().userpass,30);
       	 click(KnrPom.getInstanceLoginXP().userpass);
       	 implicitWait(30);
       	 
       	 sendKeys(KnrPom.getInstanceLoginXP().userpass,RandomStringUtils.randomAlphanumeric(8));
			    
       	implicitWait(30);	
   		explicitWait(KnrPom.getInstanceLoginXP().signinBtn,30);
       	click(KnrPom.getInstanceLoginXP().signinBtn);
    	implicitWait(30);	
     	
		if (KnrPom.getInstanceLoginXP().loginFail.isDisplayed()) {
			
			System.out.println("User is unable to enter with direct username");
			implicitWait(30);	
	   		explicitWait(KnrPom.getInstanceLoginXP().alertOkBtn,30);
			KnrPom.getInstanceLoginXP().alertOkBtn.click();
			implicitWait(30);	
			KnrPom.getInstanceLoginXP().userName.clear();
			implicitWait(30);
			KnrPom.getInstanceLoginXP().userpass.clear();
			
	}}
	
	   public static void Login() throws InterruptedException, IOException, AWTException {
		   String  loginName = BrowserManager.properties.getProperty("login_name", "KNR_test").toLowerCase();
      	 if (loginName.contains("knr")){
             
      		String  username = BrowserManager.properties.getProperty(loginName + "_username");
      		String    password = BrowserManager.properties.getProperty(loginName + "_password");
           
        

          try {
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(KnrPom.getInstanceLoginXP().userName, 30);
          	BasicFunctions.click(KnrPom.getInstanceLoginXP().userName);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.sendKeys(KnrPom.getInstanceLoginXP().userName, username);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(KnrPom.getInstanceLoginXP().userpass, 30);
          	BasicFunctions.click(KnrPom.getInstanceLoginXP().userpass);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.sendKeys(KnrPom.getInstanceLoginXP().userpass, password);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(KnrPom.getInstanceLoginXP().signinBtn, 30);
          	BasicFunctions.click(KnrPom.getInstanceLoginXP().signinBtn);
          	BasicFunctions.implicitWait(30);
          	BasicFunctions.explicitWait(KnrPom.getInstanceLoginXP().loginTags, 30);
              if (KnrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
                  System.out.println("The Admin Login Page has logged in, and the landing page of KNRUHS is displayed.");
              }
          } catch (Exception e) {
          
              System.out.println("Login failed: " + e.getMessage());
          }
      	 }
	   }
	
	public void Logout() throws IOException {		
		

		implicitWait(30);	
   		explicitWait(KnrPom.getInstanceLoginXP().loginTags,30);		
		
			if (KnrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
				
				implicitWait(30);	
		   		explicitWait(KnrPom.getInstanceLoginXP().loginTags,30);
				
				click(KnrPom.getInstanceLoginXP().loginTags);
				
				implicitWait(30);	
		   		explicitWait(KnrPom.getInstanceLoginXP().signOutBtn,30);
		   		click(KnrPom.getInstanceLoginXP().signOutBtn);
			
				
				
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
//   		explicitWait(KnrPom.getInstanceLoginXP().loginTags,30);		
//		
//			if (KnrPom.getInstanceLoginXP().loginTags.isDisplayed()) {
//				
//				implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().loginTags,30);
//				
//		   		jsScroll(KnrPom.getInstanceLoginXP().reportCardOption);
//			
//				implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().reportCardOption,30);
//		   		click(KnrPom.getInstanceLoginXP().reportCardOption);
//			
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().reportCardEnroll,30);
//				
//	    		jsScroll(KnrPom.getInstanceLoginXP().reportCardEnroll);
//				
//				implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().reportCardEnroll,30);
//		   		click(KnrPom.getInstanceLoginXP().reportCardEnroll);
//		
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().enrollNo,30);
//        		click(KnrPom.getInstanceLoginXP().enrollNo);
//		   		
//		   		String str = String.valueOf(regno);
//		   		System.out.println(str); 
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().enrollNo,30);
//		   		sendKeys(KnrPom.getInstanceLoginXP().enrollNo, str);
//		   		implicitWait(30);	
//////		   		explicitWait(KnrPom.getInstanceLoginXP().submitBtn,30);
//////		   		click(KnrPom.getInstanceLoginXP().submitBtn);
////		   		
//		   		explicitWait(KnrPom.getInstanceLoginXP().examSeries,30);
//		   		click(KnrPom.getInstanceLoginXP().examSeries);
//		   		
//		   		explicitWait(KnrPom.getInstanceLoginXP().examSeries,30);
//		   		click(KnrPom.getInstanceLoginXP().examSeries);
////		   		
//		   		explicitWait(KnrPom.getInstanceLoginXP().examSeries,30);
//		   		click(KnrPom.getInstanceLoginXP().examSeries);
////		   	
////		if(KnrPom.getInstanceLoginXP().alertOk.isDisplayed()) {
////		   		
////		   		implicitWait(30);	
////		   		explicitWait(KnrPom.getInstanceLoginXP().alertOk,30);
////		   		click(KnrPom.getInstanceLoginXP().alertOk);
////			}
//
//		
//		   		
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().examSeries,30);
//    	   		click(KnrPom.getInstanceLoginXP().examSeries);
////		   		implicitWait(30);	
////		   		
////		   	//li[contains(@class,'select2-results__option')
//		   		WebElement examDateOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + examdate + "']"));
//		   		explicitWait(examDateOption,30);
//		   		
//		   		click(examDateOption);
//		    	
////		   		dropDownClick(KnrPom.getInstanceLoginXP().examSeriesOption,examdate);
//		   		
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().awardName,30);
//		   		click(KnrPom.getInstanceLoginXP().awardName);
////
////		   	//li[contains(@class,'select2-results__option') and text()!='Select']
////		   	// Construct XPath with the dynamic text
//		    	WebElement awardOption = driver.findElement(By.xpath("//li[@role='option' and text()='" + awardName + "']"));
//		    	click(awardOption);
//
////
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().yearSession,30);
//		   		click(KnrPom.getInstanceLoginXP().yearSession);
////		   		
////		   		implicitWait(30);	
////		   		
////		   	//li[contains(@class,'select2-results__option')
//		   		WebElement yearSessionOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + semester + "']"));
//		    	click(yearSessionOption);		    	
////		    	
//		    	implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().regulation,30);
//		   		click(KnrPom.getInstanceLoginXP().regulation);
//		   		implicitWait(30);	
//		    	
//		   		WebElement regulationOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + regulation + "']"));
//		    	click(regulationOption);		    	
//		    	
//	    	
//		    	implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().examType,30);
//		   		click(KnrPom.getInstanceLoginXP().examType);
//	    		implicitWait(30);	
//		    	
//		   		WebElement examTypeOption  = driver.findElement(By.xpath("//li[@role='option' and text()='" + examType + "']"));
//		    	click(examTypeOption);	
//		    	
//		    	implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().submitBtn,30);
//		   		click(KnrPom.getInstanceLoginXP().submitBtn);
//	    	
//     	   		String str = String.valueOf(regno);
//		   		System.out.println(str); 
//		   		implicitWait(30);	
//		   		explicitWait(KnrPom.getInstanceLoginXP().enrollNo,30);
//		   		sendKeys(KnrPom.getInstanceLoginXP().enrollNo, str );
//	   		
//}}
	
	
//			
	public void SignIn() throws InterruptedException {	
			//input[@id='username']

//	Actions actions = new Actions(driver);
//	actions.moveToElement(KnrPom.getInstanceLoginXP().userName).click().perform();

//
//		WebElement check= driver.findElement(By.xpath("//label[@class='form-label']"));
//		
//		check.getText();
//		System.out.println(check);
		
	//fluentWait(KnrPom.getInstanceLoginXP().userName);
//	
//	
//	
//
//	
//	
//		Thread.sleep(5000);
//    fluentWait(KnrPom.getInstanceLoginXP().userName);
//	click( KnrPom.getInstanceLoginXP().userName);
//	implicitWait( KnrPom.getInstanceLoginXP().userName, 5);
//	sendKeys(KnrPom.getInstanceLoginXP().userName, "admin");
//	
//	
		

			

//	implicitWait( KnrPom.getInstanceLoginXP().userpass, 10);
	
	}
	
	


	
	



  
}

	


    
    

