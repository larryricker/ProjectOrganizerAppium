package com.larryricker;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
}