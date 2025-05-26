package businessComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Sauce;

public class SauceLabs {

	static WebDriver driver;
@Before
	public void browser() {
		//driver = new ChromeDriver();
	/* EdgeOptions options = new EdgeOptions();
     options.addArguments("--headless"); // Enables headless mode
     options.addArguments("--window-size=1920,1080"); // Sets a fixed viewport size
 */

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		System.out.println("Browser Launched Successfully");
	}
	
@After
public void teardown() {
	driver.close();
	driver.quit();
	}
	
	@Given("User is on SwagLabs Login page")
	public void login_page() {
		
		driver.navigate().to("https://www.saucedemo.com/");
		
	}

	@When("User enters valid credentials")
	public void user_enters_valid_credentials() {
		driver.findElement(Sauce.usrnm).sendKeys("standard_user");
		driver.findElement(Sauce.pswd).sendKeys("secret_sauce");
		driver.findElement(Sauce.login).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
	}

	@Then("User should be redirected to Products")
	public void user_should_be_redirected_to_homepage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (driver.findElement(Sauce.products).isDisplayed()) {
			System.out.println("Products Displayed Successfully");
		} else {
			System.out.println("Products not Displayed");
		}
	}


	@When("User filter the product by price")
	public void srch_product() {		
		WebElement Price=driver.findElement(Sauce.filter);
		Select select=new Select(Price);
		select.selectByValue("lohi");
	}

	@Then("add first product to the cart")
	public void addingcart() {
		driver.findElement(Sauce.addcart).click();
	}

	@And("opens cart to print the added product")
	public void verifycart() {
		driver.findElement(Sauce.cart).click();
		WebElement product = driver.findElement(Sauce.cartprod);
		if (product.isDisplayed()) {
			System.out.println("Product added to the cart is" + product.getText());
		} else {
			System.out.println("Product was not added to the cart ");

		}
	}

}
