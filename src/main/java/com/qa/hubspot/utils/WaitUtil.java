package com.qa.hubspot.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Abhishek Kumar
 *
 */
public class WaitUtil {
	WebDriver driver;
	ElementUtil eu;

	// Implicitly wait is global wait
	// it will be applied for all the WebElement by default
	// Implicitly wait is only for WebElement
	// Explicitly wait will trigger again in 0.5 seconds
	// Explicitly wait works for every thing including url,title and WebElement
	// Fluent wait is generally used for handling Ajax Components,
	/**
	 * Constructor of WaitUtil Class for driver
	 * 
	 * @param driver
	 */
	public WaitUtil(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtil(driver);
	}

	/**
	 * Wait for Implicitly wait
	 * 
	 * @param time
	 * @param unit
	 */

	public void implicitlyWait(long time, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

	/**
	 * Explicit Wait method to Verify title contains a case-sensitive substring
	 * 
	 * @param timeout
	 * @param title
	 * @return
	 */
	public String explicitlyWaitTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return eu.getTitle();
	}

	/**
	 * Explicit Wait method to check for Presence of element by locator returns
	 * Element
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementsLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return eu.getElement(locator);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement getElementByExpectedWait(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return eu.getElement(locator);
	}

	/**
	 * Explicit wait for Element to be visible and enabled so that it can be
	 * clickable.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebElement element = eu.getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	/**
	 * Explicit wait for Checking that an element is present on the DOM of page and
	 * visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return eu.getElement(locator);
	}

	/**
	 * Explicit Wait for expectation of the URL of current page to contain specific
	 * text.
	 * 
	 * @param url
	 * @param timeout
	 * @return
	 */
	public String waitForURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}

	/**
	 * Explicit wait for verifying an Alert to present return is always true so if
	 * it returns False throw exception
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public boolean waitForAlertTobePresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}

	/**
	 * Explicit Wait for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return eu.getElement(locator);
	}

	/**
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementToBeVisbile(By locator, int timeout) {
		WebElement element = eu.getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	/**
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> visiblilityOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
		return driver.findElements(locator);
	}

	/**
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		eu.getElement(locator).click();
	}

	/**
	 * This is Fluent wait used for return element for locator this will wait for
	 * 15seconds and check in every 3second in between exception found will ignore
	 * Used Concept of anonymous inner class to define WebElement type of element
	 * Note:- If any method is returning WebElement we use on that WebElement
	 * Methods
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement waitForElementFluentWait(WebDriver driver, final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	/**
	 * This is Fluent wait with Expected Conditions simplified form of Fluent wait
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement waitForElementFluenWait(WebDriver driver, final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
