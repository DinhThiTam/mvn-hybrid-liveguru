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
import pageObject.user.liveGuru99.ProductDetailsPO;
import pageObject.user.liveGuru99.ReviewPO;
import pageObject.user.liveGuru99.ShoppingCartPO;
import pageObject.user.liveGuru99.TVPO;
import utilities.DataUtil;

public class TC_09_Purchase_Products extends BaseTest {
	WebDriver driver;
	String myThoughts, myReview, nickName;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		myThoughts = "My Thoughts";
		myReview = "Acceptable quality";
		nickName = "Automation";
		
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

		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		myAccountPage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Register' page");
		myAccountPage.clickToMenuLink(driver, "header", "My Wishlist (1 item)");
	}

	@Test (description = "Dependent by TC_02_Login and TC_07_Share_Wishlist")
	public void TC_01_Add_Your_Review() {		
		log.info("TC_01 - Step 01: Click on 'Add to Cart' button");
		myAccountPage.clickToButtonByTitle(driver, "Add to Cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_01 - Step 02: Select 'Country' dropdown list");
		shoppingCartPage.selectItemInDropdownByID(driver, "country","United States");
		
		log.info("TC_01 - Step 02: Verify item is selected");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByID(driver, "country"),"United States");
		
		log.info("TC_01 - Step 02: Select 'State/Province' dropdown list");
		shoppingCartPage.selectItemInDropdownByID(driver, "region_id","New York");
		
		log.info("TC_01 - Step 02: Verify item is selected");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByID(driver, "region_id"),"New York");
		
		log.info("TC_01 - Step 02: Enter to 'Zip' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "postcode", "543432");
		
		log.info("TC_01 - Step 02: Click to 'Estimate' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Estimate");
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
	ProductDetailsPO productDetailsPage;
	ReviewPO reviewPage;
}
