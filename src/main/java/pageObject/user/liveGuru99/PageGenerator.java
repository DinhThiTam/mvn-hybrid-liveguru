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
}
