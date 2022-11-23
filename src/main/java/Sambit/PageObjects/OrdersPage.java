package Sambit.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Sambit.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent
{
	WebDriver driver;
	public OrdersPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		
	}

		
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productnames;
	
	public Boolean verifyOrderDisplay(String Item)
	{
		
		Boolean match = productnames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Item));
		return match;
	}
	
	
}
