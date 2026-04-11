package com.automationframeworks.selenium_testng_java.locators;

public class ShoppingPagelocators
{
    // Shopping Page Locators
	
    public static final String FIRST_PRODUCT_ADD_BUTTON 		= "//button[@id='add-to-cart-sauce-labs-backpack']"; // XPath
    public static final String SECOND_PRODUCT_ADD_BUTTON 		= "//button[@id='add-to-cart-sauce-labs-bike-light']"; // XPath
    public static final String CART_ICON 						= "//*[@id=\"shopping_cart_container\"]/a"; // XPath
    public static final String firstProductName              	= "(//div[@class='inventory_item_name'])[1]";
    public static final String secondProductName               	= "(//div[@class='inventory_item_name'])[2]";
    public static final String firstProductPrice               	= "(//div[@class='inventory_item_price'])[1]";
    public static final String secondProductPrice               = "(//div[@class='inventory_item_price'])[2]";
    
    
    public static final String OnesieProduct_Add_Button         = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']";
    public static final String Sauce_Labs_Onesie_ProductName	= "(//div[@class='inventory_item_name '])[5]";
    public static final String Sauce_Labs_Onesie_Price  		= "(//div[@class='inventory_item_price'])[5]";
}
