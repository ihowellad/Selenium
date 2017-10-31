package com.sap.hybris.selenium.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

public class Tut1 {

	
	WebDriver driver;
	
	public void invokeBrowser() {
		
		try {
			
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.get("http://search.google.com");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	public void searchCheese() {
		try {
			driver.findElement(By.id("lst-ib")).sendKeys("cheese");
			driver.findElement(By.name("btnK")).click();
			
			System.out.println("Page title is:  " + driver.getTitle());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeBroser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		
		Tut1 myObj = new Tut1();
		myObj.invokeBrowser();
		myObj.searchCheese();
		//myObj.closeBroser();
		
	}

}
