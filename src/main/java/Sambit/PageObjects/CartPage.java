package Sambit.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Sambit.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		
	}

	@FindBy(css = ".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = "li[class='totalRow'] button[type='button']")
	WebElement checkOutButton;
	
	public Boolean verifyProductDisplay(String Item)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Item));
		return match;
	}
	
	public CheckoutPage goToCheckOut()
	{
		checkOutButton.click(); 
		return new CheckoutPage(driver);
	}
}
