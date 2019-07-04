package TestCases;

import Utilities.BrowserChoice;
import Utilities.Constants;
import Utilities.DataProviderUtility;
import TestSuite.Login;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Report.MyReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class Login_TC extends MyReport {

public WebDriver d;
BrowserChoice b = new BrowserChoice();

//Obtain the TestData for the User and the Password from the DataProvider called "SourceData"
@Test(dataProvider = "SourceData", dataProviderClass = DataProviderUtility.class, threadPoolSize = 8)
public void LoginTest(String user, String pass) throws IOException, InterruptedException {
	Login login = new Login(user, pass);
	test = extent.createTest("Login " + user + " ");
}

//Obtain the TestData for the User and the Password from the DataProvider called "SourceData"
@Test(dataProvider = "SourceData", dataProviderClass = DataProviderUtility.class, threadPoolSize = 8)
public void Logout(String user, String pass) throws Exception {
	Login logout = new Login(user, pass);
	logout.Logout(user, pass);
	test = extent.createTest("Logout " + user + " ");
}

@Test(dataProvider = "BrowserData", dataProviderClass = DataProviderUtility.class, threadPoolSize = 8)
public void LoginBrowserTest(String user, String pass, String BrowserName, String Driverf, String DriverName, String DriverPath) throws Exception {
	d = b.SelectBrowser(BrowserName);
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);
	String url = p.getProperty("ProdUrl");
	int TimeSeconds = Integer.parseInt((p.getProperty("TimeSeconds")));
	String username2 = p.getProperty("Username_TxtBox");
	String password2 = p.getProperty("Password_TxtBox");
	String login = p.getProperty("Login_Btn");
	String OverviewDashboard = p.getProperty("OverviewDashboard");
	WebDriverWait wait = new WebDriverWait(d, TimeSeconds);
	d.manage().timeouts().implicitlyWait(TimeSeconds, TimeUnit.SECONDS);
	d.get(url);
	d.findElement(By.xpath(username2)).sendKeys(user);
	d.findElement(By.xpath(password2)).sendKeys(pass);
	d.findElement(By.xpath(login)).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OverviewDashboard)));
	Screenshot s= new Screenshot();
	s.ScreenShot(d);
	d.close();
	test = extent.createTest("Browser" + "  " + user + "   " + BrowserName);
}

@BeforeClass
public void beforeClass() {
	System.setProperty(Constants.FIREFOX, Constants.FPATH);
	d = new FirefoxDriver();
	d.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
}

@AfterClass
public void afterClass() {
	d.quit();
}

}
