package basics;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C_JSAlerts {

	@Test
	public void jsAlert() throws Exception{
		String url = "https://the-internet.herokuapp.com/javascript_alerts";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		driver.switchTo().alert().accept();
		String alertText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(alertText, "You successfully clicked an alert");
		Thread.sleep(1000);
		
		driver.quit();
	}
	
	@Test
	public void jsConfirmAlert() throws Exception{
		String url = "https://the-internet.herokuapp.com/javascript_alerts";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		driver.switchTo().alert().dismiss();
		String alertText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(alertText, "You clicked: Cancel");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		driver.switchTo().alert().accept();
		String alertText1 = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(alertText1, "You clicked: Ok");
		Thread.sleep(1000);
		
		driver.quit();
	}
	
	@Test
	public void jsPrompt() throws Exception{
		String url = "https://the-internet.herokuapp.com/javascript_alerts";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Test");
		alert.dismiss();
		
		String alertText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(alertText, "You entered: null");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert1 = driver.switchTo().alert();
		alert1.sendKeys("Test");
		alert1.accept();
		
		String alertText1 = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(alertText1, "You entered: Test");
		Thread.sleep(1000);
		
		driver.quit();
	}
	

}
