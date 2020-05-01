package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectTestUtil {

	WebDriver driver;
	SelectTestUtil tu;

	/**
	 * 
	 * @param driver
	 */
	public SelectTestUtil(WebDriver driver) {
		this.driver = driver;
		tu=new SelectTestUtil(driver);
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectBy(WebElement locator, String value) {
		Select select = new Select(locator);
		select.selectByValue(value);
	}

	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectBy(WebElement locator, int index) {
		Select select = new Select(locator);
		select.selectByIndex(index);
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */

	public void selectbyProvided(WebElement locator, String value) {
		Select select = new Select(locator);
		List<WebElement> list = select.getOptions();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(value)) {
				list.get(i).click();
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void dropDownHandle(WebDriver driver, String xpath, String value) {
		List<WebElement> listdropdown = driver.findElements(By.xpath(xpath));
		for (int i = 0; i < listdropdown.size(); i++) {
			if (listdropdown.get(i).getText().equals(value)) {
				listdropdown.get(i).click();
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param Locator
	 * @param LocatorValue
	 * @param value
	 */
	public void dropDownHandleLocatorOption(WebDriver driver, String Locator, String LocatorValue, String value) {
		List<WebElement> listdropdown = null;
		if (Locator.equals("xpath")) {
			listdropdown = driver.findElements(By.xpath(LocatorValue));
		} else if (Locator.equals("css")) {
			listdropdown = driver.findElements(By.xpath(LocatorValue));
		}
		for (int i = 0; i < listdropdown.size(); i++) {
			if (listdropdown.get(i).getText().equals(value)) {
				listdropdown.get(i).click();
			}
		}
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public ArrayList<String> getdropdownvalues(By locator) {
		ArrayList<String> ar = new ArrayList<String>();
		Select select = new Select(tu.getElement(locator));
		List<WebElement> listelement = select.getOptions();
		System.out.println("size of the listelement is: " + listelement.size());
		for (int i = 0; i < listelement.size(); i++) {
			ar.add(listelement.get(i).getText());
		}
		return ar;
	}

	/**
	 * 
	 * @param value
	 */
	public void dropdown(String... value) {
		List<WebElement> dropdown = driver.findElements(By.cssSelector("span.comboTreeItemTitle"));
		if (value[0].equalsIgnoreCase("ALL")) {
			for (int i = 0; i < dropdown.size(); i++) {
				String name = dropdown.get(i).getText();
				for (int k = 0; k < value.length; k++) {
					if (name.equals(value[k])) {
						dropdown.get(i).click();
						break;
					}
				}
			}
		} else {
			try {
				for (int all = 0; all < value.length; all++) {
					dropdown.get(all).click();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
