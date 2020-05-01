package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactTest {
	BasePage basepage;
	WebDriver driver;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	ContactPage contactPage;

	@BeforeTest
	public void SetUp() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homepage.getContactsLink();
	}

	@Test(priority = 1)
	public void pageTitleTest() {
		String title=contactPage.getContactPageTitle();
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactTestdata() {
		Object data[][]=ExcelUtil.getTestData(Constants.CONTACT_TEST_DATA);
		return data;
	}
	
	@Test(priority=2, dataProvider="getContactsTestData")
	public void contactPageTest(String emailID, String firstname, String lastname, String jobtitle, String phonenumberLink) {
		String name=contactPage.createNewContacts(emailID, firstname, lastname, jobtitle, phonenumberLink);
		Assert.assertEquals(name, firstname+" "+lastname);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
