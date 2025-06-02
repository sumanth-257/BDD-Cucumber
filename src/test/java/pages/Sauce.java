package pages;

import org.openqa.selenium.By;

public class Sauce {
//login page
	public static By usrnm=By.id("user-name");
	public static By pswd=By.id("password");
	public static By login=By.id("login-button");
//Products page	
	public static By products=By.xpath("//*[contains(text(),'Products')]");
	
//filter	
	public static By filter=By.xpath("//select[@class='product_sort_container']");
//add to cart	
	public static By addcart=By.xpath("(//button[contains(@class,'btn_primary')])[1]");
	
	
	public static By cart=By.xpath("//a[contains(@class,'shopping_cart')]");
    public static By cartprod=By.xpath("//div[@class='inventory_item_name']");
}
