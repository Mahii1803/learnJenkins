package basics;

import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

public class F_Frames {

	@Test
	public void method1() {
		String url = "https://the-internet.herokuapp.com/";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(url);

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(10))
				.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class));

		wait.until(d -> d.findElement(By.linkText("File Download")));

		WebElement ele = driver.findElement(By.linkText("Frames"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", ele);
		ele.click();

		WebElement nestedFrames = wait.until(d -> d.findElement(By.linkText("Nested Frames")));

		String parentWindow = driver.getWindowHandle();

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(nestedFrames).keyUp(Keys.CONTROL).build().perform();

		Set<String> windows = driver.getWindowHandles();
		for (String win : windows) {
			if (!win.equals(parentWindow)) {
				driver.switchTo().window(win);
				break;
			}
		}

		WebElement topFrame = driver.findElement(By.xpath("//frame[@name='frame-top']"));
		driver.switchTo().frame(topFrame);
		currentFrame(driver);
		
		WebElement leftFrame = driver.findElement(By.xpath("//frame[@name='frame-left']"));
		driver.switchTo().frame(leftFrame);
		currentFrame(driver);
		getEleText(driver);
		
		driver.switchTo().parentFrame();
		currentFrame(driver);
		
		WebElement middleFrame = driver.findElement(By.xpath("//frame[@name='frame-middle']"));
		driver.switchTo().frame(middleFrame);
		getEleText(driver);
		currentFrame(driver);
		
		driver.switchTo().parentFrame();
		currentFrame(driver);
		
		WebElement rightFrame = driver.findElement(By.xpath("//frame[@name='frame-right']"));
		driver.switchTo().frame(rightFrame);
		getEleText(driver);
		currentFrame(driver);
		
		driver.switchTo().parentFrame();
		currentFrame(driver);
		
		driver.switchTo().parentFrame();
		currentFrame(driver);
		
		WebElement bottomFrame = driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
		driver.switchTo().frame(bottomFrame);
		currentFrame(driver);
		getEleText(driver);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.quit();
		

	}
	
	public static void currentFrame(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String frame = (String) js.executeScript("return self.name");
		System.out.println("Im in the frame: "+frame);
	}

	public static void getEleText(WebDriver driver) {
		String leftText = driver.findElement(By.tagName("body")).getText();
		System.out.println(leftText);
	}
}
