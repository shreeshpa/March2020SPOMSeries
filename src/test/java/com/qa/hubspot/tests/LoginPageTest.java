package com.qa.hubspot.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.scanner.Constant;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.init_driver("chrome");
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 2)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "login page title is not matched...");
	}

	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(),"sing up link is not displayed....");
	}

	@Test(priority = 3)
	public void loginTest() {
		loginPage.doLogin("shreesh.pandey@predcred.com", "Shreesh@321");
	}	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
