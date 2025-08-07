package basics;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class H_ChromeOptions {
	
	public static void main(String[] args) throws Exception {
		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.google.com");
		
		Thread.sleep(5000);
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"//src//test//resources//screenshots//ss1.jpg");
		FileUtils.copyFile(source, target);
		driver.quit();
	
	}
	
	

}
