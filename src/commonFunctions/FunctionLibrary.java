package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean adminLogin(String user,String pass) throws Throwable
{
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
	Thread.sleep(2000);
	String Expected ="dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("Login success::"+Expected+"        "+Actual,true);
		//click logout link
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		return true;
	}
	else
	{
	String error_Message = driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
	Thread.sleep(2000);
	driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
	Reporter.log(error_Message+"     "+Expected+"        "+Actual,true);
	return false;
	}
	
	
}
}
