package com.larryricker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.appium.java_client.ios.IOSDriver;


/**
 * Read property from the app.properties file 
 * The property capabilities will specify the name of the json file that sets the AppiumDriver capabilities
 * in the format used by Appium Desktop
 * @author lawrencericker
 *
 */
public class Driver {
	static private IOSDriver aDriver;
	static private Driver singletonFactory;
	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());

	private Driver() throws MalformedURLException {
		LOGGER.log(Level.INFO, "Driver() AppiumDriver");
		final DesiredCapabilities capabilities = DesiredCapabilities.ipad();
		JsonParser parser = new JsonParser();
	       App.load();
//	            Object obj = parser.parse(new FileReader(App.capabilities));
		   Object obj = parser.parse(new InputStreamReader(Driver.class.getClassLoader().getResourceAsStream(App.capabilities)));
		    JsonObject jsonObject = (JsonObject) obj;
		    System.out.println(jsonObject);
		    Set<Map.Entry<String,JsonElement>> capSet = jsonObject.entrySet();
		    Iterator<Map.Entry<String,JsonElement>> capIter = capSet.iterator();
		    Map.Entry<String,JsonElement> capable;
		    while (capIter.hasNext()) {
		    	capable = capIter.next();
				LOGGER.log(Level.INFO, "Driver() AppiumDriver capable->" + capable.getKey() + ":" + capable.getValue());
				capabilities.setCapability(capable.getKey(), capable.getValue());
		    }
		aDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	public static Driver getSingletonFactory() throws MalformedURLException {
		if (singletonFactory == null) {
			if (singletonFactory == null) {
				singletonFactory = new Driver();
			}
		}
		return singletonFactory;
	}
	
	public static IOSDriver getDriver() throws MalformedURLException {
		if (Driver.aDriver == null) {
			new Driver();
		}
		return Driver.aDriver;
	}
	
	public static boolean exists() {
		return (Driver.aDriver != null);
	}
	
	public static void destroy() throws MalformedURLException {
		LOGGER.log(Level.INFO, "destroy()");
		final WebDriver instanceOne = com.larryricker.Driver.getDriver();
		WebDriver instanceTwo = null;
		@SuppressWarnings("rawtypes")
		final Constructor[] constructors = com.larryricker.Driver.class.getDeclaredConstructors();
		for (@SuppressWarnings("rawtypes") final Constructor constructor : constructors) {
			constructor.setAccessible(true);
			instanceTwo = com.larryricker.Driver.getDriver();
			break;
		}
		LOGGER.log(Level.FINEST, "i1: " + instanceOne.hashCode());
		LOGGER.log(Level.FINEST, "i2: " + instanceTwo.hashCode());
	}
}
