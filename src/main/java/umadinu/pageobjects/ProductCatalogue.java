package umadinu.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class ProductCatalogue extends AbstarctComponents {
	
	// In order to give live to driver class we need to create the constructor which gets initiated before any 
	// method gets initiated
//	 String productName = "ZARA COAT 3";
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)  // we can catch the arguments in the constructor
	{
		super(driver);
		this.driver = driver;  // this class refers to the local driver class
		PageFactory.initElements(driver, this);  // need to initialize initElements from PageFactory Class
	}
	
	
	// There is a design pattern called PageFactory, by using that we can reduce the number of lines of code for creating the WebElement
	
	@FindBy(css =".mb-3")
	List<WebElement> products;
	
	@FindBy(css =".ng-animating")
	WebElement animating;
	
//	@FindBy(css =".mb-3 div:nth-child(2) button:last-of-type")
	//By addToCart;
	
	// since we are sending the locator with By class in Abstarct class for elementtoappear, we cant use PageFactory for ByClass, its only used for driver class for the whole page
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type"); // this is for add to cart locator using By class
	//(//button[contains(text(),'Add To Cart')])
	
//	By addToCart = By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]");
	By cartAddedSuccessfullyMsg = By.cssSelector("#toast-container");
//	By animating = By.cssSelector(".ng-animating");
	
	
	// this Action method is to get all the product list and click on the AddtoCart 
	public List<WebElement> getProductList() throws InterruptedException
	{
		waitForElementsToAppear(productsBy);
		return products;
	}
	
	// After retrieving all the products, selecting a certain product - Zara Coat 3
	public WebElement getProductByName(String productName)  throws InterruptedException
	{
		// stream class is used to iterate through the different products, then next filter is used 
		// to choose the Zara Coat 3 product and getting the text and validating or if not found, return null.
		// here webelement "b" is the tag name for all the product name
		
		WebElement prod = getProductList().stream().filter(products->
		products.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	// the below method is clicking on the AddtoCart button, so first it will go getProductByName method  by saving the particular product using getProdcutByName
	//and saving in one web element
  }
	public CartPage addProductToCart(String productName) throws InterruptedException 
	{
		WebElement prod = getProductByName(productName);
	//	System.out.println("WebElement Prod is:", prod.getText());
		prod.findElement(addToCart).click();
	//	prod.findElement(By.cssSelector("//div[@class='container']//div[1]//div[1]//div[1]//button[2]")).click();
		waitForElementsToAppear(cartAddedSuccessfullyMsg); // we are using the Abstarct class method 
		waitForElementsToDisAppear(animating);
		return new CartPage(driver);
	}

	
}
