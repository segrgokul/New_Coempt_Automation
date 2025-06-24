package knrWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KnrMasterPageXpaths {


	
	  @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Award Master']")
	    public WebElement masterBtn;
	    
	  @FindBy(xpath = "//span[@class='nk-menu-text' and text()='College Master']")
	    public WebElement CollegeMasterOption;
	  
	  @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Course Master']")
	    public WebElement CourseMasterOption;
	       
	    
	  

	    public KnrMasterPageXpaths(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	    }
	        
	    
	    }
	

