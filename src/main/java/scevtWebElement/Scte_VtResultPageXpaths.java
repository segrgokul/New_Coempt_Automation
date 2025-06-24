package scevtWebElement;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Scte_VtResultPageXpaths {
public static WebDriver driver;




@FindBy(xpath = "//div/input[@id='rollno']")
public WebElement rollNoTB;


@FindBy(xpath = "//input[@id='dob']")
public WebElement dobTB;

@FindBy(xpath = "(//tr/th[@class='prev'])[3]")
public WebElement dobPrev;


@FindBy(xpath = "//tr/td[@colspan='7']/span[@class='year' and text()='1990']")
public WebElement dobPrevYear;

@FindBy(xpath = "//tr/td[@colspan='7']/span[@class='month' and text()='Jan']")
public WebElement dobPrevMonth;

@FindBy(xpath = "//tr/td[@class='day' and text()='15']")
public WebElement dobPrevDate;


@FindBy(id = "btnViewStudentReselts")
  public WebElement btnViewStudentResults;  


@FindBy(xpath="//button[@type='button' and text ()='OK']")
	
public WebElement btnAlertOk; 

@FindBy(xpath="//tr/td[contains(text(),'1st')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")

public WebElement btn1stSemResult; 

@FindBy(xpath="//tr/td[contains(text(),'2nd')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")

public WebElement btn2ndSemResult; 

@FindBy(xpath="//tr/td[contains(text(),'3rd')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")

public WebElement btn3rdSemResult; 

@FindBy(xpath ="//tr/td[contains(text(),'4th')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")

public WebElement btn4thSemResult; 

@FindBy(xpath="//tr/td[contains(text(),'5th')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")

public WebElement btn5thSemResult; 

@FindBy(xpath="//tr/td[contains(text(),'6th')]/following-sibling::td//a[@class='btn btn-sm btn-primary' and text()='View Result']")
public WebElement btn6thSemResult; 



public Scte_VtResultPageXpaths(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // Initialize elements
}}
