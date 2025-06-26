package pageObjMod;

import org.openqa.selenium.WebDriver;

import browsers.BrowserManager;
import scevtWebElement.Scte_VtResultPageXpaths;
import scevtWebElement.SctevtExamHistoryRegNoSearchPageXPaths;
import scevtWebElement.SctevtLoginPageXPaths;


public class SctevtPom extends BrowserManager {

	private static SctevtPom instance; // Singleton instance
	private static SctevtLoginPageXPaths Scte_VtLoginXP;
	private static SctevtExamHistoryRegNoSearchPageXPaths Scte_VtExamHistoryRegNoSearchXP;
	
	
	
	private static Scte_VtResultPageXpaths Scte_VtResultXp;

	   public SctevtPom(WebDriver driver) {
			// Initialize the driver when the class is instantiated
			this.driver = driver;
}
	

public static SctevtLoginPageXPaths getInstanceSctevtLoginXpaths() {

if (Scte_VtLoginXP == null) {
	Scte_VtLoginXP = new SctevtLoginPageXPaths(driver);
	return Scte_VtLoginXP;

}	
return Scte_VtLoginXP;


}


public static SctevtExamHistoryRegNoSearchPageXPaths getInstanceSctevtExamHistoryRegNoSearchPageXPaths() {

if (Scte_VtExamHistoryRegNoSearchXP == null) {
	Scte_VtExamHistoryRegNoSearchXP = new SctevtExamHistoryRegNoSearchPageXPaths(driver);
	return Scte_VtExamHistoryRegNoSearchXP;

}	
return Scte_VtExamHistoryRegNoSearchXP;


}



public static Scte_VtResultPageXpaths getInstanceSctevtResultXpaths() {

	   if (Scte_VtResultXp == null) {

		   Scte_VtResultXp = new Scte_VtResultPageXpaths(driver);
	   	return Scte_VtResultXp;

	   }	
	   return Scte_VtResultXp;


	   }
}