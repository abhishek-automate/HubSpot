package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;
import com.qa.hubspot.utils.WaitUtil;

public class ContactPage {
	WebDriver driver;
	ElementUtil elementUtil;
	WaitUtil waitUtil;
	JavaScriptUtil jsUtil;

	By createContactLink = By.xpath("//span[text()='Create contact']");
	By createContactFormLink = By.xpath("(//span[text()='Create contact'])[2]");

	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='firstname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	By phoneNumberLink = By.xpath("//input[@data-field='phone']");
	By contactsNavigationLink = By.xpath("(//i18n-string[text()='Contacts'])[2]");

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getContactPageTitle() {
		return waitUtil.explicitlyWaitTitle(10, Constants.CONTACTS_PAGE_TITLE);
	}

	public String createNewContacts(String emailID, String firstname, String lastname, String jobtitle, String phonenumberLink) {
		waitUtil.waitForElementToBeClickable(createContactLink, 5);
		elementUtil.doClick(createContactLink);
		waitUtil.waitForElementToBeVisbile(email, 5).sendKeys(emailID);
		waitUtil.waitForElementToBeVisbile(firstName, 5).sendKeys(firstname);
		waitUtil.waitForElementToBeVisbile(lastName, 5).sendKeys(lastname);
		waitUtil.waitForElementToBeVisbile(jobTitle, 5).sendKeys(jobtitle);
		waitUtil.waitForElementToBeVisbile(phoneNumberLink, 5).sendKeys(phonenumberLink);	
		waitUtil.waitForElementToBeClickable(createContactFormLink, 5);
		jsUtil.clickElementByJS(elementUtil.getElement(createContactFormLink));
		By nameXpath=By.xpath("(//span[text()='"+firstname+" "+lastname+"'])[2]");
		waitUtil.waitForElementToBeVisbile(contactsNavigationLink, 10);
		String name=elementUtil.doGetText(nameXpath).trim();
		//String fullname=firstname +" "+lastname;
		//String nameXpath="(//span[text()='"+firstname+" "+lastname+"'])[2]";
		//String contactName=elementUtil.doGetText(By.xpath(nameXpath)).trim();
		waitUtil.waitForElementToBeVisbile(contactsNavigationLink, 10);
		elementUtil.doClick(contactsNavigationLink);
		return name;
	}

}
