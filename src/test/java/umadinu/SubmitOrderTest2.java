package umadinu;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import umadinu.TestComponents.BaseTest;
import umadinu.pageobjects.CartPage;
import umadinu.pageobjects.CheckoutPage;
import umadinu.pageobjects.ConfirmationPage;
import umadinu.pageobjects.LandingPage;
import umadinu.pageobjects.OrdersPage;
import umadinu.pageobjects.ProductCatalogue;

public class SubmitOrderTest2 extends BaseTest  {

	String confirmordernumber;
	@Test
	
	public void SubmitOrder() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3"; 
		ProductCatalogue productcatalogue = landingpage.loginApplication("umamenon0506@gmail.com","Umadinu@82" );
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCart(); 
		
		Boolean match = cartpage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.checkOutProduct();
		
		checkoutpage.selectCountry("India", "234");
		ConfirmationPage confirmpage = checkoutpage.placeOrder();
		String msg = confirmpage.verifyConfirmationMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		confirmordernumber = confirmpage.getconfirmPageOrderNumber();
		confirmpage.signOut();
	}
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrdersPage() throws InterruptedException
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication("umamenon0506@gmail.com","Umadinu@82" );
		
		//once it goes to order page, it create an object for the orderpage which is written in Abstarct class
		OrdersPage orderpage = productcatalogue.gotToOrder();
	    
	//	confirmpage.gotToOrder();
	//    OrdersPage orderspage = orderpage.gotToOrder();
	//	OrdersPage orderspage = new OrdersPage(driver);
		List<WebElement> orderlist = orderpage.getOrdersList();
	    String orderpagenumber = orderpage.getOrderNumberText();
		Assert.assertEquals(confirmordernumber, orderpagenumber);
		orderpage.signOut();	
	}
	


}
