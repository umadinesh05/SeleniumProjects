package umadinu;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import umadinu.TestComponents.BaseTest;
import umadinu.pageobjects.CartPage;
import umadinu.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest  {

	@Test (groups="ErrorHandling")
	
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		
		ProductCatalogue productcatalogue = landingpage.loginApplication("umadines5@gmail.com","Umadinu@82" );
		Assert.assertEquals("Incorrect email password.", landingpage.loginErrorMsg());		
	}
	
	@Test
	
	public void ProductErrorValidation() throws InterruptedException
	{
		String productName = "ZARA COAT 3"; 
		ProductCatalogue productcatalogue = landingpage.loginApplication("umadinesh5@gmail.com","Umadinu@82" );
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCart(); 
		
		Boolean match = cartpage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
	} 

}
