package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.HomePageUI;

public class MyAccountPO extends BasePage {
	private WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMessageSuccessDisplayed() {
		waitForElementVisible(driver, HomePageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, HomePageUI.SUCCESS_MESSAGE);
	}




	

	
	

}
