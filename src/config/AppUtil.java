package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
// Pre conditions and post conditions
public class AppUtil {
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setUp()throws Throwable
	{
		conpro = new Properties();
		
		conpro.load(new FileInputStream("./PropertyFiles/Envinorment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value is Not Matching",true);
		}
	}
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}

}

