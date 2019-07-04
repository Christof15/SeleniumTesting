package Utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserChoice {
WebDriver driver;

/**
 * This function will execute before each Test tag in testng.xml
 *
 * @param browser
 * @return
 * @throws Exception
 */
public WebDriver SelectBrowser(String browser) throws Exception {
	
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);  //Read ObjectRepostory
	String chrome = p.getProperty("Chrome");//Name of Chrome Driver
	String edge = p.getProperty("Edge");//Name of Edge Driver
	String firefox = p.getProperty("Firefox");//Name of Firefox Driver
	String ePath = p.getProperty("ePath");
	String cPath = p.getProperty("cPath");
	String fPath = p.getProperty("fPath");
	//Check if parameter passed from TestNG is 'firefox'
	if (browser.contentEquals("Firefox")) {
		//create firefox instance
		System.setProperty(firefox, fPath);
		driver = new FirefoxDriver();
	}
	//Check if parameter passed as 'chrome'
	else if (browser.contentEquals("Chrome")) {
		//set path to chromedriver.exe
		System.setProperty(chrome, cPath);
		//create chrome instance
		driver = new ChromeDriver();
	}
	//Check if parameter passed as 'Edge'
	else if (browser.contentEquals("Edge")) {
		//set path to Edge.exe
		System.setProperty(edge, ePath);
		//create Edge instance
		driver = new EdgeDriver();
	} else {
		//If no browser passed throw exception
		throw new Exception("Browser is not correct");
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
}
}