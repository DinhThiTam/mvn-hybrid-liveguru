package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.BasePageUI;
import pageUIs.user.liveGuru99.HomePageUI;
import pageUIs.user.liveGuru99.ShoppingCartPageUI;

public class ShoppingCartPO extends BasePage {
	private WebDriver driver;

	public ShoppingCartPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isShoppingCartEmptyMessageDisplayed() {
		waitForElementVisible(driver, ShoppingCartPageUI.SHOPPING_CART_EMPTY_MESSAGE);
		return isElementDisplayed(driver, ShoppingCartPageUI.SHOPPING_CART_EMPTY_MESSAGE);
	}

	public boolean isShipingCostGeneratedDisplayed() {
		waitForElementVisible(driver, ShoppingCartPageUI.FLATE_RATE_RADIO);
		return isElementDisplayed(driver, ShoppingCartPageUI.FLATE_RATE_RADIO);
	}

	public String getTextGrandTotal() {
		waitForElementVisible(driver, ShoppingCartPageUI.GRAND_TOTAL);
		return getElementText(driver, ShoppingCartPageUI.GRAND_TOTAL);
	}

	public boolean isPageTitleDisplayed() {
		waitForElementVisible(driver, ShoppingCartPageUI.PAGE_TITLE_ORDER_SUCCESS);
		return isElementDisplayed(driver, ShoppingCartPageUI.PAGE_TITLE_ORDER_SUCCESS);
	}

	public String getFullOrderedNumber() {
		waitForElementVisible(driver, ShoppingCartPageUI.ORDERED_NUMBER);
		return getElementText(driver, ShoppingCartPageUI.ORDERED_NUMBER);
	}

	public String getOrderNumber() {
		String textOrderNumber = getElementText(driver, ShoppingCartPageUI.ORDERED_NUMBER).substring(16);
		return textOrderNumber;
	}



	



	
	





	

	
	

}
