package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author ShreeshPandey
 *
 */

public class BasePage {

	WebDriver driver;
	Properties prop;
	public ElementUtil elementUtil;
	public OptionsManager optionsanager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	
	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */

	public WebDriver init_driver(Properties prop) {
		
		String browserName = null;
		if(System.getProperty("browser") == null){
			browserName = prop.getProperty("browser");
		}else{
			browserName = System.getProperty("browser");
		}
		
		System.out.println("Running on ->"+ browserName + " browser");
		
		optionsanager=new OptionsManager(prop);
		
		//String browserName=prop.getProperty("browser");
		 
         
		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsanager.getChromeOptions()));

		}
		else if (browserName.equalsIgnoreCase("firfox")) {

			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsanager.getFirefoxOptions()));

		}
		
		else if (browserName.equalsIgnoreCase("safari")) {

			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			

		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		
		//driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();

	}
	  /**
	   * this method is used to initialize the properties from confif.properties file on the basis of given env variable
	   * @return prop
	   */
       
	   public Properties init_prop() {
		   prop=new Properties();
		   String path = null;
		   String env=null;
		   
		   try {
			   env=System.getProperty("env");
			   System.out.println("env value is--->" + env);
			   if (env==null) {
				path="./src/main/java/com/qa/hubspot/config/config.properties";
			}
			   else {
				switch (env) {
				case "qa":
					path="./src/main/java/com/qa/hubspot/config/qa.config.properties";
					break;
				case "dev":
					path="./src/main/java/com/qa/hubspot/config/dev.config.properties";
					break;
				case "stage":
					path="./src/main/java/com/qa/hubspot/config/stage.config.properties";
					break;	

				default:
					System.out.println("Please pass the correct environment value-->" +env);
					break;
				}
			}
			FileInputStream ip=new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return prop;
		   
	   }
	  
	  /**
	   *  this method will take screenshot
	 * @return 
	   */
	   
	   public String getScreenshot() {
		    File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		    String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		    File destination=new File(path);
		    try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return path;
	   }

}
