package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtil;

/**
 * 
 * @author Abhishek Kumar
 *
 */
public class LoginPage extends BasePage {
	WebDriver driver;
	ElementUtil elementutil;
	By username = By.id("username");
	By password = By.id("password");
	By signinbtn = By.id("loginBtn");
	By signup = By.linkText("Sign up");
	By showpwd = By.xpath("//span[text()='Show Password']");
	By forgotpwd = By.xpath("//i18n-string[text()='Forgot my password']");
	By rememberme = By.xpath("//i18n-string[text()='Remember me']");

	/**
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil=new ElementUtil(driver);
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return elementutil.getTitle();
	}

	/**
	 * 
	 * @return
	 */
	public boolean signUpLink() {
		return elementutil.doIsDisplayed(signup);
	}

	/**
	 * 
	 * @return
	 */
	public boolean showpwdLink() {
		return elementutil.doIsDisplayed(showpwd);
	}

	/**
	 * 
	 * @return
	 */
	public boolean forgotpwdLink() {
		return elementutil.doIsDisplayed(forgotpwd);
	}

	/**
	 * 
	 * @return
	 */
	public boolean remembermeLink() {
		return elementutil.doIsDisplayed(rememberme);
	}

	/**
	 * 
	 * @param usrname
	 * @param pwd
	 */
	public HomePage doLogin(String usrname, String pwd) {
		elementutil.doSendKeys(username, usrname);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(signinbtn);

		return new HomePage(driver);
	}

}
