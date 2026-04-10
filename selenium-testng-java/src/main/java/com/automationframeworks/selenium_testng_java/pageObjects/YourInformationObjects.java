package com.automationframeworks.selenium_testng_java.pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import com.automationframeworks.selenium_testng_java.locators.YourInformationLocators;

public class YourInformationObjects
{

    WebDriver driver;

    // Constructor
    public YourInformationObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-------------------------------Method to enter first name---------------------------------//

    @FindBy(xpath = YourInformationLocators.FIRST_NAME_FIELD)
    @CacheLookup
	public WebElement firstNameField;
    public void enterFirstName(String firstName) 
    {
        firstNameField.sendKeys(firstName);  
    }

    //-------------------------------Method to enter last name---------------------------------//

    @FindBy(xpath = YourInformationLocators.LAST_NAME_FIELD)
    @CacheLookup
	public WebElement lastNameField;
    public void enterLastName(String lastName) 
    {
        lastNameField.sendKeys(lastName);  
    }
    //-------------------------------Method to enter postal code---------------------------------//

    @FindBy(xpath = YourInformationLocators.POSTAL_CODE_FIELD)
    @CacheLookup
	public WebElement postalCodeField;
    public void enterPostalCode(String postalCode)
    {
        postalCodeField.sendKeys(postalCode);  // Using cached WebElement
    }
    //-------------------------------Method to click continue button---------------------------------//

    @FindBy(xpath = YourInformationLocators.CONTINUE_BUTTON)
    @CacheLookup
	public WebElement continueButton;
    public void clickContinueButton() 
    {
        continueButton.click();  // Using cached WebElement
    }
    
}
