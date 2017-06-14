package com.larryricker;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
	Driver.getDriver().findElementByAccessibilityId("Settings").click();
}

@AfterEach
public void cleanup() {
	
}

@Test
public void testSettingsTab() {
	
}

}
