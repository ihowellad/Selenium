package com.sap.hybris.selenium.test;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

public class Tut2Firefox {

	WebDriver driver;

	@BeforeTest
	public void invokeBrowser() {

		try {

			System.setProperty("webdriver.gecko.driver", "geckodriver");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			driver.get("http://search.google.com");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void loginToCommerce() {
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
