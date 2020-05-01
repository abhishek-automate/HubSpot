package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest {
	BasePage basepage;
	WebDriver driver;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;

	@BeforeTest
	public void SetUp() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyHomePageTitle() {
		String title=homepage.gettitlePage();
		System.out.println("HomePage title is "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
