package com.qa.hubspot.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;
	

	/**
	 * 
	 * @param driver
	 */
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method is written using JavaScript to highlight element in color which
	 * we pass in this method and element passed will be highlighted with color code
	 * provided
	 * 
	 * @param element
	 */
	public void flash(WebElement element) {
		// JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 20; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	/**
	 * This method is Private created to be used within class to be used for flash
	 * method parameter color and element used in Flash method
	 * 
	 * @param color
	 * @param element
	 */
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Javascript method is used to draw 3pixel solid red border on the WebElement
	 * passed as parameter
	 * 
	 * @param element
	 */
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	/**
	 * Javascript method to be used for generate Alert in WebPage
	 * 
	 * @param message
	 */
	public void generateAlert(String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");
	}

	/**
	 * JavaScript method to be used for Clicking WebElement takes element as
	 * parameter
	 * 
	 * @param element
	 */
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}

	/**
	 * Method To refresh WebPage if sometimes Selenium Refresh method is not
	 * responding we can use this method as it directly communicates to DOM this
	 * will work
	 */
	public void refreshBrowserByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	/**
	 * Method to get title of WebPage sometimes if DOM is not responding for
	 * Selenium GetTitle() method this can be used as it directly communicates to
	 * webpage DOM
	 * 
	 * @return
	 */
	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	/**
	 * Method to use get all text available in a WebPage it may be used for
	 * WebScrapping
	 * 
	 * @return
	 */
	public String getPageInnerText() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	/**
	 * Method is created using Javascript it scroll down webpage to bottom of
	 * webpage
	 */
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * this method is created using Javascript command to page up to top of webpage
	 */
	public void scrollPageUp() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	/**
	 * Method created using javascript to verify if element is visible it will stop
	 * scroll of WebPage
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Method used to get all browser available in OS using Javascript
	 * 
	 * @return
	 */
	public String getBrowserInfo() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String uAgent = js.executeScript("return navigator.userAgent;").toString();
		return uAgent;
	}

	/**
	 * Method is used to send values to webelement using ID JavaScript created this
	 * 
	 * @param id
	 * @param value
	 */
	public void sendKeysUsingJSWithId(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	/**
	 * Method is used to send values to webelement using Name JavaScript created
	 * this
	 * 
	 * @param name
	 * @param value
	 */
	public void sendKeysUsingJSWithName(String name, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementByName('" + name + "').value='" + value + "'");
	}

	/**
	 * Method to check if page is ready or page is loaded
	 */
	public void checkPageIsReadyJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

}
