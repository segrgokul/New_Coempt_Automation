package knrPageModules;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasicFunctions;
import pageObjMod.knrPom;

public class KnrMaster  extends BasicFunctions {

	KnrDashboardPage dashBoard = new KnrDashboardPage();
	public void MasterNavigation() throws IOException {
		
		try {
			
		
		implicitWait( 30);
		explicitWait( knrPom.getInstanceCourseXP().loginTags, 30);

		if (knrPom.getInstanceCourseXP().loginTags.isDisplayed()) {

			implicitWait( 30);
			explicitWait( knrPom.getInstanceCourseXP().loginTags, 30);

		//	scroll( knrPom.getInstanceMasterXP().masterBtn);

			implicitWait( 30);
			explicitWait( knrPom.getInstanceMasterXP().masterBtn, 30);
			click(knrPom.getInstanceMasterXP().masterBtn);
			
			
			List<WebElement>  textBox = driver.findElements(By.xpath("//input[@type='text']")); 
			
			
			System.out.println("textBox.size()"+textBox.size());
			
			while(!textBox.isEmpty()){
			for (int i =0; i< textBox.size(); i++) {
				implicitWait( 30);
				textBox.get(i).click();
				
				textBox.get(i).sendKeys(RandomStringUtils.randomAlphanumeric(5));
				
				
			
				
			}
			WebElement saveBtn = driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single']"));
			implicitWait( 30);
			saveBtn.click();
			implicitWait( 30);
			
			WebElement saveOption = driver.findElement(By.xpath("(//li[@role='option' and @aria-selected ='false'])[1]"));
			
			saveOption.click();	
			implicitWait( 30);
				
			WebElement save = driver.findElement(By.id("btnSaveAwardMaster"));
			
			save.click();
			implicitWait( 30);
			
			WebElement okBtn = driver.findElement(By.xpath("//button[@TYPE='button' and text()='OK']"));
			
			implicitWait( 30);
			if(okBtn.isDisplayed()) {
				
				okBtn.click();
			}
			
			
			}
		}	
		}
		catch(Exception e){
			e.printStackTrace();
			capture(driver);
		}

	   
		}
		
	public static void CollegeMasterNavigation() {
		
		try {
			
			
		implicitWait( 30);
		explicitWait( knrPom.getInstanceMasterXP().CollegeMasterOption, 30);

		if (knrPom.getInstanceMasterXP().CollegeMasterOption.isDisplayed()) {

			implicitWait( 30);
			explicitWait( knrPom.getInstanceMasterXP().CollegeMasterOption, 30);

			

			implicitWait( 30);
			explicitWait( knrPom.getInstanceMasterXP().CollegeMasterOption, 30);
			click(knrPom.getInstanceMasterXP().CollegeMasterOption);
			System.out.println("College Master Page is navigating sucessfully");
			
		}	
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	

	public static void CollegeMasterEntriesPerPage() {
		KnrDashboardPage.EntriesPerPage();
}
	
	public static void  CollegeMasterColumn() {

		KnrDashboardPage.DashBoardColumn();
	}
	public  void CollegeMasterSearch() {
		
	
	
		dashBoard.DashBoardSearch();
		
	}
	
	public  void CollegeMasterPageNavigation() throws InterruptedException {
		dashBoard.PageNavigation(); 
		
	}
	
	
	public void CourseMasterNavigation() {
		try {
			
			
			implicitWait( 30);
			explicitWait( knrPom.getInstanceMasterXP().CourseMasterOption, 30);

			if (knrPom.getInstanceMasterXP().CourseMasterOption.isDisplayed()) {

				implicitWait( 30);
				explicitWait( knrPom.getInstanceMasterXP().CourseMasterOption, 30);

				

				implicitWait( 30);
				explicitWait( knrPom.getInstanceMasterXP().CourseMasterOption, 30);
				click(knrPom.getInstanceMasterXP().CourseMasterOption);
				System.out.println("Course master Page is navigating sucessfully");
				
			}	
			}
			catch(Exception e){
				e.printStackTrace();
			}	
	}
	
	
	public static void CourseMasterEntriesPerPage() {

		KnrDashboardPage.EntriesPerPage();
}
	
	public static void  CourseMasterColumn() {

		KnrDashboardPage.DashBoardColumn();
	}
	public  void CourseMasterSearch() {
	
		dashBoard.DashBoardSearch();
		
	}
	
	public  void CourseMasterPageNavigation() throws InterruptedException {
		dashBoard.PageNavigation(); 
		
	}
	

}
