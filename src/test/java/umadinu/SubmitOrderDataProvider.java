package umadinu;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import umadinu.TestComponents.BaseTest;
import umadinu.pageobjects.CartPage;
import umadinu.pageobjects.CheckoutPage;
import umadinu.pageobjects.ConfirmationPage;
import umadinu.pageobjects.OrdersPage;
import umadinu.pageobjects.ProductCatalogue;

public class SubmitOrderDataProvider extends BaseTest  {
 
	String confirmordernumber;	
	@Test(dataProvider="getData", groups= "Purchase")
	
	public void SubmitOrder(String email, String password, String productName) throws IOException, InterruptedException
	{
	//	String productName = "ZARA COAT 3"; 
		ProductCatalogue productcatalogue = landingpage.loginApplication(email,password);
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
		
		ProductCatalogue productcatalogue = landingpage.loginApplication("umadinesh5@gmail.com","Umadinu@82" );
		
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
	
	// Dataprovider annotation will tell the script to retrieve the data from the file provided in the below section
	@DataProvider
	public Object[][] getData()
	{
		// if we want to run two different sets of test data like Username and Pwd, create two dimensional arrays
		// as object datatype which is the parent datatype where we can send int, strings etc
		return new Object[][] {{"umamenon0506@gmail.com","Umadinu@82","ZARA COAT 3" }, {"umadinesh5@gmail.com","Umadinu@82","ADIDAS ORIGINAL"}};		
	}
	
}
