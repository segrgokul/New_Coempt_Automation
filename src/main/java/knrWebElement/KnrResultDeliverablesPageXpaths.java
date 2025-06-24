package knrWebElement;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KnrResultDeliverablesPageXpaths {
	public static WebDriver driver;

	@FindBy(xpath = "//span[@class='nk-menu-text' and contains(text(),'(Admin)')]")
	public WebElement loginTags;


    @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Result Deliverables']")
    public WebElement resultDeliverablesOption;
  

	@FindBy(xpath = "//span[@class='nk-menu-text' and text()='TRData Collegewise ']")
	 public WebElement TRDataCollegewiseOption;
		
	  @FindBy(xpath = "//span[@id='select2-groupdrop-container' or @id='select2-ddlColleges-container']")
	    public WebElement clgDropDown;  
	    
	  @FindBy(xpath = "//input[@class='select2-search__field']")
		 public WebElement clgTextBox;

		 @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']/span[@id='select2-ddlCourse-container']")
		 public WebElement ProgramCourse;

		 
	  
	  @FindBy(id = "select2-groupdrop-results")
	    public List< WebElement>clgDropDownResults;  
		 

		@FindBy(xpath = "//span[@class='nk-menu-text' and text()='TRData Collegewise ']")
		 public WebElement TRDataCollegewise ;
		
	    @FindBy(xpath = "//div[contains(@class,'dxrd-report-preview-content dxrd-position-relative')]")
	    public WebElement reportCardImg;  
	    
	    @FindBy(xpath = "//div[@class='dx-menu-item-popout']")
	    public WebElement reportCardExportTo;  

	    @FindBy(xpath = "//div[contains(@class,'dxrd-preview-export-item-text') and text()='PDF']")
	    public WebElement reportCardExportToPdf;  
		
		
			

	    public KnrResultDeliverablesPageXpaths(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); // Initialize elements
	    }}


