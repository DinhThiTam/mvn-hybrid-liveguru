package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.HomePageUI;

public class HomePO extends BasePage {
	private WebDriver driver;

	public HomePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLogoAtHomePageDisplayed() {
		waitForElementVisible(driver, HomePageUI.LOGO_AT_HOMEPAGE);
		return isElementDisplayed(driver, HomePageUI.LOGO_AT_HOMEPAGE);
	}

	
	
	

}
