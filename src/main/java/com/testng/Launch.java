package com.testng;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Launch {
	WebDriver driver;
	WebDriverWait wait;
	static String directory=System.getProperty("user.dir");
	int i=0;
	
  @Test (dataProvider = "dp" ,priority=0)
  public void f(String n, String s) {
	  try {
		  driver.findElement(By.name("username")).sendKeys(n);
		  driver.findElement(By.name("password")).sendKeys(s);
		  driver.findElement(By.className("input-group-btn")).click();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Exception occured");
	}
  }
  
  @BeforeMethod
  @Parameters("url")
  public void beforeMethod(String url) {
	  driver.get(url);
	  driver.manage().window().maximize();
	  String title=driver.getTitle();
	  System.out.println(title);
	  Assert.assertEquals("#1 Free CRM software in the cloud for sales and service", driver.getTitle());
  }
  
  @Test (priority=1, groups="menu")
  public void changeMenu1()
  {	  
	  WebElement priceMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Pricing')]")));
	  priceMenu.click();
  }
  @Test (priority=2, groups="menu")
  public void changeMenu2()
  {
	  WebElement priceMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Customers')]")));
	  priceMenu.click();
	  Assert.assertEquals(true, true);
  }
  @Test (priority=3, dependsOnMethods="changeMenu2", groups="menu")
  public void changeMenu3()
  {	 
	  WebElement priceMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Contact')]")));
	  priceMenu.click();
  }

  @AfterMethod
  public void afterMethod() throws IOException, Exception {
	  Utility.takeScreenShot(driver, "Capture"+i+"img");
	  i++;
	  Thread.sleep(2000);
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "username", "password" }      
    };
  }
  
  @BeforeTest
  @Parameters("browser")
  public void beforeTest(String browser) {
	   try
       {
              if(browser.equalsIgnoreCase("Firefox"))
              {
            	  System.setProperty("webdriver.gecko.driver",""+directory+"//Driver//chromedriver.exe");
            	  driver = new FirefoxDriver();
              }
              if(browser.equalsIgnoreCase("Chrome"))
              {
            	  System.setProperty("webdriver.chrome.driver",""+directory+"//Driver//chromedriver.exe");
            	  driver = new ChromeDriver();
              }
              if(browser.equalsIgnoreCase("IE"))
              {    
            	  System.setProperty("webdriver.ie.driver",""+directory+"//Driver//IEDriverServer.exe");
            	  driver = new InternetExplorerDriver();
              }                   
       }
       catch (WebDriverException e)
       {
              System.out.println("Browser not found" +e.getMessage());
       }
	  new WebDriverWait(driver, 20);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
