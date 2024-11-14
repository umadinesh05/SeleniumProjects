package umadinu.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import umadinu.AbstractComponents.AbstarctComponents;
import umadinu.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
/*	public BaseTest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} */

	
	
	public WebDriver InitializeDriver() throws IOException
	{
		// Properties class is used to retrieve the data .properties file and can be used globally
		
		Properties prop = new Properties();
		// the data can only be sent through input stream i.e file has to be sent through Input stream 
		// created object for fileinputstream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/umadinu/resources/GlobalData.properties");  
		prop.load(fis);
		String browserName = prop.getProperty("browser"); //C:/Users/umadi/eclipse-workspace/SeleniumFrameWorkDesign/
		
		if(browserName.equalsIgnoreCase("chrome")){
				
	WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();
	
	}
		else if (browserName.equalsIgnoreCase("edge")){
			System.setProperty("Webdriver.edge.driver", "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs");
			 driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read json to string // UTF_8 is for what encoding format we want to write the string
		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	
		  //Convert String to HashMap through Jackson Databind (need to add the dependency in pom.xml file)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	} 
	
	// For ScreenShot
		public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
		{
			// having driver to switch to the photo mode- we cannot create object as it is an Interface, so we need to typecast the webdriver in the takescreenshot interface
			TakesScreenshot ts = (TakesScreenshot)driver; //utility to take a screenshot
			File source = ts.getScreenshotAs(OutputType.FILE);  //need screenshot as file format
			File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
			FileUtils.copyFile(source , file);
			return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
			
		}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = InitializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.GoTo("https://rahulshettyacademy.com/client");
		return landingpage;			
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}
}

