package Broken_Images;

import java.time.Duration;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class test_broken_link_images {

	WebDriver driver;
	public String url="http://the-internet.herokuapp.com/broken_images";

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void finds_broken_link() throws IOException {
		driver.get(url);
		List<WebElement> All_Images_List=driver.findElements(By.tagName("img"));
		int count=0;
		
		for(int i = 0; i < All_Images_List.size(); i++) {
			WebElement element = All_Images_List.get(i);
	          
		      String image_url = element.getAttribute("src");
		  
		      URL image_link = new URL(image_url);
		      HttpURLConnection httpConn = (HttpURLConnection) image_link.openConnection();
		      httpConn.connect();
		          
		      int status_code = httpConn.getResponseCode();
		      
		      if(status_code == 404){
		        System.out.println(image_url + " ___is a Broken Link");
		        count++;
		      }
		      else{
		        System.out.println(image_url + " ___is a Valid Link working fine");
		      }    
		 }
		      
		 System.out.println("Total no. of Broken link images are "+count);    
	}
	
	
	@AfterTest
	public void close_browser() {
		driver.quit();
	}
	
	
}	
	
	
