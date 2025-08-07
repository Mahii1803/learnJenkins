package basics;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class D_Waits {

	@Test
	public void implicitWait() {
		String url = "https://the-internet.herokuapp.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(url);
		driver.navigate().refresh();
		driver.close();
	}

	@Test
	public void webdriverwait() {
		String url = "https://the-internet.herokuapp.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(url);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/"));
		wait.until(ExpectedConditions.urlMatches("https://the-internet.herokuapp.com/"));
		wait.until(ExpectedConditions.titleIs("The Internet"));
		wait.until(ExpectedConditions.titleContains("The Internet"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Welcome to the-internet']")));
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h1[text()='Welcome to the-internets']")));
//		wait.until(ExpectedConditions.textToBe());
	}

	@Test
	public void fluentWait() {
		String url = "https://the-internet.herokuapp.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(500)).ignoring(NoSuchElementException.class);

		WebElement ele = wait.until(d -> d.findElement(By.linkText("Entry Ad")));
		ele.click();
	}

}
