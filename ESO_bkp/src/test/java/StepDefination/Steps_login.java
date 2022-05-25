package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class Steps_login {
	WebDriver driver = null;
	@Given("I have open the browser")
	public void login() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			System.out.println("Successfully open chrome Browser");
		} else {
			System.out.println("Successfully open chrome Browser");
		}
	}
	@When("I open PropertyPal.com website")
	public void openWebSite() {
		driver.get("https://www.propertypal.com/");
		System.out.println(" Successfully opened ProprtyPal.com website ");
		// driver.findElement(By.xpath("//button[@mode= 'primary']")).click();
		driver.findElement(By.xpath("//button[@mode='secondary'][1]")).click();
	}
	@Then("Verify Web Page Title as PropertyPal.com is dispalyed on UI")
	public void verifyWebPage() {
		// if(driver.findElement(By.xpath("//img[@class=\"search-logo\"]/@src")).isDisplayed())
		if (driver.findElement(By.xpath("//img[@class='search-logo']")).isDisplayed()) {
			System.out.println("Successfully verifed Page Title as- PropertyPal.com");
		} else {
			System.out.println("Verifed Page Title as- PropertyPal.com not displayed on UI");
		}
	}

	@Then("Search for properties in BT6 area")
	public void searchproperties() throws InterruptedException {
		driver.findElement(By.xpath("//article//section/input")).sendKeys("BT6");
		driver.findElement(By.xpath("//*[@id=\"searchForm\"]//button[1]")).click();
		Thread.sleep(10000);
	}
	@Then("Search for properties in BT6 and BT5 area")
	public void SearchPropertiesOnMultiplePostcodes() throws InterruptedException {
		driver.findElement(By.xpath("//article//section/input")).sendKeys("BT6,BT5");
		driver.findElement(By.xpath("//*[@id=\"searchForm\"]//button[1]")).click();
		Thread.sleep(4000);
	}
	@Then("Validate that properties from BT6 are displayed on Page")
	public void validatesearchresultPage1() {
		String ActualText = driver.findElement(By.xpath("//*[@id=\"body\"]//h1")).getText();
		System.out.println(ActualText);
		String ExpectedText = "PROPERTY FOR SALE IN BT6";
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"body\"]//h1")).getText(), ExpectedText);
		System.out.println("Properties in BT6 displayed successfully on this page.");
		List<WebElement> TotalPropertiesOnPage = driver.findElements(By.xpath("//div[@class='propbox-details']"));
		System.out.println("Total properties in on this page are " + TotalPropertiesOnPage.size());
		List<WebElement> PropertiesWithAddressBT6 = driver.findElements(By.xpath("//span[@class=\"text-ib\"]"));
		System.out.println("Properties with Address as BT6 on this page are " + PropertiesWithAddressBT6.size());
		if (TotalPropertiesOnPage.size() == PropertiesWithAddressBT6.size()) {
			System.out.println("All properties searched for BT6 area");
		} else {
			System.out.println("Incorrect Search. Please try again");
		}
	}
	@Then("Validate that properties from BT6 are displayed on Page2")
	public void validatesearchresultPage2() throws InterruptedException {
		driver.findElement(By.xpath("//a[@class='btn paging-next']")).click();
		Thread.sleep(5000);
	}
	@Then("Validate that properties from multiple postcodes BT6 and BT5 are displayed on Page")
	public void validatesearchresultBT5andBT6() {
		String ActualText = driver.findElement(By.xpath("//*[@id=\"body\"]//h1")).getText();
		System.out.println("Text displayed on Page is " + ActualText);
		String ExpectedText = "\"BT6, BT5\" IN PROPERTY FOR SALE";
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"body\"]//h1")).getText(), ExpectedText);
		System.out.println("Properties in BT6 and BT5 area are displayed successfully on this page.");
		List<WebElement> TotalPropertiesOnPage = driver.findElements(By.xpath("//div[@class='propbox-details']"));
		System.out.println("Total properties in on this page are " + TotalPropertiesOnPage.size());
		List<WebElement> PropertiesWithAddressBT6andBT5 = driver.findElements(By.xpath("//span[@class=\"text-ib\"]"));
		System.out.println("Properties with Address as BT6 and BT5 on this page are " + PropertiesWithAddressBT6andBT5.size());
		int BT5_Count = 0;
		int BT6_Count = 0;
		for (int i = 0; i < PropertiesWithAddressBT6andBT5.size(); i++) {
			String Actual_Postcode = PropertiesWithAddressBT6andBT5.get(i).getText();
			String Expected_BT5 = "BT5";
			String Expected_BT6 = "BT6";
			// System.out.println("Value of Actual_Postcode for "+i+" run is "+Actual_Postcode);
			if (Actual_Postcode.equals(Expected_BT5)) {
				BT5_Count++;
			} else if (Actual_Postcode.equals(Expected_BT6)) {
				BT6_Count++;
			}
		}
		System.out.println("Count of proprties with area BT5 are " + BT5_Count);
		System.out.println("Count of proprties with area BT6 are " + BT6_Count);
		int Total = BT5_Count + BT6_Count;
		System.out.println("Sum of properties for BT5 and BT6 area is " + Total);
		if (PropertiesWithAddressBT6andBT5.size() == Total) {
			System.out.println("All properties displayed on page are belongs to either BT5 or BT6. Correct outcome for search result");
		} else {
			System.out.println("Incorrect search result. Please check the search option");
		}
	}
	@Then("Sort the proprties for any postcode")
	public void SortProperties()
	{
		List<WebElement> PricesBeforeSort = driver.findElements(By.xpath("//span[@class=\"price-value \"]"));
		List<String> UnsortedPrice = new ArrayList<String>();
		//List<Double> UnsortedPrice = new ArrayList<>();
		for (WebElement cost : PricesBeforeSort)
		{
			//UnsortedPrice.add(Double.valueOf(cost.getText().replace("£", "")));
			UnsortedPrice.add(cost.getText().replace("£", ""));
		}	
		driver.findElement(By.xpath("//*[@id=\"body\"]//section")).click();
		driver.findElement(By.xpath("//*[@id=\"body\"]//section//li[5]")).click();
		//WebElement dropdown = driver.findElement(By.id("sort"));
		//WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"body\"]//section"));
		//Select sel = new Select(dropdown);
		//sel.selectByIndex(5);
		List<WebElement> PricesAfterSort = driver.findElements(By.xpath("//span[@class=\"price-value \"]"));
		List<String> SortedPrice = new ArrayList<String>();
		//List<Double> SortedCost = new ArrayList<>();
		for (WebElement cost : PricesAfterSort)
		{
			//SortedCost.add(Double.valueOf(porperty.getText().replace("£","")));
			SortedPrice.add(cost.getText().replace("£", ""));
		}
		Collections.sort(UnsortedPrice);
		Assert.assertEquals(UnsortedPrice, SortedPrice);
	}
	@Then("Close browser")
	public void closeBrowser() {
		driver.quit();
	}
}

