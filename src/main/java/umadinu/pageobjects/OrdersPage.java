package umadinu.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class OrdersPage extends AbstarctComponents {
	
WebDriver driver;
String confirmordernumber;
	
	public OrdersPage(WebDriver driver)  // we can catch the arguments in the constructor
	{
		super(driver);
		this.driver = driver;  // this class refers to the local driver class
		PageFactory.initElements(driver, this);  // need to initialize initElements from PageFactory Class
	}

	@FindBy(xpath = "//table[@class='table table-bordered table-hover ng-star-inserted']")
	List<WebElement> orders;
	
	@FindBy(css = "tbody tr:nth-child(1) th:nth-child(1)")
	WebElement Orderno;
	
	By ordersBy = By.xpath("//table[@class='table table-bordered table-hover ng-star-inserted']");
	
	public List<WebElement> getOrdersList() throws InterruptedException
	{
		waitForElementsToAppear(ordersBy);
		return orders;
	}

	
	public String getOrderNumberText()
	{
      
		return Orderno.getText();
    
	
	}
   }
