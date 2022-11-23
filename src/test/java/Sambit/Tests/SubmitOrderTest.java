package Sambit.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Sambit.PageObjects.CartPage;
import Sambit.PageObjects.CheckoutPage;
import Sambit.PageObjects.ConfirmationPage;
import Sambit.PageObjects.OrdersPage;
import Sambit.PageObjects.ProductCatalogue;
import Sambit.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest
{
	String Item = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void SubmitOrderTest(HashMap<String,String> input) throws IOException
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("Item"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("Item"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.chooseCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		

	}
	
	@Test(dependsOnMethods = {"SubmitOrderTest"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("sambit.gnit@gmail.com", "Sambit@123");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(Item));
		
	}
		
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap((System.getProperty("user.dir")+"\\src\\test\\java\\Sambit\\data\\PurchaseOrder.json"));
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

//@DataProvider
//public Object[][] getData()
//{
//	return new Object [][] {{"sambit.gnit@gmail.com", "Sambit@123","ZARA COAT 3"},{"gojo@ghosh.com","Ranaghat@123","IPHONE 13 PRO"}};
//}

//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "sambit.gnit@gmail.com");
//map.put("password", "Sambit@123");
//map.put("Item", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "gojo@ghosh.com");
//map1.put("password", "Ranaghat@123");
//map1.put("Item", "IPHONE 13 PRO");