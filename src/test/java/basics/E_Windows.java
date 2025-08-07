package basics;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class E_Windows {

	@Test
	public void singleWindows() throws Exception {
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

		Thread.sleep(500);

		driver.switchTo().window(mainWindow);
		Thread.sleep(500);

		for (String window : allwindow) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		Thread.sleep(1000);
		driver.close();

//		to perform any action Here switching is mandatory
		driver.switchTo().window(mainWindow);
		Thread.sleep(1000);
		driver.close();
	}

	@Test
	public void multiWindows() throws Exception {
		String url = "https://the-internet.herokuapp.com/";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		String mainWindow = driver.getWindowHandle();
		WebElement dragDrop = driver.findElement(By.linkText("Drag and Drop"));

		controlClick(driver, dragDrop);
		controlClick(driver, dragDrop);
		controlClick(driver, dragDrop);

		Set<String> win = driver.getWindowHandles();
		List<String> windows = new LinkedList<>(win);
		driver.switchTo().window(windows.get(2));
		
		Thread.sleep(500);
		driver.switchTo().window(mainWindow);
		Thread.sleep(500);
		driver.quit();

	}

	public static void switchToWindowByTitles(String expTitle, WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		for (String win : windows) {
			driver.switchTo().window(win);
			String actTitle = driver.getTitle();
			if (actTitle.contains(expTitle)) {
				System.out.println("Switched to window successfully: " + actTitle);
				break;
			}
		}
	}

	public static void controlClick(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(ele).keyUp(Keys.CONTROL).build().perform();

	}

	@Test
	public void blankWindow() throws Exception {
		String URL = "https://the-internet.herokuapp.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		String mainWindow = driver.getWindowHandle();

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(URL);

		String tab1 = driver.getWindowHandle();

		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get(URL);
		Thread.sleep(1000);
		driver.close();

		driver.switchTo().window(tab1);
		Thread.sleep(1000);
		driver.close();

		driver.switchTo().window(mainWindow);
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void jsExecute() throws Exception {
		String URL = "https://the-internet.herokuapp.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		String mainWindow = driver.getWindowHandle();

		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
		Thread.sleep(500);
		driver.switchTo().window(mainWindow);
		Thread.sleep(500);
		driver.quit();

	}
}
