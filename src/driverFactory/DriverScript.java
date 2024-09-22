package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
ExtentReports reports;
ExtentTest logger;
String inputpath="./FileInput/TestData.xlsx";
String outputpath ="./FileOutput/DataDrivenResults.xlsx";
@Test
public void startTest() throws Throwable
{
	reports = new ExtentReports("./Reports/Login.html");
	
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	
	int rc =xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("Login Test");
		logger.assignAuthor("Ranga");
		
		String username = xl.getCellData("Login", i, 0);
		String password = xl.getCellData("Login", i, 1);
		logger.log(LogStatus.INFO,username+"     "+password);
		
		boolean res =FunctionLibrary.adminLogin(username, password);
		if(res)
		{
			
			xl.setCellData("Login", i, 2, "Login success", outputpath);
		
			xl.setCellData("Login", i, 3, "pass", outputpath);
			logger.log(LogStatus.PASS, "Valid Username and password");
		}
		else
		{
			
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Login.png"));
			
			xl.setCellData("Login", i, 2, "Login Fail", outputpath);
			
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			logger.log(LogStatus.FAIL, "Invalid username and password");
		}
		
		reports.endTest(logger);
		reports.flush();
		
	}


}
}