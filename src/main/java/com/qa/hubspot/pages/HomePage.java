package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;

	//By header = By.cssSelector("h1.private-page__title");
	By header = By.xpath("(//*[@class='private-header__heading'])[position()=1]");
	By accountName = By.cssSelector("span.account-name ");
	By primaryContactLink = By.id("nav-primary-contacts-branch");
	//By primaryContactLink=By.xpath("(//*[@id='nav-primary-contacts-branch'])[position()=1]");
	By secondaryContactLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);

	}

	public String getHomePageHeaderText() {

		if (elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);

		}
		return null;
	}

	public String getLoggedInUser() {

		if (elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}

		return null;
	}

	public ContactsPage goToContactsPage() {
		ClickContactsPage();
		return new ContactsPage(driver);

	}

	private void ClickContactsPage() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		elementUtil.waitForElementToBeVisible(primaryContactLink, 200);
		elementUtil.doClick(primaryContactLink);
		elementUtil.waitForElementToBeVisible(secondaryContactLink, 200);
		elementUtil.doClick(secondaryContactLink);

	}

}