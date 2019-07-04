package TestSuite;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Screenshot;

public class Login {

public Login(String username, String password) throws IOException, InterruptedException {
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
	String firefox = p.getProperty("Firefox");//Name of Firefox Driver
	String fPath = p.getProperty("fPath");
	System.setProperty(firefox, fPath);
	FirefoxDriver d = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(d, TimeSeconds);
	d.manage().timeouts().implicitlyWait(TimeSeconds, TimeUnit.SECONDS);
	d.get(url);
	d.findElementByXPath(username2).sendKeys(username);
	d.findElementByXPath(password2).sendKeys(password);
	d.findElementByXPath(login).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OverviewDashboard)));
	Screenshot s= new Screenshot();
	s.ScreenShot(d);
	d.close();
}

public void Logout(String username, String password) throws IOException, InterruptedException {
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);
	String url = p.getProperty("ProdUrl");
	int TimeSeconds = Integer.parseInt((p.getProperty("TimeSeconds")));
	String username2 = p.getProperty("Username_TxtBox");
	String password2 = p.getProperty("Password_TxtBox");
	String login = p.getProperty("Login_Btn");
	String logout = p.getProperty("Logout_Btn");
	String customer = p.getProperty("customer");
	String OverviewDashboard = p.getProperty("OverviewDashboard");
	String firefox = p.getProperty("Firefox");//Name of Firefox Driver
	String fPath = p.getProperty("fPath");
	System.setProperty(firefox, fPath);
	FirefoxDriver d = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(d, TimeSeconds);
	d.manage().timeouts().pageLoadTimeout(TimeSeconds, TimeUnit.SECONDS);
	d.manage().timeouts().implicitlyWait(TimeSeconds, TimeUnit.SECONDS);
	d.get(url);
	d.findElementByXPath(username2).sendKeys(username);
	d.findElementByXPath(password2).sendKeys(password);
	d.findElementByXPath(login).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OverviewDashboard)));
	Screenshot s= new Screenshot();
	s.ScreenShot(d);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(customer)));
	d.findElementByXPath(logout).click();
	wait.until(ExpectedConditions.urlContains(url));
	s.ScreenShot(d);
	d.close();
}
	
}