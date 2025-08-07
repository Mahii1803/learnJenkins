package basics;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Z_BasicsInOne {

	@Test
	public void dragDrop() throws Exception {
		String url = "https://the-internet.herokuapp.com/";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		String mainWindow = driver.getWindowHandle();
		WebElement dragDrop = driver.findElement(By.linkText("Drag and Drop"));

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(dragDrop).keyUp(Keys.CONTROL).build().perform();

		Set<String> allwindow = driver.getWindowHandles();
		for (String window : allwindow) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		WebElement header = driver.findElement(By.xpath("//h3[text()='Drag and Drop']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(header));

		File targetFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\screenshot1.jpg");
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, targetFile);

		WebElement source = driver.findElement(By.xpath("//div[contains(@id,'-a')]"));
		WebElement target = driver.findElement(By.xpath("//div[contains(@id,'-b')]"));

		action.dragAndDrop(source, target).build().perform();

		File targetFile1 = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\screenshot2.jpg");
		File sourceFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile2, targetFile1);

		driver.switchTo().window(mainWindow);
		Thread.sleep(1500);

		for (String window : allwindow) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		Thread.sleep(1500);
		driver.close();
	}

	@Test
	public void dropdown() throws InterruptedException, IOException {
//		selectByIndex, selectByValue, selectByVisibleText, selectByContainsVisibleText
		String url = "https://the-internet.herokuapp.com/";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		String mainWindow = driver.getWindowHandle();
		WebElement ele1 = driver.findElement(By.linkText("Dropdown"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(ele1).keyUp(Keys.CONTROL).build().perform();

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		WebElement header = driver.findElement(By.xpath("//h3[starts-with(text(),'Dropdown')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(header));

		WebElement ele = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select select = new Select(ele);
		select.selectByValue("1");
//		select.deselectByValue("1"); // - Multiple select
		driver.navigate().refresh();

		Thread.sleep(1000);

		WebElement ele2 = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select select1 = new Select(ele2);
		select1.selectByVisibleText("Option 2");

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\screenshots1.jpg");
		FileUtils.copyFile(source, target);
	}

}
