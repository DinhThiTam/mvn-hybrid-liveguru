package com.liveguru.user;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.LoginPO;
import pageObject.user.liveGuru99.MyAccountPO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.ShoppingCartPO;
import pageObject.user.liveGuru99.TVPO;
import utilities.DataUtil;

public class TC_07_Share_Wishlist extends BaseTest {
	WebDriver driver;
	String emailAddress, message;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		fakerData = DataUtil.getData();
		
		emailAddress = fakerData.getEmailAddress();
		message = "Share wishlist";

		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();
		
		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Log In' page");
		homePage.clickToMenuLink(driver, "header", "Log In");
		loginPage = PageGenerator.getLoginPage(driver);	
		
		log.info("Pre-Condition - Step 05: Set login page cookie");
		loginPage.setAllCookies(driver, TC_02_Login.loginPageCookie);
		loginPage.sleepInsecond(5);
		loginPage.refreshPage(driver);
		myAccountPage = PageGenerator.getMyAccountPage(driver);

		log.info("Pre-Condition - Step 06: Open 'TV' menu");
		myAccountPage.clickToMenuLink(driver, "header", "TV");
		tiviPage = PageGenerator.getTVPage(driver);	
	}

	@Test (description = "Dependent by TC_02_Login")
	public void TC_01_Share_Wishlist() {		
		log.info("TC_01 - Step 01: Click on 'Add to Wishlist' for 'LG LCD' Tivi");
		tiviPage.clickToActionByProductNameAndActionName(driver, "LG LCD", "Add to Wishlist");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("TC_01 - Step 02: Verify message is displayed");
		verifyTrue(myAccountPage.isMessageDisplayed(driver, "LG LCD has been added to your wishlist. Click "));
		
		log.info("TC_01 - Step 03: Verify message is displayed");
		verifyTrue(myAccountPage.isMessageDisplayed(driver, "here"));
		
		log.info("TC_01 - Step 04: Verify message is displayed");
		verifyTrue(myAccountPage.isMessageDisplayed(driver, "to continue shopping."));
		
		log.info("TC_01 - Step 05: Click on 'Share Wishlist' button");
		myAccountPage.clickToButtonByTitle(driver, "Share Wishlist");
		
		log.info("TC_01 - Step 06: Enter to 'Email' textarea");
		myAccountPage.enterToTextAreaByID(driver, "email_address", emailAddress);
		
		log.info("TC_01 - Step 07: Enter to 'Email' textarea");
		myAccountPage.enterToTextAreaByID(driver, "message", message);
		
		log.info("TC_01 - Step 08: Click on 'Share Wishlist' button");
		myAccountPage.clickToButtonByTitle(driver, "Share Wishlist");
		
		log.info("TC_01 - Step 09: Verify message is displayed");
		myAccountPage.isMessageDisplayed(driver, "Your Wishlist has been shared.");
		
		log.info("TC_01 - Step 10: Verify element size of product name is 1");
		verifyEquals(myAccountPage.getSizeProductNameElement(),1);
		
		log.info("TC_01 - Step 11: Verify product name is 'LG LCD'");
		verifyEquals(myAccountPage.getTextProductName(),"LG LCD");
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	ShoppingCartPO shoppingCartPage;
	TVPO tiviPage;
	MyAccountPO myAccountPage;
	DataUtil fakerData;
	LoginPO loginPage;
	
}
