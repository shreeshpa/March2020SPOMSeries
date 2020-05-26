package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author ShreeshPandey
 *
 */

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */

	public WebDriver init_driver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		else if (browserName.equalsIgnoreCase("firfox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		
		else if (browserName.equalsIgnoreCase("safari")) {

			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();

		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://app.hubspot.com/login");
		
		return driver;

	}
	  /**
	   * this method is used to initialize the properties from confif.properties file
	   * @return prop
	   */
       
	   public Properties init_prop() {
		   prop=new Properties();
		   
		   try {
			FileInputStream ip=new FileInputStream("/src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return prop;
		   
	   }

}
