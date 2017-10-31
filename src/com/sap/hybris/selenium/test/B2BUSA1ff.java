package com.sap.hybris.selenium.test;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

public class B2BUSA1ff {

	WebDriver driver;
	String title;

	@BeforeTest
	public void invokeBrowser() {

		try {

			System.setProperty("webdriver.firefox.driver", "geckodriver");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			driver.get("https://qa-www.jafrabiz.com/jafrastorefront/jafra-us");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "loginData")
	public void t1loginToB2BUSA(String username, String password) throws InterruptedException {
	    title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Jafra USA Site");
		driver.findElement(By.name("j_username")).sendKeys(username);
		driver.findElement(By.name("j_password")).sendKeys(password);
		driver.findElement(By.xpath("//div/div/div/div[4]/button")).click();
		title = driver.getTitle();
		Assert.assertEquals(title, "Jafra USA Site | Homepage");
		System.out.println("Successfully logged in!");
	}
	
	@Test
	public void t2changeAccount() {
		driver.findElement(By.linkText("My Account")).click();
		title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Update Profile | Jafra USA Site");
		driver.findElement(By.name("lastName")).clear();
		driver.findElement(By.name("lastName")).sendKeys("Smithson");
		driver.findElement(By.xpath("//div[3]/div[3]/div[2]/div/div/div[2]/div/div[2]/div[2]/input")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		WebElement	we_lastname = driver.findElement(By.name("lastName"));
		String lastname = we_lastname.getAttribute("value");
		Assert.assertEquals(lastname, "Smithson");
		System.out.println("Remember to check the change in ECC");
		
	}
	
	@Test
	public void t3aSOCard() {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//div[3]/header/div[4]/nav/div/div/ul/li[1]/a"));
		action.moveToElement(we).build().perform();
		we = driver.findElement(By.xpath("//div[3]/header/div[4]/nav/div/div/ul/li[1]/div/ul/li[1]/a"));
		action.moveToElement(we).build().perform();
		driver.findElement(By.xpath("//div[3]/header/div[4]/nav/div/div/ul/li[1]/div/ul/li[1]/div/div[1]/div[1]/div[1]/ul/li[1]/a")).click();
		title = driver.getTitle();
		Assert.assertEquals(title,"Cleanser | Cleanse | Skincare | Category | Jafra USA Site");
		System.out.println("Successful navigation to product page.");
		//driver.findElement(By.cssSelector("input#$10-$20")).click();
		//driver.findElement(By.id("$10-$20")).click();
		//Add the first displayed product to the cart
		driver.findElement(By.xpath("//div[3]/div[3]/div[1]/div/div/div[2]/div/div/div[2]/div[9]/div/div[3]/div/div[2]/form/button")).click();
	}
	
	@Test
	public void t3bSOCard() {
		//Navigate to the cart
		driver.findElement(By.xpath("//div[3]/header/div[1]/div/div[2]/div[2]/div/ul/li[4]/div/div/div[1]/a")).click();		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		//Next
		driver.findElement(By.linkText("NEXT")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		//Next
		driver.findElement(By.xpath("//div[3]/div[3]/main/div[3]/div/div/div[2]/div/div[3]/form/button")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		//Select address
		driver.findElement(By.id("addressSelectRadio")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		//Next
		driver.findElement(By.id("selectAddressButtonUs")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		//select payment method = card
		driver.findElement(By.xpath("//div[3]/div[3]/div[2]/main/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/label/input")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		driver.findElement(By.xpath("//div[3]/div[3]/div[2]/main/div[2]/div/div/div[2]/div/div[2]/div[1]/div[3]/div[2]/div[1]/div/div/div/table/tbody/tr/td[1]/input")).click(); //select the first credit card
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		driver.findElement(By.xpath("//*[@id='btnQuickOrderPaymentInfoNext']")).click(); //Next
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		driver.findElement(By.id("placeorder_submit")).click(); //place order
		title = driver.getTitle();
		Assert.assertEquals(title, "");
		WebElement	we_orderid = driver.findElement(By.name("OrderId"));
		String orderid = we_orderid.getAttribute("value");
		System.out.println("Order number" + orderid + "has been submitted.");
		t4bViewOrderHistory(orderid);
	}
	
	@Test
	public void t4aViewOrderHistory() {
		String orderid = "5834301";
		t4bViewOrderHistory(orderid);
	}
	
	public void t4bViewOrderHistory(String orderid) {
		
		Boolean found = false;
		Boolean btrue = true;
		
		driver.findElement(By.linkText("My Account")).click();
		title = driver.getTitle();
		Assert.assertEquals(title, "Update Profile | Jafra USA Site");
		driver.findElement(By.linkText("Order History")).click();
		// loop through order table
		WebElement table = driver.findElement(By.xpath("//div[3]/div[3]/div[2]/div/div/div[2]/div/div[3]/div[1]/div/div/div[1]/table/tbody"));
		List<WebElement> rows = table.findElements(By.xpath("//tr[1]/td[1]"));
		java.util.Iterator<WebElement> i = rows.iterator();
		while(i.hasNext()) {
		    WebElement row = i.next();
		    if ( row.getText().contains(orderid) ) 
		    	found = true;
		}
		Assert.assertEquals(found, btrue);
		System.out.println("Order " + orderid + "found in Order History.");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

	@DataProvider(name = "loginData")
	public Object[][] loginDataFeed() {

		ReadExcelFile config = new ReadExcelFile("/Users/i804036/Documents/workspace/Selenium/Tuts/Jafra/B2BUSALoginCredentials.xlsx");

		int rows = config.getRowCount(0);

		Object[][] credentials = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			credentials[i][0] = config.getData(0, i, 0);
			credentials[i][1] = config.getData(0, i, 1);
		}

		return credentials;

	}
	
}
