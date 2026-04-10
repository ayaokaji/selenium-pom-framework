package com.automationframeworks.selenium_testng_java.utilities;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;


public class ExceptionHandler extends Base {
	
	public void NoSuchElementExceptionHandler(WebElement element)
	{
	try
	{
		if(element.isDisplayed());
		{
			Assert.assertTrue(true);
		}
	}
	catch(NoSuchElementException e)
	{
		Assert.assertTrue(false);
		logger.info("No Records Found");
	}
	}

}
