package TestSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Report.MyReport;
import Utilities.DataProviderUtility;
import Utilities.Screenshot;

public class Overview extends MyReport {

public WebDriver driver;

@BeforeTest
@Parameters("browser")
public void SelectBrowser(String browser) throws Exception {
	
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ObjectRepository\\ObjectRepository.properties");
	FileReader obj = new FileReader(f);
	p.load(obj);  //Read ObjectRepostory
	String chrome = p.getProperty("Chrome");//Name of Chrome Driver
	String edge = p.getProperty("Edge");//Name of Edge Driver
	String firefox = p.getProperty("Firefox");//Name of Firefox Driver
	String ePath = p.getProperty("ePath");
	String cPath = p.getProperty("cPath");
	String fPath = p.getProperty("fPath");
	
	//Check if parameter passed from TestNG is 'firefox'
	if (browser.equalsIgnoreCase("Firefox")) {
		//create firefox instance
		System.setProperty(firefox, fPath);
		driver = new FirefoxDriver();
	}
	//Check if parameter passed as 'chrome'
	else if (browser.equalsIgnoreCase("Chrome")) {
		//set path to chromedriver.exe
		System.setProperty(chrome, cPath);
		//create chrome instance
		driver = new ChromeDriver();
	}
	//Check if parameter passed as 'Edge'
	else if (browser.equalsIgnoreCase("Edge")) {
		//set path to Edge.exe
		System.setProperty(edge, ePath);
		//create Edge instance
		driver = new EdgeDriver();
	} else {
		//If no browser passed throw exception
		throw new Exception("Browser is not correct");
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

@Test
public void Login() throws IOException, InterruptedException {
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ObjectRepository\\ObjectRepository.properties");
	FileReader obj = new FileReader(f);
	p.load(obj);
	String url = p.getProperty("ProdUrl");
	DataProviderUtility dp = new DataProviderUtility();
	int TimeSeconds = Integer.parseInt((p.getProperty("TimeSeconds")));
	String username2 = p.getProperty("Username_TxtBox");
	String password2 = p.getProperty("Password_TxtBox");
	String login = p.getProperty("Login_Btn");
	String OverviewDashboard = p.getProperty("OverviewDashboard");
	String firefox = p.getProperty("Firefox");//Name of Firefox Driver
	String fPath = p.getProperty("fPath");
	System.setProperty(firefox, fPath);
	FirefoxDriver d = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(d, TimeSeconds);
	d.manage().timeouts().implicitlyWait(TimeSeconds, TimeUnit.SECONDS);
	d.get(url);
	d.findElementByXPath(username2).sendKeys("qatester01");
	d.findElementByXPath(password2).sendKeys("QATesting456!");
	d.findElementByXPath(login).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OverviewDashboard)));
	Screenshot s= new Screenshot();
	s.ScreenShot(d);
	test = extent.createTest("Login");
}
	
	
}
