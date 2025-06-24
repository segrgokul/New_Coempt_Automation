package pageObjMod;

import org.openqa.selenium.WebDriver;

import browsers.BrowserManager;
import scevtWebElement.Scte_VtResultPageXpaths;
import scevtWebElement.sctevtLoginPageXPaths;

public class sctevtPom extends BrowserManager {

	private static sctevtPom instance; // Singleton instance
	private static sctevtLoginPageXPaths Scte_VtLoginXP;
	private static Scte_VtResultPageXpaths Scte_VtResultXp;

	   public sctevtPom(WebDriver driver) {
			// Initialize the driver when the class is instantiated
			this.driver = driver;
}
	

public static sctevtLoginPageXPaths getInstanceSctevtLoginXpaths() {

if (Scte_VtLoginXP == null) {
	Scte_VtLoginXP = new sctevtLoginPageXPaths(driver);
	return Scte_VtLoginXP;

}	
return Scte_VtLoginXP;


}

public static Scte_VtResultPageXpaths getInstanceSctevtResultXpaths() {

	   if (Scte_VtResultXp == null) {

		   Scte_VtResultXp = new Scte_VtResultPageXpaths(driver);
	   	return Scte_VtResultXp;

	   }	
	   System.out.println(driver);
	   return Scte_VtResultXp;


	   }
}