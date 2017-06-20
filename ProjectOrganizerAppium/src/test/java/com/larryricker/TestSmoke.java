/**
 * 
 */
package com.larryricker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.WebDriverException;

/**
 * @author lawrencericker
 *
 */
@Tag("ios")
@Tag("smoke")
public class TestSmoke {

	private static final Logger LOGGER = Logger.getLogger(TestSettingsTab.class.getName());

	@BeforeAll
	public static void setupBeforeClass() {

	}

	@AfterAll
	public static void tearDownAfterClass() {

	}

	@BeforeEach
	public void setup() throws MalformedURLException {
		Org.gotoSettingsTableView();
	}

	@AfterEach
	public void cleanup() {

	}

	@Test
	public void testSettingsTab(TestReporter testReporter) throws WebDriverException, IOException, InterruptedException {
		// identify current app
		String bundleId = Org.getBundleId();
		// create a new project
		Org.createNewProject("Enhancements");
		Org.createNewProject("Uplift");
		Org.createNewProject("Reskin");
		Org.createNewProject("Search Optimization");
		if (bundleId.equals("LR.Progress-Report-Pro")) {
			Org.createNewProject("Metrics");
			Org.createNewProject("Defects");
			Org.createNewProject("Human Factors");
			Org.createNewProject("Routing");
			Org.createNewProject("Automated Testing");
			Org.createNewProject("Biometrics");
			Org.createNewProject("Content");
			Org.createNewProject("Routing");
			Org.createNewProject("Desktop");
			Org.createNewProject("Upgrade");
			Org.createNewProject("Integration");
		}
		// select first project
		App.waitForAccessibilityId("projectCell");
//		App.waitForScreenToLoad("projectCell", 30);
//		Thread.sleep(3000);
		App.snapAnyway("ProjectSelectionTableView", testReporter);
		App.click("projectCell");
		Org.addStatusReport("Test Cases");
		// create a new question
		// select first status report
		App.click("Test Cases");
		Org.addTestCase("Branchio", "Implement safari API on mobile device to\nhit branchio link and have it hit the\nuniversal link and redirect to the device.");
		Org.addTestCase("Install Backup", "Open backup file off Amazon AFS website\nadds in S3 to download and install on\ndevice.");
		Org.addTestCase("Create Backup On All Three Levels", "Send emails.");
		Org.addTestCase("Send Memo With Minutes Of the Meeting", "Send email.");
		Org.addTestCase("Orientation","Rotate orientation on each screen and\nverify the fields and buttons do not shift off\nthe screen.  It looks good, symmetrically\naligned and is functional.");
		Org.addTestCase("Localization","Switch app to Spain, Mexico\nSwithc app to England, Australia, Canada\nSwitch app to Germany\nSwitch app to France\nSwitch app to Italy.");
		Org.addTestCase("Reorder","Reorder projects\nReorder statuses\nReorder questions\nLeave View and return and verify order\npersists.");
		Org.addTestCase("Upgrade Application - Destructive", "Purchase upgrade app on settings tab\nPurchase upgraded app when exceed\nnumber of allowed projects\nPurchase app when on new project or\nrename project tab\nPurchase app when on new status report or rename\nstatus report tab\nThese tests will need the compiled app as an IPA file\nand have the app reinstalled after each test.");
		Org.addTestCase("Create New Status Report","Create new status report call test cases.\nAdd test cases to progress report.");
		Org.addTestCase("Aborted purchase","After purchase app\nCancel purchase\nShould return to free version not stay in paid version.");
		// reorder questions
		// exit questions
		// reorder status
		// exit status
		// reorder project
		// search for a question
		// drill down search to status
		// drill down search to question
		// delete a question
		// exit question
		// delete status
		// exit status
		// delete project

	}

}
