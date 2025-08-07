package basics;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class G_JSExamples {
	WebDriver driver;

	@BeforeTest
	public void login() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://the-internet.herokuapp.com/");

	}

	@AfterTest
	public void logout() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void jsScrollBy() {
		Instant t1 = Instant.now();
		System.out.println(t1.toString());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Instant t2 = Instant.now();
		System.out.println(t2.toString());
		Duration d1 = Duration.between(t1, t2);
		System.out.println(d1.toMillis());
	}

	
	
	@Test
	public void jsScrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	@Test
	public void jsScrollToTop() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, docuement.body.scrollHeight)");

		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,0)");
	}

	@Test
	public void jsScrollIntoView() {
		WebElement element = driver.findElement(By.linkText("Frames"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
	}

	@Test
	public void jsClick() {
		WebElement element = driver.findElement(By.linkText("Drag and Drop"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	@Test
	public void jsGetPageTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = (String) js.executeScript("return document.title");
		System.out.println("Page title is: " + title);
	}

	@Test
	public void jsGetCurrentWindowName() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = (String) js.executeScript("return window.name");
		System.out.println("Current window name is :" + title);
	}

	@Test
	public void jsGetCurrentFrameName() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String name = (String) js.executeScript("return self.name");
		System.out.println("Current frame name is: " + name);
	}

	@Test
	public void jsCreateAlert() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('This is sample alert');");
	}

	@Test
	public void jsPageRefresh() throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
		Thread.sleep(5000);
	}

	@Test
	public void jsPageZoom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='80%'");
	}

}
