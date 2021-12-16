package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.liveGuru99.CustomerAdminPageUI;

public class CustomerAdminPO extends BasePage {
	private WebDriver driver;

	public CustomerAdminPO(WebDriver driver) {
		this.driver = driver;
	}


	public void clickToIconCloseInPopup() {
		waitForElementClickable(driver, CustomerAdminPageUI.ICON_CLOSE);
		clickToElement(driver, CustomerAdminPageUI.ICON_CLOSE);
	}


	



	



	
	





	

	
	

}
