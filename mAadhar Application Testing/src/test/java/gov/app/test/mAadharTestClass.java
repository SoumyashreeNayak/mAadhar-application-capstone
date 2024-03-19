package gov.app.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class mAadharTestClass {
	private int countOfScreenshots = 0;
	private WebDriver webDriver;

	@Parameters({ "driverPath" })
	@BeforeTest
	public void setup(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);

		webDriver = new ChromeDriver();

		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	}


	@Parameters({ "websiteURL", "fileLoc" })
	@Test(priority = 1)
	public void HomePageTest(String websiteURL, String fileLoc) throws IOException {
		long startTime = System.currentTimeMillis();
		webDriver.get(websiteURL);
		long endTime = System.currentTimeMillis();
		Reporter.log("Page load time: " + (endTime - startTime) + " milliseconds");

		Reporter.log("Reached Home page ");
		takeScreenshot(webDriver, countOfScreenshots++,  fileLoc);
	}
	
	@Parameters({  "fileLoc" })
	@Test(priority = 2)
	public void UserRegsitrationTestInvalid(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Register")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Registration Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/button[2]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Using Existing email/mobile...");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[1]/input")).sendKeys("Akshay TR");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[2]/input")).sendKeys("ak@test.com");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[3]/input")).sendKeys("4564567890");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[4]/textarea")).sendKeys("123, New Street Chennai");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[5]/select/option[1]")).click();
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[6]/input")).sendKeys("19082000");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Citizen not Registered");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.linkText("Home")).click();
		Reporter.log("Reached Home..");
		
	}
	
	@Parameters({  "fileLoc" })
	@Test(priority = 3)
	public void UserRegsitrationTestValid(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Register")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Registration Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/button[2]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[1]/input")).sendKeys("amit");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[2]/input")).sendKeys("am@test.com");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[3]/input")).sendKeys("9555568890");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[4]/textarea")).sendKeys("1233, New Street Mumbai");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[5]/select/option[1]")).click();
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[6]/input")).sendKeys("28012004");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		String check=webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div[1]/h1")).getText();
		assertEquals(check,"Registered Successfully! Login to see your Aadhar status");
		Reporter.log("Citizen Successfully Registered");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.linkText("Home")).click();
		Reporter.log("Reached Home..");
		
	}
	@Parameters({  "fileLoc" })
	@Test(priority = 4)
	public void AdminRegsitrationTestValid(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Register")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Registration Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/button[1]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[1]/input")).sendKeys("Admin6");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[2]/input")).sendKeys("adm6@test.com");
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/div[3]/input")).sendKeys("Ad_aab523");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		String check=webDriver.findElement(By.xpath("/html/body/app-root/app-register/div/div[1]/h1")).getText();
		assertEquals(check,"Registered Successfully!");
		Reporter.log("Admin Successfully Registered");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.linkText("Home")).click();
		Reporter.log("Reached Home..");
		
	}
	@Parameters({  "fileLoc" })
	@Test(priority = 5)
	public void AdminLoginTest(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Login Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/button[1]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("adm2@test.com");
		webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Ad_aad123");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Logged in successfully!");
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/div/button[1]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//html/body/app-root/app-admin/div/div[2]/table/tbody/tr[1]/td[8]/button")).click();
		Reporter.log("Approving Citizen for Aadhar... Generating Aadhar Number");
		
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/div/button[1]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//html/body/app-root/app-admin/div/div[2]/table/tbody/tr[1]/td[8]/button")).click();
		Reporter.log("Rejecting Citizen for Aadhar...");
		
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/div/button[2]")).click();
		Thread.sleep(500);
		
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("va@test.com");
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/div/form/button")).click();
			
		Reporter.log("Marking Dead Citizen");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Logging out");
		webDriver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[1]/div[2]/button")).click();
		
	}
	
	
	@Parameters({  "fileLoc" })
	@Test(priority = 6)
	public void CitizenLoginTest1(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Home")).click();
		webDriver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Login Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/button[2]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("su@test.com");
		webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("9935568890");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Logged in successfully!");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/table/tbody/tr/td[9]/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Viewing Status");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/button")).click();
		Reporter.log("Logging out");
		
	}
	@Parameters({  "fileLoc" })
	@Test(priority =7)
	public void CitizenLoginTest2(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Login Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/button[2]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("sh@test.com");
		webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1235567890");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Logged in successfully!");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/table/tbody/tr/td[9]/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Viewing Status");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/button")).click();
		Reporter.log("Logging out");
		
	}
	@Parameters({  "fileLoc" })
	@Test(priority = 8)
	public void CitizenLoginTest3(String fileLoc) throws InterruptedException, IOException {
		webDriver.findElement(By.linkText("Login")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Reached Login Page");
		webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/button[2]")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("si@test.com");
		webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1235568890");
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/form/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Logged in successfully!");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/table/tbody/tr/td[9]/button")).click();
		Thread.sleep(500);
		takeScreenshot(webDriver, countOfScreenshots++, fileLoc);
		Reporter.log("Viewing Status");
		webDriver.findElement(By.xpath("/html/body/app-root/app-citizen/div/div/button")).click();
		Reporter.log("Logging out");
		
	}
	@AfterTest
	public void tearDown() {
		webDriver.quit();
	}
	
	public static void takeScreenshot(WebDriver wd, int count, String fileLoc) throws IOException {
		File file = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(fileLoc +"testimg_"+ count + ".png"));
	}

}
