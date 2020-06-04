package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest extends BaseTest {
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeClass	
	public void contactsSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getConatctsPageTitle();
		System.out.println("contacts page title is : " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE,"Contact page title is not found...");
	}
	
	@Test(priority = 2,enabled = false)
	public void verifyContactsPageHeader() {
		String header = contactsPage.getContactsPageHeader();
		System.out.println("contacts page header is : " + header);
		Assert.assertEquals(header, Constants.CONTACTS_PAGE_HEADER,"Contacts page header is not found...");
	}
	
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACT_SHEET_NAME);
		return data;
	}

	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void createContactTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createContact(email,firstName, lastName, jobTitle);
	}

}
