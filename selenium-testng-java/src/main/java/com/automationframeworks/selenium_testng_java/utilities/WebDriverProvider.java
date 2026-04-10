package com.automationframeworks.selenium_testng_java.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;
import java.util.Map;

public class WebDriverProvider {

    public static WebDriver getDriver(String browserName) {
        WebDriver driver = null;

        if (browserName.startsWith("chrome")) {
            ChromeOptions ops = new ChromeOptions();

            ops.setCapability("goog:loggingPrefs", new HashMap<String, String>() {{
	            put("browser", "ALL"); // Enable browser logs
	        }});
            ops.addArguments("--remote-allow-origins=*");
			ops.addArguments("--enable-logging");
			ops.addArguments("--log-level=0");
			ops.addArguments("--v=1");// 0 is for all logs, 1 is for severe, 2 for warning, 3 for info, 4 for debug
            ops.addArguments("--remote-allow-origins=*");

            if (browserName.equalsIgnoreCase("chrome-headless")) {
                ops.addArguments("--headless=new");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(ops);
        } else if (browserName.startsWith("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (browserName.equalsIgnoreCase("firefox-headless")) {
                firefoxOptions.addArguments("--headless=new");
            }
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        return driver;
    }


}