//
// @Then("Verify three button on UI in blue Red and Green")
// public void verifyButtons(){
// WebElement element_Button1 =
// driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button']"));
// String button_ID_beforeClick = element_Button1.getAttribute("id");
// System.out.println("Successfully verifed 1st button on web Page with Text as
// - " + element_Button1.getText() + " and ID as " +
// element_Button1.getAttribute("id"));
// element_Button1.click();
// String button_ID_afterClick =
// driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button']")).getAttribute("id");
// Assert.assertNotEquals(button_ID_beforeClick, button_ID_afterClick);
// System.out.println("After click on 1st button on web Page with dispalyed Text
// as - " + driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button']")).getText()+ " and ID as " +
// button_ID_afterClick);
//
// WebElement element_Button2 =
// driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button
// alert']"));
// button_ID_beforeClick = element_Button2.getAttribute("id");
// System.out.println("Successfully verifed 2nd button on web Page with Text as
// - " + element_Button2.getText() + " and ID as " +
// element_Button2.getAttribute("id"));
// element_Button2.click();
// button_ID_afterClick = driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button alert']")).getAttribute("id");
// Assert.assertNotEquals(button_ID_beforeClick, button_ID_afterClick);
// System.out.println("After click on 2nd button on web Page with dispalyed Text
// as - " + driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button alert']")).getText()+ " and ID as " +
// button_ID_afterClick);
//
// WebElement element_Button3 =
// driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button
// success']"));
// button_ID_beforeClick = element_Button3.getAttribute("id");
// System.out.println("Successfully verifed 2nd button on web Page with Text as
// - " + element_Button3.getText() + " and ID as " +
// element_Button3.getAttribute("id"));
// element_Button3.click();
// button_ID_afterClick = driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button success']")).getAttribute("id");
// Assert.assertNotEquals(button_ID_beforeClick, button_ID_afterClick);
// System.out.println("After click on 3rd button on web Page with dispalyed Text
// as - " + driver.findElement(By.xpath("//div[@class='large-2
// columns']/a[@class='button success']")).getText()+ " and ID as " +
// button_ID_afterClick);
//
// }
//
// @Then("Verify table header on webPage")
// public void verifyTableHeader(){
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[1]")).getText(),"Lorem");
// System.out.println("Column Header - Lorem displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[2]")).getText(),"Ipsum");
// System.out.println("Column Header - Ipsum displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[3]")).getText(),"Dolor");
// System.out.println("Column Header - Dolor displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[4]")).getText(),"Sit");
// System.out.println("Column Header - Sit displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[5]")).getText(),"Amet");
// System.out.println("Column Header - Amet displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[6]")).getText(),"Diceret");
// System.out.println("Column Header - Diceret displayed successfully.");
//
// Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"large-10
// columns\"]/table/thead/tr/th[7]")).getText(),"Action");
// System.out.println("Column Header - Action displayed successfully.");
//
// }
//
// @Then("Verify edit and delete button in table")
// public void verifyEditDeleteButton() {
// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr[1]/td[7]/a[1]")).getText(),"edit");
// System.out.println("Successfully verifed Edit button on web Page");
// Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table/tbody/tr[1]/td[7]/a[2]")).getText(),"delete");
// System.out.println("Successfully verifed Delete button on web Page");
// }
//
// @Then("Verify Link name at the bottom of the Page")
// public void verifyLinkName() {
// Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"page-footer\"]/div/div/a")).getText(),"Elemental
// Selenium");
// System.out.println("Successfully verifed Link from web Page");
// }
//
// @Then("Close browser")
// public void closeBrowser(){
// driver.quit();
// }
//
// }
