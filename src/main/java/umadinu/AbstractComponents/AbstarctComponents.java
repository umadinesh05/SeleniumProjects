package umadinu.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import umadinu.pageobjects.CartPage;
import umadinu.pageobjects.OrdersPage;

public class AbstarctComponents {

	WebDriver driver;
	
	// In parent class to catch that driver, we need to create a constructor as only constructors can catch the variables
	
	public AbstarctComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);  // need to initialize initElements from PageFactory Class
	}
	
	@FindBy(css = ("[routerlink*='cart']"))
	WebElement cartHeader;
	
	
	@FindBy(css = ("[routerlink*='orders']"))
	WebElement order;
	
	//li:nth-child(5) button:nth-child(1)
	
	@FindBy(css = ("li:nth-child(5) button:nth-child(1)"))
	WebElement SignOut;
	
//	@FindBy(xpath = "//button[@type='button'])[2]")
	//WebElement selectIndia;

	public void waitForElementsToAppear(By findBy) throws InterruptedException
	{
//	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));   //By.cssSelector("#toast-container")	
	}
	
	public void waitForWebElementsToAppear(List<WebElement> ele) throws InterruptedException
	{

	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	
	public void waitForWebElementToAppear(WebElement elem) throws InterruptedException
	{

	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(elem));
	}
	
	public void waitForElementsToDisAppear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrdersPage gotToOrder()
	{
		order.click();
		OrdersPage orderpage = new OrdersPage(driver);
		return orderpage;
		
	}
	
	public void signOut()
	{
		SignOut.click();
		
	}
	
}
