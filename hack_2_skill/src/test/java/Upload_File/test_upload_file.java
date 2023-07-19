package Upload_File;

import java.awt.Window;
import java.nio.file.Path;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test_upload_file {

	WebDriver driver;
	public String url="http://the-internet.herokuapp.com/upload";

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void upload_file() {
		driver.get(url);
		
		WebElement choose_file = driver.findElement(By.id("file-upload"));
		choose_file.sendKeys("C:\\Users\\Pratibhav\\Desktop\\MASAI SCHOOL\\hack_2_skill\\src\\main\\resources\\SDLC_BWC.png");

		WebElement upload_button = driver.findElement(By.id("file-submit"));
		upload_button.click();
		
		WebElement uploaded_component = driver.findElement(By.id("uploaded-files"));

		if(uploaded_component.isDisplayed()) {
			Assert.assertEquals(uploaded_component.getText(), "SDLC_BWC.png");
		}else {
			Assert.assertTrue(false, "File not Uploaded");
		}
		  
	}
	
	@AfterTest
	public void close_browser() {
		driver.quit();
	}
	
}
