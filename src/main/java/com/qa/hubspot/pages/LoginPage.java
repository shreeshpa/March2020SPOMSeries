package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	
	//1.  By Locators  --> OR
	   
	   By username=By.id("username");
	   By password=By.id("password");
	   By loginButton=By.id("loginBtn");
	   By signUpLink=By.linkText("Sign up");
	   
	//2. Create constructor of page class
	   
	   public LoginPage(WebDriver driver) {
		   
		   this.driver=driver;
		   
	   }
	 
	  // 3. Page Actions
	   
	   public String getLoginPageTitle() {
		   
		   return driver.getTitle();
	   }
	
       public boolean verifySignUpLink() {
    	   
    	   return driver.findElement(signUpLink).isDisplayed();
       }
       
       public void doLogin(String username, String password) {
    	   
    	   driver.findElement(this.username).sendKeys(username);
    	   driver.findElement(this.password).sendKeys(password);
    	   driver.findElement(this.loginButton).click();
       }
}
