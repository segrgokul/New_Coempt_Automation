package knrPageModules;

import org.openqa.selenium.interactions.Actions;

import base.BasicFunctions;
import pageObjMod.KnrPom;

public class KnrAttendanceTheoryPage  extends BasicFunctions {

		public void AttendanceMarkNavigation() {
			

			
			implicitWait( 30);
			explicitWait(KnrPom.getInstanceEnrollXP().loginTags, 30);

			if (KnrPom.getInstanceEnrollXP().loginTags.isDisplayed()) {

				implicitWait( 30);
				explicitWait( KnrPom.getInstanceEnrollXP().loginTags, 30);

				scrollTillWebElement( KnrPom.getInstanceTheroryXP().attendanceMark);

				implicitWait( 30);
				explicitWait( KnrPom.getInstanceTheroryXP().attendanceMark, 30);
				click(  KnrPom.getInstanceTheroryXP().attendanceMark);
			}
		}
		
		
		public void TheroryAttendanceNavigation() {
			
			implicitWait( 30);
			explicitWait(KnrPom.getInstanceTheroryXP().attendanceMark, 30);

			if (KnrPom.getInstanceTheroryXP().attendanceMark.isDisplayed()) {

				implicitWait( 30);
				explicitWait( KnrPom.getInstanceTheroryXP().theoryAttendanceOption, 30);

				scrollTillWebElement(KnrPom.getInstanceTheroryXP().theoryAttendanceOption);

				implicitWait( 30);
				explicitWait(KnrPom.getInstanceTheroryXP().theoryAttendanceOption, 30);
				click(KnrPom.getInstanceTheroryXP().theoryAttendanceOption);
			}
	}

		public void TheroryAttendanceBrowse() {
			Actions action = new Actions(driver);
			implicitWait( 30);
		//	explicitWait(KnrPom.getInstanceTheroryXP().uplodadFiles, 30);

		//	if (KnrPom.getInstanceTheroryXP().uplodadFiles.get(0).isDisplayed()) {

				implicitWait( 30);
		//		explicitWait( KnrPom.getInstanceTheroryXP().uplodadFiles, 30);

		//		jsscrollTillWebElementTillWebElementTillWebElement(KnrPom.getInstanceTheroryXP().uplodadFiles);

				implicitWait( 30);
		//		explicitWait(KnrPom.getInstanceTheroryXP().uplodadFiles, 30);
				
		action.moveToElement(KnrPom.getInstanceTheroryXP().uplodadFiles).click().perform();
					
			}

		}
