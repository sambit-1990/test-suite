package Sambit.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Sambit.PageObjects.CartPage;
import Sambit.PageObjects.ProductCatalogue;
import Sambit.TestComponents.BaseTest;
import Sambit.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest
{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		String Item = "ZARA COAT 3";
		landingPage.loginApplication("sambit.gnit@gmail.com", "Sambit@g123");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException
	{
		String Item = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("sambit.gnit@gmail.com", "Sambit@123");
		
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(Item);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
