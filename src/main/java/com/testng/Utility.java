package com.testng;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

public class Utility {
	
	
	public static void takeScreenShot(WebDriver driver,String name) throws IOException
	{
		TakesScreenshot shot= ((TakesScreenshot)driver);
		File src=shot.getScreenshotAs(OutputType.FILE);
		File dest= new File(Launch.directory+"//ScreenShot//"+name+".png");
		FileUtils.copyFile(src, dest);
	}
	 
	public static void SelectDropdown(WebDriver driver,String xpath,String value){
		 try {
			WebDriverWait wait0 = new WebDriverWait(driver, 80);
				WebElement ele = wait0.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				Select dropdown=new Select(ele);
				dropdown.selectByVisibleText(value);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			WebDriverWait wait0 = new WebDriverWait(driver, 80);
			WebElement ele = wait0.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			Select dropdown=new Select(ele);
			dropdown.selectByVisibleText(value);
		}
	}
	
	 public static void elementscrolltoview(WebDriver driver,String xpath){
			WebElement ele=driver.findElement(By.xpath(xpath));//    click  admin then users
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}
	 
	 public static void doubleClick(WebDriver driver, String element, WebDriverWait wait)
		{
			WebElement promotion4=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			Actions action5 = new Actions(driver).doubleClick(promotion4);
			action5.build().perform();
		}
}
