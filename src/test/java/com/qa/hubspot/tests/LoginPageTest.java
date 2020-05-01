package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;

//pre condition --> Test --> actual vs expected --> post step
//@BeforeTest -->   @Test --> Assert   --> @AfterTest
//browser, url --> test google title --> Google vs Google --> close browser
//Note:- Assert is a class coming from or available in TestNg
//Note:- if Static Method/Variable defined in another class/Package can be called using classname.method/Variable

//-------------------------------------------------------------------------------------------------------
//-------------Rules of Page Object Model----------------------------------------------------------------
//Article to follow:- https://martinfowler.com/bliki/PageObject.html
//Note:- According to page object model every page class should have the respective By locators and the page actions. And every test class should have respective methods. 
//Short:- Inside Test class we should avoid driver API's/ Methods(Simon Stewart)
//Note:- Assertion should only be added inside Test class not inside Page Class
public class LoginPageTest {
	BasePage basepage; // BasePage reference created to be used throughout class
	WebDriver driver;
	LoginPage loginpage;
	Properties prop;

	@BeforeTest
	@Description
	public void setUp() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginpage.getTitle();
		System.out.println("Login Page title is  :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginpage.signUpLink());
	}

	@Test(priority = 3)
	public void doLoginTest() {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 4)
	public void showpwdTest() {
		Assert.assertTrue(loginpage.showpwdLink());
	}

	@Test(priority = 5)
	public void forgotpwdTest() {
		Assert.assertTrue(loginpage.forgotpwdLink());
	}

	@Test(priority = 6)
	public void remembermeTest() {
		Assert.assertTrue(loginpage.remembermeLink());
	}

	@AfterTest
	public void testDownTest() {
		driver.quit();
	}

}
