package com.automationframeworks.selenium_testng_java.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationframeworks.selenium_testng_java.locators.OverviewPageLocators;

public class OverviewPageObjects 
{

     WebDriver driver;

    // Constructor
    public OverviewPageObjects(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to retrieve the Item Total from the Overview page
    
    @FindBy(xpath = OverviewPageLocators.ITEM_TOTAL)
    @CacheLookup
    WebElement itemTotal;

    public String getItemTotalText()
    {
        return itemTotal.getText();  // Returns the text of the item total, e.g., "$39.98"
    }

    
    
    

   
}
