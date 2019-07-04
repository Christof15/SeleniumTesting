package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import Report.MyReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class NewTest extends MyReport {

public WebDriver driver;

@Test
public void openMyBlog() {
	driver.get("https://www.softwaretestingmaterial.com");
	test = extent.createTest("WebPage test");
}


@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\augustog\\Desktop\\Automation\\geckodriver-v0.24.0-win64\\geckodriver.exe");
	driver = new FirefoxDriver();
}

@AfterClass
public void afterClass() {
	driver.quit();
}

}