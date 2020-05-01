package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionTestUtil {
	WebDriver driver;

	/**
	 * 
	 * @param driver
	 */
	public ActionTestUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void ActionClass(WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	}

	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void ActionClassST(WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.clickAndHold(source).moveToElement(target).release().build().perform();
	}

	/**
	 * 
	 * @param target
	 * @param Click
	 */
	public void ActionClassmove(WebElement target, By Click) {
		Actions action = new Actions(driver);
		action.moveToElement(target).build().perform();
		driver.findElement(Click).click();
	}

	/**
	 * 
	 * @param optionList
	 * @param driver
	 * @param rightclick
	 * @param value
	 */
	public void RightClickAction(List<WebElement> optionList, WebDriver driver, WebElement rightclick, String value) {
		Actions action = new Actions(driver);
		action.contextClick(rightclick).build().perform(); // For rightclick using actions use contextClick method under
															// Actions Class
		for (int i = 0; i < optionList.size(); i++) {
			String text = optionList.get(i).getText();
			if (text.equalsIgnoreCase(value)) {
				optionList.get(i).click();
			}
		}
	}

	/**
	 * 
	 * @param optionList
	 * @param driver
	 * @param rightclick
	 * @param value
	 * @return
	 */
	public List<String> listOfRightClick(List<WebElement> optionList, WebDriver driver, WebElement rightclick,
			String value) {
		List<String> ar = new ArrayList<String>();
		Actions action = new Actions(driver);
		action.contextClick(rightclick).build().perform(); // For rightclick using actions use contextClick method under
															// Actions Class
		for (int i = 0; i < optionList.size(); i++) {
			String text = optionList.get(i).getText();
			ar.add(text);
		}
		return ar;
	}
	/**
	 * Custom wait created for wait for element until element found will loop in every 1000ms
	 * If element found will return True or else it will return false
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public boolean customIsElementDisplayedCheck(WebDriver driver,By locator, int timeout) {
		WebElement element=null;
		boolean flag=false;
		for(int i=0;i<timeout;i++) {
			try {
			element=driver.findElement(locator);
			flag=element.isDisplayed();
			break;
			}catch(Exception e) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e1) {	
				}
			}
		}return flag;
	}
}
