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

public class TC_08_Add_Review extends BaseTest {
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

		log.info("Pre-Condition - Step 06: Open 'TV' menu");
		myAccountPage.clickToMenuLink(driver, "header", "TV");
		tiviPage = PageGenerator.getTVPage(driver);	
	}

	@Test (description = "Dependent by TC_02_Login")
	public void TC_01_Add_Your_Review() {		
		log.info("TC_01 - Step 01: Click on 'Samsung LCD' detail");
		tiviPage.openProductDetail(driver, "Samsung LCD");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("TC_01 - Step 01: Click on 'Add your review' link");
		productDetailsPage.clickToLinkInProductDeatailsPage(driver, "extra-info", "Add Your Review");
		reviewPage = PageGenerator.getReviewPage(driver);
		
		log.info("TC_01 - Step 01: Enter to 'Let us know your thoughts' textarea");
		reviewPage.enterToTextAreaByID(driver, "review_field", "");
		
		log.info("TC_01 - Step 01: Enter to 'Summary of Your Review' textbox");
		reviewPage.enterToTextboxByID(driver, "summary_field", "");
		
		log.info("TC_01 - Step 01: Enter to 'What's your nickname?' textbox");
		reviewPage.enterToTextboxByID(driver, "nickname_field", "");
		
		log.info("TC_01 - Step 01: Click to 'Submit Review' button");
		reviewPage.clickToButtonByTitle(driver, "Submit Review");
		
		log.info("TC_01 - Step 01: Verify error message is displayed");
		verifyTrue(reviewPage.isErrorMessageDisplayedByContainsIDAndText(driver, "review_field", "This is a required field."));
		
		log.info("TC_01 - Step 01: Verify error message is displayed");
		verifyTrue(reviewPage.isErrorMessageDisplayedByContainsIDAndText(driver, "nickname_field", "This is a required field."));
		
		log.info("TC_01 - Step 01: Verify error message is displayed");
		verifyTrue(reviewPage.isErrorMessageDisplayedByContainsIDAndText(driver, "summary_field", "This is a required field."));
		
		log.info("TC_01 - Step 01: Enter to 'Let us know your thoughts' textarea");
		reviewPage.enterToTextAreaByID(driver, "review_field", myThoughts);
		
		log.info("TC_01 - Step 01: Enter to 'Summary of Your Review' textbox");
		reviewPage.enterToTextboxByID(driver, "summary_field", myReview);
		
		log.info("TC_01 - Step 01: Enter to 'What's your nickname?' textbox");
		reviewPage.enterToTextboxByID(driver, "nickname_field", nickName);
		
		log.info("TC_01 - Step 01: Select to 'Quality 1' radio button");
		reviewPage.checkToRadioButton(driver, "Quality 1_3");
		
		log.info("TC_01 - Step 01: Click to 'Submit Review' button");
		reviewPage.clickToButtonByTitle(driver, "Submit Review");
		
		log.info("TC_01 - Step 01: Verify message is displayed");
		reviewPage.isMessageDisplayed(driver, "Your review has been accepted for moderation.");
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
