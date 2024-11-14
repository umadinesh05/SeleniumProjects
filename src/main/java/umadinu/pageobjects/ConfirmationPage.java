package umadinu.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import umadinu.AbstractComponents.AbstarctComponents;

public class ConfirmationPage extends AbstarctComponents {
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".hero-primary")
	WebElement ConfirmMsg;
			
	@FindBy(css = "label[class='ng-star-inserted']")
	WebElement Ordernumber;
	
	public String verifyConfirmationMsg()
	{
		return ConfirmMsg.getText();
	}
	
	public String getconfirmPageOrderNumber()
	{
		return Ordernumber.getText().replaceAll("[^a-zA-Z0-9]+","");

	//	return Ordertrim;
		
		
	}

	
}
