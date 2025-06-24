package pageObjMod;

	import org.openqa.selenium.WebDriver;

import browsers.BrowserManager;

import knrWebElement.KnrAttendanceTheroryXpaths;
import knrWebElement.KnrDashboardPageXpaths;
import knrWebElement.KnrLoginPageXPaths;
import knrWebElement.KnrMasterPageXpaths;
import knrWebElement.KnrReportCoursePageXpaths;
import knrWebElement.KnrReportEnrollmentPageXpaths;
import knrWebElement.KnrResultDeliverablesPageXpaths;
import knrWebElement.KnrResultTRDataXpaths;
import knrWebElement.KnrSettingsAssignCoursePageXpaths;




	public class knrPom  extends BrowserManager{

				private static knrPom instance; // Singleton instance
				public static KnrLoginPageXPaths loginXP ;
		
				private static KnrReportEnrollmentPageXpaths EnrollmentXP;

	
				private static KnrReportCoursePageXpaths CourseXP;
				private static KnrResultTRDataXpaths TRDataXP;
				private static KnrSettingsAssignCoursePageXpaths AssignCourseXP;
				private static KnrAttendanceTheroryXpaths TheroryXP;
				private static KnrDashboardPageXpaths DashboardXP;
				private static KnrMasterPageXpaths MasterXP;
				
				private static KnrResultDeliverablesPageXpaths ResultDeliverablesXP;
				// Use a different name for the static instance
		
				// Constructor to initialize the WebDriver when the class is instantiated
   public knrPom(WebDriver driver) {
						// Initialize the driver when the class is instantiated
						this.driver = driver;
		    }

	    // Method to get the instance of LoginPageXPath
	    public static KnrLoginPageXPaths getInstanceLoginXP() {
	        // If the instance is null, create a new instance
	    	if (loginXP == null) {
	        	loginXP = new KnrLoginPageXPaths(driver);
	        	return loginXP;
	        }
	        
	        return loginXP; // Return the instance
	    }
	    


		
		public static KnrReportEnrollmentPageXpaths getInstanceEnrollXP() {
		
					if (EnrollmentXP == null) {
						EnrollmentXP = new KnrReportEnrollmentPageXpaths(driver);
						return EnrollmentXP;

					}	
			return EnrollmentXP;
		}


public static KnrDashboardPageXpaths getInstanceDashboardXP() {
    // If the instance is null, create a new instance
	if (DashboardXP == null) {
		DashboardXP = new KnrDashboardPageXpaths(driver);
    	return DashboardXP;
    }
    
    return DashboardXP; // Return the instance
}


public static KnrMasterPageXpaths getInstanceMasterXP() {
    // If the instance is null, create a new instance
	if (MasterXP == null) {
		MasterXP = new KnrMasterPageXpaths(driver);
    	return MasterXP;
    }
    
    return MasterXP; // Return the instance
}

public static KnrAttendanceTheroryXpaths getInstanceTheroryXP() {
	
	if (TheroryXP == null) {
		TheroryXP = new KnrAttendanceTheroryXpaths(driver);
		return TheroryXP;
	}	
return TheroryXP;
}


public static KnrReportCoursePageXpaths getInstanceCourseXP() {

	if (CourseXP == null) {
		CourseXP = new KnrReportCoursePageXpaths(driver);
		return CourseXP;
	}	
return CourseXP;
}


public static KnrResultDeliverablesPageXpaths getInstanceResultDeliverablesCP() {
	
	if (ResultDeliverablesXP == null) {
		ResultDeliverablesXP = new KnrResultDeliverablesPageXpaths(driver);
		return ResultDeliverablesXP;
	}	
return ResultDeliverablesXP;
}


public static KnrResultTRDataXpaths getInstanceTRDataXP() {
	
	if (TRDataXP == null) {
		TRDataXP = new KnrResultTRDataXpaths(driver);
		return TRDataXP;

	}	
return TRDataXP;
}

public static KnrSettingsAssignCoursePageXpaths getInstanceAssignCourseXP() {
	
	if (AssignCourseXP == null) {
		AssignCourseXP = new KnrSettingsAssignCoursePageXpaths(driver);
		return AssignCourseXP;

	}	
return AssignCourseXP;


}	

	
	
	}
	
