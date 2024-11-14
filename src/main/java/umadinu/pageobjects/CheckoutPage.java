package umadinu.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class CheckoutPage extends AbstarctComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".ta-item:last-of-type")
	WebElement selectIndia;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")  //css = input[placeholder='Select Country']
	WebElement SelectCountry;
	
	@FindBy(css = ".form__cc div:nth-child(2) div:nth-child(2) input")
	WebElement Cvv;
	
	@FindBy(css = ".action__submit")
	WebElement PlaceOrder;
	
	By countrSearchResults = By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryName, String cvvValue) throws InterruptedException
	{
		Actions a = new Actions(driver);  // Action class
		a.sendKeys(SelectCountry, countryName).build().perform();
		a.sendKeys(Cvv, cvvValue).build().perform();
		// below code is waiting for all the searches to appear after entering the country India, capturing 
		waitForElementsToAppear(countrSearchResults);
//		selectIndia.click();
		a.moveToElement(selectIndia).click().perform();
		
	}
	
	public ConfirmationPage placeOrder()
	{
		
		PlaceOrder.click();
		return new ConfirmationPage(driver);
	} 

}
