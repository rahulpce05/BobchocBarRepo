package com.learn.util;

import java.io.FileReader;
import java.util.Properties;

public class PropertyReader {
	FileReader reader;
	private static volatile PropertyReader propInstance;
	Properties prop = new Properties();
	private PropertyReader() {
		
	}

	public static synchronized PropertyReader getInstance() {
		if(propInstance==null) {
			propInstance = new PropertyReader();
		}	
		return propInstance;	
	}

	/**
	 * 
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName) {
		try {
			reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\application.properties");
			prop.load(reader);
			if(prop.getProperty(propertyName) != null) {
				return prop.getProperty(propertyName);
			}
		}catch (Exception e) {
			System.out.println("Property not found");
		}
		return null;
	}
}
