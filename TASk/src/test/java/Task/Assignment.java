package Task;



import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment {
	
	

			public static WebDriver driver;
			 
			     @Test(priority=1)
				 public void VerifySignINPage() {
				 System.setProperty("webdriver.chrome.driver",
				 "C:\\Users\\Saurabh\\OneDrive\\Documents\\Chromedriver\\chromedriver_win32 (3)\\chromedriver.exe");
				 ChromeOptions co=new ChromeOptions();
				 co.addArguments("--remote-allow-origins=*");
				 driver =new ChromeDriver(co);
				 driver.get("https://data.grampower.com/hes/");
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 driver.manage().window().maximize();
				 
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("automationuser");
				 driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("grampower");
				 driver.findElement(By.xpath("//input[@value='LOG IN']")).click();
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 String Title =driver.getTitle();
				 Title.contains(Title);
			 }	
			     @Test(priority=2)
			     public void verifyHomePage() throws InterruptedException {
			     WebElement metersCountElement = driver.findElement(By.xpath("//h3[normalize-space()='Recently Assigned']"));
			     String metersCountText = metersCountElement.getText();
				 int totalMetersCount = Integer.parseInt(metersCountText);
				 Thread.sleep(3000);
				 WebElement assignedMetersCountElement = driver.findElement(By.xpath("//h3[normalize-space()='Recently Deassigned']"));
                 String assignedMetersCountText = assignedMetersCountElement.getText();
                 int assignedMetersCount = Integer.parseInt(assignedMetersCountText);
                 if (assignedMetersCount < totalMetersCount) {
				 System.out.println("Home Page Verification: Passed");
				 System.out.println("Total Meters Count: " + totalMetersCount);
				 System.out.println("Assigned Meters Count: " + assignedMetersCount);
				 } else {
			     System.out.println("Home Page Verification: Failed");
				 System.out.println("Total Meters Count: " + totalMetersCount);
				 System.out.println("Assigned Meters Count: " + assignedMetersCount);
				            }
				        }
			     @Test(priority=3)
			     public static void VerfyAssignedMetersList(WebDriver driver) {
				        
				  WebElement recentlyAssignedListContainer = driver.findElement(By.xpath("//h3[normalize-space()='Recently Assigned']"));
				  List<WebElement> recentlyAssignedMeters = recentlyAssignedListContainer.findElements(By.xpath("//h3[normalize-space()='Recently Deassigned']"));

				        
				  if (recentlyAssignedMeters.size() > 0) {
				  System.out.println("Recently Assigned Meters List Verification: Passed");

				           
				  for (WebElement meter : recentlyAssignedMeters) {
				  System.out.println("Meter: " + meter.getText());
				            }
				   } else {
				  System.out.println("Recently Assigned Meters List Verification: Failed (List is empty)");
				        }
				    }
				
			     @Test(priority=4)
			     public static void verifySiteArrowIcon(WebDriver driver) {  
			     driver.findElement(By.xpath("//img[@alt='arrow']")).click(); 
			     
 }
			     @Test(priority=5)
			     public static void verifyTransformerIcon(WebDriver driver) {
			     driver.findElement(By.className("//img[@id='ROFANANDA AG4']")).click();
			     

}
}