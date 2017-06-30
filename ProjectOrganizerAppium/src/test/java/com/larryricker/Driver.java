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
 * Read property from the app.properties file The property capabilities will
 * specify the name of the json file that sets the AppiumDriver capabilities in
 * the format used by Appium Desktop
 * 
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
		Object obj = parser
				.parse(new InputStreamReader(Driver.class.getClassLoader().getResourceAsStream(App.capabilities)));
		JsonObject jsonObject = (JsonObject) obj;
		System.out.println(jsonObject);
		Set<Map.Entry<String, JsonElement>> capSet = jsonObject.entrySet();
		Iterator<Map.Entry<String, JsonElement>> capIter = capSet.iterator();
		Map.Entry<String, JsonElement> capable;
		while (capIter.hasNext()) {
			capable = capIter.next();
			LOGGER.log(Level.INFO, "Driver() AppiumDriver capable->" + capable.getKey() + ":" + capable.getValue());
			capabilities.setCapability(capable.getKey(), capable.getValue());
		}
		aDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	public static Driver getSingletonFactory() throws MalformedURLException {
		if (singletonFactory == null) {
			singletonFactory = new Driver();
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
		Driver.aDriver = null;
	}
}
