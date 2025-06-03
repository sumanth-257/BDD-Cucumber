package businessComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Sauce;

public class SauceLabs {

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    //static WebDriver driver;
   WebDriver driver;
    
   
   @Before
    public void browser() {
       
  System.setProperty("webdriver.chrome.driver", "Drivers/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new"); // Use "--headless" for older Edge versions
        driver = new EdgeDriver(options);
//driver = new EdgeDriver();
//driver = new ChromeDriver();
        driver.manage().window().maximize();

      //  System.out.println("Browser Launched Successfully");
        System.out.println("Browser Launched Successfully in Headless Mode");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Navigates to the SwagLabs login page.4
     */
    @Given("User is on SwagLabs Login page")
    public void login_page() {
        driver.navigate().to(BASE_URL);
    }

    /**
     * Enters valid credentials and logs in.
     */
    @When("User enters valid credentials")
    public void user_enters_valid_credentials() {
        driver.findElement(Sauce.usrnm).sendKeys(USERNAME);
        driver.findElement(Sauce.pswd).sendKeys(PASSWORD);
        driver.findElement(Sauce.login).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Verifies that the user is redirected to the Products page.
     */
    @Then("User should be redirected to Products")
    public void user_should_be_redirected_to_homepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsElement = driver.findElement(Sauce.products);
        if (productsElement.isDisplayed()) {
            System.out.println("Products Displayed Successfully");
        } else {
            System.out.println("Products not Displayed");
        }
    }

    /**
     * Filters the product by price.
     */
    @When("User filter the product by price")
    public void srch_product() {
        WebElement price = driver.findElement(Sauce.filter);
        Select select = new Select(price);
        select.selectByValue("lohi");
    }

    /**
     * Adds the first product to the cart.
     */
    @Then("add first product to the cart")
    public void addingcart() {
        driver.findElement(Sauce.addcart).click();
    }

    /**
     * Opens the cart and prints the added product.
     */
    @And("opens cart to print the added product")
    public void verifycart() {
        driver.findElement(Sauce.cart).click();
        WebElement product = driver.findElement(Sauce.cartprod);
        if (product.isDisplayed()) {
            System.out.println("Product added to the cart is: " + product.getText());
        } else {
            System.out.println("Product was not added to the cart");
        }
    }
}
