package com.liveguru.user;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.liveguru.common.Common_01_Login_User;

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

public class TC_10_Advanced_Search extends BaseTest {
	WebDriver driver;
	String country, state, zip, address, city, telephone;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		country = "United States";
		state = "New York";
		zip = "543432";
		city = "New York";
		telephone = "0983980447";
		address = "Street 1";
		
		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();
		
		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Log In' page");
		homePage.clickToMenuLink(driver, "header", "Log In");
		loginPage = PageGenerator.getLoginPage(driver);	
		
		log.info("Pre-Condition - Step 05: Set login page cookie");
		loginPage.setAllCookies(driver, Common_01_Login_User.loginPageCookie);
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
		shoppingCartPage.selectItemInDropdownByID(driver, "country",country);
		
		log.info("TC_01 - Step 03: Verify item is selected");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByID(driver, "country"),country);
		
		log.info("TC_01 - Step 04: Select 'State/Province' dropdown list");
		shoppingCartPage.selectItemInDropdownByID(driver, "region_id",state);
		
		log.info("TC_01 - Step 05: Verify item is selected");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByID(driver, "region_id"),state);
		
		log.info("TC_01 - Step 06: Enter to 'Zip' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "postcode", zip);
		
		log.info("TC_01 - Step 07: Click to 'Estimate' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Estimate");
		
		log.info("TC_01 - Step 08: Verify the shiping cost is generated");
		verifyTrue(shoppingCartPage.isShipingCostGeneratedDisplayed());
		
		log.info("TC_01 - Step 09: Check to 'Flate ' radio button");
		shoppingCartPage.checkToRadioButtonByID(driver, "s_method_flatrate_flatrate");
		
		log.info("TC_01 - Step 10: Click to 'Update Total' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Update Total");
		
		log.info("TC_01 - Step 11: Verify the grand total displayed includes shipping costs ");
		verifyEquals(shoppingCartPage.getTextGrandTotal(), "$620.00");
		
		log.info("TC_01 - Step 12: Click to 'Proceed to Checkout' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Proceed to Checkout");
		
		log.info("TC_01 - Step 13: Enter to 'Country' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "billing:street1", country);

		log.info("TC_01 - Step 14: Select 'State/Province' dropdown list");
		shoppingCartPage.selectItemInDropdownByID(driver, "billing:region_id",state);
		
		log.info("TC_01 - Step 15: Verify item is selected");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByID(driver, "billing:region_id"),state);
		
		log.info("TC_01 - Step 16: Enter to 'Zip' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "billing:postcode", zip);
		
		log.info("TC_01 - Step 17: Enter to 'City' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "billing:city", city);
		
		log.info("TC_01 - Step 18: Enter to 'Telephone' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "billing:telephone", telephone);
		
		log.info("TC_01 - Step 19: Click to 'Continue' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Continue");
		
		log.info("TC_01 - Step 20: Click to 'Continue' button");
		shoppingCartPage.clickToButtonContinueByOnclick(driver, "shippingMethod.save()");
		
		log.info("TC_01 - Step 21: Check to 'Method check mo' radio button");
		shoppingCartPage.checkToRadioButtonByID(driver, "p_method_checkmo");
		
		log.info("TC_01 - Step 22: Click to 'Continue' button");
		shoppingCartPage.clickToButtonContinueByOnclick(driver, "payment.save()");
		
		log.info("TC_01 - Step 23: Click to 'Place Order' button");
		shoppingCartPage.clickToButtonByTitle(driver, "Place Order");
		
		log.info("TC_01 - Step 24:Verify the page title is displayed");
		verifyTrue(shoppingCartPage.isPageTitleDisplayed());
		
		log.info("TC_01 - Step 25: Get the order number");
		String orderNumber = shoppingCartPage.getOrderNumber();
		
		log.info("TC_01 - Step 26: Verify order success with the order number is displayed ");
		verifyEquals(shoppingCartPage.getFullOrderedNumber(),"Your order # is:" + orderNumber);
		
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
