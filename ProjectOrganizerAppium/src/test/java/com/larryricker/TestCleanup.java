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
			// "BadWithNames.json"
			 "BadWithNamesPro.json"
			 , "BadWithNamesProiPad.json"
			// , "GoodWithNames.json"
			 ,"GoodWithNamesPro.json", "PMIS-Pro.json"
			// , "PMIS.json", "ProgressReport.json"
			 , "ProgressReportPro.json"
			 // , "ProjectInfo.json"
			 , "ProjectInfoPro.json"
			 //, "ProjectOrganizer.json"
			 , "ProjectOrganizerPro.json"
			 //, "StatusReport4.json"
			 , "StatusReport4Pro.json"
			 // , "ProjectStatus.json"
			// , "ProjectStatusPro.json" 
			})
	@DisplayName("Cleanup Test")
	public void testCleanup(String capabilities, TestInfo info, TestReporter testReporter)
			throws WebDriverException, IOException, InterruptedException {
		Assert.assertNotNull("capabilities is not null", capabilities);
		System.setProperty("TEST_CAPABILITIES", capabilities);
		// Click on edit button
		// check if - exists
		do {
			Org.deleteAllTableViewRows("Delete Reskin");
			Org.deleteAllTableViewRows("Delete Enhancements");
			Org.deleteAllTableViewRows("Delete Uplift");
			Org.deleteAllTableViewRows("Delete Search Optimization");
			Org.deleteAllTableViewRows("Delete Metrics");
			Org.deleteAllTableViewRows("Delete Defects");
			Org.deleteAllTableViewRows("Delete Human Factors");
			Org.deleteAllTableViewRows("Delete Routing");
			Org.deleteAllTableViewRows("Delete Automated Testing");
			Org.deleteAllTableViewRows("Delete Content");
			Org.deleteAllTableViewRows("Delete Routing");
			Org.deleteAllTableViewRows("Delete Desktop");
			Org.deleteAllTableViewRows("Delete Upgrade");
			Org.deleteAllTableViewRows("Delete Integration");
			Org.deleteAllTableViewRows("Delete Biometrics");
			// temporary to clean up mess left by 
			// keyStrategy setValue
			Org.deleteAllTableViewRows("Delete ReskinReskin");
			Org.deleteAllTableViewRows("Delete EnhancementsEnhancements");
			Org.deleteAllTableViewRows("Delete UpliftUplift");
			Org.deleteAllTableViewRows("Delete Search OptimizationSearch Optimization");
			Org.deleteAllTableViewRows("Delete MetricsMetrics");
			Org.deleteAllTableViewRows("Delete DefectsDefects");
			Org.deleteAllTableViewRows("Delete Human FactorsHuman Factors");
			Org.deleteAllTableViewRows("Delete RoutingRouting");
			Org.deleteAllTableViewRows("Delete Automated TestingAutomated Testing");
			Org.deleteAllTableViewRows("Delete ContentContent");
			Org.deleteAllTableViewRows("Delete RoutingRouting");
			Org.deleteAllTableViewRows("Delete DesktopDesktop");
			Org.deleteAllTableViewRows("Delete UpgradeUpgrade");
			Org.deleteAllTableViewRows("Delete IntegrationIntegration");
			Org.deleteAllTableViewRows("Delete BiometricsBiometrics");
		} while (App.exists("More Info"));
		// when no more -
		// click on Done if it exists
		// Org.exitEditMode();
	}

}
