package scevtWebElement;

		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.support.FindBy;
		import org.openqa.selenium.support.PageFactory;

		public class sctevtLoginPageXPaths {
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

		@FindBy(xpath ="//span[@class='nk-menu-text' and text() ='Results']")

		public WebElement resultsBtn; 

		@FindBy(xpath ="//span[@class='nk-menu-text' and text() ='Exam History']")

		public WebElement resultsExamHistoryBtn; 


		@FindBy(id="rollno")
			
		public WebElement rollNoTextBox; 

		@FindBy(id="btnViewStudentReseltsList")

		public WebElement btnViewStudentResultsList; 

		@FindBy(xpath="//h2[@id='swal2-title' and text()='Loading Student History!']")

		public WebElement loadingStudentHistoryAlert; 


		@FindBy(xpath="//h5[@class='nk-block-title' and text()='STUDENT EXAM HISTORY DETAILS']")

		public WebElement studentHistory;

		public sctevtLoginPageXPaths(WebDriver driver) {
		    this.driver = driver;
		    PageFactory.initElements(driver, this); // Initialize elements
		}
		}

