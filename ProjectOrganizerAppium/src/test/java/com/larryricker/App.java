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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;

public class App {
		private static final Logger LOGGER = Logger.getLogger(App.class.getName());
		public static String capabilities, localizedCapabilities, overrideCapabilities;
	/**
	 * Read properties such as capabilities from properties file to specify the json file to parse to get the 
	 * capabilities for the current test suite
	 */
	public static void load() {

		LOGGER.log(Level.INFO, "load() - loads app.properties");
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = App.class.getClassLoader().getResourceAsStream("app.properties");
			prop.load(input);
			String computername = InetAddress.getLocalHost().getHostName();
			localizedCapabilities = prop.getProperty(computername + ".capabilities");
			overrideCapabilities = System.getenv("TEST_CAPABILITIES");
			if (overrideCapabilities == null) {
				overrideCapabilities = System.getProperty("TEST_CAPABILITIES");
			}
			capabilities = (overrideCapabilities != null 
					? overrideCapabilities 
							: localizedCapabilities != null 
							? localizedCapabilities : prop.getProperty("capabilities"));
			LOGGER.log(Level.INFO, "load() - loads app.properties capabilities=" + capabilities);
			
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, "load() - loading of app.properties failed");
			ioe.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException eieio) {
					eieio.printStackTrace();
				}
			}
		}
	}
	/**
	 * click on element with accessibility id
	 * @param using
	 * @throws MalformedURLException
	 */
	public static void click(String using) throws MalformedURLException {
		Driver.getDriver().findElementByAccessibilityId(using).click();
	}
	/**
	 * find WebElement by accessibility id
	 * @param using
	 * @return
	 * @throws MalformedURLException
	 */
	public static WebElement find(String using) throws MalformedURLException {
		WebElement thisElement = null;
		try {
			thisElement =  Driver.getDriver().findElementByAccessibilityId(using);
		}
		catch (NoSuchElementException e) {
		}
		return thisElement;
	}
	/**
	 * check if element with accessibility id of using exists
	 * @param using
	 * @return
	 * @throws MalformedURLException
	 */
	public static boolean exists(String using) throws MalformedURLException {
		WebElement thisElement = find(using);
		return thisElement != null;
	}
	/**
	 * enter text 'projectName' in text field with accessibility id of using
	 * @param using
	 * @param projectName
	 * @throws MalformedURLException
	 */
	public static void enterText(String using, String projectName) throws MalformedURLException {

		WebElement projectNameEdit = find(using);
		if (projectNameEdit != null && projectNameEdit.getText() != null && "".equals(projectNameEdit.getText()) == false) {
			projectNameEdit.clear();
		}
		projectNameEdit = find(using);
		if (projectNameEdit != null) {
			projectNameEdit.sendKeys(projectName);
		}
	}
	/**
	 * focus on an element
	 * @param using
	 * @throws MalformedURLException
	 */
	public static void focus(String using) throws MalformedURLException {
		TouchAction action = new TouchAction(Driver.getDriver());
	       action.tap(App.find(using));
	       action.perform();
	}
	/**
	 * Take a screen shot of the browser window regardless if an error has occurred
	 * The parameter testReporter of type TestReporter is required to
	 * allow the screen shot to display in jUnit5 html reports when 
	 * run from the Jenkins server and merged with the the Add Attachments
	 * plugin for Jenkins.
	 * @param testReporter
	 * @return 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws WebDriverException 
	 */
	public static String snapAnyway(String screenName, TestReporter testReporter) throws WebDriverException, MalformedURLException, IOException {
		String fileName = App.snapAnyway(screenName);
		testReporter.publishEntry(screenName, "\r\n\r\n[[ATTACHMENT|" + fileName + "]]\r\n\r\n");;
		return fileName;
	}
	
	/**
	 * Take a screen shot of the browser window regardless if an error has occurred
	 * @param screenName
	 * @return
	 * @throws WebDriverException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static String snapAnyway(String screenName) throws WebDriverException, MalformedURLException, IOException {
		LOGGER.fine("snapAnyway()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
		// get current date
		Calendar cal = Calendar.getInstance();
		// move to same directory as the XML file to have Jenkins pick 
//		it up
		File directoryToCreate = new File(System.getProperty("user.dir")
				+ File.separator + "build" + File.separator 
				+ "test-results" + File.separator + "junit-platform"
				);
		if (directoryToCreate.exists() == false) {
			LOGGER.fine("snapAnyway(); create directory " + directoryToCreate.mkdir());
		}
		else {
			LOGGER.fine("snapAnyway() existing directory " + directoryToCreate.exists());
		}
		File imageCopy = new File(System.getProperty("user.dir")
				+ File.separator + "build" + File.separator 
				+ "test-results" + File.separator + "junit-platform"
				+ File.separator + screenName + "_"
				+ dateFormat.format(cal.getTime()).toString() + ".png");
		// take screen shot directly to final resting place
		FileOutputStream out = new FileOutputStream(imageCopy.getAbsolutePath().toString());
		out.write(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES));
		out.close();
		return imageCopy.getAbsolutePath().toString();
	}
	/**
	 * wait for element with accessibility id of using
	 * @param using
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 */
	public static void waitForAccessibilityId(String using) throws InterruptedException, MalformedURLException {
		LOGGER.info("waitForAccessibilityId(" + using + ")");
		int count = 0;
		WebElement thisElement = null;
		do {
			count++;
			Thread.sleep(100);			
			LOGGER.info("waitForAccessibilityId(" + using + ") count-> " + count);
			thisElement = App.find(using);
		} while(count < 100
				&& thisElement != null
				&& App.isDisplayed(thisElement));
	}
	/**
	 * is element thisElement displayed on the screen
	 * @param using
	 * @return
	 * @throws MalformedURLException
	 */
	public static boolean isDisplayed(WebElement thisElement) throws MalformedURLException {
		return thisElement.isDisplayed();
	}
	/**
	 * is element with accessibility id of using displayed on the screen
	 * @param using
	 * @return
	 * @throws MalformedURLException
	 */
	public static boolean isDisplayed(String using) throws MalformedURLException {
		return isDisplayed(App.find(using));
	}
		
	
	/**
	 * wait for screen to load with accessibility id of using
	 * @param using
	 * @param seconds
	 * @throws MalformedURLException
	 */
	public static void waitForScreenToLoad(String using, int seconds) throws MalformedURLException{
		LOGGER.info("waitForScreenToLoad(" + using + ", " + seconds + ")");

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),seconds);
        wait.until(ExpectedConditions.visibilityOf(App.find(using)));
	}
}