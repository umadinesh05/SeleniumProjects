package umadinu;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;

public class WindowsPopUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	String username = "amdin";
		//String password = "admin";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://admin:admin@the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Basic Auth")).click();
	//	driver.get("https://the-internet.herokuapp.com/basic_auth");
	//	JavascriptExecutor js = (JavascriptExecutor)driver;
	//	js.executeScript("alert(username, password)");
	//	driver.switchTo().alert().sendKeys("admin");
		//driver.switchTo().alert().sendKeys("admin");
	//	driver.close();

	}

}
