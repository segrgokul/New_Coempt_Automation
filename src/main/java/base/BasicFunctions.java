package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browsers.BrowserManager;


public class BasicFunctions extends BrowserManager {
	  public static ExtentReports extentReport;
	  public static ExtentSparkReporter sparkReporter;
	
	

	//i wait until timeout sec but i will loop it for every seconds i menioned in durations of seconds
	public static WebElement fluentWait(final WebElement ele,int timeoutInSec) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeoutInSec))
				.pollingEvery(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

		WebElement elementWaitedFor = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return ele;
			}
		});
		return elementWaitedFor;
	}
	
	//i wait until element gets display,clickable,visibe
	public static WebElement explicitWait(final WebElement ele, int timeoutInSec) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec));
	    WebElement elementWaitedFor = wait.until(new ExpectedCondition<WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return ele.isDisplayed() ? ele : null;
	        }
	    });
	    return elementWaitedFor;
	}
	
	//i wait until the timeout sec
	public static void implicitWait( int timeoutInSec) {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSec));
	}
	
	
	    public static void click(WebElement element) {
	    	

	        element.click();
	    }

		
	
	
	    public static void dropDownClick(WebDriver driver, WebElement element,String value) {
	    	
	    	 element.click();
	    }
	    
	public void clear(WebElement ele) {

	
		    ele.clear();
		
	}


	  public static void selectByVisibleText(WebElement dropdownElement, String visibleText) {
	        Select select = new Select(dropdownElement);
	        select.selectByValue(visibleText);
	    }

		public static void selectByIndex(WebElement dropdownElement, int index) {
			Select dropdown = new Select(dropdownElement);
			dropdown.selectByIndex(index);
		}
	  
	public static void sendKeys(final WebElement ele, Object value) {
	    // Convert the value to a String
	    String valueStr = null;
	    
	    if (value instanceof Integer) {
	        valueStr = String.valueOf(value);  // Convert int to String
	    } else if (value instanceof String) {
	        valueStr = (String) value;  // Use the String value directly
	    }

	    if (valueStr != null && !valueStr.trim().isEmpty()) {
	        // Trim any leading or trailing spaces
	        valueStr = valueStr.trim();

	        // Wait for the element to be visible and interactable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(ele));

	        // Clear the field before entering the value, if needed
	        ele.clear();

	        // Send the value to the element
	        ele.sendKeys(valueStr);
	    } else {
	        System.out.println("Invalid value provided: " + value);
	    }
	}


	public static void scrollTillWebElement(WebElement ele) {


		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	public void scrollTillEnd(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void explicityWait(WebElement ele, int second) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(second));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

    public static String capture(WebDriver driver) throws IOException {
        // Capture screenshot and store it in a file
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination path for the screenshot
        String destPath = System.getProperty("user.dir") + "/src/test/resources/Screen_Shorts/screenshot_" + System.currentTimeMillis() + ".png";
        File dest = new File(destPath);

        // Copy the screenshot to the destination
        try {
            FileUtils.copyFile(scrFile, dest);
        } catch (IOException e) {
            // Handle the exception (e.g., log the error, rethrow the exception)
            throw new IOException("Failed to save screenshot to " + destPath, e);
        }

        
        
        // Return the absolute path of the screenshot file
        return dest.getAbsolutePath();
    }

	
	public String incrementPhoneNumber(String phoneNumber) {
	        long number = Long.parseLong(phoneNumber) + 1;
	        return String.valueOf(number);
	    }
	  public boolean isElementPresent(WebDriver driver, WebElement ele) {
	        try {
	        	
	        	return ele != null;
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            // Return false if element is not found
	            return false;
	        }
	    }


}