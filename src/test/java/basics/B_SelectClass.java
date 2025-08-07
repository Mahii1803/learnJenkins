package basics;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

public class B_SelectClass {

	@Test
	public void dropdown() throws InterruptedException, IOException {
//		selectByIndex, selectByValue, selectByVisibleText, selectByContainsVisibleText
//		Same for de-selection but de-selection will work multi-select drop-down
		String url = "https://the-internet.herokuapp.com/dropdown";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		wait.until(d -> d.findElement(By.xpath("//h3[starts-with(text(),'Dropdown')]")));

		WebElement ele = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select select = new Select(ele);
		select.selectByValue("1");
//		select.deselectByValue("1"); // - Multiple select
		driver.navigate().refresh();

		Thread.sleep(1000);

		WebElement ele2 = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select select1 = new Select(ele2);
		select1.selectByVisibleText("Option 2");
		
		driver.close();

	}

}
