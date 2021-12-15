package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.CompareProductsListPageUI;
import pageUIs.user.liveGuru99.HomePageUI;
import pageUIs.user.liveGuru99.ShoppingCartPageUI;

public class CompareProductsListPO extends BasePage {
	private WebDriver driver;

	public CompareProductsListPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHeadingDisplayed() {
		waitForElementVisible(driver, CompareProductsListPageUI.HEADING_MESSAGE);
		return isElementDisplayed(driver, CompareProductsListPageUI.HEADING_MESSAGE);
	}

	



	
	





	

	
	

}
