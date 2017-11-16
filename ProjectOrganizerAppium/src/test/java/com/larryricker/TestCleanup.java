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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import com.google.common.collect.ImmutableList;

/**
 * @author lawrencericker
 *
 */
@Tag("cleanup")
public class TestCleanup {

	static final Logger LOGGER = Logger.getLogger(TestSettingsTab.class.getName());

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
	public void cleanup(String capabilities, TestInfo info, TestReporter testReporter)
			throws WebDriverException, IOException {
		try {
			App.snapAnyway(capabilities.replaceAll(".json", "") + "ExitCleanup", testReporter);
			Driver.destroy();
		} catch (NoSuchElementException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	// StatusReport4Pro and ProjectStatusPro have lists of projects to delete
	@ParameterizedTest
	@ValueSource(strings = {
//			"BadWithNames6p.json"
//			, "BadWithNamesPro6p.json", "GoodWithNames6p.json"
//			,"GoodWithNamesPro6p.json", "PMIS6p.json"
//			, "PMIS-Pro6p.json", "ProgressReport6p.json"
//			, "ProgressReportPro6p.json", "ProjectInfo6p.json"
//			, "ProjectInfoPro6p.json", "StatusReport46p.json"
//			, "StatusReport4Pro6p.json"// , "ProjectStatus6p.json"
			// , "ProjectStatusPro6p.json"
//			, 
//			"BadWithNames.json", "BadWithNamesPro.json"
//			, "GoodWithNames.json"
//			, "GoodWithNamesPro.json", "PMIS-Pro.json"
//			, "PMIS.json", "ProgressReport.json"
//			, "ProgressReportPro.json", "ProjectInfo.json"
//			, "ProjectInfoPro.json"
			"ProjectOrganizer.json", "ProjectOrganizerPro.json"
//			, "StatusReport4.json"
//			, "StatusReport4Pro.json", "ProjectStatus.json"
//			, "ProjectStatusPro.json"
//			, "BadWithNamesiPad.json"
//			, "BadWithNamesProiPad.json"
//			, "GoodWithNamesiPad.json"
//			,"GoodWithNamesProiPad.json"
//			, "PMIS-ProiPad.json"
//			, "PMISiPad.json"
//			, "ProgressReportiPad.json"
//			, "ProgressReportProiPad.json"
//			, "ProjectInfoiPad.json"
//			, "ProjectInfoProiPad.json"
			, "ProjectOrganizeriPad.json"
			, "ProjectOrganizerProiPad.json"
//			, "StatusReport4iPad.json"
//			, "StatusReport4ProiPad.json"
//			, "ProjectStatusiPad.json"
//			, "ProjectStatusProiPad.json"
			})
	@DisplayName("Cleanup Test")
	public void testCleanup(String capabilities, TestInfo info, TestReporter testReporter)
			throws WebDriverException, IOException, InterruptedException {
		Assert.assertNotNull("capabilities is not null", capabilities);
		System.setProperty("TEST_CAPABILITIES", capabilities);

		// Click on edit button
		// check if - exists
		do {
			Org.deleteAllTableViewRows("Delete Reskin", testReporter);
			Org.deleteAllTableViewRows("Delete Enhancements", testReporter);
			Org.deleteAllTableViewRows("Delete Uplift", testReporter);
			Org.deleteAllTableViewRows("Delete Search Optimization", testReporter);
			Org.deleteAllTableViewRows("Delete Metrics", testReporter);
			Org.deleteAllTableViewRows("Delete Defects", testReporter);
			Org.deleteAllTableViewRows("Delete Human Factors", testReporter);
			Org.deleteAllTableViewRows("Delete Routing", testReporter);
			Org.deleteAllTableViewRows("Delete Automated Testing", testReporter);
			Org.deleteAllTableViewRows("Delete Content", testReporter);
			Org.deleteAllTableViewRows("Delete Routing", testReporter);
			Org.deleteAllTableViewRows("Delete Desktop", testReporter);
			Org.deleteAllTableViewRows("Delete Upgrade", testReporter);
			Org.deleteAllTableViewRows("Delete Integration", testReporter);
			Org.deleteAllTableViewRows("Delete Biometrics", testReporter);
			// temporary to clean up mess left by 
			// keyStrategy setValue
			Org.deleteAllTableViewRows("Delete ReskinReskin", testReporter);
			Org.deleteAllTableViewRows("Delete EnhancementsEnhancements", testReporter);
			Org.deleteAllTableViewRows("Delete UpliftUplift", testReporter);
			Org.deleteAllTableViewRows("Delete Search OptimizationSearch Optimization", testReporter);
			Org.deleteAllTableViewRows("Delete MetricsMetrics", testReporter);
			Org.deleteAllTableViewRows("Delete DefectsDefects", testReporter);
			Org.deleteAllTableViewRows("Delete Human FactorsHuman Factors", testReporter);
			Org.deleteAllTableViewRows("Delete RoutingRouting", testReporter);
			Org.deleteAllTableViewRows("Delete Automated TestingAutomated Testing", testReporter);
			Org.deleteAllTableViewRows("Delete ContentContent", testReporter);
			Org.deleteAllTableViewRows("Delete RoutingRouting", testReporter);
			Org.deleteAllTableViewRows("Delete DesktopDesktop", testReporter);
			Org.deleteAllTableViewRows("Delete UpgradeUpgrade", testReporter);
			Org.deleteAllTableViewRows("Delete IntegrationIntegration", testReporter);
			Org.deleteAllTableViewRows("Delete BiometricsBiometrics", testReporter);
		} while (App.exists("More Info"));
		// when no more -
		// click on Done if it exists
		// Org.exitEditMode();
	}

}
