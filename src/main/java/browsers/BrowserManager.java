package browsers;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;




import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import base.BasicFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserManager  {

    public static WebDriver driver;

        public static Properties properties = new Properties();
    
        public static void Browser_Launch() throws InterruptedException, IOException, AWTException {
            // Load the configuration file
            try {
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Get browser and environment details from properties file
            String browser = properties.getProperty("browser", "chrome").toLowerCase();
           String  loginName = properties.getProperty("login_name", "knr_test").toLowerCase();
          
            String url = urlBasedLogin(loginName);

            // Launch browser
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    System.out.println("Invalid browser selection.");
                    return;
            }

            // Maximize and open URL
            driver.manage().window().maximize();
            driver.get(url);

            // Perform login
           
           
        }

        // Method to return the appropriate URL based on login name
        private static String urlBasedLogin(String loginName) {
            switch (loginName) {
                case "knr_live":
                    return "https://knruhs.uonex.in/";
                case "knr_test":
                    return "http://103.154.253.118:81/";
                case "knr_localhost":
                    return "http://localhost:4599/";
                case "sctevt":  
               	 return "http://sctevt-qa.uonex.in/sn20Yz";
               case "sctevt2":  
              	 return "http://sctevt-qa.uonex.in/";  	 
                	 
                default:
                    System.out.println("Invalid login name, defaulting to localhost.");
                    return "Invalid";
            }
        }


        
    }
