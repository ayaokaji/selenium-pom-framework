package com.automationframeworks.selenium_testng_java;

import java.util.Optional;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import com.automationframeworks.selenium_testng_java.utilities.Base;
import com.automationframeworks.selenium_testng_java.utilities.CommonMethods;
import com.automationframeworks.selenium_testng_java.utilities.TestUserProvider;

public class AppTest extends Base {
	public JSONObject applicationUser;
	private final TestUserProvider testUserProvider = new TestUserProvider();
	private String marcomBiosHomePageEndpoint;
	@BeforeMethod
	public void openApplicationUrl() {
		this.applicationEndpoint = this.baseURL.concat(this.marcomBiosHomePageEndpoint);
		this.driver.get(this.applicationEndpoint);
		JSONObject user = testUserProvider.returnUserByApplicationRole("saucedemo", "standard_user");
		this.applicationUser = Optional.ofNullable(this.applicationUser).orElse(user);
		this.commonMethods = new CommonMethods(this.driver);
		commonMethods.loginToSauceLabs(applicationUser);
	}
}
