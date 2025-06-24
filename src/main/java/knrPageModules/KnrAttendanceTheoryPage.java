package knrPageModules;

import org.openqa.selenium.interactions.Actions;

import base.BasicFunctions;
import pageObjMod.knrPom;

public class KnrAttendanceTheoryPage  extends BasicFunctions {

		public void AttendanceMarkNavigation() {
			

			
			implicitWait( 30);
			explicitWait(knrPom.getInstanceEnrollXP().loginTags, 30);

			if (knrPom.getInstanceEnrollXP().loginTags.isDisplayed()) {

				implicitWait( 30);
				explicitWait( knrPom.getInstanceEnrollXP().loginTags, 30);

				scrollTillWebElement( knrPom.getInstanceTheroryXP().attendanceMark);

				implicitWait( 30);
				explicitWait( knrPom.getInstanceTheroryXP().attendanceMark, 30);
				click(  knrPom.getInstanceTheroryXP().attendanceMark);
			}
		}
		
		
		public void TheroryAttendanceNavigation() {
			
			implicitWait( 30);
			explicitWait(knrPom.getInstanceTheroryXP().attendanceMark, 30);

			if (knrPom.getInstanceTheroryXP().attendanceMark.isDisplayed()) {

				implicitWait( 30);
				explicitWait( knrPom.getInstanceTheroryXP().theoryAttendanceOption, 30);

				scrollTillWebElement(knrPom.getInstanceTheroryXP().theoryAttendanceOption);

				implicitWait( 30);
				explicitWait(knrPom.getInstanceTheroryXP().theoryAttendanceOption, 30);
				click(knrPom.getInstanceTheroryXP().theoryAttendanceOption);
			}
	}

		public void TheroryAttendanceBrowse() {
			Actions action = new Actions(driver);
			implicitWait( 30);
		//	explicitWait(knrPom.getInstanceTheroryXP().uplodadFiles, 30);

		//	if (knrPom.getInstanceTheroryXP().uplodadFiles.get(0).isDisplayed()) {

				implicitWait( 30);
		//		explicitWait( knrPom.getInstanceTheroryXP().uplodadFiles, 30);

		//		jsscrollTillWebElementTillWebElementTillWebElement(knrPom.getInstanceTheroryXP().uplodadFiles);

				implicitWait( 30);
		//		explicitWait(knrPom.getInstanceTheroryXP().uplodadFiles, 30);
				
		action.moveToElement(knrPom.getInstanceTheroryXP().uplodadFiles).click().perform();
					
			}

		}
