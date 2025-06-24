package knrWebElement;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KnrResultTRDataXpaths {
public static WebDriver driver;




@FindBy(xpath = "//span[@class='nk-menu-text' and text()='Result Deliverables']")
public WebElement resultDeliverables;

@FindBy(xpath = "//span[@class='nk-menu-text' and text()='TRData Collegewise ']")
public WebElement trDataClgWise;


@FindBy(id = "select2-ddlColleges-container")
  public WebElement clgDropDown;  
  

public KnrResultTRDataXpaths(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // Initialize elements
}}
