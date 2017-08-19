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
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
	public void setup(String capabilities, TestInfo info, TestReporter testReporter) throws MalformedURLException {
	}

	@AfterEach
	public void cleanup(String capabilities, TestInfo info, TestReporter testReporter) throws WebDriverException, IOException {
		App.snapAnyway(capabilities.replaceAll(".json", "") + "ExitSmoke", testReporter);
		Driver.destroy();
	}

	@ParameterizedTest
	@ValueSource(strings= {
//		"BadWithNames6p.json"
//		, "BadWithNamesPro6p.json", "GoodWithNames6p.json"
//		,"GoodWithNamesPro6p.json", "PMIS6p.json"
//		, "PMIS-Pro6p.json", "ProgressReport6p.json"
//		, "ProgressReportPro6p.json", "ProjectInfo6p.json"
//		, "ProjectInfoPro6p.json", "StatusReport46p.json"
//		, "StatusReport4Pro6p.json"// , "ProjectStatus6p.json"
		// , "ProjectStatusPro6p.json"
//		, 
		"BadWithNames.json", "BadWithNamesPro.json"
		, "GoodWithNames.json"
		, "GoodWithNamesPro.json", "PMIS-Pro.json"
		, "PMIS.json", "ProgressReport.json"
		, "ProgressReportPro.json", "ProjectInfo.json"
		, "ProjectInfoPro.json"
		, "ProjectOrganizer.json", "ProjectOrganizerPro.json"
		, "StatusReport4.json"
		, "StatusReport4Pro.json", "ProjectStatus.json"
		, "ProjectStatusPro.json"
		, "BadWithNamesiPad.json"
		, "BadWithNamesProiPad.json"
		, "GoodWithNamesiPad.json"
		,"GoodWithNamesProiPad.json"
		, "PMIS-ProiPad.json"
		, "PMISiPad.json"
		, "ProgressReportiPad.json"
		, "ProgressReportProiPad.json"
		, "ProjectInfoiPad.json"
		, "ProjectInfoProiPad.json"
		, "ProjectOrganizeriPad.json"
		, "ProjectOrganizerProiPad.json"
		, "StatusReport4iPad.json"
		, "StatusReport4ProiPad.json"
		, "ProjectStatusiPad.json"
		, "ProjectStatusProiPad.json"
	})
	@DisplayName("Smoke Test")
	public void testSmoke(String capabilities, TestInfo info, TestReporter testReporter) throws WebDriverException, IOException, InterruptedException {
		Assert.assertNotNull("capabilities is not null", capabilities);
		System.setProperty("TEST_CAPABILITIES", capabilities);
		// identify current app
		String bundleId = Org.getBundleId(testReporter);
		// create a new project
		Org.createNewProject("Enhancements", testReporter);
		Org.createNewProject("Uplift", testReporter);
		Org.createNewProject("Reskin", testReporter);
		Org.createNewProject("Search Optimization", testReporter);
		if (Org.isProVersion(bundleId)) {
			Org.createNewProject("Metrics", testReporter);
//			Org.createNewProject("Defects", testReporter);
//			Org.createNewProject("Human Factors", testReporter);
//			Org.createNewProject("Routing", testReporter);
//			Org.createNewProject("Automated Testing", testReporter);
//			Org.createNewProject("Biometrics", testReporter);
//			Org.createNewProject("Content", testReporter);
//			Org.createNewProject("Routing", testReporter);
//			Org.createNewProject("Desktop", testReporter);
			Org.createNewProject("Upgrade", testReporter);
			Org.createNewProject("Integration", testReporter);
		}
//		App.waitForAccessibilityId("projectCell");
//		App.waitForScreenToLoad("projectCell", 30);
//		Thread.sleep(3000);
		App.snapAnyway("ProjectSelectionTableView", testReporter);
		// select first project
		if (App.exists("Enhancements")) {
			App.click("Enhancements");
			
		}
		else if (App.exists("projectCell")) {
			App.click("projectCell");
			
		}
		Org.addStatusReport("Test Cases", testReporter);
		// create a new question
		// select first status report
		App.click("Test Cases");
		Org.addTestCase("Branchio", "Implement safari API on mobile device to\nhit branchio link and have it hit the\nuniversal link and redirect to the device.");
		Org.addTestCase("Install Backup", "Open backup file off Amazon AFS website\nadds in S3 to download and install on\ndevice.");
		Org.addTestCase("Create Backup On All Three Levels", "Send emails.");
//		Org.addTestCase("Send Memo With Minutes Of the Meeting", "Send email.");
//		Org.addTestCase("Orientation","Rotate orientation on each screen and\nverify the fields and buttons do not shift off\nthe screen.  It looks good, symmetrically\naligned and is functional.");
		Org.addTestCase("Localization","Switch app to Spain, Mexico\nSwitch app to England, Australia, Canada\nSwitch app to Germany\nSwitch app to France\nSwitch app to Italy.");
//		Org.addTestCase("Reorder","Reorder projects\nReorder statuses\nReorder questions\nLeave View and return and verify order\npersists.");
//		Org.addTestCase("Upgrade Application - Destructive", "Purchase upgrade app on settings tab\nPurchase upgraded app when exceed\nnumber of allowed projects\nPurchase app when on new project or\nrename project tab\nPurchase app when on new status report or rename\nstatus report tab\nThese tests will need the compiled app as an IPA file\nand have the app reinstalled after each test.");
//		Org.addTestCase("Create New Status Report","Create new status report call test cases.\nAdd test cases to progress report.");
//		Org.addTestCase("Aborted purchase","After purchase app\nCancel purchase\nShould return to free version not stay in paid version.");
		// reorder questions
		// exit questions
		Org.exitQuestionsSelection();
		// reorder status
		// exit status
		Org.exitStatusSelection();
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
