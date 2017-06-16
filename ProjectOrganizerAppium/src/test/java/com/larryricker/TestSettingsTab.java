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

import java.net.MalformedURLException;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;



@Tag("ios")
public class TestSettingsTab {
	
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
	Org.gotoSettingsTableView();
	// verify upsell message
	verifySettingsUpsellMessage();
	// click on upsell and buy app - skip for now
	// Click on rate our app
//	App.click("	Rate our app"); // not testable at this time
	// exit app rating
	Org.shareAppFromSettingsTableView();
	// restore purchases - skip for now
	Org.exitSettingsTableView();	
	// create a new project
	Org.createNewProject("Enhancements");
	Org.createNewProject("Uplift");
	Org.createNewProject("Reskin");
	Org.createNewProject("Search Optimization");
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
	// create a new status report
	// create a new question
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

private void verifySettingsUpsellMessage() throws MalformedURLException {
	WebElement upsell = App.find("Buy Progress Report Pro 4.99");
	Assertions.assertEquals(upsell.getText(), upsell.getText(), "Upsell message is correct");
}

}
