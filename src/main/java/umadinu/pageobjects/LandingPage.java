package umadinu.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents{
	
	// In order to give life to driver class we need to create the constructor which gets initiated before any 
	// method gets initiated
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)  // we can catch the arguments in the constructor
	{
		//from child to parent we can send a variable through super class keyword
		super(driver);  //super keyword is used to call superclass methods, and to access the superclass constructor.
		this.driver = driver;  // this class refers to the local driver class
		PageFactory.initElements(driver, this);
	}
	
	public void GoTo(String url)
	{
		driver.get(url);
	}
	
	// There is a design pattern called PageFactory, by using that we can reduce the number of lines of code for creating the WebElement
	@FindBy(id="userEmail")  // Instead of WebElement userEmail=driver.findElement(By.id("userEmail"));
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCatalogue loginApplication(String email, String password)  // the values will be sent in the test case i.e SubmitOrderTest
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
		
	}
	
	public String loginErrorMsg() throws InterruptedException
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
}
