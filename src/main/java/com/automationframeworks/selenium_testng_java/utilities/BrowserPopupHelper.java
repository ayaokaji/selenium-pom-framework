package com.automationframeworks.selenium_testng_java.utilities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

/**
 * Handles short-lived browser UI (native alerts, simple overlays) that can block automation.
 */
public final class BrowserPopupHelper {

	private BrowserPopupHelper() {
	}

	/**
	 * Dismisses a native JS {@link Alert} if one is shown (e.g. immediately after navigation).
	 */
	public static void dismissJavascriptAlertIfPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(800));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.dismiss();
		} catch (TimeoutException ignored) {
			// no alert
		}
	}

	/**
	 * Sends Escape once; helps some in-page banners without affecting normal controls if nothing is focused.
	 */
	public static void pressEscapeOnce(WebDriver driver) {
		try {
			new Actions(driver).sendKeys(Keys.ESCAPE).perform();
		} catch (Exception ignored) {
			// ignore
		}
	}
}
