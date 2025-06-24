package knrWebElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasicFunctions;

public class KnrLoginPageXPaths extends BasicFunctions {

    @FindBy(xpath = "//input[@id='username']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement userpass;

    @FindBy(xpath = "//button[@id='btnLogin']")
    public WebElement signinBtn;
    
    @FindBy(xpath = "//h2[@class='swal2-title' and text()='Invalid Username!']")
    public WebElement invaidUserName;    
  
    @FindBy(xpath = "//h2[@class='swal2-title' and text()='Invalid Password!']")
    public WebElement invaidPassword;    
    
    @FindBy(xpath = "//h2[@class='swal2-title' and text()='Login Failed']")
    public WebElement loginFail;    
    
    @FindBy(xpath = "//button[@type='button' and text()='OK']")
    public WebElement alertOkBtn;    

    
    
    
    
    @FindBy(xpath = "//span[@class='nk-menu-text' and contains(text(),'(Admin)')]")
    public WebElement loginTags;
   
    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
	public WebElement signOutBtn;

    @FindBy(xpath = "//span[contains(text(),'Dashboard')]")
    public WebElement dashboard;

    @FindBy(xpath = "//tr[@role='row']//span[@class='dt-column-title']")
    public List<WebElement> dashboardResultOptions;

    @FindBy(xpath = "//li[@class='nk-menu-item has-sub current-page' or @class='nk-menu-item has-sub current-page active']")
    public WebElement masterButton;

    @FindBy(xpath = "(//ul[@class='nk-menu-sub'])[1]//span")
    public List<WebElement> subModuleList;

    @FindBy(xpath = "//span[text()='Master']")
    public WebElement master;

    @FindBy(xpath = "//tr[@role='row']//span[@class='dt-column-title']")
    public List<WebElement> masterResultOptions;
    
    @FindBy(xpath = "//a[@id='btnAddNew']/span")
    public WebElement addNewButton;

    @FindBy(xpath = "//input[@id='txtClgCode']")
    public WebElement clgCodeInput;

    @FindBy(xpath = "//input[@id='txtClgName']")
    public WebElement clgNameInput;

    @FindBy(id = "txtPrncplName")
    public WebElement principleNameInput;

    @FindBy(id = "txtOfcTelNo")
    public WebElement phoneNoInput;

    @FindBy(id = "txtMobileNo")
    public WebElement mobileNoInput;

    @FindBy(id = "txtEmail")
    public WebElement emailInput;

    @FindBy(id = "txtexamincharge")
    public WebElement examInchargeInput;

    @FindBy(id = "txtColAdd")
    public WebElement clgAddressInput;

    @FindBy(xpath = "//span[contains(@class,'select2-selection') and @aria-expanded='true' or @aria-expanded='false']")
    public WebElement selectDropdown;
    
       
    
  

    public KnrLoginPageXPaths(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
        
    
    }