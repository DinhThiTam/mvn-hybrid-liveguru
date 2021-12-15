package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.HomePageUI;
import pageUIs.user.liveGuru99.MyAccountPageUI;

public class MyAccountPO extends BasePage {
	private WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public int getSizeProductNameElement() {
		waitForElementVisible(driver, MyAccountPageUI.PRODUCT_NAME_IN_TABLE);
		return getSizeElements(driver, MyAccountPageUI.PRODUCT_NAME_IN_TABLE);
	}

	public String getTextProductName() {
		waitForElementVisible(driver, MyAccountPageUI.PRODUCT_NAME_IN_TABLE);
		return getElementText(driver, MyAccountPageUI.PRODUCT_NAME_IN_TABLE);
	}




	



	

	
	

}
