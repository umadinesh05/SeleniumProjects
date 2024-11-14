package umadinu.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class CartPage extends AbstarctComponents {
	
	// In order to give live to driver class we need to create the constructor which gets initiated before any 
	// method gets initiated
//	 String productName = "ZARA COAT 3";
	WebDriver driver;
	
	public CartPage(WebDriver driver)  // we can catch the arguments in the constructor
	{
		super(driver);
		this.driver = driver;  // this class refers to the local driver class
		PageFactory.initElements(driver, this);  // need to initialize initElements from PageFactory Class
	} 
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProductLists;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;
	
	public Boolean verifyCartProductDisplay(String productName)
	{
		Boolean match = cartProductLists.stream().anyMatch(cartproduct->
		cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage checkOutProduct()
	{
		checkOutEle.click();
		return new CheckoutPage(driver);  // CheckoutPage object is created which can be called in test
	}

}
