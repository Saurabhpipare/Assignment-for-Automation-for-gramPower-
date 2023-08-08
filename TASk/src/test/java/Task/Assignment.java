package Task;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Assignment {
	
	
	public static WebDriver driver;
	public static String USERNAME="automationuser";
	public static String PASSWORLD="grampower";
	public static String Total;
	
	
	@BeforeClass
	@Parameters({"Browser","URL"})
	public void BrowserInit(String Browser,String URL) {
	if(Browser.equals("Chrome")) {	
	System.setProperty("webdriver.chrome.driver",
	"C:\\Users\\Saurabh\\OneDrive\\Documents\\Chromedriver\\chromedriver_win32 (3)\\chromedriver.exe");
	ChromeOptions co=new ChromeOptions();
	co.addArguments("--remote-allow-origins=*");
    driver=new ChromeDriver(co);
	}
	else if(Browser.equals("Edge")) {
	System.setProperty("webdriver.edge.driver",
	"C:\\Users\\Saurabh\\OneDrive\\Documents\\Chromedriver\\edgedriver_win64\\msedgedriver.exe");
	ChromeOptions co=new ChromeOptions();
	co.addArguments("--remote-allow-origins=*");
	driver=new EdgeDriver();
	}
    driver.get(URL);
	driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	driver.manage().window().maximize();	
	}
	
	
	@Test(priority=1)
	public void VerifyURl() {
	String URL=driver.getCurrentUrl();
	Assert.assertEquals(URL,"https://data.grampower.com/hes/");
	}
	
	@Test(priority=2)
	public void CheckCredencials() throws InterruptedException {
	Thread.sleep(2000);	
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);	
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORLD);
	driver.findElement(By.xpath("//input[@value='LOG IN']")).click();
	}
	
	@Test(priority=3,enabled=false)
	public void CheckMetser() throws InterruptedException {
	Thread.sleep(2000);	
	WebElement Frame=driver.findElement(By.xpath("//iframe[@src='/hes/retail_dashboard?project=office']"));
	driver.switchTo().frame(Frame);
	Total=driver.findElement(By.cssSelector("#div_assign_count")).getText();
	String Test=driver.findElement(By.xpath("//h3[contains(text(),'ROOM ASSIGNED')]")).getText();
	System.out.println(Total);
	System.out.println(Test);
	Thread.sleep(1000);
	List<WebElement>Meters=driver.findElements(By.xpath("//ul[@id='ul_assigned_list']//li"));
	for(int i=0; i<Meters.size(); i++) {
	Assert.assertNotEquals(Total,i);
	}
	driver.switchTo().parentFrame();
	}
	
	@Test(priority=4)
	public void Verify() {
	List<WebElement>Meters=driver.findElements(By.xpath("//ul[@id='ul_assigned_list']//li"));
	for(WebElement meter:Meters) {
	String data=meter.getText();
	System.out.println(data);
	}
	}
	
	@Test(priority=5)
	public void CheckArrow() {
	WebElement Frame2=driver.findElement(By.xpath("//iframe[@src='/hes/retail_dashboard?project=office']"));
	driver.switchTo().frame(Frame2);
	driver.findElement(By.xpath("//img[@alt='arrow']")).click();
	driver.switchTo().parentFrame();
	}
	
	@Test(priority=6)
	public void VerifyTransformer() {
	driver.findElement(By.xpath("//div[@id='suggestion_box_statics']//p[contains(text(),'1 Distribution Transformer (DTR)')]")).click();
	}
	
	@Test(priority=7)
	public void VerifyIcon() throws InterruptedException {
	Thread.sleep(3000);	
	driver.findElement(By.xpath("//div[@style='width: 32px; height: 32px; overflow: hidden; position: absolute; left: -39px; top: 3px; z-index: 18;']//img[@src='/hes/static/img/dtr_gray.png']")).click();
	driver.findElements(By.xpath("//*[text()='6']")).get(0).click();
	driver.findElement(By.xpath("//img[@id='ROFANANDAA1103']")).click();
	}
	
	@Test(priority=8)
	public void VerifyEnergy() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Realtime']")).click();
	
	Thread.sleep(3000);
	
	String V1=driver.findElement(By.xpath("//td[contains(text(),'468.55')]")).getText();
	String V2=driver.findElements(By.xpath("//td[contains(text(),'469.63')]")).get(0).getText();
	Assert.assertEquals(V1,V2);
	
	String V3=driver.findElement(By.xpath("//td[contains(text(),'469.63')]")).getText();
	String v4=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[2]/td[6]")).getText();
	Assert.assertEquals(V3,v4);
	
	String V5=driver.findElement(By.cssSelector("body > div:nth-child(3) > section:nth-child(2) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(3) > td:nth-child(4).")).getText();
	String V6=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[3]/td[6]")).getText();
	Assert.assertEquals(V5, V6);
	
	String V7=driver.findElement(By.xpath("//td[contains(text(),'474.34')]")).getText();
	String V8=driver.findElement(By.xpath("//td[contains(text(),'477.92')]")).getText();
	Assert.assertEquals(V7, V8);
	
	String V9=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[5]/td[4]")).getText();
	String V10= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[5]/td[6]")).getText();
	Assert.assertEquals(V9, V10);
	
	String V11=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[6]/td[4]")).getText();
	String V12=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[6]/td[6]")).getText();
	Assert.assertEquals(V11, V12);
	
	String V13=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[7]/td[4]")).getText();
	String V14=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[7]/td[6]")).getText();
	Assert.assertEquals(V13, V14);
	
	String V15=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/section[1]/div[1]/section[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[8]/td[4]")).getText();
	String V16=driver.findElement(By.xpath("//td[normalize-space()='488.1']")).getText();
	Assert.assertEquals(V15, V16);
	
	driver.quit();
	
	}
	
}