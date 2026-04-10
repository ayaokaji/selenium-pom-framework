package com.automationframeworks.selenium_testng_java.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

	private Properties properties;

    public String determinePropertiesFile(){
		String environment = System.getProperty("env", "dev");
		if (environment.equals("prod")){
            return "src/main/resources/prod.properties";
		}
		else if(environment.equals("uat"))
		{
			return "src/main/resources/uat.properties";
		}
		else{
            return "src/main/resources/dev.properties";
		}
	}

	public PropertiesReader()
	{
		File src = new File(determinePropertiesFile());

		try {
			FileInputStream fis = new FileInputStream(src);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getProperty(String propertyName) {
		return this.properties.getProperty(propertyName);
	}

}




