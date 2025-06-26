package scevtWebElement;

		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.support.FindBy;
		import org.openqa.selenium.support.PageFactory;

		public class SctevtLoginPageXPaths {
			public static WebDriver driver;
			
			

		@FindBy(id="username")
			
		public WebElement userName; 


		@FindBy(id="password")
			
		public WebElement passWord; 


		@FindBy(id="btnLogin")
			
		public WebElement btnLogin; 

		@FindBy(xpath ="//button[@class='swal2-confirm swal2-styled' and text()='OK']")

		public WebElement okBtn;

		@FindBy(xpath ="//div[@class='user-status user-status-active']")

		public WebElement userIconArea; 



		public SctevtLoginPageXPaths(WebDriver driver) {
		    this.driver = driver;
		    PageFactory.initElements(driver, this); // Initialize elements
		}
		}

