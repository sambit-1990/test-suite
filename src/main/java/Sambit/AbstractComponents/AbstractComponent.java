package Sambit.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Sambit.PageObjects.CartPage;
import Sambit.PageObjects.OrdersPage;

public class AbstractComponent 
{
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersButton;
	 
	public void waitForElementToAppear(By FindBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	
	public OrdersPage goToOrdersPage()
	{
		ordersButton.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
		
	}
}
