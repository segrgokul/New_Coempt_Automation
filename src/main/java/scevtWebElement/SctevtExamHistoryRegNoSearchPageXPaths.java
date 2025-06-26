package scevtWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SctevtExamHistoryRegNoSearchPageXPaths {
	public static WebDriver driver;
	
	@FindBy(xpath ="//span[@class='nk-menu-text' and text() ='Exam History']")

	public WebElement resultsExamHistoryBtn; 

	@FindBy(xpath ="//span[@class='nk-menu-text' and text() ='RegNo Search']")

	public WebElement regNoSearchBtn; 


	@FindBy(id="rollno")
		
	public WebElement rollNoTextBox; 

	@FindBy(id="btnViewStudentReseltsList")

	public WebElement btnViewStudentResultsList; 

	@FindBy(xpath="//h2[@id='swal2-title' and text()='Loading Student History!']")

	public WebElement loadingStudentHistoryAlert; 


	@FindBy(xpath="//h5[@class='nk-block-title' and text()='STUDENT EXAM HISTORY DETAILS']")

	public WebElement studentHistory;


public SctevtExamHistoryRegNoSearchPageXPaths(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // Initialize elements
}}