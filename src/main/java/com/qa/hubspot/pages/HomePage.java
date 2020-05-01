package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.WaitUtil;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementutil;
	WaitUtil waitUtil;
	By accName = By.cssSelector("span.account-name ");
	By pageTitle = By.className("private-page__title");
	By primaryContactsLink = By.id("nav-primary-contacts-branch");
	By secondaryContactsLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}

	public String gettitlePage() {
		return elementutil.getTitle();
	}

	public String getHomePageHeader() {
		if (elementutil.doIsDisplayed(pageTitle)) {
			return elementutil.doGetText(pageTitle);
		}
		return null;
	}

	public ContactPage getContactsLink() {
		getClickOnContacts();
		return new ContactPage(driver);
	}

	private void getClickOnContacts() {
		waitUtil.getElementByExpectedWait(primaryContactsLink, 10);
		elementutil.doClick(primaryContactsLink);
		waitUtil.getElementByExpectedWait(secondaryContactsLink, 10);
		elementutil.doClick(secondaryContactsLink);

	}

	public String getAccountName() {
		if (elementutil.doIsDisplayed(accName)) {
			return elementutil.doGetText(accName);
		}
		return null;
	}

}
