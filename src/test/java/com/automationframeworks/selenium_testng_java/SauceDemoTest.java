	package com.automationframeworks.selenium_testng_java;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.automationframeworks.selenium_testng_java.pageObjects.LoginObjects;
import com.automationframeworks.selenium_testng_java.pageObjects.OverviewPageObjects;
import com.automationframeworks.selenium_testng_java.pageObjects.ShoppingPageObjects;
import com.automationframeworks.selenium_testng_java.pageObjects.YourCartObjects;
import com.automationframeworks.selenium_testng_java.pageObjects.YourInformationObjects;
import com.automationframeworks.selenium_testng_java.utilities.Base;
import com.automationframeworks.selenium_testng_java.utilities.PropertiesReader;

public class SauceDemoTest extends Base 
{
	LoginObjects loginObjects;
    ShoppingPageObjects shoppingPage;
    YourCartObjects yourCart;
    YourInformationObjects yourInformationPage;
    OverviewPageObjects overviewPage;
    
    private PropertiesReader propertiesReader;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;
    
    @BeforeMethod
    public void setup() throws IOException
    {
        propertiesReader = new PropertiesReader();
        
		username = propertiesReader.getProperty("username");
        password = propertiesReader.getProperty("password");
        firstName = propertiesReader.getProperty("firstName");
        lastName = propertiesReader.getProperty("lastName");
        postalCode = propertiesReader.getProperty("postalCode");
        
    	// Initialize Page Object instances
        loginObjects = new LoginObjects(driver);
        shoppingPage = new ShoppingPageObjects(driver);
        yourCart = new YourCartObjects(driver);
        yourInformationPage = new YourInformationObjects(driver);
        overviewPage = new OverviewPageObjects(driver);
        
        driver.get(baseURL);  	
    }
    
