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
	
	public static CompareProductsListPO getCompareProductListPage(WebDriver driver) {

		return new CompareProductsListPO(driver);
	}
	
	public static TVPO getTVPage(WebDriver driver) {
		
		return new TVPO(driver);
	}
	
	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {

		return new ProductDetailsPO(driver);
	}
	
	public static ReviewPO getReviewPage(WebDriver driver) {

		return new ReviewPO(driver);
	}
	
	public static LoginAdminPO getLoginAdminPage(WebDriver driver) {

		return new LoginAdminPO(driver);
	}
	
	public static CustomerAdminPO getCustomerAdminPage(WebDriver driver) {

		return new CustomerAdminPO(driver);
	}
}
