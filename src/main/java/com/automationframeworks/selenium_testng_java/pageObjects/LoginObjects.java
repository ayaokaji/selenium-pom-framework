package com.automationframeworks.selenium_testng_java.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automationframeworks.selenium_testng_java.locators.LoginLocators;

public class LoginObjects 
{
	WebDriver driver;
    
    //Constructor
    public LoginObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

  //-------------------------------EnterUsername---------------------------------//
    
    @FindBy(xpath = LoginLocators.USERNAME_FIELD) 							//  finds the web element using the XPath of LoginLocators.USERNAME_FIELD			
    @CacheLookup 																
	 WebElement usernameField; 												// Declaring a WebElement variable 'usernameField' to store the reference to the username field on the page				

    public  WebElement getUsernameField() 									// Getter method to return the 'usernameField' WebElement when needed				
    {
        return usernameField; 												// Returning the reference to the username field			
    }

 
    //-------------------------------EnterPassWord---------------------------------//
    
    @FindBy(xpath = LoginLocators.PASSWORD_FIELD)
    @CacheLookup
	 WebElement passwordField;
    
    public  WebElement getPasswordField()
    {
    	return passwordField;
    }
    
    
    //-------------------------------loginButton---------------------------------//
    
    @FindBy(xpath = LoginLocators.LOGIN_BUTTON)
    @CacheLookup
	 WebElement loginButton;
    
    public  WebElement LoginButton()
    {
		return loginButton;
    }	
    
    
    //-------------------------------Accepted Usernames---------------------------------//
    
    @FindBy(xpath = LoginLocators.standard_user) 
    @CacheLookup
    WebElement standardUsername;

    public  String getStandardUsername()									// Method to retrieve the standard username from the web element
    {
    	String allText = standardUsername.getText();						// Get all the text from the standardUsername web element
        String[] usernames = allText.split("\n");							// Split the text by line breaks (newline characters) into an array of strings
        return usernames[1].trim();											// Return the second line (index 1) of the split text, with leading and trailing whitespace removed
       
    }
    
    		//----------------------------------------------------------------//

    @FindBy(xpath = LoginLocators.locked_out_user) 
    @CacheLookup
     WebElement lockedUsername;

    public  WebElement getLockedUsername()
    {
        return lockedUsername;  
    }
    
			//----------------------------------------------------------------//

    @FindBy(xpath = LoginLocators.problem_user) 
    @CacheLookup
     WebElement problemUsername;

    public  WebElement getProblemUsername()
    {
        return problemUsername;  
    }
    
			//----------------------------------------------------------------//

    
    @FindBy(xpath = LoginLocators.performance_glitch_user) 
    @CacheLookup
     WebElement performanceUsername;

    public  WebElement getPerformanceUsername()
    {
        return performanceUsername;  
    }
    
			//----------------------------------------------------------------//

    @FindBy(xpath = LoginLocators.error_user) 
    @CacheLookup
     WebElement errorUsername;

    public  WebElement getErrorUsername()
    {
        return errorUsername;  
    }
    
			//----------------------------------------------------------------//
    
    @FindBy(xpath = LoginLocators.visual_user) 
    @CacheLookup
     WebElement visualUsername;

    public  WebElement getVisualUsername()
    {
        return visualUsername;  // Returns the Visual username WebElement.
    }
    
  //-------------------------------Password for all users---------------------------------//

    @FindBy(xpath = LoginLocators.PasswordforALL) 
    @CacheLookup
    WebElement passwordForAll;

    public  WebElement getPasswordForAllUsers()
    {
		return passwordForAll;
         
    }
}
