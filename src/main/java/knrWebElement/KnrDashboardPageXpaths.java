package knrWebElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KnrDashboardPageXpaths {


    @FindBy(xpath = "//select[@id='dt-length-1' or @id='dt-length-0']")
    public WebElement entriesDropDown;

    @FindBy(xpath = "//select[@id='dt-length-1' or @id='dt-length-0']/option")
    public List<WebElement> entriesDropDownOption;



    @FindBy(xpath = "//td[contains(@class,'sorting_1')]")
    public List<WebElement> codeNos;

   

    
    @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Dashboard']")
    public WebElement dashboardOption;
    
    @FindBy(xpath = "//a[text()='Terms & Conditions']")
    public WebElement termsAndConditions;
    
  
    
    @FindBy(xpath = "//span[@class='dt-column-title']")
    public List<WebElement> dashboardBtns;
    
    @FindBy(xpath = "//input[@id='dt-search-1' or @id='dt-search-0']")
    public WebElement dashboardSearch;
    
    @FindBy(xpath= "//button[@class='dt-paging-button next']")
    public WebElement nextPageNavigation;

    @FindBy(xpath= "//button[@class='dt-paging-button last']")
    public WebElement lastPageNavigation;
    
    @FindBy(xpath= "//button[@class='dt-paging-button previous']")
    public WebElement previousPageNavigation;

    @FindBy(xpath= "//button[@class='dt-paging-button first']")
    public WebElement firstPageNavigation;
    
    
    public KnrDashboardPageXpaths(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
        
    
    }
