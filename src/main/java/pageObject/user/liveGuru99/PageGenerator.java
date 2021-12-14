package pageObject.user.liveGuru99;

import org.openqa.selenium.WebDriver;


public class PageGenerator {
	WebDriver driver;

	private PageGenerator() {
	}
	
	public static HomePO getHomePO(WebDriver driver) {
		return new HomePO(driver);
	}
	
	public static RegisterPO getRegisterPage(WebDriver driver) {

		return new RegisterPO(driver);
	}
	
	public static MyAccountPO getMyAccountPage(WebDriver driver) {

		return new MyAccountPO(driver);
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {

		return new LoginPO(driver);
	}
	
	public static MobilePO getMobilePage(WebDriver driver) {

		return new MobilePO(driver);
	}
	
	public static ShoppingCartPO getShoppingCartPage(WebDriver driver) {

		return new ShoppingCartPO(driver);
	}
}
