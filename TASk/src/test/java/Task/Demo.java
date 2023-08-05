package Task;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class Demo {
	
	
        public static WebDriver driver;
        
	    public  static void main(String[]args) {
	    System.setProperty("webdriver.chrome.driver","C:\\Users\\Saurabh\\OneDrive\\Documents\\Chromedriver\\chromedriver_win32 (3)\\chromedriver.exe");
	    ChromeOptions co=new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver =new ChromeDriver();
	    driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("automationuser");
	    driver.findElement(By.xpath("/input[@placeholder='Password']")).sendKeys("grampower");
	    driver.findElement(By.xpath("//input[@value='LOG IN']")).click();
	    if (driver.getTitle().contains("Home Page")) {
	    System.out.println("Test Case - Sign In with Valid Credentials: Passed");
	    } else {
	    System.out.println("Test Case - Sign In with Valid Credentials: Failed");
	        }
	    }
	    
	     public void verifyHomePage() {
	           
	     WebElement metersCountElement = driver.findElement(By.id("meters-count"));
         String metersCountText = metersCountElement.getText();
	            int totalMetersCount = Integer.parseInt(metersCountText);

	            // Locate the assigned meters count element
	            WebElement assignedMetersCountElement = driver.findElement(By.id("assigned-meters-count"));

	            // Get the text displaying the assigned meters count
	            String assignedMetersCountText = assignedMetersCountElement.getText();

	            // Convert the text to an integer for further verification
	            int assignedMetersCount = Integer.parseInt(assignedMetersCountText);

	            // Verify that assigned meters are always less than the total meters
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
	    


	    public void testSignInWithInvalidCredentials() {
	        // Test steps for signing in with invalid credentials
	        WebElement username = driver.findElement(By.name("username"));
	        WebElement password = driver.findElement(By.name("password"));
	        WebElement signInButton = driver.findElement(By.id("signin-button"));

	        // Replace with invalid credentials
	        username.sendKeys("invalid_username");
	        password.sendKeys("invalid_password");
	        signInButton.click();

	        // Verify the error message is displayed
	        if (driver.getPageSource().contains("Invalid credentials")) {
	            System.out.println("Test Case - Sign In with Invalid Credentials: Passed");
	        } else {
	            System.out.println("Test Case - Sign In with Invalid Credentials: Failed");
	        }
	    }
	   @Test(priority=3)
	    public static void verifyRecentlyAssignedMetersList(WebDriver driver) {
	        // Locate the recently assigned meters list container
	        WebElement recentlyAssignedListContainer = driver.findElement(By.xpath("//h3[normalize-space()='Recently Assigned']"));

	        // Get all the elements inside the container
	        List<WebElement> recentlyAssignedMeters = recentlyAssignedListContainer.findElements(By.xpath("//h3[normalize-space()='Recently Deassigned']"));

	        // Verify that the list is not empty
	        if (recentlyAssignedMeters.size() > 0) {
	            System.out.println("Recently Assigned Meters List Verification: Passed");

	            // Loop through the list and print the meter names
	            for (WebElement meter : recentlyAssignedMeters) {
	                System.out.println("Meter: " + meter.getText());
	            }
	        } else {
	            System.out.println("Recently Assigned Meters List Verification: Failed (List is empty)");
	        }
	    }
	}



