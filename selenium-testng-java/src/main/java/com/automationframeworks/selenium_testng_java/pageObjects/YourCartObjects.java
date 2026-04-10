package com.automationframeworks.selenium_testng_java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import com.automationframeworks.selenium_testng_java.locators.YourCartLocators;

public class YourCartObjects 
{

    WebDriver driver;

    // Constructor
    public YourCartObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-------------------------------Method to get the list of product names in the cart---------------------------------//
    
    @FindBy(xpath = YourCartLocators.CART_ITEM_NAME)
    @CacheLookup
    private List<WebElement> cartItemNames;
    public List<WebElement> getCartItemNames() 
    {
        return cartItemNames;
    }
    
    //-------------------------------Method to get the list of item prices in the cart---------------------------------//

    @FindBy(xpath = YourCartLocators.CART_ITEM_PRICE)
    @CacheLookup
    private List<WebElement> cartItemPrices;
    public List<WebElement> getCartItemPrices() 
    {
        return cartItemPrices;
    }
    
    //-------------------------------Method to get the added products Total value---------------------------------//

    public double getTotalCartValue ()
    {
    	List<WebElement> cartItemPrices = driver.findElements(By.xpath(YourCartLocators.CART_ITEM_PRICE));
    	
    	double firstItemPrice = Double.parseDouble(cartItemPrices.get(0).getText().replace("$", ""));
    	double secondItemPrice = Double.parseDouble(cartItemPrices.get(1).getText().replace("$", ""));
		return firstItemPrice + secondItemPrice ;
    }

    //-------------------------------Method to click on the Checkout button---------------------------------//

    @FindBy(xpath = YourCartLocators.CHECKOUT_BUTTON)
    @CacheLookup
	public WebElement checkoutButton;
    public void clickCheckoutButton() 
    {
        checkoutButton.click();
    }

   
}

