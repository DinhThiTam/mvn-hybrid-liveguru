package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.HomePageUI;
import pageUIs.user.liveGuru99.ShoppingCartPageUI;

public class ProductDetailsPO extends BasePage {
	private WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isShoppingCartEmptyMessageDisplayed() {
		waitForElementVisible(driver, ShoppingCartPageUI.SHOPPING_CART_EMPTY_MESSAGE);
		return isElementDisplayed(driver, ShoppingCartPageUI.SHOPPING_CART_EMPTY_MESSAGE);
	}

	



	
	





	

	
	

}