	@Test
    public void verifyProductPurchase() throws Exception
    {
	//----------------------------- Validating Navigation (checking title, URL, and page elements)---------------------------------//
    	
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title is not as expected.");      
        logger.info("Page title validated successfully");
        
    //----------------------------- Validate Availability of Username and Password Fields On Login Page ---------------------------------//
        
        WebElement usernameField = loginObjects.getUsernameField();
        WebElement passwordField = loginObjects.getPasswordField();
        
        Assert.assertTrue(usernameField.isDisplayed(), "Username field is not visible.");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not visible.");
        
        logger.info("Username and Password fields are visible on the Login page.");
        
    //-----------------------------	Validating  Availability of Username and Password Test Data ---------------------------------//
        String standardUsername = loginObjects.getStandardUsername();
        String passwordForAllUsers = loginObjects.getPasswordForAllUsers().getText().trim().split(":")[1].trim();
        
        Assert.assertEquals(standardUsername, username , "Entered username doesn't match.");
        Assert.assertEquals(passwordForAllUsers, password  , "Entered Password doesn't match.");

       logger.info("Test data for Username and Password is available.");
       
    //------------------------------ 	Entering Login Details ----------------------------------------------------------------//
       
       loginObjects.getUsernameField().sendKeys(standardUsername);
       loginObjects.getPasswordField().sendKeys(passwordForAllUsers);
       
       Assert.assertEquals(usernameField.getDomAttribute("value"), standardUsername, "Username input does not match.");
       Assert.assertEquals(passwordField.getDomAttribute("value"), passwordForAllUsers, "Password input does not match.");
       
       logger.info("Password entered matches the expected value.");
       
     //------------------------------	Validating Availability of Login Button ----------------------------------------------------------------//
       
       WebElement loginButton = loginObjects.LoginButton();
       Assert.assertTrue(loginButton.isEnabled(), "Login button is not enabled.");
       logger.info("Login button is enabled.");
       
     //-----------------------------  Click on Login Button -----------------------------//
       
       loginButton.click();;
       String shoppingPageTitle = driver.getTitle();
       Assert.assertEquals(shoppingPageTitle, "Swag Labs", "shopping page title is not as expected.");
       
       logger.info("Shopping page validated successfully.");
       
     //-----------------------------	Validate Availability of Products and Add to Cart Buttons -----------------------------//
       
       int productCount = shoppingPage.getProductCount();
       Assert.assertTrue(productCount > 0, "No products found on the page.");
       
       int addToCartButtonCount = shoppingPage.getAddToCartButtonCount();
       Assert.assertEquals(productCount , addToCartButtonCount , "'Add to Cart' buttons are not visible for all products.");
       
       logger.info("Availability of Products and Add to Cart Buttons validated successfully");
       
     //----------------------------- Clicking on Add to Cart for First Two Products -----------------------------------//
       
       logger.info("Clicking 'Add to Cart' for the first product.");
       shoppingPage.addFirstProductToCart();
    
       logger.info("Clicking 'Add to Cart' for the second product.");
       shoppingPage.addSecondProductToCart();
       
     //----------------------------- 	Validating Cart Icon with Number 2 ---------------------------------------------------//
       
       WebElement cartCount = shoppingPage.getCartCount();
       Assert.assertTrue(cartCount.getText().contains("2"));
       logger.info("Cart icon displays the correct number of items: 2.");

     //----------------------------- 	Validating Availability of Cart Icon ---------------------------------------------------//
       
       Assert.assertTrue(shoppingPage.cartIcon.isDisplayed());       
       logger.info("Availability of cart icon validated successfully");
       
     //----------------------------- 	Clicking on Cart Icon ---------------------------------------------------/
       shoppingPage.clickingOnCartIcon();
       
       String currentUrl = driver.getCurrentUrl();
       Assert.assertTrue(currentUrl.contains("cart"), "The user was not directed to the 'Your Cart' page.");
       
       logger.info("User was successfully directed to the 'Your Cart' page.");
       
     //----------------------------- Validate that the two products added are displayed in the cart ---------------------------------------------------//
      List<WebElement> cartItemNames = yourCart.getCartItemNames();
      List<WebElement> cartItemPrices = yourCart.getCartItemPrices();
      
      					// Validate that the correct product names are displayed in the cart
      String firstProductName = shoppingPage.getFirstProductName();
      String secondProductName = shoppingPage.getSecondProductName();
      
      Assert.assertTrue(cartItemNames.get(0).getText().contains(firstProductName), "First product name in the cart is incorrect.");
      Assert.assertTrue(cartItemNames.get(1).getText().contains(secondProductName), "Second product name in the cart is incorrect.");
      
      					// Validate that the correct product prices are displayed in the cart
      String firstProductPrice = shoppingPage.getFirstProductPrice();
      String secondProductPrice = shoppingPage.getSecondProductPrice();
      
      Assert.assertTrue(cartItemPrices.get(0).getText().contains(firstProductPrice), "First product price in the cart is incorrect.");
      Assert.assertTrue(cartItemPrices.get(1).getText().contains(secondProductPrice), "Second product price in the cart is incorrect.");
      
      logger.info("two products added are displayed in the cart validated successfully");
      
      //----------------------------- Validating product Item total  ---------------------------------------------------//
      double actualTotalPrice = yourCart.getTotalCartValue();
      
      				// Calculate expected total price by summing up the individual product prices
      double expectedTotalPrice = Double.parseDouble(firstProductPrice.replace("$", "")) + Double.parseDouble(secondProductPrice.replace("$", ""));
      
      				// Assertion to verify if the actual total matches the expected total
      Assert.assertEquals(actualTotalPrice, expectedTotalPrice, "Total price in the cart is incorrect.");
      
      logger.info("product Item total validated successfully");
      
     //----------------------------- Validate Availability of Checkout Button --------------------------------//
     
      Assert.assertTrue(yourCart.checkoutButton.isDisplayed());
      Assert.assertTrue(yourCart.checkoutButton.isEnabled());
      logger.info("Checkout button is visible and enabled.");
      
     //----------------------------- Click on Checkout --------------------------------//
      
      yourCart.clickCheckoutButton();
      
      String checkoutPageUrl = driver.getCurrentUrl();
      Assert.assertTrue(checkoutPageUrl.contains("checkout-step-one"));
      
      logger.info("User is on the 'Checkout: Your Information' page.");

     //--------------------- Validating Availability of "First Name", "Last Name", and "Postal Code" Fields ---------------------------------//
      
      logger.info("Filling in the user information: First Name, Last Name, and Postal Code.");
      
      yourInformationPage.enterFirstName(firstName);
      yourInformationPage.enterLastName(lastName);
      yourInformationPage.enterPostalCode(postalCode);
      
      							// Assert that the fields are filled 
      
      Assert.assertEquals(yourInformationPage.firstNameField.getAttribute("value"), firstName);
      Assert.assertEquals(yourInformationPage.lastNameField.getAttribute("value"), lastName);
      Assert.assertEquals(yourInformationPage.postalCodeField.getAttribute("value"), postalCode);
      
      logger.info("First Name, Last Name, and Postal Code fields have been filled correctly.");

     //--------------------------- Validating Availability of "Continue" Button ------------------------------//
      
      Assert.assertTrue(yourInformationPage.continueButton.isDisplayed());
      Assert.assertTrue(yourInformationPage.continueButton.isEnabled());
      
      logger.info("Continue button is visible and enabled.");
      
      //----------------------------- Click on Continue --------------------------------//
      
      yourInformationPage.continueButton.click();
      logger.info("Continue button Clicked Succesfully");      
      
      //----------------------------- Validate Navigation to "Checkout: Overview" Page --------------------------------//
      
      String overviewPageUrl = driver.getCurrentUrl();
      Assert.assertTrue(overviewPageUrl.contains("checkout-step-two"));
      logger.info("User is on the 'Checkout: Overview' page.");
      
      //----------------------------- Validate "Item Total" Calculation --------------------------------//
     
      String checkoutItemTotalText = overviewPage.getItemTotalText();
      checkoutItemTotalText = checkoutItemTotalText.replaceAll("[^\\d.]", "").trim();
      double checkoutItemTotal = Double.parseDouble(checkoutItemTotalText);
      Assert.assertEquals(actualTotalPrice, checkoutItemTotal, "Item total does not match between 'Your Cart' and 'Checkout: Overview' page.");
      logger.info("Item total matches between 'Your Cart' and 'Checkout: Overview' page.");

    }  
    
	
}
