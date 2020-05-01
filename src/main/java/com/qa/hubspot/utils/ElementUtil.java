package com.qa.hubspot.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author Abhishek Kumar
 *
 */
public class ElementUtil {
	WebDriver driver;
	JavaScriptUtil jsUtil;

	/**
	 * 
	 * @param driver
	 */
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		jsUtil.flash(element);
		return element;
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		WebElement target = getElement(locator);
		action.sendKeys(target, value).perform();
	}

	/**
	 * 
	 * @param locator
	 */
	public void doActionClick(By locator) {
		Actions action = new Actions(driver);
		WebElement target = getElement(locator);
		action.click(target).perform();
	}

	/**
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

}
