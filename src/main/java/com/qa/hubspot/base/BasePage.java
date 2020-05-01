package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;

	// ThreadLocal has concept of Get and Set which say's first set then you Get
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// Concept of Encapsulation will be used whenever we get Value we have to
	// return.
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * With the concept of Thread local we have created below method
	 * 
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		String browsername = prop.getProperty("browser");
		System.out.println("Browser name to be used is " + browsername);
		optionsManager = new OptionsManager(prop);
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browsername.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Wrong browser entered" + browsername);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * Properties return type is called properties class object reference This
	 * method is used initialize/load properties file
	 * 
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");
			if (env == null) {
				path = "";
			} else {
				switch (env) {
				case "dev":
					path = "";
				case "qa":
					path = "";
				case "sit":
					path = "";
				case "prod":
					path = "";
				default:
					System.out.println("Please pass the correct env value...");
					break;
				// take path from command line can use below command:-  (mvn test -Denv="qa")
				}
			}
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\mathu\\eclipse-workspace\\HubSpot\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 1. TakesScreenshot is an interface 2. getScreenShot(Method is responsible for
	 * taking ScreenShot) 3. here "user.dir" means current project directory Note:-
	 * Extent reports always work on thread so to resolve this problem we will use
	 * thread local handle driver and extent report properly take screenshot utility
	 * it's new concept in JDK 1.8 (It says for every thread instance will create
	 * separate instance for driver which will be of same copy of driver)
	 * 
	 * @return
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
