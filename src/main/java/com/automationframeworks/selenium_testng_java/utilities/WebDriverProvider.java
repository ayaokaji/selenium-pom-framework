package com.automationframeworks.selenium_testng_java.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;
import java.util.Map;

public class WebDriverProvider {

	private static Map<String, Object> chromiumPopupSuppressionPrefs() {
		Map<String, Object> prefs = new HashMap<>();
		// 2 = block notifications (permission prompts / toasts)
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		// Native "Change your password" / breach dialog (Google Password Manager)
		prefs.put("profile.password_manager_leak_detection", false);
		return prefs;
	}

	/** With {@link #chromiumPopupSuppressionPrefs()}; newer Chrome often needs flags as well. */
	private static void addChromiumPasswordBreachAndSaveUiSuppression(ChromeOptions ops) {
		ops.addArguments("--disable-save-password-bubble");
		ops.addArguments(
				"--disable-features=PasswordLeakDetection,PasswordManagerLeakDetection,PasswordManagerOnboarding");
	}

	private static void addChromiumPasswordBreachAndSaveUiSuppression(EdgeOptions ops) {
		ops.addArguments("--disable-save-password-bubble");
		ops.addArguments(
				"--disable-features=PasswordLeakDetection,PasswordManagerLeakDetection,PasswordManagerOnboarding");
	}

    public static WebDriver getDriver(String browserName) {
        WebDriver driver = null;

        if (browserName.startsWith("chrome")) {
            ChromeOptions ops = new ChromeOptions();
			ops.setExperimentalOption("prefs", chromiumPopupSuppressionPrefs());
			addChromiumPasswordBreachAndSaveUiSuppression(ops);

            ops.setCapability("goog:loggingPrefs", new HashMap<String, String>() {{
	            put("browser", "ALL"); // Enable browser logs
	        }});
            ops.addArguments("--remote-allow-origins=*");
			ops.addArguments("--enable-logging");
			ops.addArguments("--log-level=0");
			ops.addArguments("--v=1");// 0 is for all logs, 1 is for severe, 2 for warning, 3 for info, 4 for debug
			ops.addArguments("--disable-notifications");

            if (browserName.equalsIgnoreCase("chrome-headless")) {
                ops.addArguments("--headless=new");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(ops);
        } else if (browserName.startsWith("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            if (browserName.equalsIgnoreCase("firefox-headless")) {
                firefoxOptions.addArguments("--headless=new");
            }
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setExperimentalOption("prefs", chromiumPopupSuppressionPrefs());
			addChromiumPasswordBreachAndSaveUiSuppression(edgeOptions);
			edgeOptions.addArguments("--disable-notifications");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        return driver;
    }


}
