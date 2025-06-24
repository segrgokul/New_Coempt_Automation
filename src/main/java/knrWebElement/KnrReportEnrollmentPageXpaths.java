package knrWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KnrReportEnrollmentPageXpaths {
public static WebDriver driver;

@FindBy(xpath = "//span[@class='nk-menu-text' and contains(text(),'(Admin)')]")
public WebElement loginTags;

    @FindBy(xpath = "//span[@class='nk-menu-text' and text()='Report Card']")
    public WebElement reportCardOption;
  
    @FindBy(xpath = "(//span[@class='nk-menu-text' and text()='Enrollment-wise'])[2]")
    public WebElement reportCardEnroll;   
    
    @FindBy(id = "EnrollmentNo")
    public WebElement enrollNo;  
    
    @FindBy(id = "select2-ExamSeriesSelection-container")
    public WebElement examSeries;  
  
    @FindBy(xpath ="//li[contains(@class,'select2-results__option') and text()!='Select']")
    public WebElement examSeriesOption;
  
    @FindBy(id = "select2-selSchemaMain-container")
    public WebElement awardName;  
  
    @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']/span[@id='select2-SemSelection-container' or @id='select2-ddlSem-container']")
    public WebElement yearSession; 
    
    @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']/span[@id='select2-ddlRegulation-container']")
    public WebElement regulation;

    @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']/span[@id='select2-ddlExamType-container']")
    public WebElement examType;
    

    @FindBy(xpath = "    //h2[@id='swal2-title' and text()='Loading the Report Card...']")
    public WebElement loadingScreen;

    


    
    @FindBy(id = "btnSUB")
    public WebElement submitBtn;  
    
    
    @FindBy(xpath = "//div[@class='swal2-actions']/button[@class='swal2-confirm swal2-styled']")
    public WebElement okBtn;    


    @FindBy(xpath="//h2[@class='swal2-title' and contains(text(),'All fields are mandatory!')]")
    public WebElement allFieldsMandatoryAlert;    

   @FindBy(xpath="//h2[@class='swal2-title' and contains(text(),'No Records Found')]")
    public WebElement noRecordsFoundAlert;   
    

    @FindBy(xpath = "//div[contains(@class,'dxrd-report-preview-content dxrd-position-relative')]")
    public WebElement reportCardImg;  
    
    @FindBy(xpath = "//li[@class='dx-menu-item-wrapper']")
    public WebElement reportCardExportTo;  

    @FindBy(xpath = "//div[contains(@class,'dxrd-preview-export-item-text') and text()='PDF']")
    public WebElement reportCardExportToPdf;  
  
    
  
    
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    public WebElement alertOk ;

    
	   

    public KnrReportEnrollmentPageXpaths(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements
    }}
