package com.automationframeworks.selenium_testng_java.pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationframeworks.selenium_testng_java.locators.ShoppingPagelocators;

public class ShoppingPageObjects
{
	  WebDriver driver;
	
	 // Constructor 
	  public ShoppingPageObjects(WebDriver driver) 
	  	{
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    } 
	  
	 //------------------------------------------Actions for Shopping-------------------------------------------//
	  
	  //---------Method to click the "Add to Cart" button for the first product------------//

	  @FindBy(xpath = ShoppingPagelocators.FIRST_PRODUCT_ADD_BUTTON)
	  @CacheLookup
	  WebElement firstProductAddButton;
	  
	  public void addFirstProductToCart() 
	  	{
	        firstProductAddButton.click();
	    }
	  
	  //----------Method to click the "Add to Cart" button for the second product----------------//
	  
	  @FindBy(xpath = ShoppingPagelocators.SECOND_PRODUCT_ADD_BUTTON)
	  @CacheLookup
	  WebElement secondProductAddButton;
	  
	  public void addSecondProductToCart()
	  	{
	        secondProductAddButton.click();
	    }
	  
	  //-----------------Method to click on the cart icon----------------------------//	
	  
	  @FindBy(xpath = ShoppingPagelocators.CART_ICON)
	  @CacheLookup
	  public WebElement cartIcon;
	  
	  public void clickingOnCartIcon()
	  	{
	        cartIcon.click();
	    }
	  
	  //-----------Method to retrieve the text of the cart icon (cart item count)------//
	  
	  @FindBy(xpath = ShoppingPagelocators.CART_ICON)
	  @CacheLookup
	  WebElement cartIcon1;
	  
	  public WebElement getCartCount()
	  	{
		  	// Get the text (cart item count)
	        cartIcon.getText();
	        // Return the cart icon WebElement
			return cartIcon1;
	    }
	 
		 //------------------------------------------Method to get the first product name-------------------------------------------//
	  
	  	@FindBy(xpath = ShoppingPagelocators.firstProductName)
	    @CacheLookup
	    WebElement firstProductName;

	    public String getFirstProductName() 
	    {
	        return firstProductName.getText();
	    }

		 //------------------------------------------Method to get the second product name-------------------------------------------//

	    @FindBy(xpath = ShoppingPagelocators.secondProductName)
	    @CacheLookup
	    WebElement secondProductName;

	    public String getSecondProductName() 
	    {
	        return secondProductName.getText();
	    }
	    
		 //------------------------------------------Method to get the first product price-------------------------------------------//
	    
	    @FindBy(xpath = ShoppingPagelocators.firstProductPrice)
	    @CacheLookup
	    WebElement firstProductPrice;

	    public String getFirstProductPrice()
	    {
	        return firstProductPrice.getText();
	    }

		 //------------------------------------------Method to get the second product price-------------------------------------------//
	    
	    @FindBy(xpath = ShoppingPagelocators.secondProductPrice)
	    @CacheLookup
	    WebElement secondProductPrice;

	    public String getSecondProductPrice() 
	    {
	        return secondProductPrice.getText();
	    }
	    
		//------------------------------------------Method to get the Product Count -------------------------------------------//
	    
	    @FindBy(className = "inventory_item")
	    @CacheLookup
	    public List<WebElement> allInventoryItems;
	    
	    public int getProductCount()
	    {
	        return allInventoryItems.size();  
	    }
	   
		//------------------------------------------Method to get the add to card button count -------------------------------------------//
	    @FindBy(className = "btn_inventory")
	    @CacheLookup
	    public List<WebElement> allAddToCartButtons;
	    
	    public int getAddToCartButtonCount()
	    {
	        return allAddToCartButtons.size(); 
	    }
	    
}
