package umadinu;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import umadinu.pageobjects.CartPage;
import umadinu.pageobjects.CheckoutPage;
import umadinu.pageobjects.ConfirmationPage;
import umadinu.pageobjects.LandingPage;
import umadinu.pageobjects.OrdersPage;
import umadinu.pageobjects.ProductCatalogue;

public class SubmitOrderTest  {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
	//	String confirmordernumber;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// launching the URL by initializing the object for the LandingPage class 
		LandingPage landingpage = new LandingPage(driver); // created an object for the class and passing driver in the test 
		
		// Calling methods using object for LadingPage class
		 // launching the URL
		landingpage.GoTo("https://rahulshettyacademy.com/client");  
		
		// Log into the Application
		ProductCatalogue productcatalogue = landingpage.loginApplication("umamenon0506@gmail.com","Umadinu@82" );  
		
		// creating the object for ProductCatalogue Class to have the access to the methods
	//	ProductCatalogue productcatalogue = new ProductCatalogue(driver); //
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
		// since productcatalogue child class is inheriting the Absrarct class, so child class
		//can access the method in parent class
		CartPage cartpage = productcatalogue.goToCart(); 
		
//		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.checkOutProduct();
		
//		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry("India", "234");
		
		//Instead of ConfirmationPage confirmpage = new ConfirmationPage(driver);
		ConfirmationPage confirmpage = checkoutpage.placeOrder();
		String msg = confirmpage.verifyConfirmationMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	    String confirmordernumber = confirmpage.getconfirmPageOrderNumber();
	//	Assert.assertTrue(ordernumber.contentEquals(ordernumber))
		confirmpage.gotToOrder();
		
		OrdersPage orderspage = new OrdersPage(driver);
		List<WebElement> orderlist = orderspage.getOrdersList();
	    String orderpagenumber = orderspage.getOrderNumberText();
		Assert.assertEquals(confirmordernumber, orderpagenumber);
	//	List<WebElement> Orders = confirmpage.getOrdersList();
		driver.close();
		
	}

}
