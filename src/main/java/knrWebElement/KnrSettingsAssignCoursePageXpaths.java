package knrWebElement;
	import java.util.List;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class KnrSettingsAssignCoursePageXpaths {
		  public WebDriver driver;




		 @FindBy(xpath = "//span[@class='nk-menu-text' and contains(text(),'(Admin)')]")
		   public WebElement loginTags;
		   
		@FindBy(xpath = "//span[@class='nk-menu-text' and text()='Settings']")
		    public WebElement settingsOption;
		 
		 @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Assign Course']")
		    public WebElement assignCourse;
		
		 @FindBy(id = "dt-length-0")
		    public WebElement entriesDropDown;
		 
		 @FindBy(xpath = "//span[@class='dt-column-title']")
		    public List<WebElement> assignCourseBtns;
		

		 @FindBy(xpath= "//button[@class='dt-paging-button next']")
		    public WebElement nextPageNavigation;
		 
		
		 
		 
		
		 public KnrSettingsAssignCoursePageXpaths(WebDriver driver) {
			// TODO Auto-generated constructor stub
			  this.driver = driver;
		        PageFactory.initElements(driver, this);
		}	
		
	}
	
	
	

