/**
 * Copyright (c) 2017 Larry Ricker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.larryricker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * @author lawrencericker
 *
 */
public class Org {
	private static final Logger LOGGER = Logger.getLogger(Org.class.getName());

	/**
	 * Clicks on settings button at the bottom of the page
	 * @throws MalformedURLException
	 * @throws InterruptedException 
	 */
	static void gotoSettingsTableView() throws MalformedURLException, InterruptedException {
		App.click("Settings");
		App.waitForAccessibilityId("Project");
	}

	/**
	 * exit settings tab
	 * @throws MalformedURLException
	 */
	public static void exitSettingsTableView() throws MalformedURLException {
		App.click("Project");
	}

	/**
	 * get the bundle ID for the app being tested
	 * @return
	 * @throws IOException 
	 * @throws WebDriverException 
	 * @throws InterruptedException 
	 */
	public static String getBundleId(TestReporter testReporter) throws WebDriverException, IOException, InterruptedException {
		Org.gotoSettingsTableView();
		App.snapAnyway("SettingsScreen", testReporter);
		String bundleId = "";
		WebElement bi = null;
		bundleId = isThisTheBundleId(bundleId, "LR.Progress-Report");
		bundleId = isThisTheBundleId(bundleId, "LR.Progress-Report-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.PMIS2");
		bundleId = isThisTheBundleId(bundleId, "LR.PMIS-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Organizer-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Status");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Status-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Info");
		bundleId = isThisTheBundleId(bundleId, "LR.Project-Info-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Bad-With-Names");
		bundleId = isThisTheBundleId(bundleId, "LR.Bad-With-Names-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Good-With-Names");
		bundleId = isThisTheBundleId(bundleId, "LR.Good-With-Names-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.StatusReport4");
		bundleId = isThisTheBundleId(bundleId, "LR.StatusReport4-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Student-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Student-Organizer-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Job-Hunt");
		bundleId = isThisTheBundleId(bundleId, "LR.Job-Hunt-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Opportunity-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Opportunity-Organizer-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Passing-Notes");
		bundleId = isThisTheBundleId(bundleId, "LR.Passing-Notes-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Class-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Class-Organizer-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Notes-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Notes-Organizer-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Career-Search");
		bundleId = isThisTheBundleId(bundleId, "LR.Career-Search-Pro");
		bundleId = isThisTheBundleId(bundleId, "LR.Curriculum-Organizer");
		bundleId = isThisTheBundleId(bundleId, "LR.Curriculum-Organizer-Pro");
		exitSettingsTableView();
		LOGGER.log(Level.INFO, "getBundleId() - bundleId->" + bundleId);
		return bundleId;
	}

	private static String isThisTheBundleId(String bundleId, String using) throws MalformedURLException {
		LOGGER.info("bundleId bundleId-> " + bundleId + ", using->" + using);
		if ("".equals(bundleId) && App.exists(using)) {
			bundleId = using;
		}
		return bundleId;
	}

	/**
	 * Share app by email
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws WebDriverException 
	 */
	public static void shareAppFromSettingsTableView(TestReporter testReporter) throws InterruptedException, WebDriverException, IOException {
		// share app
		App.click("Share");
		App.waitForAccessibilityId("toField");
		// toField - enter text
		WebElement to = App.find("toField");
		to.clear();
		to.sendKeys("larry@larryricker.com");
		App.snapAnyway("ShareApplication", testReporter);
		// send email
		App.click("Send");
	}

	/**
	 * Creates a new project
	 * @param projectName
	 * @throws IOException 
	 * @throws WebDriverException 
	 * @throws InterruptedException 
	 */
	public static void createNewProject(String projectName, TestReporter testReporter) throws WebDriverException, IOException, InterruptedException {
		App.click("Add");

		if (App.exists("Continue")) {
			// Search
//			App.click("Search");
//			App.click("Cancel");
			// Unlimited Projects
			// Share All Projects
			// Backup All Projects
			// No Advertising
			// Continue
			App.click("Continue");
		}
		else {
			// projectNameEdit
//			App.waitForAccessibilityId("projectNameEdit");
			App.waitForScreenToLoad("projectNameEdit", 10);
			App.snapAnyway("NewProjectNameView", testReporter);			// name project
			App.enterText("projectNameEdit", projectName);
			// click on done button
			App.click("Done");
		}
	}

	/**
	 * verify settings upsell message
	 * @throws MalformedURLException
	 */
	public static void verifySettingsUpsellMessage() throws MalformedURLException {
		verifyUpsellMessage("Buy PMIS Pro 4.99");
		verifyUpsellMessage("Buy Progress Report Pro 4.99");
		verifyUpsellMessage("Buy Project Organizer Pro 4.99");
		verifyUpsellMessage("Buy Project Status Pro 4.99");
		verifyUpsellMessage("Buy StatusReport4 Pro 3.99");
		verifyUpsellMessage("Buy Project Info Pro 4.99");
		verifyUpsellMessage("Buy Bad With Names Pro 0.99");
		verifyUpsellMessage("Buy Good With Names Pro 0.99");
		verifyUpsellMessage("Buy Student Organizer Pro 0.99");
		verifyUpsellMessage("Buy Passing Notes Pro 4.99");
		verifyUpsellMessage("Buy Job Hunt Pro 1.99");
		verifyUpsellMessage("Buy Opportunity Organizer Pro 2.99");
		verifyUpsellMessage("Buy Class Organizer Pro 0.99");
	}

	private static void verifyUpsellMessage(String using) throws MalformedURLException {
		WebElement upsell;
		upsell = App.find(using);
		if (upsell != null) {
			Assertions.assertEquals(upsell.getText(), using, "Upsell message is correct");
		}
	}

	/**
	 * add status report
	 * @param statusReportName
	 * @throws MalformedURLException
	 */
	public static void addStatusReport(String statusReportName) throws MalformedURLException {
		// create a new status report
		App.click("Add");
		// statusToAdd
		App.enterText("statusToAdd", statusReportName);
		// save status
		App.click("Done");
	}

	/**
	 * add test case
	 * @param statusReportTitle
	 * @param answer
	 * @throws MalformedURLException
	 */
	public static void addTestCase(String statusReportTitle, String answer) throws MalformedURLException {
		App.click("Add");
		App.enterText("statusReportTitle", statusReportTitle);
		App.enterText("questionTextField", "What is the test case?");
		App.focus("answerTextField");
		App.enterText("answerTextField", answer);
		App.click("Done");
	}

	public static boolean isProVersion(String bundleId) {
		return bundleId.equals("LR.Progress-Report-Pro")
				|| bundleId.equals("LR.StatusReport4-Pro")
				|| bundleId.equals("LR.Project-Organizer-Pro")
				|| bundleId.equals("LR.Project-Info-Pro")
				|| bundleId.equals("LR.PMIS-Pro")
				|| bundleId.equals("LR.Good-With-Names-Pro")
				|| bundleId.equals("LR.Bad-With-Names-Pro");
	}

}
