/**
 * 
 */
package com.larryricker;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
	public void testSettingsTab() throws MalformedURLException {
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
		App.click("Enhancements");
		addStatusReport("Test Cases");
		// create a new question
		// select first status report
		App.click("Test Cases");
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

	public void addStatusReport(String statusReportName) throws MalformedURLException {
		// create a new status report
		App.click("Add");
		// statusToAdd
		App.enterText("statusToAdd", statusReportName);
		// save status
		App.click("Done");
	}

}
