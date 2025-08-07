package basics;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class A_ActionsClass {

	@Test
	public void dragDrop() throws Exception {
		String url = "https://the-internet.herokuapp.com/";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		String mainWindow = driver.getWindowHandle();
		WebElement dragDrop = driver.findElement(By.linkText("Drag and Drop"));

//		Multi-button click
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(dragDrop).keyUp(Keys.CONTROL).build().perform();

		Set<String> allwindow = driver.getWindowHandles();
		for (String window : allwindow) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		Wait<WebDriver> waits = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class));
		waits.until(d -> d.findElement(By.xpath("//h3[text()='Drag and Drop']")));

//		Drag and Drop
		WebElement source = driver.findElement(By.xpath("//div[contains(@id,'-a')]"));
		WebElement target = driver.findElement(By.xpath("//div[contains(@id,'-b')]"));
		action.dragAndDrop(source, target).build().perform();

		driver.close();
		driver.switchTo().window(mainWindow);
		Thread.sleep(500);

//		Scroll into the view & Click
		WebElement hover = driver.findElement(By.linkText("Hovers"));
		action.moveToElement(hover).build().perform();
		hover.click();

//		Hover over
		WebElement tooltip = driver.findElement(By.xpath("(//img[@alt='User Avatar'])[1]"));
		action.moveToElement(tooltip).build().perform();
		String tooltipValue = driver.findElement(By.xpath("//a/preceding-sibling::h5[contains(text(),'user1')]"))
				.getText();
		Assert.assertEquals(tooltipValue, "name: user1");

		Thread.sleep(2000);

//		Context click
		driver.navigate().back();
		driver.findElement(By.linkText("Context Menu")).click();
		By menu = By.xpath("//div[@id='hot-spot']");
		waits.until(d -> d.findElement(menu));
		action.contextClick(driver.findElement(menu)).build().perform();
		driver.switchTo().alert().accept();
		Thread.sleep(1000);

//		Context click
		driver.navigate().to("https://the-internet.herokuapp.com/key_presses");
		WebElement textField = driver.findElement(By.id("target"));
		action.moveToElement(textField).click().keyDown(Keys.CONTROL).sendKeys("test").keyUp(Keys.CONTROL)
				.sendKeys("Testing").keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
				.build().perform();
		Thread.sleep(1000);

	}

}
