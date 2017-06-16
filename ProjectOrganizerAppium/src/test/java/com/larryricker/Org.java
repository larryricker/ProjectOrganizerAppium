/**
 * 
 */
package com.larryricker;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

/**
 * @author lawrencericker
 *
 */
public class Org {

	/**
	 * Clicks on settings button at the bottom of the page
	 * @throws MalformedURLException
	 */
	static void gotoSettingsTableView() throws MalformedURLException {
		App.click("Settings");
	}

	public static void exitSettingsTableView() throws MalformedURLException {
		App.click("Project");
	}

	public static String getBundleId() throws MalformedURLException {
		String bundleId = "";
		WebElement bi = null;
		if (Driver.getDriver().findElementByAccessibilityId("LR.Progress-Report") != null) {
			bundleId = "LR.Progress-Report";
		}
		exitSettingsTableView();
		return bundleId;
	}

	public static void shareAppFromSettingsTableView() throws MalformedURLException {
		// share app
		App.click("Share");
		// toField - enter text
		WebElement to = App.find("toField");
		to.sendKeys("larry@larryricker.com");
		// send email
		App.click("Send");
	}

	public static void createNewProject(String projectName) throws MalformedURLException {
		App.click("Add");
		// name project
		WebElement projectNameEdit = App.find("projectNameEdit");
		projectNameEdit.sendKeys(projectName);
		// click on done button
		App.click("Done");
	}

}
