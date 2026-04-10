package com.automationframeworks.selenium_testng_java.locators;

public class LoginLocators 
{
	// --------------------------------Login Page Locators------------------------------------------- //
    public static final String USERNAME_FIELD  = "//input[@id='user-name']"; // XPATH
    public static final String PASSWORD_FIELD  = "//input[@type='password']"; // XPATH
    public static final String LOGIN_BUTTON    = "//input[@data-test='login-button']"; // XPATH
    
	// --------------------------------Accepted usernames------------------------------------------- //

    public static final String standard_user       		= "//*[@id='login_credentials']"; 
    public static final String locked_out_user         	= "//*[@id='login_credentials']";
    public static final String problem_user        		= "//*[@id=\"login_credentials\"]/text()[3]"; 
    public static final String performance_glitch_user  = "//*[@id=\"login_credentials\"]/text()[4]"; 
    public static final String error_user        		= "//*[@id=\"login_credentials\"]/text()[5]"; 
    public static final String visual_user         		= "//*[@id=\"login_credentials\"]/text()[6]";

	// --------------------------------Password for all users------------------------------------------- //    
    public static final String PasswordforALL 		 = "//div[text()='secret_sauce']";
}
